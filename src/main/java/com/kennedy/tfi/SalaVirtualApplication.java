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
	public CommandLineRunner initData(MedicalDoctorRepository medicalDoctorRepository) {
		return (args) -> {
			// save a couple of MedicalDoctors MedicalDoctor
			MedicalDoctor tempDermatologia;
			MedicalDoctor tempClinica;
			tempDermatologia = new MedicalDoctor("Nelida", "Esposito", LocalDate.of(1961, 1, 8), 10565878, 545498,
					"Dermatologia");
			medicalDoctorRepository.save(tempDermatologia);

			tempClinica = new MedicalDoctor("Jose", "Lucia", LocalDate.of(1963, 4, 2), 11532998, 671538, "Clinico");
			medicalDoctorRepository.save(tempClinica);

		};
	}
}