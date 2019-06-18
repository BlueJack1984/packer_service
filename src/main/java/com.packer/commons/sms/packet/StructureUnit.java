package com.packer.commons.sms.packet;


import com.packer.commons.sms.base.AbstractSelfObject;
import com.packer.commons.sms.packet.valuesource.BusinessDatasValueSource;
import com.packer.commons.sms.util.Assert;
import com.packer.commons.sms.util.CollectionUtil;
import com.packer.commons.sms.util.StringUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class StructureUnit extends AbstractSelfObject {
    private static final long serialVersionUID = 1L;
    private static final String UNIT_TYPE_MO = "UNIT_TYPE_MO";
    private static final String UNIT_TYPE_MT = "UNIT_TYPE_MT";
    private String bussfield;
    private String cmdField;
    private String type;
    private FieldsTree controlFields = new FieldsTree("字段结构", "");
    private LinkedHashMap<String, PacketGroup> packetGroupsMapping = new LinkedHashMap();

    private StructureUnit(String name, String description, String type) {
        super(name, description);
        this.type = type;
    }

    public static StructureUnit createMoUnit(String name, String description) {
        return new StructureUnit(name, description, "UNIT_TYPE_MO");
    }

    public static StructureUnit createMtUnit(String name, String description) {
        return new StructureUnit(name, description, "UNIT_TYPE_MT");
    }

    public boolean isMoUnit() {
        return "UNIT_TYPE_MO".equals(this.type);
    }

    public boolean isMtUnit() {
        return "UNIT_TYPE_MT".equals(this.type);
    }

    public boolean existInUnit(String fieldName) {
        if (this.controlFields.contains(fieldName)) {
            return true;
        } else {
            Collection<PacketGroup> values = this.packetGroupsMapping.values();
            Iterator it = values.iterator();

            while(it.hasNext()) {
                PacketGroup packetGroup = (PacketGroup)it.next();
                List<Packet> packets = packetGroup.getPackets();

                for(int i = 0; i < packets.size(); ++i) {
                    Packet packet = (Packet)packets.get(i);
                    if (packet.containsBusinessField(fieldName)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    public boolean existInControlFields(String fieldName) {
        return this.controlFields.contains(fieldName);
    }

    public void addControlField(IField field) throws PacketConfigException {
        if (this.existInUnit(field.getName())) {
            throw new PacketConfigException("Field[name=" + field.getName() + "]已经存在!");
        } else {
            this.controlFields.addField(field);
        }
    }

    public void addControlField(int index, IField field) throws PacketConfigException {
        if (this.existInUnit(field.getName())) {
            throw new PacketConfigException("Field[name=" + field.getName() + "]已经存在!");
        } else {
            this.controlFields.addField(index, field);
        }
    }

    public void updateControlField(String originalFieldName, IField updatedField) {
        if (!originalFieldName.equals(updatedField.getName()) && this.existInUnit(updatedField.getName())) {
            throw new PacketConfigException("Field[name=" + updatedField.getName() + "]已经存在!");
        } else {
            this.controlFields.updateField(originalFieldName, updatedField);
            if (originalFieldName.equals(this.bussfield)) {
                this.bussfield = updatedField.getName();
            }

            if (originalFieldName.equals(this.cmdField)) {
                this.cmdField = updatedField.getName();
            }

        }
    }

    public boolean removeControlField(String fieldName) {
        boolean result = this.controlFields.removeField(fieldName);
        if (result) {
            if (!this.existInControlFields(this.bussfield)) {
                this.bussfield = "";
            }

            if (!this.existInControlFields(this.cmdField)) {
                this.cmdField = "";
            }
        }

        return result;
    }

    public IField getControlField(String fieldName) {
        return this.controlFields.getField(fieldName);
    }

    public FieldsTree getControlFieldsTree() {
        return this.controlFields;
    }

    public List<IField> getControlFields() {
        return this.controlFields.getRootFields();
    }

    public void moveControlFiled(String fieldName, int step) {
        this.controlFields.moveField(fieldName, step);
    }

    public boolean containsPacketGroup(String packetGroupName) {
        return Assert.isTrimEmpty(packetGroupName) ? false : this.packetGroupsMapping.containsKey(packetGroupName);
    }

    public PacketGroup getPacketGroup(String packetGroupName) {
        return (PacketGroup)this.packetGroupsMapping.get(packetGroupName);
    }

    public boolean containsPacket(Packet packet) {
        if (Assert.isNull(packet)) {
            return false;
        } else {
            PacketGroup packetGroup = this.getPacketGroup(packet.getName());
            return packetGroup == null ? false : packetGroup.containsPacket(packet);
        }
    }

    public void addPacketGroup(PacketGroup packetGroup) throws PacketConfigException {
        String packetGroupName = packetGroup.getName();
        if (Assert.isTrimEmpty(packetGroupName)) {
            throw new PacketConfigException("Exception when add PacketGroup: The name of packetGroup is empty!");
        } else if (this.containsPacketGroup(packetGroupName)) {
            throw new PacketConfigException("Exception when add PacketGroup: The name [" + packetGroupName + "] of packetGroup is existed!");
        } else {
            this.packetGroupsMapping.put(packetGroup.getName(), packetGroup);
        }
    }

    public void updatePacketGroup(String originalPakcetGroupName, PacketGroup updatedPacketGroup) {
        String updatedGroupName = updatedPacketGroup.getName();
        if (!originalPakcetGroupName.equals(updatedGroupName) && this.containsPacketGroup(updatedGroupName)) {
            throw new PacketConfigException("Exception when update PacketGroup: The name [" + updatedGroupName + "] of packetGroup is existed!");
        } else {
            CollectionUtil.updateLinkedHashMap(this.packetGroupsMapping, originalPakcetGroupName, updatedGroupName, updatedPacketGroup);
            updatedPacketGroup.synchronizePackets();
        }
    }

    public void removePacketGroup(String packeGrouptName) {
        this.packetGroupsMapping.remove(packeGrouptName);
    }

    public void movePacketGroup(String packetGroupName, int step) {
        CollectionUtil.moveLinkedHashMap(this.packetGroupsMapping, packetGroupName, step);
    }

    protected void validate() throws PacketConfigException {
        IField field = this.getControlField(this.getBussfield());
        if (field == null) {
            throw new PacketConfigException("The unit [name=" + this.getName() + "] is not set the business field!");
        } else if (!(field instanceof AtomicField)) {
            throw new PacketConfigException("The business field of unit [name=" + this.getName() + "] is not aotmic field!");
        } else {
            AtomicField atomicField = (AtomicField)field;
            IValueSource vs = atomicField.getValueSource();
            if (vs != null && !(vs instanceof BusinessDatasValueSource)) {
                throw new PacketConfigException("The business field of unit [name=" + this.getName() + "] is invalid!");
            }
        }
    }

    public List<PacketGroup> getPacketGroups() {
        return new ArrayList(this.packetGroupsMapping.values());
    }

    public void setPacketGroups(List<PacketGroup> moPacketGroups) {
        this.packetGroupsMapping.clear();

        for(int i = 0; i < moPacketGroups.size(); ++i) {
            PacketGroup packetGroup = (PacketGroup)moPacketGroups.get(i);
            this.packetGroupsMapping.put(packetGroup.getName(), packetGroup);
        }

    }

    public String getCmdField() {
        return this.cmdField;
    }

    public void setCmdField(String cmdField) {
        this.cmdField = cmdField;
    }

    public LinkedHashMap<String, PacketGroup> getPacketGroupsMapping() {
        return this.packetGroupsMapping;
    }

    public String getBussfield() {
        return this.bussfield;
    }

    public void setBussfield(String bussfield) {
        this.bussfield = bussfield;
    }

    public String print(int indent) {
        StringBuilder sb = new StringBuilder();
        String s = StringUtil.repeat(indent, "-");
        s = s + "|" + StringUtil.repeat(3, "-");
        sb.append(s + "StructUnit: [Type=" + this.type + ", bussfield=" + this.bussfield + ", cmdField=" + this.cmdField + "]\n");
        sb.append(s + "ControlFileds:\n");
        sb.append(this.controlFields.print(indent + 4));
        sb.append(s + "PacketGroups:\n");
        Iterator var5 = this.packetGroupsMapping.values().iterator();

        while(var5.hasNext()) {
            PacketGroup pg = (PacketGroup)var5.next();
            sb.append(pg.print(indent + 4));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        StructureUnit unit = createMoUnit("", "");
        unit.addControlField(new AtomicField("", "fiel1", ""));
        unit.addControlField(new AtomicField("", "fiel2", ""));
        PacketGroup group1 = new PacketGroup("query", "", "01");
        Packet packet1 = new Packet("query", "", "01");
        packet1.addBusinessField(new AtomicField("", "field3", ""));
        group1.addPacket(packet1);
        unit.addPacketGroup(group1);
        System.out.println(unit.existInUnit("field3"));
    }
}

