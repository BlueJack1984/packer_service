package nu.xom.jaxen;


public class XPathSyntaxException extends JaxenException {
    private static final long serialVersionUID = 1980601567207604059L;
    private String xpath;
    private int position;

    public XPathSyntaxException(nu.xom.jaxen.saxpath.XPathSyntaxException var1) {
        super(var1);
        this.xpath = var1.getXPath();
        this.position = var1.getPosition();
    }

    public XPathSyntaxException(String var1, int var2, String var3) {
        super(var3);
        this.xpath = var1;
        this.position = var2;
    }

    public int getPosition() {
        return this.position;
    }

    public String getXPath() {
        return this.xpath;
    }

    public String getPositionMarker() {
        StringBuffer var1 = new StringBuffer();
        int var2 = this.getPosition();

        for(int var3 = 0; var3 < var2; ++var3) {
            var1.append(" ");
        }

        var1.append("^");
        return var1.toString();
    }

    public String getMultilineMessage() {
        StringBuffer var1 = new StringBuffer(this.getMessage());
        var1.append("\n");
        var1.append(this.getXPath());
        var1.append("\n");
        var1.append(this.getPositionMarker());
        return var1.toString();
    }
}

