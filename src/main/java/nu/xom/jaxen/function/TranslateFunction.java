package nu.xom.jaxen.function;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.Function;
import nu.xom.jaxen.FunctionCallException;
import nu.xom.jaxen.Navigator;

import java.util.HashMap;
import java.util.List;

public class TranslateFunction implements Function {
    public TranslateFunction() {
    }

    public Object call(Context var1, List var2) throws FunctionCallException {
        if (var2.size() == 3) {
            return evaluate(var2.get(0), var2.get(1), var2.get(2), var1.getNavigator());
        } else {
            throw new FunctionCallException("translate() requires three arguments.");
        }
    }

    public static String evaluate(Object var0, Object var1, Object var2, Navigator var3) throws FunctionCallException {
        String var4 = StringFunction.evaluate(var0, var3);
        String var5 = StringFunction.evaluate(var1, var3);
        String var6 = StringFunction.evaluate(var2, var3);
        HashMap var7 = new HashMap();
        String[] var8 = toUnicodeCharacters(var5);
        String[] var9 = toUnicodeCharacters(var6);
        int var10 = var8.length;
        int var11 = var9.length;

        for(int var12 = 0; var12 < var10; ++var12) {
            String var13 = var8[var12];
            if (!var7.containsKey(var13)) {
                if (var12 < var11) {
                    var7.put(var13, var9[var12]);
                } else {
                    var7.put(var13, (Object)null);
                }
            }
        }

        StringBuffer var18 = new StringBuffer(var4.length());
        String[] var19 = toUnicodeCharacters(var4);
        int var14 = var19.length;

        for(int var15 = 0; var15 < var14; ++var15) {
            String var16 = var19[var15];
            if (var7.containsKey(var16)) {
                String var17 = (String)var7.get(var16);
                if (var17 != null) {
                    var18.append(var17);
                }
            } else {
                var18.append(var16);
            }
        }

        return var18.toString();
    }

    private static String[] toUnicodeCharacters(String var0) throws FunctionCallException {
        String[] var1 = new String[var0.length()];
        int var2 = 0;

        for(int var3 = 0; var3 < var0.length(); ++var3) {
            char var4 = var0.charAt(var3);
            if (isHighSurrogate(var4)) {
                try {
                    char var5 = var0.charAt(var3 + 1);
                    if (!isLowSurrogate(var5)) {
                        throw new FunctionCallException("Mismatched surrogate pair in translate function");
                    }

                    var1[var2] = (var4 + "" + var5).intern();
                    ++var3;
                } catch (StringIndexOutOfBoundsException var6) {
                    throw new FunctionCallException("High surrogate without low surrogate at end of string passed to translate function");
                }
            } else {
                var1[var2] = String.valueOf(var4).intern();
            }

            ++var2;
        }

        if (var2 == var1.length) {
            return var1;
        } else {
            String[] var7 = new String[var2];
            System.arraycopy(var1, 0, var7, 0, var2);
            return var7;
        }
    }

    private static boolean isHighSurrogate(char var0) {
        return var0 >= '\ud800' && var0 <= '\udbff';
    }

    private static boolean isLowSurrogate(char var0) {
        return var0 >= '\udc00' && var0 <= '\udfff';
    }
}
