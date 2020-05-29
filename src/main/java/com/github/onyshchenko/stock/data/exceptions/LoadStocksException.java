package com.github.onyshchenko.stock.data.exceptions;

public class LoadStocksException extends RuntimeException {

    public LoadStocksException() {
        super();
    }

    public LoadStocksException(final String message) {
        super(message);
    }
}
