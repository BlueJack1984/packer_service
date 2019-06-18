//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package nu.xom;

import java.io.Writer;

class ISOArabicWriter extends TextWriter {
    ISOArabicWriter(Writer var1, String var2) {
        super(var1, var2);
    }

    boolean needsEscaping(char var1) {
        if (var1 <= 160) {
            return false;
        } else {
            switch(var1) {
                case '،':
                    return false;
                case '؍':
                    return true;
                case '؎':
                    return true;
                case '؏':
                    return true;
                case 'ؐ':
                    return true;
                case 'ؑ':
                    return true;
                case 'ؒ':
                    return true;
                case 'ؓ':
                    return true;
                case 'ؔ':
                    return true;
                case 'ؕ':
                    return true;
                case 'ؖ':
                    return true;
                case 'ؗ':
                    return true;
                case 'ؘ':
                    return true;
                case 'ؙ':
                    return true;
                case 'ؚ':
                    return true;
                case '؛':
                    return false;
                case '\u061c':
                    return true;
                case '\u061d':
                    return true;
                case '؞':
                    return true;
                case '؟':
                    return false;
                case 'ؠ':
                    return true;
                case 'ء':
                    return false;
                case 'آ':
                    return false;
                case 'أ':
                    return false;
                case 'ؤ':
                    return false;
                case 'إ':
                    return false;
                case 'ئ':
                    return false;
                case 'ا':
                    return false;
                case 'ب':
                    return false;
                case 'ة':
                    return false;
                case 'ت':
                    return false;
                case 'ث':
                    return false;
                case 'ج':
                    return false;
                case 'ح':
                    return false;
                case 'خ':
                    return false;
                case 'د':
                    return false;
                case 'ذ':
                    return false;
                case 'ر':
                    return false;
                case 'ز':
                    return false;
                case 'س':
                    return false;
                case 'ش':
                    return false;
                case 'ص':
                    return false;
                case 'ض':
                    return false;
                case 'ط':
                    return false;
                case 'ظ':
                    return false;
                case 'ع':
                    return false;
                case 'غ':
                    return false;
                case 'ػ':
                    return true;
                case 'ؼ':
                    return true;
                case 'ؽ':
                    return true;
                case 'ؾ':
                    return true;
                case 'ؿ':
                    return true;
                case 'ـ':
                    return false;
                case 'ف':
                    return false;
                case 'ق':
                    return false;
                case 'ك':
                    return false;
                case 'ل':
                    return false;
                case 'م':
                    return false;
                case 'ن':
                    return false;
                case 'ه':
                    return false;
                case 'و':
                    return false;
                case 'ى':
                    return false;
                case 'ي':
                    return false;
                case 'ً':
                    return false;
                case 'ٌ':
                    return false;
                case 'ٍ':
                    return false;
                case 'َ':
                    return false;
                case 'ُ':
                    return false;
                case 'ِ':
                    return false;
                case 'ّ':
                    return false;
                case 'ْ':
                    return false;
                default:
                    switch(var1) {
                        case '¤':
                            return false;
                        case '\u00ad':
                            return false;
                        default:
                            return true;
                    }
            }
        }
    }
}
