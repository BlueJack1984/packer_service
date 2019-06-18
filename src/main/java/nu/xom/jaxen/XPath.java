package nu.xom.jaxen;

import java.util.List;

public interface XPath {
    Object evaluate(Object var1) throws JaxenException;

    /** @deprecated */
    String valueOf(Object var1) throws JaxenException;

    String stringValueOf(Object var1) throws JaxenException;

    boolean booleanValueOf(Object var1) throws JaxenException;

    Number numberValueOf(Object var1) throws JaxenException;

    List selectNodes(Object var1) throws JaxenException;

    Object selectSingleNode(Object var1) throws JaxenException;

    void addNamespace(String var1, String var2) throws JaxenException;

    void setNamespaceContext(NamespaceContext var1);

    void setFunctionContext(FunctionContext var1);

    void setVariableContext(VariableContext var1);

    NamespaceContext getNamespaceContext();

    FunctionContext getFunctionContext();

    VariableContext getVariableContext();

    Navigator getNavigator();
}
