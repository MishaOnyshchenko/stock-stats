package com.github.onyshchenko.stock.data.exceptions;

public class LoadSymbolsException extends RuntimeException {

    public LoadSymbolsException() {
        super();
    }

    public LoadSymbolsException(final String message) {
        super(message);
    }
}
