package com.packer.commons.sms.packet;


import com.packer.commons.sms.SmsException;
import com.packer.commons.sms.base.AbstractSelfObject;
import com.packer.commons.sms.util.Assert;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class AbstractField extends AbstractSelfObject implements IField {
    private static final long serialVersionUID = -7672916468130093201L;
    private List<IValueConverter> valueConverters = new ArrayList();
    private IValueType valueType;
    private IValueValidator valueValidator;
    private ICountLimit countLimit;
    private String parentFieldName;
    private String classProperty;

    public AbstractField() {
    }

    public AbstractField(String parentFieldName) {
        this.parentFieldName = parentFieldName;
    }

    public AbstractField(String parentFieldName, String name, String description) {
        super(name, description);
        this.parentFieldName = parentFieldName;
    }

    protected String convert(String data) {
        if (this.valueConverters != null && this.valueConverters.size() != 0) {
            String inputData = data;

            for(int i = 0; i < this.valueConverters.size(); ++i) {
                IValueConverter converter = (IValueConverter)this.valueConverters.get(i);
                inputData = converter.convert(inputData);
            }

            return inputData;
        } else {
            return data;
        }
    }

    protected String reconvert(String data) {
        if (this.valueConverters != null && this.valueConverters.size() != 0) {
            String inputData = data;

            for(int i = this.valueConverters.size(); i > 0; --i) {
                IValueConverter converter = (IValueConverter)this.valueConverters.get(i - 1);
                inputData = converter.reconvert(inputData);
            }

            return inputData;
        } else {
            return data;
        }
    }

    protected abstract FieldData parseField(PacketData var1, StringBuffer var2) throws SmsException;

    private void appendParseLog(StringBuffer buffer, Map<String, Object> datas) {
        if (datas != null && datas.size() != 0) {
            Iterator it = datas.keySet().iterator();

            while(it.hasNext()) {
                String key = (String)it.next();
                Object value = datas.get(key);
                buffer.append("\n\t").append(key).append(" = ").append(value);
            }

        }
    }

    protected String constructParseLog(FieldData fieldData) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Parse Field[").append(fieldData.getFieldName()).append("]....");
        buffer.append("\n\tinputData = ").append(fieldData.getInputData());
        buffer.append("\n\tconvertedData = ").append(fieldData.getConvertedData());
        buffer.append("\n\ttypedData = ").append(fieldData.getTypedData());
        this.appendParseLog(buffer, DataManager.getGlobalDatas());
        this.appendParseLog(buffer, DataManager.getParameterDatas());
        return buffer.toString();
    }

    public List<FieldData> parse(StringBuffer sms) throws SmsException {
        PacketData packetData = DataManager.getCurrentPacketData();
        if (packetData == null) {
            throw new ThreadDataException("Can't find the packetData in current thread!");
        } else {
            try {
                List<FieldData> result = new ArrayList();
                ICountLimit limit = this.getCountLimit();
                if (limit.isFixed()) {
                    int count = limit.getCount();

                    for(int i = 0; i < count; ++i) {
                        FieldData fieldData = this.parseField(packetData, sms);
                        result.add(fieldData);
                    }
                } else {
                    while(sms.length() > 0) {
                        FieldData fieldData = this.parseField(packetData, sms);
                        result.add(fieldData);
                    }
                }

                return result;
            } catch (SmsException var8) {
                if (Assert.isEmpty(var8.getFieldName())) {
                    var8.setFieldName(this.getName());
                }

                throw var8;
            } catch (StringIndexOutOfBoundsException var9) {
                SmsFormatInvalidException smsEx = new SmsFormatInvalidException("Exception when substring the sms[" + sms.toString() + "]!", var9);
                smsEx.setFieldName(this.getName());
                throw smsEx;
            } catch (Exception var10) {
                SmsException smsEx = new SmsException(var10);
                smsEx.setFieldName(this.getName());
                throw smsEx;
            }
        }
    }

    public List<IValueConverter> getValueConverters() {
        return this.valueConverters;
    }

    public void setValueConverters(List<IValueConverter> valueConverters) {
        this.valueConverters = valueConverters;
    }

    public IValueType getValueType() {
        return this.valueType;
    }

    public void setValueType(IValueType valueType) {
        this.valueType = valueType;
    }

    public IValueValidator getValueValidator() {
        return this.valueValidator;
    }

    public void setValueValidator(IValueValidator valueValidator) {
        this.valueValidator = valueValidator;
    }

    public ICountLimit getCountLimit() {
        return this.countLimit;
    }

    public void setCountLimit(ICountLimit countLimit) {
        this.countLimit = countLimit;
    }

    public String getParentFieldName() {
        return this.parentFieldName;
    }

    public boolean hasParentField() {
        return !Assert.isEmpty(this.parentFieldName);
    }

    public String getClassProperty() {
        return this.classProperty;
    }

    public void setClassProperty(String classProperty) {
        this.classProperty = classProperty;
    }

    public void setParentFieldName(String parentFieldName) {
        this.parentFieldName = parentFieldName;
    }
}
