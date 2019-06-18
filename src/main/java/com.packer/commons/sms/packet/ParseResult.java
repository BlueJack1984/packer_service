package com.packer.commons.sms.packet;

import java.util.List;

public class ParseResult {
    private boolean isParseOver;
    private List<String> receivedSmses;
    private PacketGroupData groupData;
    private PacketGroup packetGroup;

    public ParseResult() {
    }

    public boolean isParseOver() {
        return this.isParseOver;
    }

    public void setParseOver(boolean isParseOver) {
        this.isParseOver = isParseOver;
    }

    public PacketGroupData getGroupData() {
        return this.groupData;
    }

    public void setGroupData(PacketGroupData groupData) {
        this.groupData = groupData;
    }

    public List<String> getReceivedSmses() {
        return this.receivedSmses;
    }

    public void setReceivedSmses(List<String> receivedSmses) {
        this.receivedSmses = receivedSmses;
    }

    public PacketGroup getPacketGroup() {
        return this.packetGroup;
    }

    public void setPacketGroup(PacketGroup packetGroup) {
        this.packetGroup = packetGroup;
    }
}
