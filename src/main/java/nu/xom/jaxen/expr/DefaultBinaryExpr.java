package nu.xom.jaxen.expr;


abstract class DefaultBinaryExpr extends DefaultExpr implements BinaryExpr {
    private Expr lhs;
    private Expr rhs;

    DefaultBinaryExpr(Expr var1, Expr var2) {
        this.lhs = var1;
        this.rhs = var2;
    }

    public Expr getLHS() {
        return this.lhs;
    }

    public Expr getRHS() {
        return this.rhs;
    }

    public void setLHS(Expr var1) {
        this.lhs = var1;
    }

    public void setRHS(Expr var1) {
        this.rhs = var1;
    }

    public abstract String getOperator();

    public String getText() {
        return "(" + this.getLHS().getText() + " " + this.getOperator() + " " + this.getRHS().getText() + ")";
    }

    public String toString() {
        return "[" + this.getClass().getName() + ": " + this.getLHS() + ", " + this.getRHS() + "]";
    }

    public Expr simplify() {
        this.setLHS(this.getLHS().simplify());
        this.setRHS(this.getRHS().simplify());
        return this;
    }
}
