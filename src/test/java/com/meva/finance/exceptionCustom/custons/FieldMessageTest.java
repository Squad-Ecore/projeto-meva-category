package com.meva.finance.exceptionCustom.custons;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldMessageTest {

    @Test
    void testEqualsAndHashCode() {
        FieldMessage message1 = new FieldMessage(400, "Error 1");
        FieldMessage message2 = new FieldMessage(400, "Error 1");

        // Verifica se message1 é igual a message2 e vice-versa
        assertEquals(message1, message2);
        assertEquals(message2, message1);

        // Verifica se os hashcodes são iguais
        assertEquals(message1.hashCode(), message2.hashCode());
    }

    @Test
    void testToString() {
        FieldMessage message = new FieldMessage(400, "Error 1");

        assertEquals("FieldMessage(status=400, message=Error 1)", message.toString());
    }

    @Test
    void testSetStatusAndMessage() {
        FieldMessage message = new FieldMessage(400, "Error 1");

        assertEquals(400, message.getStatus());
        assertEquals("Error 1", message.getMessage());

        message.setStatus(500);
        message.setMessage("Error 2");

        assertEquals(500, message.getStatus());
        assertEquals("Error 2", message.getMessage());
    }

}