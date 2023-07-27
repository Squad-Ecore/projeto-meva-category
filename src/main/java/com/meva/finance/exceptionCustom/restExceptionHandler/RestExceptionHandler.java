package com.meva.finance.exceptionCustom.restExceptionHandler;

import com.meva.finance.exceptionCustom.custons.FieldMessage;
import com.meva.finance.exceptionCustom.custons.ValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ValidException.class)
    public ResponseEntity<FieldMessage> exceptionCustonValid(ValidException ex) {
        FieldMessage message = new FieldMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
