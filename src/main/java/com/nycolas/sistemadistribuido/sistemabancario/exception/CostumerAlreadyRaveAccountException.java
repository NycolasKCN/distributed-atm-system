package com.nycolas.sistemadistribuido.sistemabancario.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CostumerAlreadyRaveAccountException extends RuntimeException {
    public CostumerAlreadyRaveAccountException(String msg) {
        super(msg);
    }
}
