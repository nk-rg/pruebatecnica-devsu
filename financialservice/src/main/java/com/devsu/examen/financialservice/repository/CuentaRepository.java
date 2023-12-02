package com.devsu.examen.financialservice.repository;

import com.devsu.examen.financialservice.model.Cuenta;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Cuenta> findByIdCuenta(Integer idCuenta);


}
