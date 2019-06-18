package nu.xom.jaxen.function;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.Function;
import nu.xom.jaxen.FunctionCallException;
import nu.xom.jaxen.Navigator;

import java.util.List;

public class SubstringBeforeFunction implements Function {
    public SubstringBeforeFunction() {
    }

    public Object call(Context var1, List var2) throws FunctionCallException {
        if (var2.size() == 2) {
            return evaluate(var2.get(0), var2.get(1), var1.getNavigator());
        } else {
            throw new FunctionCallException("substring-before() requires two arguments.");
        }
    }

    public static String evaluate(Object var0, Object var1, Navigator var2) {
        String var3 = StringFunction.evaluate(var0, var2);
        String var4 = StringFunction.evaluate(var1, var2);
        int var5 = var3.indexOf(var4);
        return var5 < 0 ? "" : var3.substring(0, var5);
    }
}
