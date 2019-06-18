package com.packer.commons.sms.packet;


import com.packer.commons.sms.SmsException;
import com.packer.commons.sms.util.Assert;
import com.packer.commons.sms.util.CollectionUtil;
import com.packer.commons.sms.util.StringUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class FieldGroup extends AbstractField {
    private static final long serialVersionUID = -4011743094420018267L;
    private LinkedHashMap<String, IField> fieldsMapping = new LinkedHashMap();
    private String businessObjClass;

    public FieldGroup() {
    }

    public FieldGroup(String parentFieldName, String name, String description) {
        super(parentFieldName, name, description);
    }

    public boolean containChildField(String fieldName) {
        return this.fieldsMapping.containsKey(fieldName);
    }

    public void addField(IField field) {
        this.addField(this.fieldsMapping.size(), field);
    }

    public void addField(int index, IField field) {
        if (this.containChildField(field.getName())) {
            throw new PacketConfigException("The name of field [" + field.getName() + "]is existed in the field group[" + this.getName() + "]!");
        } else {
            int size = this.fieldsMapping.size();
            if (index > size) {
                index = size;
            }

            if (index < 0) {
                index = 0;
            }

            this.fieldsMapping.put(field.getName(), field);
            int step = size - index;
            CollectionUtil.moveLinkedHashMap(this.fieldsMapping, field.getName(), -step);
        }
    }

    public void updateField(String originalFieldName, IField updatedField) {
        if (!originalFieldName.equals(updatedField.getName()) && this.containChildField(updatedField.getName())) {
            throw new PacketConfigException("The name of field [" + updatedField.getName() + "] is existed in the field group[" + this.getName() + "]!");
        } else {
            CollectionUtil.updateLinkedHashMap(this.fieldsMapping, originalFieldName, updatedField.getName(), updatedField);
            if (updatedField instanceof FieldGroup) {
                FieldGroup group = (FieldGroup)updatedField;
                group.synchronizeName();
            }

        }
    }

    void synchronizeName() {
        List<IField> fields = this.getFields();

        for(int i = 0; i < fields.size(); ++i) {
            IField field = (IField)fields.get(i);
            field.setParentFieldName(this.getName());
        }

    }

    public void removeField(String fieldName) {
        this.fieldsMapping.remove(fieldName);
    }

    public void removeIField(IField field) {
        this.removeField(field.getName());
    }

    public void move(String fieldName, int step) {
        CollectionUtil.moveLinkedHashMap(this.fieldsMapping, fieldName, step);
    }

    protected FieldData parseField(PacketData packetData, StringBuffer sms) throws SmsException {
        String[] contents = this.getValueType().parse(sms);
        String typeValue = contents[0];
        String convertedData = contents[1];
        String inputValue = this.reconvert(convertedData);
        FieldData fieldData = new FieldData(this.getName(), inputValue, convertedData, typeValue);
        packetData.addFieldData(fieldData);
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(this.constructParseLog(fieldData));
        }

        StringBuffer childSms = new StringBuffer(inputValue);
        List<IField> childFields = this.getFields();

        for(int j = 0; j < childFields.size(); ++j) {
            IField childField = (IField)childFields.get(j);
            childField.parse(childSms);
        }

        return fieldData;
    }

    public List<IField> getFields() {
        return new ArrayList(this.fieldsMapping.values());
    }

    public void setFields(List<IField> fields) {
        this.fieldsMapping.clear();

        for(int i = 0; i < fields.size(); ++i) {
            this.addField((IField)fields.get(i));
        }

    }

    public String getBusinessObjClass() {
        return this.businessObjClass;
    }

    public void setBusinessObjClass(String businessObjClass) {
        this.businessObjClass = businessObjClass;
    }

    public int calLoopCount(PacketData pd) {
        List<IField> fs = this.getFields();
        IField stdField = null;
        Iterator var5 = fs.iterator();

        while(var5.hasNext()) {
            IField f = (IField)var5.next();
            if (!f.getCountLimit().isFixed() && pd.getFieldDatas(f.getName()).size() > 0) {
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("find a child field[" + f.getName() + "]'s countlimit is x,when pack fieldgroup " + this.getName());
                }

                return 1;
            }

            if (stdField == null && f.getCountLimit().getCount() != 0 && pd.getFieldDatas(f.getName()).size() > 0) {
                stdField = f;
            }
        }

        if (stdField != null) {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("find a child field[" + stdField.getName() + "]'s countlimit is fixed and not zero,when pack fieldgroup " + this.getName());
            }

            int sizeOfValue = pd.getFieldDatas(stdField.getName()).size();
            int countOfField = stdField.getCountLimit().getCount();
            if (sizeOfValue % countOfField != 0) {
                throw new SmsException("the field " + stdField.getName() + " values count " + sizeOfValue + " should multiple by the countlimit " + countOfField);
            } else {
                return sizeOfValue / countOfField;
            }
        } else {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("this fieldgroup " + this.getName() + " all child fields have a counlimit 0");
            }

            return 0;
        }
    }

    public List<FieldData> pack() {
        try {
            List<FieldData> datas = new ArrayList();
            PacketGroupData pgd = DataManager.getPacketGroupData();
            if (pgd == null) {
                return datas;
            } else {
                PacketData pd = pgd.getCurrentPacketData();
                List<IField> fs = this.getFields();
                int count = this.calLoopCount(pd);
                if (count == 0) {
                    pd.addFieldData(this.getName(), new ArrayList());
                }

                for(int i = 0; i < count; ++i) {
                    StringBuilder sb = new StringBuilder();
                    Iterator var9 = fs.iterator();

                    while(var9.hasNext()) {
                        IField f = (IField)var9.next();
                        List<FieldData> fds = pd.getFieldDatas(f.getName());
                        if (f.getCountLimit().isFixed()) {
                            if (fds.size() < (i + 1) * f.getCountLimit().getCount()) {
                                SmsException smsException = new SmsException("Build the fieldgroup[" + this.getName() + "] need child filed [" + f.getName() + "] have at least " + (i + 1) * f.getCountLimit().getCount() + " values,but actual is " + fds.size());
                                smsException.setFieldName(this.getName());
                                throw smsException;
                            }

                            fds = fds.subList(i * f.getCountLimit().getCount(), (i + 1) * f.getCountLimit().getCount());
                        }

                        Iterator var12 = fds.iterator();

                        while(var12.hasNext()) {
                            FieldData fd = (FieldData)var12.next();
                            sb.append(fd.getTypedData());
                        }
                    }

                    this.logger.debug("[" + this.getName() + "] value:" + sb.toString());
                    String vc = this.convert(sb.toString());
                    this.logger.debug("[" + this.getName() + "] convert value:" + vc);
                    String vct = vc;
                    if (this.getValueType() != null) {
                        vct = this.getValueType().construct(vc);
                        this.logger.debug("[" + this.getName() + "] type value:" + vct);
                    }

                    pd.addFieldData(new FieldData(this.getName(), sb.toString(), vc, vct));
                }

                return pd.getFieldDatas(this.getName());
            }
        } catch (SmsException var13) {
            if (Assert.isEmpty(var13.getFieldName())) {
                var13.setFieldName(this.getName());
            }

            throw var13;
        } catch (Exception var14) {
            SmsException s = new SmsException(var14.getMessage(), var14);
            s.setFieldName(this.getName());
            throw s;
        }
    }

    public LinkedHashMap<String, IField> getFieldsMapping() {
        return this.fieldsMapping;
    }

    public String print(int indent) {
        StringBuilder root = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        sb.append("ClassProperty=" + this.getClassProperty()).append(" , Convertor=" + (this.getValueConverters() == null ? null : this.getValueConverters().size())).append(" , Type=" + (this.getValueType() == null ? null : this.getValueType().getName())).append(" , Validator=" + (this.getValueValidator() == null ? null : this.getValueValidator().getName())).append(" , CountLimit=" + (this.getCountLimit() == null ? null : this.getCountLimit())).append(" , Class=" + this.getBusinessObjClass());
        String s = StringUtil.repeat(indent, "-");
        s = s + "|" + StringUtil.repeat(3, "-");
        root.append(s + "Group:" + this.getName() + "[" + sb.toString() + "]\n");
        Iterator var6 = this.fieldsMapping.values().iterator();

        while(var6.hasNext()) {
            IField f = (IField)var6.next();
            root.append(f.print(indent + 4));
        }

        return root.toString();
    }
}
