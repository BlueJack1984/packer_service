package nu.xom.jaxen.expr;

abstract class DefaultAdditiveExpr extends nu.xom.jaxen.expr.DefaultArithExpr implements AdditiveExpr {
    DefaultAdditiveExpr(Expr var1, Expr var2) {
        super(var1, var2);
    }

    public String toString() {
        return "[(" + this.getClass().getName() + "): " + this.getLHS() + ", " + this.getRHS() + "]";
    }
}