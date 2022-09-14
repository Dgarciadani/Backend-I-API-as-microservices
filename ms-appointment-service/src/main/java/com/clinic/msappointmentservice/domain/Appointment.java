package com.clinic.msappointmentservice.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "appointments")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long dentist_id;
    private Long patient_id;
    private LocalDateTime date;
    private Double price;


}
