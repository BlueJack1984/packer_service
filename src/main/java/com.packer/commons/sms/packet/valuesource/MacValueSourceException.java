package com.packer.commons.sms.packet.valuesource;

import com.packer.commons.sms.packet.ValueSourceException;

public class MacValueSourceException extends ValueSourceException {
    private static final long serialVersionUID = 8438386253979178868L;

    public MacValueSourceException() {
    }

    public MacValueSourceException(String msg, Throwable ex) {
        super(msg, ex);
    }

    public MacValueSourceException(String msg) {
        super(msg);
    }

    public MacValueSourceException(Throwable ex) {
        super(ex);
    }
}
