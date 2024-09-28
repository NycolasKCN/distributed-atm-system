package com.nycolas.sistemadistribuido.sistemabancario.operations.dto;

public record TransferRequest(
        String fromAccountNumber,
        String toAccountNumber,
        double amount
) {
    @Override
    public String toString() {
        return "TransferRequest{" +
                "fromAccountNumber='" + fromAccountNumber + '\'' +
                ", toAccountNumber='" + toAccountNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}
