package nu.xom.jaxen.expr;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.Function;
import nu.xom.jaxen.JaxenException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** @deprecated */
public class DefaultFunctionCallExpr extends DefaultExpr implements FunctionCallExpr {
    private static final long serialVersionUID = -4747789292572193708L;
    private String prefix;
    private String functionName;
    private List parameters;

    public DefaultFunctionCallExpr(String var1, String var2) {
        this.prefix = var1;
        this.functionName = var2;
        this.parameters = new ArrayList();
    }

    public void addParameter(Expr var1) {
        this.parameters.add(var1);
    }

    public List getParameters() {
        return this.parameters;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getFunctionName() {
        return this.functionName;
    }

    public String getText() {
        StringBuffer var1 = new StringBuffer();
        String var2 = this.getPrefix();
        if (var2 != null && var2.length() > 0) {
            var1.append(var2);
            var1.append(":");
        }

        var1.append(this.getFunctionName());
        var1.append("(");
        Iterator var3 = this.getParameters().iterator();

        while(var3.hasNext()) {
            Expr var4 = (Expr)var3.next();
            var1.append(var4.getText());
            if (var3.hasNext()) {
                var1.append(", ");
            }
        }

        var1.append(")");
        return var1.toString();
    }

    public Expr simplify() {
        List var1 = this.getParameters();
        int var2 = var1.size();
        ArrayList var3 = new ArrayList(var2);

        for(int var4 = 0; var4 < var2; ++var4) {
            Expr var5 = (Expr)var1.get(var4);
            var3.add(var5.simplify());
        }

        this.parameters = var3;
        return this;
    }

    public String toString() {
        String var1 = this.getPrefix();
        return var1 == null ? "[(DefaultFunctionCallExpr): " + this.getFunctionName() + "(" + this.getParameters() + ") ]" : "[(DefaultFunctionCallExpr): " + this.getPrefix() + ":" + this.getFunctionName() + "(" + this.getParameters() + ") ]";
    }

    public Object evaluate(Context var1) throws JaxenException {
        String var2 = this.getPrefix();
        String var3 = null;
        if (var2 != null && !"".equals(var2)) {
            var3 = var1.translateNamespacePrefixToUri(var2);
        }

        Function var4 = var1.getFunction(var3, var2, this.getFunctionName());
        List var5 = this.evaluateParams(var1);
        return var4.call(var1, var5);
    }

    public List evaluateParams(Context var1) throws JaxenException {
        List var2 = this.getParameters();
        int var3 = var2.size();
        ArrayList var4 = new ArrayList(var3);

        for(int var5 = 0; var5 < var3; ++var5) {
            Expr var6 = (Expr)var2.get(var5);
            Object var7 = var6.evaluate(var1);
            var4.add(var7);
        }

        return var4;
    }
}
