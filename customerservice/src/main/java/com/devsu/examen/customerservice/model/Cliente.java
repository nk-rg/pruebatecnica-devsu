package com.devsu.examen.customerservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@PrimaryKeyJoinColumn(name = "id_cliente", referencedColumnName = "id_persona")
public class Cliente extends Persona {
    private String clave;
    private Boolean estado;
}
