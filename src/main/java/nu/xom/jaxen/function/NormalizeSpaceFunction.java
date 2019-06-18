package nu.xom.jaxen.function;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.Function;
import nu.xom.jaxen.FunctionCallException;
import nu.xom.jaxen.Navigator;

import java.util.List;

public class NormalizeSpaceFunction implements Function {
    public NormalizeSpaceFunction() {
    }

    public Object call(Context var1, List var2) throws FunctionCallException {
        if (var2.size() == 0) {
            return evaluate(var1.getNodeSet(), var1.getNavigator());
        } else if (var2.size() == 1) {
            return evaluate(var2.get(0), var1.getNavigator());
        } else {
            throw new FunctionCallException("normalize-space() cannot have more than one argument");
        }
    }

    public static String evaluate(Object var0, Navigator var1) {
        String var2 = StringFunction.evaluate(var0, var1);
        char[] var3 = var2.toCharArray();
        int var4 = 0;
        int var5 = 0;
        boolean var6 = false;
        int var7 = 0;

        while(true) {
            while(var7 < var3.length) {
                if (isXMLSpace(var3[var7])) {
                    if (var6) {
                        var3[var4++] = ' ';
                    }

                    while(true) {
                        ++var7;
                        if (var7 >= var3.length || !isXMLSpace(var3[var7])) {
                            break;
                        }
                    }
                } else {
                    var3[var4++] = var3[var7++];
                    var6 = true;
                    var5 = var4;
                }
            }

            return new String(var3, 0, var5);
        }
    }

    private static boolean isXMLSpace(char var0) {
        return var0 == ' ' || var0 == '\n' || var0 == '\r' || var0 == '\t';
    }
}
