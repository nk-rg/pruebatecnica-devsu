package com.devsu.examen.customerservice.dto;

import lombok.Data;

@Data
public class ClienteResponse {
    private Integer idPersona;
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private String clave;
    private Boolean estado;
}
