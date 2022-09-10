package com.clinic.mspatientservice.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "patients")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private String dni;
    private String email;

    private LocalDate createdAt;

   // private Address address;
}
