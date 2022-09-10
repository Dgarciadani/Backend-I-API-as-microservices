package com.clinic.msaddressservice.service.imp;

import com.clinic.msaddressservice.domain.Address;
import com.clinic.msaddressservice.domain.dto.AddressDto;
import com.clinic.msaddressservice.repository.IAddressRepository;
import com.clinic.msaddressservice.service.IAddressService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressService implements IAddressService {

    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(AddressService.class);

    private final IAddressRepository addressRepository;

    private final ModelMapper modelMapper;


    @Override
    public AddressDto findByPatientId(Long id) {

        return modelMapper.map(addressRepository.findByPatientId(id), AddressDto.class);
    }

    @Override
    public AddressDto findById(Long id) {
        return modelMapper.map(addressRepository.findById(id).get(), AddressDto.class);
    }

    @Override
    public AddressDto save(Address entity) {
        return modelMapper.map(addressRepository.save(entity), AddressDto.class);
    }

    @Override
    public void deleteByPatientId(Long id) {
        addressRepository.deleteByPatientId(id);

    }

    @Override
    public AddressDto updateByPatientId(Long id, Address entity) {
        return modelMapper.map(addressRepository.updateByPatientId(id, entity), AddressDto.class);
    }

}