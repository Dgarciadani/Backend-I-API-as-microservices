package com.clinic.msaddressservice.controller;

import com.clinic.msaddressservice.domain.Address;
import com.clinic.msaddressservice.domain.dto.AddressDto;
import com.clinic.msaddressservice.service.IAddressService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class AddressController {

    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(AddressController.class);

    private final IAddressService addressService;

    @GetMapping("/id={id}")
    public ResponseEntity<AddressDto> findById(@PathVariable Long id) {
        return new ResponseEntity<AddressDto>(addressService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/patientId={id}")
    public ResponseEntity<AddressDto> findByPatientId(@PathVariable Long id) {
        return new ResponseEntity<AddressDto>(addressService.findByPatientId(id), HttpStatus.OK);
    }

    @PostMapping("/register/patientId={patientId}")
    public ResponseEntity<AddressDto> register(@PathVariable Long patientId,@RequestBody @Valid Address address) {
        return new ResponseEntity<AddressDto>(addressService.save(patientId,address), HttpStatus.CREATED);
    }

    @PutMapping("/update/patientId={patientId}")
    public ResponseEntity<AddressDto> updateByPatientId(@PathVariable Long patientId, @RequestBody  Address address) {
        return new ResponseEntity<AddressDto>(addressService.updateByPatientId(patientId, address), HttpStatus.OK);
    }

    @DeleteMapping("/delete/patientId={patientId}")
    public ResponseEntity<Void> deleteByPatientId(@PathVariable Long patientId) {
        addressService.deleteByPatientId(patientId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
