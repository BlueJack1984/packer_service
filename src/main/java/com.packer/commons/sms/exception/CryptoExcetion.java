package com.packer.commons.sms.exception;

public class CryptoExcetion extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CryptoExcetion() {
    }

    public CryptoExcetion(String message) {
        super(message);
    }

    public CryptoExcetion(String message, Throwable cause) {
        super(message, cause);
    }

    public CryptoExcetion(Throwable cause) {
        super(cause);
    }
}