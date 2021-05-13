package com.experimentality.Store.exceptions;

import com.experimentality.Store.domain.dto.ExceptionResponse;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.chrono.Chronology;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomExceptionHandlerShould {

    @Test
    public void throwsANotFoundException() {

        String message = "Custom exception message";
        String statusException = "Not Found";
        Chronology timestamp = LocalDateTime.now().getChronology();
        int code = 404;

        CustomExceptionHandler exception = new CustomExceptionHandler();

        ExceptionResponse status = exception.notFound(new NotFoundException("Custom exception message"));

        assertEquals(statusException, status.getStatus());
        assertEquals(message, status.getMessage());
        assertEquals(timestamp, status.getTimestamp().getChronology());
        assertEquals(code, status.getCode());
    }

    @Test
    public void throwsaBadRequestException() {

        String message = "Custom exception message";
        String statusException = "Bad Request";
        Chronology timestamp = LocalDateTime.now().getChronology();
        int code = 400;

        CustomExceptionHandler exception = new CustomExceptionHandler();

        ExceptionResponse status = exception.badRequest(new IllegalArgumentException("Custom exception message"));

        assertEquals(statusException, status.getStatus());
        assertEquals(message, status.getMessage());
        assertEquals(timestamp, status.getTimestamp().getChronology());
        assertEquals(code, status.getCode());
    }

    @Test
    public void throwsAnUnauthorizedException() {

        String message = "Custom exception message";
        String statusException = "Unathorized";
        Chronology timestamp = LocalDateTime.now().getChronology();
        int code = 401;

        CustomExceptionHandler exception = new CustomExceptionHandler();

        ExceptionResponse status = exception.badRequest(new SecurityException("Custom exception message"));

        assertEquals(statusException, status.getStatus());
        assertEquals(message, status.getMessage());
        assertEquals(timestamp, status.getTimestamp().getChronology());
        assertEquals(code, status.getCode());
    }

    @Test
    public void throwsAForbidenException() {

        String message = "Custom exception message";
        String statusException = "Forbiden";
        Chronology timestamp = LocalDateTime.now().getChronology();
        int code = 403;

        CustomExceptionHandler exception = new CustomExceptionHandler();

        ExceptionResponse status = exception.badRequest(new IllegalAccessException("Custom exception message"));

        assertEquals(statusException, status.getStatus());
        assertEquals(message, status.getMessage());
        assertEquals(timestamp, status.getTimestamp().getChronology());
        assertEquals(code, status.getCode());
    }
}