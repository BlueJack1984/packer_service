package nu.xom.jaxen.function.xslt;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.Function;
import nu.xom.jaxen.FunctionCallException;
import nu.xom.jaxen.Navigator;
import nu.xom.jaxen.function.StringFunction;

import java.util.List;

public class DocumentFunction implements Function {
    public DocumentFunction() {
    }

    public Object call(Context var1, List var2) throws FunctionCallException {
        if (var2.size() == 1) {
            Navigator var3 = var1.getNavigator();
            String var4 = StringFunction.evaluate(var2.get(0), var3);
            return evaluate(var4, var3);
        } else {
            throw new FunctionCallException("document() requires one argument.");
        }
    }

    public static Object evaluate(String var0, Navigator var1) throws FunctionCallException {
        return var1.getDocument(var0);
    }
}
