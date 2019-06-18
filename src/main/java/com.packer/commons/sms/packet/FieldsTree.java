package com.packer.commons.sms.packet;


import com.packer.commons.sms.base.AbstractSelfObject;
import com.packer.commons.sms.base.ISelfDescriptive;
import com.packer.commons.sms.util.Assert;
import com.packer.commons.sms.util.CollectionUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FieldsTree extends AbstractSelfObject implements ISelfDescriptive {
    private static final long serialVersionUID = 1L;
    private List<IField> rootFields = new ArrayList();
    private Map<String, IField> allFieldsCache = new LinkedHashMap();

    public FieldsTree(String name, String description) {
        super(name, description);
    }

    private void clearNameFromCache(IField field) {
        this.allFieldsCache.remove(field.getName());
        if (field instanceof FieldGroup) {
            FieldGroup fieldGroup = (FieldGroup)field;
            List<IField> fields = fieldGroup.getFields();

            for(int i = 0; i < fields.size(); ++i) {
                IField childField = (IField)fields.get(i);
                this.clearNameFromCache(childField);
            }
        }

    }

    private void addNameToCache(IField field) {
        if (this.allFieldsCache.containsKey(field.getName())) {
            throw new PacketConfigException("The name of field [" + field.getName() + "] is existed!");
        } else {
            this.allFieldsCache.put(field.getName(), field);
            if (field instanceof FieldGroup) {
                FieldGroup fieldGroup = (FieldGroup)field;
                List<IField> fields = fieldGroup.getFields();

                for(int i = 0; i < fields.size(); ++i) {
                    IField childField = (IField)fields.get(i);
                    this.addNameToCache(childField);
                }

            }
        }
    }

    private FieldGroup getParentFieldGroup(String fieldGroupName) {
        IField originalField = (IField)this.allFieldsCache.get(fieldGroupName);
        if (originalField == null) {
            throw new PacketConfigException("Can't find the field group through the name [" + fieldGroupName + "]!");
        } else if (!(originalField instanceof FieldGroup)) {
            throw new PacketConfigException("The field group [name=" + fieldGroupName + "] is invalid!");
        } else {
            return (FieldGroup)originalField;
        }
    }

    public boolean addField(IField field) {
        if (field == null) {
            return false;
        } else if (this.contains(field.getName())) {
            return false;
        } else {
            if (field.hasParentField()) {
                FieldGroup group = this.getParentFieldGroup(field.getParentFieldName());
                group.addField(field);
            } else {
                this.rootFields.add(field);
            }

            this.addNameToCache(field);
            return true;
        }
    }

    public boolean addField(int index, IField field) {
        if (field == null) {
            return false;
        } else if (this.contains(field.getName())) {
            return false;
        } else {
            if (field.hasParentField()) {
                FieldGroup group = this.getParentFieldGroup(field.getParentFieldName());
                group.addField(index, field);
            } else {
                this.rootFields.add(index, field);
            }

            this.addNameToCache(field);
            return true;
        }
    }

    public boolean updateField(String originalFieldName, IField updatedField) {
        IField originalField = (IField)this.allFieldsCache.get(originalFieldName);
        if (originalField == null) {
            return false;
        } else {
            this.clearNameFromCache(originalField);
            if (originalField.hasParentField()) {
                String parentFieldName = originalField.getParentFieldName();
                FieldGroup parentGroup = (FieldGroup)this.allFieldsCache.get(parentFieldName);
                parentGroup.updateField(originalFieldName, updatedField);
            } else {
                int index = this.rootFields.indexOf(originalField);
                this.rootFields.remove(index);
                this.rootFields.add(index, updatedField);
            }

            this.addNameToCache(updatedField);
            if (updatedField instanceof FieldGroup) {
                FieldGroup group = (FieldGroup)updatedField;
                group.synchronizeName();
            }

            return true;
        }
    }

    public boolean removeField(String fieldName) {
        IField field = (IField)this.allFieldsCache.get(fieldName);
        if (field == null) {
            return false;
        } else {
            this.clearNameFromCache(field);
            if (field.hasParentField()) {
                String parentFieldName = field.getParentFieldName();
                FieldGroup parentGroup = (FieldGroup)this.allFieldsCache.get(parentFieldName);
                parentGroup.removeIField(field);
            } else {
                this.rootFields.remove(field);
            }

            return true;
        }
    }

    public IField getField(String fieldName) {
        return (IField)this.allFieldsCache.get(fieldName);
    }

    public boolean contains(String fieldName) {
        return this.allFieldsCache.containsKey(fieldName);
    }

    public List<FieldGroup> getParentFieldGroups(IField field) {
        List<FieldGroup> parentFieldGroups = new ArrayList();

        FieldGroup parentFieldGroup;
        for(String parentFieldGroupName = field.getParentFieldName(); !Assert.isEmpty(parentFieldGroupName); parentFieldGroupName = parentFieldGroup.getParentFieldName()) {
            parentFieldGroup = (FieldGroup)this.getField(parentFieldGroupName);
            parentFieldGroups.add(0, parentFieldGroup);
        }

        return parentFieldGroups;
    }

    public void moveField(String fieldName, int step) {
        IField field = this.getField(fieldName);
        if (field != null) {
            CollectionUtil.moveList(this.rootFields, field, step);
        }
    }

    public List<IField> getRootFields() {
        return this.rootFields;
    }

    public List<IField> getAllFields() {
        return new ArrayList(this.allFieldsCache.values());
    }

    public void setRootFields(List<IField> fields) {
        this.rootFields.clear();
        this.allFieldsCache.clear();

        for(int i = 0; i < fields.size(); ++i) {
            this.addField((IField)fields.get(i));
        }

    }

    public String print(int indent) {
        StringBuilder sb = new StringBuilder();
        Iterator var4 = this.rootFields.iterator();

        while(var4.hasNext()) {
            IField f = (IField)var4.next();
            sb.append(f.print(indent));
        }

        return sb.toString();
    }
}
