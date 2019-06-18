package nu.xom.jaxen;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class SimpleNamespaceContext implements NamespaceContext, Serializable {
    private static final long serialVersionUID = -808928409643497762L;
    private Map namespaces;

    public SimpleNamespaceContext() {
        this.namespaces = new HashMap();
    }

    public SimpleNamespaceContext(Map var1) {
        Iterator var2 = var1.entrySet().iterator();

        Entry var3;
        do {
            if (!var2.hasNext()) {
                this.namespaces = new HashMap(var1);
                return;
            }

            var3 = (Entry)var2.next();
        } while(var3.getKey() instanceof String && var3.getValue() instanceof String);

        throw new ClassCastException("Non-string namespace binding");
    }

    public void addElementNamespaces(Navigator var1, Object var2) throws UnsupportedAxisException {
        Iterator var3 = var1.getNamespaceAxisIterator(var2);

        while(var3.hasNext()) {
            Object var4 = var3.next();
            String var5 = var1.getNamespacePrefix(var4);
            String var6 = var1.getNamespaceStringValue(var4);
            if (this.translateNamespacePrefixToUri(var5) == null) {
                this.addNamespace(var5, var6);
            }
        }

    }

    public void addNamespace(String var1, String var2) {
        this.namespaces.put(var1, var2);
    }

    public String translateNamespacePrefixToUri(String var1) {
        return this.namespaces.containsKey(var1) ? (String)this.namespaces.get(var1) : null;
    }
}
