package com.packer.commons.sms.packet;

import com.packer.commons.sms.mapping.XMLMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DefaultChannelManager implements IChannelManager {
    public Map<String, PacketChannel> channels;
    XMLMapping impl;

    public DefaultChannelManager() {
    }

    public List<PacketChannel> loadPacketChannels() {
        if (this.channels == null) {
            Class var1 = DefaultChannelManager.class;
            synchronized(DefaultChannelManager.class) {
                if (this.channels == null) {
                    this.channels = new HashMap();
                    List<PacketChannel> list = this.impl.loadChannels();
                    if (list != null) {
                        Iterator var4 = list.iterator();

                        while(var4.hasNext()) {
                            PacketChannel pc = (PacketChannel)var4.next();
                            this.channels.put(pc.getName(), pc);
                        }
                    }
                }
            }
        }

        return new ArrayList(this.channels.values());
    }

    public PacketChannel loadPacketChannel(String channelName) {
        if (this.channels == null) {
            this.loadPacketChannels();
        }

        if (this.channels.get(channelName) == null) {
            throw new PacketConfigException("The [" + channelName + "] doesn't exist in the config");
        } else {
            return (PacketChannel)this.channels.get(channelName);
        }
    }

    public void removePacketChannel(String channelName) {
        if (this.channels == null) {
            this.loadPacketChannels();
        }

        this.channels.remove(channelName);
    }

    public void removePacketChannels() {
        if (this.channels != null) {
            this.channels.clear();
        }

    }

    public void savePacketChannel(PacketChannel packetChannel) {
        List<PacketChannel> pc = new ArrayList();
        pc.add(packetChannel);
        this.impl.serializeChannels(pc);
    }

    public void updatePacketChannel(String originalChannelName, PacketChannel packetChannel) {
        List<PacketChannel> pc = new ArrayList();
        pc.add(packetChannel);
        this.impl.serializeChannels(pc);
    }

    public XMLMapping getImpl() {
        return this.impl;
    }

    public void setImpl(XMLMapping impl) {
        this.impl = impl;
    }

    public void savePacketChannels(List<PacketChannel> channles) {
        this.impl.serializeChannels(channles);
    }
}
