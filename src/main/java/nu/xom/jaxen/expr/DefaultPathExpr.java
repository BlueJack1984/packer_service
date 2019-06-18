package nu.xom.jaxen.expr;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.JaxenException;

class DefaultPathExpr extends DefaultExpr implements PathExpr {
    private static final long serialVersionUID = -6593934674727004281L;
    private Expr filterExpr;
    private LocationPath locationPath;

    DefaultPathExpr(Expr var1, LocationPath var2) {
        this.filterExpr = var1;
        this.locationPath = var2;
    }

    public Expr getFilterExpr() {
        return this.filterExpr;
    }

    public void setFilterExpr(Expr var1) {
        this.filterExpr = var1;
    }

    public LocationPath getLocationPath() {
        return this.locationPath;
    }

    public String toString() {
        return this.getLocationPath() != null ? "[(DefaultPathExpr): " + this.getFilterExpr() + ", " + this.getLocationPath() + "]" : "[(DefaultPathExpr): " + this.getFilterExpr() + "]";
    }

    public String getText() {
        StringBuffer var1 = new StringBuffer();
        if (this.getFilterExpr() != null) {
            var1.append(this.getFilterExpr().getText());
        }

        if (this.getLocationPath() != null) {
            if (!this.getLocationPath().getSteps().isEmpty()) {
                var1.append("/");
            }

            var1.append(this.getLocationPath().getText());
        }

        return var1.toString();
    }

    public Expr simplify() {
        if (this.getFilterExpr() != null) {
            this.setFilterExpr(this.getFilterExpr().simplify());
        }

        if (this.getLocationPath() != null) {
            this.getLocationPath().simplify();
        }

        if (this.getFilterExpr() == null && this.getLocationPath() == null) {
            return null;
        } else if (this.getLocationPath() == null) {
            return this.getFilterExpr();
        } else {
            return (Expr)(this.getFilterExpr() == null ? this.getLocationPath() : this);
        }
    }

    public Object evaluate(Context var1) throws JaxenException {
        Object var2 = null;
        Context var3 = null;
        if (this.getFilterExpr() != null) {
            var2 = this.getFilterExpr().evaluate(var1);
            var3 = new Context(var1.getContextSupport());
            var3.setNodeSet(convertToList(var2));
        }

        return this.getLocationPath() != null ? this.getLocationPath().evaluate(var3) : var2;
    }
}

