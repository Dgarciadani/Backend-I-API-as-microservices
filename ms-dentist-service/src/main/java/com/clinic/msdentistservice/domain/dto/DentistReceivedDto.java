package com.clinic.msdentistservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class DentistDto {

    private Integer dentist_id;
    @NotNull(message = "name is required")
    private String name;
    @NotNull(message = "lastName is required")
    private String lastName;

    @NotNull(message = "register is required")
    private Integer register;
}
