package nu.xom.jaxen.expr;

abstract class DefaultArithExpr extends nu.xom.jaxen.expr.DefaultBinaryExpr {
    DefaultArithExpr(Expr var1, Expr var2) {
        super(var1, var2);
    }

    public String toString() {
        return "[(DefaultArithExpr): " + this.getLHS() + ", " + this.getRHS() + "]";
    }
}
