package com.clinic.msappointmentservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DateNotAvailableException extends RuntimeException {

    public DateNotAvailableException(String message) {
        super(message);
    }

}
