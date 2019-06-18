package com.packer.commons.sms.packet.constructor;

import com.packer.commons.sms.SmsException;
import com.packer.commons.sms.packet.AutoLoadPacketData;
import com.packer.commons.sms.packet.ConstructResult;
import com.packer.commons.sms.packet.DataManager;
import com.packer.commons.sms.packet.FieldData;
import com.packer.commons.sms.packet.IField;
import com.packer.commons.sms.packet.IPacketConstructor;
import com.packer.commons.sms.packet.IValueSource;
import com.packer.commons.sms.packet.Packet;
import com.packer.commons.sms.packet.PacketChannel;
import com.packer.commons.sms.packet.PacketData;
import com.packer.commons.sms.packet.PacketGroup;
import com.packer.commons.sms.packet.PacketGroupData;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PacketConstructor implements IPacketConstructor {
    protected PacketChannel pc;

    public PacketConstructor(PacketChannel pc) {
        this.pc = pc;
    }

    public ConstructResult construct(PacketGroup pg, Map<String, IValueSource> data, Map<String, Object> datas) {
        PacketGroupData pgd = new PacketGroupData();
        pgd.setPacketGroupName(pg.getName());
        pgd.setPacketGroupCommand(pg.getCommand());
        pgd.setParameterDatas(datas);
        DataManager.registerFieldsValue(data);
        DataManager.savePacketGroupData(pgd);
        String[] packets = (String[])null;

        try {
            if (!pg.isSimple() && !pg.isCascadeManual()) {
                packets = this.packDynamicPacket(pg, pgd);
            } else {
                packets = this.packSimplePacket(pg, pgd);
            }
        } catch (Exception var11) {
            SmsException se = var11 instanceof SmsException ? (SmsException)var11 : new SmsException(var11.getMessage(), var11);
            this.fillSmsException(se, pg, datas);
            throw se;
        } finally {
            DataManager.clearPacketGroupData();
            DataManager.clearFieldsValue();
        }

        return new ConstructResult(pg, pgd, packets);
    }

    private PacketData initPacketData(Packet p, PacketGroup pg) {
        PacketData pd = new AutoLoadPacketData(p);
        pd.setCommand(pg.getCommand());
        pd.setPacketName(pg.getName());
        return pd;
    }

    private String[] packSimplePacket(PacketGroup pg, PacketGroupData pgd) {
        List<Packet> ps = pg.getPackets();
        String[] packets = new String[ps.size()];

        int i;
        Packet p;
        for(i = 0; i < ps.size(); ++i) {
            p = (Packet)ps.get(i);
            pgd.addPacketData(this.initPacketData(p, pg));
        }

        for(i = 0; i < ps.size(); ++i) {
            p = (Packet)ps.get(i);
            StringBuilder business = new StringBuilder();
            pgd.setCurrentIndex(i + 1);
            PacketData pd = pgd.getCurrentPacketData();
            Iterator var10 = p.getBusinessFields().iterator();

            while(true) {
                while(var10.hasNext()) {
                    IField f = (IField)var10.next();
                    List<FieldData> fds = pd.getFieldDatas(f.getName());
                    if (f.getCountLimit().isFixed()) {
                        for(int j = 0; j < f.getCountLimit().getCount(); ++j) {
                            if (fds.size() < j + 1) {
                                SmsException smsException = new SmsException("Build the field[" + f.getName() + "] need have at least " + (j + 1) + " values,but actual is " + fds.size());
                                smsException.setFieldName(f.getName());
                                throw smsException;
                            }

                            business.append(((FieldData)fds.get(j)).getTypedData());
                        }
                    } else {
                        Iterator var13 = fds.iterator();

                        while(var13.hasNext()) {
                            FieldData fd = (FieldData)var13.next();
                            business.append(fd.getTypedData());
                        }
                    }
                }

                pd.setBusinessFieldsSms(business.toString());
                String packet = this.packControls(p, pd);
                pd.setControlFieldsSms(packet);
                packets[i] = packet;
                break;
            }
        }

        return packets;
    }

    private String packControls(Packet p, PacketData pd) {
        StringBuilder sb = new StringBuilder();
        Iterator var5 = p.getControlFields().iterator();

        while(var5.hasNext()) {
            IField f = (IField)var5.next();
            List<FieldData> fds = pd.getFieldDatas(f.getName());

            for(int i = 0; i < f.getCountLimit().getCount() && i < fds.size(); ++i) {
                sb.append(((FieldData)fds.get(i)).getTypedData());
            }
        }

        return sb.toString();
    }

    private String[] packDynamicPacket(PacketGroup pg, PacketGroupData pgd) {
        List<Packet> ps = pg.getPackets();
        String[] packets = (String[])null;
        if (ps != null && ps.size() > 0) {
            Packet p = (Packet)ps.get(0);
            PacketData pd = this.initPacketData(p, pg);
            pgd.addPacketData(pd);
            List<StringBuffer> business = pg.getAutoCascadeManager().split(p, pd);
            int sizeOfActualPacket = business.size();
            if (sizeOfActualPacket == 0) {
                business.add(new StringBuffer(""));
            }

            packets = new String[business.size()];

            int i;
            for(i = 1; i < packets.length; ++i) {
                pgd.addPacketData(this.initPacketData(p, pg));
            }

            for(i = 0; i < packets.length; ++i) {
                pgd.setCurrentIndex(i + 1);
                pd = pgd.getCurrentPacketData();
                pd.setBusinessFieldsSms(((StringBuffer)business.get(i)).toString());
                String packet = this.packControls(p, pd);
                packets[i] = packet;
                pd.setControlFieldsSms(packet);
            }
        }

        return packets;
    }

    protected void fillSmsException(SmsException ex, PacketGroup group, Map<String, Object> parameterDatas) {
        if (this.pc != null) {
            ex.setPacketChannelName(this.pc.getName());
        }

        if (group != null) {
            ex.setPacketGroupName(group.getName());
        }

        ex.addProperties(DataManager.getGlobalDatas());
        ex.addProperties(parameterDatas);
    }
}
