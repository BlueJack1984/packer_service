package com.packer.commons.sms.packet;


import com.packer.commons.sms.SmsException;

public class PacketConfigException extends SmsException {
    private static final long serialVersionUID = 1L;

    public PacketConfigException() {
    }

    public PacketConfigException(String msg) {
        super(msg);
    }

    public PacketConfigException(String msg, Throwable ex) {
        super(msg, ex);
    }

    public PacketConfigException(Throwable ex) {
        super(ex);
    }
}
