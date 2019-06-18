//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package nu.xom;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.*;
import java.util.StringTokenizer;

final class Verifier {
    private static final byte XML_CHARACTER = 1;
    private static final byte NAME_CHARACTER = 2;
    private static final byte NAME_START_CHARACTER = 4;
    private static final byte NCNAME_CHARACTER = 8;
    private static byte[] flags = null;
    private static boolean[] C0Table;
    private static nu.xom.Verifier.URICache cache;
    private static XMLReader parser;

    private Verifier() {
    }

    private static void loadFlags(ClassLoader var0) {
        DataInputStream var1 = null;

        try {
            InputStream var2 = var0.getResourceAsStream("nu/xom/characters.dat");
            if (var2 == null) {
                throw new RuntimeException("Broken XOM installation: could not load nu/xom/characters.dat");
            }

            var1 = new DataInputStream(var2);
            flags = new byte[65536];
            var1.readFully(flags);
        } catch (IOException var10) {
            throw new RuntimeException("Broken XOM installation: could not load nu/xom/characters.dat");
        } finally {
            try {
                if (var1 != null) {
                    var1.close();
                }
            } catch (IOException var9) {
            }

        }

    }

    static void checkNCName(String var0) {
        if (var0 == null) {
            throwIllegalNameException(var0, "NCNames cannot be null");
        }

        int var1 = var0.length();
        if (var1 == 0) {
            throwIllegalNameException(var0, "NCNames cannot be empty");
        }

        char var2 = var0.charAt(0);
        if ((flags[var2] & 4) == 0) {
            throwIllegalNameException(var0, "NCNames cannot start with the character " + Integer.toHexString(var2));
        }

        for(int var3 = 1; var3 < var1; ++var3) {
            char var4 = var0.charAt(var3);
            if ((flags[var4] & 8) == 0) {
                if (var4 == ':') {
                    throwIllegalNameException(var0, "NCNames cannot contain colons");
                } else {
                    throwIllegalNameException(var0, "0x" + Integer.toHexString(var4) + " is not a legal NCName character");
                }
            }
        }

    }

    private static void throwIllegalNameException(String var0, String var1) {
        IllegalNameException var2 = new IllegalNameException(var1);
        var2.setData(var0);
        throw var2;
    }

    private static void throwIllegalCharacterDataException(String var0, String var1) {
        IllegalCharacterDataException var2 = new IllegalCharacterDataException(var1);
        var2.setData(var0);
        throw var2;
    }

    private static void throwMalformedURIException(String var0, String var1) {
        MalformedURIException var2 = new MalformedURIException(var1);
        var2.setData(var0);
        throw var2;
    }

    static void checkPCDATA(String var0) {
        if (var0 == null) {
            throw new IllegalCharacterDataException("Null text");
        } else {
            char[] var1 = var0.toCharArray();
            int var2 = 0;

            for(int var3 = var1.length; var2 < var3; ++var2) {
                char var4 = var1[var2];
                if (var4 >= '\ud800' && var4 <= '\udbff') {
                    IllegalCharacterDataException var6;
                    try {
                        char var5 = var1[var2 + 1];
                        if (var5 < '\udc00' || var5 > '\udfff') {
                            var6 = new IllegalCharacterDataException("Bad surrogate pair");
                            var6.setData(var0);
                            throw var6;
                        }

                        ++var2;
                    } catch (ArrayIndexOutOfBoundsException var7) {
                        var6 = new IllegalCharacterDataException("Bad Surrogate Pair", var7);
                        var6.setData(var0);
                        throw var6;
                    }
                } else if ((flags[var4] & 1) == 0) {
                    throwIllegalCharacterDataException(var0, "0x" + Integer.toHexString(var4) + " is not allowed in XML content");
                }
            }

        }
    }

    static void checkURIReference(String var0) {
        if (var0 != null && var0.length() != 0) {
            nu.xom.URIUtil.ParsedURI var1 = new nu.xom.URIUtil.ParsedURI(var0);

            try {
                if (var1.scheme != null) {
                    checkScheme(var1.scheme);
                }

                if (var1.authority != null) {
                    checkAuthority(var1.authority);
                }

                checkPath(var1.path);
                if (var1.fragment != null) {
                    checkFragment(var1.fragment);
                }

                if (var1.query != null) {
                    checkQuery(var1.query);
                }

            } catch (MalformedURIException var3) {
                var3.setData(var0);
                throw var3;
            }
        }
    }

