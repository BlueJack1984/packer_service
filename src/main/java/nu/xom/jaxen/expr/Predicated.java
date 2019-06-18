package nu.xom.jaxen.expr;


import java.io.Serializable;
import java.util.List;

public interface Predicated extends Serializable {
    void addPredicate(Predicate var1);

    List getPredicates();

    PredicateSet getPredicateSet();
}
