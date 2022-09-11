package com.clinic.mspatientservice.domain.dto;

import com.clinic.mspatientservice.domain.model.Address;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatientReceivedDto {
    @NotNull(message = "name is required")
    private String name;
    @NotNull(message = "lastName is required")
    private String lastName;
    @NotNull(message = "email is required")
    @Email(message = "email format is not valid")
    private String email;
    @NotNull(message = "dni is required")
    private String dni;
    @NotNull(message = "address is required")
    private Address address;
}
