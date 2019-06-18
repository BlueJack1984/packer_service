package nu.xom;


import nu.xom.jaxen.NamespaceContext;

import java.util.HashMap;
import java.util.Map;

public final class XPathContext {
    private Map namespaces;

    public XPathContext(String var1, String var2) {
        this();
        this.addNamespace(var1, var2);
    }

    public XPathContext() {
        this.namespaces = new HashMap();
        this.addNamespace("xml", "http://www.w3.org/XML/1998/namespace");
    }

    public void addNamespace(String var1, String var2) {
        if ("xml".equals(var1) && !"http://www.w3.org/XML/1998/namespace".equals(var2)) {
            throw new NamespaceConflictException("Wrong namespace URI for xml prefix: " + var2);
        } else {
            if ("".equals(var2)) {
                var2 = null;
            }

            if (var1 == null) {
                throw new NullPointerException("Prefixes used in XPath expressions cannot be null");
            } else if ("".equals(var1)) {
                throw new NamespaceConflictException("XPath expressions do not use the default namespace");
            } else {
                nu.xom.Verifier.checkNCName(var1);
                if (var2 == null) {
                    this.namespaces.remove(var1);
                } else {
                    this.namespaces.put(var1, var2);
                }

            }
        }
    }

    public static nu.xom.XPathContext makeNamespaceContext(Element var0) {
        nu.xom.XPathContext var1 = new nu.xom.XPathContext();
        var1.namespaces = var0.getNamespacePrefixesInScope();
        return var1;
    }

    NamespaceContext getJaxenContext() {
        return new nu.xom.XPathContext.JaxenNamespaceContext();
    }

    public String lookup(String var1) {
        return "".equals(var1) ? null : (String)this.namespaces.get(var1);
    }

    private class JaxenNamespaceContext implements NamespaceContext {
        private JaxenNamespaceContext() {
        }

        public String translateNamespacePrefixToUri(String var1) {
            return nu.xom.XPathContext.this.lookup(var1);
        }
    }
}
