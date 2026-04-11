package com.klu.handler;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.klu.exception.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Map<String,Object> handleStudentNotFound(StudentNotFoundException ex){

        Map<String,Object> error=new HashMap<>();

        error.put("timestamp",LocalDateTime.now());
        error.put("message",ex.getMessage());
        error.put("statusCode",404);

        return error;
    }

    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String,Object> handleInvalidInput(InvalidInputException ex){

        Map<String,Object> error=new HashMap<>();

        error.put("timestamp",LocalDateTime.now());
        error.put("message",ex.getMessage());
        error.put("statusCode",400);

        return error;
    }
}