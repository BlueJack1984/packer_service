package nu.xom.jaxen.expr;


import nu.xom.jaxen.function.NumberFunction;

class DefaultEqualsExpr extends DefaultEqualityExpr {
    private static final long serialVersionUID = -8327599812627931648L;

    DefaultEqualsExpr(Expr var1, Expr var2) {
        super(var1, var2);
    }

    public String getOperator() {
        return "=";
    }

    public String toString() {
        return "[(DefaultEqualsExpr): " + this.getLHS() + ", " + this.getRHS() + "]";
    }

    protected boolean evaluateObjectObject(Object var1, Object var2) {
        return !this.eitherIsNumber(var1, var2) || !NumberFunction.isNaN((Double)var1) && !NumberFunction.isNaN((Double)var2) ? var1.equals(var2) : false;
    }
}

