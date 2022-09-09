package com.clinic.msdentistservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DentistReceivedDto {

    @NotNull(message = "name is required")
    private String name;
    @NotNull(message = "lastName is required")
    private String lastName;

    @NotNull(message = "register is required")
    private Integer register;


}