    private static void checkQuery(String var0) {
        int var1 = var0.length();

        for(int var2 = 0; var2 < var1; ++var2) {
            char var3 = var0.charAt(var2);
            if (var3 != '%') {
                if (!isQueryCharacter(var3)) {
                    throw new MalformedURIException("Illegal query character " + var3);
                }
            } else {
                try {
                    if (!isHexDigit(var0.charAt(var2 + 1)) || !isHexDigit(var0.charAt(var2 + 2))) {
                        throwMalformedURIException(var0, "Bad percent escape sequence");
                    }
                } catch (StringIndexOutOfBoundsException var5) {
                    throwMalformedURIException(var0, "Bad percent escape sequence");
                }

                var2 += 2;
            }
        }

    }

    private static boolean isQueryCharacter(char var0) {
        switch(var0) {
            case '!':
                return true;
            case '"':
                return false;
            case '#':
                return false;
            case '$':
                return true;
            case '%':
                return false;
            case '&':
                return true;
            case '\'':
                return true;
            case '(':
                return true;
            case ')':
                return true;
            case '*':
                return true;
            case '+':
                return true;
            case ',':
                return true;
            case '-':
                return true;
            case '.':
                return true;
            case '/':
                return true;
            case '0':
                return true;
            case '1':
                return true;
            case '2':
                return true;
            case '3':
                return true;
            case '4':
                return true;
            case '5':
                return true;
            case '6':
                return true;
            case '7':
                return true;
            case '8':
                return true;
            case '9':
                return true;
            case ':':
                return true;
            case ';':
                return true;
            case '<':
                return false;
            case '=':
                return true;
            case '>':
                return false;
            case '?':
                return true;
            case '@':
                return true;
            case 'A':
                return true;
            case 'B':
                return true;
            case 'C':
                return true;
            case 'D':
                return true;
            case 'E':
                return true;
            case 'F':
                return true;
            case 'G':
                return true;
            case 'H':
                return true;
            case 'I':
                return true;
            case 'J':
                return true;
            case 'K':
                return true;
            case 'L':
                return true;
            case 'M':
                return true;
            case 'N':
                return true;
            case 'O':
                return true;
            case 'P':
                return true;
            case 'Q':
                return true;
            case 'R':
                return true;
            case 'S':
                return true;
            case 'T':
                return true;
            case 'U':
                return true;
            case 'V':
                return true;
            case 'W':
                return true;
            case 'X':
                return true;
            case 'Y':
                return true;
            case 'Z':
                return true;
            case '[':
                return false;
            case '\\':
                return false;
            case ']':
                return false;
            case '^':
                return false;
            case '_':
                return true;
            case '`':
                return false;
            case 'a':
                return true;
            case 'b':
                return true;
            case 'c':
                return true;
            case 'd':
                return true;
            case 'e':
                return true;
            case 'f':
                return true;
            case 'g':
                return true;
            case 'h':
                return true;
            case 'i':
                return true;
            case 'j':
                return true;
            case 'k':
                return true;
            case 'l':
                return true;
            case 'm':
                return true;
            case 'n':
                return true;
            case 'o':
                return true;
            case 'p':
                return true;
            case 'q':
                return true;
            case 'r':
                return true;
            case 's':
                return true;
            case 't':
                return true;
            case 'u':
                return true;
            case 'v':
                return true;
            case 'w':
                return true;
            case 'x':
                return true;
            case 'y':
                return true;
            case 'z':
                return true;
            case '{':
                return false;
            case '|':
                return false;
            case '}':
                return false;
            case '~':
                return true;
            default:
                return false;
        }
    }

    private static void checkFragment(String var0) {
        checkQuery(var0);
    }

