package nu.xom.jaxen.function;

import nu.xom.jaxen.Context;
import nu.xom.jaxen.Function;
import nu.xom.jaxen.FunctionCallException;
import nu.xom.jaxen.Navigator;

import java.util.List;

public class FloorFunction implements Function {
    public FloorFunction() {
    }

    public Object call(Context var1, List var2) throws FunctionCallException {
        if (var2.size() == 1) {
            return evaluate(var2.get(0), var1.getNavigator());
        } else {
            throw new FunctionCallException("floor() requires one argument.");
        }
    }

    public static Double evaluate(Object var0, Navigator var1) {
        Double var2 = NumberFunction.evaluate(var0, var1);
        return new Double(Math.floor(var2));
    }
}