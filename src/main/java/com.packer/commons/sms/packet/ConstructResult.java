package com.packer.commons.sms.packet;

public class ConstructResult {
    private PacketGroup packetGroup;
    private PacketGroupData packetGroupData;
    private String[] smses;

    public ConstructResult(PacketGroup packetGroup, PacketGroupData packetGroupData, String[] smses) {
        this.packetGroup = packetGroup;
        this.packetGroupData = packetGroupData;
        this.smses = smses;
    }

    public PacketGroup getPacketGroup() {
        return this.packetGroup;
    }

    public PacketGroupData getPacketGroupData() {
        return this.packetGroupData;
    }

    public String[] getSmses() {
        return this.smses;
    }
}
