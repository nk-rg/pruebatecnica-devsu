package com.devsu.examen.customerservice;

import com.devsu.examen.customerservice.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerRepository extends JpaRepository<Cliente, Integer> {
}
