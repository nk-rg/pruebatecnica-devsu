package com.devsu.examen.customerservice.dto;

import com.devsu.examen.customerservice.model.enums.GeneroEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteRequest {
    @NotBlank(message = "Nombre es requerido")
    private String nombre;
    @NotNull(message = "Genero es requerido")
    private GeneroEnum genero;
    @NotNull(message = "Edad es requerido")
    @Size(min = 18, max = 90, message = "Cliente no tiene edad valida")
    private Integer edad;
    private String identificacion;
    @NotBlank(message = "Direccion es requerido")
    private String direccion;
    @NotBlank(message = "Telefono es requerido")
    private String telefono;
    @NotBlank(message = "Clave es requerido")
    private String clave;
    @NotNull(message = "Estado es requerido")
    private Boolean estado;
}
