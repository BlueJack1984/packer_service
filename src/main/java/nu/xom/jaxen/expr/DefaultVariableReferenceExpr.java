package nu.xom.jaxen.expr;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.UnresolvableException;

class DefaultVariableReferenceExpr extends DefaultExpr implements VariableReferenceExpr {
    private static final long serialVersionUID = 8832095437149358674L;
    private String prefix;
    private String localName;

    DefaultVariableReferenceExpr(String var1, String var2) {
        this.prefix = var1;
        this.localName = var2;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getVariableName() {
        return this.localName;
    }

    public String toString() {
        return "[(DefaultVariableReferenceExpr): " + this.getQName() + "]";
    }

    private String getQName() {
        return "".equals(this.prefix) ? this.localName : this.prefix + ":" + this.localName;
    }

    public String getText() {
        return "$" + this.getQName();
    }

    public Object evaluate(Context var1) throws UnresolvableException {
        String var2 = this.getPrefix();
        String var3 = null;
        if (var2 != null && !"".equals(var2)) {
            var3 = var1.translateNamespacePrefixToUri(var2);
        }

        return var1.getVariableValue(var3, var2, this.localName);
    }
}

