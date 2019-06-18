//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package nu.xom;

import java.io.Writer;

class Latin10Writer extends TextWriter {
    Latin10Writer(Writer var1, String var2) {
        super(var1, var2);
    }

    boolean needsEscaping(char var1) {
        if (var1 <= 160) {
            return false;
        } else {
            switch(var1) {
                case '§':
                    return false;
                case '¨':
                    return true;
                case '©':
                    return false;
                case 'ª':
                    return true;
                case '«':
                    return false;
                case '¬':
                    return true;
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
                    return true;
                case '³':
                    return true;
                case '´':
                    return true;
                case 'µ':
                    return true;
                case '¶':
                    return false;
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
                    return true;
                case '¾':
                    return true;
                case '¿':
                    return true;
                case 'À':
                    return false;
                case 'Á':
                    return false;
                case 'Â':
                    return false;
                case 'Ã':
                    return true;
                case 'Ä':
                    return false;
                case 'Å':
                    return true;
                case 'Æ':
                    return false;
                case 'Ç':
                    return false;
                case 'È':
                    return false;
                case 'É':
                    return false;
                case 'Ê':
                    return false;
                case 'Ë':
                    return false;
                case 'Ì':
                    return false;
                case 'Í':
                    return false;
                case 'Î':
                    return false;
                case 'Ï':
                    return false;
                case 'Ð':
                    return true;
                case 'Ñ':
                    return true;
                case 'Ò':
                    return false;
                case 'Ó':
                    return false;
                case 'Ô':
                    return false;
                case 'Õ':
                    return true;
                case 'Ö':
                    return false;
                case '×':
                    return true;
                case 'Ø':
                    return true;
                case 'Ù':
                    return false;
                case 'Ú':
                    return false;
                case 'Û':
                    return false;
                case 'Ü':
                    return false;
                case 'Ý':
                    return true;
                case 'Þ':
                    return true;
                case 'ß':
                    return false;
                case 'à':
                    return false;
                case 'á':
                    return false;
                case 'â':
                    return false;
                case 'ã':
                    return true;
                case 'ä':
                    return false;
                case 'å':
                    return true;
                case 'æ':
                    return false;
                case 'ç':
                    return false;
                case 'è':
                    return false;
                case 'é':
                    return false;
                case 'ê':
                    return false;
                case 'ë':
                    return false;
                case 'ì':
                    return false;
                case 'í':
                    return false;
                case 'î':
                    return false;
                case 'ï':
                    return false;
                case 'ð':
                    return true;
                case 'ñ':
                    return true;
                case 'ò':
                    return false;
                case 'ó':
                    return false;
                case 'ô':
                    return false;
                case 'õ':
                    return true;
                case 'ö':
                    return false;
                case '÷':
                    return true;
                case 'ø':
                    return true;
                case 'ù':
                    return false;
                case 'ú':
                    return false;
                case 'û':
                    return false;
                case 'ü':
                    return false;
                case 'ý':
                    return true;
                case 'þ':
                    return true;
                case 'ÿ':
                    return false;
                case 'Ā':
                    return true;
                case 'ā':
                    return true;
                case 'Ă':
                    return false;
                case 'ă':
                    return false;
                case 'Ą':
                    return false;
                case 'ą':
                    return false;
                case 'Ć':
                    return false;
                case 'ć':
                    return false;
                case 'Ĉ':
                    return true;
                case 'ĉ':
                    return true;
                case 'Ċ':
                    return true;
                case 'ċ':
                    return true;
                case 'Č':
                    return false;
                case 'č':
                    return false;
                case 'Ď':
                    return true;
                case 'ď':
                    return true;
                case 'Đ':
                    return false;
                case 'đ':
                    return false;
                case 'Ē':
                    return true;
                case 'ē':
                    return true;
                case 'Ĕ':
                    return true;
                case 'ĕ':
                    return true;
                case 'Ė':
                    return true;
                case 'ė':
                    return true;
                case 'Ę':
                    return false;
                case 'ę':
                    return false;
                case 'Ě':
                    return true;
                case 'ě':
                    return true;
                case 'Ĝ':
                    return true;
                case 'ĝ':
                    return true;
                case 'Ğ':
                    return true;
                case 'ğ':
                    return true;
                case 'Ġ':
                    return true;
                case 'ġ':
                    return true;
                case 'Ģ':
                    return true;
                case 'ģ':
                    return true;
                case 'Ĥ':
                    return true;
                case 'ĥ':
                    return true;
                case 'Ħ':
                    return true;
                case 'ħ':
                    return true;
                case 'Ĩ':
                    return true;
                case 'ĩ':
                    return true;
                case 'Ī':
                    return true;
                case 'ī':
                    return true;
                case 'Ĭ':
                    return true;
                case 'ĭ':
                    return true;
                case 'Į':
                    return true;
                case 'į':
                    return true;
                case 'İ':
                    return true;
                case 'ı':
                    return true;
                case 'Ĳ':
                    return true;
                case 'ĳ':
                    return true;
                case 'Ĵ':
                    return true;
                case 'ĵ':
                    return true;
                case 'Ķ':
                    return true;
                case 'ķ':
                    return true;
                case 'ĸ':
                    return true;
                case 'Ĺ':
                    return true;
                case 'ĺ':
                    return true;
                case 'Ļ':
                    return true;
                case 'ļ':
                    return true;
                case 'Ľ':
                    return true;
                case 'ľ':
                    return true;
                case 'Ŀ':
                    return true;
                case 'ŀ':
                    return true;
                case 'Ł':
                    return false;
                case 'ł':
                    return false;
                case 'Ń':
                    return false;
                case 'ń':
                    return false;
                case 'Ņ':
                    return true;
                case 'ņ':
                    return true;
                case 'Ň':
                    return true;
                case 'ň':
                    return true;
                case 'ŉ':
                    return true;
                case 'Ŋ':
                    return true;
                case 'ŋ':
                    return true;
                case 'Ō':
                    return true;
                case 'ō':
                    return true;
                case 'Ŏ':
                    return true;
                case 'ŏ':
                    return true;
                case 'Ő':
                    return false;
                case 'ő':
                    return false;
                case 'Œ':
                    return false;
                case 'œ':
                    return false;
                case 'Ŕ':
                    return true;
                case 'ŕ':
                    return true;
                case 'Ŗ':
                    return true;
                case 'ŗ':
                    return true;
                case 'Ř':
                    return true;
                case 'ř':
                    return true;
                case 'Ś':
                    return false;
                case 'ś':
                    return false;
                case 'Ŝ':
                    return true;
                case 'ŝ':
                    return true;
                case 'Ş':
                    return true;
                case 'ş':
                    return true;
                case 'Š':
                    return false;
                case 'š':
                    return false;
                case 'Ţ':
                    return true;
                case 'ţ':
                    return true;
                case 'Ť':
                    return true;
                case 'ť':
                    return true;
                case 'Ŧ':
                    return true;
                case 'ŧ':
                    return true;
                case 'Ũ':
                    return true;
                case 'ũ':
                    return true;
                case 'Ū':
                    return true;
                case 'ū':
                    return true;
                case 'Ŭ':
                    return true;
                case 'ŭ':
                    return true;
                case 'Ů':
                    return true;
                case 'ů':
                    return true;
                case 'Ű':
                    return false;
                case 'ű':
                    return false;
                case 'Ų':
                    return true;
                case 'ų':
                    return true;
                case 'Ŵ':
                    return true;
                case 'ŵ':
                    return true;
                case 'Ŷ':
                    return true;
                case 'ŷ':
                    return true;
                case 'Ÿ':
                    return false;
                case 'Ź':
                    return false;
                case 'ź':
                    return false;
                case 'Ż':
                    return false;
                case 'ż':
                    return false;
                case 'Ž':
                    return false;
                case 'ž':
                    return false;
                default:
                    switch(var1) {
                        case 'Ș':
                            return false;
                        case 'ș':
                            return false;
                        case 'Ț':
                            return false;
                        case 'ț':
                            return false;
                        case 'Ȝ':
                            return true;
                        case '”':
                            return false;
                        case '„':
                            return false;
                        default:
                            return var1 != 8364;
                    }
            }
        }
    }
}
