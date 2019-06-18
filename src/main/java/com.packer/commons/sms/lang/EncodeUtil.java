package com.packer.commons.sms.lang;

import com.packer.commons.sms.exception.EncodeException;
import com.packer.commons.sms.jce.JceBase;
import com.packer.commons.sms.jce.JceBase.DigestAlg;
import com.packer.commons.sms.lang.ByteUtil.ByteOrder;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;

public final class EncodeUtil extends JceBase {
    private static Random r = new Random(System.currentTimeMillis());

    private EncodeUtil() {
    }

    public static int getRandom() {
        return r.nextInt(2147483647);
    }

    public static int getRandom(int n) {
        return (new Float(r.nextFloat() * (float)n)).intValue();
    }

    public static byte[] md5(byte[] data) {
        return digest(data, DigestAlg.MD5);
    }

    public static byte[] sha1(byte[] data) {
        return digest(data, DigestAlg.SHA1);
    }

    public static byte[] sha256(byte[] data) {
        return digest(data, DigestAlg.SHA256);
    }

    public static byte[] sha512(byte[] data) {
        return digest(data, DigestAlg.SHA512);
    }

    public static byte[] digest(byte[] data, DigestAlg alg) {
        if (data != null) {
            try {
                MessageDigest md = MessageDigest.getInstance(alg.getName(), bc);
                byte[] b = md.digest(data);
                return b;
            } catch (Exception var4) {
                throw new EncodeException("MessageDigest error occured:" + var4.getMessage(), var4);
            }
        } else {
            throw new IllegalArgumentException("data is null");
        }
    }

    public static byte[] digestFile(File file, DigestAlg alg) {
        if (file != null && file.isFile() && file.exists()) {
            FileInputStream in = null;

            try {
                MessageDigest md = MessageDigest.getInstance(alg.getName(), bc);
                in = new FileInputStream(file);
                byte[] buffer = new byte[256];
                boolean var5 = false;

                int readed;
                while((readed = in.read(buffer)) != -1) {
                    md.update(buffer, 0, readed);
                }

                byte[] result = md.digest();
                in.close();
                byte[] var7 = result;
                return var7;
            } catch (Exception var16) {
                throw new EncodeException("MessageDigest error occured:" + var16.getMessage(), var16);
            } finally {
                try {
                    in.close();
                } catch (Exception var15) {
                }

            }
        } else {
            throw new IllegalArgumentException("file is null or not a valid file");
        }
    }

    protected static byte[] generaterandom(int rdmLength) {
        if (rdmLength != 0) {
            SecureRandom sr = null;

            try {
                sr = SecureRandom.getInstance("SHA1PRNG");
                byte[] resBuf = new byte[rdmLength];
                sr.nextBytes(resBuf);
                return resBuf;
            } catch (Exception var3) {
                throw new EncodeException("generating random number error");
            }
        } else {
            throw new IllegalArgumentException("invalid random length");
        }
    }

    public static String encodeLVUnicode(String msg) {
        return encodeLVUnicode(msg, 1);
    }

    public static String decodeLVUnicode(String msg) {
        return decodeLVUnicode(msg, 1);
    }

    public static String encodeLVUnicode(String msg, int length) {
        String unicode = ByteUtil.string2UnicodeInHex(msg, ByteOrder.BIG_ENDING);
        StringBuffer sb = new StringBuffer();
        String l = Integer.toHexString(unicode.length() / 2);
        sb.append(LFStringUtil.paddingHeadZero(l.toUpperCase(), length * 2));
        sb.append(unicode);
        return sb.toString();
    }

    public static String decodeLVUnicode(String msg, int length) {
        if (msg != null && msg.length() > length * 2) {
            String unicode = msg.substring(length * 2);

            try {
                String s = new String(ByteUtil.HEX2Bytes(unicode), "UTF-16BE");
                return s;
            } catch (UnsupportedEncodingException var5) {
                throw new EncodeException(var5.getMessage(), var5);
            }
        } else {
            throw new IllegalArgumentException("data is invalid");
        }
    }

    public static String encodeL80VUnicode(String msg, int length) {
        String unicode = ByteUtil.string2UnicodeInHex(msg, ByteOrder.BIG_ENDING);
        StringBuffer sb = new StringBuffer();
        sb.append(LFStringUtil.paddingHeadZero(Integer.toHexString(unicode.length() / 2 + 1), length * 2));
        sb.append("80").append(unicode);
        return sb.toString();
    }

    public static String decodeL80VUnicode(String msg, int length) {
        if (msg != null && msg.length() > (length + 1) * 2) {
            String unicode = msg.substring((length + 1) * 2);

            try {
                String s = new String(ByteUtil.HEX2Bytes(unicode), "UTF-16BE");
                return s;
            } catch (UnsupportedEncodingException var5) {
                throw new EncodeException(var5.getMessage(), var5);
            }
        } else {
            throw new IllegalArgumentException("data is invalid");
        }
    }
}
