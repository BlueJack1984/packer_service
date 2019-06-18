package nu.xom;

import nu.xom.Attribute.Type;
import nu.xom.jaxen.*;
import nu.xom.jaxen.util.SingleObjectIterator;

import java.util.*;
import java.util.Map.Entry;

class JaxenNavigator extends DefaultNavigator implements NamedAccessNavigator {
    private static final long serialVersionUID = 7008740797833836742L;

    JaxenNavigator() {
    }

    public Iterator getSelfAxisIterator(Object var1) {
        if (var1 instanceof Text) {
            Text var2 = (Text)var1;
            ArrayList var3 = new ArrayList();
            ParentNode var4 = var2.getParent();
            int var5 = var4.indexOf(var2);
            int var6 = var5;

            int var7;
            for(var7 = var5; var6 > 0 && var4.getChild(var6 - 1).isText(); --var6) {
            }

            while(var7 < var4.getChildCount() - 1 && var4.getChild(var7 + 1).isText()) {
                ++var7;
            }

            for(int var8 = var6; var8 <= var7; ++var8) {
                var3.add(var4.getChild(var8));
            }

            var1 = var3;
        }

        return new SingleObjectIterator(var1);
    }

    public Object getElementById(Object var1, String var2) {
        Node var3;
        if (var1 instanceof ArrayList) {
            var3 = (Node)((List)var1).get(0);
        } else {
            var3 = (Node)var1;
        }

        ParentNode var4;
        if (!var3.isElement() && !var3.isDocument()) {
            var4 = var3.getParent();
        } else {
            var4 = (ParentNode)var3;
        }

        ParentNode var5;
        for(var5 = var4; var4 != null; var4 = var4.getParent()) {
            var5 = var4;
        }

        Element var6;
        if (var5.isDocument()) {
            var6 = ((Document)var5).getRootElement();
        } else {
            Node var7 = var5.getChild(0);
            if (!var7.isElement()) {
                return null;
            }

            var6 = (Element)var5.getChild(0);
        }

        return findByID(var6, var2);
    }

    public static Element findByID(Element var0, String var1) {
        if (hasID(var0, var1)) {
            return var0;
        } else {
            Elements var2 = var0.getChildElements();

            for(int var3 = 0; var3 < var2.size(); ++var3) {
                Element var4 = findByID(var2.get(var3), var1);
                if (var4 != null) {
                    return var4;
                }
            }

            return null;
        }
    }

    private static boolean hasID(Element var0, String var1) {
        int var2 = var0.getAttributeCount();

        for(int var3 = 0; var3 < var2; ++var3) {
            Attribute var4 = var0.getAttribute(var3);
            if (Type.ID == var4.getType()) {
                return var4.getValue().trim().equals(var1);
            }
        }

        return false;
    }

    public String getNamespacePrefix(Object var1) {
        Namespace var2 = (Namespace)var1;
        return var2.getPrefix();
    }

    public String getNamespaceStringValue(Object var1) {
        Namespace var2 = (Namespace)var1;
        return var2.getValue();
    }

    public Iterator getNamespaceAxisIterator(Object var1) {
        try {
            Element var2 = (Element)var1;
            Map var3 = var2.getNamespacePrefixesInScope();
            Iterator var4 = var3.entrySet().iterator();
            ArrayList var5 = new ArrayList(var3.size() + 1);
            var5.add(new Namespace("xml", "http://www.w3.org/XML/1998/namespace", var2));

            while(true) {
                String var7;
                String var8;
                do {
                    if (!var4.hasNext()) {
                        return var5.iterator();
                    }

                    Entry var6 = (Entry)var4.next();
                    var7 = (String)var6.getKey();
                    var8 = (String)var6.getValue();
                } while("".equals(var7) && "".equals(var8));

                Namespace var9 = new Namespace(var7, var8, var2);
                var5.add(var9);
            }
        } catch (ClassCastException var10) {
            return JaxenConstants.EMPTY_ITERATOR;
        }
    }

    public Iterator getParentAxisIterator(Object var1) {
        Node var2 = (Node)this.getParentNode(var1);
        return (Iterator)(var2 == null ? JaxenConstants.EMPTY_ITERATOR : new SingleObjectIterator(var2));
    }

