package nu.xom.jaxen.expr;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.JaxenException;
import nu.xom.jaxen.Navigator;
import nu.xom.jaxen.function.NumberFunction;

import java.util.Iterator;
import java.util.List;

abstract class DefaultRelationalExpr extends DefaultTruthExpr implements RelationalExpr {
    DefaultRelationalExpr(Expr var1, Expr var2) {
        super(var1, var2);
    }

    public String toString() {
        return "[(DefaultRelationalExpr): " + this.getLHS() + ", " + this.getRHS() + "]";
    }

    public Object evaluate(Context var1) throws JaxenException {
        Object var2 = this.getLHS().evaluate(var1);
        Object var3 = this.getRHS().evaluate(var1);
        Navigator var4 = var1.getNavigator();
        if (this.bothAreSets(var2, var3)) {
            return this.evaluateSetSet((List)var2, (List)var3, var4);
        } else if (this.eitherIsSet(var2, var3)) {
            return this.isSet(var2) ? this.evaluateSetSet((List)var2, convertToList(var3), var4) : this.evaluateSetSet(convertToList(var2), (List)var3, var4);
        } else {
            return this.evaluateObjectObject(var2, var3, var4) ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    private Object evaluateSetSet(List var1, List var2, Navigator var3) {
        if (!this.setIsEmpty(var1) && !this.setIsEmpty(var2)) {
            Iterator var4 = var1.iterator();

            while(var4.hasNext()) {
                Object var5 = var4.next();
                Iterator var6 = var2.iterator();

                while(var6.hasNext()) {
                    Object var7 = var6.next();
                    if (this.evaluateObjectObject(var5, var7, var3)) {
                        return Boolean.TRUE;
                    }
                }
            }

            return Boolean.FALSE;
        } else {
            return Boolean.FALSE;
        }
    }

    private boolean evaluateObjectObject(Object var1, Object var2, Navigator var3) {
        if (var1 != null && var2 != null) {
            Double var4 = NumberFunction.evaluate(var1, var3);
            Double var5 = NumberFunction.evaluate(var2, var3);
            return !NumberFunction.isNaN(var4) && !NumberFunction.isNaN(var5) ? this.evaluateDoubleDouble(var4, var5) : false;
        } else {
            return false;
        }
    }

    protected abstract boolean evaluateDoubleDouble(Double var1, Double var2);
}
