package com.devsu.examen.financialservice.service;

import com.devsu.examen.financialservice.exception.CustomException;
import com.devsu.examen.financialservice.dto.MovimientoRequest;
import com.devsu.examen.financialservice.dto.MovimientoResponse;
import com.devsu.examen.financialservice.model.Cuenta;
import com.devsu.examen.financialservice.model.Movimiento;
import com.devsu.examen.financialservice.repository.CuentaRepository;
import com.devsu.examen.financialservice.repository.MovimientoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    @Transactional
    public Integer insert(MovimientoRequest request) {
        Cuenta cuenta = cuentaRepository.findByIdCuenta(request.getIdCuenta())
                .orElseThrow(() -> new EntityNotFoundException("Cuenta establecida no existe"));
        if (request.getValor().compareTo(BigDecimal.ZERO) < 0 &&
                request.getValor().abs().compareTo(cuenta.getSaldo()) > 0) {
            throw new CustomException("Saldo no disponible");
        }
        Movimiento movimiento = new Movimiento();
        setDataMovimiento(movimiento, request, cuenta);
        movimientoRepository.save(movimiento);
        cuenta.setSaldo(cuenta.getSaldo().add(movimiento.getValor()));
        cuentaRepository.save(cuenta);
        return movimiento.getIdMovimiento();
    }

    private void setDataMovimiento(Movimiento movimiento, MovimientoRequest request, Cuenta cuenta) {
        BeanUtils.copyProperties(request, movimiento);
        movimiento.setSaldoInicial(cuenta.getSaldo());
        movimiento.setCuenta(cuenta);
    }

    @Transactional
    public void delete(Integer id) {
        Movimiento movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movimiento no encontrado"));
        Cuenta cuenta = movimiento.getCuenta();
        cuenta.setSaldo(cuenta.getSaldo().subtract(movimiento.getValor()));
        cuentaRepository.save(cuenta);
        movimientoRepository.delete(movimiento);
    }

    @Transactional
    public void update(Integer id, MovimientoRequest request) {
        Cuenta cuenta = cuentaRepository.findByIdCuenta(request.getIdCuenta())
                .orElseThrow(() -> new EntityNotFoundException("Cuenta establecida no existe"));
        Movimiento movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movimiento no existe"));
        if (request.getValor().compareTo(BigDecimal.ZERO) < 0 &&
                request.getValor().abs().compareTo(cuenta.getSaldo().subtract(movimiento.getValor())) > 0) {
            throw new CustomException("Saldo no disponible");
        }
        setDataMovimiento(movimiento, request, cuenta);
        movimientoRepository.save(movimiento);
        cuenta.setSaldo(cuenta.getSaldo().subtract(movimiento.getValor()).add(request.getValor()));
        cuentaRepository.save(cuenta);
    }
}
