package com.packer.commons.sms.platformapp.impl;

import com.packer.commons.sms.packet.AtomicField;
import com.packer.commons.sms.packet.FieldData;
import com.packer.commons.sms.packet.FieldGroup;
import com.packer.commons.sms.packet.IField;
import com.packer.commons.sms.packet.IValueSource;
import com.packer.commons.sms.packet.Packet;
import com.packer.commons.sms.packet.PacketConfigException;
import com.packer.commons.sms.packet.PacketGroup;
import com.packer.commons.sms.packet.PacketGroupData;
import com.packer.commons.sms.platformapp.IExchanger;
import com.packer.commons.sms.platformapp.IObjectPropertyExchanger;
import com.packer.commons.sms.platformapp.SmsObjectMappingException;
import com.packer.commons.sms.util.Assert;
import com.packer.commons.sms.util.ClassUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldStructureExchanger implements IExchanger {
    private ObjMapping objMapping = new ObjMapping();
    private List<IObjectPropertyExchanger<Object>> objectPropertyExchangers;
    private Map<String, List<PropertyChain>> propertyChainCache = new HashMap();

    public FieldStructureExchanger() {
    }

    protected List<PropertyChain> loadPropertyChains(PacketGroup packetGroup) throws PacketConfigException {
        List<PropertyChain> propertyChains = new ArrayList();
        List<Packet> packets = packetGroup.getPackets();
        if (packets != null && packets.size() != 0) {
            List<IField> controlFields = ((Packet)packets.get(0)).getControlFields();
            this.loadFieldsPropertyChains(controlFields, propertyChains);

            for(int i = 0; i < packets.size(); ++i) {
                Packet packet = (Packet)packets.get(i);
                this.loadFieldsPropertyChains(packet.getBusinessFields(), propertyChains);
            }

            return propertyChains;
        } else {
            return propertyChains;
        }
    }

    protected void loadFieldsPropertyChains(List<IField> fields, List<PropertyChain> propertyChains) {
        for(int i = 0; i < fields.size(); ++i) {
            IField field = (IField)fields.get(i);
            if (field instanceof AtomicField) {
                this.loadAtomicFieldPropertyChain((AtomicField)field, propertyChains);
            } else if (field instanceof FieldGroup) {
                FieldGroup fieldGroup = (FieldGroup)field;
                this.loadFieldsPropertyChains(fieldGroup.getFields(), propertyChains);
            }
        }

    }

    protected void loadAtomicFieldPropertyChain(AtomicField aotmicField, List<PropertyChain> propertyChains) {
        String classProperty = aotmicField.getClassProperty();
        if (!Assert.isEmpty(classProperty)) {
            String[] properties = classProperty.split("\\.");
            PropertyChain chain = new PropertyChain();
            chain.setFieldName(aotmicField.getName());
            chain.setProperties(properties);
            propertyChains.add(chain);
        }
    }

    private List<PropertyChain> getPropertyChains(PacketGroup packetGroup) {
        String groupName = packetGroup.getName();
        List<PropertyChain> chains = (List)this.propertyChainCache.get(groupName);
        if (chains == null) {
            chains = this.loadPropertyChains(packetGroup);
            this.propertyChainCache.put(groupName, chains);
        }

        return chains;
    }

    protected Object getBusinessObject(PacketGroup packetGroup, PacketGroupData groupData) {
        String businessObjClass = packetGroup.getBusinessObjClass();
        if (Assert.isEmpty(businessObjClass)) {
            return null;
        } else {
            Object packetGroupObj = this.objMapping.newInstance(businessObjClass);
            return packetGroupObj;
        }
    }

    public Object toBusinessObject(PacketGroup packetGroup, PacketGroupData groupData) throws SmsObjectMappingException {
        String businessObjClass = packetGroup.getBusinessObjClass();
        if (Assert.isEmpty(businessObjClass)) {
            return null;
        } else {
            Object packetGroupObj = this.getBusinessObject(packetGroup, groupData);
            this.propertyChainCache.get(packetGroup.getName());
            List<PropertyChain> propertyChains = this.getPropertyChains(packetGroup);

            for(int i = 0; i < propertyChains.size(); ++i) {
                PropertyChain chain = (PropertyChain)propertyChains.get(i);
                String[] properties = chain.getProperties();
                if (properties.length == 1) {
                    this.setBaseProperty(packetGroupObj, chain.getFieldName(), chain.getProperties()[0], groupData);
                } else if (properties.length == 2) {
                    this.setNestedProperty(packetGroupObj, chain, groupData);
                }
            }

            return packetGroupObj;
        }
    }

    private List<String> getInputDatas(List<FieldData> fieldDatas) {
        List<String> result = new ArrayList(fieldDatas.size());

        for(int i = 0; i < fieldDatas.size(); ++i) {
            FieldData fieldData = (FieldData)fieldDatas.get(i);
            result.add(fieldData.getInputData());
        }

        return result;
    }

    private void setBaseProperty(Object object, String fieldName, String property, PacketGroupData groupData) {
        List<FieldData> fieldDatas = groupData.getFieldDatas(fieldName);
        if (fieldDatas != null) {
            this.objMapping.setSimpleTypeValues(object, property, this.getInputDatas(fieldDatas));
        }
    }

    private void setArrayNestedProperty(Object groupObject, PropertyChain chain, PacketGroupData groupData, Class<?> arrayClass) {
        Class<?> arrayType = ClassUtil.getArrayType(arrayClass);
        if (ClassUtil.isArray(arrayType)) {
            throw new SmsObjectMappingException("字段[" + chain.getFieldName() + "]配置成多纬数组!");
        } else if (this.objMapping.isSimpleType(arrayType)) {
            throw new SmsObjectMappingException("字段[" + chain.getFieldName() + "]配置成简单类型数组!");
        } else {
            List<FieldData> fieldDatas = groupData.getFieldDatas(chain.getFieldName());
            if (fieldDatas != null) {
                Object[] objs = (Object[])this.objMapping.getBusinessObject(groupObject, chain.getProperties()[0]);
                int i;
                if (objs == null || objs.length == 0) {
                    i = fieldDatas.size();
                    objs = (Object[])ClassUtil.createArray(arrayType, i);

                    for(i = 0; i < objs.length; ++i) {
                        objs[i] = this.objMapping.newInstance(arrayType);
                    }

                    this.objMapping.setObjectValue(groupObject, chain.getProperties()[0], objs);
                }

                for(i = 0; i < objs.length; ++i) {
                    Object obj = objs[i];
                    FieldData fieldData = (FieldData)fieldDatas.get(i);
                    this.objMapping.setSimpleTypeValue(obj, chain.getProperties()[1], fieldData.getInputData());
                }

            }
        }
    }

    private void setNestedProperty(Object groupObject, PropertyChain chain, PacketGroupData groupData) {
        Class<?> propertyClass = this.objMapping.getPropertyClass(groupObject, chain.getProperties()[0]);
        this.validateBusinessClass(chain.getFieldName(), chain.getProperties()[0], propertyClass);
        if (ClassUtil.isArray(propertyClass)) {
            this.setArrayNestedProperty(groupObject, chain, groupData, propertyClass);
        } else {
            Object businessObj = this.objMapping.getBusinessObject(groupObject, chain.getProperties()[0]);
            if (businessObj == null) {
                businessObj = this.objMapping.newInstance(propertyClass);
                this.objMapping.setObjectValue(groupObject, chain.getProperties()[0], businessObj);
            }

            this.setBaseProperty(businessObj, chain.getFieldName(), chain.getProperties()[1], groupData);
        }

    }

    public Map<String, IValueSource> toFieldValues(PacketGroup packetGroup, Object packetGroupObj) throws SmsObjectMappingException {
        Map<String, IValueSource> valueSourceMapping = new HashMap();
        List<PropertyChain> chains = this.getPropertyChains(packetGroup);

        for(int i = 0; i < chains.size(); ++i) {
            PropertyChain chain = (PropertyChain)chains.get(i);
            String[] properties = chain.getProperties();
            if (properties.length == 1) {
                List<String> values = this.objMapping.getSimpleTypeValues(packetGroupObj, properties[0]);
                this.putValueSource(valueSourceMapping, chain.getFieldName(), values);
            } else if (properties.length == 2) {
                Object businessObj = this.objMapping.getBusinessObject(packetGroupObj, properties[0]);
                if (businessObj != null) {
                    this.validateBusinessClass(chain.getFieldName(), chain.getProperties()[0], businessObj.getClass());
                    if (ClassUtil.isArray(businessObj.getClass())) {
                        this.putValueSource(valueSourceMapping, (Object[])businessObj, properties[1], chain.getFieldName());
                    } else {
                        List<String> values = this.objMapping.getSimpleTypeValues(businessObj, properties[1]);
                        this.putValueSource(valueSourceMapping, chain.getFieldName(), values);
                    }
                }
            }
        }

        return valueSourceMapping;
    }

    private void putValueSource(Map<String, IValueSource> valueSourceMapping, Object[] businessObjs, String property, String fieldName) {
        for(int i = 0; i < businessObjs.length; ++i) {
            Object obj = businessObjs[i];
            if (obj != null) {
                List<String> values = this.objMapping.getSimpleTypeValues(obj, property);
                this.putValueSource(valueSourceMapping, fieldName, values);
            }
        }

    }

    private void putValueSource(Map<String, IValueSource> valueSourceMapping, String fieldName, List<String> values) {
        if (values != null) {
            AssignedValueSource vs = (AssignedValueSource)valueSourceMapping.get(fieldName);
            if (vs == null) {
                vs = new AssignedValueSource();
                valueSourceMapping.put(fieldName, vs);
            }

            vs.addValues(values);
        }
    }

    private void validateBusinessClass(String fieldName, String property, Class<?> businessClass) {
        if (this.objMapping.isSimpleType(businessClass)) {
            throw new SmsObjectMappingException("字段[" + fieldName + "]的属性[" + property + "]配置错误：把业务对象配置成简单类型!");
        } else if (Collection.class.isAssignableFrom(businessClass)) {
            throw new SmsObjectMappingException("字段[" + fieldName + "]的属性[" + property + "]配置错误：不支持业务对象的集合!");
        } else if (Map.class.isAssignableFrom(businessClass)) {
            throw new SmsObjectMappingException("字段[" + fieldName + "]的属性[" + property + "]配置错误：不支持Map!");
        }
    }

    public List<IObjectPropertyExchanger<Object>> getObjectPropertyExchangers() {
        return this.objectPropertyExchangers;
    }

    public void setObjectPropertyExchangers(List<IObjectPropertyExchanger<Object>> objectPropertyExchangers) {
        this.objectPropertyExchangers = objectPropertyExchangers;
        this.objMapping.setObjectExchangerMapping(objectPropertyExchangers);
    }
}
