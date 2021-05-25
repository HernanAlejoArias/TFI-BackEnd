package com.kennedy.tfi.Repositories;

import java.time.LocalDate;
import java.util.List;

import com.kennedy.tfi.models.Appointment;
import com.kennedy.tfi.models.MedicalDoctor;
import com.kennedy.tfi.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> findByDate(LocalDate date);

	List<Appointment> findByPatient(Patient Patient);

	List<Appointment> findByMedicalDoctorAndDate(MedicalDoctor MedicalDoctor, LocalDate date);

	Appointment findByPatientAndDate(Patient patient, LocalDate date);

}