    private static void checkPath(String var0) {
        int var1 = var0.length();
        char[] var2 = var0.toCharArray();

        for(int var3 = 0; var3 < var1; ++var3) {
            char var4 = var2[var3];
            if (var4 == '/') {
                if (var3 < var1 - 1 && var2[var3 + 1] == '/') {
                    throwMalformedURIException(var0, "Double slash (//) in path");
                }
            } else if (var4 != '%') {
                if (!isPathCharacter(var4)) {
                    throwMalformedURIException(var0, "Illegal path character " + var4);
                }
            } else {
                try {
                    if (!isHexDigit(var2[var3 + 1]) || !isHexDigit(var2[var3 + 2])) {
                        throwMalformedURIException(var0, "Bad percent escape sequence");
                    }
                } catch (ArrayIndexOutOfBoundsException var6) {
                    throwMalformedURIException(var0, "Bad percent escape sequence");
                }

                var3 += 2;
            }
        }

    }

    private static void checkAuthority(String var0) {
        String var1 = null;
        String var2 = null;
        String var3 = null;
        int var4 = var0.indexOf(64);
        if (var4 != -1) {
            var1 = var0.substring(0, var4);
            var0 = var0.substring(var4 + 1);
        }

        int var5;
        if (var0.startsWith("[")) {
            var5 = var0.indexOf("]:");
            if (var5 != -1) {
                ++var5;
            }
        } else {
            var5 = var0.indexOf(58);
        }

        if (var5 != -1) {
            var2 = var0.substring(0, var5);
            var3 = var0.substring(var5 + 1);
        } else {
            var2 = var0;
        }

        if (var1 != null) {
            checkUserInfo(var1);
        }

        if (var3 != null) {
            checkPort(var3);
        }

        checkHost(var2);
    }

    private static void checkHost(String var0) {
        int var1 = var0.length();
        if (var1 != 0) {
            char[] var2 = var0.toCharArray();
            if (var2[0] == '[') {
                if (var2[var1 - 1] != ']') {
                    throw new MalformedURIException("Missing closing ]");
                }

                checkIP6Address(var0.substring(1, var1 - 1));
            } else {
                if (var1 > 255) {
                    throw new MalformedURIException("Host name too long: " + var0);
                }

                for(int var3 = 0; var3 < var1; ++var3) {
                    char var4 = var2[var3];
                    if (var4 != '%') {
                        if (!isRegNameCharacter(var4)) {
                            throwMalformedURIException(var0, "Illegal host character " + var4);
                        }
                    } else {
                        try {
                            if (!isHexDigit(var2[var3 + 1]) || !isHexDigit(var2[var3 + 2])) {
                                throwMalformedURIException(var0, "Bad percent escape sequence");
                            }
                        } catch (ArrayIndexOutOfBoundsException var6) {
                            throwMalformedURIException(var0, "Bad percent escape sequence");
                        }

                        var3 += 2;
                    }
                }
            }

        }
    }

    private static boolean isRegNameCharacter(char var0) {
        switch(var0) {
            case '!':
                return true;
            case '"':
                return false;
            case '#':
                return false;
            case '$':
                return true;
            case '%':
                return false;
            case '&':
                return true;
            case '\'':
                return true;
            case '(':
                return true;
            case ')':
                return true;
            case '*':
                return true;
            case '+':
                return true;
            case ',':
                return true;
            case '-':
                return true;
            case '.':
                return true;
            case '/':
                return false;
            case '0':
                return true;
            case '1':
                return true;
            case '2':
                return true;
            case '3':
                return true;
            case '4':
                return true;
            case '5':
                return true;
            case '6':
                return true;
            case '7':
                return true;
            case '8':
                return true;
            case '9':
                return true;
            case ':':
                return false;
            case ';':
                return true;
            case '<':
                return false;
            case '=':
                return true;
            case '>':
                return false;
            case '?':
                return false;
            case '@':
                return false;
            case 'A':
                return true;
            case 'B':
                return true;
            case 'C':
                return true;
            case 'D':
                return true;
            case 'E':
                return true;
            case 'F':
                return true;
            case 'G':
                return true;
            case 'H':
                return true;
            case 'I':
                return true;
            case 'J':
                return true;
            case 'K':
                return true;
            case 'L':
                return true;
            case 'M':
                return true;
            case 'N':
                return true;
            case 'O':
                return true;
            case 'P':
                return true;
            case 'Q':
                return true;
            case 'R':
                return true;
            case 'S':
                return true;
            case 'T':
                return true;
            case 'U':
                return true;
            case 'V':
                return true;
            case 'W':
                return true;
            case 'X':
                return true;
            case 'Y':
                return true;
            case 'Z':
                return true;
            case '[':
                return false;
            case '\\':
                return false;
            case ']':
                return false;
            case '^':
                return false;
            case '_':
                return true;
            case '`':
                return false;
            case 'a':
                return true;
            case 'b':
                return true;
            case 'c':
                return true;
            case 'd':
                return true;
            case 'e':
                return true;
            case 'f':
                return true;
            case 'g':
                return true;
            case 'h':
                return true;
            case 'i':
                return true;
            case 'j':
                return true;
            case 'k':
                return true;
            case 'l':
                return true;
            case 'm':
                return true;
            case 'n':
                return true;
            case 'o':
                return true;
            case 'p':
                return true;
            case 'q':
                return true;
            case 'r':
                return true;
            case 's':
                return true;
            case 't':
                return true;
            case 'u':
                return true;
            case 'v':
                return true;
            case 'w':
                return true;
            case 'x':
                return true;
            case 'y':
                return true;
            case 'z':
                return true;
            case '{':
                return false;
            case '|':
                return false;
            case '}':
                return false;
            case '~':
                return true;
            default:
                return false;
        }
    }

