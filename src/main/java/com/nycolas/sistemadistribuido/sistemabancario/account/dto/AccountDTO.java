package com.nycolas.sistemadistribuido.sistemabancario.account.dto;

import com.nycolas.sistemadistribuido.sistemabancario.account.model.AccountType;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.dto.CostumerDTO;

public record AccountDTO(
        CostumerDTO owner,
        AccountType accountType,
        String accountNumber,
        Double currentBalance
) {
}
