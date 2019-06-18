package com.packer.commons.sms.packet;

import com.packer.commons.sms.base.AbstractSelfObject;
import com.packer.commons.sms.base.ISelfDescriptive;
import com.packer.commons.sms.util.StringUtil;
import java.util.List;

public class Packet extends AbstractSelfObject implements ISelfDescriptive, Cloneable {
    private static final long serialVersionUID = 1L;
    private FieldsTree controlFieldsTree = new FieldsTree("字段结构", "");
    private String command;
    private FieldsTree businessFieldsTree = new FieldsTree("业务字段列表", "");

    public Packet() {
    }

    public Packet(String name, String description) {
        super(name, description);
    }

    public Packet(String name, String description, String command) {
        super(name, description);
        this.command = command;
    }

    public boolean containsField(String fieldName) {
        return this.containsBusinessField(fieldName) ? true : this.controlFieldsTree.contains(fieldName);
    }

    public boolean containsBusinessField(String fieldName) {
        return this.businessFieldsTree.contains(fieldName);
    }

    public void addBusinessField(IField field) throws PacketConfigException {
        this.validateFieldName(field.getName());
        this.businessFieldsTree.addField(field);
    }

    public void addBusinessField(int index, IField field) throws PacketConfigException {
        this.validateFieldName(field.getName());
        this.businessFieldsTree.addField(index, field);
    }

    public void updateBusinessField(String originalFieldName, IField updatedField) {
        if (!originalFieldName.equals(updatedField.getName())) {
            this.validateFieldName(updatedField.getName());
        }

        this.businessFieldsTree.updateField(originalFieldName, updatedField);
    }

    public boolean removeBusinessField(String fieldName) {
        return this.businessFieldsTree.removeField(fieldName);
    }

    public IField getBusinessField(String fieldName) {
        return this.businessFieldsTree.getField(fieldName);
    }

    public void moveBusinessFiled(String fieldName, int step) {
        this.businessFieldsTree.moveField(fieldName, step);
    }

    public String getCommand() {
        return this.command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<IField> getBusinessFields() {
        return this.businessFieldsTree.getRootFields();
    }

    public List<IField> getAllBusinessFields() {
        return this.businessFieldsTree.getAllFields();
    }

    public void setBusinessFields(List<IField> controlFields) {
        this.businessFieldsTree.setRootFields(controlFields);
    }

    public IField getControlField(String fieldName) {
        return this.controlFieldsTree.getField(fieldName);
    }

    public List<IField> getControlFields() {
        return this.controlFieldsTree.getRootFields();
    }

    public List<IField> getAllControlFields() {
        return this.controlFieldsTree.getAllFields();
    }

    public FieldsTree getControlFieldsTree() {
        return this.controlFieldsTree;
    }

    public void setControlFieldsTree(FieldsTree controlFieldsTree) {
        this.controlFieldsTree = controlFieldsTree;
    }

    public FieldsTree getBusinessFieldsTree() {
        return this.businessFieldsTree;
    }

    public void setBusinessFieldsTree(FieldsTree businessFieldsTree) {
        this.businessFieldsTree = businessFieldsTree;
    }

    private void validateFieldName(String fieldName) {
        if (this.businessFieldsTree.contains(fieldName)) {
            throw new PacketConfigException("The field [name=" + fieldName + "] is existed in the packet[name=" + this.getName() + "]!");
        }
    }

    public String print(int indent) {
        StringBuilder sb = new StringBuilder();
        String s = StringUtil.repeat(indent, "-");
        s = s + "|" + StringUtil.repeat(3, "-");
        sb.append(s + "Packet:" + this.getName() + "[Cmd:" + this.getCommand() + "]\n");
        sb.append(this.businessFieldsTree.print(indent + 4));
        return sb.toString();
    }
}
