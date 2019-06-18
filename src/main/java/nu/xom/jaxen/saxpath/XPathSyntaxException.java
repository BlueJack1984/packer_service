package nu.xom.jaxen.saxpath;


public class XPathSyntaxException extends SAXPathException {
    private static final long serialVersionUID = 3567675610742422397L;
    private String xpath;
    private int position;
    private static final String lineSeparator = System.getProperty("line.separator");

    public XPathSyntaxException(String var1, int var2, String var3) {
        super(var3);
        this.position = var2;
        this.xpath = var1;
    }

    public int getPosition() {
        return this.position;
    }

    public String getXPath() {
        return this.xpath;
    }

    public String toString() {
        return this.getClass() + ": " + this.getXPath() + ": " + this.getPosition() + ": " + this.getMessage();
    }

    private String getPositionMarker() {
        int var1 = this.getPosition();
        StringBuffer var2 = new StringBuffer(var1 + 1);

        for(int var3 = 0; var3 < var1; ++var3) {
            var2.append(" ");
        }

        var2.append("^");
        return var2.toString();
    }

    public String getMultilineMessage() {
        StringBuffer var1 = new StringBuffer();
        var1.append(this.getMessage());
        var1.append(lineSeparator);
        var1.append(this.getXPath());
        var1.append(lineSeparator);
        var1.append(this.getPositionMarker());
        return var1.toString();
    }
}
