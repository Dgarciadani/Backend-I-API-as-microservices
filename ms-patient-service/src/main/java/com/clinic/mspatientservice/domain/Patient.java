package com.clinic.mspatientservice.domain;

import com.clinic.mspatientservice.domain.model.Address;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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

}
