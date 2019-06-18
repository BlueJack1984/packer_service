package nu.xom.jaxen.expr;

import nu.xom.jaxen.Context;

class DefaultNumberExpr extends DefaultExpr implements NumberExpr {
    private static final long serialVersionUID = -6021898973386269611L;
    private Double number;

    DefaultNumberExpr(Double var1) {
        this.number = var1;
    }

    public Number getNumber() {
        return this.number;
    }

    public String toString() {
        return "[(DefaultNumberExpr): " + this.getNumber() + "]";
    }

    public String getText() {
        return this.getNumber().toString();
    }

    public Object evaluate(Context var1) {
        return this.getNumber();
    }
}