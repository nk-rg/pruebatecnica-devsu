package com.devsu.examen.financialservice.repository.custom;

import com.devsu.examen.financialservice.dto.ReporteResponse;
import com.devsu.examen.financialservice.model.Cuenta;
import com.devsu.examen.financialservice.model.Movimiento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;

import java.time.LocalDate;
import java.util.List;

public class MovimientoRepositoryCustomImpl implements MovimientoRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<ReporteResponse> generateReport(LocalDate fchInicio, LocalDate fchFin, Integer idCliente) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ReporteResponse> criteriaQuery = criteriaBuilder.createQuery(ReporteResponse.class);
        Root<Movimiento> movimientoRoot = criteriaQuery.from(Movimiento.class);
        Join<Movimiento, Cuenta> cuentaJoin = movimientoRoot.join("cuenta");
        Predicate rangeDate = criteriaBuilder.between(movimientoRoot.get("fecha"), fchInicio, fchFin);
        Predicate cliente = criteriaBuilder.equal(cuentaJoin.get("idCliente"), idCliente);
        criteriaQuery.where(rangeDate, cliente);
        criteriaQuery.multiselect(
                movimientoRoot.get("fecha"),
                cuentaJoin.get("numeroCuenta"),
                cuentaJoin.get("tipo"),
                movimientoRoot.get("saldoInicial"),
                movimientoRoot.get("valor"),
                criteriaBuilder.sum(
                        movimientoRoot.get("saldoInicial"),
                        movimientoRoot.get("valor"))
        );
        Order orderFecha = criteriaBuilder.desc(movimientoRoot.get("fecha"));
        Order orderNumeroCuenta = criteriaBuilder.desc(cuentaJoin.get("numeroCuenta"));
        criteriaQuery.orderBy(orderFecha, orderNumeroCuenta);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
