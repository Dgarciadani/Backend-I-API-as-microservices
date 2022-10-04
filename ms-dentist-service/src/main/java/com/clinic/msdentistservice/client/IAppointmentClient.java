package com.clinic.msdentistservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "appointment-service")
public interface IAppointmentClient {

    @GetMapping("/appointment/dentist/hassappointments/id={id}")
    ResponseEntity<Boolean> existsAppointmentByDentistId(@PathVariable Long id);


}
