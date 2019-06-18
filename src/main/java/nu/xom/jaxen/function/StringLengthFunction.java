package nu.xom.jaxen.function;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.Function;
import nu.xom.jaxen.FunctionCallException;
import nu.xom.jaxen.Navigator;

import java.util.List;

public class StringLengthFunction implements Function {
    public StringLengthFunction() {
    }

    public Object call(Context var1, List var2) throws FunctionCallException {
        if (var2.size() == 0) {
            return evaluate(var1.getNodeSet(), var1.getNavigator());
        } else if (var2.size() == 1) {
            return evaluate(var2.get(0), var1.getNavigator());
        } else {
            throw new FunctionCallException("string-length() requires one argument.");
        }
    }

    public static Double evaluate(Object var0, Navigator var1) throws FunctionCallException {
        String var2 = StringFunction.evaluate(var0, var1);
        char[] var3 = var2.toCharArray();
        int var4 = 0;

        for(int var5 = 0; var5 < var3.length; ++var5) {
            char var6 = var3[var5];
            ++var4;
            if (var6 >= '\ud800' && var6 <= '\udfff') {
                try {
                    char var7 = var3[var5 + 1];
                    if (var7 < '\udc00' || var7 > '\udfff') {
                        throw new FunctionCallException("Bad surrogate pair in string " + var2);
                    }

                    ++var5;
                } catch (ArrayIndexOutOfBoundsException var8) {
                    throw new FunctionCallException("Bad surrogate pair in string " + var2);
                }
            }
        }

        return new Double((double)var4);
    }
}

