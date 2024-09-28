package com.nycolas.sistemadistribuido.sistemabancario.operations.dto;

public record DepositRequest(
        String accountNumber,
        Double amount
) {
    @Override
    public String toString() {
        return "DepositDTO{" +
                "accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}
