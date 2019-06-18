//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package nu.xom;

import java.io.Writer;

class ISOThaiWriter extends TextWriter {
    ISOThaiWriter(Writer var1, String var2) {
        super(var1, var2);
    }

    boolean needsEscaping(char var1) {
        if (var1 < 128) {
            return false;
        } else {
            switch(var1) {
                case 'ก':
                    return false;
                case 'ข':
                    return false;
                case 'ฃ':
                    return false;
                case 'ค':
                    return false;
                case 'ฅ':
                    return false;
                case 'ฆ':
                    return false;
                case 'ง':
                    return false;
                case 'จ':
                    return false;
                case 'ฉ':
                    return false;
                case 'ช':
                    return false;
                case 'ซ':
                    return false;
                case 'ฌ':
                    return false;
                case 'ญ':
                    return false;
                case 'ฎ':
                    return false;
                case 'ฏ':
                    return false;
                case 'ฐ':
                    return false;
                case 'ฑ':
                    return false;
                case 'ฒ':
                    return false;
                case 'ณ':
                    return false;
                case 'ด':
                    return false;
                case 'ต':
                    return false;
                case 'ถ':
                    return false;
                case 'ท':
                    return false;
                case 'ธ':
                    return false;
                case 'น':
                    return false;
                case 'บ':
                    return false;
                case 'ป':
                    return false;
                case 'ผ':
                    return false;
                case 'ฝ':
                    return false;
                case 'พ':
                    return false;
                case 'ฟ':
                    return false;
                case 'ภ':
                    return false;
                case 'ม':
                    return false;
                case 'ย':
                    return false;
                case 'ร':
                    return false;
                case 'ฤ':
                    return false;
                case 'ล':
                    return false;
                case 'ฦ':
                    return false;
                case 'ว':
                    return false;
                case 'ศ':
                    return false;
                case 'ษ':
                    return false;
                case 'ส':
                    return false;
                case 'ห':
                    return false;
                case 'ฬ':
                    return false;
                case 'อ':
                    return false;
                case 'ฮ':
                    return false;
                case 'ฯ':
                    return false;
                case 'ะ':
                    return false;
                case 'ั':
                    return false;
                case 'า':
                    return false;
                case 'ำ':
                    return false;
                case 'ิ':
                    return false;
                case 'ี':
                    return false;
                case 'ึ':
                    return false;
                case 'ื':
                    return false;
                case 'ุ':
                    return false;
                case 'ู':
                    return false;
                case 'ฺ':
                    return false;
                default:
                    switch(var1) {
                        case '฿':
                            return false;
                        case 'เ':
                            return false;
                        case 'แ':
                            return false;
                        case 'โ':
                            return false;
                        case 'ใ':
                            return false;
                        case 'ไ':
                            return false;
                        case 'ๅ':
                            return false;
                        case 'ๆ':
                            return false;
                        case '็':
                            return false;
                        case '่':
                            return false;
                        case '้':
                            return false;
                        case '๊':
                            return false;
                        case '๋':
                            return false;
                        case '์':
                            return false;
                        case 'ํ':
                            return false;
                        case '๎':
                            return false;
                        case '๏':
                            return false;
                        case '๐':
                            return false;
                        case '๑':
                            return false;
                        case '๒':
                            return false;
                        case '๓':
                            return false;
                        case '๔':
                            return false;
                        case '๕':
                            return false;
                        case '๖':
                            return false;
                        case '๗':
                            return false;
                        case '๘':
                            return false;
                        case '๙':
                            return false;
                        case '๚':
                            return false;
                        case '๛':
                            return false;
                        default:
                            return true;
                    }
            }
        }
    }
}
