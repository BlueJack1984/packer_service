package nu.xom.jaxen.function;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.Function;
import nu.xom.jaxen.FunctionCallException;
import nu.xom.jaxen.Navigator;

import java.util.*;

public class IdFunction implements Function {
    public IdFunction() {
    }

    public Object call(Context var1, List var2) throws FunctionCallException {
        if (var2.size() == 1) {
            return evaluate(var1.getNodeSet(), var2.get(0), var1.getNavigator());
        } else {
            throw new FunctionCallException("id() requires one argument");
        }
    }

    public static List evaluate(List var0, Object var1, Navigator var2) {
        if (var0.size() == 0) {
            return Collections.EMPTY_LIST;
        } else {
            ArrayList var3 = new ArrayList();
            Object var4 = var0.get(0);
            if (var1 instanceof List) {
                Iterator var5 = ((List)var1).iterator();

                while(var5.hasNext()) {
                    String var6 = StringFunction.evaluate(var5.next(), var2);
                    var3.addAll(evaluate(var0, var6, var2));
                }
            } else {
                String var9 = StringFunction.evaluate(var1, var2);
                StringTokenizer var10 = new StringTokenizer(var9, " \t\n\r");

                while(var10.hasMoreTokens()) {
                    String var7 = var10.nextToken();
                    Object var8 = var2.getElementById(var4, var7);
                    if (var8 != null) {
                        var3.add(var8);
                    }
                }
            }

            return var3;
        }
    }
}
