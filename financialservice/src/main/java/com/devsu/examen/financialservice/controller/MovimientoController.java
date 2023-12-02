package com.devsu.examen.financialservice.controller;

import com.devsu.examen.financialservice.dto.MovimientoRequest;
import com.devsu.examen.financialservice.dto.MovimientoResponse;
import com.devsu.examen.financialservice.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovimientoController {

    private final MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody MovimientoRequest request) {
        Integer id = movimientoService.insert(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody MovimientoRequest request) {
        movimientoService.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Integer id) {
        movimientoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
