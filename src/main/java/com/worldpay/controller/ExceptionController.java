package com.worldpay.controller;

import com.worldpay.exception.OfferNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.worldpay.model.Error;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = {OfferNotFoundException.class})
    public ResponseEntity<Error> handleCourseNotFoundException(RuntimeException excption){
        Error error = new Error(404, excption.getMessage(), "visit Worldpay help page for troubleshooting");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
