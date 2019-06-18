package com.packer.commons.sms.serialize;

public class SerializerException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SerializerException() {
    }

    public SerializerException(String msg, Throwable ex) {
        super(msg, ex);
    }

    public SerializerException(String msg) {
        super(msg);
    }

    public SerializerException(Throwable ex) {
        super(ex);
    }
}

