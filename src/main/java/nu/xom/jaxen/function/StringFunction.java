package nu.xom.jaxen.function;


import nu.xom.jaxen.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class StringFunction implements Function {
    private static DecimalFormat format;

    public StringFunction() {
    }

    public Object call(Context var1, List var2) throws FunctionCallException {
        int var3 = var2.size();
        if (var3 == 0) {
            return evaluate(var1.getNodeSet(), var1.getNavigator());
        } else if (var3 == 1) {
            return evaluate(var2.get(0), var1.getNavigator());
        } else {
            throw new FunctionCallException("string() takes at most argument.");
        }
    }

    public static String evaluate(Object var0, Navigator var1) {
        try {
            if (var1 != null && var1.isText(var0)) {
                return var1.getTextStringValue(var0);
            } else {
                if (var0 instanceof List) {
                    List var2 = (List)var0;
                    if (var2.isEmpty()) {
                        return "";
                    }

                    var0 = var2.get(0);
                }

                if (var1 != null) {
                    if (var1.isElement(var0)) {
                        return var1.getElementStringValue(var0);
                    }

                    if (var1.isAttribute(var0)) {
                        return var1.getAttributeStringValue(var0);
                    }

                    if (var1.isDocument(var0)) {
                        Iterator var5 = var1.getChildAxisIterator(var0);

                        while(var5.hasNext()) {
                            Object var3 = var5.next();
                            if (var1.isElement(var3)) {
                                return var1.getElementStringValue(var3);
                            }
                        }
                    } else {
                        if (var1.isProcessingInstruction(var0)) {
                            return var1.getProcessingInstructionData(var0);
                        }

                        if (var1.isComment(var0)) {
                            return var1.getCommentStringValue(var0);
                        }

                        if (var1.isText(var0)) {
                            return var1.getTextStringValue(var0);
                        }

                        if (var1.isNamespace(var0)) {
                            return var1.getNamespaceStringValue(var0);
                        }
                    }
                }

                if (var0 instanceof String) {
                    return (String)var0;
                } else if (var0 instanceof Boolean) {
                    return stringValue((Boolean)var0);
                } else {
                    return var0 instanceof Number ? stringValue(((Number)var0).doubleValue()) : "";
                }
            }
        } catch (UnsupportedAxisException var4) {
            throw new JaxenRuntimeException(var4);
        }
    }

    private static String stringValue(double var0) {
        if (var0 == 0.0D) {
            return "0";
        } else {
            String var2 = null;
            synchronized(format) {
                var2 = format.format(var0);
                return var2;
            }
        }
    }

    private static String stringValue(boolean var0) {
        return var0 ? "true" : "false";
    }

    static {
        format = (DecimalFormat)NumberFormat.getInstance(Locale.ENGLISH);
        DecimalFormatSymbols var0 = new DecimalFormatSymbols(Locale.ENGLISH);
        var0.setNaN("NaN");
        var0.setInfinity("Infinity");
        format.setGroupingUsed(false);
        format.setMaximumFractionDigits(32);
        format.setDecimalFormatSymbols(var0);
    }
}