    private static void checkPort(String var0) {
        for(int var1 = var0.length() - 1; var1 >= 0; --var1) {
            char var2 = var0.charAt(var1);
            if (var2 < '0' || var2 > '9') {
                throw new MalformedURIException("Bad port: " + var0);
            }
        }

    }

    private static void checkUserInfo(String var0) {
        int var1 = var0.length();

        for(int var2 = 0; var2 < var1; ++var2) {
            char var3 = var0.charAt(var2);
            if (var3 != '%') {
                if (!isUserInfoCharacter(var3)) {
                    throw new MalformedURIException("Bad user info: " + var0);
                }
            } else {
                try {
                    if (!isHexDigit(var0.charAt(var2 + 1)) || !isHexDigit(var0.charAt(var2 + 2))) {
                        throwMalformedURIException(var0, "Bad percent escape sequence");
                    }
                } catch (StringIndexOutOfBoundsException var5) {
                    throwMalformedURIException(var0, "Bad percent escape sequence");
                }

                var2 += 2;
            }
        }

    }

    private static void checkScheme(String var0) {
        if (!"http".equals(var0)) {
            if (var0.length() == 0) {
                throw new MalformedURIException("URIs cannot begin with a colon");
            } else {
                char var1 = var0.charAt(0);
                if (!isAlpha(var1)) {
                    throw new MalformedURIException("Illegal initial scheme character " + var1);
                } else {
                    for(int var2 = var0.length() - 1; var2 >= 1; --var2) {
                        var1 = var0.charAt(var2);
                        if (!isSchemeCharacter(var1)) {
                            throw new MalformedURIException("Illegal scheme character " + var1);
                        }
                    }

                }
            }
        }
    }

    private static void checkIP6Address(String var0) {
        StringTokenizer var1 = new StringTokenizer(var0, ":", true);
        int var2 = var1.countTokens();
        if (var2 <= 15 && var2 >= 2) {
            for(int var3 = 0; var3 < var2; ++var3) {
                String var4 = var1.nextToken();
                if (!":".equals(var4)) {
                    try {
                        int var5 = Integer.parseInt(var4, 16);
                        if (var5 < 0) {
                            throw new MalformedURIException("Illegal IP6 host address: " + var0);
                        }
                    } catch (NumberFormatException var6) {
                        if (var3 == var2 - 1) {
                            checkIP4Address(var4, var0);
                        } else {
                            throwMalformedURIException(var0, "Illegal IP6 host address: " + var0);
                        }
                    }
                }
            }

            if (var0.indexOf("::") != var0.lastIndexOf("::")) {
                throw new MalformedURIException("Illegal IP6 host address: " + var0);
            }
        } else {
            throw new MalformedURIException("Illegal IP6 host address: " + var0);
        }
    }

