package com.clinic.msappointmentservice.client;

import com.clinic.msappointmentservice.domain.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient-service")
public interface IPatientClient {


    @GetMapping("/patient/exists/id={id}")
    ResponseEntity<Boolean> existsById(@PathVariable Long id);

    @GetMapping("/patient/id={id}")
    ResponseEntity<Patient> findById(@PathVariable Long id);

}
