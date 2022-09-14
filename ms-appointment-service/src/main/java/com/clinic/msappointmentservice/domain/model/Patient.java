package com.clinic.msappointmentservice.domain.model;

import java.time.LocalDate;

public record Patient(Long id,
                      String name,
                      String lastName,
                      String dni,
                      String email,
                      LocalDate createdAt) {
}
