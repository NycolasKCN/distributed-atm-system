package com.nycolas.sistemadistribuido.sistemabancario.costumer.service;

import com.nycolas.sistemadistribuido.sistemabancario.costumer.dto.CreateCostumerDTO;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.repository.CostumerRepository;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.dto.CostumerDTO;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.model.Costumer;
import com.nycolas.sistemadistribuido.sistemabancario.exception.NotFoundedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@Slf4j
public class CostumerServiceImpl implements CostumerService {
    private final CostumerRepository costumerRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public CostumerServiceImpl(CostumerRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.costumerRepository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public CostumerDTO createCostumer(CreateCostumerDTO user) {
        Costumer newCostumer = new Costumer(user);
        newCostumer.setPassword(passwordEncoder.encode(user.password()));

        return costumerRepository
                .save(newCostumer)
                .toDto();
    }

    public void deleteCustomerById(long userId) {
        costumerRepository.deleteById(userId);
    }

    public CostumerDTO getCostumerById(Long id) throws NotFoundedException {
        return this.costumerRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundedException("Costumer not founded."))
                .toDto();
    }

    public CostumerDTO updateCostumer(CostumerDTO costumerDTO, Long id) throws NotFoundedException {
        var currentCostumer = costumerRepository.findById(id).orElseThrow(() -> new NotFoundedException("Costumer not founded."));
        log.debug("Current costumer: {}", currentCostumer);

        var updatedCostumer = new Costumer(costumerDTO);
        updatedCostumer.setId(currentCostumer.getId());
        updatedCostumer.setPassword(currentCostumer.getPassword());

        log.debug("Updeted costumer: {}", updatedCostumer);

        return costumerRepository
                .save(updatedCostumer)
                .toDto();
    }


}
