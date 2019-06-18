package com.packer.commons.sms.platformapp.impl;


import com.packer.commons.sms.packet.ConstructResult;
import com.packer.commons.sms.packet.PacketChannel;
import com.packer.commons.sms.packet.PacketConfigException;
import com.packer.commons.sms.packet.PacketGroup;
import com.packer.commons.sms.packet.constructor.PacketConstructor;
import com.packer.commons.sms.platformapp.IExchanger;
import com.packer.commons.sms.platformapp.IPacketGroupSelector;
import com.packer.commons.sms.platformapp.IPlatformPacketConstructor;
import com.packer.commons.sms.platformapp.SmsObjectMappingException;
import java.util.Map;

public class DefaultPlatFormPacketConstructor extends PacketConstructor implements IPlatformPacketConstructor {
    private IPacketGroupSelector selector;
    private IExchanger exchanger;

    public DefaultPlatFormPacketConstructor(PacketChannel pc, IPacketGroupSelector selector) {
        this(pc, selector, new FieldStructureExchanger());
    }

    public DefaultPlatFormPacketConstructor(PacketChannel pc, IPacketGroupSelector selector, IExchanger exchanger) {
        super(pc);
        this.selector = selector;
        this.exchanger = exchanger;
    }

    public ConstructResult construct(Object businessObject, Map<String, Object> datas) {
        if (businessObject == null) {
            throw new PacketConfigException("The business object is null!");
        } else {
            PacketGroup pg = this.selector.select(this.pc, businessObject);
            Map vss = null;

            try {
                if (pg == null) {
                    throw new PacketConfigException("根据业务对象[" + businessObject + "]找不到对应的报文组");
                }

                vss = this.exchanger.toFieldValues(pg, businessObject);
            } catch (Exception var7) {
                SmsObjectMappingException se = var7 instanceof SmsObjectMappingException ? (SmsObjectMappingException)var7 : new SmsObjectMappingException(var7.getMessage(), var7);
                this.fillSmsException(se, pg, datas);
                throw se;
            }

            ConstructResult result = this.construct(pg, vss, datas);
            return result;
        }
    }
}
