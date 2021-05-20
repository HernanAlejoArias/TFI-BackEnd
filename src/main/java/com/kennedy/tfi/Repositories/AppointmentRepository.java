package com.kennedy.tfi.Repositories;

import java.time.LocalDate;
import java.util.List;

import com.kennedy.tfi.models.Appointment;
import com.kennedy.tfi.models.MedicalDoctor;
import com.kennedy.tfi.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> findByDate(LocalDate date);

	List<Appointment> findByPatient(Patient Patient);

	// @Query(value =
	// "SELECT * FROM Appointment t WHERE DATE(date) >= ?2 and t.idPatient = ?1",
	// nativeQuery = true) List<Appointment> findActivosByidPatient(long idPatient,
	// LocalDate now);

	// @Query(value = "SELECT * FROM Appointment app WHERE DATE(date) >= ?2 and
	// app.MedicalDoctor.idMedicalDoctor = ?1", nativeQuery = true)
	// List<Appointment> findTurnosDelDiaByidMedicalDoctor(MedicalDoctor
	// MedicalDoctor, LocalDate now);

	List<Appointment> findByMedicalDoctorAndDate(MedicalDoctor MedicalDoctor, LocalDate date);

}
