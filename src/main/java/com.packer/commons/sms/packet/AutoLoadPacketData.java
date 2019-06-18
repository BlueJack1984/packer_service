package com.packer.commons.sms.packet;


import java.util.List;

public class AutoLoadPacketData extends PacketData {
    Packet p;

    public AutoLoadPacketData(Packet p) {
        this.p = p;
    }

    public List<FieldData> getFieldDatas(String fieldName) {
        if (this.fieldDatasMapping.get(fieldName) == null) {
            IField f = this.p.getBusinessField(fieldName);
            if (f == null) {
                FieldsTree control = this.p.getControlFieldsTree();
                f = control.getField(fieldName);
            }

            if (f != null) {
                f.pack();
            }
        }

        return (List)this.fieldDatasMapping.get(fieldName);
    }
}