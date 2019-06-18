package com.packer.commons.sms.packet;

import java.util.List;

public interface IChannelManager {
    void savePacketChannels(List<PacketChannel> var1);

    void savePacketChannel(PacketChannel var1);

    List<PacketChannel> loadPacketChannels();

    PacketChannel loadPacketChannel(String var1);

    void updatePacketChannel(String var1, PacketChannel var2);

    void removePacketChannel(String var1);

    void removePacketChannels();
}
