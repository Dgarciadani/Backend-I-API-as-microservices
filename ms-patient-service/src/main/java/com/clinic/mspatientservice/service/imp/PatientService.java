package com.clinic.mspatientservice.service.imp;

import com.clinic.mspatientservice.domain.Patient;
import com.clinic.mspatientservice.domain.dto.PatientReceivedDto;
import com.clinic.mspatientservice.domain.dto.PatientSendDto;
import com.clinic.mspatientservice.exceptions.ResourceNotFoundException;
import com.clinic.mspatientservice.repository.IPatientRepository;
import com.clinic.mspatientservice.service.IPatientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class PatientService implements IPatientService {

    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(PatientService.class);

    private final IPatientRepository patientRepository;

    private final ModelMapper modelMapper;


    @Override
    public boolean existsById(Long id) {

        return patientRepository.existsById(id);
    }

    @Override
    public boolean existsByDni(String dni) {
        return patientRepository.existsByDni(dni);
    }

    @Override
    public PatientSendDto findById(Long id) {
        if (this.existsById(id)) {
            logger.info("patient with id: " + id + " was found");
            return modelMapper.map(patientRepository.findById(id).get(), PatientSendDto.class);
        } else {
            throw new ResourceNotFoundException("Patient with id: " + id + " not found");
        }
    }

    @Override
    public PatientSendDto save(PatientReceivedDto entity) {
        if (this.existsByDni(entity.getDni())) {

            throw new ResourceNotFoundException("Patient with dni: " + entity.getDni() + " already exists");
        } else {
            logger.info("patient saved"+entity);
            Patient patient = modelMapper.map(entity, Patient.class);
            patient.setCreatedAt(LocalDate.now());
            return modelMapper.map(patientRepository.save(patient), PatientSendDto.class);
        }
    }

    @Override
    public void deleteById(Long id) {
        if (this.existsById(id)) {
            patientRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Patient with id: " + id + " not found");
        }

    }

    @Override
    public PatientSendDto update(Long id, PatientReceivedDto entity) {
        if (this.existsById(id)) {
            Patient patient = modelMapper.map(entity, Patient.class);
            patient.setId(id);
            logger.info("patient updated"+entity);
            return modelMapper.map(patientRepository.save(patient), PatientSendDto.class);
        } else {
            throw new ResourceNotFoundException("Patient with id: " + id + " not found");
        }
    }

    @Override
    public List<PatientSendDto> findAll() {

        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(patient -> modelMapper.map(patient, PatientSendDto.class)).toList();
    }
}