    private static void checkIP4Address(String var0, String var1) {
        StringTokenizer var2 = new StringTokenizer(var0, ".");
        int var3 = var2.countTokens();
        if (var3 != 4) {
            throw new MalformedURIException("Illegal IP6 host address: " + var1);
        } else {
            for(int var4 = 0; var4 < 4; ++var4) {
                String var5 = var2.nextToken();

                try {
                    int var6 = Integer.parseInt(var5);
                    if (var6 > 255 || var6 < 0) {
                        throw new MalformedURIException("Illegal IP6 host address: " + var1);
                    }
                } catch (NumberFormatException var7) {
                    throw new MalformedURIException("Illegal IP6 host address: " + var1);
                }
            }

        }
    }

    static void checkXMLName(String var0) {
        if (var0 == null) {
            throwIllegalNameException(var0, "XML names cannot be null");
        }

        int var1 = var0.length();
        if (var1 == 0) {
            throwIllegalNameException(var0, "XML names cannot be empty");
        }

        char var2 = var0.charAt(0);
        if ((flags[var2] & 4) == 0) {
            throwIllegalNameException(var0, "XML names cannot start with the character " + Integer.toHexString(var2));
        }

        for(int var3 = 1; var3 < var1; ++var3) {
            char var4 = var0.charAt(var3);
            if ((flags[var4] & 2) == 0) {
                throwIllegalNameException(var0, "0x" + Integer.toHexString(var4) + " is not a legal name character");
            }
        }

    }

    static boolean isXMLSpaceCharacter(char var0) {
        return var0 > ' ' ? false : C0Table[var0];
    }

    private static boolean isHexDigit(char var0) {
        switch(var0) {
            case '0':
                return true;
            case '1':
                return true;
            case '2':
                return true;
            case '3':
                return true;
            case '4':
                return true;
            case '5':
                return true;
            case '6':
                return true;
            case '7':
                return true;
            case '8':
                return true;
            case '9':
                return true;
            case ':':
                return false;
            case ';':
                return false;
            case '<':
                return false;
            case '=':
                return false;
            case '>':
                return false;
            case '?':
                return false;
            case '@':
                return false;
            case 'A':
                return true;
            case 'B':
                return true;
            case 'C':
                return true;
            case 'D':
                return true;
            case 'E':
                return true;
            case 'F':
                return true;
            case 'G':
                return false;
            case 'H':
                return false;
            case 'I':
                return false;
            case 'J':
                return false;
            case 'K':
                return false;
            case 'L':
                return false;
            case 'M':
                return false;
            case 'N':
                return false;
            case 'O':
                return false;
            case 'P':
                return false;
            case 'Q':
                return false;
            case 'R':
                return false;
            case 'S':
                return false;
            case 'T':
                return false;
            case 'U':
                return false;
            case 'V':
                return false;
            case 'W':
                return false;
            case 'X':
                return false;
            case 'Y':
                return false;
            case 'Z':
                return false;
            case '[':
                return false;
            case '\\':
                return false;
            case ']':
                return false;
            case '^':
                return false;
            case '_':
                return false;
            case '`':
                return false;
            case 'a':
                return true;
            case 'b':
                return true;
            case 'c':
                return true;
            case 'd':
                return true;
            case 'e':
                return true;
            case 'f':
                return true;
            default:
                return false;
        }
    }

    static void checkAbsoluteURIReference(String var0) {
        if (!cache.contains(var0)) {
            nu.xom.URIUtil.ParsedURI var1 = new nu.xom.URIUtil.ParsedURI(var0);

            try {
                if (var1.scheme == null) {
                    throwMalformedURIException(var0, "Missing scheme in absolute URI reference");
                }

                checkScheme(var1.scheme);
                if (var1.authority != null) {
                    checkAuthority(var1.authority);
                }

                checkPath(var1.path);
                if (var1.fragment != null) {
                    checkFragment(var1.fragment);
                }

                if (var1.query != null) {
                    checkQuery(var1.query);
                }

                cache.put(var0);
            } catch (MalformedURIException var3) {
                var3.setData(var0);
                throw var3;
            }
        }
    }

