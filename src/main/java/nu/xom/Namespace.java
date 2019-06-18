package nu.xom;

public final class Namespace extends Node {
    private final String prefix;
    private final String uri;
    public static final String XML_NAMESPACE = "http://www.w3.org/XML/1998/namespace";

    public Namespace(String var1, String var2, Element var3) {
        if (var1 == null) {
            var1 = "";
        } else {
            if ("xmlns".equals(var1)) {
                throw new IllegalNameException("The xmlns prefix may not be bound to a URI.");
            }

            if ("xml".equals(var1) && !"http://www.w3.org/XML/1998/namespace".equals(var2)) {
                throw new NamespaceConflictException("The prefix xml can only be bound to the URI http://www.w3.org/XML/1998/namespace");
            }
        }

        if (var1.length() != 0) {
            Verifier.checkNCName(var1);
        }

        if (var2 == null) {
            var2 = "";
        } else if (var2.equals("http://www.w3.org/XML/1998/namespace") && !"xml".equals(var1)) {
            throw new NamespaceConflictException("The URI http://www.w3.org/XML/1998/namespace can only be bound to the prefix xml");
        }

        if (var2.length() == 0) {
            if (var1.length() != 0) {
                throw new NamespaceConflictException("Prefixed elements must have namespace URIs.");
            }
        } else {
            Verifier.checkAbsoluteURIReference(var2);
        }

        this.prefix = var1;
        this.uri = var2;
        super.setParent(var3);
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getValue() {
        return this.uri;
    }

    public Node getChild(int var1) {
        throw new IndexOutOfBoundsException("Namespaces do not have children");
    }

    public int getChildCount() {
        return 0;
    }

    public Node copy() {
        return new nu.xom.Namespace(this.prefix, this.uri, (Element)null);
    }

    public void detach() {
        super.setParent((ParentNode)null);
    }

    public String toXML() {
        String var1 = this.prefix.equals("") ? "" : ":";
        return "xmlns" + var1 + this.prefix + "=\"" + this.uri + "\"";
    }

    public String toString() {
        return "[Namespace: " + this.toXML() + "]";
    }
}
