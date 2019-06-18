package nu.xom.jaxen.function;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.Function;
import nu.xom.jaxen.FunctionCallException;
import nu.xom.jaxen.Navigator;

import java.util.List;

public class LocalNameFunction implements Function {
    public LocalNameFunction() {
    }

    public Object call(Context var1, List var2) throws FunctionCallException {
        if (var2.size() == 0) {
            return evaluate(var1.getNodeSet(), var1.getNavigator());
        } else if (var2.size() == 1) {
            return evaluate(var2, var1.getNavigator());
        } else {
            throw new FunctionCallException("local-name() requires zero or one argument.");
        }
    }

    public static String evaluate(List var0, Navigator var1) throws FunctionCallException {
        if (!var0.isEmpty()) {
            Object var2 = var0.get(0);
            if (var2 instanceof List) {
                return evaluate((List)var2, var1);
            } else if (var1.isElement(var2)) {
                return var1.getElementName(var2);
            } else if (var1.isAttribute(var2)) {
                return var1.getAttributeName(var2);
            } else if (var1.isProcessingInstruction(var2)) {
                return var1.getProcessingInstructionTarget(var2);
            } else if (var1.isNamespace(var2)) {
                return var1.getNamespacePrefix(var2);
            } else if (var1.isDocument(var2)) {
                return "";
            } else if (var1.isComment(var2)) {
                return "";
            } else if (var1.isText(var2)) {
                return "";
            } else {
                throw new FunctionCallException("The argument to the local-name function must be a node-set");
            }
        } else {
            return "";
        }
    }
}

