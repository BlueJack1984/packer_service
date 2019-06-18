package com.iot.util;

import com.packer.commons.sms.crypto.LF3DesCryptoUtil;
import com.packer.commons.sms.jce.JceBase.Padding;

public  class KeyUtil {

    /**
     * 保护密钥（对通信密钥和数据密钥进行3des-cbc加密的密钥，取值为cf10lbruCF10LBRN的asc）
     */
    public static String PROTECT_KEY = "636631306C62726E434631304C42524E";

    /**
     * ki、opc数据库保护密钥
     *
     */
    public static String[] DATA_KEYS = { "bfcad4bf690a9e42467fa68fd2e06ab3",
            "c784062b4d484c77feb5a3562190bb9c",
            "66feab1dc9313a3b7c69a8ae23faaaea",
            "125ecbc442f74303f22828c351f8a31b",
            "424d9b747e5b235fc6644bb6da13341f" };

    /**
     * 用于数据库表加密ki、opc 密钥索引必须在1到5之间
     *
     * @param keyIndex
     * @param data
     * @return
     */
    public static String encryptKIorOPC(int keyIndex, String data) {
        if (keyIndex < 1 || keyIndex > 5) {
            return null;
        }
        String key = DATA_KEYS[keyIndex - 1];
        return LF3DesCryptoUtil.ecb_encrypt(key, data, Padding.NoPadding);
    }

    /**
     * 用于解密数据库表ki、opc 密钥索引必须在1到5之间
     *
     * @param keyIndex
     * @param data
     * @return
     */
    public static String decryptKIorOPC(int keyIndex, String data) {
        if (keyIndex < 1 || keyIndex > 5) {
            return null;
        }
        String key = DATA_KEYS[keyIndex - 1];
        return LF3DesCryptoUtil.ecb_decrypt(key, data, Padding.NoPadding);
    }

    /**
     * 使用保护密钥对密钥密文进行解密，传入的enKey长度必须为8字节整数倍
     *
     * @param enKey
     * @return
     */
    public static String decryptKeyBy3DesEcb(String enKey) {
        return LF3DesCryptoUtil.ecb_decrypt(PROTECT_KEY, enKey,
                Padding.NoPadding);
    }

    /**
     * 使用保护密钥对密钥密文进行加密，传入的enKey长度必须为8字节整数倍
     *
     * @param enKey
     * @return
     */
    public static String encryptKeyBy3DesEcb(String enKey) {
        return LF3DesCryptoUtil.ecb_encrypt(PROTECT_KEY, enKey,
                Padding.NoPadding);
    }


    public static String padding(String data, String pad, int len) {
        while (data.length() % len != 0) {
            data += pad;
        }
        return data;
    }

    public static void main(String[] args) {

        String encKey1="a6e90F439DBE2348D5248F50D84EF9CD";
        String encKey2="2a9c9aa172e99bdda6275c576565d1f7";
        String encKey3="412b121b61c9782fca6b983af29862f6";
        String encKey4="1c81b29b6fede3662aab5d3e81279a5f";
        String encKey5="c7d9bb6528857d387bc9856713644233";

        String commKey1="5E98C1E5488DCE8E5FC7A1682561046C";
        String commKey2="55C3CDADB9C0190EC9C819F89F7A7956";
        String commKey3="8A72CEA8FA20D8170ACB135912E74913";
        String commKey4="0E1408C3D129EC64DA505CB48CD61D57";
        String commKey5="C8E8D07BDA0CF7F087AA7151F7FBD8C1";

        LF3DesCryptoUtil.ecb_encrypt(PROTECT_KEY, commKey5,
                Padding.NoPadding);
//		String encryptedCommKey1 = encryptKeyBy3DesEcb(commKey1);
//		String encryptedCommKey2 = encryptKeyBy3DesEcb(commKey2);
//		String encryptedCommKey3 = encryptKeyBy3DesEcb(commKey3);
//		String encryptedCommKey4 = encryptKeyBy3DesEcb(commKey4);
//		String encryptedCommKey5 = encryptKeyBy3DesEcb(commKey5);
//
//		String encryptedEncKey1 = encryptKeyBy3DesEcb(encKey1);
//		String encryptedEncKey2 = encryptKeyBy3DesEcb(encKey2);
//		String encryptedEncKey3 = encryptKeyBy3DesEcb(encKey3);
//		String encryptedEncKey4 = encryptKeyBy3DesEcb(encKey4);
//		String encryptedEncKey5 = encryptKeyBy3DesEcb(encKey5);
//
//		System.out.println("encryptedCommKey1=" +encryptedCommKey1 +",encryptedCommKey2=" +encryptedCommKey2+
//				",encryptedCommKey3=" +encryptedCommKey3+",encryptedCommKey4=" +encryptedCommKey4
//				+ ",encryptedCommKey5=" +encryptedCommKey5);
//
//		System.out.println("encryptedEncKey1=" +encryptedEncKey1 +",encryptedEncKey2=" +encryptedEncKey2+
//				",encryptedEncKey3=" +encryptedEncKey3+",encryptedEncKey4=" +encryptedEncKey4
//				+ "encryptedEncKey5=" +encryptedEncKey5);

        String ki="99B00127198643E9D3B07B7CAAA712CC";
        String opc="355D9F63FEA19FB91C8BF79C61AAA0E2";

        //加密KI
        ki = ki + "80";
        ki = padding(ki,"0",16);
        String encryptedKi = encryptKIorOPC(1,ki);
        //加密opc
        opc = opc + "80";
        opc = padding(opc,"0",16);
        String encryptedOpc = encryptKIorOPC(1,opc);

        System.out.println("encryptedKi="+encryptedKi+";encryptedOpc="+encryptedOpc);
//        String data += "80";
//			data = this.padding(data, "0", 16);
//			return KeyUtil.encryptKIorOPC(keyIndex, data);
    }
}
