package com.packer.commons.sms.mapping.impl;


import com.packer.commons.sms.base.AbstractSelfObject;
import com.packer.commons.sms.base.Property;
import com.packer.commons.sms.packet.AutoCascadeException;
import com.packer.commons.sms.packet.ConverterException;
import com.packer.commons.sms.packet.CountLimitException;
import com.packer.commons.sms.packet.IAutoCascadeManager;
import com.packer.commons.sms.packet.ICountLimit;
import com.packer.commons.sms.packet.IValueConverter;
import com.packer.commons.sms.packet.IValueSource;
import com.packer.commons.sms.packet.IValueType;
import com.packer.commons.sms.packet.IValueValidator;
import com.packer.commons.sms.packet.Packet;
import com.packer.commons.sms.packet.PacketData;
import com.packer.commons.sms.packet.PacketGroupData;
import com.packer.commons.sms.packet.ValueSourceException;
import com.packer.commons.sms.packet.ValueTypeException;
import com.packer.commons.sms.packet.ValueValidatorException;
import java.util.List;
import java.util.Map;

public class ErrorDelegateExtendPointer extends AbstractSelfObject implements IValueSource, IValueConverter, IValueType, IValueValidator, ICountLimit, IAutoCascadeManager {
    private static final long serialVersionUID = 5439748120920936737L;
    private static final String DESCRIPTION = "扩展点加载错误";
    private Property errorReasonProperty = new Property("错误原因", "", "");
    private Throwable ex;
    private String extendPointerClass;
    private Map<String, String> extendPointerProperties;

    public ErrorDelegateExtendPointer() {
        super("扩展点加载错误", "");
    }

    public void setOriginalExtendPointer(String extendPointerClass, Map<String, String> extendPointerProperties) {
        this.extendPointerClass = extendPointerClass;
        this.extendPointerProperties = extendPointerProperties;
    }

    public String getExtendPointerClass() {
        return this.extendPointerClass;
    }

    public Map<String, String> getExtendPointerProperties() {
        return this.extendPointerProperties;
    }

    public void setError(Throwable ex) {
        this.ex = ex;
        this.errorReasonProperty.setValue(ex.getMessage());
    }

    public Throwable getError() {
        return this.ex;
    }

    public Property getErrorReasonProperty() {
        return this.errorReasonProperty;
    }

    public void validate(String data) throws ValueValidatorException {
        throw new ValueValidatorException("扩展点加载错误", this.ex);
    }

    public List<String> getValues() throws ValueSourceException {
        throw new ValueSourceException("扩展点加载错误", this.ex);
    }

    public String construct(String moFieldContent) throws ValueTypeException {
        throw new ValueTypeException("扩展点加载错误", this.ex);
    }

    public String[] parse(StringBuffer sms) throws ValueTypeException {
        throw new ValueTypeException("扩展点加载错误", this.ex);
    }

    public StringBuffer assemble(Packet packet, PacketGroupData packetGroupData, String[] businessSmses) throws AutoCascadeException {
        throw new AutoCascadeException("扩展点加载错误", this.ex);
    }

    public List<StringBuffer> split(Packet packet, PacketData packetData) throws AutoCascadeException {
        throw new AutoCascadeException("扩展点加载错误", this.ex);
    }

    public String convert(String value) throws ConverterException {
        throw new ConverterException("扩展点加载错误", this.ex);
    }

    public String reconvert(String value) throws ConverterException {
        throw new ConverterException("扩展点加载错误", this.ex);
    }

    public int getCount() throws CountLimitException {
        throw new CountLimitException("扩展点加载错误", this.ex);
    }

    public boolean isFixed() throws CountLimitException {
        throw new CountLimitException("扩展点加载错误", this.ex);
    }
}

