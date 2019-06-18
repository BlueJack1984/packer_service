package com.packer.commons.sms.util;

import com.packer.commons.sms.exception.EncodeException;
import com.packer.commons.sms.lang.ByteUtil;
import com.packer.commons.sms.lang.LFStringUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public StringUtil() {
    }

    public static Map<String, String> String2Param(String param) {
        Map<String, String> m = new HashMap();
        if (param != null) {
            String[] params = param.split(",");
            String[] var6 = params;
            int var5 = params.length;

            for(int var4 = 0; var4 < var5; ++var4) {
                String kv = var6[var4];
                int position = kv.indexOf("=");
                if (position != -1) {
                    String key = kv.substring(0, position);
                    String value = kv.substring(position + 1, kv.length());
                    m.put(key, value);
                } else {
                    m.put(kv, "");
                }
            }
        }

        return m;
    }

    public static String getMethod(String pre, String name) {
        return pre + LFStringUtil.upperCaseFirstLetter(name);
    }

    public static String[] parseByRegex(String param) {
        String regex = "^\\{(.*?)\\}(\\((.*)\\))?$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(param);
        return m.matches() ? new String[]{m.group(1), m.group(3)} : null;
    }

    public static String repeat(int time, String s) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < time; ++i) {
            sb.append(s);
        }

        return sb.toString();
    }

    public static void main(String[] str) {
        Map<String, String> temp = String2Param("p");
        Iterator var3 = temp.keySet().iterator();

        while(var3.hasNext()) {
            String k = (String)var3.next();
            System.out.println("key=" + k + " value=" + (String)temp.get(k));
        }

    }
    public static String paddingHeadZero(String str, int totallen) {
        return paddingHead(str, totallen, "0");
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
    public static String paddingTailZero(String str, int totallen) {
        return paddingTail(str, totallen, "0");
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
    public static String paddingTailMutipBy16(String str, String padding) {
        return paddingTailMutipBy(str, 16, padding);
    }
    public static String paddingTailMutipBy(String str, int factor, String padding) {
        if (str != null) {
            int pad = str.length() % factor == 0 ? str.length() : str.length() + factor - str.length() % factor;
            return paddingTail(str, pad, padding);
        } else {
            throw new IllegalArgumentException("data is null");
        }
    }
}
