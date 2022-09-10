package com.clinic.mspatientservice.controller;

import com.clinic.mspatientservice.domain.dto.PatientReceivedDto;
import com.clinic.mspatientservice.domain.dto.PatientSendDto;
import com.clinic.mspatientservice.service.IPatientService;
import com.clinic.mspatientservice.service.imp.PatientService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class PatientController {

    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(PatientService.class);

    private final IPatientService patientService;


    @GetMapping("/id={id}")
    public ResponseEntity<PatientSendDto> getPatientById(@PathVariable Long id) {
        return new ResponseEntity<PatientSendDto>(patientService.findById(id), org.springframework.http.HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<PatientSendDto> registerPatient(@RequestBody @Valid PatientReceivedDto patientReceivedDto) {
        return new ResponseEntity<PatientSendDto>(patientService.save(patientReceivedDto), org.springframework.http.HttpStatus.CREATED);
    }

    @PutMapping("/id={id}")
    public ResponseEntity<PatientSendDto> updatePatient(@PathVariable Long id, @RequestBody @Valid PatientReceivedDto patientReceivedDto) {
        return ResponseEntity.ok().body(patientService.update(id, patientReceivedDto));
    }

    @DeleteMapping("/id={id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        patientService.deleteById(id);
        return ResponseEntity.ok("Patient deleted");
    }

    @GetMapping("/findAll")
    public ResponseEntity<Iterable<PatientSendDto>> getAllPatients() {
        List<PatientSendDto> patientSendDtoList = new ArrayList<>(patientService.findAll());
        if (patientSendDtoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body(patientSendDtoList);
        }
    }

}
