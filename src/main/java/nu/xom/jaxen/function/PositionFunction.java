package nu.xom.jaxen.function;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.Function;
import nu.xom.jaxen.FunctionCallException;

import java.util.List;

public class PositionFunction implements Function {
    public PositionFunction() {
    }

    public Object call(Context var1, List var2) throws FunctionCallException {
        if (var2.size() == 0) {
            return evaluate(var1);
        } else {
            throw new FunctionCallException("position() does not take any arguments.");
        }
    }

    public static Double evaluate(Context var0) {
        return new Double((double)var0.getPosition());
    }
}