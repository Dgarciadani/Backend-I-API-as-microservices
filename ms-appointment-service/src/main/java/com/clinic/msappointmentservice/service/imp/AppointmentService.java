package com.clinic.msappointmentservice.service.imp;

import com.clinic.msappointmentservice.client.IDentistClient;
import com.clinic.msappointmentservice.client.IPatientClient;
import com.clinic.msappointmentservice.domain.Appointment;
import com.clinic.msappointmentservice.domain.dto.AppointmentToSendDto;
import com.clinic.msappointmentservice.exceptions.DateNotAvailableException;
import com.clinic.msappointmentservice.exceptions.ResourceNotFoundException;
import com.clinic.msappointmentservice.repository.IAppointmentRepository;
import com.clinic.msappointmentservice.service.IAppointmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
public class AppointmentService implements IAppointmentService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(AppointmentService.class);
    private final IAppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;

    private final IDentistClient dentistClient;

    private final IPatientClient patientClient;


    @Override
    public boolean existsAppointmentAtDateD(Long dentistId, LocalDateTime date) {
        return appointmentRepository.existsAppointmentAtDateD(dentistId, date);
    }

    @Override
    public boolean existsAppointmentAtDateP(Long patientId, LocalDateTime date) {
        return appointmentRepository.existsAppointmentAtDateP(patientId, date);
    }

    @Override
    public boolean existsAppointmentById(Long id) {
        return appointmentRepository.existsAppointmentById(id);
    }

    @Override
    public AppointmentToSendDto createAppointment( Appointment appointment) {
        try {
            if (appointmentRepository.existsAppointmentAtDateD(appointment.getDentist_id(), appointment.getDate())) {
                throw new DateNotAvailableException("The date is not available for the dentist");
            } else if (appointmentRepository.existsAppointmentAtDateP(appointment.getPatient_id(), appointment.getDate())) {
                throw new DateNotAvailableException("The date is not available for the patient");
            } else if (Boolean.TRUE.equals(dentistClient.existsById(appointment.getDentist_id()).getBody()) && Boolean.TRUE.equals(patientClient.existsById(appointment.getPatient_id()).getBody())) {
                appointmentRepository.save(appointment);
                logger.info("Appointment created");
                AppointmentToSendDto appointmentToSendDto = modelMapper.map(appointment, AppointmentToSendDto.class);
                appointmentToSendDto.setDentist(dentistClient.findById(appointment.getDentist_id()).getBody());
                appointmentToSendDto.setPatient(patientClient.findById(appointment.getPatient_id()).getBody());
                return appointmentToSendDto;
            } else {
                throw new RuntimeException("The dentist or patient does not exist");
            }
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error creating the appointment: "+ e.getMessage());
        }

    }

    @Override
    public AppointmentToSendDto updateAppointment(Long id, Appointment appointment) {
        return modelMapper.map(appointmentRepository.save(appointment), AppointmentToSendDto.class);
    }

    @Override
    public AppointmentToSendDto findById(Long id) {
        if (this.existsAppointmentById(id)) {
            Appointment appointment = appointmentRepository.findById(id).get();
            AppointmentToSendDto appointmentToSendDto = modelMapper.map(appointment, AppointmentToSendDto.class);
            appointmentToSendDto.setPatient(patientClient.findById(appointment.getPatient_id()).getBody());
            appointmentToSendDto.setDentist(dentistClient.findById(appointment.getDentist_id()).getBody());
            return appointmentToSendDto;
        } else {
            throw new ResourceNotFoundException("Appointment not found");
        }
    }

    @Override
    public List<AppointmentToSendDto> findAll() {
        if (appointmentRepository.findAll().isEmpty()) {
            throw new ResourceNotFoundException("Appointment not found");
        } else {
            List<Appointment> appointments = appointmentRepository.findAll();
            List<AppointmentToSendDto> appointmentToSendDtos = modelMapper.map(appointments, List.class);
            for (int i = 0; i < appointmentToSendDtos.size(); i++) {
                appointmentToSendDtos.get(i).setPatient(patientClient.findById(appointments.get(i).getPatient_id()).getBody());
                appointmentToSendDtos.get(i).setDentist(dentistClient.findById(appointments.get(i).getDentist_id()).getBody());
            }
            return appointmentToSendDtos;
        }
    }

    @Override
    public List<AppointmentToSendDto> findByPatientId(Long patientId) {
        if (appointmentRepository.findAppointmentsByPatient_id(patientId).isEmpty()) {
            throw new ResourceNotFoundException("Appointments not found");
        } else {
            List<Appointment> appointments = appointmentRepository.findAppointmentsByPatient_id(patientId);
            List<AppointmentToSendDto> appointmentToSendDtos = modelMapper.map(appointments, List.class);
            for (int i = 0; i < appointmentToSendDtos.size(); i++) {
                appointmentToSendDtos.get(i).setPatient(patientClient.findById(appointments.get(i).getPatient_id()).getBody());
                appointmentToSendDtos.get(i).setDentist(dentistClient.findById(appointments.get(i).getDentist_id()).getBody());
            }
            return appointmentToSendDtos;
        }
    }

    @Override
    public List<AppointmentToSendDto> findByDentistId(Long dentistId) {
        if (appointmentRepository.findAppointmentsByDentist_id(dentistId).isEmpty()) {
            throw new ResourceNotFoundException("Appointments not found");
        } else {
            List<Appointment> appointments = appointmentRepository.findAppointmentsByDentist_id(dentistId);
            List<AppointmentToSendDto> appointmentToSendDtos = modelMapper.map(appointments, List.class);
            for (int i = 0; i < appointmentToSendDtos.size(); i++) {
                appointmentToSendDtos.get(i).setPatient(patientClient.findById(appointments.get(i).getPatient_id()).getBody());
                appointmentToSendDtos.get(i).setDentist(dentistClient.findById(appointments.get(i).getDentist_id()).getBody());
            }
            return appointmentToSendDtos;
        }
    }


}
