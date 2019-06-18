package nu.xom;


import nu.xom.jaxen.NamespaceContext;

import java.util.List;

//import com.sun.org.apache.xpath.internal.XPathContext;

public abstract class Node {
    private ParentNode parent = null;
    private static NamespaceContext emptyContext = new nu.xom.Node.EmptyNamespaceContext();

    Node() {
    }

    public abstract String getValue();

    public final Document getDocument() {
        Object var1;
        for(var1 = this; var1 != null && !((nu.xom.Node)var1).isDocument(); var1 = ((nu.xom.Node)var1).getParent()) {
        }

        return (Document)var1;
    }

    final nu.xom.Node getRoot() {
        ParentNode var1 = this.getParent();
        if (var1 == null) {
            return this;
        } else {
            while(var1.getParent() != null) {
                var1 = var1.getParent();
            }

            return var1;
        }
    }

    public String getBaseURI() {
        return this.parent == null ? "" : this.parent.getBaseURI();
    }

    public final ParentNode getParent() {
        return this.parent;
    }

    final void setParent(ParentNode var1) {
        this.parent = var1;
    }

    public void detach() {
        if (this.parent != null) {
            if (this.isAttribute()) {
                Element var1 = (Element)this.parent;
                var1.removeAttribute((Attribute)this);
            } else {
                this.parent.removeChild(this);
            }

        }
    }

    public abstract nu.xom.Node getChild(int var1);

    public abstract int getChildCount();

    public abstract nu.xom.Node copy();

    public abstract String toXML();

    public final boolean equals(Object var1) {
        return this == var1;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public final Nodes query(String var1, XPathContext var2) {
        if (this.isDocType()) {
            throw new XPathException("Can't use XPath on a DocType");
        } else {
            nu.xom.DocumentFragment var3 = null;
            nu.xom.Node var4 = this.getRoot();
            if (!var4.isDocument()) {
                var3 = new nu.xom.DocumentFragment();
                var3.appendChild(var4);
            }

            Nodes var7;
            try {
                JaxenConnector var5 = new JaxenConnector(var1);
                if (var2 == null) {
                    var5.setNamespaceContext(emptyContext);
                } else {
                    var5.setNamespaceContext(var2.getJaxenContext());
                }

                List var15 = var5.selectNodes(this);
                var7 = new Nodes(var15);
            } catch (XPathException var12) {
                var12.setXPath(var1);
                throw var12;
            } catch (Exception var13) {
                XPathException var6 = new XPathException("XPath error: " + var13.getMessage(), var13);
                var6.setXPath(var1);
                throw var6;
            } finally {
                if (var3 != null) {
                    var3.removeChild(0);
                }

            }

            return var7;
        }
    }

    public final Nodes query(String var1) {
        return this.query(var1, (XPathContext)null);
    }

    boolean isElement() {
        return false;
    }

    boolean isText() {
        return false;
    }

    boolean isComment() {
        return false;
    }

    boolean isProcessingInstruction() {
        return false;
    }

    boolean isAttribute() {
        return false;
    }

    boolean isDocument() {
        return false;
    }

    boolean isDocType() {
        return false;
    }

    boolean isDocumentFragment() {
        return false;
    }

    private static class EmptyNamespaceContext implements NamespaceContext {
        private EmptyNamespaceContext() {
        }

        public String translateNamespacePrefixToUri(String var1) {
            return null;
        }
    }
}
