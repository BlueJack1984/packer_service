package com.packer.commons.sms.packet;


import com.packer.commons.sms.SmsException;
import com.packer.commons.sms.util.Assert;
import com.packer.commons.sms.util.StringUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AtomicField extends AbstractField {
    private static final long serialVersionUID = 6604069728483292961L;
    private IValueSource valueSource;

    public AtomicField() {
    }

    public AtomicField(String parentFieldName, String name, String description) {
        super(parentFieldName, name, description);
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

        return fieldData;
    }

    public List<FieldData> pack() {
        try {
            List<FieldData> datas = new ArrayList();
            PacketGroupData pgd = DataManager.getPacketGroupData();
            if (pgd == null) {
                return datas;
            } else {
                PacketData pd = pgd.getCurrentPacketData();
                IValueSource vsource = DataManager.getFieldValueSource(this.getName()) != null ? DataManager.getFieldValueSource(this.getName()) : this.getValueSource();
                if (vsource != null) {
                    List<String> values = vsource.getValues();
                    if (this.getCountLimit().isFixed() && this.getCountLimit().getCount() > 0 && values.size() < this.getCountLimit().getCount()) {
                        SmsException smsException = new SmsException("Build the field[" + this.getName() + "] need have at least " + this.getCountLimit().getCount() + " values,but actual is " + values.size());
                        smsException.setFieldName(this.getName());
                        throw smsException;
                    }

                    if (values.size() != 0) {
                        Iterator var7 = values.iterator();

                        while(var7.hasNext()) {
                            String v = (String)var7.next();
                            String vc = this.convert(v);
                            String vct = this.getValueType() == null ? vc : this.getValueType().construct(vc);
                            this.logger.debug("[" + this.getName() + "] value=" + v + " ,converted=" + vc + " ,typeed=" + vct);
                            pd.addFieldData(new FieldData(this.getName(), v, vc, vct));
                        }

                        return pd.getFieldDatas(this.getName());
                    }
                } else if (this.getCountLimit().isFixed() && this.getCountLimit().getCount() > 0) {
                    SmsException smsException = new SmsException("Build the field[" + this.getName() + "] need have at least " + this.getCountLimit().getCount() + " values,but valuesource is null");
                    smsException.setFieldName(this.getName());
                    throw smsException;
                }

                pd.addFieldData(this.getName(), new ArrayList());
                return pd.getFieldDatas(this.getName());
            }
        } catch (SmsException var10) {
            if (Assert.isEmpty(var10.getFieldName())) {
                var10.setFieldName(this.getName());
            }

            throw var10;
        } catch (Exception var11) {
            SmsException s = new SmsException(var11.getMessage(), var11);
            s.setFieldName(this.getName());
            throw s;
        }
    }

    public IValueSource getValueSource() {
        return this.valueSource;
    }

    public void setValueSource(IValueSource valueSource) {
        this.valueSource = valueSource;
    }

    public String print(int indent) {
        StringBuilder root = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        sb.append("ClassProperty=" + this.getClassProperty()).append(" , ValueSource=" + (this.getValueSource() == null ? null : this.getValueSource().getName())).append(" , Convertor=" + (this.getValueConverters() == null ? null : this.getValueConverters().size())).append(" , Type=" + (this.getValueType() == null ? null : this.getValueType().getName())).append(" , Validator=" + (this.getValueValidator() == null ? null : this.getValueValidator().getName())).append(" , Countlimit=" + this.getCountLimit());
        String s = StringUtil.repeat(indent, "-");
        s = s + "|" + StringUtil.repeat(3, "-");
        root.append(s + "Field:" + this.getName() + "[" + sb.toString() + "]\n");
        return root.toString();
    }
}
