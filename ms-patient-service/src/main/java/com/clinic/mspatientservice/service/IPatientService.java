package com.clinic.mspatientservice.service;

import com.clinic.mspatientservice.domain.dto.PatientReceivedDto;
import com.clinic.mspatientservice.domain.dto.PatientSendDto;

import java.util.List;

public interface IPatientService {


     boolean existsById(Long id);

     boolean existsByDni(String dni);


    PatientSendDto findById(Long id);

    PatientSendDto save(PatientReceivedDto entity);

    void deleteById(Long id);

    PatientSendDto update(Long id, PatientReceivedDto entity);

    List<PatientSendDto> findAll();

}
