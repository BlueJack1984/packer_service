package nu.xom;

import nu.xom.jaxen.BaseXPath;
import nu.xom.jaxen.FunctionContext;
import nu.xom.jaxen.JaxenException;
import nu.xom.jaxen.XPathFunctionContext;

import java.util.List;
import java.util.ListIterator;

class JaxenConnector extends BaseXPath {
    private static final long serialVersionUID = 9025734269448515308L;
    private static FunctionContext functionContext = new XPathFunctionContext(false);

    JaxenConnector(String var1) throws JaxenException {
        super(var1, new nu.xom.JaxenNavigator());
        this.setFunctionContext(functionContext);
    }

    public List selectNodes(Object var1) throws JaxenException {
        List var2 = super.selectNodes(var1);
        ListIterator var3 = var2.listIterator();

        while(true) {
            List var8;
            label33:
            do {
                while(var3.hasNext()) {
                    Object var4 = var3.next();
                    if (var4 instanceof List) {
                        var8 = (List)var4;
                        var3.set(var8.get(0));
                        continue label33;
                    }

                    try {
                        Node var5 = (Node)var4;
                        if (var5.isDocumentFragment()) {
                            var3.remove();
                            if (var2.isEmpty()) {
                                throw new XPathException("Tried to get document node of disconnected subtree");
                            }
                        }
                    } catch (ClassCastException var7) {
                        XPathTypeException var6 = new XPathTypeException(var2.get(0));
                        throw var6;
                    }
                }

                return var2;
            } while(var8.size() <= 1);

            ListIterator var9 = var8.listIterator(1);

            while(var9.hasNext()) {
                var3.add(var9.next());
            }
        }
    }
}