    static boolean isAlpha(char var0) {
        switch(var0) {
            case 'A':
                return true;
            case 'B':
                return true;
            case 'C':
                return true;
            case 'D':
                return true;
            case 'E':
                return true;
            case 'F':
                return true;
            case 'G':
                return true;
            case 'H':
                return true;
            case 'I':
                return true;
            case 'J':
                return true;
            case 'K':
                return true;
            case 'L':
                return true;
            case 'M':
                return true;
            case 'N':
                return true;
            case 'O':
                return true;
            case 'P':
                return true;
            case 'Q':
                return true;
            case 'R':
                return true;
            case 'S':
                return true;
            case 'T':
                return true;
            case 'U':
                return true;
            case 'V':
                return true;
            case 'W':
                return true;
            case 'X':
                return true;
            case 'Y':
                return true;
            case 'Z':
                return true;
            case '[':
                return false;
            case '\\':
                return false;
            case ']':
                return false;
            case '^':
                return false;
            case '_':
                return false;
            case '`':
                return false;
            case 'a':
                return true;
            case 'b':
                return true;
            case 'c':
                return true;
            case 'd':
                return true;
            case 'e':
                return true;
            case 'f':
                return true;
            case 'g':
                return true;
            case 'h':
                return true;
            case 'i':
                return true;
            case 'j':
                return true;
            case 'k':
                return true;
            case 'l':
                return true;
            case 'm':
                return true;
            case 'n':
                return true;
            case 'o':
                return true;
            case 'p':
                return true;
            case 'q':
                return true;
            case 'r':
                return true;
            case 's':
                return true;
            case 't':
                return true;
            case 'u':
                return true;
            case 'v':
                return true;
            case 'w':
                return true;
            case 'x':
                return true;
            case 'y':
                return true;
            case 'z':
                return true;
            default:
                return false;
        }
    }

    static boolean isSchemeCharacter(char var0) {
        switch(var0) {
            case '+':
                return true;
            case ',':
                return false;
            case '-':
                return true;
            case '.':
                return true;
            case '/':
                return false;
            case '0':
                return true;
            case '1':
                return true;
            case '2':
                return true;
            case '3':
                return true;
            case '4':
                return true;
            case '5':
                return true;
            case '6':
                return true;
            case '7':
                return true;
            case '8':
                return true;
            case '9':
                return true;
            case ':':
                return false;
            case ';':
                return false;
            case '<':
                return false;
            case '=':
                return false;
            case '>':
                return false;
            case '?':
                return false;
            case '@':
                return false;
            case 'A':
                return true;
            case 'B':
                return true;
            case 'C':
                return true;
            case 'D':
                return true;
            case 'E':
                return true;
            case 'F':
                return true;
            case 'G':
                return true;
            case 'H':
                return true;
            case 'I':
                return true;
            case 'J':
                return true;
            case 'K':
                return true;
            case 'L':
                return true;
            case 'M':
                return true;
            case 'N':
                return true;
            case 'O':
                return true;
            case 'P':
                return true;
            case 'Q':
                return true;
            case 'R':
                return true;
            case 'S':
                return true;
            case 'T':
                return true;
            case 'U':
                return true;
            case 'V':
                return true;
            case 'W':
                return true;
            case 'X':
                return true;
            case 'Y':
                return true;
            case 'Z':
                return true;
            case '[':
                return false;
            case '\\':
                return false;
            case ']':
                return false;
            case '^':
                return false;
            case '_':
                return false;
            case '`':
                return false;
            case 'a':
                return true;
            case 'b':
                return true;
            case 'c':
                return true;
            case 'd':
                return true;
            case 'e':
                return true;
            case 'f':
                return true;
            case 'g':
                return true;
            case 'h':
                return true;
            case 'i':
                return true;
            case 'j':
                return true;
            case 'k':
                return true;
            case 'l':
                return true;
            case 'm':
                return true;
            case 'n':
                return true;
            case 'o':
                return true;
            case 'p':
                return true;
            case 'q':
                return true;
            case 'r':
                return true;
            case 's':
                return true;
            case 't':
                return true;
            case 'u':
                return true;
            case 'v':
                return true;
            case 'w':
                return true;
            case 'x':
                return true;
            case 'y':
                return true;
            case 'z':
                return true;
            default:
                return false;
        }
    }

