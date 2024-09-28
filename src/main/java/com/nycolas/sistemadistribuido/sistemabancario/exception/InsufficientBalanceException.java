package com.nycolas.sistemadistribuido.sistemabancario.exception;

import org.springframework.security.access.AccessDeniedException;

public class InsufficientBalanceException extends AccessDeniedException {
    public InsufficientBalanceException(String msg) {
        super(msg);
    }
}
