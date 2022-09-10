package com.clinic.mspatientservice.repository;

import com.clinic.mspatientservice.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long> {

    public boolean existsById(Long id);

    public boolean existsByDni(String dni);


}
