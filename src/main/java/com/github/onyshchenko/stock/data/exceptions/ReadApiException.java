package com.github.onyshchenko.stock.data.exceptions;

public class ReadApiException extends RuntimeException {

    public ReadApiException() {
        super();
    }

    public ReadApiException(final String message) {
        super(message);
    }
}
