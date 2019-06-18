package nu.xom;

import java.util.ArrayList;
import java.util.HashMap;

class Namespaces {
    private HashMap namespaces = new HashMap(1);
    private ArrayList prefixes = new ArrayList(1);

    Namespaces() {
    }

    void put(String var1, String var2) {
        this.namespaces.put(var1, var2);
        this.prefixes.remove(var1);
        this.prefixes.add(var1);
    }

    void remove(String var1) {
        if (var1 == null) {
            var1 = "";
        }

        this.namespaces.remove(var1);
        this.prefixes.remove(var1);
    }

    String getURI(String var1) {
        return (String)((String)this.namespaces.get(var1));
    }

    ArrayList getPrefixes() {
        return this.prefixes;
    }

    nu.xom.Namespaces copy() {
        nu.xom.Namespaces var1 = new nu.xom.Namespaces();
        var1.namespaces = (HashMap)this.namespaces.clone();
        var1.prefixes = (ArrayList)this.prefixes.clone();
        return var1;
    }

    int size() {
        return this.prefixes.size();
    }

    String getPrefix(int var1) {
        return (String)this.prefixes.get(var1);
    }
}
