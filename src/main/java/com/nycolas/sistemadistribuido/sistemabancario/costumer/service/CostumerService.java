package com.nycolas.sistemadistribuido.sistemabancario.costumer.service;

import com.nycolas.sistemadistribuido.sistemabancario.costumer.dto.CostumerDTO;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.dto.CreateCostumerDTO;

public interface CostumerService {
    CostumerDTO createCostumer(CreateCostumerDTO costumerDTO);

    CostumerDTO getCostumerById(Long id);

    CostumerDTO updateCostumer(CostumerDTO costumerDTO, Long id);

    void deleteCustomerById(long id);
}
