package com.nycolas.sistemadistribuido.sistemabancario.account.service;

import com.nycolas.sistemadistribuido.sistemabancario.account.dto.AccountDTO;
import com.nycolas.sistemadistribuido.sistemabancario.account.model.Account;
import com.nycolas.sistemadistribuido.sistemabancario.account.model.AccountType;
import com.nycolas.sistemadistribuido.sistemabancario.account.repository.AccountRepository;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.model.Costumer;
import com.nycolas.sistemadistribuido.sistemabancario.exception.NotFoundedException;
import com.nycolas.sistemadistribuido.sistemabancario.exception.CostumerAlreadyRaveAccountException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO createSavingsAccount(Costumer owner) {
        return createAccount(owner, AccountType.SAVINGS);
    }

    @Override
    public AccountDTO createCheckingAccount(Costumer owner) {
        return createAccount(owner, AccountType.CHECKING);
    }

    @Override
    public AccountDTO getSavingAccount(Costumer owner) {
        return getAccount(owner, AccountType.SAVINGS);
    }

    @Override
    public AccountDTO getCheckingAccount(Costumer owner) {
        return getAccount(owner, AccountType.CHECKING);
    }

    private AccountDTO getAccount(Costumer owner, AccountType accType) {
        return this.accountRepository.findByCostumerAndType(owner, accType)
                .orElseThrow(() -> new NotFoundedException("Costumer don't have an savings account"))
                .toDto();
    }

    private AccountDTO createAccount(Costumer owner, AccountType accType) {
        var accountOptional = accountRepository.findByCostumerAndType(owner, accType);
        if (accountOptional.isPresent()) throw new CostumerAlreadyRaveAccountException("Costumer already have an " + accType + " account");

        Account account = new Account(owner, accType, generateAccountNumber() , 0.0);

        log.trace("Creating account: {}", account);

        return accountRepository.save(account).toDto();
    }

    private String generateAccountNumber() {
        Long number = new Random().nextLong(10000, 99999);
        return String.valueOf(number);
    }
}
