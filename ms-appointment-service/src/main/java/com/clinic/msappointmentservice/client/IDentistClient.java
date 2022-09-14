package com.clinic.msappointmentservice.client;

import com.clinic.msappointmentservice.domain.model.Dentist;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "dentist-service")
public interface IDentistClient {


    @GetMapping("/dentist/exists/id={id}")
    ResponseEntity<Boolean> existsById(@PathVariable Long id);

    @GetMapping("/dentist/id={id}")
    ResponseEntity<Dentist> findById(@PathVariable Long id);
}
