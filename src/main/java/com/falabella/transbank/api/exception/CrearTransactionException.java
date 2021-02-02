package com.falabella.transbank.api.exception;

public class CrearTransactionException extends ServiceException{

    public CrearTransactionException(String message, Exception e) {
        super(message);
    }
}
