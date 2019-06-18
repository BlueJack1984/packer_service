package nu.xom.jaxen.saxpath;


import nu.xom.jaxen.JaxenRuntimeException;

public class Axis {
    public static final int INVALID_AXIS = 0;
    public static final int CHILD = 1;
    public static final int DESCENDANT = 2;
    public static final int PARENT = 3;
    public static final int ANCESTOR = 4;
    public static final int FOLLOWING_SIBLING = 5;
    public static final int PRECEDING_SIBLING = 6;
    public static final int FOLLOWING = 7;
    public static final int PRECEDING = 8;
    public static final int ATTRIBUTE = 9;
    public static final int NAMESPACE = 10;
    public static final int SELF = 11;
    public static final int DESCENDANT_OR_SELF = 12;
    public static final int ANCESTOR_OR_SELF = 13;

    private Axis() {
    }

    public static String lookup(int var0) {
        switch(var0) {
            case 1:
                return "child";
            case 2:
                return "descendant";
            case 3:
                return "parent";
            case 4:
                return "ancestor";
            case 5:
                return "following-sibling";
            case 6:
                return "preceding-sibling";
            case 7:
                return "following";
            case 8:
                return "preceding";
            case 9:
                return "attribute";
            case 10:
                return "namespace";
            case 11:
                return "self";
            case 12:
                return "descendant-or-self";
            case 13:
                return "ancestor-or-self";
            default:
                throw new JaxenRuntimeException("Illegal Axis Number");
        }
    }

    public static int lookup(String var0) {
        if ("child".equals(var0)) {
            return 1;
        } else if ("descendant".equals(var0)) {
            return 2;
        } else if ("parent".equals(var0)) {
            return 3;
        } else if ("ancestor".equals(var0)) {
            return 4;
        } else if ("following-sibling".equals(var0)) {
            return 5;
        } else if ("preceding-sibling".equals(var0)) {
            return 6;
        } else if ("following".equals(var0)) {
            return 7;
        } else if ("preceding".equals(var0)) {
            return 8;
        } else if ("attribute".equals(var0)) {
            return 9;
        } else if ("namespace".equals(var0)) {
            return 10;
        } else if ("self".equals(var0)) {
            return 11;
        } else if ("descendant-or-self".equals(var0)) {
            return 12;
        } else {
            return "ancestor-or-self".equals(var0) ? 13 : 0;
        }
    }
}
