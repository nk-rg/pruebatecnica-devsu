package com.devsu.examen.customerservice;

import com.devsu.examen.customerservice.dto.ClienteRequest;
import com.devsu.examen.customerservice.dto.ClienteResponse;
import com.devsu.examen.customerservice.model.Cliente;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Integer insert(ClienteRequest clienteRequest) {
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(clienteRequest, cliente);
        return customerRepository.save(cliente).getIdPersona();
    }

    @Transactional
    public void update(Integer id, ClienteRequest request) {
        Cliente cliente = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no existe"));
        BeanUtils.copyProperties(request, cliente);
        customerRepository.save(cliente);
    }

    public void delete(Integer id) {
        Cliente cliente = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no existe"));
        customerRepository.delete(cliente);
    }

    public ClienteResponse getById(Integer id) {
        Cliente cliente = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no existe"));
        ClienteResponse clienteResponse = new ClienteResponse();
        BeanUtils.copyProperties(cliente, clienteResponse);
        return clienteResponse;
    }
}
