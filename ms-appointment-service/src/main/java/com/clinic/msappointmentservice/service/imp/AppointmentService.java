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
    public AppointmentToSendDto createAppointment(Appointment appointment) {
        logger.info("Creating appointment");
        try {
            if (!this.existsAppointmentAtDateD(appointment.getDentist_id(), appointment.getDate())) {
                throw new DateNotAvailableException("The date is not available for the dentist");
            } else if (!this.existsAppointmentAtDateP(appointment.getPatient_id(), appointment.getDate())) {
                throw new DateNotAvailableException("The date is not available for the patient");
            } else if (Boolean.TRUE.equals(dentistClient.existsById(appointment.getDentist_id()).getBody()) && Boolean.TRUE.equals(patientClient.existsById(appointment.getPatient_id()).getBody())) {
                logger.info("dentist and patient exists");
                appointmentRepository.save(appointment);
                logger.info("Appointment created");
                AppointmentToSendDto appointmentToSendDto = mapToAppointmentDto(appointment);
                setPatientAndDentis(appointment.getPatient_id(), appointment.getDentist_id(), appointmentToSendDto);
                return appointmentToSendDto;
            } else {
                throw new RuntimeException("The dentist or patient does not exist");
            }
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error creating the appointment: " + e.getMessage());
        }

    }

    @Override
    public AppointmentToSendDto updateAppointment(Long id, Appointment appointment) {
        if (this.existsAppointmentById(id)) {
            logger.info("Updating appointment");
            appointment.setId(id);
            appointmentRepository.save(appointment);
            AppointmentToSendDto appointmentToSendDto = mapToAppointmentDto(appointment);
            setPatientAndDentis(appointment.getPatient_id(), appointment.getDentist_id(), appointmentToSendDto);
            logger.info("Appointment updated");
            return appointmentToSendDto;

        } else {
            throw new ResourceNotFoundException("The appointment does not exist");
        }

    }

    @Override
    public AppointmentToSendDto findById(Long id) {
        if (this.existsAppointmentById(id)) {
            Appointment appointment = appointmentRepository.findById(id).get();
            AppointmentToSendDto appointmentToSendDto = mapToAppointmentDto(appointment);
            setPatientAndDentis(appointment.getPatient_id(), appointment.getDentist_id(), appointmentToSendDto);
            return appointmentToSendDto;
        } else {
            throw new ResourceNotFoundException("Appointment not found");
        }
    }

    @Override
    public List<AppointmentToSendDto> findAll() {
        List<Appointment> appointments = appointmentRepository.findAll();
        if (appointments.isEmpty()) {
            throw new ResourceNotFoundException("Appointment not found");
        } else {
            List<AppointmentToSendDto> appointmentToSendDtos = mapToAppointmentList(appointments);
            for (int i = 0; i < appointmentToSendDtos.size(); i++) {
                setPatientAndDentis(appointments.get(i).getPatient_id(), appointments.get(i).getDentist_id(), appointmentToSendDtos.get(i));
            }
            return appointmentToSendDtos;
        }
    }

    @Override
    public List<AppointmentToSendDto> findByPatientId(Long patientId) {
        List<Appointment> appointments = appointmentRepository.findAppointmentsByPatient_id(patientId);
        List<AppointmentToSendDto> appointmentToSendDtos = mapToAppointmentList(appointments);
        for (int i = 0; i < appointmentToSendDtos.size(); i++) {
            setPatientAndDentis(appointments.get(i).getPatient_id(), appointments.get(i).getDentist_id(), appointmentToSendDtos.get(i));
        }
        return appointmentToSendDtos;
    }

    @Override
    public List<AppointmentToSendDto> findByDentistId(Long dentistId) {
        List<Appointment> appointments = appointmentRepository.findAppointmentsByDentist_id(dentistId);
        List<AppointmentToSendDto> appointmentToSendDtos = mapToAppointmentList(appointments);
        for (int i = 0; i < appointmentToSendDtos.size(); i++) {
            setPatientAndDentis(appointments.get(i).getPatient_id(), appointments.get(i).getDentist_id(), appointmentToSendDtos.get(i));
        }
        return appointmentToSendDtos;
    }

    @Override
    public void deleteAppointment(Long id) {
        if (this.existsAppointmentById(id)) {
            appointmentRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Appointment not found");
        }
    }

    @Override
    public boolean patientHasAppointments(Long patientId) {
        return appointmentRepository.patientHasAppointments(patientId);
    }

    @Override
    public boolean dentistHasAppointments(Long dentistId) {
        return appointmentRepository.dentistHasAppointments(dentistId);
    }


    //mappers

    private Appointment mapToAppointment(AppointmentToSendDto appointmentDto) {
        return modelMapper.map(appointmentDto, Appointment.class);
    }

    private AppointmentToSendDto mapToAppointmentDto(Appointment appointment) {
        return modelMapper.map(appointment, AppointmentToSendDto.class);
    }

    private List<AppointmentToSendDto> mapToAppointmentList(List<Appointment> appointments) {
        return appointments.stream().map(this::mapToAppointmentDto).toList();
    }

    //setters
    private AppointmentToSendDto setPatientAndDentis(Long patientId, Long dentistId, AppointmentToSendDto appointment) {
        appointment.setPatient(patientClient.findById(patientId).getBody());
        appointment.setDentist(dentistClient.findById(dentistId).getBody());
        return appointment;
    }

}
