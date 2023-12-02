package com.devsu.examen.financialservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CuentaResponse {
    private String numeroCuenta;
    private String tipo;
    private BigDecimal saldo;
    private boolean estado;
    private Integer idCliente;
}
