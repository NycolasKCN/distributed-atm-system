package com.nycolas.sistemadistribuido.sistemabancario.account.dto;

public record CreateAccountDTO(
        String costumerUsername
) {
    @Override
    public String toString() {
        return "CreateAccountDTO{" +
                "costumerUsername='" + costumerUsername + '\'' +
                '}';
    }
}
