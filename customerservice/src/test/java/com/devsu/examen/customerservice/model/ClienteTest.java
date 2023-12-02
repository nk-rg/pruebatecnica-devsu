package com.devsu.examen.customerservice.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClienteTest {

    @Test
    void testClienteIsInstanceOfPersona() {
        Cliente cliente = new Cliente();
        Assertions.assertInstanceOf(Persona.class, cliente);
    }
}