package com.devsu.examen.financialservice.repository;

import com.devsu.examen.financialservice.model.Movimiento;
import com.devsu.examen.financialservice.repository.custom.MovimientoRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer>, MovimientoRepositoryCustom {
}
