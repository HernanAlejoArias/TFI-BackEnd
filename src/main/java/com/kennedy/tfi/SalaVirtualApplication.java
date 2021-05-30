package com.kennedy.tfi;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.kennedy.tfi.Repositories.AppointmentRepository;
import com.kennedy.tfi.Repositories.MedicalDoctorRepository;
import com.kennedy.tfi.Repositories.PatientRepository;
import com.kennedy.tfi.Repositories.UserRepository;
import com.kennedy.tfi.constants.Gender;
import com.kennedy.tfi.models.Appointment;
import com.kennedy.tfi.models.MedicalDoctor;
import com.kennedy.tfi.models.MyUser;
import com.kennedy.tfi.models.Patient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SalaVirtualApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalaVirtualApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(UserRepository userRepository, MedicalDoctorRepository medicalDoctorRepository,
			PatientRepository patientRepository, AppointmentRepository appointmentRepository) {
		return (args) -> {
			// save a couple of MedicalDoctors
			MedicalDoctor tempDermatologia;
			MedicalDoctor tempClinica;

			Appointment tempAppointment;
			MyUser tempUsr;
			Patient tempPatient;

			tempDermatologia = new MedicalDoctor("Nelida", "Esposito", LocalDate.of(1961, 1, 8), 10565878, 545498,
					"Dermatologia");
			medicalDoctorRepository.save(tempDermatologia);

			tempClinica = new MedicalDoctor("Jose", "Lucia", LocalDate.of(1963, 4, 2), 11532998, 671538, "Clinico");
			medicalDoctorRepository.save(tempClinica);

			// First User - Patient - Appointment 16:15
			tempUsr = new MyUser("haa", "haa", "haa@gmail.com", true, "USER");
			tempPatient = new Patient("Hernan", "Arias", LocalDate.of(1982, 10, 9), 29747542, Gender.MALE, "Lanus",
					false, false, false, false, false);

			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);

			userRepository.save(tempUsr);

			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(16, 15), tempClinica, tempPatient);
			appointmentRepository.save(tempAppointment);

			tempUsr = new MyUser("spal", "spal", "spal@gmail.com", true, "USER");
			tempPatient = new Patient("Sebastian", "Palmiero", LocalDate.of(1979, 9, 10), 261117625, Gender.MALE,
					"CABA", true, false, false, false, false);

			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);

			userRepository.save(tempUsr);

			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(15, 20), false,
					LocalTime.of(15, 30), tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);

			tempUsr = new MyUser("ssac", "ssac", "ssac@gmail.com", true, "USER");
			tempPatient = new Patient("Sebastian", "Saccani", LocalDate.of(1985, 12, 12), 30137911, Gender.MALE, "CABA",
					true, false, false, false, false);

			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);

			userRepository.save(tempUsr);

			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(16, 5), false,
					LocalTime.of(16, 15), tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);

			tempUsr = new MyUser("aluc", "aluc", "aluc@gmail.com", true, "USER");
			tempPatient = new Patient("Anabela", "Lucia", LocalDate.of(1983, 8, 12), 31311256, Gender.FEMALE,
					"Temperley", false, false, false, true, false);

			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);

			userRepository.save(tempUsr);

			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(15, 45), false,
					LocalTime.of(16, 5), tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);

		};
	}
}