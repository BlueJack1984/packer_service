package com.packer.commons.sms.crypto;


import com.packer.commons.sms.jce.JceBase;
//import com.packer.commons.sms.jce.JceBase.Block;
//import com.packer.commons.sms.jce.JceBase.CryptoAlg;
//import com.packer.commons.sms.jce.JceBase.Padding;
import com.packer.commons.sms.lang.ByteUtil;

public final class LF3DesCryptoUtil extends JceBase {
    private static final String IV = "0000000000000000";

    private LF3DesCryptoUtil() {
    }

    private static byte[] tripledes(Block blk, int mode, byte[] key, byte[] data, Padding pad, byte[] iv) {
        if (key == null || key.length != 16 && key.length != 24) {
            throw new IllegalArgumentException("invalid key length");
        } else {
            if (data.length % 8 == 0) {
                pad = Padding.NoPadding;
            }

            byte[] r = null;
            switch(mode) {
                case 1:
                    r = CryptoUtil.encrypt(getAlgorithm(CryptoAlg.DESede, blk, pad.getName()), key, data, iv);
                    break;
                case 2:
                    r = CryptoUtil.decrypt(getAlgorithm(CryptoAlg.DESede, blk, pad.getName()), key, data, iv);
            }

            return r;
        }
    }

    public static String ecb_encrypt(String keyInhex, String dataInhex, Padding pad) {
        byte[] key = ByteUtil.HEX2Bytes(keyInhex);
        byte[] data = ByteUtil.HEX2Bytes(dataInhex);
        byte[] crypttext = tripledes(Block.ECB, 1, key, data, pad, (byte[])null);
        return ByteUtil.bytes2HEX(crypttext);
    }

    public static String ecb_decrypt(String keyInhex, String dataInhex, Padding pad) {
        byte[] key = ByteUtil.HEX2Bytes(keyInhex);
        byte[] data = ByteUtil.HEX2Bytes(dataInhex);
        byte[] crypttext = tripledes(Block.ECB, 2, key, data, pad, (byte[])null);
        return ByteUtil.bytes2HEX(crypttext);
    }

    public static String cbc_encrypt(String keyInhex, String dataInhex, Padding pad, String ivInhex) {
        byte[] key = ByteUtil.HEX2Bytes(keyInhex);
        byte[] data = ByteUtil.HEX2Bytes(dataInhex);
        byte[] iv;
        if (ivInhex == null) {
            iv = ByteUtil.HEX2Bytes("0000000000000000");
        } else {
            iv = ByteUtil.HEX2Bytes(ivInhex);
        }

        byte[] crypttext = tripledes(Block.CBC, 1, key, data, pad, iv);
        return ByteUtil.bytes2HEX(crypttext);
    }

    public static String cbc_decrypt(String keyInhex, String dataInhex, Padding pad, String ivInhex) {
        byte[] key = ByteUtil.HEX2Bytes(keyInhex);
        byte[] data = ByteUtil.HEX2Bytes(dataInhex);
        byte[] iv;
        if (ivInhex == null) {
            iv = ByteUtil.HEX2Bytes("0000000000000000");
        } else {
            iv = ByteUtil.HEX2Bytes(ivInhex);
        }

        byte[] crypttext = tripledes(Block.CBC, 2, key, data, pad, iv);
        return ByteUtil.bytes2HEX(crypttext);
    }
}

