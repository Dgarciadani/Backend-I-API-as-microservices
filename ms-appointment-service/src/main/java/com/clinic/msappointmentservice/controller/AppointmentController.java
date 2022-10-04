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

    @DeleteMapping("/delete/id={id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().body("Appointment deleted successfully");
    }

    @PutMapping("/update/id={id}")
    public ResponseEntity<AppointmentToSendDto> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        return ResponseEntity.ok().body(appointmentService.updateAppointment(id, appointment));
    }

    @GetMapping("/patient/id={id}")
    public ResponseEntity<List<AppointmentToSendDto>> findByPatientId(@PathVariable Long id) {
        List<AppointmentToSendDto> appointments = appointmentService.findByPatientId(id);
        if (appointments.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body(appointments);
        }

    }

    @GetMapping("/dentist/id={id}")
    public ResponseEntity<List<AppointmentToSendDto>> findByDentistId(@PathVariable Long id) {
        List<AppointmentToSendDto> appointments = appointmentService.findByDentistId(id);
        if (appointments.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body(appointments);
        }
    }

    @GetMapping("/patient/hassappointments/id={id}")
    public ResponseEntity<Boolean> patientHasAppointments(@PathVariable Long id) {
        boolean hasAppointments = appointmentService.patientHasAppointments(id);
        if (hasAppointments) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/dentist/hassappointments/id={id}")
    public ResponseEntity<Boolean> dentistHasAppointments(@PathVariable Long id) {
        boolean hasAppointments = appointmentService.dentistHasAppointments(id);
        if (hasAppointments) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.noContent().build();
        }
    }


}
