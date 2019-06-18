package nu.xom.jaxen.function;


import nu.xom.jaxen.*;

import java.util.Iterator;
import java.util.List;

public class LangFunction implements Function {
    private static final String LANG_LOCALNAME = "lang";
    private static final String XMLNS_URI = "http://www.w3.org/XML/1998/namespace";

    public LangFunction() {
    }

    public Object call(Context var1, List var2) throws FunctionCallException {
        if (var2.size() != 1) {
            throw new FunctionCallException("lang() requires exactly one argument.");
        } else {
            Object var3 = var2.get(0);

            try {
                return evaluate(var1.getNodeSet(), var3, var1.getNavigator());
            } catch (UnsupportedAxisException var5) {
                throw new FunctionCallException("Can't evaluate lang()", var5);
            }
        }
    }

    private static Boolean evaluate(List var0, Object var1, Navigator var2) throws UnsupportedAxisException {
        return evaluate(var0.get(0), StringFunction.evaluate(var1, var2), var2) ? Boolean.TRUE : Boolean.FALSE;
    }

    private static boolean evaluate(Object var0, String var1, Navigator var2) throws UnsupportedAxisException {
        Object var3 = var0;
        if (!var2.isElement(var0)) {
            var3 = var2.getParentNode(var0);
        }

        while(var3 != null && var2.isElement(var3)) {
            Iterator var4 = var2.getAttributeAxisIterator(var3);

            while(var4.hasNext()) {
                Object var5 = var4.next();
                if ("lang".equals(var2.getAttributeName(var5)) && "http://www.w3.org/XML/1998/namespace".equals(var2.getAttributeNamespaceUri(var5))) {
                    return isSublang(var2.getAttributeStringValue(var5), var1);
                }
            }

            var3 = var2.getParentNode(var3);
        }

        return false;
    }

    private static boolean isSublang(String var0, String var1) {
        if (var0.equalsIgnoreCase(var1)) {
            return true;
        } else {
            int var2 = var1.length();
            return var0.length() > var2 && var0.charAt(var2) == '-' && var0.substring(0, var2).equalsIgnoreCase(var1);
        }
    }
}

