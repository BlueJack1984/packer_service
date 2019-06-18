package nu.xom.jaxen.function;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.Function;
import nu.xom.jaxen.FunctionCallException;

import java.util.List;

public class CountFunction implements Function {
    public CountFunction() {
    }

    public Object call(Context var1, List var2) throws FunctionCallException {
        if (var2.size() == 1) {
            return evaluate(var2.get(0));
        } else {
            throw new FunctionCallException("count() requires one argument.");
        }
    }

    public static Double evaluate(Object var0) throws FunctionCallException {
        if (var0 instanceof List) {
            return new Double((double)((List)var0).size());
        } else {
            throw new FunctionCallException("count() function can only be used for node-sets");
        }
    }
}
