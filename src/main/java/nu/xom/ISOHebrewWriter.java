//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package nu.xom;

import java.io.Writer;

class ISOHebrewWriter extends TextWriter {
    ISOHebrewWriter(Writer var1, String var2) {
        super(var1, var2);
    }

    boolean needsEscaping(char var1) {
        if (var1 <= 160) {
            return false;
        } else {
            switch(var1) {
                case '¢':
                    return false;
                case '£':
                    return false;
                case '¤':
                    return false;
                case '¥':
                    return false;
                case '¦':
                    return false;
                case '§':
                    return false;
                case '¨':
                    return false;
                case '©':
                    return false;
                case 'ª':
                default:
                    switch(var1) {
                        case 'א':
                            return false;
                        case 'ב':
                            return false;
                        case 'ג':
                            return false;
                        case 'ד':
                            return false;
                        case 'ה':
                            return false;
                        case 'ו':
                            return false;
                        case 'ז':
                            return false;
                        case 'ח':
                            return false;
                        case 'ט':
                            return false;
                        case 'י':
                            return false;
                        case 'ך':
                            return false;
                        case 'כ':
                            return false;
                        case 'ל':
                            return false;
                        case 'ם':
                            return false;
                        case 'מ':
                            return false;
                        case 'ן':
                            return false;
                        case 'נ':
                            return false;
                        case 'ס':
                            return false;
                        case 'ע':
                            return false;
                        case 'ף':
                            return false;
                        case 'פ':
                            return false;
                        case 'ץ':
                            return false;
                        case 'צ':
                            return false;
                        case 'ק':
                            return false;
                        case 'ר':
                            return false;
                        case 'ש':
                            return false;
                        case 'ת':
                            return false;
                        default:
                            switch(var1) {
                                case '×':
                                    return false;
                                case '÷':
                                    return false;
                                case '‗':
                                    return false;
                                default:
                                    return true;
                            }
                    }
                case '«':
                    return false;
                case '¬':
                    return false;
                case '\u00ad':
                    return false;
                case '®':
                    return false;
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
                    return false;
                case 'µ':
                    return false;
                case '¶':
                    return false;
                case '·':
                    return false;
                case '¸':
                    return false;
                case '¹':
                    return false;
                case 'º':
                    return true;
                case '»':
                    return false;
                case '¼':
                    return false;
                case '½':
                    return false;
                case '¾':
                    return false;
            }
        }
    }
}
