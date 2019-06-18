package nu.xom.jaxen.expr;

import nu.xom.jaxen.ContextSupport;
import nu.xom.jaxen.Navigator;
import nu.xom.jaxen.expr.iter.IterableAxis;

/** @deprecated */
public class DefaultTextNodeStep extends DefaultStep implements TextNodeStep {
    private static final long serialVersionUID = -3821960984972022948L;

    public DefaultTextNodeStep(IterableAxis var1, PredicateSet var2) {
        super(var1, var2);
    }

    public boolean matches(Object var1, ContextSupport var2) {
        Navigator var3 = var2.getNavigator();
        return var3.isText(var1);
    }

    public String getText() {
        return this.getAxisName() + "::text()" + super.getText();
    }
}

