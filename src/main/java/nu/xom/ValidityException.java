package nu.xom;


import org.xml.sax.SAXParseException;

import java.util.ArrayList;
import java.util.List;

public class ValidityException extends ParsingException {
    private static final long serialVersionUID = 8950434465665278751L;
    private List saxExceptions = new ArrayList();
    private transient Document document;

    public ValidityException(String var1, Throwable var2) {
        super(var1, var2);
    }

    public ValidityException(String var1, int var2, int var3) {
        super(var1, var2, var3);
    }

    public ValidityException(String var1, int var2, int var3, Throwable var4) {
        super(var1, var2, var3, var4);
    }

    public ValidityException(String var1, String var2, int var3, int var4) {
        super(var1, var2, var3, var4);
    }

    public ValidityException(String var1, String var2, int var3, int var4, Throwable var5) {
        super(var1, var2, var3, var4, var5);
    }

    public ValidityException(String var1) {
        super(var1);
    }

    public Document getDocument() {
        return this.document;
    }

    void setDocument(Document var1) {
        this.document = var1;
    }

    void addError(SAXParseException var1) {
        this.saxExceptions.add(var1);
    }

    public int getErrorCount() {
        return this.saxExceptions.size();
    }

    public String getValidityError(int var1) {
        Exception var2 = (Exception)this.saxExceptions.get(var1);
        return var2.getMessage();
    }

    public int getLineNumber(int var1) {
        SAXParseException var2 = (SAXParseException)this.saxExceptions.get(var1);
        return var2.getLineNumber();
    }

    public int getColumnNumber(int var1) {
        SAXParseException var2 = (SAXParseException)this.saxExceptions.get(var1);
        return var2.getColumnNumber();
    }
}
