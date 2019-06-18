package nu.xom.jaxen.expr;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.JaxenException;
import nu.xom.jaxen.function.NumberFunction;

class DefaultUnaryExpr extends DefaultExpr implements UnaryExpr {
    private static final long serialVersionUID = 2303714238683092334L;
    private Expr expr;

    DefaultUnaryExpr(Expr var1) {
        this.expr = var1;
    }

    public Expr getExpr() {
        return this.expr;
    }

    public String toString() {
        return "[(DefaultUnaryExpr): " + this.getExpr() + "]";
    }

    public String getText() {
        return "-(" + this.getExpr().getText() + ")";
    }

    public Expr simplify() {
        this.expr = this.expr.simplify();
        return this;
    }

    public Object evaluate(Context var1) throws JaxenException {
        Double var2 = NumberFunction.evaluate(this.getExpr().evaluate(var1), var1.getNavigator());
        return new Double(var2.doubleValue() * -1.0D);
    }
}

