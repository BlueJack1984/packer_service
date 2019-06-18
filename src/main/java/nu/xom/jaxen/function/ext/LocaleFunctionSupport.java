package nu.xom.jaxen.function.ext;


import nu.xom.jaxen.Function;
import nu.xom.jaxen.Navigator;
import nu.xom.jaxen.function.StringFunction;

import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public abstract class LocaleFunctionSupport implements Function {
    public LocaleFunctionSupport() {
    }

    protected Locale getLocale(Object var1, Navigator var2) {
        if (var1 instanceof Locale) {
            return (Locale)var1;
        } else {
            if (var1 instanceof List) {
                List var3 = (List)var1;
                if (!var3.isEmpty()) {
                    return this.getLocale(var3.get(0), var2);
                }
            } else {
                String var4 = StringFunction.evaluate(var1, var2);
                if (var4 != null && var4.length() > 0) {
                    return this.findLocale(var4);
                }
            }

            return null;
        }
    }

    protected Locale findLocale(String var1) {
        StringTokenizer var2 = new StringTokenizer(var1, "-");
        if (var2.hasMoreTokens()) {
            String var3 = var2.nextToken();
            if (!var2.hasMoreTokens()) {
                return this.findLocaleForLanguage(var3);
            } else {
                String var4 = var2.nextToken();
                if (!var2.hasMoreTokens()) {
                    return new Locale(var3, var4);
                } else {
                    String var5 = var2.nextToken();
                    return new Locale(var3, var4, var5);
                }
            }
        } else {
            return null;
        }
    }

    protected Locale findLocaleForLanguage(String var1) {
        Locale[] var2 = Locale.getAvailableLocales();
        int var3 = 0;

        for(int var4 = var2.length; var3 < var4; ++var3) {
            Locale var5 = var2[var3];
            if (var1.equals(var5.getLanguage())) {
                String var6 = var5.getCountry();
                if (var6 == null || var6.length() == 0) {
                    String var7 = var5.getVariant();
                    if (var7 == null || var7.length() == 0) {
                        return var5;
                    }
                }
            }
        }

        return null;
    }
}
