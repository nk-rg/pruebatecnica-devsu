package com.devsu.examen.financialservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ReporteResponse {
    private LocalDate fecha;
    private String numeroCuenta;
    private String tipo;
    private BigDecimal saldoInicial;
    private BigDecimal valor;
    private BigDecimal saldoDisponible;
    private String nombreCliente;

    public ReporteResponse(LocalDate fecha, String numeroCuenta,
                           String tipo, BigDecimal saldoInicial,
                           BigDecimal valor, BigDecimal saldoDisponible) {
        this.fecha = fecha;
        this.numeroCuenta = numeroCuenta;
        this.tipo = tipo;
        this.saldoInicial = saldoInicial;
        this.valor = valor;
        this.saldoDisponible = saldoDisponible;
    }
}
