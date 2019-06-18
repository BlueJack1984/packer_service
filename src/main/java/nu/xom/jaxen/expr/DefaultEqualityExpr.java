package nu.xom.jaxen.expr;

import nu.xom.jaxen.Context;
import nu.xom.jaxen.JaxenException;
import nu.xom.jaxen.Navigator;
import nu.xom.jaxen.function.BooleanFunction;
import nu.xom.jaxen.function.NumberFunction;
import nu.xom.jaxen.function.StringFunction;

import java.util.Iterator;
import java.util.List;

abstract class DefaultEqualityExpr extends nu.xom.jaxen.expr.DefaultTruthExpr implements EqualityExpr {
    DefaultEqualityExpr(Expr var1, Expr var2) {
        super(var1, var2);
    }

    public String toString() {
        return "[(DefaultEqualityExpr): " + this.getLHS() + ", " + this.getRHS() + "]";
    }

    public Object evaluate(Context var1) throws JaxenException {
        Object var2 = this.getLHS().evaluate(var1);
        Object var3 = this.getRHS().evaluate(var1);
        if (var2 != null && var3 != null) {
            Navigator var4 = var1.getNavigator();
            if (this.bothAreSets(var2, var3)) {
                return this.evaluateSetSet((List)var2, (List)var3, var4);
            } else {
                Boolean var5;
                Boolean var6;
                if (this.isSet(var2) && this.isBoolean(var3)) {
                    var5 = ((List)var2).isEmpty() ? Boolean.FALSE : Boolean.TRUE;
                    var6 = (Boolean)var3;
                    return this.evaluateObjectObject(var5, var6, var4);
                } else if (this.isBoolean(var2) && this.isSet(var3)) {
                    var5 = (Boolean)var2;
                    var6 = ((List)var3).isEmpty() ? Boolean.FALSE : Boolean.TRUE;
                    return this.evaluateObjectObject(var5, var6, var4);
                } else if (this.eitherIsSet(var2, var3)) {
                    return this.isSet(var2) ? this.evaluateSetSet((List)var2, convertToList(var3), var4) : this.evaluateSetSet(convertToList(var2), (List)var3, var4);
                } else {
                    return this.evaluateObjectObject(var2, var3, var4);
                }
            }
        } else {
            return Boolean.FALSE;
        }
    }

    private Boolean evaluateSetSet(List var1, List var2, Navigator var3) {
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
        if (this.eitherIsBoolean(var1, var2)) {
            return this.evaluateObjectObject(BooleanFunction.evaluate(var1, var3), BooleanFunction.evaluate(var2, var3));
        } else {
            return this.eitherIsNumber(var1, var2) ? this.evaluateObjectObject(NumberFunction.evaluate(var1, var3), NumberFunction.evaluate(var2, var3)) : this.evaluateObjectObject(StringFunction.evaluate(var1, var3), StringFunction.evaluate(var2, var3));
        }
    }

    protected abstract boolean evaluateObjectObject(Object var1, Object var2);
}
