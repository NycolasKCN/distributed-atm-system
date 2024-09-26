package com.nycolas.sistemadistribuido.sistemabancario.account.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountType {
    CHECKING("checking account"),
    SAVINGS("savings account");

    private final String description;
}
