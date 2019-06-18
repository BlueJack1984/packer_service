package nu.xom.jaxen;


import nu.xom.jaxen.saxpath.SAXPathException;

import java.io.Serializable;
import java.util.Iterator;

public interface Navigator extends Serializable {
    Iterator getChildAxisIterator(Object var1) throws UnsupportedAxisException;

    Iterator getDescendantAxisIterator(Object var1) throws UnsupportedAxisException;

    Iterator getParentAxisIterator(Object var1) throws UnsupportedAxisException;

    Iterator getAncestorAxisIterator(Object var1) throws UnsupportedAxisException;

    Iterator getFollowingSiblingAxisIterator(Object var1) throws UnsupportedAxisException;

    Iterator getPrecedingSiblingAxisIterator(Object var1) throws UnsupportedAxisException;

    Iterator getFollowingAxisIterator(Object var1) throws UnsupportedAxisException;

    Iterator getPrecedingAxisIterator(Object var1) throws UnsupportedAxisException;

    Iterator getAttributeAxisIterator(Object var1) throws UnsupportedAxisException;

    Iterator getNamespaceAxisIterator(Object var1) throws UnsupportedAxisException;

    Iterator getSelfAxisIterator(Object var1) throws UnsupportedAxisException;

    Iterator getDescendantOrSelfAxisIterator(Object var1) throws UnsupportedAxisException;

    Iterator getAncestorOrSelfAxisIterator(Object var1) throws UnsupportedAxisException;

    Object getDocument(String var1) throws FunctionCallException;

    Object getDocumentNode(Object var1);

    Object getParentNode(Object var1) throws UnsupportedAxisException;

    String getElementNamespaceUri(Object var1);

    String getElementName(Object var1);

    String getElementQName(Object var1);

    String getAttributeNamespaceUri(Object var1);

    String getAttributeName(Object var1);

    String getAttributeQName(Object var1);

    String getProcessingInstructionTarget(Object var1);

    String getProcessingInstructionData(Object var1);

    boolean isDocument(Object var1);

    boolean isElement(Object var1);

    boolean isAttribute(Object var1);

    boolean isNamespace(Object var1);

    boolean isComment(Object var1);

    boolean isText(Object var1);

    boolean isProcessingInstruction(Object var1);

    String getCommentStringValue(Object var1);

    String getElementStringValue(Object var1);

    String getAttributeStringValue(Object var1);

    String getNamespaceStringValue(Object var1);

    String getTextStringValue(Object var1);

    String getNamespacePrefix(Object var1);

    String translateNamespacePrefixToUri(String var1, Object var2);

    XPath parseXPath(String var1) throws SAXPathException;

    Object getElementById(Object var1, String var2);

    short getNodeType(Object var1);
}
