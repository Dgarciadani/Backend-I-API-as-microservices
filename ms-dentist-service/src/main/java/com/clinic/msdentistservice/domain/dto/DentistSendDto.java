package com.clinic.msdentistservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@ToString
public class DentistSendDto {
    private Long id;

    private String name;
    private String lastName;
    private Integer register;
}
