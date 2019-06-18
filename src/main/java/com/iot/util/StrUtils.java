package com.iot.util;

public class StrUtils {
    public StrUtils() {
    }

    public static String padleft(String var0, int var1, char var2) {
        var0 = var0.trim();
        if (var0.length() > var1) {
            throw new NumberFormatException(b.A_B_C_D("jpucimc(eme,") + var0.length() + b.A_B_C_D("0") + var1);
        } else {
            StringBuffer var3 = new StringBuffer(var1);
            int var4 = var1 - var0.length();

            while(var4-- > 0) {
                var3.append(var2);
            }

            var3.append(var0);
            return var3.toString();
        }
    }

    public static String padright(String var0, int var1, char var2) {
        var0 = var0.trim();
        if (var0.length() > var1) {
            throw new NumberFormatException(b.A_B_C_D("jpucimc(eme,") + var0.length() + b.A_B_C_D("0") + var1);
        } else {
            StringBuffer var3 = new StringBuffer(var1);
            int var4 = var1 - var0.length();
            var3.append(var0);

            while(var4-- > 0) {
                var3.append(var2);
            }

            return var3.toString();
        }
    }

    public static String zeropad(String var0, int var1) {
        return padleft(var0, var1, '0');
    }

    public static String copyFixdString(String var0, int var1) {
        String var2 = var0;

        try {
            var2 = padright(var0, var1, '0');
        } catch (Exception var4) {
        }

        return var2.substring(0, var1);
    }

    public static String copyFixdString(String var0, int var1, char var2) {
        String var3 = var0;

        try {
            var3 = padright(var0, var1, var2);
        } catch (Exception var5) {
        }

        return var3.substring(0, var1);
    }

    public static String replaceEmpty(String var0) {
        return replaceEmpty(var0, b.A_B_C_D(""));
    }

    public static boolean hasLength(String var0) {
        return var0 != null && var0.length() > 0;
    }

    public static String replaceEmpty(String var0, String var1) {
        return !hasLength(var0) ? var1 : var0;
    }

    public static boolean equals(String var0, String var1) {
        return var0 != null && var0 != null ? var0.equals(var1) : false;
    }

    public static String rtrimStr(String var0, String var1) {
        int var2 = var0.length();
        int var3 = var2;

        while(true) {
            int var4 = var0.lastIndexOf(var1, var2 - var1.length());
            if (var4 == -1 || var3 - var4 != var1.length()) {
                return var0.substring(0, var2);
            }

            var2 = var4;
            var3 = var4;
        }
    }

    public static String ltrimStr(String var0, String var1) {
        int var2 = 0;
        int var3 = 0;

        while(true) {
            int var4 = var0.indexOf(var1, var2);
            if (var4 != 0 && (var4 == -1 || var4 - var3 != var1.length())) {
                return var0.substring(var2);
            }

            var2 = var4 + var1.length();
            var3 = var4;
        }
    }

    public static void main(String[] var0) {
        System.out.println(ltrimStr(b.A_B_C_D("NMPO686"), b.A_B_C_D("N")).equals(b.A_B_C_D("242")));
        System.out.println(ltrimStr(b.A_B_C_D("NMPO686"), b.A_B_C_D("NM")).equals(b.A_B_C_D("242")));
        System.out.println(rtrimStr(b.A_B_C_D("2MPOJ555"), b.A_B_C_D("N")).equals(b.A_B_C_D("2MPOJ555")));
        System.out.println(ltrimStr(b.A_B_C_D("N111"), b.A_B_C_D("N")).equals(b.A_B_C_D("242")));
        System.out.println(ltrimStr(b.A_B_C_D("2MPOJ555"), b.A_B_C_D("NM")).equals(b.A_B_C_D("2MPOJ555")));
        System.out.println(rtrimStr(b.A_B_C_D("242O"), b.A_B_C_D("N")).equals(b.A_B_C_D("242")));
        System.out.println(rtrimStr(b.A_B_C_D("242O6"), b.A_B_C_D("N")).equals(b.A_B_C_D("242O6")));
        System.out.println(rtrimStr(b.A_B_C_D("242OJ5"), b.A_B_C_D("NM")).equals(b.A_B_C_D("242OJ5")));
        System.out.println(rtrimStr(b.A_B_C_D("242OJIL"), b.A_B_C_D("N")).equals(b.A_B_C_D("242")));
        System.out.println(rtrimStr(b.A_B_C_D("242OJIL"), b.A_B_C_D("NM")).equals(b.A_B_C_D("242")));
        System.out.println(rtrimStr(b.A_B_C_D("242O5I7"), b.A_B_C_D("N")).equals(b.A_B_C_D("242O5I7")));
        System.out.println(rtrimStr(b.A_B_C_D("242O5I7"), b.A_B_C_D("N2")).equals(b.A_B_C_D("242")));
    }
}
