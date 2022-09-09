package com.clinic.msdentistservice.repository;

import com.clinic.msdentistservice.domain.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryDentist extends JpaRepository<Dentist, Long> {

}
