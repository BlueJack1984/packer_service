package com.packer.commons.sms.base;

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public abstract class AbstractSelfObject implements ISelfDescriptive {
    private static final long serialVersionUID = -3044480122547987789L;
    private String description;
    private String name;
    private Map<String, Object> parameters = new HashMap();
    protected transient Logger logger = LogManager.getLogger(this.getClass());

    protected AbstractSelfObject() {
    }

    protected AbstractSelfObject(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addParameter(String name, Object value) {
        this.parameters.put(name, value);
    }

    public Object getParameter(String name) {
        return this.parameters.get(name);
    }

    public List<String> getParameterNames() {
        return new ArrayList(this.parameters.keySet());
    }

    public Object clone() {
        Object another = null;

        try {
            another = super.clone();
        } catch (CloneNotSupportedException var3) {
            this.logger.error("Exception when clone the Object[" + this.getClass().getName() + "]!", var3);
        }

        return another;
    }

    public Object readResolve() throws ObjectStreamException {
        this.logger = LogManager.getLogger(this.getClass());
        return this;
    }

    public String toString() {
        return this.getName();
    }
}

