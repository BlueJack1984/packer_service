package nu.xom.jaxen;

import nu.xom.jaxen.expr.Expr;
import nu.xom.jaxen.expr.XPathExpr;
import nu.xom.jaxen.function.BooleanFunction;
import nu.xom.jaxen.function.NumberFunction;
import nu.xom.jaxen.function.StringFunction;
import nu.xom.jaxen.saxpath.SAXPathException;
import nu.xom.jaxen.saxpath.XPathReader;
import nu.xom.jaxen.saxpath.helpers.XPathReaderFactory;
import nu.xom.jaxen.util.SingletonList;

import java.io.Serializable;
import java.util.List;

public class BaseXPath implements XPath, Serializable {
    private static final long serialVersionUID = -1993731281300293168L;
    private final String exprText;
    private final XPathExpr xpath;
    private ContextSupport support;
    private Navigator navigator;

    protected BaseXPath(String var1) throws JaxenException {
        try {
            XPathReader var2 = XPathReaderFactory.createReader();
            JaxenHandler var3 = new JaxenHandler();
            var2.setXPathHandler(var3);
            var2.parse(var1);
            this.xpath = var3.getXPathExpr();
        } catch (nu.xom.jaxen.saxpath.XPathSyntaxException var4) {
            throw new nu.xom.jaxen.XPathSyntaxException(var4);
        } catch (SAXPathException var5) {
            throw new JaxenException(var5);
        }

        this.exprText = var1;
    }

    public BaseXPath(String var1, Navigator var2) throws JaxenException {
        this(var1);
        this.navigator = var2;
    }

    public Object evaluate(Object var1) throws JaxenException {
        List var2 = this.selectNodes(var1);
        if (var2 != null && var2.size() == 1) {
            Object var3 = var2.get(0);
            if (var3 instanceof String || var3 instanceof Number || var3 instanceof Boolean) {
                return var3;
            }
        }

        return var2;
    }

    public List selectNodes(Object var1) throws JaxenException {
        Context var2 = this.getContext(var1);
        return this.selectNodesForContext(var2);
    }

    public Object selectSingleNode(Object var1) throws JaxenException {
        List var2 = this.selectNodes(var1);
        return var2.isEmpty() ? null : var2.get(0);
    }

    /** @deprecated */
    public String valueOf(Object var1) throws JaxenException {
        return this.stringValueOf(var1);
    }

    public String stringValueOf(Object var1) throws JaxenException {
        Context var2 = this.getContext(var1);
        Object var3 = this.selectSingleNodeForContext(var2);
        return var3 == null ? "" : StringFunction.evaluate(var3, var2.getNavigator());
    }

    public boolean booleanValueOf(Object var1) throws JaxenException {
        Context var2 = this.getContext(var1);
        List var3 = this.selectNodesForContext(var2);
        return var3 == null ? false : BooleanFunction.evaluate(var3, var2.getNavigator());
    }

    public Number numberValueOf(Object var1) throws JaxenException {
        Context var2 = this.getContext(var1);
        Object var3 = this.selectSingleNodeForContext(var2);
        return NumberFunction.evaluate(var3, var2.getNavigator());
    }

    public void addNamespace(String var1, String var2) throws JaxenException {
        NamespaceContext var3 = this.getNamespaceContext();
        if (var3 instanceof SimpleNamespaceContext) {
            ((SimpleNamespaceContext)var3).addNamespace(var1, var2);
        } else {
            throw new JaxenException("Operation not permitted while using a non-simple namespace context.");
        }
    }

    public void setNamespaceContext(NamespaceContext var1) {
        this.getContextSupport().setNamespaceContext(var1);
    }

    public void setFunctionContext(FunctionContext var1) {
        this.getContextSupport().setFunctionContext(var1);
    }

    public void setVariableContext(VariableContext var1) {
        this.getContextSupport().setVariableContext(var1);
    }

    public NamespaceContext getNamespaceContext() {
        return this.getContextSupport().getNamespaceContext();
    }

    public FunctionContext getFunctionContext() {
        return this.getContextSupport().getFunctionContext();
    }

    public VariableContext getVariableContext() {
        return this.getContextSupport().getVariableContext();
    }

    public Expr getRootExpr() {
        return this.xpath.getRootExpr();
    }

    public String toString() {
        return this.exprText;
    }

    public String debug() {
        return this.xpath.toString();
    }

    protected Context getContext(Object var1) {
        if (var1 instanceof Context) {
            return (Context)var1;
        } else {
            Context var2 = new Context(this.getContextSupport());
            if (var1 instanceof List) {
                var2.setNodeSet((List)var1);
            } else {
                SingletonList var3 = new SingletonList(var1);
                var2.setNodeSet(var3);
            }

            return var2;
        }
    }

    protected ContextSupport getContextSupport() {
        if (this.support == null) {
            this.support = new ContextSupport(this.createNamespaceContext(), this.createFunctionContext(), this.createVariableContext(), this.getNavigator());
        }

        return this.support;
    }

    public Navigator getNavigator() {
        return this.navigator;
    }

    protected FunctionContext createFunctionContext() {
        return XPathFunctionContext.getInstance();
    }

    protected NamespaceContext createNamespaceContext() {
        return new SimpleNamespaceContext();
    }

    protected VariableContext createVariableContext() {
        return new SimpleVariableContext();
    }

    protected List selectNodesForContext(Context var1) throws JaxenException {
        List var2 = this.xpath.asList(var1);
        return var2;
    }

    protected Object selectSingleNodeForContext(Context var1) throws JaxenException {
        List var2 = this.selectNodesForContext(var1);
        return var2.isEmpty() ? null : var2.get(0);
    }
}
