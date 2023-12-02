package com.devsu.examen.customerservice.model;

import com.devsu.examen.customerservice.model.enums.GeneroEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.ColumnTransformer;

@Data
@Entity
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {
    @Id
    @Column(name = "id_persona")
    @GeneratedValue(generator = "persona_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "persona_sequence", allocationSize = 1)
    private Integer idPersona;
    private String nombre;
    @Enumerated(EnumType.STRING)
    @Column(name = "genero")
    @ColumnTransformer(write = "?::genero_enum")
    private GeneroEnum genero;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
