package com.packer.commons.sms.platformapp;

import com.packer.commons.sms.packet.ConstructResult;
import com.packer.commons.sms.packet.IPacketConstructor;
import java.util.Map;

public interface IPlatformPacketConstructor extends IPacketConstructor {
    ConstructResult construct(Object var1, Map<String, Object> var2);
}
