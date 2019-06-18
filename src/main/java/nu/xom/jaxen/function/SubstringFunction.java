package nu.xom.jaxen.function;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.Function;
import nu.xom.jaxen.FunctionCallException;
import nu.xom.jaxen.Navigator;

import java.util.List;

public class SubstringFunction implements Function {
    public SubstringFunction() {
    }

    public Object call(Context var1, List var2) throws FunctionCallException {
        int var3 = var2.size();
        if (var3 >= 2 && var3 <= 3) {
            Navigator var4 = var1.getNavigator();
            String var5 = StringFunction.evaluate(var2.get(0), var4);
            if (var5 == null) {
                return "";
            } else {
                int var6 = StringLengthFunction.evaluate(var2.get(0), var4).intValue();
                if (var6 == 0) {
                    return "";
                } else {
                    Double var7 = NumberFunction.evaluate(var2.get(1), var4);
                    if (var7.isNaN()) {
                        return "";
                    } else {
                        int var8 = RoundFunction.evaluate(var7, var4).intValue() - 1;
                        int var9 = var6;
                        if (var3 == 3) {
                            Double var10 = NumberFunction.evaluate(var2.get(2), var4);
                            if (!var10.isNaN()) {
                                var9 = RoundFunction.evaluate(var10, var4).intValue();
                            } else {
                                var9 = 0;
                            }
                        }

                        if (var9 < 0) {
                            return "";
                        } else {
                            int var11 = var8 + var9;
                            if (var3 == 2) {
                                var11 = var6;
                            }

                            if (var8 < 0) {
                                var8 = 0;
                            } else if (var8 > var6) {
                                return "";
                            }

                            if (var11 > var6) {
                                var11 = var6;
                            } else if (var11 < var8) {
                                return "";
                            }

                            return var6 == var5.length() ? var5.substring(var8, var11) : unicodeSubstring(var5, var8, var11);
                        }
                    }
                }
            }
        } else {
            throw new FunctionCallException("substring() requires two or three arguments.");
        }
    }

    private static String unicodeSubstring(String var0, int var1, int var2) {
        StringBuffer var3 = new StringBuffer(var0.length());
        int var4 = 0;

        for(int var5 = 0; var5 < var2; ++var5) {
            char var6 = var0.charAt(var4);
            if (var5 >= var1) {
                var3.append(var6);
            }

            if (var6 >= '\ud800') {
                ++var4;
                if (var5 >= var1) {
                    var3.append(var0.charAt(var4));
                }
            }

            ++var4;
        }

        return var3.toString();
    }
}

