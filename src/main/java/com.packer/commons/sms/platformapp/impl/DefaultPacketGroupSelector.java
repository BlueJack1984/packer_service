package com.packer.commons.sms.platformapp.impl;

import com.packer.commons.sms.platformapp.IPacketGroupSelector;
import com.packer.commons.sms.packet.PacketChannel;
import com.packer.commons.sms.packet.PacketGroup;
import com.packer.commons.sms.packet.StructureUnit;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DefaultPacketGroupSelector implements IPacketGroupSelector {
    public Map<String, PacketGroup> cache = new HashMap();

    public DefaultPacketGroupSelector() {
    }

    public PacketGroup select(PacketChannel packetChannel, Object business) {
        String key = business.getClass().getName();
        if (this.cache.get(key) != null) {
            return (PacketGroup)this.cache.get(key);
        } else {
            Class var4 = DefaultPacketGroupSelector.class;
            synchronized(DefaultPacketGroupSelector.class) {
                if (this.cache.get(key) == null) {
                    List<StructureUnit> sus = packetChannel.getMtUnits();
                    Iterator var7 = sus.iterator();

                    while(var7.hasNext()) {
                        StructureUnit su = (StructureUnit)var7.next();
                        List<PacketGroup> pgs = su.getPacketGroups();
                        Iterator var10 = pgs.iterator();

                        while(var10.hasNext()) {
                            PacketGroup pg = (PacketGroup)var10.next();
                            this.cache.put(pg.getBusinessObjClass(), pg);
                        }
                    }
                }
            }

            return (PacketGroup)this.cache.get(key);
        }
    }
}