    private static boolean isPathCharacter(char var0) {
        switch(var0) {
            case '!':
                return true;
            case '"':
                return false;
            case '#':
                return false;
            case '$':
                return true;
            case '%':
                return false;
            case '&':
                return true;
            case '\'':
                return true;
            case '(':
                return true;
            case ')':
                return true;
            case '*':
                return true;
            case '+':
                return true;
            case ',':
                return true;
            case '-':
                return true;
            case '.':
                return true;
            case '/':
                return false;
            case '0':
                return true;
            case '1':
                return true;
            case '2':
                return true;
            case '3':
                return true;
            case '4':
                return true;
            case '5':
                return true;
            case '6':
                return true;
            case '7':
                return true;
            case '8':
                return true;
            case '9':
                return true;
            case ':':
                return true;
            case ';':
                return true;
            case '<':
                return false;
            case '=':
                return true;
            case '>':
                return false;
            case '?':
                return false;
            case '@':
                return true;
            case 'A':
                return true;
            case 'B':
                return true;
            case 'C':
                return true;
            case 'D':
                return true;
            case 'E':
                return true;
            case 'F':
                return true;
            case 'G':
                return true;
            case 'H':
                return true;
            case 'I':
                return true;
            case 'J':
                return true;
            case 'K':
                return true;
            case 'L':
                return true;
            case 'M':
                return true;
            case 'N':
                return true;
            case 'O':
                return true;
            case 'P':
                return true;
            case 'Q':
                return true;
            case 'R':
                return true;
            case 'S':
                return true;
            case 'T':
                return true;
            case 'U':
                return true;
            case 'V':
                return true;
            case 'W':
                return true;
            case 'X':
                return true;
            case 'Y':
                return true;
            case 'Z':
                return true;
            case '[':
                return false;
            case '\\':
                return false;
            case ']':
                return false;
            case '^':
                return false;
            case '_':
                return true;
            case '`':
                return false;
            case 'a':
                return true;
            case 'b':
                return true;
            case 'c':
                return true;
            case 'd':
                return true;
            case 'e':
                return true;
            case 'f':
                return true;
            case 'g':
                return true;
            case 'h':
                return true;
            case 'i':
                return true;
            case 'j':
                return true;
            case 'k':
                return true;
            case 'l':
                return true;
            case 'm':
                return true;
            case 'n':
                return true;
            case 'o':
                return true;
            case 'p':
                return true;
            case 'q':
                return true;
            case 'r':
                return true;
            case 's':
                return true;
            case 't':
                return true;
            case 'u':
                return true;
            case 'v':
                return true;
            case 'w':
                return true;
            case 'x':
                return true;
            case 'y':
                return true;
            case 'z':
                return true;
            case '{':
                return false;
            case '|':
                return false;
            case '}':
                return false;
            case '~':
                return true;
            default:
                return false;
        }
    }

    private static boolean isUserInfoCharacter(char var0) {
        switch(var0) {
            case '!':
                return true;
            case '"':
                return false;
            case '#':
                return false;
            case '$':
                return true;
            case '%':
                return false;
            case '&':
                return true;
            case '\'':
                return true;
            case '(':
                return true;
            case ')':
                return true;
            case '*':
                return true;
            case '+':
                return true;
            case ',':
                return true;
            case '-':
                return true;
            case '.':
                return true;
            case '/':
                return true;
            case '0':
                return true;
            case '1':
                return true;
            case '2':
                return true;
            case '3':
                return true;
            case '4':
                return true;
            case '5':
                return true;
            case '6':
                return true;
            case '7':
                return true;
            case '8':
                return true;
            case '9':
                return true;
            case ':':
                return true;
            case ';':
                return true;
            case '<':
                return false;
            case '=':
                return true;
            case '>':
                return false;
            case '?':
                return false;
            case '@':
                return false;
            case 'A':
                return true;
            case 'B':
                return true;
            case 'C':
                return true;
            case 'D':
                return true;
            case 'E':
                return true;
            case 'F':
                return true;
            case 'G':
                return true;
            case 'H':
                return true;
            case 'I':
                return true;
            case 'J':
                return true;
            case 'K':
                return true;
            case 'L':
                return true;
            case 'M':
                return true;
            case 'N':
                return true;
            case 'O':
                return true;
            case 'P':
                return true;
            case 'Q':
                return true;
            case 'R':
                return true;
            case 'S':
                return true;
            case 'T':
                return true;
            case 'U':
                return true;
            case 'V':
                return true;
            case 'W':
                return true;
            case 'X':
                return true;
            case 'Y':
                return true;
            case 'Z':
                return true;
            case '[':
                return false;
            case '\\':
                return false;
            case ']':
                return false;
            case '^':
                return false;
            case '_':
                return true;
            case '`':
                return false;
            case 'a':
                return true;
            case 'b':
                return true;
            case 'c':
                return true;
            case 'd':
                return true;
            case 'e':
                return true;
            case 'f':
                return true;
            case 'g':
                return true;
            case 'h':
                return true;
            case 'i':
                return true;
            case 'j':
                return true;
            case 'k':
                return true;
            case 'l':
                return true;
            case 'm':
                return true;
            case 'n':
                return true;
            case 'o':
                return true;
            case 'p':
                return true;
            case 'q':
                return true;
            case 'r':
                return true;
            case 's':
                return true;
            case 't':
                return true;
            case 'u':
                return true;
            case 'v':
                return true;
            case 'w':
                return true;
            case 'x':
                return true;
            case 'y':
                return true;
            case 'z':
                return true;
            case '{':
                return false;
            case '|':
                return false;
            case '}':
                return false;
            case '~':
                return true;
            default:
                return false;
        }
    }

