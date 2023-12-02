package com.devsu.examen.financialservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Cuenta {
    @Id
    @GeneratedValue(generator = "cuenta_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "cuenta_seq", allocationSize = 1)
    private Integer idCuenta;
    private String numeroCuenta;
    private String tipo;
    private BigDecimal saldo;
    private boolean estado;
    private Integer idCliente;
    @OneToMany(mappedBy = "cuenta")
    private List<Movimiento> movimientos = new ArrayList<>();
}
