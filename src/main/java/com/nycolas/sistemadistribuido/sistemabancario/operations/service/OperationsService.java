package com.nycolas.sistemadistribuido.sistemabancario.operations.service;

import com.nycolas.sistemadistribuido.sistemabancario.account.model.Account;
import com.nycolas.sistemadistribuido.sistemabancario.account.repository.AccountRepository;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.model.Costumer;
import com.nycolas.sistemadistribuido.sistemabancario.operations.dto.BalanceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OperationsService {
    private final AccountRepository accountRepository;

    @Autowired
    public OperationsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean costumerOwnsAccount(Costumer costumer, Account account) {
        log.trace("Costumer owns account: {}", account.getCostumer().equals(costumer));
        return account.getCostumer().equals(costumer);
    }

    public void deposit(double amount, String accountNumber, Authentication auth) {
        Costumer costumer =  (Costumer) auth.getPrincipal();
        Account acc = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found."));

        log.trace("Authenticated Costumer: {}", costumer);
        log.trace("Account: {}", acc);
        if (!costumerOwnsAccount(costumer, acc)) throw new AccessDeniedException("Costumer mismatch.");

        deposit(amount, acc);
    }

    public void deposit(double amount, Account account) {
        account.deposit(amount);
        accountRepository.save(account);
    }

    public BalanceResponse balance(String accountNumber, Authentication auth) {
        Costumer costumer =  (Costumer) auth.getPrincipal();
        Account acc = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found."));

        if (!costumerOwnsAccount(costumer, acc)) throw new AccessDeniedException("Costumer mismatch.");

        return new BalanceResponse(acc.getAccountNumber(), acc.getCurrentBalance());
    }
}
