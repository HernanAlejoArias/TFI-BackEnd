package com.kennedy.tfi.Repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.kennedy.tfi.models.Appointment;
import com.kennedy.tfi.models.MedicalDoctor;
import com.kennedy.tfi.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	Set<Appointment> findByDate(LocalDate date);

	Set<Appointment> findByPatient(Patient Patient);

	Set<Appointment> findByPatientAndStatus(Patient Patient, String status);

	Set<Appointment> findByMedicalDoctorAndDate(MedicalDoctor MedicalDoctor, LocalDate date);

	Appointment findByPatientAndDate(Patient patient, LocalDate date);

	@Query(value = "SELECT * FROM Appointment app WHERE DATE(date) >= ?1 and app.MedicalDoctor.idMedicalDoctor = ?2 and ( early_monday = true or early_tuesday = true )", nativeQuery = true)
	List<Appointment> findCandidates(LocalDate canceledAppDate, MedicalDoctor MedicalDoctor);

}
