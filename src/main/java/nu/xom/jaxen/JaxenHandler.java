package nu.xom.jaxen;


import nu.xom.jaxen.expr.*;
import nu.xom.jaxen.saxpath.XPathHandler;

import java.util.Iterator;
import java.util.LinkedList;

public class JaxenHandler implements XPathHandler {
    private XPathFactory xpathFactory = new DefaultXPathFactory();
    private XPathExpr xpath;
    protected boolean simplified;
    protected LinkedList stack = new LinkedList();

    public JaxenHandler() {
    }

    public void setXPathFactory(XPathFactory var1) {
        this.xpathFactory = var1;
    }

    public XPathFactory getXPathFactory() {
        return this.xpathFactory;
    }

    public XPathExpr getXPathExpr() {
        return this.getXPathExpr(true);
    }

    public XPathExpr getXPathExpr(boolean var1) {
        if (var1 && !this.simplified) {
            this.xpath.simplify();
            this.simplified = true;
        }

        return this.xpath;
    }

    public void startXPath() {
        this.simplified = false;
        this.pushFrame();
    }

    public void endXPath() throws JaxenException {
        this.xpath = this.getXPathFactory().createXPath((Expr)this.pop());
        this.popFrame();
    }

    public void startPathExpr() {
        this.pushFrame();
    }

    public void endPathExpr() throws JaxenException {
        LocationPath var1;
        FilterExpr var2;
        if (this.stackSize() == 2) {
            var1 = (LocationPath)this.pop();
            var2 = (FilterExpr)this.pop();
        } else {
            Object var3 = this.pop();
            if (var3 instanceof LocationPath) {
                var1 = (LocationPath)var3;
                var2 = null;
            } else {
                var1 = null;
                var2 = (FilterExpr)var3;
            }
        }

        this.popFrame();
        this.push(this.getXPathFactory().createPathExpr(var2, var1));
    }

    public void startAbsoluteLocationPath() throws JaxenException {
        this.pushFrame();
        this.push(this.getXPathFactory().createAbsoluteLocationPath());
    }

    public void endAbsoluteLocationPath() throws JaxenException {
        this.endLocationPath();
    }

    public void startRelativeLocationPath() throws JaxenException {
        this.pushFrame();
        this.push(this.getXPathFactory().createRelativeLocationPath());
    }

    public void endRelativeLocationPath() throws JaxenException {
        this.endLocationPath();
    }

    protected void endLocationPath() throws JaxenException {
        LocationPath var1 = (LocationPath)this.peekFrame().removeFirst();
        this.addSteps(var1, this.popFrame().iterator());
        this.push(var1);
    }

    protected void addSteps(LocationPath var1, Iterator var2) {
        while(var2.hasNext()) {
            var1.addStep((Step)var2.next());
        }

    }

    public void startNameStep(int var1, String var2, String var3) throws JaxenException {
        this.pushFrame();
        this.push(this.getXPathFactory().createNameStep(var1, var2, var3));
    }

    public void endNameStep() {
        this.endStep();
    }

    public void startTextNodeStep(int var1) throws JaxenException {
        this.pushFrame();
        this.push(this.getXPathFactory().createTextNodeStep(var1));
    }

    public void endTextNodeStep() {
        this.endStep();
    }

    public void startCommentNodeStep(int var1) throws JaxenException {
        this.pushFrame();
        this.push(this.getXPathFactory().createCommentNodeStep(var1));
    }

    public void endCommentNodeStep() {
        this.endStep();
    }

    public void startAllNodeStep(int var1) throws JaxenException {
        this.pushFrame();
        this.push(this.getXPathFactory().createAllNodeStep(var1));
    }

    public void endAllNodeStep() {
        this.endStep();
    }

    public void startProcessingInstructionNodeStep(int var1, String var2) throws JaxenException {
        this.pushFrame();
        this.push(this.getXPathFactory().createProcessingInstructionNodeStep(var1, var2));
    }

    public void endProcessingInstructionNodeStep() {
        this.endStep();
    }

    protected void endStep() {
        Step var1 = (Step)this.peekFrame().removeFirst();
        this.addPredicates(var1, this.popFrame().iterator());
        this.push(var1);
    }

    public void startPredicate() {
        this.pushFrame();
    }

    public void endPredicate() throws JaxenException {
        Predicate var1 = this.getXPathFactory().createPredicate((Expr)this.pop());
        this.popFrame();
        this.push(var1);
    }

    public void startFilterExpr() {
        this.pushFrame();
    }

