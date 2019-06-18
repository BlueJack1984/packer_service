package com.iot.DEScrypto;

import com.iot.util.HexStr;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class DESCrypto {

	private static String mac(String hexKey, String hexData, String hexIV,
			String alg, String transformation) throws Exception {
		hexData = hexDataPad(hexData, "0000000000000000");
		try {
			SecretKeySpec spec = new SecretKeySpec(HexStr.hexToBuffer(hexKey),
					alg);
			Key deskey = spec;
			Cipher cipher = Cipher.getInstance(transformation);
			cipher.init(Cipher.ENCRYPT_MODE, deskey);

			byte data[] = HexStr.hexToBuffer(hexData);
			byte iv[] = HexStr.hexToBuffer(hexIV);
			for (int i = 0; i < data.length / 8; i++) {
				for (int j = 0; j < 8; j++)
					iv[j] ^= data[i * 8 + j];
				iv = cipher.doFinal(iv);
			}
			return HexStr.bufferToHex(iv);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	private static String des_ecb(String hexKey, String hexData, int opmode,
			String alg, String pading) throws Exception {
	//	hexData = hexDataPad(hexData, "0000000000000000");
		hexData = hexDataPad(hexData, "8000000000000000");    //20171212
		try {
			SecretKeySpec spec = new SecretKeySpec(HexStr.hexToBuffer(hexKey),
					alg);
			Key deskey = spec;
			Cipher cipher = Cipher.getInstance(pading);
			cipher.init(opmode, deskey);
			return HexStr.bufferToHex(cipher.doFinal(HexStr
					.hexToBuffer(hexData)));
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	private static String des_cbc(String hexKey, String hexData, String hexIV,
			int opmode, String alg, String pading) throws Exception {
		hexData = hexDataPad(hexData, "0000000000000000");
		
		try {
			SecretKeySpec spec = new SecretKeySpec(HexStr.hexToBuffer(hexKey),
					alg);
			Key deskey = spec;
			Cipher cipher = Cipher.getInstance(pading);
			cipher.init(opmode, deskey, new IvParameterSpec(HexStr
					.hexToBuffer(hexIV)));
			return HexStr.bufferToHex(cipher.doFinal(HexStr
					.hexToBuffer(hexData)));
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * des/3des ecb
	 * 
	 * @param hexKey -
	 *
	 *
	 * @return
	 * */
	public static String des_ecb_encrypt(String hexKey, String hexData)
			throws Exception {
		int keylength = hexKey.length();
		if (keylength == 16) {
			return des_ecb(hexKey, hexData, Cipher.ENCRYPT_MODE, "DES",
					"DES/ECB/NoPadding");
		} else if (keylength == 32) {
			hexKey += hexKey.substring(0, 16);
			return des_ecb(hexKey, hexData, Cipher.ENCRYPT_MODE, "DESede",
					"DESede/ECB/NoPadding");
		} else if (keylength == 48) {
			return des_ecb(hexKey, hexData, Cipher.ENCRYPT_MODE, "DESede",
					"DESede/ECB/NoPadding");
		} else {
			throw new Exception("error key length " + keylength);
		}
	}

	/**
	 * des/3des ecb 瑙ｅ�?
	 * 
	 * @param hexKey -
	 *
	 *
	 * @return
	 * */
	public static String des_ecb_decrypt(String hexKey, String hexData)
			throws Exception {
		int keylength = hexKey.length();
		if (keylength == 16) {
			return des_ecb(hexKey, hexData, Cipher.DECRYPT_MODE, "DES",
					"DES/ECB/NoPadding");
		} else if (keylength == 32) {
			hexKey += hexKey.substring(0, 16);
			return des_ecb(hexKey, hexData, Cipher.DECRYPT_MODE, "DESede",
					"DESede/ECB/NoPadding");
		} else if (keylength == 48) {
			return des_ecb(hexKey, hexData, Cipher.DECRYPT_MODE, "DESede",
					"DESede/ECB/NoPadding");
		} else {
			throw new Exception("error key length " + keylength);
		}
	}

	/**
	 * des/3des cbc 鍔犲�?
	 * 
	 * @param hexKey -
	 *
	 *
	 * @param hexIV -
	 * */
	public static String des_cbc_encrypt(String hexKey, String hexData,
			String hexIV) throws Exception {
		int keylength = hexKey.length();
		if (keylength == 16) {
			return des_cbc(hexKey, hexData, hexIV, Cipher.ENCRYPT_MODE, "DES",
					"DES/CBC/NoPadding");
		} else if (keylength == 32) {
			hexKey += hexKey.substring(0, 16);
			return des_cbc(hexKey, hexData, hexIV, Cipher.ENCRYPT_MODE,
					"DESede", "DESede/CBC/NoPadding");
		} else if (keylength == 48) {
			return des_cbc(hexKey, hexData, hexIV, Cipher.ENCRYPT_MODE,
					"DESede", "DESede/CBC/NoPadding");
		} else {
			throw new Exception("error key length " + keylength);
		}
	}

	/**
	 * des/3des cbc 瑙ｅ�?
	 * 
	 * @param hexKey -
	 *
	 *
	 * @param hexIV -
	 * */
	public static String des_cbc_decrypt(String hexKey, String hexData,
			String hexIV) throws Exception {
		int keylength = hexKey.length();
		if (keylength == 16) {
			return des_cbc(hexKey, hexData, hexIV, Cipher.DECRYPT_MODE, "DES",
					"DES/CBC/NoPadding");
		} else if (keylength == 32) {
			hexKey += hexKey.substring(0, 16);
			return des_cbc(hexKey, hexData, hexIV, Cipher.DECRYPT_MODE,
					"DESede", "DESede/CBC/NoPadding");
		} else if (keylength == 48) {
			return des_cbc(hexKey, hexData, hexIV, Cipher.DECRYPT_MODE,
					"DESede", "DESede/CBC/NoPadding");
		} else {
			throw new Exception("error key length " + keylength);
		}
	}

	/**
	 * des/3des MAC璁＄�?
	 * 
	 * @param hexKey -
	 *
	 *
	 * */
	public static String des_mac(String hexKey, String hexData, String hexIV)
			throws Exception {		
		int keylength = hexKey.length();
		if (keylength == 16) {
			return mac(hexKey, hexData, hexIV, "DES", "DES/ECB/NoPadding");
		} else if (keylength == 32) {
			String str = mac(hexKey.substring(0, 16), hexData, hexIV, "DES",
					"DES/ECB/NoPadding");
			String str1 = des_ecb_decrypt(hexKey.substring(16, 32), str);
			return des_ecb_encrypt(hexKey.substring(0, 16), str1);
		} else if (keylength == 48) {
			String str = mac(hexKey.substring(0, 16), hexData, hexIV, "DES",
					"DES/ECB/NoPadding");
			String str1 = des_ecb_decrypt(hexKey.substring(16, 32), str);
			return des_ecb_encrypt(hexKey.substring(32, 48), str1);
			// return mac(hexKey, hexData, hexIV, "DESede",
			// "DESede/ECB/NoPadding");
		} else {
			throw new Exception("error key length " + keylength);
		}
	}

	/**
	 * PBOC MAC
	 * 
	 * @param hexKey -
	 *
	 *
	 * */
	public static String pboc_mac(String hexKey, String hexData, String hexIV) throws Exception {
		return des_mac(hexKey, hexData+"80", hexIV);
	}
	
	/**
	 * 3des
	 * 
	 * @param hexKey -
	 *
	 *
	 * 
	 * @return
	 * */
	public static String des_derive(String hexKey, String hexGen)
			throws Exception {
		if ((hexKey.length() != 32 && hexKey.length() != 48)
				|| (hexGen.length() != 16 && hexGen.length() != 32))
			throw new Exception("error length " + hexKey.length() + "/"
					+ hexGen.length());

		if (hexGen.length() == 16) {
			byte data[] = HexStr.hexToBuffer(hexGen);
			byte tmp[] = new byte[16];
			for (int i = 0; i < 8; i++) {
				tmp[i] = data[i];
				tmp[i + 8] = (byte) (data[i] ^ (byte) 0xFF);
			}
			return des_ecb_encrypt(hexKey, HexStr.bufferToHex(tmp));
		} else {
			return des_ecb_encrypt(hexKey, hexGen);
		}
	}

	/**
	 *
	 * 
	 * @param hexData - 
	 *
	 * @param pad -
	 *
	 * 
	 * @return
	 */	
	public static String hexDataPad(String hexData, String pad) {
		if (hexData.length() % 16 != 0) {
			hexData += pad.substring(0, 16 - (hexData.length() % 16));
		}
		return hexData;
	}
	
	/**
	 *
	 * @param hexString
	 * @return
	 */
	public static String xor(String hexString) {
		byte buffer[] = HexStr.hexToBuffer(hexString);
		int len = buffer.length/2;
		for (int i=0; i<len; i++) {
			buffer[i] ^= buffer[i+len];
		}
		return HexStr.bufferToHex(buffer, 0, len);
	}
	
	/**
	 * PIN
	 * @param pik	PIK
	 * @param pin	PIN
	 * @param pan
	 * @return
	 * @throws Exception
	 */
/*	public static String pinCrypto(String pik, String pin, String pan) throws Exception {
		pin = StrUtils.padleft("" + pin.length(), 2, '0')  + pin;
		pin = StrUtils.padright(pin, 16, 'F'); 
		pan = StrUtils.padleft(pan, 16, '0');
		
		String pinBlock = DESCrypto.xor(pin+pan);
		
		return DESCrypto.des_ecb_encrypt(pik, pinBlock);
	}*/
	
	/**
	 *
	 * * @param mak		MAC
	 * @param
	 * @return
	 * @throws Exception
	 */
	/*public static String pos_mac(String mak, String hexData) throws Exception {
		hexData = hexDataPad(hexData, "0000000000000000");

			byte buffer[] = HexStr.hexToBuffer(hexData);


		for (int i = 1; i < buffer.length / 8; i++) {
			for (int j = 0; j < 8; j++)
				buffer[j] ^= buffer[i * 8 + j];
		}


		String iso = HexStr.bufferToHex(buffer, 0, 8);
		iso = HexStr.bufferToHex(iso.getBytes());


		String ENCBLOCK1 = DESCrypto.des_ecb_encrypt(mak, iso.substring(0, 16));


		String TEMPBLOCK = xor(ENCBLOCK1 + iso.substring(16, 32));


		return DESCrypto.des_ecb_encrypt(mak, TEMPBLOCK);
	}*/
	
	public static void main(String[] args) throws Exception {
		System.out.println(DESCrypto.des_ecb_encrypt("1111111111111111", "11111180"));
		try {
			String pik = DESCrypto.des_ecb_decrypt("1234567890123456", "21A92FF253B1BBF2");
			//System.out.println(pinCrypto(pik, "888888", "000000000000") + " 556880F813D8E8E5");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
