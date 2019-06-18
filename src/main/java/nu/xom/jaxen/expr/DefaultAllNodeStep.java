package nu.xom.jaxen.expr;
import nu.xom.jaxen.ContextSupport;
import nu.xom.jaxen.expr.iter.IterableAxis;

/** @deprecated */
public class DefaultAllNodeStep extends DefaultStep implements AllNodeStep {
    private static final long serialVersionUID = 292886316770123856L;

    public DefaultAllNodeStep(IterableAxis var1, PredicateSet var2) {
        super(var1, var2);
    }

    public String toString() {
        return "[(DefaultAllNodeStep): " + this.getAxisName() + "]";
    }

    public String getText() {
        return this.getAxisName() + "::node()" + super.getText();
    }

    public boolean matches(Object var1, ContextSupport var2) {
        return true;
    }
}
