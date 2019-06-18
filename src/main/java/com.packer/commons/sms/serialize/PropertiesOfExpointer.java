package com.packer.commons.sms.serialize;


import com.packer.commons.sms.base.Property;
import freemarker.ext.beans.BeanModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PropertiesOfExpointer implements TemplateMethodModelEx {
    public PropertiesOfExpointer() {
    }

    public Object exec(List arg0) throws TemplateModelException {
        ArrayList<String> list = new ArrayList();
        Iterator var4 = arg0.iterator();

        while(var4.hasNext()) {
            Object l = var4.next();
            BeanModel tm = (BeanModel)l;
            Object o = tm.getWrappedObject();
            Method[] methods = o.getClass().getMethods();
            Method[] var11 = methods;
            int var10 = methods.length;

            for(int var9 = 0; var9 < var10; ++var9) {
                Method m = var11[var9];
                if (m.getName().startsWith("get") && m.getReturnType() == Property.class) {
                    String fieldname = m.getName().replaceFirst("get", "");
                    fieldname = fieldname.substring(0, 1).toLowerCase() + fieldname.substring(1, fieldname.length());

                    try {
                        Property p = (Property)m.invoke(o);
                        if (p.getValue() != null) {
                            list.add(fieldname + "=" + p.getValue().toString());
                        }
                    } catch (Exception var14) {
                        var14.printStackTrace();
                    }
                }
            }
        }

        return list;
    }
}
