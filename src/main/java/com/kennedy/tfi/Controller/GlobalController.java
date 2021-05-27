package com.kennedy.tfi.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.kennedy.tfi.MyUserDetailsService;
import com.kennedy.tfi.Repositories.AppointmentRepository;
import com.kennedy.tfi.Repositories.PatientRepository;
import com.kennedy.tfi.Repositories.UserRepository;
import com.kennedy.tfi.models.Appointment;
import com.kennedy.tfi.models.AppointmentETA;
import com.kennedy.tfi.models.AuthenticationRequest;
import com.kennedy.tfi.models.AuthenticationResponse;
import com.kennedy.tfi.models.MyUser;
import com.kennedy.tfi.models.Patient;
import com.kennedy.tfi.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appropointmentRepository;

    @RequestMapping({ "/hello" })
    public String firstPage() {
        return "Hello World";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {

        System.out.println("Dummy sentence: in Authenticate");

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

    @RequestMapping(value = "/user-waiting-room/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserWaitingRoom(@PathVariable("username") String username) {

        MyUser loggedUser = userRepository.findByUsername(username);

        // Get today appointment for the User
        Appointment todayAppointment = appropointmentRepository.findByPatientAndDate(loggedUser.getPatient(),
                LocalDate.of(2021, 6, 1));

        // Get list of Today's Appointments for the MedicalDoctor
        Set<Appointment> MDAppointments = appropointmentRepository
                .findByMedicalDoctorAndDate(todayAppointment.getMedicalDoctor(), LocalDate.of(2021, 6, 1));

        System.out.println("Dummy sentence: in user-waiting-room");

        return ResponseEntity.ok(makeUserWaitingRoomDTO(todayAppointment, MDAppointments));
    }

    public Map<String, Object> makeUserWaitingRoomDTO(Appointment todayAppointment, Set<Appointment> MDAppointments) {
        Map<String, Object> dtoResponse = new LinkedHashMap<>();

        List<Appointment> apps = MDAppointments.stream().sorted(Comparator.comparing(Appointment::getTime))
                .filter(app -> app.getTime().isBefore(todayAppointment.getTime())).collect(Collectors.toList());

        Set<AppointmentETA> appsETA = new HashSet<>();
        LocalTime calculatedETA = apps.get(0).getTime();
        AppointmentETA appETA;
        int lastAppointmentPos = 0;
        int pos = 0;

        for (Appointment app : apps) {

            appETA = new AppointmentETA(app);

            if (appETA.getAppointment().getEndTime() == null) {

                appETA.setStartETA(calculatedETA);
                appETA.setEndETA(calculatedETA.plusMinutes(appETA.getAppointment().getDuration()));

            } else {

                calculatedETA = appETA.getAppointment().getEndTime();
                lastAppointmentPos = pos;

            }

            appETA.calculateStatus();

            if (apps.size() < 2) {
                appsETA.add(appETA);
            } else if ((pos + 1) > (apps.size() - 2)) {
                appsETA.add(appETA);
            }
            pos = pos + 1;
        }

        AppointmentETA todayAppointmentETA = new AppointmentETA(todayAppointment);
        todayAppointmentETA.setStartETA(calculatedETA);
        todayAppointmentETA.setEndETA(calculatedETA.plusMinutes(todayAppointment.getDuration()));
        todayAppointmentETA.calculateStatus();

        dtoResponse.put("prev-appointments", appsETA);
        dtoResponse.put("user-appointment", todayAppointmentETA);

        return dtoResponse;
    }

    /*
     * private List<Appointment> calculateETAs(Appointment todayAppointment,
     * Set<Appointment> MDAppointments) {
     * 
     * List<Appointment> apps =
     * MDAppointments.stream().sorted(Comparator.comparing(Appointment::getTime))
     * .collect(Collectors.toList());
     * 
     * LocalTime calculatedETA = apps.get(0).getTime(); AppointmentETA appETA;
     * 
     * for (Appointment app : apps) {
     * 
     * if (app.getEndTime() == null) { app.setStartETA(calculatedETA);
     * app.setEndETA(calculatedETA.plusMinutes(app.getDuration()));
     * 
     * } else { calculatedETA = app.getEndTime(); } }
     * 
     * return apps; }
     */

    public Map<String, Object> makeRegisterResponseDTO(String jwt, MyUser user) {
        Map<String, Object> dtoResponse = new LinkedHashMap<>();
        Map<String, Object> dtoUser = new LinkedHashMap<>();
        dtoResponse.put("jwt", jwt);
        dtoUser = user.makeMyUserDTO();
        dtoResponse.put("user", dtoUser);
        return dtoResponse;
    }

}
