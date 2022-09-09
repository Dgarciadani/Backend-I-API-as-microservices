package com.clinic.msdentistservice.service;

import com.clinic.msdentistservice.domain.dto.DentistReceivedDto;
import com.clinic.msdentistservice.domain.dto.DentistSendDto;

import java.util.List;

public interface IDentistService {

    boolean registerAlreadyExists(Integer register);

    boolean existsById(Long Id);

    DentistSendDto findById(Long id);

    DentistSendDto save(DentistReceivedDto entity);

    void deleteById(Long id);

    DentistSendDto update(Long id, DentistReceivedDto entity);

    List<DentistSendDto> findAll();

}

