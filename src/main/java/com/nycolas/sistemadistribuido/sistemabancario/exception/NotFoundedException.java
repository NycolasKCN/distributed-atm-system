package com.nycolas.sistemadistribuido.sistemabancario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundedException extends RuntimeException{
    public NotFoundedException(String s) {
        super(s);
    }
}
