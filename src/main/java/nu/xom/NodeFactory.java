package nu.xom;


import nu.xom.Attribute.Type;

public class NodeFactory {
    public NodeFactory() {
    }

    public Element makeRootElement(String var1, String var2) {
        return this.startMakingElement(var1, var2);
    }

    public Element startMakingElement(String var1, String var2) {
        return new Element(var1, var2);
    }

    public Nodes finishMakingElement(Element var1) {
        return new Nodes(var1);
    }

    public Document startMakingDocument() {
        return new Document(Element.build("root", "http://www.xom.nu/fakeRoot", "root"));
    }

    public void finishMakingDocument(Document var1) {
    }

    public Nodes makeAttribute(String var1, String var2, String var3, Type var4) {
        return new Nodes(new Attribute(var1, var2, var3, var4));
    }

    public Nodes makeComment(String var1) {
        return new Nodes(new Comment(var1));
    }

    public Nodes makeDocType(String var1, String var2, String var3) {
        return new Nodes(new DocType(var1, var2, var3));
    }

    public Nodes makeText(String var1) {
        return new Nodes(new Text(var1));
    }

    Nodes makeCDATASection(String var1) {
        return this.makeText(var1);
    }

    public Nodes makeProcessingInstruction(String var1, String var2) {
        return new Nodes(new ProcessingInstruction(var1, var2));
    }

    void addAttribute(Element var1, Attribute var2) {
        var1.addAttribute(var2);
    }

    void insertChild(Element var1, Node var2, int var3) {
        var1.insertChild(var2, var3);
    }
}
