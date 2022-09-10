package com.clinic.msaddressservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDto {

    private Long addressId;
    private String street;
    private Integer door;
    private String city;
    private String state;

    private Long patientId;
}
