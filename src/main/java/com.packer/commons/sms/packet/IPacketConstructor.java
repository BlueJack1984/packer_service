package com.packer.commons.sms.packet;

import java.util.Map;

public interface IPacketConstructor {
    ConstructResult construct(PacketGroup var1, Map<String, IValueSource> var2, Map<String, Object> var3);
}
