package nu.xom;


import org.xml.sax.Attributes;

class NonVerifyingHandler extends nu.xom.XOMHandler {
    NonVerifyingHandler(NodeFactory var1) {
        super(var1);
    }

    public void startElement(String var1, String var2, String var3, Attributes var4) {
        this.flushText();
        Element var5 = Element.build(var3, var1, var2);
        if (this.parent == this.document) {
            this.document.setRootElement(var5);
            this.inProlog = false;
        }

        this.current = var5;
        this.parents.add(var5);
        if (this.parent != this.document) {
            this.parent.fastInsertChild(var5, this.parent.getChildCount());
        }

        String var6 = this.locator.getSystemId();
        if (var6 != null && !var6.equals(this.documentBaseURI)) {
            var5.setActualBaseURI(var6);
        }

        int var7 = var4.getLength();

        int var8;
        String var9;
        String var10;
        String var11;
        for(var8 = 0; var8 < var7; ++var8) {
            var9 = var4.getQName(var8);
            if (!var9.startsWith("xmlns:") && !var9.equals("xmlns")) {
                var10 = var4.getURI(var8);
                var11 = var4.getValue(var8);
                Attribute var12 = Attribute.build(var9, var10, var11, convertStringToType(var4.getType(var8)), var4.getLocalName(var8));
                var5.fastAddAttribute(var12);
            }
        }

        for(var8 = 0; var8 < var7; ++var8) {
            var9 = var4.getQName(var8);
            String var13;
            if (var9.startsWith("xmlns:")) {
                var10 = var4.getValue(var8);
                var11 = var9.substring(6);
                var13 = var5.getNamespaceURI(var11);
                if (!var10.equals(var13)) {
                    var5.addNamespaceDeclaration(var11, var10);
                }
            } else if (var9.equals("xmlns")) {
                var10 = var4.getValue(var8);
                var11 = "";
                var13 = var5.getNamespaceURI(var11);
                if (!var10.equals(var13)) {
                    var5.addNamespaceDeclaration(var11, var10);
                }
            }
        }

        this.parent = var5;
    }

    public void endElement(String var1, String var2, String var3) {
        this.current = (ParentNode)this.parents.remove(this.parents.size() - 1);
        this.flushText();
        this.parent = this.current.getParent();
        if (this.parent.isDocument()) {
            Document var4 = (Document)this.parent;
            var4.setRootElement((Element)this.current);
        }

    }

    private void flushText() {
        if (this.buffer != null) {
            this.textString = this.buffer.toString();
            this.buffer = null;
        }

        if (this.textString != null) {
            Text var1;
            if (!this.inCDATA) {
                var1 = Text.build(this.textString);
            } else {
                var1 = nu.xom.CDATASection.build(this.textString);
            }

            this.parent.fastInsertChild(var1, this.parent.getChildCount());
            this.textString = null;
        }

        this.inCDATA = false;
        this.finishedCDATA = false;
    }

    public void processingInstruction(String var1, String var2) {
        if (!this.inDTD) {
            this.flushText();
        } else if (!this.inInternalSubset()) {
            return;
        }

        ProcessingInstruction var3 = ProcessingInstruction.build(var1, var2);
        if (!this.inDTD) {
            if (this.inProlog) {
                this.parent.fastInsertChild(var3, this.position);
                ++this.position;
            } else {
                this.parent.fastInsertChild(var3, this.parent.getChildCount());
            }
        } else {
            this.internalDTDSubset.append("  ");
            this.internalDTDSubset.append(var3.toXML());
            this.internalDTDSubset.append("\n");
        }

    }

    public void startDTD(String var1, String var2, String var3) {
        this.inDTD = true;
        DocType var4 = DocType.build(var1, var2, var3);
        this.document.fastInsertChild(var4, this.position);
        ++this.position;
        this.internalDTDSubset = new StringBuffer();
        this.doctype = var4;
    }

    public void comment(char[] var1, int var2, int var3) {
        if (!this.inDTD) {
            this.flushText();
        } else if (!this.inInternalSubset()) {
            return;
        }

        Comment var4 = Comment.build(new String(var1, var2, var3));
        if (!this.inDTD) {
            if (this.inProlog) {
                this.parent.insertChild(var4, this.position);
                ++this.position;
            } else {
                this.parent.fastInsertChild(var4, this.parent.getChildCount());
            }
        } else {
            this.internalDTDSubset.append("  ");
            this.internalDTDSubset.append(var4.toXML());
            this.internalDTDSubset.append("\n");
        }

    }

    public void endDTD() {
        this.inDTD = false;
        if (this.doctype != null) {
            this.doctype.fastSetInternalDTDSubset(this.internalDTDSubset.toString());
        }

    }
}
