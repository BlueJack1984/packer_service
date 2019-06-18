package nu.xom.jaxen.expr;


import nu.xom.jaxen.function.NumberFunction;

class DefaultNotEqualsExpr extends nu.xom.jaxen.expr.DefaultEqualityExpr {
    private static final long serialVersionUID = -8001267398136979152L;

    DefaultNotEqualsExpr(Expr var1, Expr var2) {
        super(var1, var2);
    }

    public String getOperator() {
        return "!=";
    }

    public String toString() {
        return "[(DefaultNotEqualsExpr): " + this.getLHS() + ", " + this.getRHS() + "]";
    }

    protected boolean evaluateObjectObject(Object var1, Object var2) {
        if (!this.eitherIsNumber(var1, var2) || !NumberFunction.isNaN((Double)var1) && !NumberFunction.isNaN((Double)var2)) {
            return !var1.equals(var2);
        } else {
            return true;
        }
    }
}

