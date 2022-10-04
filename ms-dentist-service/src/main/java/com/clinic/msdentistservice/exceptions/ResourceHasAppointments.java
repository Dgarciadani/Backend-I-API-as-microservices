package com.clinic.msdentistservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)

public class ResourceHasAppointments extends RuntimeException {

    public ResourceHasAppointments(String message) {
        super(message);
    }
}
