package com.nycolas.sistemadistribuido.sistemabancario.costumer.repository;

import com.nycolas.sistemadistribuido.sistemabancario.costumer.model.Costumer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CostumerRepository extends CrudRepository<Costumer, Long> {
    Optional<Costumer> findByUsername(String username);
}
