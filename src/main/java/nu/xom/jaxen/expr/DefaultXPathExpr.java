package nu.xom.jaxen.expr;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.JaxenException;

import java.util.List;

/** @deprecated */
public class DefaultXPathExpr implements XPathExpr {
    private static final long serialVersionUID = 3007613096320896040L;
    private Expr rootExpr;

    public DefaultXPathExpr(Expr var1) {
        this.rootExpr = var1;
    }

    public Expr getRootExpr() {
        return this.rootExpr;
    }

    public void setRootExpr(Expr var1) {
        this.rootExpr = var1;
    }

    public String toString() {
        return "[(DefaultXPath): " + this.getRootExpr() + "]";
    }

    public String getText() {
        return this.getRootExpr().getText();
    }

    public void simplify() {
        this.setRootExpr(this.getRootExpr().simplify());
    }

    public List asList(Context var1) throws JaxenException {
        Expr var2 = this.getRootExpr();
        Object var3 = var2.evaluate(var1);
        List var4 = DefaultExpr.convertToList(var3);
        return var4;
    }
}