    static void checkAbsoluteURI(String var0) {
        nu.xom.URIUtil.ParsedURI var1 = new nu.xom.URIUtil.ParsedURI(var0);

        try {
            if (var1.scheme == null) {
                throwMalformedURIException(var0, "Missing scheme in absolute URI");
            }

            checkScheme(var1.scheme);
            if (var1.authority != null) {
                checkAuthority(var1.authority);
            }

            checkPath(var1.path);
            if (var1.fragment != null) {
                throwMalformedURIException(var0, "URIs cannot have fragment identifiers");
            }

            if (var1.query != null) {
                checkQuery(var1.query);
            }

        } catch (MalformedURIException var3) {
            var3.setData(var0);
            throw var3;
        }
    }

    static synchronized void checkInternalDTDSubset(String var0) {
        if (parser == null) {
            final InputSource var1 = new InputSource(new nu.xom.Verifier.EmptyReader());
            parser = Builder.findParser(false);
            parser.setEntityResolver(new EntityResolver() {
                public InputSource resolveEntity(String var1x, String var2) {
                    return var1;
                }
            });
        }

        String var6 = "<!DOCTYPE a [" + var0 + "]><a/>";

        try {
            InputSource var2 = new InputSource(new StringReader(var6));
            var2.setSystemId("http://www.example.org/");
            parser.parse(var2);
        } catch (SAXException var4) {
            IllegalDataException var3 = new IllegalDataException("Malformed internal DTD subset: " + var4.getMessage(), var4);
            var3.setData(var0);
            throw var3;
        } catch (IOException var5) {
            throw new RuntimeException("BUG: I don't think this can happen");
        }
    }

    static {
        //xiugai yangning
        ClassLoader var0 = nu.xom.Verifier.class.getClassLoader();
        if (var0 != null) {
            loadFlags(var0);
        }

        if (flags == null) {
            var0 = Thread.currentThread().getContextClassLoader();
            loadFlags(var0);
        }

        C0Table = new boolean[33];
        C0Table[10] = true;
        C0Table[13] = true;
        C0Table[9] = true;
        C0Table[32] = true;
        cache = new nu.xom.Verifier.URICache();
    }

    private static class EmptyReader extends Reader {
        private EmptyReader() {
        }

        public int read(char[] var1, int var2, int var3) throws IOException {
            return -1;
        }

        public void close() {
        }
    }

    private static final class URICache {
        private static final int LOAD = 6;
        private String[] cache;
        private int position;

        private URICache() {
            this.cache = new String[6];
            this.position = 0;
        }

        synchronized boolean contains(String var1) {
            for(int var2 = 0; var2 < 6; ++var2) {
                if (var1 == this.cache[var2]) {
                    return true;
                }
            }

            return false;
        }

        synchronized void put(String var1) {
            this.cache[this.position] = var1;
            ++this.position;
            if (this.position == 6) {
                this.position = 0;
            }

        }
    }
}
