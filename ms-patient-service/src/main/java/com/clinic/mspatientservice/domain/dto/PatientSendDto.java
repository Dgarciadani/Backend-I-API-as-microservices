package com.clinic.mspatientservice.domain.dto;

import com.clinic.mspatientservice.domain.model.Address;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientSendDto {

    private Integer id;
    private String name;
    private String lastName;
    private String email;
    private String dni;

    private Address address;

    private LocalDate createdAt;
}
