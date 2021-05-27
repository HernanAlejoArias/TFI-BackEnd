package com.kennedy.tfi.Repositories;

import java.time.LocalDate;
import java.util.Set;

import com.kennedy.tfi.models.Appointment;
import com.kennedy.tfi.models.MedicalDoctor;
import com.kennedy.tfi.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	Set<Appointment> findByDate(LocalDate date);

	Set<Appointment> findByPatient(Patient Patient);

	Set<Appointment> findByMedicalDoctorAndDate(MedicalDoctor MedicalDoctor, LocalDate date);

	Appointment findByPatientAndDate(Patient patient, LocalDate date);

}
