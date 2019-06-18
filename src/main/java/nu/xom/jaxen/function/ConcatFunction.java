package nu.xom.jaxen.function;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.Function;
import nu.xom.jaxen.FunctionCallException;
import nu.xom.jaxen.Navigator;

import java.util.Iterator;
import java.util.List;

public class ConcatFunction implements Function {
    public ConcatFunction() {
    }

    public Object call(Context var1, List var2) throws FunctionCallException {
        if (var2.size() >= 2) {
            return evaluate(var2, var1.getNavigator());
        } else {
            throw new FunctionCallException("concat() requires at least two arguments");
        }
    }

    public static String evaluate(List var0, Navigator var1) {
        StringBuffer var2 = new StringBuffer();
        Iterator var3 = var0.iterator();

        while(var3.hasNext()) {
            var2.append(StringFunction.evaluate(var3.next(), var1));
        }

        return var2.toString();
    }
}