    public Object getDocumentNode(Object var1) {
        Node var2 = (Node)var1;
        return var2.getRoot();
    }

    public Object getDocument(String var1) throws FunctionCallException {
        throw new FunctionCallException("document() function not supported");
    }

    public Iterator getAttributeAxisIterator(Object var1) {
        try {
            Element var2 = (Element)var1;
            return var2.attributeIterator();
        } catch (ClassCastException var3) {
            return JaxenConstants.EMPTY_ITERATOR;
        }
    }

    public Iterator getChildAxisIterator(Object var1) {
        return (Iterator)(var1 instanceof ParentNode ? new nu.xom.JaxenNavigator.ChildIterator((ParentNode)var1) : JaxenConstants.EMPTY_ITERATOR);
    }

    public Iterator getFollowingSiblingAxisIterator(Object var1) {
        Node var3;
        if (var1 instanceof ArrayList) {
            ArrayList var2 = (ArrayList)var1;
            var3 = (Node)var2.get(var2.size() - 1);
        } else {
            var3 = (Node)var1;
        }

        ParentNode var5 = var3.getParent();
        if (var5 == null) {
            return JaxenConstants.EMPTY_ITERATOR;
        } else {
            int var4 = var5.indexOf(var3) + 1;
            return new nu.xom.JaxenNavigator.ChildIterator(var5, var4);
        }
    }

    public Object getParentNode(Object var1) {
        Node var2;
        if (var1 instanceof ArrayList) {
            var2 = (Node)((List)var1).get(0);
        } else {
            var2 = (Node)var1;
        }

        return var2.getParent();
    }

    public String getTextStringValue(Object var1) {
        List var2 = (List)var1;
        if (var2.size() == 1) {
            return ((Text)var2.get(0)).getValue();
        } else {
            StringBuffer var3 = new StringBuffer();
            Iterator var4 = var2.iterator();

            while(var4.hasNext()) {
                Text var5 = (Text)var4.next();
                var3.append(var5.getValue());
            }

            return var3.toString();
        }
    }

    public String getElementNamespaceUri(Object var1) {
        return ((Element)var1).getNamespaceURI();
    }

    public String getElementName(Object var1) {
        return ((Element)var1).getLocalName();
    }

    public String getElementQName(Object var1) {
        return ((Element)var1).getQualifiedName();
    }

    public String getAttributeNamespaceUri(Object var1) {
        Attribute var2 = (Attribute)var1;
        return var2.getNamespaceURI();
    }

    public String getAttributeName(Object var1) {
        Attribute var2 = (Attribute)var1;
        return var2.getLocalName();
    }

    public String getAttributeQName(Object var1) {
        Attribute var2 = (Attribute)var1;
        return var2.getQualifiedName();
    }

    public String getProcessingInstructionData(Object var1) {
        ProcessingInstruction var2 = (ProcessingInstruction)var1;
        return var2.getValue();
    }

    public String getProcessingInstructionTarget(Object var1) {
        ProcessingInstruction var2 = (ProcessingInstruction)var1;
        return var2.getTarget();
    }

    public boolean isDocument(Object var1) {
        return var1 instanceof Document || var1 instanceof nu.xom.DocumentFragment;
    }

    public boolean isElement(Object var1) {
        return var1 instanceof Element;
    }

    public boolean isAttribute(Object var1) {
        return var1 instanceof Attribute;
    }

    public boolean isNamespace(Object var1) {
        return var1 instanceof Namespace;
    }

    public boolean isComment(Object var1) {
        return var1 instanceof Comment;
    }

    public boolean isText(Object var1) {
        if (var1 instanceof ArrayList) {
            Iterator var2 = ((List)var1).iterator();

            do {
                if (!var2.hasNext()) {
                    return true;
                }
            } while(var2.next() instanceof Text);

            return false;
        } else {
            return false;
        }
    }

    public boolean isProcessingInstruction(Object var1) {
        return var1 instanceof ProcessingInstruction;
    }

