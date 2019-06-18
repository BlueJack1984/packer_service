package nu.xom.jaxen;


import nu.xom.jaxen.util.*;

import java.util.Iterator;

public abstract class DefaultNavigator implements Navigator {
    public DefaultNavigator() {
    }

    public Iterator getChildAxisIterator(Object var1) throws UnsupportedAxisException {
        throw new UnsupportedAxisException("child");
    }

    public Iterator getDescendantAxisIterator(Object var1) throws UnsupportedAxisException {
        return new DescendantAxisIterator(var1, this);
    }

    public Iterator getParentAxisIterator(Object var1) throws UnsupportedAxisException {
        throw new UnsupportedAxisException("parent");
    }

    public Iterator getAncestorAxisIterator(Object var1) throws UnsupportedAxisException {
        return new AncestorAxisIterator(var1, this);
    }

    public Iterator getFollowingSiblingAxisIterator(Object var1) throws UnsupportedAxisException {
        return new FollowingSiblingAxisIterator(var1, this);
    }

    public Iterator getPrecedingSiblingAxisIterator(Object var1) throws UnsupportedAxisException {
        return new PrecedingSiblingAxisIterator(var1, this);
    }

    public Iterator getFollowingAxisIterator(Object var1) throws UnsupportedAxisException {
        return new FollowingAxisIterator(var1, this);
    }

    public Iterator getPrecedingAxisIterator(Object var1) throws UnsupportedAxisException {
        return new PrecedingAxisIterator(var1, this);
    }

    public Iterator getAttributeAxisIterator(Object var1) throws UnsupportedAxisException {
        throw new UnsupportedAxisException("attribute");
    }

    public Iterator getNamespaceAxisIterator(Object var1) throws UnsupportedAxisException {
        throw new UnsupportedAxisException("namespace");
    }

    public Iterator getSelfAxisIterator(Object var1) throws UnsupportedAxisException {
        return new SelfAxisIterator(var1);
    }

    public Iterator getDescendantOrSelfAxisIterator(Object var1) throws UnsupportedAxisException {
        return new DescendantOrSelfAxisIterator(var1, this);
    }

    public Iterator getAncestorOrSelfAxisIterator(Object var1) throws UnsupportedAxisException {
        return new AncestorOrSelfAxisIterator(var1, this);
    }

    public Object getDocumentNode(Object var1) {
        return null;
    }

    public String translateNamespacePrefixToUri(String var1, Object var2) {
        return null;
    }

    public String getProcessingInstructionTarget(Object var1) {
        return null;
    }

    public String getProcessingInstructionData(Object var1) {
        return null;
    }

    public short getNodeType(Object var1) {
        if (this.isElement(var1)) {
            return 1;
        } else if (this.isAttribute(var1)) {
            return 2;
        } else if (this.isText(var1)) {
            return 3;
        } else if (this.isComment(var1)) {
            return 8;
        } else if (this.isDocument(var1)) {
            return 9;
        } else if (this.isProcessingInstruction(var1)) {
            return 7;
        } else {
            return (short)(this.isNamespace(var1) ? 13 : 14);
        }
    }

    public Object getParentNode(Object var1) throws UnsupportedAxisException {
        Iterator var2 = this.getParentAxisIterator(var1);
        return var2 != null && var2.hasNext() ? var2.next() : null;
    }

    public Object getDocument(String var1) throws FunctionCallException {
        return null;
    }

    public Object getElementById(Object var1, String var2) {
        return null;
    }
}
