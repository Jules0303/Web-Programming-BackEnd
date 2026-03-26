package com.dauphine.blocker.controller;

import com.dauphine.blocker.exception.BadRequestException;
import com.dauphine.blocker.exception.CategoryNotFoundException;
import com.dauphine.blocker.exception.PostNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger=Logger.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler({
            CategoryNotFoundException.class,
            PostNotFoundException.class,
    })
    public ResponseEntity<String> handleNotFoundException(Exception ex){
        logger.warning(ex.getMessage());
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex){
        logger.warning(ex.getMessage());
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex){
        ex.printStackTrace();
        logger.severe(ex.getMessage());
        return ResponseEntity.status(500).body(ex.toString() + "\n" + java.util.Arrays.toString(ex.getStackTrace()));
    }
}
