package com.devsu.examen.financialservice.repository.custom;

import com.devsu.examen.financialservice.dto.ReporteResponse;

import java.time.LocalDate;
import java.util.List;

public interface MovimientoRepositoryCustom {

    List<ReporteResponse> generateReport(LocalDate fchInicio, LocalDate fchFin, Integer idCliente);
}
