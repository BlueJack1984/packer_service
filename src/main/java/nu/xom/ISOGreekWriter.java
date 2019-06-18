//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package nu.xom;

import java.io.Writer;

class ISOGreekWriter extends TextWriter {
    ISOGreekWriter(Writer var1, String var2) {
        super(var1, var2);
    }

    boolean needsEscaping(char var1) {
        if (var1 < 127) {
            return false;
        } else {
            switch(var1) {
                case '΄':
                    return false;
                case '΅':
                    return false;
                case 'Ά':
                    return false;
                case '·':
                    return true;
                case 'Έ':
                    return false;
                case 'Ή':
                    return false;
                case 'Ί':
                    return false;
                case '\u038b':
                    return true;
                case 'Ό':
                    return false;
                case '\u038d':
                    return true;
                case 'Ύ':
                    return false;
                case 'Ώ':
                    return false;
                case 'ΐ':
                    return false;
                case 'Α':
                    return false;
                case 'Β':
                    return false;
                case 'Γ':
                    return false;
                case 'Δ':
                    return false;
                case 'Ε':
                    return false;
                case 'Ζ':
                    return false;
                case 'Η':
                    return false;
                case 'Θ':
                    return false;
                case 'Ι':
                    return false;
                case 'Κ':
                    return false;
                case 'Λ':
                    return false;
                case 'Μ':
                    return false;
                case 'Ν':
                    return false;
                case 'Ξ':
                    return false;
                case 'Ο':
                    return false;
                case 'Π':
                    return false;
                case 'Ρ':
                    return false;
                case '\u03a2':
                    return true;
                case 'Σ':
                    return false;
                case 'Τ':
                    return false;
                case 'Υ':
                    return false;
                case 'Φ':
                    return false;
                case 'Χ':
                    return false;
                case 'Ψ':
                    return false;
                case 'Ω':
                    return false;
                case 'Ϊ':
                    return false;
                case 'Ϋ':
                    return false;
                case 'ά':
                    return false;
                case 'έ':
                    return false;
                case 'ή':
                    return false;
                case 'ί':
                    return false;
                case 'ΰ':
                    return false;
                case 'α':
                    return false;
                case 'β':
                    return false;
                case 'γ':
                    return false;
                case 'δ':
                    return false;
                case 'ε':
                    return false;
                case 'ζ':
                    return false;
                case 'η':
                    return false;
                case 'θ':
                    return false;
                case 'ι':
                    return false;
                case 'κ':
                    return false;
                case 'λ':
                    return false;
                case 'μ':
                    return false;
                case 'ν':
                    return false;
                case 'ξ':
                    return false;
                case 'ο':
                    return false;
                case 'π':
                    return false;
                case 'ρ':
                    return false;
                case 'ς':
                    return false;
                case 'σ':
                    return false;
                case 'τ':
                    return false;
                case 'υ':
                    return false;
                case 'φ':
                    return false;
                case 'χ':
                    return false;
                case 'ψ':
                    return false;
                case 'ω':
                    return false;
                case 'ϊ':
                    return false;
                case 'ϋ':
                    return false;
                case 'ό':
                    return false;
                case 'ύ':
                    return false;
                case 'ώ':
                    return false;
                default:
                    switch(var1) {
                        case ' ':
                            return false;
                        case '¡':
                            return true;
                        case '¢':
                            return true;
                        case '£':
                            return false;
                        case '¤':
                            return true;
                        case '¥':
                            return true;
                        case '¦':
                            return false;
                        case '§':
                            return false;
                        case '¨':
                            return false;
                        case '©':
                            return false;
                        case 'ª':
                            return true;
                        case '«':
                            return false;
                        case '¬':
                            return false;
                        case '\u00ad':
                            return false;
                        case '®':
                            return true;
                        case '¯':
                            return true;
                        case '°':
                            return false;
                        case '±':
                            return false;
                        case '²':
                            return false;
                        case '³':
                            return false;
                        case '´':
                            return true;
                        case 'µ':
                            return true;
                        case '¶':
                            return true;
                        case '·':
                            return false;
                        case '¸':
                            return true;
                        case '¹':
                            return true;
                        case 'º':
                            return true;
                        case '»':
                            return false;
                        case '¼':
                            return true;
                        case '½':
                            return false;
                        default:
                            switch(var1) {
                                case '―':
                                    return false;
                                default:
                                    return true;
                            }
                    }
            }
        }
    }
}
