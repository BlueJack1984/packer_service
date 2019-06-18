package nu.xom.jaxen.function;

import nu.xom.jaxen.Context;
import nu.xom.jaxen.Function;
import nu.xom.jaxen.FunctionCallException;
import nu.xom.jaxen.Navigator;

import java.util.Iterator;
import java.util.List;

public class NumberFunction implements Function {
    private static final Double NaN = new Double(0.0D / 0.0);

    public NumberFunction() {
    }

    public Object call(Context var1, List var2) throws FunctionCallException {
        if (var2.size() == 1) {
            return evaluate(var2.get(0), var1.getNavigator());
        } else if (var2.size() == 0) {
            return evaluate(var1.getNodeSet(), var1.getNavigator());
        } else {
            throw new FunctionCallException("number() takes at most one argument.");
        }
    }

    public static Double evaluate(Object var0, Navigator var1) {
        if (var0 instanceof Double) {
            return (Double)var0;
        } else if (var0 instanceof String) {
            String var2 = (String)var0;

            try {
                Double var3 = new Double(var2);
                return var3;
            } catch (NumberFormatException var4) {
                return NaN;
            }
        } else if (!(var0 instanceof List) && !(var0 instanceof Iterator)) {
            if (!var1.isElement(var0) && !var1.isAttribute(var0) && !var1.isText(var0) && !var1.isComment(var0) && !var1.isProcessingInstruction(var0) && !var1.isDocument(var0) && !var1.isNamespace(var0)) {
                if (var0 instanceof Boolean) {
                    return var0 == Boolean.TRUE ? new Double(1.0D) : new Double(0.0D);
                } else {
                    return NaN;
                }
            } else {
                return evaluate(StringFunction.evaluate(var0, var1), var1);
            }
        } else {
            return evaluate(StringFunction.evaluate(var0, var1), var1);
        }
    }

    public static boolean isNaN(double var0) {
        return Double.isNaN(var0);
    }

    public static boolean isNaN(Double var0) {
        return var0.equals(NaN);
    }
}

