package nu.xom.jaxen.expr;


import nu.xom.jaxen.Context;

class DefaultLiteralExpr extends DefaultExpr implements LiteralExpr {
    private static final long serialVersionUID = -953829179036273338L;
    private String literal;

    DefaultLiteralExpr(String var1) {
        this.literal = var1;
    }

    public String getLiteral() {
        return this.literal;
    }

    public String toString() {
        return "[(DefaultLiteralExpr): " + this.getLiteral() + "]";
    }

    public String getText() {
        return this.literal.indexOf(34) == -1 ? "\"" + this.getLiteral() + "\"" : "'" + this.getLiteral() + "'";
    }

    public Object evaluate(Context var1) {
        return this.getLiteral();
    }
}

