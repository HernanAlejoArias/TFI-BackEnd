package com.kennedy.tfi;

import java.time.LocalDate;

import com.kennedy.tfi.Repositories.MedicalDoctorRepository;
import com.kennedy.tfi.Repositories.PatientRepository;
import com.kennedy.tfi.Repositories.UserRepository;
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
			PatientRepository patientRepository) {
		return (args) -> {
			// save a couple of Users
			MyUser usuarioTest = new MyUser("test", "test", "test@gmail.com", true, "USER");

			userRepository.save(usuarioTest);

			// save a couple of MedicalDoctors
			MedicalDoctor tempMD = new MedicalDoctor("Nelida", "Esposito", LocalDate.of(1961, 1, 8), 10565878, 545498,
					"Dermatologia");

			medicalDoctorRepository.save(tempMD);

			// save a couple of Patient
			Patient tempPatient = new Patient("Hernan", "Arias", LocalDate.of(1982, 10, 9), 29747542, 1, "Lanus", true,
					false, false, false, false);

			patientRepository.save(tempPatient);
		};
	}

}
