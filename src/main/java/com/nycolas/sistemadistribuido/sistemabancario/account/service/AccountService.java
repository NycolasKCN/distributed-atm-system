package com.nycolas.sistemadistribuido.sistemabancario.account.service;

import com.nycolas.sistemadistribuido.sistemabancario.account.dto.AccountDTO;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.model.Costumer;

public interface AccountService {
    AccountDTO createSavingsAccount(Costumer owner);

    AccountDTO createCheckingAccount(Costumer owner);

    AccountDTO getSavingAccount(Costumer owner);

    AccountDTO getCheckingAccount(Costumer owner);
}
