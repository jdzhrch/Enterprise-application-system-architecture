package cipher;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESCipher {
	public static String AESEncode(String content) {
		try {
			String encodeRules = "zhrch";
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			keygen.init(128, new SecureRandom(encodeRules.getBytes()));
			SecretKey original_key = keygen.generateKey();
			byte[] raw = original_key.getEncoded();
			SecretKey key = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] byte_encode = content.getBytes("utf-8");
			byte[] byte_AES = cipher.doFinal(byte_encode);
			String AES_encode = new String(new BASE64Encoder().encode(byte_AES));
			return AES_encode;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String AESDncode(String content) {
		try {
			String encodeRules = "zhrch";
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			keygen.init(128, new SecureRandom(encodeRules.getBytes()));
			SecretKey original_key = keygen.generateKey();
			byte[] raw = original_key.getEncoded();
			SecretKey key = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] byte_content = new BASE64Decoder().decodeBuffer(content);
			byte[] byte_decode = cipher.doFinal(byte_content);
			String AES_decode = new String(byte_decode, "utf-8");
			return AES_decode;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

}