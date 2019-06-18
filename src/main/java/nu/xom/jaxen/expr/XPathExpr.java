package nu.xom.jaxen.expr;

import nu.xom.jaxen.Context;
import nu.xom.jaxen.JaxenException;

import java.io.Serializable;
import java.util.List;

public interface XPathExpr extends Serializable {
    Expr getRootExpr();

    void setRootExpr(Expr var1);

    String getText();

    void simplify();

    List asList(Context var1) throws JaxenException;
}
