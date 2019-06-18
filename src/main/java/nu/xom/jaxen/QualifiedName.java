package nu.xom.jaxen;


import java.io.Serializable;

class QualifiedName implements Serializable {
    private static final long serialVersionUID = 2734958615642751535L;
    private String namespaceURI;
    private String localName;

    QualifiedName(String var1, String var2) {
        if (var1 == null) {
            var1 = "";
        }

        this.namespaceURI = var1;
        this.localName = var2;
    }

    public int hashCode() {
        return this.localName.hashCode() ^ this.namespaceURI.hashCode();
    }

    public boolean equals(Object var1) {
        nu.xom.jaxen.QualifiedName var2 = (nu.xom.jaxen.QualifiedName)var1;
        return this.namespaceURI.equals(var2.namespaceURI) && var2.localName.equals(this.localName);
    }

    String getClarkForm() {
        return "".equals(this.namespaceURI) ? this.localName : "{" + this.namespaceURI + "}" + ":" + this.localName;
    }
}

