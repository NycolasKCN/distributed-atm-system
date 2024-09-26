package com.nycolas.sistemadistribuido.sistemabancario.costumer.controller;

import com.nycolas.sistemadistribuido.sistemabancario.costumer.dto.CostumerDTO;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.dto.CreateCostumerDTO;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.service.CostumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("v1/api/user")
@Slf4j
public class CostumerController {
    private final CostumerService costumerService;

    @Autowired
    public CostumerController(CostumerService costumerService) {
        this.costumerService = costumerService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CostumerDTO createUser(@RequestBody CreateCostumerDTO costumerDTO) {
        log.debug("New costumer to create: {}", costumerDTO);
        return this.costumerService.createCostumer(costumerDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public CostumerDTO getUserById(@PathVariable long id) {
        // TODO: Implement authorization
        log.debug("Get costumer by id: {}", id);
        return this.costumerService.getCostumerById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public CostumerDTO updateUser(@RequestBody CostumerDTO costumerDTO, @PathVariable Long id) {
        // TODO: Implement authorization
        log.debug("Costumer to update: {}", costumerDTO);
        return this.costumerService.updateCostumer(costumerDTO,id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        // TODO: Implement authorization
        log.debug("Delete costumer by id: {}", id);
        this.costumerService.deleteCustomerById(id);
    }
}
