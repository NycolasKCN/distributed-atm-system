package com.nycolas.sistemadistribuido.sistemabancario.account.repository;

import com.nycolas.sistemadistribuido.sistemabancario.account.model.Account;
import com.nycolas.sistemadistribuido.sistemabancario.account.model.AccountType;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.model.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(String accountNumber);
    Optional<Account> findByCostumerAndType(Costumer costumer, AccountType type);
}
