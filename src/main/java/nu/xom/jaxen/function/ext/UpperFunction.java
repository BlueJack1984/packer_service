package nu.xom.jaxen.function.ext;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.FunctionCallException;
import nu.xom.jaxen.Navigator;
import nu.xom.jaxen.function.StringFunction;

import java.util.List;
import java.util.Locale;

public class UpperFunction extends LocaleFunctionSupport {
    public UpperFunction() {
    }

    public Object call(Context var1, List var2) throws FunctionCallException {
        Navigator var3 = var1.getNavigator();
        int var4 = var2.size();
        if (var4 > 0) {
            Object var5 = var2.get(0);
            Locale var6 = null;
            if (var4 > 1) {
                var6 = this.getLocale(var2.get(1), var3);
            }

            return evaluate(var5, var6, var3);
        } else {
            throw new FunctionCallException("upper-case() requires at least one argument.");
        }
    }

    public static String evaluate(Object var0, Locale var1, Navigator var2) {
        String var3 = StringFunction.evaluate(var0, var2);
        if (var1 == null) {
            var1 = Locale.ENGLISH;
        }

        return var3.toUpperCase(var1);
    }
}
