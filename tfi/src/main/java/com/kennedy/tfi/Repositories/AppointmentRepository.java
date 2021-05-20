package com.kennedy.tfi.Repositories;

import java.time.LocalDate;
import java.util.List;

import com.kennedy.tfi.models.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	@Query(value = "SELECT * FROM Turno t WHERE DATE(fecha) >= ?2 and t.idPaciente = ?1", nativeQuery = true)
	List<Appointment> findActivosByidPatient(long idPatient, LocalDate now);

	List<Appointment> findByDate(LocalDate date);

	List<Appointment> findByidPatient(long idPatient);

	@Query(value = "SELECT * FROM Turno t WHERE DATE(date) >= ?2 and t.idMedicalDoctor = ?1", nativeQuery = true)
	List<Appointment> findTurnosDelDiaByidMedicalDoctor(long idMedicalDoctor, LocalDate now);

}
