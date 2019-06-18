package nu.xom.jaxen.expr;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.JaxenException;
import nu.xom.jaxen.Navigator;
import nu.xom.jaxen.function.BooleanFunction;

class DefaultAndExpr extends nu.xom.jaxen.expr.DefaultLogicalExpr {
    private static final long serialVersionUID = -5237984010263103742L;

    DefaultAndExpr(Expr var1, Expr var2) {
        super(var1, var2);
    }

    public String getOperator() {
        return "and";
    }

    public String toString() {
        return "[(DefaultAndExpr): " + this.getLHS() + ", " + this.getRHS() + "]";
    }

    public Object evaluate(Context var1) throws JaxenException {
        Navigator var2 = var1.getNavigator();
        Boolean var3 = BooleanFunction.evaluate(this.getLHS().evaluate(var1), var2);
        if (!var3) {
            return Boolean.FALSE;
        } else {
            Boolean var4 = BooleanFunction.evaluate(this.getRHS().evaluate(var1), var2);
            return !var4 ? Boolean.FALSE : Boolean.TRUE;
        }
    }
}

