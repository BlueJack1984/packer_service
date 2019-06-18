//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package nu.xom;

import java.io.Writer;

class Latin4Writer extends TextWriter {
    Latin4Writer(Writer var1, String var2) {
        super(var1, var2);
    }

    boolean needsEscaping(char var1) {
        if (var1 <= 160) {
            return false;
        } else {
            switch(var1) {
                case '¡':
                    return true;
                case '¢':
                    return true;
                case '£':
                    return true;
                case '¤':
                    return false;
                case '¥':
                    return true;
                case '¦':
                    return true;
                case '§':
                    return false;
                case '¨':
                    return false;
                case '©':
                    return true;
                case 'ª':
                    return true;
                case '«':
                    return true;
                case '¬':
                    return true;
                case '\u00ad':
                    return false;
                case '®':
                    return true;
                case '¯':
                    return false;
                case '°':
                    return false;
                case '±':
                    return true;
                case '²':
                    return true;
                case '³':
                    return true;
                case '´':
                    return false;
                case 'µ':
                    return true;
                case '¶':
                    return true;
                case '·':
                    return true;
                case '¸':
                    return false;
                case '¹':
                    return true;
                case 'º':
                    return true;
                case '»':
                    return true;
                case '¼':
                    return true;
                case '½':
                    return true;
                case '¾':
                    return true;
                case '¿':
                    return true;
                case 'À':
                    return true;
                case 'Á':
                    return false;
                case 'Â':
                    return false;
                case 'Ã':
                    return false;
                case 'Ä':
                    return false;
                case 'Å':
                    return false;
                case 'Æ':
                    return false;
                case 'Ç':
                    return true;
                case 'È':
                    return true;
                case 'É':
                    return false;
                case 'Ê':
                    return true;
                case 'Ë':
                    return false;
                case 'Ì':
                    return true;
                case 'Í':
                    return false;
                case 'Î':
                    return false;
                case 'Ï':
                    return true;
                case 'Ð':
                    return true;
                case 'Ñ':
                    return true;
                case 'Ò':
                    return true;
                case 'Ó':
                    return true;
                case 'Ô':
                    return false;
                case 'Õ':
                    return false;
                case 'Ö':
                    return false;
                case '×':
                    return false;
                case 'Ø':
                    return false;
                case 'Ù':
                    return true;
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
                    return true;
                case 'á':
                    return false;
                case 'â':
                    return false;
                case 'ã':
                    return false;
                case 'ä':
                    return false;
                case 'å':
                    return false;
                case 'æ':
                    return false;
                case 'ç':
                    return true;
                case 'è':
                    return true;
                case 'é':
                    return false;
                case 'ê':
                    return true;
                case 'ë':
                    return false;
                case 'ì':
                    return true;
                case 'í':
                    return false;
                case 'î':
                    return false;
                case 'ï':
                    return true;
                case 'ð':
                    return true;
                case 'ñ':
                    return true;
                case 'ò':
                    return true;
                case 'ó':
                    return true;
                case 'ô':
                    return false;
                case 'õ':
                    return false;
                case 'ö':
                    return false;
                case '÷':
                    return false;
                case 'ø':
                    return false;
                case 'ù':
                    return true;
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
                    return true;
                case 'Ā':
                    return false;
                case 'ā':
                    return false;
                case 'Ă':
                    return true;
                case 'ă':
                    return true;
                case 'Ą':
                    return false;
                case 'ą':
                    return false;
                case 'Ć':
                    return true;
                case 'ć':
                    return true;
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
                    return false;
                case 'ē':
                    return false;
                case 'Ĕ':
                    return true;
                case 'ĕ':
                    return true;
                case 'Ė':
                    return false;
                case 'ė':
                    return false;
                case 'Ę':
                    return false;
                case 'ę':
                    return false;
                case 'Ě':
                case 'ě':
                case 'Ĝ':
                case 'ĝ':
                case 'Ğ':
                case 'ğ':
                default:
                    switch(var1) {
                        case 'ˇ':
                            return false;
                        case 'ˈ':
                            return true;
                        case 'ˉ':
                            return true;
                        case 'ˊ':
                            return true;
                        case 'ˋ':
                            return true;
                        case 'ˌ':
                            return true;
                        case 'ˍ':
                            return true;
                        case 'ˎ':
                            return true;
                        case 'ˏ':
                            return true;
                        case 'ː':
                            return true;
                        case 'ˑ':
                            return true;
                        case '˒':
                            return true;
                        case '˓':
                            return true;
                        case '˔':
                            return true;
                        case '˕':
                            return true;
                        case '˖':
                            return true;
                        case '˗':
                            return true;
                        case '˘':
                            return true;
                        case '˙':
                            return false;
                        case '˚':
                            return true;
                        case '˛':
                            return false;
                        default:
                            return true;
                    }
                case 'Ġ':
                    return true;
                case 'ġ':
                    return true;
                case 'Ģ':
                    return false;
                case 'ģ':
                    return false;
                case 'Ĥ':
                    return true;
                case 'ĥ':
                    return true;
                case 'Ħ':
                    return true;
                case 'ħ':
                    return true;
                case 'Ĩ':
                    return false;
                case 'ĩ':
                    return false;
                case 'Ī':
                    return false;
                case 'ī':
                    return false;
                case 'Ĭ':
                    return true;
                case 'ĭ':
                    return true;
                case 'Į':
                    return false;
                case 'į':
                    return false;
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
                    return false;
                case 'ķ':
                    return false;
                case 'ĸ':
                    return false;
                case 'Ĺ':
                    return true;
                case 'ĺ':
                    return true;
                case 'Ļ':
                    return false;
                case 'ļ':
                    return false;
                case 'Ľ':
                    return true;
                case 'ľ':
                    return true;
                case 'Ŀ':
                    return true;
                case 'ŀ':
                    return true;
                case 'Ł':
                    return true;
                case 'ł':
                    return true;
                case 'Ń':
                    return true;
                case 'ń':
                    return true;
                case 'Ņ':
                    return false;
                case 'ņ':
                    return false;
                case 'Ň':
                    return true;
                case 'ň':
                    return true;
                case 'ŉ':
                    return true;
                case 'Ŋ':
                    return false;
                case 'ŋ':
                    return false;
                case 'Ō':
                    return false;
                case 'ō':
                    return false;
                case 'Ŏ':
                    return true;
                case 'ŏ':
                    return true;
                case 'Ő':
                    return true;
                case 'ő':
                    return true;
                case 'Œ':
                    return true;
                case 'œ':
                    return true;
                case 'Ŕ':
                    return true;
                case 'ŕ':
                    return true;
                case 'Ŗ':
                    return false;
                case 'ŗ':
                    return false;
                case 'Ř':
                    return true;
                case 'ř':
                    return true;
                case 'Ś':
                    return true;
                case 'ś':
                    return true;
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
                    return false;
                case 'ŧ':
                    return false;
                case 'Ũ':
                    return false;
                case 'ũ':
                    return false;
                case 'Ū':
                    return false;
                case 'ū':
                    return false;
                case 'Ŭ':
                    return true;
                case 'ŭ':
                    return true;
                case 'Ů':
                    return true;
                case 'ů':
                    return true;
                case 'Ű':
                    return true;
                case 'ű':
                    return true;
                case 'Ų':
                    return false;
                case 'ų':
                    return false;
                case 'Ŵ':
                    return true;
                case 'ŵ':
                    return true;
                case 'Ŷ':
                    return true;
                case 'ŷ':
                    return true;
                case 'Ÿ':
                    return true;
                case 'Ź':
                    return true;
                case 'ź':
                    return true;
                case 'Ż':
                    return true;
                case 'ż':
                    return true;
                case 'Ž':
                    return false;
                case 'ž':
                    return false;
            }
        }
    }
}
