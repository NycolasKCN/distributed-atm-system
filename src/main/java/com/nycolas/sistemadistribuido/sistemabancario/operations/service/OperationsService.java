package com.nycolas.sistemadistribuido.sistemabancario.operations.service;

import com.nycolas.sistemadistribuido.sistemabancario.account.dto.AccountDTO;
import com.nycolas.sistemadistribuido.sistemabancario.account.model.Account;
import com.nycolas.sistemadistribuido.sistemabancario.account.repository.AccountRepository;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.model.Costumer;
import com.nycolas.sistemadistribuido.sistemabancario.exception.InsufficientBalanceException;
import com.nycolas.sistemadistribuido.sistemabancario.exception.NotFoundedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public AccountDTO deposit(double amount, String accountNumber, Authentication auth) {
        Costumer costumer =  (Costumer) auth.getPrincipal();
        Account acc = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new NotFoundedException("Account not found."));

        log.trace("Authenticated Costumer: {}", costumer);
        log.trace("Account: {}", acc);
        if (!costumerOwnsAccount(costumer, acc)) throw new AccessDeniedException("Costumer mismatch.");

        return deposit(amount, acc);
    }

    private AccountDTO deposit(double amount, Account account) {
        account.deposit(amount);
        return accountRepository.save(account).toDto();
    }

    public AccountDTO balance(String accountNumber, Authentication auth) {
        Costumer costumer =  (Costumer) auth.getPrincipal();
        Account acc = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new NotFoundedException("Account not found."));

        if (!costumerOwnsAccount(costumer, acc)) throw new AccessDeniedException("Costumer mismatch.");

        return acc.toDto();
    }

    public AccountDTO withdrawal(double amount, String accountNumber, Authentication auth) {
        Costumer costumer =  (Costumer) auth.getPrincipal();
        Account acc = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new NotFoundedException("Account not found."));

        if(!costumerOwnsAccount(costumer, acc)) throw new AccessDeniedException("Costumer mismatch.");

        return withdrawal(amount, acc);
    }

    private AccountDTO withdrawal(double amount, Account account) {
        if (account.getCurrentBalance() < amount) throw new InsufficientBalanceException("Insufficient Balance.");
        account.withdrawal(amount);
        return accountRepository.save(account).toDto();
    }

    @Transactional
    public void transfer(double amount, String fromAccount, String toAccount, Authentication auth) {
        Costumer costumer = (Costumer) auth.getPrincipal();
        Account fromAcc = accountRepository.findByAccountNumber(fromAccount)
                .orElseThrow(() -> new NotFoundedException("From account not found."));
        Account toAcc = accountRepository.findByAccountNumber(toAccount)
                .orElseThrow(() -> new NotFoundedException("To account not found."));

        if (!costumerOwnsAccount(costumer, fromAcc)) throw new AccessDeniedException("Costumer mismatch.");

        transfer(amount, fromAcc, toAcc);
    }

    @Transactional
    protected void transfer(double amount, Account fromAccount, Account toAccount) {
        withdrawal(amount, fromAccount);
        deposit(amount, toAccount);
    }
}
