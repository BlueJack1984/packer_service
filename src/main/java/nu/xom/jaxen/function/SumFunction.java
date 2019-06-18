package nu.xom.jaxen.function;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.Function;
import nu.xom.jaxen.FunctionCallException;
import nu.xom.jaxen.Navigator;

import java.util.Iterator;
import java.util.List;

public class SumFunction implements Function {
    public SumFunction() {
    }

    public Object call(Context var1, List var2) throws FunctionCallException {
        if (var2.size() == 1) {
            return evaluate(var2.get(0), var1.getNavigator());
        } else {
            throw new FunctionCallException("sum() requires one argument.");
        }
    }

    public static Double evaluate(Object var0, Navigator var1) throws FunctionCallException {
        double var2 = 0.0D;
        if (!(var0 instanceof List)) {
            throw new FunctionCallException("The argument to the sum function must be a node-set");
        } else {
            double var5;
            for(Iterator var4 = ((List)var0).iterator(); var4.hasNext(); var2 += var5) {
                var5 = NumberFunction.evaluate(var4.next(), var1);
            }

            return new Double(var2);
        }
    }
}
