package nu.xom.jaxen.expr;

class DefaultGreaterThanExpr extends nu.xom.jaxen.expr.DefaultRelationalExpr {
    private static final long serialVersionUID = 6379252220540222867L;

    DefaultGreaterThanExpr(Expr var1, Expr var2) {
        super(var1, var2);
    }

    public String getOperator() {
        return ">";
    }

    protected boolean evaluateDoubleDouble(Double var1, Double var2) {
        return var1.compareTo(var2) > 0;
    }
}

