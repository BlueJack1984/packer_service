package com.packer.commons.sms.lang;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import com.packer.commons.sms.exception.EncodeException;

public class LFStringUtil {
    private static String CHAR_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String NUM_SET = "0123456789";
    private static String ALL_SET;
    private static String HEX_SET;

    private LFStringUtil() {
    }

    public static String getRandomHexString(int len) {
        StringBuilder sb = new StringBuilder();
        int i = 0;

        while(i < len) {
            ++i;
            int pos = EncodeUtil.getRandom() % HEX_SET.length();
            sb.append(HEX_SET.charAt(pos));
        }

        return sb.toString();
    }

    public static String getRandomString(int len) {
        StringBuilder sb = new StringBuilder();
        int i = 0;

        while(i < len) {
            ++i;
            int pos = EncodeUtil.getRandom() % ALL_SET.length();
            sb.append(ALL_SET.charAt(pos));
        }

        return sb.toString();
    }

    public static String getRandomCharString(int len) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < len; ++i) {
            int pos = EncodeUtil.getRandom() % CHAR_SET.length();
            sb.append(CHAR_SET.charAt(pos));
        }

        return sb.toString();
    }

    public static String getRandomNumString(int len) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < len; ++i) {
            int pos = EncodeUtil.getRandom() % NUM_SET.length();
            sb.append(NUM_SET.charAt(pos));
        }

        return sb.toString();
    }

    public static String string2ASCII(String str) {
        if (str == null) {
            throw new IllegalArgumentException("data is null");
        } else {
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < str.length(); ++i) {
                char c = str.charAt(i);
                if (c < ' ' || c > '~') {
                    throw new EncodeException("String contains charactor no-ASCII OR is invisible ");
                }

                sb.append(Integer.toHexString(c).toUpperCase());
            }

            return sb.toString();
        }
    }

    public static String ascii2String(String asciiStringInHex) {
        StringBuilder sb = new StringBuilder();
        if (asciiStringInHex != null && asciiStringInHex.length() % 2 == 0) {
            StringReader reader = new StringReader(asciiStringInHex);
            char[] cbuf = new char[2];

            try {
                while(reader.read(cbuf) != -1) {
                    int v = Integer.parseInt(new String(cbuf), 16);
                    if (v < 32 || v > 126) {
                        throw new EncodeException("String contains charactor no-ASCII OR is invisible ");
                    }

                    char c = (char)v;
                    sb.append(c);
                }
            } catch (Exception var6) {
                throw new EncodeException(var6);
            }

            return sb.toString();
        } else {
            throw new IllegalArgumentException("String is null OR  String's length must be divide exactly by 2");
        }
    }

    public static String removeInvisibleChar(String str) {
        if (str != null) {
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < str.length(); ++i) {
                char c = str.charAt(i);
                if (c >= ' ' && c <= '~') {
                    sb.append(c);
                }
            }

            return sb.toString();
        } else {
            throw new IllegalArgumentException("data is null");
        }
    }

    public static String string2ADN(String numstr) {
        if (numstr == null) {
            throw new IllegalArgumentException("data is null");
        } else {
            if (numstr.length() % 2 == 1) {
                numstr = numstr + "F";
            }

            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < numstr.length(); i += 2) {
                String temp = numstr.substring(i, i + 2);
                sb.append(temp.charAt(1));
                sb.append(temp.charAt(0));
            }

            return sb.toString();
        }
    }

    public static String adn2string(String numberInAdn) {
        if (numberInAdn == null) {
            throw new IllegalArgumentException("data is null");
        } else if (numberInAdn.length() % 2 == 1) {
            throw new IllegalArgumentException("the adn string's length can't be odd");
        } else {
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < numberInAdn.length(); i += 2) {
                String temp = numberInAdn.substring(i, i + 2);
                sb.append(temp.charAt(1));
                sb.append(temp.charAt(0));
            }

            return trimTail(sb.toString(), "F");
        }
    }

    public static String paddingHead(String str, int totallen, String padding) {
        if (str == null) {
            throw new IllegalArgumentException("data is null");
        } else if (str.length() != totallen && Assert.isEmpty(padding)) {
            throw new IllegalArgumentException("the original string:" + str + " the totallen:" + totallen + " the padding:[" + padding + "]");
        } else {
            int loop = totallen - str.length();
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < loop; i += padding.length()) {
                sb.append(padding);
            }

            sb.append(str);
            if (sb.length() != totallen) {
                throw new IllegalArgumentException("the padding:" + padding + " can't completely pad the str:" + str + " to the len:" + totallen);
            } else {
                return sb.toString();
            }
        }
    }

    public static String paddingTail(String str, int totallen, String padding) {
        if (str == null) {
            throw new IllegalArgumentException("data is null");
        } else if (str.length() != totallen && Assert.isEmpty(padding)) {
            throw new IllegalArgumentException("the original string:" + str + " the totallen:" + totallen + " the padding:[" + padding + "]");
        } else {
            int loop = totallen - str.length();
            StringBuilder sb = new StringBuilder();
            sb.append(str);

            for(int i = 0; i < loop; i += padding.length()) {
                sb.append(padding);
            }

            if (sb.length() != totallen) {
                throw new IllegalArgumentException("the padding:" + padding + " can't completely pad the str:" + str + " to the len:" + totallen);
            } else {
                return sb.toString();
            }
        }
    }

    public static String paddingTailMutipBy(String str, int factor, String padding) {
        if (str != null) {
            int pad = str.length() % factor == 0 ? str.length() : str.length() + factor - str.length() % factor;
            return paddingTail(str, pad, padding);
        } else {
            throw new IllegalArgumentException("data is null");
        }
    }

    public static String paddingTailMutipBy16(String str, String padding) {
        return paddingTailMutipBy(str, 16, padding);
    }

    public static String paddingHeadZero(String str, int totallen) {
        return paddingHead(str, totallen, "0");
    }

    public static String paddingTailZero(String str, int totallen) {
        return paddingTail(str, totallen, "0");
    }

    public static String trimHead(String str, String trim) {
        return trimHead(str, trim, 0);
    }

    public static String trimHead(String str, String trim, int minLen) {
        if (str != null && trim != null) {
            if (str.length() != minLen && Assert.isEmpty(trim)) {
                throw new IllegalArgumentException("the original string:" + str + " the minLen:" + minLen + " the trim:[" + trim + "]");
            } else {
                while(str.startsWith(trim) && str.length() > minLen) {
                    str = str.substring(trim.length());
                }

                return str;
            }
        } else {
            throw new IllegalArgumentException("data is null");
        }
    }

    public static String trimTail(String str, String trim) {
        return trimTail(str, trim, 0);
    }

    public static String trimTail(String str, String trim, int minLen) {
        if (str != null && trim != null) {
            if (str.length() != minLen && Assert.isEmpty(trim)) {
                throw new IllegalArgumentException("the original string:" + str + " the minLen:" + minLen + " the trim:[" + trim + "]");
            } else {
                while(str.endsWith(trim) && str.length() > minLen) {
                    str = str.substring(0, str.length() - trim.length());
                }

                return str;
            }
        } else {
            throw new IllegalArgumentException("data is null");
        }
    }

    public static String trimHeadZero(String str) {
        return trimHead(str, "0");
    }

    public static String trimTailZero(String str) {
        return trimTail(str, "0");
    }

    public static String upperCaseFirstLetter(String str) {
        if (str != null && str.length() >= 1) {
            StringBuilder sb = new StringBuilder();
            sb.append(str.substring(0, 1).toUpperCase()).append(str.substring(1));
            return sb.toString();
        } else {
            throw new IllegalArgumentException("str is null or is not contains any char");
        }
    }

    public static String asc2hex(String ascStr) {
        if (ascStr != null) {
            byte[] bytes;
            try {
                bytes = ascStr.getBytes("ISO8859-1");
            } catch (Exception var3) {
                throw new EncodeException(var3);
            }

            return ByteUtil.bytes2HEX(bytes);
        } else {
            throw new IllegalArgumentException("data is null");
        }
    }

    public static String hex2asc(String hex) {
        try {
            String r = new String(ByteUtil.HEX2Bytes(hex), "ISO8859-1");
            return r;
        } catch (UnsupportedEncodingException var3) {
            throw new EncodeException(var3);
        }
    }

    static {
        ALL_SET = NUM_SET + CHAR_SET;
        HEX_SET = "0123456789ABCDEF";
    }
}
