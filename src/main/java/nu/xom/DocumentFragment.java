package nu.xom;

class DocumentFragment extends ParentNode {
    DocumentFragment() {
    }

    void insertionAllowed(Node var1, int var2) {
    }

    public void setBaseURI(String var1) {
        throw new UnsupportedOperationException("XOM bug");
    }

    public String getValue() {
        throw new UnsupportedOperationException("XOM bug");
    }

    public Node copy() {
        throw new UnsupportedOperationException("XOM bug");
    }

    public String toXML() {
        throw new UnsupportedOperationException("XOM bug");
    }

    boolean isDocumentFragment() {
        return true;
    }
}
