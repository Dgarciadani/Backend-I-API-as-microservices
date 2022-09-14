package com.clinic.msappointmentservice.repository;

import com.clinic.msappointmentservice.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment,Long> {

    boolean existsAppointmentById(Long id);

    @Query("SELECT a FROM Appointment a WHERE a.patient_id = ?1")
    List<Appointment> findAppointmentsByPatient_id(Long PatientId);

    @Query("SELECT a FROM Appointment a WHERE a.dentist_id = ?1")
    List<Appointment> findAppointmentsByDentist_id(Long DentistId);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Appointment a WHERE a.dentist_id = ?1 AND a.date = ?2")
    boolean existsAppointmentAtDateD(Long dentist_id,LocalDateTime date);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Appointment a WHERE a.patient_id = ?1 AND a.date = ?2")
    boolean existsAppointmentAtDateP(Long patient_id,LocalDateTime date);
}
