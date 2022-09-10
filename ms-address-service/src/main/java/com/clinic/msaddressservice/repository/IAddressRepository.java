package com.clinic.msaddressservice.repository;

import com.clinic.msaddressservice.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface IAddressRepository extends JpaRepository<Address,Long> {


    public Address findByPatientId(Long id);

    @Modifying
    @Transactional
    public void deleteByPatientId(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Address a SET a.street = ?2, a.door = ?3, a.city = ?4, a.state = ?5 WHERE a.patientId = ?1")
    public Address updateByPatientId(Long id, Address entity);
}
