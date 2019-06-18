package nu.xom.jaxen.function;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.Function;
import nu.xom.jaxen.FunctionCallException;
import nu.xom.jaxen.Navigator;

import java.util.List;

public class BooleanFunction implements Function {
    public BooleanFunction() {
    }

    public Object call(Context var1, List var2) throws FunctionCallException {
        if (var2.size() == 1) {
            return evaluate(var2.get(0), var1.getNavigator());
        } else {
            throw new FunctionCallException("boolean() requires one argument");
        }
    }

    public static Boolean evaluate(Object var0, Navigator var1) {
        if (var0 instanceof List) {
            List var2 = (List)var0;
            if (var2.size() == 0) {
                return Boolean.FALSE;
            }

            var0 = var2.get(0);
        }

        if (var0 instanceof Boolean) {
            return (Boolean)var0;
        } else if (var0 instanceof Number) {
            double var3 = ((Number)var0).doubleValue();
            return var3 != 0.0D && !Double.isNaN(var3) ? Boolean.TRUE : Boolean.FALSE;
        } else if (var0 instanceof String) {
            return ((String)var0).length() > 0 ? Boolean.TRUE : Boolean.FALSE;
        } else {
            return var0 != null ? Boolean.TRUE : Boolean.FALSE;
        }
    }
}
