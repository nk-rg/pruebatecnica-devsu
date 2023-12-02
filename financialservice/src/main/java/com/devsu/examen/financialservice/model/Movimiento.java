package com.devsu.examen.financialservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Entity
@Table
public class Movimiento {
    @Id
    @GeneratedValue(generator = "movimiento_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "movimiento_seq", allocationSize = 1)
    private Integer idMovimiento;
    @ManyToOne
    @JoinColumn(name = "id_cuenta")
    private Cuenta cuenta;
    private LocalDate fecha;
    private BigDecimal valor;
    @Column(name = "saldo_inicial")
    private BigDecimal saldoInicial;
}
