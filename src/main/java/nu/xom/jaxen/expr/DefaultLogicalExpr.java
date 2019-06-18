package nu.xom.jaxen.expr;

abstract class DefaultLogicalExpr extends DefaultTruthExpr implements LogicalExpr {
    DefaultLogicalExpr(Expr var1, Expr var2) {
        super(var1, var2);
    }
}
