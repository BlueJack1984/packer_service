package com.packer.commons.sms.mapping.impl;

import com.packer.commons.sms.SmsException;

public class MappingException extends SmsException {
    private static final long serialVersionUID = 1L;

    public MappingException() {
    }

    public MappingException(String message) {
        super(message);
    }

    public MappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public MappingException(Throwable cause) {
        super(cause);
    }
}
