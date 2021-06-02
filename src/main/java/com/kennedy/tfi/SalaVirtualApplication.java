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

			// Test Case 1 - First User - Patient - Appointment 16:15
			tempUsr = new MyUser("haa", "haa", "haa@gmail.com", true, "USER");
			tempPatient = new Patient("Hernan", "Arias", LocalDate.of(1982, 10, 9), 29747542, Gender.MALE, "Lanus",
					false, false, false, false, false);

			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);

			userRepository.save(tempUsr);

			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(16, 45), tempClinica, tempPatient);
			appointmentRepository.save(tempAppointment);

			// Test Case 1 Scenario
			tempUsr = new MyUser("JulAri", "JulAri123", "Julio.Arias@gmail.com", true, "USER");
			tempPatient = new Patient("Julio", "Arias", LocalDate.of(1953, 1, 17), 10664879, Gender.MALE, "Lanus",
					false, true, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(9, 0), false, LocalTime.of(9, 20),
					tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("BeaLeg", "BeaLeg123", "Beatriz.Leguizamon@gmail.com", true, "USER");
			tempPatient = new Patient("Beatriz", "Leguizamon", LocalDate.of(1955, 6, 25), 9215788, Gender.FEMALE,
					"Lanus", true, false, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(9, 15), false, LocalTime.of(9, 35),
					tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("CriVal", "CriVal123", "Cristian.Vallarino@gmail.com", true, "USER");
			tempPatient = new Patient("Cristian", "Vallarino", LocalDate.of(1983, 2, 22), 30127532, Gender.MALE,
					"Banfield", true, false, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(9, 30), false, LocalTime.of(10, 0),
					tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("VicAri", "VicAri123", "Victoria.Arias@gmail.com", true, "USER");
			tempPatient = new Patient("Victoria", "Arias", LocalDate.of(1980, 1, 25), 26742442, Gender.FEMALE, "Lomas",
					true, false, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(9, 45), false,
					LocalTime.of(10, 15), tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("CamGar", "CamGar123", "Camila.Garcia@gmail.com", true, "USER");
			tempPatient = new Patient("Camila", "Garcia", LocalDate.of(1982, 7, 9), 30333982, Gender.FEMALE, "Lomas",
					false, false, true, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(10, 0), false,
					LocalTime.of(10, 30), tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("GabSua", "GabSua123", "Gabriela.Suarez@gmail.com", true, "USER");
			tempPatient = new Patient("Gabriela", "Suarez", LocalDate.of(1981, 11, 11), 28447445, Gender.FEMALE, "CABA",
					true, false, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(10, 15), false,
					LocalTime.of(10, 50), tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("FraDe ", "FraDe 123", "Francisco.De Marco@gmail.com", true, "USER");
			tempPatient = new Patient("Francisco", "De Marco", LocalDate.of(1978, 9, 10), 25725839, Gender.MALE, "CABA",
					true, true, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(10, 30), false,
					LocalTime.of(11, 0), tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("EmiDis", "EmiDis123", "Emilio.Dissi@gmail.com", true, "USER");
			tempPatient = new Patient("Emilio", "Dissi", LocalDate.of(1970, 3, 3), 20876091, Gender.MALE, "Lomas", true,
					true, true, true, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(10, 45), false,
					LocalTime.of(11, 15), tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("IriDe ", "IriDe 123", "Iris.De Ojo@gmail.com", true, "USER");
			tempPatient = new Patient("Iris", "De Ojo", LocalDate.of(1985, 9, 1), 33445775, Gender.FEMALE, "Lanus",
					true, false, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(11, 0), false,
					LocalTime.of(11, 35), tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("LucLun", "LucLun123", "Luciano.Lunga@gmail.com", true, "USER");
			tempPatient = new Patient("Luciano", "Lunga", LocalDate.of(1981, 11, 11), 22987456, Gender.MALE, "Lomas",
					true, false, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(11, 15), false,
					LocalTime.of(11, 40), tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("AlePer", "AlePer123", "Alejo.Perez@gmail.com", true, "USER");
			tempPatient = new Patient("Alejo", "Perez", LocalDate.of(1982, 2, 15), 29851753, Gender.MALE, "Temperley",
					false, false, false, true, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(11, 30), false,
					LocalTime.of(12, 0), tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("PauPer", "PauPer123", "Paula.Perez@gmail.com", true, "USER");
			tempPatient = new Patient("Paula", "Perez", LocalDate.of(1980, 2, 25), 29356543, Gender.FEMALE, "Lanus",
					false, true, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(11, 45), false,
					LocalTime.of(12, 15), tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("JulSan", "JulSan123", "Julio.Sanchez@gmail.com", true, "USER");
			tempPatient = new Patient("Julio", "Sanchez", LocalDate.of(1955, 5, 15), 12615229, Gender.MALE, "Lanus",
					false, false, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(12, 0), false,
					LocalTime.of(12, 35), tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("LauBon", "LauBon123", "Laura.Bonilla@gmail.com", true, "USER");
			tempPatient = new Patient("Laura", "Bonilla", LocalDate.of(1984, 5, 15), 33153323, Gender.FEMALE, "Lanus",
					false, false, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(12, 15), false,
					LocalTime.of(12, 55), tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("AndGon", "AndGon123", "Andrea.Gonzales@gmail.com", true, "USER");
			tempPatient = new Patient("Andrea", "Gonzales", LocalDate.of(1982, 2, 27), 29445256, Gender.FEMALE,
					"Temperlay", true, true, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(12, 30), false,
					LocalTime.of(13, 15), tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("OliPer", "OliPer123", "Olivia.Perez@gmail.com", true, "USER");
			tempPatient = new Patient("Olivia", "Perez", LocalDate.of(1952, 7, 7), 9234568, Gender.FEMALE, "Lanus",
					true, false, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(12, 45), false,
					LocalTime.of(13, 30), tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("AndMal", "AndMal123", "Andres.Maldini@gmail.com", true, "USER");
			tempPatient = new Patient("Andres", "Maldini", LocalDate.of(1981, 1, 19), 28567532, Gender.MALE, "Banfield",
					true, false, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(13, 0), false,
					LocalTime.of(13, 45), tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("SebTit", "SebTit123", "Sebastian.Titolo@gmail.com", true, "USER");
			tempPatient = new Patient("Sebastian", "Titolo", LocalDate.of(1980, 7, 3), 26123982, Gender.MALE, "Lanus",
					true, false, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(13, 15), false,
					LocalTime.of(14, 5), tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("CamEns", "CamEns123", "Camila.Ensilla@gmail.com", true, "USER");
			tempPatient = new Patient("Camila", "Ensilla", LocalDate.of(1990, 10, 2), 37091840, Gender.FEMALE, "Lomas",
					false, false, true, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(13, 30), false,
					LocalTime.of(14, 20), tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("RobBur", "RobBur123", "Roberto.Burgos@gmail.com", true, "USER");
			tempPatient = new Patient("Roberto", "Burgos", LocalDate.of(1982, 7, 7), 290911112, Gender.MALE, "CABA",
					true, false, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(13, 45), false,
					LocalTime.of(14, 45), tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("HugFer", "HugFer123", "Hugo.Fernandez@gmail.com", true, "USER");
			tempPatient = new Patient("Hugo", "Fernandez", LocalDate.of(1977, 9, 10), 25763201, Gender.MALE, "CABA",
					true, true, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(14, 0), false, LocalTime.of(15, 0),
					tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("GerCas", "GerCas123", "German.Castro@gmail.com", true, "USER");
			tempPatient = new Patient("German", "Castro", LocalDate.of(1967, 1, 28), 18888881, Gender.MALE, "Lomas",
					true, true, true, true, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(14, 15), false,
					LocalTime.of(15, 20), tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("SebPal", "SebPal123", "Sebastian.Palmiero@gmail.com", true, "USER");
			tempPatient = new Patient("Sebastian", "Palmiero", LocalDate.of(1979, 9, 10), 261117625, Gender.MALE,
					"CABA", true, false, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(14, 30), false,
					LocalTime.of(15, 30), tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("GusBer", "GusBer123", "Gustavo.Bermudez@gmail.com", true, "USER");
			tempPatient = new Patient("Gustavo", "Bermudez", LocalDate.of(1986, 6, 28), 33554109, Gender.MALE, "Lomas",
					true, false, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(14, 45), false,
					LocalTime.of(15, 45), tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("AnaLuc", "AnaLuc123", "Anabela.Lucia@gmail.com", true, "USER");
			tempPatient = new Patient("Anabela", "Lucia", LocalDate.of(1983, 8, 12), 31311256, Gender.MALE, "Temperley",
					false, false, false, true, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(15, 0), false, LocalTime.of(16, 5),
					tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("SebSac", "SebSac123", "Sebastian.Saccani@gmail.com", true, "USER");
			tempPatient = new Patient("Sebastian", "Saccani", LocalDate.of(1985, 12, 12), 30137911, Gender.MALE, "CABA",
					true, false, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(15, 15), false,
					LocalTime.of(16, 15), tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("NatSac", "NatSac123", "Natalia.Saccani@gmail.com", true, "USER");
			tempPatient = new Patient("Natalia", "Saccani", LocalDate.of(1989, 6, 13), 36009272, Gender.FEMALE, "Lanus",
					true, false, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(15, 30), false,
					LocalTime.of(16, 30), tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("NatDam", "NatDam123", "Natalia.Damele@gmail.com", true, "USER");
			tempPatient = new Patient("Natalia", "Damele", LocalDate.of(1990, 1, 13), 40192827, Gender.MALE, "Lanus",
					false, false, false, true, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(15, 45), false,
					LocalTime.of(16, 50), tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("EliMat", "EliMat123", "Eliana.Matera@gmail.com", true, "USER");
			tempPatient = new Patient("Eliana", "Matera", LocalDate.of(1994, 11, 19), 45029126, Gender.MALE, "CABA",
					true, false, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(16, 0), false, LocalTime.of(17, 0),
					tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("FloPer", "FloPer123", "Florencia.Persiani@gmail.com", true, "USER");
			tempPatient = new Patient("Florencia", "Persiani", LocalDate.of(1986, 9, 20), 33245927, Gender.FEMALE,
					"Lomas", true, false, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(16, 15), false, null, tempClinica,
					tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("FloDe ", "FloDe 123", "Florencia.De Lucca@gmail.com", true, "USER");
			tempPatient = new Patient("Florencia", "De Lucca", LocalDate.of(1991, 5, 12), 37726189, Gender.FEMALE,
					"Lanus", false, false, false, true, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(16, 30), false, null, tempClinica,
					tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("NorPon", "NorPon123", "Norma.Ponce@gmail.com", true, "USER");
			tempPatient = new Patient("Norma", "Ponce", LocalDate.of(1968, 5, 4), 13916645, Gender.MALE, "CABA", true,
					false, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(16, 45), false, null, tempClinica,
					tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("FloSal", "FloSal123", "Florencia.Salamanca@gmail.com", true, "USER");
			tempPatient = new Patient("Florencia", "Salamanca", LocalDate.of(1995, 6, 13), 46782722, Gender.FEMALE,
					"Lanus", true, false, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(17, 0), false, null, tempClinica,
					tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("VanApo", "VanApo123", "Vanesa.Apodaca@gmail.com", true, "USER");
			tempPatient = new Patient("Vanesa", "Apodaca", LocalDate.of(1982, 5, 14), 29786687, Gender.FEMALE, "Lanus",
					true, false, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(17, 15), false, null, tempClinica,
					tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("GabPer", "GabPer123", "Gabriel.Perez@gmail.com", true, "USER");
			tempPatient = new Patient("Gabriel", "Perez", LocalDate.of(1988, 3, 27), 35781115, Gender.MALE, "Lanus",
					true, false, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(17, 30), false, null, tempClinica,
					tempPatient, true, false);
			appointmentRepository.save(tempAppointment);
			tempUsr = new MyUser("GonMar", "GonMar123", "Gonzalo.Marcec@gmail.com", true, "USER");
			tempPatient = new Patient("Gonzalo", "Marcec", LocalDate.of(1984, 7, 10), 31766985, Gender.MALE, "Lanus",
					true, false, false, false, false);
			tempUsr.setPatient(tempPatient);
			tempPatient.setUser(tempUsr);
			userRepository.save(tempUsr);
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(17, 45), false, null, tempClinica,
					tempPatient, true, false);
			appointmentRepository.save(tempAppointment);

		};
	}
}