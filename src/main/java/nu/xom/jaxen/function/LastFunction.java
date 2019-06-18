package nu.xom.jaxen.function;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.Function;
import nu.xom.jaxen.FunctionCallException;

import java.util.List;

public class LastFunction implements Function {
    public LastFunction() {
    }

    public Object call(Context var1, List var2) throws FunctionCallException {
        if (var2.size() == 0) {
            return evaluate(var1);
        } else {
            throw new FunctionCallException("last() requires no arguments.");
        }
    }

    public static Double evaluate(Context var0) {
        return new Double((double)var0.getSize());
    }
}