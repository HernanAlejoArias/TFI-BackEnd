package com.kennedy.tfi;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.kennedy.tfi.Repositories.AppointmentRepository;
import com.kennedy.tfi.Repositories.MedicalDoctorRepository;
import com.kennedy.tfi.Repositories.PatientRepository;
import com.kennedy.tfi.Repositories.UserRepository;
import com.kennedy.tfi.models.Appointment;
import com.kennedy.tfi.models.MedicalDoctor;
import com.kennedy.tfi.models.MyUser;
import com.kennedy.tfi.models.Patient;

import org.springframework.beans.factory.annotation.Autowired;
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
			// save a couple of Users
			MyUser tempUsr;

			tempUsr = new MyUser("usuario", "usuario", "usuario@gmail.com", true, "USER");
			userRepository.save(tempUsr);

			tempUsr = new MyUser("admin", "admin", "admin@gmail.com", true, "ADMIN");
			userRepository.save(tempUsr);

			// save a couple of MedicalDoctors
			MedicalDoctor tempDermatologia;
			tempDermatologia = new MedicalDoctor("Nelida", "Esposito", LocalDate.of(1961, 1, 8), 10565878, 545498,
					"Dermatologia");
			medicalDoctorRepository.save(tempDermatologia);

			MedicalDoctor tempClinica;
			tempClinica = new MedicalDoctor("Jose", "Lucia", LocalDate.of(1963, 4, 2), 11532998, 671538, "Clinico");
			medicalDoctorRepository.save(tempClinica);

			// save a couple of Patient & their Appointments
			Patient tempPatient;
			Appointment tempAppointment;

			tempPatient = new Patient("Hernan", "Arias", LocalDate.of(1982, 10, 9), 29747542, 1, "Lanus", false, false,
					false, false, false);
			patientRepository.save(tempPatient);

			// Patient
			tempPatient = new Patient("Marina", "Lucia", LocalDate.of(1982, 6, 30), 29747542, 2, "Lanus", true, true,
					false, false, false);
			patientRepository.save(tempPatient);

			// Appointment
			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(9, 0), false, LocalTime.of(9, 20),
					tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);

			tempPatient = new Patient("Julio", "Arias", LocalDate.of(1953, 1, 17), 10664879, 1, "Lanus", false, true,
					false, false, false);
			patientRepository.save(tempPatient);

			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(9, 20), false, LocalTime.of(9, 40),
					tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);

			tempPatient = new Patient("Beatriz", "Leguizamon", LocalDate.of(1955, 6, 25), 9215788, 2, "Lanus", true,
					false, false, false, false);
			patientRepository.save(tempPatient);

			tempAppointment = new Appointment(LocalDate.of(2021, 6, 1), LocalTime.of(9, 41), false, LocalTime.of(9, 55),
					tempClinica, tempPatient, true, false);
			appointmentRepository.save(tempAppointment);

			tempPatient = new Patient("Cristian", "Vallarino", LocalDate.of(1983, 2, 22), 30127532, 1, "Banfield", true,
					false, false, false, false);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("Victoria", "Arias", LocalDate.of(1980, 1, 25), 26742442, 2, "Lomas", true, false,
					false, false, false);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("Camila", "Garcia", LocalDate.of(1982, 7, 9), 30333982, 2, "Lomas", false, false,
					true, false, true);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("Gabriela", "Suarez", LocalDate.of(1981, 11, 11), 28447445, 2, "CABA", true,
					false, false, false, false);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("Francisco", "De Marco", LocalDate.of(1978, 9, 10), 25725839, 1, "CABA", true,
					true, false, false, false);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("Emilio", "Dissi", LocalDate.of(1970, 3, 3), 20876091, 1, "Lomas", true, true,
					true, true, false);
			patientRepository.save(tempPatient);
			// 10

			tempPatient = new Patient("Iris", "De Ojo", LocalDate.of(1985, 9, 1), 33445775, 2, "Lanus", true, false,
					false, false, false);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("Luciano", "Lunga", LocalDate.of(1981, 11, 11), 22987456, 1, "Lomas", true, false,
					false, false, false);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("Alejo", "Perez", LocalDate.of(1982, 2, 15), 29851753, 1, "Temperley", false,
					false, false, true, false);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("Paula", "Perez", LocalDate.of(1980, 2, 25), 29356543, 2, "Lanus", false, true,
					false, false, false);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("Julio", "Sanchez", LocalDate.of(1955, 5, 15), 12615229, 1, "Lanus", false, false,
					false, false, false);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("Laura", "Bonilla", LocalDate.of(1984, 5, 15), 33153323, 2, "Lanus", false, false,
					false, false, false);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("Andrea", "Gonzales", LocalDate.of(1982, 2, 27), 29445256, 2, "Temperlay", true,
					true, false, false, false);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("Olivia", "Perez", LocalDate.of(1952, 7, 7), 9234568, 2, "Lanus", true, false,
					false, false, false);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("Andres", "Maldini", LocalDate.of(1981, 1, 19), 28567532, 1, "Banfield", true,
					false, false, false, false);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("Sebastian", "Titolo", LocalDate.of(1980, 7, 3), 26123982, 1, "Lanus", true,
					false, false, false, false);
			patientRepository.save(tempPatient);
			// 20

			tempPatient = new Patient("Camila", "Ensilla", LocalDate.of(1990, 10, 2), 37091840, 2, "Lomas", false,
					false, true, false, true);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("Roberto", "Burgos", LocalDate.of(1982, 7, 7), 290911112, 1, "CABA", true, false,
					false, false, false);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("Hugo", "Fernandez", LocalDate.of(1977, 9, 10), 25763201, 1, "CABA", true, true,
					false, false, false);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("German", "Castro", LocalDate.of(1967, 1, 28), 18888881, 1, "Lomas", true, true,
					true, true, false);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("Sebastian", "Palmiero", LocalDate.of(1979, 9, 10), 261117625, 1, "CABA", true,
					false, false, false, false);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("Gustavo", "Bermudez", LocalDate.of(1986, 6, 28), 33554109, 1, "Lomas", true,
					false, false, false, false);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("Anabela", "Lucia", LocalDate.of(1983, 8, 12), 31311256, 1, "Temperley", false,
					false, false, true, false);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("Sebastian", "Saccani", LocalDate.of(1985, 12, 12), 30137911, 1, "CABA", true,
					false, false, false, false);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("Natalia", "Saccani", LocalDate.of(1989, 6, 13), 36009272, 2, "Lanus", true,
					false, false, false, false);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("Natalia", "Damele", LocalDate.of(1990, 1, 13), 40192827, 1, "Lanus", false,
					false, false, true, false);
			patientRepository.save(tempPatient);
			// 30

			tempPatient = new Patient("Eliana", "Matera", LocalDate.of(1994, 11, 19), 45029126, 1, "CABA", true, false,
					false, false, false);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("Florencia", "Persiani", LocalDate.of(1986, 9, 20), 33245927, 2, "Lomas", true,
					false, false, false, false);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("Florencia", "De Lucca", LocalDate.of(1991, 5, 12), 37726189, 2, "Lanus", false,
					false, false, true, false);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("Norma", "Ponce", LocalDate.of(1968, 5, 4), 13916645, 1, "CABA", true, false,
					false, false, false);
			patientRepository.save(tempPatient);

			tempPatient = new Patient("Florencia", "Salamanca", LocalDate.of(1995, 6, 13), 46782722, 2, "Lanus", true,
					false, false, false, false);
			patientRepository.save(tempPatient);
			// 35

			// Free Appointments
			tempAppointment = new Appointment(LocalDate.of(2021, 5, 1), LocalTime.of(9, 0), tempDermatologia);
			appointmentRepository.save(tempAppointment);

			List<Appointment> apps = appointmentRepository.findByMedicalDoctorAndDate(tempClinica,
					LocalDate.of(2021, 6, 1));

			apps = appointmentRepository.findByMedicalDoctorAndDate(tempDermatologia, LocalDate.of(2021, 5, 1));

			System.out.println("Dummy sentence");

		};
	}

}