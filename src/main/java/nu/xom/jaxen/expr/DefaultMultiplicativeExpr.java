package nu.xom.jaxen.expr;

abstract class DefaultMultiplicativeExpr extends nu.xom.jaxen.expr.DefaultArithExpr implements MultiplicativeExpr {
    DefaultMultiplicativeExpr(Expr var1, Expr var2) {
        super(var1, var2);
    }

    public String toString() {
        return "[(DefaultMultiplicativeExpr): " + this.getLHS() + ", " + this.getRHS() + "]";
    }
}
