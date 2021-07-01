package com.kennedy.tfi.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.kennedy.tfi.MyUserDetailsService;
import com.kennedy.tfi.Repositories.AppointmentRepository;
import com.kennedy.tfi.Repositories.MedicalDoctorRepository;
import com.kennedy.tfi.Repositories.PatientRepository;
import com.kennedy.tfi.Repositories.UserRepository;
import com.kennedy.tfi.models.AI;
import com.kennedy.tfi.models.Appointment;
import com.kennedy.tfi.models.AppointmentETA;
import com.kennedy.tfi.models.AuthenticationRequest;
import com.kennedy.tfi.models.AuthenticationResponse;
import com.kennedy.tfi.models.MedicalDoctor;
import com.kennedy.tfi.models.MyUser;
import com.kennedy.tfi.models.Patient;
import com.kennedy.tfi.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class GlobalController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedicalDoctorRepository medicalDoctorRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping({ "/hello" })
    public String firstPage() {
        return "Hello World";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody MyUser user) throws Exception {

        user.setActive(true);
        user.setRoles("USER");

        MyUser savedUser = userRepository.save(user);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(savedUser.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(makeRegisterResponseDTO(jwt, savedUser));

    };

    @RequestMapping(value = "/user-waiting-room", method = RequestMethod.GET)
    public ResponseEntity<?> getUserWaitingRoom(@RequestHeader("authorization") String autParam) {

        String username = null;
        String jwt = null;

        jwt = autParam.substring(7);
        username = jwtUtil.extractUsername(jwt);

        MyUser loggedUser = userRepository.findByUsername(username);

        // Get today appointment for the User --- COMMENTED FOR TEST PURPOSES
        // Appointment todayAppointment =
        // appropointmentRepository.findByPatientAndDate(loggedUser.getPatient(),
        // LocalDate.of(2021, 6, 1));

        Appointment todayAppointment = appointmentRepository.findByPatient(loggedUser.getPatient()).stream().findFirst()
                .orElse(null);

        // Get list of Today's Appointments for the MedicalDoctor
        Set<Appointment> MDAppointments = appointmentRepository
                .findByMedicalDoctorAndDate(todayAppointment.getMedicalDoctor(), todayAppointment.getDate());

        return ResponseEntity.ok(makeUserWaitingRoomDTO(todayAppointment, MDAppointments));
    }

    @RequestMapping(value = "/user-appointments", method = RequestMethod.GET)
    public ResponseEntity<?> getUserAppointments(@RequestHeader("authorization") String autParam) {

        String username = null;
        String jwt = null;

        jwt = autParam.substring(7);
        username = jwtUtil.extractUsername(jwt);

        MyUser loggedUser = userRepository.findByUsername(username);

        Set<Appointment> userAppointments = appointmentRepository
                .findByPatientAndStatus(loggedUser.getPatient(), "Creado").stream()
                .sorted(Comparator.comparing(Appointment::getId)).collect(Collectors.toSet());

        return ResponseEntity.ok(makeUserAppointmentsDTO(userAppointments));
    }

    public Map<String, Object> makeUserAppointmentsDTO(Set<Appointment> userApps) {
        Map<String, Object> dtoResponse = new LinkedHashMap<>();
        List<Object> apps = new ArrayList<>();

        for (Appointment app : userApps) {
            Map<String, Object> dtoApp = new LinkedHashMap<>();
            dtoApp.put("id", app.getId());
            dtoApp.put("specialism", app.getMedicalDoctor().getSpecialism());
            dtoApp.put("medicalDoctor", app.getMedicalDoctor().getCompleteName());
            dtoApp.put("date", app.getDate());
            dtoApp.put("time", app.getTime());

            if (app.getEarlyDayAppointment() > 0 || app.isEarlyMonday() || app.isEarlyTuesday()
                    || app.isEarlyWednesday() || app.isEarlyThrusday() || app.isEarlyFriday()) {
                dtoApp.put("early", true);
            } else {
                dtoApp.put("early", false);
            }

            apps.add(dtoApp);
        }

        dtoResponse.put("appointments", apps);

        return dtoResponse;
    }

    public Map<String, Object> makeUserWaitingRoomDTO(Appointment todayAppointment, Set<Appointment> MDAppointments) {
        Map<String, Object> dtoResponse = new LinkedHashMap<>();

        List<Appointment> apps = MDAppointments.stream().sorted(Comparator.comparing(Appointment::getTime))
                .filter(app -> app.getTime().isBefore(todayAppointment.getTime())).collect(Collectors.toList());

        List<AppointmentETA> appsETA = new ArrayList<>();
        LocalTime calculatedETA = apps.get(0).getTime();
        AppointmentETA appETA;
        int pos = 0;

        for (Appointment app : apps) {

            appETA = new AppointmentETA();

            if (app.getEndTime() == null) {

                appETA.setStartETA(calculatedETA);
                calculatedETA = calculatedETA.plusMinutes(app.getDuration());
                appETA.setEndETA(calculatedETA);

            } else {

                appETA.setStartETA(calculatedETA);
                calculatedETA = app.getEndTime();
                appETA.setEndETA(calculatedETA);

            }

            if ((apps.size() < 2) || ((pos + 1) > (apps.size() - 2))) {
                appETA.calculateStatus(app.getTime(), app.getEndTime(), app.getStatus());

                appsETA.add(appETA);
            }

            pos = pos + 1;
        }

        AppointmentETA todayAppointmentETA = new AppointmentETA(calculatedETA,
                calculatedETA.plusMinutes(todayAppointment.getDuration()));
        todayAppointmentETA.calculateStatus(todayAppointment.getTime(), todayAppointment.getEndTime(),
                todayAppointment.getStatus());

        dtoResponse.put("prevAppointments", appsETA);
        dtoResponse.put("userAppointment", makeUserAppointmentDTO(todayAppointment, todayAppointmentETA));

        return dtoResponse;
    }

    public Map<String, Object> makeUserAppointmentDTO(Appointment app, AppointmentETA appETA) {
        Map<String, Object> dtoResponse = new LinkedHashMap<>();
        Map<String, Object> dtoApp = new LinkedHashMap<>();
        dtoApp.put("medicalDoctor", app.getMedicalDoctor().getCompleteName());
        dtoApp.put("specialism", app.getMedicalDoctor().getSpecialism());
        dtoApp.put("time", app.getTime());

        dtoResponse.put("appointment", dtoApp);
        dtoResponse.put("appointmentETA", appETA);

        return dtoResponse;
    }

    public Map<String, Object> makeRegisterResponseDTO(String jwt, MyUser user) {
        Map<String, Object> dtoResponse = new LinkedHashMap<>();
        Map<String, Object> dtoUser = new LinkedHashMap<>();
        dtoResponse.put("jwt", jwt);
        dtoUser = user.makeMyUserDTO();
        dtoResponse.put("user", dtoUser);
        return dtoResponse;
    }

    @RequestMapping(value = "/register-appointment", method = RequestMethod.POST)
    public ResponseEntity<?> registerAppointment(@RequestHeader("authorization") String autParam,
            @RequestBody AppointmentController appointmentController) throws Exception {

        String username = null;
        String jwt = null;

        jwt = autParam.substring(7);
        username = jwtUtil.extractUsername(jwt);

        MyUser loggedUser = userRepository.findByUsername(username);
        Patient loggedPatient = patientRepository.findByUser(loggedUser);

        Optional<MedicalDoctor> tempMDoOptional;
        tempMDoOptional = medicalDoctorRepository.findById(appointmentController.getMd());

        if (tempMDoOptional.isPresent()) {

            Appointment newAppointment = new Appointment(appointmentController.getDate(),
                    appointmentController.getTime(), tempMDoOptional.get(), loggedPatient,
                    appointmentController.getEarlyDayAppointment(), appointmentController.isEarlyMonday(),
                    appointmentController.isEarlyTuesday(), appointmentController.isEarlyWednesday(),
                    appointmentController.isEarlyThrusday(), appointmentController.isEarlyFriday(),
                    appointmentController.isEarlyMorning(), appointmentController.isEarlyAfternoon());
            appointmentRepository.save(newAppointment);

            return ResponseEntity.ok(newAppointment);

        } else {
            return new ResponseEntity<>("No MD", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/cancel-appointment/{appId}", method = RequestMethod.POST)
    public ResponseEntity<?> cancelAppointment(@RequestHeader("authorization") String autParam,
            @PathVariable long appId) throws Exception {

        String username = null;
        String jwt = null;

        jwt = autParam.substring(7);
        username = jwtUtil.extractUsername(jwt);

        MyUser loggedUser = userRepository.findByUsername(username);
        Patient loggedPatient = patientRepository.findByUser(loggedUser);

        Optional<Appointment> canceledAppointment;
        canceledAppointment = appointmentRepository.findById(appId);

        if (canceledAppointment.isPresent()) {

            canceledAppointment.get().setStatus("Cancelado");
            canceledAppointment.get().setAvailable(true);

            // List<Appointment> appointments =
            // appointmentRepository.findCandidates(canceledAppointment.get().getDate(),
            // canceledAppointment.get().getMedicalDoctor());

            // List<AI> result = appointments.stream()
            // .map(app -> new AI(app.getVisitType(), app.getCreation(), app.getDate(),
            // app.getTime(),
            // app.getPatient().getBirthday(), app.getPatient().getNeighborhood(),
            // app.isFirstVisit(),
            // app.getPatient().getPriorNoShows()))
            // .sorted(Comparator.comparing(AI::calculateShowRate)).collect(Collectors.toList());

            canceledAppointment.get().setPatient(null);
            appointmentRepository.save(canceledAppointment.get());
            return ResponseEntity.ok(canceledAppointment.get());

        } else {
            return new ResponseEntity<>("No Appointment", HttpStatus.NOT_FOUND);
        }
    }

}
