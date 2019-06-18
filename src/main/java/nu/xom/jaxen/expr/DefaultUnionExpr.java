package nu.xom.jaxen.expr;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.JaxenException;
import nu.xom.jaxen.XPathSyntaxException;

import java.util.*;

/** @deprecated */
public class DefaultUnionExpr extends DefaultBinaryExpr implements UnionExpr {
    private static final long serialVersionUID = 7629142718276852707L;

    public DefaultUnionExpr(Expr var1, Expr var2) {
        super(var1, var2);
    }

    public String getOperator() {
        return "|";
    }

    public String toString() {
        return "[(DefaultUnionExpr): " + this.getLHS() + ", " + this.getRHS() + "]";
    }

    public Object evaluate(Context var1) throws JaxenException {
        ArrayList var2 = new ArrayList();

        try {
            List var3 = (List)this.getLHS().evaluate(var1);
            List var4 = (List)this.getRHS().evaluate(var1);
            HashSet var5 = new HashSet();
            var2.addAll(var3);
            var5.addAll(var3);
            Iterator var6 = var4.iterator();

            while(var6.hasNext()) {
                Object var7 = var6.next();
                if (!var5.contains(var7)) {
                    var2.add(var7);
                    var5.add(var7);
                }
            }

            Collections.sort(var2, new NodeComparator(var1.getNavigator()));
            return var2;
        } catch (ClassCastException var8) {
            throw new XPathSyntaxException(this.getText(), var1.getPosition(), "Unions are only allowed over node-sets");
        }
    }
}