    public void endFilterExpr() throws JaxenException {
        Expr var1 = (Expr)this.peekFrame().removeFirst();
        FilterExpr var2 = this.getXPathFactory().createFilterExpr(var1);
        Iterator var3 = this.popFrame().iterator();
        this.addPredicates(var2, var3);
        this.push(var2);
    }

    protected void addPredicates(Predicated var1, Iterator var2) {
        while(var2.hasNext()) {
            var1.addPredicate((Predicate)var2.next());
        }

    }

    protected void returnExpr() {
        Expr var1 = (Expr)this.pop();
        this.popFrame();
        this.push(var1);
    }

    public void startOrExpr() {
    }

    public void endOrExpr(boolean var1) throws JaxenException {
        if (var1) {
            Expr var2 = (Expr)this.pop();
            Expr var3 = (Expr)this.pop();
            this.push(this.getXPathFactory().createOrExpr(var3, var2));
        }

    }

    public void startAndExpr() {
    }

    public void endAndExpr(boolean var1) throws JaxenException {
        if (var1) {
            Expr var2 = (Expr)this.pop();
            Expr var3 = (Expr)this.pop();
            this.push(this.getXPathFactory().createAndExpr(var3, var2));
        }

    }

    public void startEqualityExpr() {
    }

    public void endEqualityExpr(int var1) throws JaxenException {
        if (var1 != 0) {
            Expr var2 = (Expr)this.pop();
            Expr var3 = (Expr)this.pop();
            this.push(this.getXPathFactory().createEqualityExpr(var3, var2, var1));
        }

    }

    public void startRelationalExpr() {
    }

    public void endRelationalExpr(int var1) throws JaxenException {
        if (var1 != 0) {
            Expr var2 = (Expr)this.pop();
            Expr var3 = (Expr)this.pop();
            this.push(this.getXPathFactory().createRelationalExpr(var3, var2, var1));
        }

    }

    public void startAdditiveExpr() {
    }

    public void endAdditiveExpr(int var1) throws JaxenException {
        if (var1 != 0) {
            Expr var2 = (Expr)this.pop();
            Expr var3 = (Expr)this.pop();
            this.push(this.getXPathFactory().createAdditiveExpr(var3, var2, var1));
        }

    }

    public void startMultiplicativeExpr() {
    }

    public void endMultiplicativeExpr(int var1) throws JaxenException {
        if (var1 != 0) {
            Expr var2 = (Expr)this.pop();
            Expr var3 = (Expr)this.pop();
            this.push(this.getXPathFactory().createMultiplicativeExpr(var3, var2, var1));
        }

    }

    public void startUnaryExpr() {
    }

    public void endUnaryExpr(int var1) throws JaxenException {
        if (var1 != 0) {
            this.push(this.getXPathFactory().createUnaryExpr((Expr)this.pop(), var1));
        }

    }

    public void startUnionExpr() {
    }

    public void endUnionExpr(boolean var1) throws JaxenException {
        if (var1) {
            Expr var2 = (Expr)this.pop();
            Expr var3 = (Expr)this.pop();
            this.push(this.getXPathFactory().createUnionExpr(var3, var2));
        }

    }

    public void number(int var1) throws JaxenException {
        this.push(this.getXPathFactory().createNumberExpr(var1));
    }

    public void number(double var1) throws JaxenException {
        this.push(this.getXPathFactory().createNumberExpr(var1));
    }

    public void literal(String var1) throws JaxenException {
        this.push(this.getXPathFactory().createLiteralExpr(var1));
    }

    public void variableReference(String var1, String var2) throws JaxenException {
        this.push(this.getXPathFactory().createVariableReferenceExpr(var1, var2));
    }

    public void startFunction(String var1, String var2) throws JaxenException {
        this.pushFrame();
        this.push(this.getXPathFactory().createFunctionCallExpr(var1, var2));
    }

    public void endFunction() {
        FunctionCallExpr var1 = (FunctionCallExpr)this.peekFrame().removeFirst();
        this.addParameters(var1, this.popFrame().iterator());
        this.push(var1);
    }

    protected void addParameters(FunctionCallExpr var1, Iterator var2) {
        while(var2.hasNext()) {
            var1.addParameter((Expr)var2.next());
        }

    }

    protected int stackSize() {
        return this.peekFrame().size();
    }

    protected void push(Object var1) {
        this.peekFrame().addLast(var1);
    }

    protected Object pop() {
        return this.peekFrame().removeLast();
    }

    protected boolean canPop() {
        return this.peekFrame().size() > 0;
    }

    protected void pushFrame() {
        this.stack.addLast(new LinkedList());
    }

    protected LinkedList popFrame() {
        return (LinkedList)this.stack.removeLast();
    }

    protected LinkedList peekFrame() {
        return (LinkedList)this.stack.getLast();
    }
}
