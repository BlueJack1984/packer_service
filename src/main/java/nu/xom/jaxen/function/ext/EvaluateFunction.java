package nu.xom.jaxen.function.ext;


import nu.xom.jaxen.*;
import nu.xom.jaxen.function.StringFunction;
import nu.xom.jaxen.saxpath.SAXPathException;

import java.util.Collections;
import java.util.List;

public class EvaluateFunction implements Function {
    public EvaluateFunction() {
    }

    public Object call(Context var1, List var2) throws FunctionCallException {
        if (var2.size() == 1) {
            return evaluate(var1, var2.get(0));
        } else {
            throw new FunctionCallException("evaluate() requires one argument");
        }
    }

    public static List evaluate(Context var0, Object var1) throws FunctionCallException {
        List var2 = var0.getNodeSet();
        if (var2.size() == 0) {
            return Collections.EMPTY_LIST;
        } else {
            Navigator var3 = var0.getNavigator();
            String var4;
            if (var1 instanceof String) {
                var4 = (String)var1;
            } else {
                var4 = StringFunction.evaluate(var1, var3);
            }

            try {
                XPath var5 = var3.parseXPath(var4);
                ContextSupport var6 = var0.getContextSupport();
                var5.setVariableContext(var6.getVariableContext());
                var5.setFunctionContext(var6.getFunctionContext());
                var5.setNamespaceContext(var6.getNamespaceContext());
                return var5.selectNodes(var0.duplicate());
            } catch (SAXPathException var7) {
                throw new FunctionCallException(var7.toString());
            }
        }
    }
}
