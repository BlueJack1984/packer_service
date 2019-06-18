package nu.xom.jaxen;


import nu.xom.jaxen.function.*;
import nu.xom.jaxen.function.ext.EndsWithFunction;
import nu.xom.jaxen.function.ext.EvaluateFunction;
import nu.xom.jaxen.function.ext.LowerFunction;
import nu.xom.jaxen.function.ext.UpperFunction;
import nu.xom.jaxen.function.xslt.DocumentFunction;

public class XPathFunctionContext extends SimpleFunctionContext {
    private static nu.xom.jaxen.XPathFunctionContext instance = new nu.xom.jaxen.XPathFunctionContext();

    public static FunctionContext getInstance() {
        return instance;
    }

    public XPathFunctionContext() {
        this(true);
    }

    public XPathFunctionContext(boolean var1) {
        this.registerXPathFunctions();
        if (var1) {
            this.registerXSLTFunctions();
            this.registerExtensionFunctions();
        }

    }

    private void registerXPathFunctions() {
        this.registerFunction((String)null, "boolean", new BooleanFunction());
        this.registerFunction((String)null, "ceiling", new CeilingFunction());
        this.registerFunction((String)null, "concat", new ConcatFunction());
        this.registerFunction((String)null, "contains", new ContainsFunction());
        this.registerFunction((String)null, "count", new CountFunction());
        this.registerFunction((String)null, "false", new FalseFunction());
        this.registerFunction((String)null, "floor", new FloorFunction());
        this.registerFunction((String)null, "id", new IdFunction());
        this.registerFunction((String)null, "lang", new LangFunction());
        this.registerFunction((String)null, "last", new LastFunction());
        this.registerFunction((String)null, "local-name", new LocalNameFunction());
        this.registerFunction((String)null, "name", new NameFunction());
        this.registerFunction((String)null, "namespace-uri", new NamespaceUriFunction());
        this.registerFunction((String)null, "normalize-space", new NormalizeSpaceFunction());
        this.registerFunction((String)null, "not", new NotFunction());
        this.registerFunction((String)null, "number", new NumberFunction());
        this.registerFunction((String)null, "position", new PositionFunction());
        this.registerFunction((String)null, "round", new RoundFunction());
        this.registerFunction((String)null, "starts-with", new StartsWithFunction());
        this.registerFunction((String)null, "string", new StringFunction());
        this.registerFunction((String)null, "string-length", new StringLengthFunction());
        this.registerFunction((String)null, "substring-after", new SubstringAfterFunction());
        this.registerFunction((String)null, "substring-before", new SubstringBeforeFunction());
        this.registerFunction((String)null, "substring", new SubstringFunction());
        this.registerFunction((String)null, "sum", new SumFunction());
        this.registerFunction((String)null, "true", new TrueFunction());
        this.registerFunction((String)null, "translate", new TranslateFunction());
    }

    private void registerXSLTFunctions() {
        this.registerFunction((String)null, "document", new DocumentFunction());
    }

    private void registerExtensionFunctions() {
        this.registerFunction((String)null, "evaluate", new EvaluateFunction());
        this.registerFunction((String)null, "lower-case", new LowerFunction());
        this.registerFunction((String)null, "upper-case", new UpperFunction());
        this.registerFunction((String)null, "ends-with", new EndsWithFunction());
    }
}