    public String getCommentStringValue(Object var1) {
        return ((Comment)var1).getValue();
    }

    public String getElementStringValue(Object var1) {
        return ((Element)var1).getValue();
    }

    public String getAttributeStringValue(Object var1) {
        return ((Attribute)var1).getValue();
    }

    public XPath parseXPath(String var1) throws JaxenException {
        return new JaxenConnector(var1);
    }

    public Iterator getChildAxisIterator(Object var1, String var2, String var3, String var4) throws UnsupportedAxisException {
        return (Iterator)(var1 instanceof ParentNode ? new nu.xom.JaxenNavigator.NamedChildIterator((ParentNode)var1, var2, var3, var4) : JaxenConstants.EMPTY_ITERATOR);
    }

    public Iterator getAttributeAxisIterator(Object var1, String var2, String var3, String var4) throws UnsupportedAxisException {
        try {
            Element var5 = (Element)var1;
            Attribute var6 = null;
            if (var4 == null) {
                var6 = var5.getAttribute(var2);
            } else {
                var6 = var5.getAttribute(var2, var4);
            }

            return (Iterator)(var6 == null ? JaxenConstants.EMPTY_ITERATOR : new SingleObjectIterator(var6));
        } catch (ClassCastException var7) {
            return JaxenConstants.EMPTY_ITERATOR;
        }
    }

    private static class NamedChildIterator implements Iterator {
        private final ParentNode parent;
        private int index = -1;
        private final int xomCount;
        private Element next;
        private final String localName;
        private final String URI;

        NamedChildIterator(ParentNode var1, String var2, String var3, String var4) {
            this.parent = var1;
            this.xomCount = var1.getChildCount();
            this.localName = var2;
            if (var4 == null) {
                this.URI = "";
            } else {
                this.URI = var4;
            }

            this.findNext();
        }

        private void findNext() {
            while(true) {
                if (++this.index < this.xomCount) {
                    Node var1 = this.parent.getChild(this.index);
                    if (!var1.isElement()) {
                        continue;
                    }

                    Element var2 = (Element)var1;
                    String var3 = var2.getNamespaceURI();
                    if (!var3.equals(this.URI) || !var2.getLocalName().equals(this.localName)) {
                        continue;
                    }

                    this.next = var2;
                    return;
                }

                this.next = null;
                return;
            }
        }

        public boolean hasNext() {
            return this.next != null;
        }

        public Object next() {
            if (this.next == null) {
                throw new NoSuchElementException();
            } else {
                Element var1 = this.next;
                this.findNext();
                return var1;
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private static class ChildIterator implements Iterator {
        private final ParentNode parent;
        private int xomIndex = 0;
        private final int xomCount;

        ChildIterator(ParentNode var1) {
            this.parent = var1;
            this.xomCount = var1.getChildCount();
        }

        ChildIterator(ParentNode var1, int var2) {
            this.parent = var1;
            this.xomIndex = var2;
            this.xomCount = var1.getChildCount();
        }

        public boolean hasNext() {
            for(int var1 = this.xomIndex; var1 < this.xomCount; ++var1) {
                Node var2 = this.parent.getChild(var1);
                if (!var2.isText()) {
                    return true;
                }

                if (!((Text)var2).isEmpty()) {
                    return true;
                }
            }

            return false;
        }

        public Object next() {
            Node var1 = this.parent.getChild(this.xomIndex++);
            Object var6;
            if (var1.isText()) {
                Text var2 = (Text)var1;
                boolean var3 = var2.isEmpty();
                ArrayList var4 = new ArrayList(1);
                var4.add(var2);

                while(this.xomIndex < this.xomCount) {
                    Node var5 = this.parent.getChild(this.xomIndex);
                    if (!var5.isText()) {
                        break;
                    }

                    ++this.xomIndex;
                    var4.add(var5);
                    if (var3 && !((Text)var5).isEmpty()) {
                        var3 = false;
                    }
                }

                if (var3) {
                    return this.next();
                }

                var6 = var4;
            } else {
                if (var1.isDocType()) {
                    return this.next();
                }

                var6 = var1;
            }

            return var6;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
