package nu.xom.jaxen.expr;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.ContextSupport;
import nu.xom.jaxen.JaxenException;
import nu.xom.jaxen.Navigator;
import nu.xom.jaxen.util.SingletonList;

import java.util.Collections;
import java.util.List;

/** @deprecated */
public class DefaultAbsoluteLocationPath extends DefaultLocationPath {
    private static final long serialVersionUID = 2174836928310146874L;

    public DefaultAbsoluteLocationPath() {
    }

    public String toString() {
        return "[(DefaultAbsoluteLocationPath): " + super.toString() + "]";
    }

    public boolean isAbsolute() {
        return true;
    }

    public String getText() {
        return "/" + super.getText();
    }

    public Object evaluate(Context var1) throws JaxenException {
        ContextSupport var2 = var1.getContextSupport();
        Navigator var3 = var2.getNavigator();
        Context var4 = new Context(var2);
        List var5 = var1.getNodeSet();
        if (var5.isEmpty()) {
            return Collections.EMPTY_LIST;
        } else {
            Object var6 = var5.get(0);
            Object var7 = var3.getDocumentNode(var6);
            if (var7 == null) {
                return Collections.EMPTY_LIST;
            } else {
                SingletonList var8 = new SingletonList(var7);
                var4.setNodeSet(var8);
                return super.evaluate(var4);
            }
        }
    }
}
