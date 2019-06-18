package com.packer.commons.sms.lang;

import com.packer.commons.sms.exception.EncodeException;
import java.io.UnsupportedEncodingException;


public final class ByteUtil {
    public static final char[] HEX = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private ByteUtil() {
    }

    public static String bytes2HEX(byte[] bytes) {
        return bytes2HEX(bytes, 0, bytes == null ? 0 : bytes.length);
    }

    public static String bytes2HEX(byte[] bytes, int start, int offset) {
        if (bytes == null) {
            throw new IllegalArgumentException("data is null");
        } else {
            StringBuffer sb = new StringBuffer();

            for(int i = start; i < start + offset; ++i) {
                sb.append(HEX[bytes[i] >> 4 & 15]);
                sb.append(HEX[bytes[i] & 15]);
            }

            return sb.toString();
        }
    }

    public static byte[] HEX2Bytes(String hexstr) {
        if (hexstr != null && hexstr.length() % 2 == 0) {
            byte[] bytes = new byte[hexstr.length() / 2];

            for(int i = 0; i < hexstr.length() / 2; ++i) {
                String bytestr = hexstr.substring(2 * i, 2 * i + 2);
                bytes[i] = (byte)Integer.parseInt(bytestr, 16);
            }

            return bytes;
        } else {
            throw new IllegalArgumentException("data is null or is not mutiple by 2");
        }
    }

    public static String byte2HEX(byte b) {
        StringBuffer sb = new StringBuffer();
        int high = b >> 4 & 15;
        int low = b & 15;
        sb.append(HEX[high]);
        sb.append(HEX[low]);
        return sb.toString();
    }

    public static String string2UnicodeInHex(String str, ByteOrder bo) {
        if (str != null) {
            Object var2 = null;

            byte[] bytes;
            try {
                bytes = str.getBytes("utf-16BE");
            } catch (UnsupportedEncodingException var4) {
                throw new EncodeException("Unsupported Encoding", var4);
            }

            String hexstrinbe = bytes2HEX(bytes);
            switch(bo) {
                case LETTLE_ENDING:
                    return exchangeTwoByteInHex(hexstrinbe);
                case BIG_ENDING:
                    return hexstrinbe;
                default:
                    return null;
            }
        } else {
            throw new IllegalArgumentException("data is null");
        }
    }

    public static String exchangeTwoByteInHex(String hexStr) {
        if (hexStr != null && hexStr.length() % 2 == 0 && hexStr.length() >= 4) {
            StringBuffer sb = new StringBuffer();
            char[] hexchars = hexStr.toCharArray();

            for(int i = 0; i < hexchars.length; i += 4) {
                sb.append(hexchars[i + 2]);
                sb.append(hexchars[i + 3]);
                sb.append(hexchars[i + 0]);
                sb.append(hexchars[i + 1]);
            }

            return sb.toString();
        } else {
            throw new IllegalArgumentException("Argument is invalid");
        }
    }

    public static class BOM {
        public static String UTF_16_LE = "FFFE";
        public static String UTF_16_BE = "FEFF";
        public static String UTF_8 = "EFBBBF";

        public BOM() {
        }
    }

    public static enum ByteOrder {
        BIG_ENDING,
        LETTLE_ENDING;

        private ByteOrder() {
        }
    }
}
