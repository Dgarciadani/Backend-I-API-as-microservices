package com.clinic.msdentistservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class RegisterAlreadyRegisteredException extends RuntimeException {

    public RegisterAlreadyRegisteredException(String message) {
        super(message);
    }

    public RegisterAlreadyRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }


}
