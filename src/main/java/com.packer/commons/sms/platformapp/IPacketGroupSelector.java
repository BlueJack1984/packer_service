package com.packer.commons.sms.platformapp;

import com.packer.commons.sms.packet.PacketChannel;
import com.packer.commons.sms.packet.PacketGroup;

public interface IPacketGroupSelector {
    PacketGroup select(PacketChannel var1, Object var2);
}
