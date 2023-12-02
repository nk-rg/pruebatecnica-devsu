package com.devsu.examen.financialservice.service;

import com.devsu.examen.financialservice.dto.CuentaResponse;
import com.devsu.examen.financialservice.exception.CustomException;
import com.devsu.examen.financialservice.dto.ClienteResponse;
import com.devsu.examen.financialservice.dto.CuentaRequest;
import com.devsu.examen.financialservice.dto.ReporteResponse;
import com.devsu.examen.financialservice.model.Cuenta;
import com.devsu.examen.financialservice.repository.CuentaRepository;
import com.devsu.examen.financialservice.repository.MovimientoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class CuentaService {
    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;
    private final WebClient webClient;

    public CuentaService(CuentaRepository cuentaRepository,
                         MovimientoRepository movimientoRepository,
                         WebClient.Builder webBuilder) {
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
        this.webClient = webBuilder.baseUrl("http://localhost:8801").build();
    }

    public Integer insert(CuentaRequest request) {
       Cuenta cuenta = new Cuenta();
       BeanUtils.copyProperties(request, cuenta);
       return cuentaRepository.save(cuenta).getIdCuenta();
    }

    @Transactional
    public void delete(Integer id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cuenta no existe"));
        if (!cuenta.getMovimientos().isEmpty()) {
            throw new CustomException("No se puede eliminar la cuenta porque cuenta con movimientos");
        }
        cuentaRepository.delete(cuenta);
    }

    @Transactional
    public void update(Integer id, CuentaRequest request) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cuenta no existe"));
        if (!cuenta.getMovimientos().isEmpty()) {
            throw new CustomException("No se puede actualizar la cuenta porque cuenta con movimientos");
        }
        BeanUtils.copyProperties(request, cuenta);
        cuentaRepository.save(cuenta);
    }

    public List<ReporteResponse> report(LocalDate fchInicio, LocalDate fchFinal, Integer idCliente) {
        CompletableFuture<ClienteResponse> future = webClient.get()
                .uri("/clientes/" + idCliente)
                .retrieve()
                .bodyToMono(ClienteResponse.class)
                .toFuture();

        List<ReporteResponse> reporteResponses = movimientoRepository.generateReport(fchInicio, fchFinal, idCliente);
        reporteResponses.forEach(reporte -> {
            try {
                reporte.setNombreCliente(future.get().getNombre());
            } catch (InterruptedException | ExecutionException e) {
                log.error("Error al obtener nombre de cliente: {}", e.getMessage());
                reporte.setNombreCliente("NO ENCONTRADO");
            }
        });
        return reporteResponses;
    }

    public CuentaResponse findById(Integer id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cuenta no existe"));
        CuentaResponse cuentaResponse = new CuentaResponse();
        BeanUtils.copyProperties(cuenta, cuentaResponse);
        return cuentaResponse;
    }
}
