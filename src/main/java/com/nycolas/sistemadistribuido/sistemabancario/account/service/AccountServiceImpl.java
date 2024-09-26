package com.nycolas.sistemadistribuido.sistemabancario.account.service;

import com.nycolas.sistemadistribuido.sistemabancario.account.dto.AccountDTO;
import com.nycolas.sistemadistribuido.sistemabancario.account.model.Account;
import com.nycolas.sistemadistribuido.sistemabancario.account.model.AccountType;
import com.nycolas.sistemadistribuido.sistemabancario.account.repository.AccountRepository;
import com.nycolas.sistemadistribuido.sistemabancario.config.JwtService;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.model.Costumer;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.repository.CostumerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;
    private final JwtService jwtService;
    private final CostumerRepository costumerRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository,
                              CostumerRepository costumerRepository,
                              JwtService jwtService) {
        this.accountRepository = accountRepository;
        this.costumerRepository = costumerRepository;
        this.jwtService = jwtService;
    }

    @Override
    public AccountDTO createAccount(Costumer owner, AccountType accType) {
        Account account = new Account(owner, accType, generateAccountNumber() , 0.0);

        log.trace("Creating account {}", account);

        return accountRepository.save(account).toDto();
    }

    @Override
    public AccountDTO createSavingsAccount(Costumer owner) {
        return createAccount(owner, AccountType.SAVINGS);
    }

    @Override
    public AccountDTO createCheckingAccount(Costumer owner) {
        return createAccount(owner, AccountType.CHECKING);
    }

    public String generateAccountNumber() {
        Long number = new Random().nextLong(10000, 99999);
        return String.valueOf(number);
    }
}
