package com.packer.commons.sms.serialize;


import com.packer.commons.sms.packet.AtomicField;
import com.packer.commons.sms.packet.FieldGroup;
import freemarker.ext.beans.BeanModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import java.util.Iterator;
import java.util.List;

public class TypeOfAtomFieldFunction implements TemplateMethodModelEx {
    public TypeOfAtomFieldFunction() {
    }

    public Object exec(List arg0) throws TemplateModelException {
        Iterator var3 = arg0.iterator();
        if (var3.hasNext()) {
            Object l = var3.next();
            BeanModel tm = (BeanModel)l;
            Object o = tm.getWrappedObject();
            if (o instanceof FieldGroup) {
                return false;
            } else if (o instanceof AtomicField) {
                return true;
            } else {
                throw new RuntimeException("the input object neither  an instance of FieldGroup nor an instance of AtomField");
            }
        } else {
            throw new RuntimeException("the parameter of the function maybe empty");
        }
    }
}
