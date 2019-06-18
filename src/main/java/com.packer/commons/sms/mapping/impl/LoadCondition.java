package com.packer.commons.sms.mapping.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LoadCondition {
    Map<StructType, List<ExtentionType>> unloadlist = new HashMap();

    public LoadCondition() {
        this.unloadlist.put(StructType.MO, new ArrayList());
        this.unloadlist.put(StructType.MT, new ArrayList());
    }

    public boolean canLoad(StructType st, ExtentionType et) {
        return !((List)this.unloadlist.get(st)).contains(et);
    }

    public void addUnload(StructType st, ExtentionType et) {
        ((List)this.unloadlist.get(st)).add(et);
    }

    public void setUnload(Map<String, List<String>> map) {
        Iterator var3 = map.keySet().iterator();

        while(var3.hasNext()) {
            String k = (String)var3.next();
            Iterator var5 = ((List)map.get(k)).iterator();

            while(var5.hasNext()) {
                String v = (String)var5.next();
                this.addUnload(StructType.valueOf(k), ExtentionType.valueOf(v));
            }
        }

    }
}
