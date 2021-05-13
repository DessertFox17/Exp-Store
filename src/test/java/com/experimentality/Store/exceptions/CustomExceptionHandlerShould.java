package com.experimentality.Store.exceptions;

import com.experimentality.Store.domain.dto.ExceptionResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.chrono.Chronology;

public class CustomExceptionHandlerShould {

    @Test
    public void throws_a_notFound_exception() {

        String message = "Custom exception message", statusException = "Not Found";
        Chronology timestamp = LocalDateTime.now().getChronology();
        int code = 404;

        CustomExceptionHandler exception = new CustomExceptionHandler();

        ExceptionResponse status = exception.notFound(new NotFoundException("Custom exception message"));

        assertEquals(statusException,status.getStatus());
        assertEquals(message,status.getMessage());
        assertEquals(timestamp,status.getTimestamp().getChronology());
        assertEquals(code,status.getCode());
    }

    @Test
    public void throws_a_badRequest_exception() {

        String message = "Custom exception message", statusException = "Bad Request";
        Chronology timestamp = LocalDateTime.now().getChronology();
        int code = 400;

        CustomExceptionHandler exception = new CustomExceptionHandler();

        ExceptionResponse status = exception.badRequest(new IllegalArgumentException("Custom exception message"));

        assertEquals(statusException,status.getStatus());
        assertEquals(message,status.getMessage());
        assertEquals(timestamp,status.getTimestamp().getChronology());
        assertEquals(code,status.getCode());
    }

    @Test
    public void throws_a_unathorized_exception() {

        String message = "Custom exception message", statusException = "Unathorized";
        Chronology timestamp = LocalDateTime.now().getChronology();
        int code = 401;

        CustomExceptionHandler exception = new CustomExceptionHandler();

        ExceptionResponse status = exception.badRequest(new SecurityException("Custom exception message"));

        assertEquals(statusException,status.getStatus());
        assertEquals(message,status.getMessage());
        assertEquals(timestamp,status.getTimestamp().getChronology());
        assertEquals(code,status.getCode());
    }

    @Test
    public void throws_a_forbiden_exception() {

        String message = "Custom exception message", statusException = "Forbiden";
        Chronology timestamp = LocalDateTime.now().getChronology();
        int code = 403;

        CustomExceptionHandler exception = new CustomExceptionHandler();

        ExceptionResponse status = exception.badRequest(new IllegalAccessException("Custom exception message"));

        assertEquals(statusException,status.getStatus());
        assertEquals(message,status.getMessage());
        assertEquals(timestamp,status.getTimestamp().getChronology());
        assertEquals(code,status.getCode());
    }
}