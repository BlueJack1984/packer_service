package com.packer.commons.sms.packet;

import com.packer.commons.sms.base.IExtendPointer;

public interface ICountLimit extends IExtendPointer {
    boolean isFixed() throws CountLimitException;

    int getCount() throws CountLimitException;
}
