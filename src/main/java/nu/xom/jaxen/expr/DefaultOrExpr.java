package nu.xom.jaxen.expr;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.JaxenException;
import nu.xom.jaxen.Navigator;
import nu.xom.jaxen.function.BooleanFunction;

class DefaultOrExpr extends nu.xom.jaxen.expr.DefaultLogicalExpr {
    private static final long serialVersionUID = 4894552680753026730L;

    DefaultOrExpr(Expr var1, Expr var2) {
        super(var1, var2);
    }

    public String getOperator() {
        return "or";
    }

    public String toString() {
        return "[(DefaultOrExpr): " + this.getLHS() + ", " + this.getRHS() + "]";
    }

    public Object evaluate(Context var1) throws JaxenException {
        Navigator var2 = var1.getNavigator();
        Boolean var3 = BooleanFunction.evaluate(this.getLHS().evaluate(var1), var2);
        if (var3) {
            return Boolean.TRUE;
        } else {
            Boolean var4 = BooleanFunction.evaluate(this.getRHS().evaluate(var1), var2);
            return var4 ? Boolean.TRUE : Boolean.FALSE;
        }
    }
}