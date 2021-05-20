package com.kennedy.tfi.Repositories;

import com.kennedy.tfi.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
