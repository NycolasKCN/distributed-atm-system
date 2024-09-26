package com.nycolas.sistemadistribuido.sistemabancario.costumer.dto;

public record CostumerDTO(
        String username,
        String email,
        String firstName,
        String lastName
) {
    @Override
    public String toString() {
        return "CostumerDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
