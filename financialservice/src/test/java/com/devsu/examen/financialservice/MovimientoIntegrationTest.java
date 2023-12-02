package com.devsu.examen.financialservice;

import com.devsu.examen.financialservice.dto.MovimientoRequest;
import com.devsu.examen.financialservice.model.Movimiento;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MovimientoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testInsertSuccessfully() throws Exception {
        MovimientoRequest movimientoRequest = new MovimientoRequest();
        movimientoRequest.setIdCuenta(1);
        movimientoRequest.setFecha(LocalDate.of(2023, 12, 2));
        movimientoRequest.setValor(new BigDecimal("-500"));
        String json = new ObjectMapper()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .writeValueAsString(movimientoRequest);

        mockMvc.perform(MockMvcRequestBuilders.get("/cuentas/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.saldo").value(2000));

        mockMvc.perform(MockMvcRequestBuilders.post("/movimientos")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.get("/cuentas/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.saldo").value(1500));

    }
}
