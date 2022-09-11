package com.clinic.mspatientservice.service.imp;

import com.clinic.mspatientservice.client.IAddressClient;
import com.clinic.mspatientservice.domain.Patient;
import com.clinic.mspatientservice.domain.dto.PatientReceivedDto;
import com.clinic.mspatientservice.domain.dto.PatientSendDto;
import com.clinic.mspatientservice.domain.model.Address;
import com.clinic.mspatientservice.exceptions.DniAlreadyRegisteredException;
import com.clinic.mspatientservice.exceptions.ResourceNotFoundException;
import com.clinic.mspatientservice.repository.IPatientRepository;
import com.clinic.mspatientservice.service.IPatientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class PatientService implements IPatientService {

    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(PatientService.class);

    private final IPatientRepository patientRepository;

    private final ModelMapper modelMapper;


    private final IAddressClient addressService;


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
            Patient patient = patientRepository.findById(id).get();
            PatientSendDto patientSendDto = modelMapper.map(patient, PatientSendDto.class);
            patientSendDto.setAddress(addressService.getAddressById(patient.getId()).getBody());
            return patientSendDto;
        } else {
            throw new ResourceNotFoundException("Patient with id: " + id + " not found");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PatientSendDto save(PatientReceivedDto entity) {
        if (this.existsByDni(entity.getDni())) {
            throw new DniAlreadyRegisteredException("Patient with dni: " + entity.getDni() + " already exists");
        } else {
            try {
                logger.info("patient saved" + entity);
                Patient patient = modelMapper.map(entity, Patient.class);
                patient.setCreatedAt(LocalDate.now());
                Patient patientSaved = patientRepository.save(patient);
                PatientSendDto patientSendDto = modelMapper.map(patientSaved, PatientSendDto.class);
                Address address = addressService.registerAddress(patient.getId(), entity.getAddress()).getBody();
                patientSendDto.setAddress(address);
                return patientSendDto;
            } catch (Exception e) {
                throw new RuntimeException("Error al guardar el paciente");
            }
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
            logger.info("patient updated" + entity);
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
