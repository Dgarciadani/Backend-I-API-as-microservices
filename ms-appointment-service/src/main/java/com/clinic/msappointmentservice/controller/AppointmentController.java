package com.clinic.msappointmentservice.controller;

import com.clinic.msappointmentservice.domain.Appointment;
import com.clinic.msappointmentservice.domain.dto.AppointmentToSendDto;
import com.clinic.msappointmentservice.service.IAppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class AppointmentController {

    private final IAppointmentService appointmentService;

    @GetMapping("/id={id}")
    public ResponseEntity<AppointmentToSendDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(appointmentService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AppointmentToSendDto>> findAll() {
        return ResponseEntity.ok().body(appointmentService.findAll());
    }

    @PostMapping("/register")
    public ResponseEntity<AppointmentToSendDto> createAppointment(@RequestBody Appointment appointment) {
        return ResponseEntity.ok().body(appointmentService.createAppointment(appointment));
    }

}
