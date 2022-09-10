package com.clinic.msaddressservice.service;

import com.clinic.msaddressservice.domain.Address;
import com.clinic.msaddressservice.domain.dto.AddressDto;

import java.util.List;

public interface IAddressService {

    AddressDto findByPatientId(Long id);

    AddressDto findById(Long id) ;

    AddressDto save(Long patientId,Address entity);

    void deleteByPatientId(Long id) ;

    AddressDto updateByPatientId(Long id, Address entity) ;


}
