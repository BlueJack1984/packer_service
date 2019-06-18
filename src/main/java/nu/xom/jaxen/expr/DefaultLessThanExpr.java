package nu.xom.jaxen.expr;


class DefaultLessThanExpr extends DefaultRelationalExpr {
    private static final long serialVersionUID = 8423816025305001283L;

    DefaultLessThanExpr(Expr var1, Expr var2) {
        super(var1, var2);
    }

    public String getOperator() {
        return "<";
    }

    protected boolean evaluateDoubleDouble(Double var1, Double var2) {
        return var1.compareTo(var2) < 0;
    }
}

