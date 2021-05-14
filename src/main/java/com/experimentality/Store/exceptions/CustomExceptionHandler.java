package com.experimentality.Store.exceptions;
import com.experimentality.Store.domain.dto.ExceptionResponse;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@RestControllerAdvice
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,
        RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.PATCH,
        RequestMethod.TRACE}, allowedHeaders = "*")
public class CustomExceptionHandler extends Exception{

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ExceptionResponse notFound(NotFoundException e){

        ExceptionResponse status = new ExceptionResponse();

        status.setTimestamp(LocalDateTime.now());
        status.setCode(404);
        status.setStatus("Not Found");
        status.setMessage(e.getMessage());

        return status;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ExceptionResponse badRequest(IllegalArgumentException e){

        ExceptionResponse status = new ExceptionResponse();

        status.setTimestamp(LocalDateTime.now());
        status.setCode(400);
        status.setStatus("Bad Request");
        status.setMessage(e.getMessage());

        return status;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(SecurityException.class)
    public ExceptionResponse badRequest(SecurityException e){

        ExceptionResponse status = new ExceptionResponse();

        status.setTimestamp(LocalDateTime.now());
        status.setCode(401);
        status.setStatus("Unathorized");
        status.setMessage(e.getMessage());

        return status;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(IllegalAccessException.class)
    public ExceptionResponse badRequest(IllegalAccessException e){

        ExceptionResponse status = new ExceptionResponse();

        status.setTimestamp(LocalDateTime.now());
        status.setCode(403);
        status.setStatus("Forbiden");
        status.setMessage(e.getMessage());

        return status;
    }


}
