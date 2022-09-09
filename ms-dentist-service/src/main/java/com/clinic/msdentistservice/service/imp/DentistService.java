package com.clinic.msdentistservice.service.imp;

import com.clinic.msdentistservice.domain.Dentist;
import com.clinic.msdentistservice.domain.dto.DentistReceivedDto;
import com.clinic.msdentistservice.domain.dto.DentistSendDto;
import com.clinic.msdentistservice.exceptions.RegisterAlreadyRegisteredException;
import com.clinic.msdentistservice.exceptions.ResourceNotFoundException;
import com.clinic.msdentistservice.repository.IDentistRepository;
import com.clinic.msdentistservice.service.IDentistService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DentistService implements IDentistService {

    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(DentistService.class);

    private final IDentistRepository dentistRepository;

    private ModelMapper mapper;


    @Override
    public boolean registerAlreadyExists(Integer register) {
        return dentistRepository.registerAlreadyExists(register);
    }

    @Override
    public boolean existsById(Long Id) {
        return dentistRepository.existsById(Id);
    }

    @Override
    public DentistSendDto findById(Long id) {
        if (dentistRepository.existsById(id)) {
            logger.info("dentist with id: " + id + " was found");
            return mapper.map(dentistRepository.findById(id).get(), DentistSendDto.class);
        } else {
            throw new ResourceNotFoundException("dentist with id: " + id + " not found");
        }
    }

    @Override
    public DentistSendDto save(DentistReceivedDto entity) {
        if (dentistRepository.registerAlreadyExists(entity.getRegister())) {
            throw new RegisterAlreadyRegisteredException("Dentist with register: " + entity.getRegister() + " already exists");
        } else {
            Dentist dentist = mapper.map(entity, Dentist.class);
            logger.info("dentist with register: " + entity.getRegister() + " was saved");
            return mapper.map(dentistRepository.save(dentist), DentistSendDto.class);
        }

    }

    @Override
    public void deleteById(Long id) {
        if (dentistRepository.existsById(id)) {
            dentistRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Dentist with id: " + id);
        }

    }

    @Override
    public DentistSendDto update(Long id, DentistReceivedDto entity) {
        if (dentistRepository.existsById(id)) {
            Dentist dentist = mapper.map(entity, Dentist.class);
            dentist.setId(id);
            return mapper.map(dentistRepository.save(dentist), DentistSendDto.class);
        } else {
            throw new ResourceNotFoundException("Dentist with id: " + id);
        }
    }

    @Override
    public List<DentistSendDto> findAll() {
        if (dentistRepository.findAll().isEmpty()) {
            throw new ResourceNotFoundException("Dentists not found");
        } else {
            List<Dentist> dentist = dentistRepository.findAll();
            return dentist.stream().map(dentist1 -> mapper.map(dentist1, DentistSendDto.class)).collect(java.util.stream.Collectors.toList());
        }
    }
}