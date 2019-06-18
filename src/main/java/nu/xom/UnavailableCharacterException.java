package nu.xom;

public class UnavailableCharacterException extends XMLException {
    private static final long serialVersionUID = -8280912714497572798L;
    private final char unavailableCharacter;
    private final String encoding;

    public UnavailableCharacterException(char var1, String var2) {
        super("Cannot use the character " + var1 + " (&#x" + Integer.toHexString(var1).toUpperCase() + ";) in the " + var2 + " encoding.");
        this.unavailableCharacter = var1;
        this.encoding = var2;
    }

    public char getCharacter() {
        return this.unavailableCharacter;
    }

    public String getEncoding() {
        return this.encoding;
    }
}

