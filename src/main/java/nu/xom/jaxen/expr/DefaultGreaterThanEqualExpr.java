package nu.xom.jaxen.expr;

class DefaultGreaterThanEqualExpr extends nu.xom.jaxen.expr.DefaultRelationalExpr {
    private static final long serialVersionUID = -7848747981787197470L;

    DefaultGreaterThanEqualExpr(Expr var1, Expr var2) {
        super(var1, var2);
    }

    public String getOperator() {
        return ">=";
    }

    protected boolean evaluateDoubleDouble(Double var1, Double var2) {
        return var1.compareTo(var2) >= 0;
    }
}
