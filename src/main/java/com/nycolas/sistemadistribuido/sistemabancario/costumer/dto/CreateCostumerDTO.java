package com.nycolas.sistemadistribuido.sistemabancario.costumer.dto;

public record CreateCostumerDTO(
        String username,
        String email,
        String firstName,
        String lastName,
        String password
) {
    @Override
    public String toString() {
        return "CreateCostumerDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
