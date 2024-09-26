package com.nycolas.sistemadistribuido.sistemabancario.costumer.repository;

import com.nycolas.sistemadistribuido.sistemabancario.costumer.model.Costumer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CostumerRepository extends CrudRepository<Costumer, Long> {
    @Query("select c from Costumer c where c.username=?1")
    Optional<Costumer> findByUsername(String username);
}
