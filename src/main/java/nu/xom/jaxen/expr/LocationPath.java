package nu.xom.jaxen.expr;

import java.util.List;

public interface LocationPath extends Expr {
    void addStep(Step var1);

    List getSteps();

    boolean isAbsolute();
}
