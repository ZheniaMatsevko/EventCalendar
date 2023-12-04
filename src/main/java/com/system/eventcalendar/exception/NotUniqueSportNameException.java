package com.system.eventcalendar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotUniqueSportNameException extends RuntimeException{
    public NotUniqueSportNameException() {
        super("Sport name must be unique");
    }
    public NotUniqueSportNameException(Throwable cause) {
        super("Sport name must be unique", cause);
    }

}