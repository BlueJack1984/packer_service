package nu.xom.jaxen.expr;

import nu.xom.jaxen.Context;
import nu.xom.jaxen.JaxenException;

import java.io.Serializable;

public interface Predicate extends Serializable {
    Expr getExpr();

    void setExpr(Expr var1);

    void simplify();

    String getText();

    Object evaluate(Context var1) throws JaxenException;
}
