package com.packer.commons.sms.packet;
import com.packer.commons.sms.base.AbstractSelfObject;
import com.packer.commons.sms.base.ISelfDescriptive;
import com.packer.commons.sms.util.StringUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PacketGroup extends AbstractSelfObject implements ISelfDescriptive, Cloneable {
    private static final long serialVersionUID = 1L;
    private static final String SIMPLE_PACKET_STATE = "SIMPLE_PACKET_STATE";
    private static final String CASCADE_PACKETS_AUTO_STATE = "CASCADE_PACKETS_AUTO_STATE";
    private static final String CASCADE_PACKETS_MANUAL_STATE = "CASCADE_PACKETS_MANUAL_STATE";
    private String state = "SIMPLE_PACKET_STATE";
    private String command;
    private List<Packet> packets = new ArrayList();
    private String businessObjClass;
    private IAutoCascadeManager autoCascadeManager = null;

    public PacketGroup() {
    }

    public PacketGroup(String name, String description) {
        super(name, description);
    }

    public PacketGroup(String name, String description, String command) {
        super(name, description);
        this.command = command;
    }

    public boolean containsPacket(Packet packet) {
        return this.packets.contains(packet);
    }

    public int getPacketCount() {
        return this.packets.size();
    }

    public void addPacket(Packet packet) {
        String groupName = this.getName();
        if (!packet.getName().equals(groupName)) {
            throw new PacketConfigException("Exception when add the packet：The name of packet [" + packet.getName() + " is not same with the name of PacketGroup [" + groupName + "]!");
        } else {
            if (this.command != null) {
                if (!this.command.equals(packet.getCommand())) {
                    throw new PacketConfigException("Exception when add the packet：The command of packet [" + packet.getCommand() + " is not same with the command of PacketGroup [" + this.command + "]!");
                }
            } else if (packet.getCommand() != null) {
                throw new PacketConfigException("Exception when add the packet：The command of packet [" + packet.getCommand() + " is not same with the command of PacketGroup [" + this.command + "]!");
            }

            if (this.getPacketCount() == 0) {
                this.packets.add(packet);
                this.state = "SIMPLE_PACKET_STATE";
            } else {
                if (!this.isSimple() && !this.isCascadeManual()) {
                    throw new PacketConfigException("Exception when add the packet：The PacketGroup [name=" + groupName + "] is alread set the  CascadeManager!");
                }

                this.packets.add(packet);
                this.state = "CASCADE_PACKETS_MANUAL_STATE";
            }

        }
    }

    public void updatePacket(int index, Packet updatedPacket) {
        this.packets.set(index, updatedPacket);
    }

    public boolean removePacket(Packet packet) {
        boolean result = this.packets.remove(packet);
        if (result && this.isCascadeManual() && this.getPacketCount() <= 1) {
            this.state = "SIMPLE_PACKET_STATE";
        }

        return result;
    }

    public boolean canUpdateCascadeManager() {
        return !this.isCascadeManual();
    }

    public boolean isSimple() {
        return this.state.equals("SIMPLE_PACKET_STATE");
    }

    public boolean isCascadeAuto() {
        return this.state.equals("CASCADE_PACKETS_AUTO_STATE");
    }

    public boolean isCascadeManual() {
        return this.state.equals("CASCADE_PACKETS_MANUAL_STATE");
    }

    public String getCommand() {
        return this.command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Packet> getPackets() {
        return this.packets;
    }

    public void setPackets(List<Packet> packets) {
        this.packets = packets;
    }

    public IAutoCascadeManager getAutoCascadeManager() {
        return this.autoCascadeManager;
    }

    public void setAutoCascadeSplitor(IAutoCascadeManager autoCascadeManager) {
        if (this.isCascadeManual()) {
            throw new PacketConfigException("Exception when set the CasacdeManager：The PacketGroup [name=" + this.getName() + "] is alread add " + this.getPacketCount() + " packets!");
        } else {
            if (autoCascadeManager != null) {
                this.state = "CASCADE_PACKETS_AUTO_STATE";
            } else {
                this.state = "SIMPLE_PACKET_STATE";
            }

            this.autoCascadeManager = autoCascadeManager;
        }
    }

    public String getBusinessObjClass() {
        return this.businessObjClass;
    }

    public void setBusinessObjClass(String businessObjClass) {
        this.businessObjClass = businessObjClass;
    }

    public void synchronizePackets() {
        for(int i = 0; i < this.packets.size(); ++i) {
            Packet packet = (Packet)this.packets.get(i);
            packet.setName(this.getName());
            packet.setCommand(this.command);
        }

    }

    public String print(int indent) {
        StringBuilder root = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        sb.append("Type=" + this.getState());
        sb.append(",Cmd=" + this.getCommand());
        sb.append(",PacketCount=" + this.getPacketCount());
        sb.append(",BusinessClass=" + this.getBusinessObjClass());
        sb.append(",AutoCascadeSplitor=" + (this.getAutoCascadeManager() != null ? this.getAutoCascadeManager().getClass() : null));
        String s = StringUtil.repeat(indent, "-");
        s = s + "|" + StringUtil.repeat(3, "-");
        root.append(s + "PacketGroup:" + this.getName() + " [" + sb.toString() + "]\n");
        Iterator var6 = this.getPackets().iterator();

        while(var6.hasNext()) {
            Packet p = (Packet)var6.next();
            root.append(p.print(indent + 4));
        }

        return root.toString();
    }
}
