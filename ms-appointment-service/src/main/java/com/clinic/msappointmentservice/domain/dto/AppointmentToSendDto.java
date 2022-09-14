package com.clinic.msappointmentservice.domain.dto;

import com.clinic.msappointmentservice.domain.model.Dentist;
import com.clinic.msappointmentservice.domain.model.Patient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentToSendDto {

    private Long id;
    private Dentist dentist;
    private Patient patient;
    private LocalDateTime date;
    private Double price;
}
