package com.packer.commons.sms.crypto;

import com.packer.commons.sms.exception.CryptoExcetion;
import com.packer.commons.sms.jce.JceBase;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;



public final class CryptoUtil extends JceBase {
    private CryptoUtil() {
    }

    public static byte[] encrypt(String transformation, byte[] key, byte[] data, byte[] iv) {
        try {
            String algorithm = transformation.split("/")[0];
            SecretKeySpec deskey = new SecretKeySpec(key, algorithm);
            Cipher c = Cipher.getInstance(transformation, bc);
            if (iv == null) {
                c.init(1, deskey);
            } else {
                AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
                c.init(1, deskey, paramSpec);
            }

            byte[] r = c.doFinal(data);
            return r;
        } catch (Exception var9) {
            throw new CryptoExcetion(var9);
        }
    }

    public static byte[] decrypt(String transformation, byte[] key, byte[] data, byte[] iv) {
        try {
            String algorithm = transformation.split("/")[0];
            SecretKeySpec deskey = new SecretKeySpec(key, algorithm);
            Cipher c = Cipher.getInstance(transformation, bc);
            if (iv == null) {
                c.init(2, deskey);
            } else {
                AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
                c.init(2, deskey, paramSpec);
            }

            byte[] r = c.doFinal(data);
            return r;
        } catch (Exception var9) {
            throw new CryptoExcetion(var9);
        }
    }
}
