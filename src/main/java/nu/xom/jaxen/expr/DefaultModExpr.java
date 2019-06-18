package nu.xom.jaxen.expr;

import nu.xom.jaxen.Context;
import nu.xom.jaxen.JaxenException;
import nu.xom.jaxen.function.NumberFunction;

class DefaultModExpr extends nu.xom.jaxen.expr.DefaultMultiplicativeExpr {
    private static final long serialVersionUID = -5554964716492040687L;

    DefaultModExpr(Expr var1, Expr var2) {
        super(var1, var2);
    }

    public String getOperator() {
        return "mod";
    }

    public Object evaluate(Context var1) throws JaxenException {
        Double var2 = NumberFunction.evaluate(this.getLHS().evaluate(var1), var1.getNavigator());
        Double var3 = NumberFunction.evaluate(this.getRHS().evaluate(var1), var1.getNavigator());
        double var4 = var2.doubleValue() % var3.doubleValue();
        return new Double(var4);
    }
}
