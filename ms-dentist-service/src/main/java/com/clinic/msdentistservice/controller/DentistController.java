package com.clinic.msdentistservice.controller;

import com.clinic.msdentistservice.domain.dto.DentistReceivedDto;
import com.clinic.msdentistservice.domain.dto.DentistSendDto;
import com.clinic.msdentistservice.service.IDentistService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class DentistController {


    private IDentistService dentistService;


    @GetMapping("/id={id}")
    public ResponseEntity<DentistSendDto> findById(@PathVariable Long id) {
        return new ResponseEntity<DentistSendDto>(dentistService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<DentistSendDto> registerDentist(@RequestBody @Valid DentistReceivedDto dentistReceivedDto) {
        return new ResponseEntity<DentistSendDto>(dentistService.save(dentistReceivedDto), org.springframework.http.HttpStatus.CREATED);
    }

    @PutMapping("/id={id}")
    public ResponseEntity<DentistSendDto> updateDentist(@PathVariable Long id, @RequestBody @Valid DentistReceivedDto dentistReceivedDto) {
        return ResponseEntity.ok().body(dentistService.update(id, dentistReceivedDto));
    }

    @DeleteMapping("/id={id}")
    public ResponseEntity<String> deleteDentist(@PathVariable Long id) {
        dentistService.deleteById(id);
        return ResponseEntity.ok("Dentist deleted");
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<DentistSendDto>> getAllDentists() {
        return new ResponseEntity<List<DentistSendDto>>(dentistService.findAll(), HttpStatus.OK);
    }
}
