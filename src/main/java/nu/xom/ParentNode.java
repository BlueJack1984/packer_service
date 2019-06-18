package nu.xom;

public abstract class ParentNode extends Node {
    Node[] children;
    int childCount = 0;
    String actualBaseURI;

    ParentNode() {
    }

    public int getChildCount() {
        return this.childCount;
    }

    public void insertChild(Node var1, int var2) {
        this._insertChild(var1, var2);
    }

    final void _insertChild(Node var1, int var2) {
        this.insertionAllowed(var1, var2);
        this.fastInsertChild(var1, var2);
    }

    void fastInsertChild(Node var1, int var2) {
        if (var2 > this.childCount) {
            throw new IndexOutOfBoundsException("Inserted node at position " + var2 + " after children");
        } else {
            this.checkCapacity(this.childCount + 1);
            if (var2 < this.childCount) {
                System.arraycopy(this.children, var2, this.children, var2 + 1, this.childCount - var2);
            }

            this.children[var2] = var1;
            ++this.childCount;
            var1.setParent(this);
        }
    }

    private void checkCapacity(int var1) {
        if (this.children == null) {
            this.children = new Node[1];
        } else if (var1 >= this.children.length) {
            Node[] var2 = new Node[this.children.length * 2];
            System.arraycopy(this.children, 0, var2, 0, this.children.length);
            this.children = var2;
        }

    }

    abstract void insertionAllowed(Node var1, int var2);

    public void appendChild(Node var1) {
        this.insertChild(var1, this.childCount);
    }

    public Node getChild(int var1) {
        if (this.children == null) {
            throw new IndexOutOfBoundsException("This node has no children");
        } else {
            return this.children[var1];
        }
    }

    public int indexOf(Node var1) {
        if (this.children == null) {
            return -1;
        } else {
            for(int var2 = 0; var2 < this.childCount; ++var2) {
                if (var1 == this.children[var2]) {
                    return var2;
                }
            }

            return -1;
        }
    }

    public Node removeChild(int var1) {
        if (this.children == null) {
            throw new IndexOutOfBoundsException("This node has no children");
        } else {
            Node var2 = this.children[var1];
            if (var2.isElement()) {
                this.fillInBaseURI((Element)var2);
            }

            int var3 = this.childCount - var1 - 1;
            if (var3 > 0) {
                System.arraycopy(this.children, var1 + 1, this.children, var1, var3);
            }

            --this.childCount;
            this.children[this.childCount] = null;
            var2.setParent((nu.xom.ParentNode)null);
            return var2;
        }
    }

    void fillInBaseURI(Element var1) {
        Object var2 = var1;

        String var3;
        for(var3 = ""; var2 != null && var3.equals(""); var2 = ((nu.xom.ParentNode)var2).getParent()) {
            var3 = ((nu.xom.ParentNode)var2).getActualBaseURI();
        }

        var1.setActualBaseURI(var3);
    }

    public Node removeChild(Node var1) {
        if (this.children == null) {
            throw new NoSuchChildException("Child does not belong to this node");
        } else {
            int var2 = this.indexOf(var1);
            if (var2 == -1) {
                throw new NoSuchChildException("Child does not belong to this node");
            } else {
                if (var1.isElement()) {
                    this.fillInBaseURI((Element)var1);
                }

                this.removeChild(var2);
                var1.setParent((nu.xom.ParentNode)null);
                return var1;
            }
        }
    }

    public void replaceChild(Node var1, Node var2) {
        if (var1 == null) {
            throw new NullPointerException("Tried to replace null child");
        } else if (var2 == null) {
            throw new NullPointerException("Tried to replace child with null");
        } else if (this.children == null) {
            throw new NoSuchChildException("Reference node is not a child of this node.");
        } else {
            int var3 = this.indexOf(var1);
            if (var3 == -1) {
                throw new NoSuchChildException("Reference node is not a child of this node.");
            } else if (var1 != var2) {
                this.insertionAllowed(var2, var3);
                this.removeChild(var3);
                this.insertChild(var2, var3);
            }
        }
    }

    public abstract void setBaseURI(String var1);

    String getActualBaseURI() {
        return this.actualBaseURI == null ? "" : this.actualBaseURI;
    }

    void setActualBaseURI(String var1) {
        if (var1 == null) {
            var1 = "";
        }

        if (!"".equals(var1)) {
            Verifier.checkAbsoluteURI(var1);
        }

        this.actualBaseURI = var1;
    }

    final String findActualBaseURI() {
        nu.xom.ParentNode var1 = this;

        while(true) {
            String var2 = var1.getActualBaseURI();
            nu.xom.ParentNode var3 = var1.getParent();
            if (var3 == null) {
                return var2;
            }

            if (!"".equals(var2)) {
                return var2;
            }

            var1 = var3;
        }
    }
}
