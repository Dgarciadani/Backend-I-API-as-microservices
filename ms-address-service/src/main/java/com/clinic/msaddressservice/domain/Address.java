package com.clinic.msaddressservice.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "addresses")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    @NotNull(message = "street is required")
    private String street;
    @NotNull(message = "door is required")
    private Integer door;
    @NotNull(message = "city is required")
    private String city;
    @NotNull(message = "state is required")
    private String state;

    private Long patientId;
}
