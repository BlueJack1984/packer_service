package nu.xom.jaxen.saxpath;

public interface XPathHandler {
    void startXPath() throws SAXPathException;

    void endXPath() throws SAXPathException;

    void startPathExpr() throws SAXPathException;

    void endPathExpr() throws SAXPathException;

    void startAbsoluteLocationPath() throws SAXPathException;

    void endAbsoluteLocationPath() throws SAXPathException;

    void startRelativeLocationPath() throws SAXPathException;

    void endRelativeLocationPath() throws SAXPathException;

    void startNameStep(int var1, String var2, String var3) throws SAXPathException;

    void endNameStep() throws SAXPathException;

    void startTextNodeStep(int var1) throws SAXPathException;

    void endTextNodeStep() throws SAXPathException;

    void startCommentNodeStep(int var1) throws SAXPathException;

    void endCommentNodeStep() throws SAXPathException;

    void startAllNodeStep(int var1) throws SAXPathException;

    void endAllNodeStep() throws SAXPathException;

    void startProcessingInstructionNodeStep(int var1, String var2) throws SAXPathException;

    void endProcessingInstructionNodeStep() throws SAXPathException;

    void startPredicate() throws SAXPathException;

    void endPredicate() throws SAXPathException;

    void startFilterExpr() throws SAXPathException;

    void endFilterExpr() throws SAXPathException;

    void startOrExpr() throws SAXPathException;

    void endOrExpr(boolean var1) throws SAXPathException;

    void startAndExpr() throws SAXPathException;

    void endAndExpr(boolean var1) throws SAXPathException;

    void startEqualityExpr() throws SAXPathException;

    void endEqualityExpr(int var1) throws SAXPathException;

    void startRelationalExpr() throws SAXPathException;

    void endRelationalExpr(int var1) throws SAXPathException;

    void startAdditiveExpr() throws SAXPathException;

    void endAdditiveExpr(int var1) throws SAXPathException;

    void startMultiplicativeExpr() throws SAXPathException;

    void endMultiplicativeExpr(int var1) throws SAXPathException;

    void startUnaryExpr() throws SAXPathException;

    void endUnaryExpr(int var1) throws SAXPathException;

    void startUnionExpr() throws SAXPathException;

    void endUnionExpr(boolean var1) throws SAXPathException;

    void number(int var1) throws SAXPathException;

    void number(double var1) throws SAXPathException;

    void literal(String var1) throws SAXPathException;

    void variableReference(String var1, String var2) throws SAXPathException;

    void startFunction(String var1, String var2) throws SAXPathException;

    void endFunction() throws SAXPathException;
}
