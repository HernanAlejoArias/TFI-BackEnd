package com.kennedy.tfi.Repositories;

import java.util.Optional;

import com.kennedy.tfi.models.MedicalDoctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalDoctorRepository extends JpaRepository<MedicalDoctor, Long> {

}
