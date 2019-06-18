package nu.xom.jaxen.expr;


import nu.xom.jaxen.ContextSupport;
import nu.xom.jaxen.Navigator;
import nu.xom.jaxen.expr.iter.IterableAxis;

/** @deprecated */
public class DefaultCommentNodeStep extends DefaultStep implements CommentNodeStep {
    private static final long serialVersionUID = 4340788283861875606L;

    public DefaultCommentNodeStep(IterableAxis var1, PredicateSet var2) {
        super(var1, var2);
    }

    public String toString() {
        return "[(DefaultCommentNodeStep): " + this.getAxis() + "]";
    }

    public String getText() {
        return this.getAxisName() + "::comment()";
    }

    public boolean matches(Object var1, ContextSupport var2) {
        Navigator var3 = var2.getNavigator();
        return var3.isComment(var1);
    }
}

