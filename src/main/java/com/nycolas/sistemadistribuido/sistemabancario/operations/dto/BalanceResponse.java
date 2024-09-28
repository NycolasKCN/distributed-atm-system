package com.nycolas.sistemadistribuido.sistemabancario.operations.dto;

public record BalanceResponse(
        String accountNumber,
        Double balance
) {
}
