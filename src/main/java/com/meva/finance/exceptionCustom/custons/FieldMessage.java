package com.meva.finance.exceptionCustom.custons;

import lombok.Data;

import java.io.Serializable;

@Data
public class FieldMessage implements Serializable {

    private int status;
    private String message;

    public FieldMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }


}
