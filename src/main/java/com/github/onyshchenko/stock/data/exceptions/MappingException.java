package com.github.onyshchenko.stock.data.exceptions;

public class MappingException extends RuntimeException {

    public MappingException() {
        super();
    }

    public MappingException(final String message) {
        super(message);
    }
}
