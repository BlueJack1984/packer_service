package com.packer.commons.sms.jce;

import java.security.Provider;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class JceBase {
    protected static Provider bc = new BouncyCastleProvider();

    public JceBase() {
    }

    protected static String getAlgorithm(CryptoAlg alg, Block block, String padding) {
        return alg.name() + "/" + block.name() + "/" + padding;
    }

    public static enum DigestAlg {
        MD5("MD5"),
        SHA1("SHA-1"),
        SHA256("SHA-256"),
        SHA512("SHA-512");

        private String name;

        private DigestAlg(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static enum SignatureType {
        MD5withRSA,
        SHA1withRSA;

        private SignatureType() {
        }
    }

    public static enum Padding {
        NoPadding("NoPadding"),
        PKCS5Padding("PKCS5Padding"),
        ISO10126Padding("ISO10126Padding"),
        ZeroBytePadding("ZeroBytePadding"),
        X923Padding("X9.23Padding"),
        TBCPadding("TBCPadding"),
        PKCS7Padding("PKCS7Padding"),
        ISO7816d4Padding("ISO7816-4Padding"),
        ISO10126d2Padding("ISO10126-2Padding"),
        PKCS1Padding("PKCS1Padding"),
        ISO9796d1PADDING("ISO9796-1PADDING"),
        OAEPPADDING("OAEPPADDING");

        private String name;

        private Padding(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static enum Block {
        CBC,
        ECB;

        private Block() {
        }
    }

    public static enum CryptoAlg {
        DES,
        DESede,
        AES,
        RSA;

        private CryptoAlg() {
        }
    }
}
