package ru.itmo.rbdip.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itmo.rbdip.exceptions.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorController {

    private final Map<String, HttpStatus> statusesMap;

    public ErrorController() {
        this.statusesMap = new HashMap<String, HttpStatus>() {{
            put(NumberFormatException.class.getName(), HttpStatus.BAD_REQUEST);
            put(HttpMessageNotReadableException.class.getName(), HttpStatus.BAD_REQUEST);
            put(UserAlreadyExists.class.getName(), HttpStatus.BAD_REQUEST);
            put(EmptyUserOrPasswordException.class.getName(), HttpStatus.BAD_REQUEST);
            put(InvalidAuthorizationHeader.class.getName(), HttpStatus.UNAUTHORIZED);
            put(InvalidUserCredentials.class.getName(), HttpStatus.UNAUTHORIZED);
            put(UserNotFoundException.class.getName(), HttpStatus.BAD_REQUEST);
            put(HttpRequestMethodNotSupportedException.class.getName(), HttpStatus.METHOD_NOT_ALLOWED);
            put(MissingRequestHeaderException.class.getName(), HttpStatus.UNAUTHORIZED);
        }};

    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<String> handleException(Throwable throwable) {
        String errorName = throwable.getClass().getName();
        HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        if (this.statusesMap.containsKey(errorName)) statusCode = this.statusesMap.get(errorName);
        return ResponseEntity.status(statusCode).contentType(MediaType.TEXT_PLAIN).body(throwable.getMessage());
    }
}
