package com.meva.finance.exceptionCustom.restExceptionHandler;

import com.meva.finance.exceptionCustom.custons.FieldMessage;
import com.meva.finance.exceptionCustom.custons.ValidException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class RestExceptionHandlerTest {


    @Test
    void testExceptionCustonValid() {

        String errorMessage = "Erro personalizado";
        ValidException exception = new ValidException(errorMessage);


        RestExceptionHandler handler = new RestExceptionHandler();

        ResponseEntity<FieldMessage> response = handler.exceptionCustonValid(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        FieldMessage body = response.getBody();
        assertEquals(errorMessage, body.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.value(), body.getStatus());
    }

}