package nu.xom;


public class Document extends ParentNode {
    public Document(Element var1) {
        this._insertChild(var1, 0);
    }

    public Document(nu.xom.Document var1) {
        this.insertChild(var1.getRootElement().copy(), 0);
        int var2 = var1.getChildCount();

        for(int var3 = 0; var3 < var2; ++var3) {
            Node var4 = var1.getChild(var3);
            if (!var4.isElement()) {
                this.insertChild(var4.copy(), var3);
            }
        }

        this.actualBaseURI = var1.actualBaseURI;
    }

    final void insertionAllowed(Node var1, int var2) {
        if (var1 == null) {
            throw new NullPointerException("Tried to insert a null child in the document");
        } else if (var1.getParent() != null) {
            throw new MultipleParentException("Child already has a parent.");
        } else if (!var1.isComment() && !var1.isProcessingInstruction()) {
            if (var1.isDocType()) {
                if (var2 <= this.getRootPosition()) {
                    DocType var3 = this.getDocType();
                    if (var3 != null) {
                        throw new IllegalAddException("Tried to insert a second DOCTYPE");
                    }
                } else {
                    throw new IllegalAddException("Cannot add a document type declaration after the root element");
                }
            } else if (var1.isElement()) {
                if (this.getChildCount() != 0) {
                    throw new IllegalAddException("Cannot add a second root element to a Document.");
                }
            } else {
                throw new IllegalAddException("Cannot add a " + var1.getClass().getName() + " to a Document.");
            }
        }
    }

    private int getRootPosition() {
        int var1 = 0;

        while(true) {
            Node var2 = this.getChild(var1);
            if (var2.isElement()) {
                return var1;
            }

            ++var1;
        }
    }

    public final DocType getDocType() {
        for(int var1 = 0; var1 < this.getChildCount(); ++var1) {
            Node var2 = this.getChild(var1);
            if (var2.isDocType()) {
                return (DocType)var2;
            }
        }

        return null;
    }

    public void setDocType(DocType var1) {
        DocType var2 = this.getDocType();
        if (var1 == null) {
            throw new NullPointerException("Null DocType");
        } else if (var1 != var2) {
            if (var1.getParent() != null) {
                throw new MultipleParentException("DocType belongs to another document");
            } else {
                if (var2 == null) {
                    this.insertChild(var1, 0);
                } else {
                    int var3 = this.indexOf(var2);
                    super.removeChild(var3);
                    this.fastInsertChild(var1, var3);
                    var2.setParent((ParentNode)null);
                    var1.setParent(this);
                }

            }
        }
    }

    public final Element getRootElement() {
        int var1 = 0;

        while(true) {
            Node var2 = this.getChild(var1);
            if (var2.isElement()) {
                return (Element)var2;
            }

            ++var1;
        }
    }

    public void setRootElement(Element var1) {
        Element var2 = this.getRootElement();
        if (var1 != var2) {
            if (var1 == null) {
                throw new NullPointerException("Root element cannot be null");
            } else if (var1.getParent() != null) {
                throw new MultipleParentException(var1.getQualifiedName() + " already has a parent");
            } else {
                this.fillInBaseURI(var2);
                int var3 = this.indexOf(var2);
                var2.setParent((ParentNode)null);
                this.children[var3] = var1;
                var1.setParent(this);
            }
        }
    }

    public void setBaseURI(String var1) {
        this.setActualBaseURI(var1);
    }

    public final String getBaseURI() {
        return this.getActualBaseURI();
    }

    public Node removeChild(int var1) {
        if (var1 == this.getRootPosition()) {
            throw new WellformednessException("Cannot remove the root element");
        } else {
            return super.removeChild(var1);
        }
    }

    public Node removeChild(Node var1) {
        if (var1 == this.getRootElement()) {
            throw new WellformednessException("Cannot remove the root element");
        } else {
            return super.removeChild(var1);
        }
    }

    public void replaceChild(Node var1, Node var2) {
        if (var1 == this.getRootElement() && var2 != null && var2.isElement()) {
            this.setRootElement((Element)var2);
        } else if (var1 == this.getDocType() && var2 != null && var2.isDocType()) {
            this.setDocType((DocType)var2);
        } else {
            super.replaceChild(var1, var2);
        }

    }

    public final String getValue() {
        return this.getRootElement().getValue();
    }

    public final String toXML() {
        StringBuffer var1 = new StringBuffer(64);
        var1.append("<?xml version=\"1.0\"?>\n");

        for(int var2 = 0; var2 < this.getChildCount(); ++var2) {
            var1.append(this.getChild(var2).toXML());
            var1.append("\n");
        }

        return var1.toString();
    }

    public Node copy() {
        return new nu.xom.Document(this);
    }

    boolean isDocument() {
        return true;
    }

    public final String toString() {
        return "[" + this.getClass().getName() + ": " + this.getRootElement().getQualifiedName() + "]";
    }
}
