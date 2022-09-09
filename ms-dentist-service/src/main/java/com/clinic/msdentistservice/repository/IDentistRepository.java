package com.clinic.msdentistservice.repository;

import com.clinic.msdentistservice.domain.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IDentistRepository extends JpaRepository<Dentist, Long> {

    public boolean existsById(Long Id);

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM Dentist d WHERE d.register = ?1")
    public boolean registerAlreadyExists(Integer register);

}
