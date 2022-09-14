package com.clinic.msappointmentservice.service;

import com.clinic.msappointmentservice.domain.Appointment;
import com.clinic.msappointmentservice.domain.dto.AppointmentToSendDto;

import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {

    boolean existsAppointmentAtDateD(Long dentistId, LocalDateTime date);

    boolean existsAppointmentAtDateP(Long patientId, LocalDateTime date);

    boolean existsAppointmentById(Long id);
    AppointmentToSendDto createAppointment(Appointment appointment);

    AppointmentToSendDto updateAppointment(Long id , Appointment appointment);

    AppointmentToSendDto findById(Long id);

    List<AppointmentToSendDto> findAll();

    List<AppointmentToSendDto> findByPatientId(Long patientId);

    List<AppointmentToSendDto> findByDentistId(Long dentistId);

    public void deleteAppointment(Long id);

}
