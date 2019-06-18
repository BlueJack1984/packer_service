package nu.xom.jaxen;


import java.io.Serializable;

public class ContextSupport implements Serializable {
    private static final long serialVersionUID = 4494082174713652559L;
    private transient FunctionContext functionContext;
    private NamespaceContext namespaceContext;
    private VariableContext variableContext;
    private Navigator navigator;

    public ContextSupport() {
    }

    public ContextSupport(NamespaceContext var1, FunctionContext var2, VariableContext var3, Navigator var4) {
        this.setNamespaceContext(var1);
        this.setFunctionContext(var2);
        this.setVariableContext(var3);
        this.navigator = var4;
    }

    public void setNamespaceContext(NamespaceContext var1) {
        this.namespaceContext = var1;
    }

    public NamespaceContext getNamespaceContext() {
        return this.namespaceContext;
    }

    public void setFunctionContext(FunctionContext var1) {
        this.functionContext = var1;
    }

    public FunctionContext getFunctionContext() {
        return this.functionContext;
    }

    public void setVariableContext(VariableContext var1) {
        this.variableContext = var1;
    }

    public VariableContext getVariableContext() {
        return this.variableContext;
    }

    public Navigator getNavigator() {
        return this.navigator;
    }

    public String translateNamespacePrefixToUri(String var1) {
        if ("xml".equals(var1)) {
            return "http://www.w3.org/XML/1998/namespace";
        } else {
            NamespaceContext var2 = this.getNamespaceContext();
            return var2 != null ? var2.translateNamespacePrefixToUri(var1) : null;
        }
    }

    public Object getVariableValue(String var1, String var2, String var3) throws UnresolvableException {
        VariableContext var4 = this.getVariableContext();
        if (var4 != null) {
            return var4.getVariableValue(var1, var2, var3);
        } else {
            throw new UnresolvableException("No variable context installed");
        }
    }

    public Function getFunction(String var1, String var2, String var3) throws UnresolvableException {
        FunctionContext var4 = this.getFunctionContext();
        if (var4 != null) {
            return var4.getFunction(var1, var2, var3);
        } else {
            throw new UnresolvableException("No function context installed");
        }
    }
}

