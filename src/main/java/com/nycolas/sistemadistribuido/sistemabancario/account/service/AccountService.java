package com.nycolas.sistemadistribuido.sistemabancario.account.service;

import com.nycolas.sistemadistribuido.sistemabancario.account.dto.AccountDTO;
import com.nycolas.sistemadistribuido.sistemabancario.account.model.AccountType;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.model.Costumer;

public interface AccountService {
    AccountDTO createAccount(Costumer owner, AccountType accType);

    AccountDTO createSavingsAccount(Costumer owner);

    AccountDTO createCheckingAccount(Costumer owner);
}
