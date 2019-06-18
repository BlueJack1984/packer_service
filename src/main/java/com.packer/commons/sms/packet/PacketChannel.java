package com.packer.commons.sms.packet;


import com.packer.commons.sms.base.AbstractSelfObject;
import com.packer.commons.sms.base.ISelfDescriptive;
import com.packer.commons.sms.util.Assert;
import com.packer.commons.sms.util.CollectionUtil;
import com.packer.commons.sms.util.StringUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class PacketChannel extends AbstractSelfObject implements ISelfDescriptive {
    private static final long serialVersionUID = 2150063486158236258L;
    private LinkedHashMap<String, StructureUnit> moUnitsMapping = new LinkedHashMap();
    private LinkedHashMap<String, StructureUnit> mtUnitsMapping = new LinkedHashMap();

    public PacketChannel(String name, String description) {
        super(name, description);
    }

    public boolean containsUnit(String unitName) {
        if (Assert.isTrimEmpty(unitName)) {
            return false;
        } else {
            boolean result = this.moUnitsMapping.containsKey(unitName) || this.mtUnitsMapping.containsKey(unitName);
            return result;
        }
    }

    public StructureUnit getPacketGroupUnit(String packetGroupName) {
        StructureUnit unit = this.getPacketGroupMoUnit(packetGroupName);
        if (unit != null) {
            return unit;
        } else {
            unit = this.getPacketGroupMtUnit(packetGroupName);
            return unit;
        }
    }

    public PacketGroup getPacketGroup(String packetGroupName) {
        PacketGroup packetGroup = this.getMoPacketGroup(packetGroupName);
        if (packetGroup != null) {
            return packetGroup;
        } else {
            packetGroup = this.getMtPacketGroup(packetGroupName);
            return packetGroup;
        }
    }

    public boolean containsPacketGroup(String packetGroupName) {
        if (Assert.isTrimEmpty(packetGroupName)) {
            return false;
        } else {
            boolean result = this.containsMoPacketGroup(packetGroupName) || this.containsMtPacketGroup(packetGroupName);
            return result;
        }
    }

    public StructureUnit getMoUnit(String moUnitName) {
        return (StructureUnit)this.moUnitsMapping.get(moUnitName);
    }

    public StructureUnit getPacketGroupMoUnit(String packetGroupName) {
        List<StructureUnit> units = this.getMoUnits();

        for(int i = 0; i < units.size(); ++i) {
            StructureUnit unit = (StructureUnit)units.get(i);
            if (unit.containsPacketGroup(packetGroupName)) {
                return unit;
            }
        }

        return null;
    }

    public List<StructureUnit> getMoUnits() {
        return new ArrayList(this.moUnitsMapping.values());
    }

    public PacketGroup getMoPacketGroup(String packetGroupName) {
        PacketGroup packetGroup = null;
        List<StructureUnit> units = this.getMoUnits();

        for(int i = 0; i < units.size(); ++i) {
            StructureUnit unit = (StructureUnit)units.get(i);
            packetGroup = unit.getPacketGroup(packetGroupName);
            if (packetGroup != null) {
                return packetGroup;
            }
        }

        return packetGroup;
    }

    public boolean containsMoPacketGroup(String packetGroupName) {
        if (Assert.isNull(packetGroupName)) {
            return false;
        } else {
            return this.getMoPacketGroup(packetGroupName) != null;
        }
    }

    public void addUnit(StructureUnit unit) throws PacketConfigException {
        if (unit == null) {
            throw new PacketConfigException("结构单元为空!");
        } else {
            if (unit.isMoUnit()) {
                this.addMoUnit(unit);
            }

            if (unit.isMtUnit()) {
                this.addMtUnit(unit);
            }

        }
    }

    public void addMoUnit(StructureUnit unit) throws PacketConfigException {
        if (Assert.isNull(unit)) {
            throw new PacketConfigException("Mo单元为空!");
        } else if (!unit.isMoUnit()) {
            throw new PacketConfigException("The unit[name=" + unit.getName() + "] is not MO type!");
        } else {
            String unitName = unit.getName();
            if (this.containsUnit(unitName)) {
                throw new PacketConfigException("Exception when add MO unit: The unit[name=" + unit.getName() + "] is existed!");
            } else {
                this.moUnitsMapping.put(unitName, unit);
            }
        }
    }

    public void updateMoUnit(String originalUnitName, StructureUnit moUnit) {
        String updatedMoUnitName = moUnit.getName();
        if (!originalUnitName.equals(updatedMoUnitName) && this.containsUnit(updatedMoUnitName)) {
            throw new PacketConfigException("Exception when update  MO unit: The unit[name=" + updatedMoUnitName + "] is existed!");
        } else {
            CollectionUtil.updateLinkedHashMap(this.moUnitsMapping, originalUnitName, updatedMoUnitName, moUnit);
        }
    }

    public void removeMoUnit(String unitName) {
        this.moUnitsMapping.remove(unitName);
    }

    public void moveMoUnit(String unitName, int step) {
        CollectionUtil.moveLinkedHashMap(this.moUnitsMapping, unitName, step);
    }

    public StructureUnit getMtUnit(String mtUnitName) {
        return (StructureUnit)this.mtUnitsMapping.get(mtUnitName);
    }

    public StructureUnit getPacketGroupMtUnit(String packetGroupName) {
        List<StructureUnit> units = this.getMtUnits();

        for(int i = 0; i < units.size(); ++i) {
            StructureUnit unit = (StructureUnit)units.get(i);
            if (unit.containsPacketGroup(packetGroupName)) {
                return unit;
            }
        }

        return null;
    }

    public List<StructureUnit> getMtUnits() {
        return new ArrayList(this.mtUnitsMapping.values());
    }

    public PacketGroup getMtPacketGroup(String packetGroupName) {
        PacketGroup packetGroup = null;
        List<StructureUnit> units = this.getMtUnits();

        for(int i = 0; i < units.size(); ++i) {
            StructureUnit unit = (StructureUnit)units.get(i);
            packetGroup = unit.getPacketGroup(packetGroupName);
            if (packetGroup != null) {
                return packetGroup;
            }
        }

        return packetGroup;
    }

    public boolean containsMtPacketGroup(String packetGroupName) {
        if (Assert.isNull(packetGroupName)) {
            return false;
        } else {
            return this.getMtPacketGroup(packetGroupName) != null;
        }
    }

    public void addMtUnit(StructureUnit unit) throws PacketConfigException {
        if (Assert.isNull(unit)) {
            throw new PacketConfigException("Mt单元为空!");
        } else if (!unit.isMtUnit()) {
            throw new PacketConfigException("The unit[name=" + unit.getName() + "] is not MT type!");
        } else {
            String unitName = unit.getName();
            if (this.containsUnit(unitName)) {
                throw new PacketConfigException("Exception when add  MT unit: The unit [name=" + unitName + "] is existed!");
            } else {
                this.mtUnitsMapping.put(unitName, unit);
            }
        }
    }

    public void updateMtUnit(String originalUnitName, StructureUnit mutUnit) {
        String updatedMuUnitName = mutUnit.getName();
        if (!originalUnitName.equals(updatedMuUnitName) && this.containsUnit(updatedMuUnitName)) {
            throw new PacketConfigException("Exception when update  MT unit: The unit [name=" + updatedMuUnitName + "] is existed!");
        } else {
            CollectionUtil.updateLinkedHashMap(this.mtUnitsMapping, originalUnitName, updatedMuUnitName, mutUnit);
        }
    }

    public void updateUnit(String originalUnitName, StructureUnit unit) {
        if (unit != null) {
            if (unit.isMoUnit()) {
                this.updateMoUnit(originalUnitName, unit);
            }

            if (unit.isMtUnit()) {
                this.updateMtUnit(originalUnitName, unit);
            }

        }
    }

    public void removeMtUnit(String unitName) {
        this.mtUnitsMapping.remove(unitName);
    }

    public void moveMtUnit(String unitName, int step) {
        CollectionUtil.moveLinkedHashMap(this.mtUnitsMapping, unitName, step);
    }

    public LinkedHashMap<String, StructureUnit> getMoUnitsMapping() {
        return this.moUnitsMapping;
    }

    public LinkedHashMap<String, StructureUnit> getMtUnitsMapping() {
        return this.mtUnitsMapping;
    }

    public void validate() throws PacketConfigException {
        List<StructureUnit> allUnits = new ArrayList();
        allUnits.addAll(this.getMoUnits());
        allUnits.addAll(this.getMtUnits());

        for(int i = 0; i < allUnits.size(); ++i) {
            StructureUnit currentUnit = (StructureUnit)allUnits.get(i);
            currentUnit.validate();
            this.validatePacketGroup(allUnits, currentUnit);
            this.validateControlFields(currentUnit.getControlFields(), currentUnit);
            this.validateBusinessFields(currentUnit);
        }

    }

    private void validatePacketGroup(List<StructureUnit> allUnits, StructureUnit currentUnit) {
        for(int i = 0; i < allUnits.size(); ++i) {
            StructureUnit unit = (StructureUnit)allUnits.get(i);
            if (unit != currentUnit) {
                List<PacketGroup> packetGroups = currentUnit.getPacketGroups();

                for(int j = 0; j < packetGroups.size(); ++j) {
                    PacketGroup group = (PacketGroup)packetGroups.get(j);
                    if (unit.containsPacketGroup(group.getName())) {
                        throw new PacketConfigException("The packetGroup [name=" + group.getName() + "] is not unique in the PacketChannel[name=" + this.getName() + "]!");
                    }
                }
            }
        }

    }

    private void validateControlFields(List<IField> controlFields, StructureUnit currentUnit) {
        for(int i = 0; i < controlFields.size(); ++i) {
            IField controlField = (IField)controlFields.get(i);
            this.validateControlField(controlField, currentUnit);
            if (controlField instanceof FieldGroup) {
                FieldGroup fieldGroup = (FieldGroup)controlField;
                this.validateControlFields(fieldGroup.getFields(), currentUnit);
            }
        }

    }

    private void validateControlField(IField controlField, StructureUnit currentUnit) {
        List<PacketGroup> packetGroups = currentUnit.getPacketGroups();

        for(int i = 0; i < packetGroups.size(); ++i) {
            PacketGroup packetGroup = (PacketGroup)packetGroups.get(i);
            List<Packet> packets = packetGroup.getPackets();

            for(int j = 0; j < packets.size(); ++j) {
                Packet packet = (Packet)packets.get(j);
                if (packet.containsBusinessField(controlField.getName())) {
                    throw new PacketConfigException("The field [name=" + controlField.getName() + "] is not unique in the unit[name=" + currentUnit.getName() + "]!");
                }
            }
        }

    }

    private void validateBusinessFields(StructureUnit currentUnit) {
        List<PacketGroup> groups = currentUnit.getPacketGroups();

        for(int i = 0; i < groups.size(); ++i) {
            PacketGroup group = (PacketGroup)groups.get(i);
            List<Packet> packets = group.getPackets();

            for(int j = 0; j < packets.size(); ++j) {
                Packet packet = (Packet)packets.get(j);
                List<IField> businessFields = packet.getBusinessFields();
                this.validateBusinessFields(businessFields, packets, packet);
            }
        }

    }

    private void validateBusinessFields(List<IField> businessFields, List<Packet> packets, Packet excludePacket) {
        for(int i = 0; i < businessFields.size(); ++i) {
            IField field = (IField)businessFields.get(i);

            for(int j = 0; j < packets.size(); ++j) {
                Packet packet = (Packet)packets.get(j);
                if (packet != excludePacket) {
                    if (packet.containsBusinessField(field.getName())) {
                        throw new PacketConfigException("The field [name=" + field.getName() + "] is not unique in the PacketGroup[name=" + packet.getName() + "]!");
                    }

                    if (field instanceof FieldGroup) {
                        List<IField> childFields = ((FieldGroup)field).getFields();
                        this.validateBusinessFields(childFields, packets, excludePacket);
                    }
                }
            }
        }

    }

    public void print(int indent) {
        if (this.logger.isDebugEnabled()) {
            StringBuilder sb = new StringBuilder();
            String s = StringUtil.repeat(indent, "-");
            s = s + "|" + StringUtil.repeat(3, "-");
            sb.append(s + "PacketChannel:" + this.getName() + "\n");
            sb.append(s + "MO:\n");
            Collection<StructureUnit> mos = this.moUnitsMapping.values();
            Iterator var6 = mos.iterator();

            while(var6.hasNext()) {
                StructureUnit mo = (StructureUnit)var6.next();
                sb.append(mo.print(indent + 4));
            }

            sb.append(s + "MTS:\n");
            Collection<StructureUnit> mts = this.mtUnitsMapping.values();
            Iterator var7 = mts.iterator();

            while(var7.hasNext()) {
                StructureUnit mt = (StructureUnit)var7.next();
                sb.append(mt.print(indent + 4));
            }

            this.logger.debug("----------------------Channel-------------------------\n" + sb.toString());
        }

    }
}
