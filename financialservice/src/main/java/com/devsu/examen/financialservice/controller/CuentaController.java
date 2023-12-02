package com.devsu.examen.financialservice.controller;

import com.devsu.examen.financialservice.dto.CuentaRequest;
import com.devsu.examen.financialservice.dto.CuentaResponse;
import com.devsu.examen.financialservice.dto.ReporteResponse;
import com.devsu.examen.financialservice.service.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class CuentaController {

    private final CuentaService cuentaService;

    @GetMapping("/{id}")
    ResponseEntity<CuentaResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(cuentaService.findById(id));
    }

    @PostMapping
    ResponseEntity<Void> insert(@RequestBody CuentaRequest request) {
        Integer id = cuentaService.insert(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody CuentaRequest request) {
        cuentaService.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Integer id) {
        cuentaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/reportes")
    ResponseEntity<Object> report(
            @RequestParam("fechaInicio") LocalDate fchInicio,
            @RequestParam("fechaFinal") LocalDate fchFinal,
            @RequestParam("cliente") Integer idCliente) {
        List<ReporteResponse> reporte = cuentaService.report(fchInicio, fchFinal, idCliente);
        return ResponseEntity.ok(reporte);
    }

}
