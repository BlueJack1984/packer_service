package nu.xom.jaxen.function;

import nu.xom.jaxen.Context;
import nu.xom.jaxen.Function;
import nu.xom.jaxen.FunctionCallException;

import java.util.List;

public class FalseFunction implements Function {
    public FalseFunction() {
    }

    public Object call(Context var1, List var2) throws FunctionCallException {
        if (var2.size() == 0) {
            return evaluate();
        } else {
            throw new FunctionCallException("false() requires no arguments.");
        }
    }

    public static Boolean evaluate() {
        return Boolean.FALSE;
    }
}