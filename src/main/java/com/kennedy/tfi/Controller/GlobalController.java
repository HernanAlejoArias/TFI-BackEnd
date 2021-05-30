package com.kennedy.tfi.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
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
import com.kennedy.tfi.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
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
    private AppointmentRepository appropointmentRepository;

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

        // Get today appointment for the User
        Appointment todayAppointment = appropointmentRepository.findByPatientAndDate(loggedUser.getPatient(),
                LocalDate.of(2021, 6, 1));

        // Get list of Today's Appointments for the MedicalDoctor
        Set<Appointment> MDAppointments = appropointmentRepository
                .findByMedicalDoctorAndDate(todayAppointment.getMedicalDoctor(), LocalDate.of(2021, 6, 1));

        return ResponseEntity.ok(makeUserWaitingRoomDTO(todayAppointment, MDAppointments));
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
                appETA.setEndETA(calculatedETA.plusMinutes(app.getDuration()));

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

}
