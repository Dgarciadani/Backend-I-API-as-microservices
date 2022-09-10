package com.clinic.mspatientservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DniAlreadyRegisteredException extends RuntimeException {

    public DniAlreadyRegisteredException(String message) {
        super(message);
    }

    public DniAlreadyRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }


}
