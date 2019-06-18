package nu.xom.jaxen.expr;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.JaxenException;

class DefaultPredicate implements Predicate {
    private static final long serialVersionUID = -4140068594075364971L;
    private Expr expr;

    DefaultPredicate(Expr var1) {
        this.setExpr(var1);
    }

    public Expr getExpr() {
        return this.expr;
    }

    public void setExpr(Expr var1) {
        this.expr = var1;
    }

    public String getText() {
        return "[" + this.getExpr().getText() + "]";
    }

    public String toString() {
        return "[(DefaultPredicate): " + this.getExpr() + "]";
    }

    public void simplify() {
        this.setExpr(this.getExpr().simplify());
    }

    public Object evaluate(Context var1) throws JaxenException {
        return this.getExpr().evaluate(var1);
    }
}
