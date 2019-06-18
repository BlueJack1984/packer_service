package nu.xom;


import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Locale;

class TextWriterFactory {
    TextWriterFactory() {
    }

    public static nu.xom.TextWriter getTextWriter(Writer var0, String var1) {
        String var2 = var1.toUpperCase(Locale.ENGLISH);
        if (!var2.startsWith("UTF") && !var2.startsWith("UNICODE")) {
            if (!var2.startsWith("ISO-10646-UCS") && !var2.startsWith("UCS") && !var2.equals("GB18030")) {
                if (var2.equals("ISO-8859-1")) {
                    return new nu.xom.Latin1Writer(var0, var1);
                } else if (var2.equals("ISO-8859-2")) {
                    return new nu.xom.Latin2Writer(var0, var2);
                } else if (var2.equals("ISO-8859-3")) {
                    return new nu.xom.Latin3Writer(var0, var2);
                } else if (var2.equals("ISO-8859-4")) {
                    return new nu.xom.Latin4Writer(var0, var2);
                } else if (var2.equals("ISO-8859-5")) {
                    return new nu.xom.ISOCyrillicWriter(var0, var2);
                } else if (var2.equals("ISO-8859-6")) {
                    return new nu.xom.ISOArabicWriter(var0, var2);
                } else if (var2.equals("ISO-8859-7")) {
                    return new nu.xom.ISOGreekWriter(var0, var2);
                } else if (var2.equals("ISO-8859-8")) {
                    return new nu.xom.ISOHebrewWriter(var0, var2);
                } else if (!var2.equals("ISO-8859-9") && !var2.equals("EBCDIC-CP-TR") && !var2.equals("CP1037")) {
                    if (var1.equals("ISO-8859-10")) {
                        return new nu.xom.Latin6Writer(var0, var1);
                    } else if (!var2.equals("ISO-8859-11") && !var2.equals("TIS-620") && !var2.equals("TIS620")) {
                        if (var2.equals("ISO-8859-13")) {
                            return new Latin7Writer(var0, var2);
                        } else if (var1.equals("ISO-8859-14")) {
                            return new nu.xom.Latin8Writer(var0, var1);
                        } else if (var2.equals("ISO-8859-15")) {
                            return new nu.xom.Latin9Writer(var0, var2);
                        } else if (var1.equals("ISO-8859-16")) {
                            return new nu.xom.Latin10Writer(var0, var1);
                        } else if (var2.endsWith("ASCII")) {
                            return new nu.xom.ASCIIWriter(var0, var2);
                        } else if (!var2.equals("IBM037") && !var2.equals("CP037") && !var2.equals("EBCDIC-CP-US") && !var2.equals("EBCDIC-CP-CA") && !var2.equals("EBCDIC-CP-WA") && !var2.equals("EBCDIC-CP-NL") && !var2.equals("CSIBM037")) {
                            try {
                                return new nu.xom.GenericWriter(var0, var1);
                            } catch (UnsupportedEncodingException var4) {
                                return new nu.xom.ASCIIWriter(var0, var1);
                            }
                        } else {
                            return new nu.xom.Latin1Writer(var0, var2);
                        }
                    } else {
                        return new nu.xom.ISOThaiWriter(var0, var2);
                    }
                } else {
                    return new Latin5Writer(var0, var2);
                }
            } else {
                return new nu.xom.UCSWriter(var0, var1);
            }
        } else {
            return new nu.xom.UnicodeWriter(var0, var1);
        }
    }
}
