package com.iot.util;


public class HexStr {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public HexStr() {
    }

    public static String ascToHex(String var0) {
        byte[] var1 = var0.getBytes();
        return bufferToHex(var1);
    }

    public static String hexToASC(String var0) throws NumberFormatException {
        byte[] var1 = hexToBuffer(var0);
        return new String(var1);
    }

    public static String bufferToHex(byte[] var0) {
        return bufferToHex(var0, 0, var0.length).toUpperCase();
    }

    public static String bufferToHex(byte[] var0, int var1, int var2) {
        StringBuffer var3 = new StringBuffer(2 * var2);
        int var4 = var1 + var2;

        for(int var5 = var1; var5 < var4; ++var5) {
            a(var0[var5], var3);
        }

        return var3.toString().toUpperCase();
    }

    public static byte[] hexToBuffer(String var0) throws NumberFormatException {
        int var1 = var0.length();
        if (var1 % 2 != 0) {
            throw new NumberFormatException(b.A_B_C_D("Jpucimc(ams,iejge2\u007fw{") + var1 + b.A_B_C_D("\u000b") + var0);
        } else {
            byte[] var2 = new byte[(var1 + 1) / 2];
            boolean var3 = true;
            byte var4 = 0;
            int var5 = 0;
            if (var1 % 2 == Integer.valueOf(1)) {
                var3 = false;
            }

            for(int var6 = 0; var6 < var1; ++var6) {
                char var7 = var0.charAt(var6);
                int var8;
                if (var7 >= '0' && var7 <= '9') {
                    var8 = var7 - 48;
                } else if (var7 >= 'A' && var7 <= 'F') {
                    var8 = var7 - 65 + 10;
                } else {
                    if (var7 < 'a' || var7 > 'f') {
                        throw new NumberFormatException(b.A_B_C_D("Jpucimc(ams,iejge26") + var7 + b.A_B_C_D("(0"));
                    }

                    var8 = var7 - 97 + 10;
                }

                if (var3) {
                    var4 = (byte)(var8 << 4);
                } else {
                    var4 += (byte)var8;
                    var2[var5++] = var4;
                }

                var3 = !var3;
            }

            return var2;
        }
    }

    private static void a(byte var0, StringBuffer var1) {
        char var2 = a[(var0 & 240) >> 4];
        char var3 = a[var0 & 15];
        var1.append(var2);
        var1.append(var3);
    }

    public static String hexToBinary(String var0) {
        var0 = var0.toUpperCase();
        String var1 = b.A_B_C_D("");
        int var2 = var0.length();

        for(int var3 = 0; var3 < var2; ++var3) {
            char var4 = var0.charAt(var3);
            switch(var4) {
                case '0':
                    var1 = var1 + b.A_B_C_D("1234");
                    break;
                case '1':
                    var1 = var1 + b.A_B_C_D("1233");
                    break;
                case '2':
                    var1 = var1 + b.A_B_C_D("1244");
                    break;
                case '3':
                    var1 = var1 + b.A_B_C_D("1243");
                    break;
                case '4':
                    var1 = var1 + b.A_B_C_D("1134");
                    break;
                case '5':
                    var1 = var1 + b.A_B_C_D("1133");
                    break;
                case '6':
                    var1 = var1 + b.A_B_C_D("1144");
                    break;
                case '7':
                    var1 = var1 + b.A_B_C_D("1143");
                    break;
                case '8':
                    var1 = var1 + b.A_B_C_D("2234");
                    break;
                case '9':
                    var1 = var1 + b.A_B_C_D("2233");
                case ':':
                case ';':
                case '<':
                case '=':
                case '>':
                case '?':
                case '@':
                default:
                    break;
                case 'A':
                    var1 = var1 + b.A_B_C_D("2244");
                    break;
                case 'B':
                    var1 = var1 + b.A_B_C_D("2243");
                    break;
                case 'C':
                    var1 = var1 + b.A_B_C_D("2134");
                    break;
                case 'D':
                    var1 = var1 + b.A_B_C_D("2133");
                    break;
                case 'E':
                    var1 = var1 + b.A_B_C_D("2144");
                    break;
                case 'F':
                    var1 = var1 + b.A_B_C_D("2143");
            }
        }

        return var1;
    }

    public static String longToHex(long var0, int var2) {
        return StrUtils.zeropad(Long.toHexString(var0).toUpperCase(), var2).toUpperCase();
    }
}

