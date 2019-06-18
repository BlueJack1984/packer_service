package com.packer.commons.sms.packet.parser;


import com.packer.commons.sms.packet.PacketGroup;
import java.util.List;

public class HandleResult {
    private int total;
    private int currentIndex;
    private List<String> receivedSmses;
    private PacketGroup packetGroup;

    public HandleResult() {
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrentIndex() {
        return this.currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
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

    public boolean isReceivedOver() {
        if (this.receivedSmses == null) {
            return false;
        } else {
            return this.total == this.receivedSmses.size();
        }
    }
}

