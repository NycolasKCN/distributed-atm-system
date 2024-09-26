package com.nycolas.sistemadistribuido.sistemabancario.account.service;

import com.nycolas.sistemadistribuido.sistemabancario.account.dto.AccountDTO;
import com.nycolas.sistemadistribuido.sistemabancario.account.dto.CreateAccountDTO;
import com.nycolas.sistemadistribuido.sistemabancario.account.model.AccountType;

public interface AccountService {
    AccountDTO createAccount(CreateAccountDTO accountDTO, AccountType accType, String token);

    AccountDTO createSavingsAccount(CreateAccountDTO accountDTO, String token);

    AccountDTO createCheckingAccount(CreateAccountDTO accountDTO, String token);
}
