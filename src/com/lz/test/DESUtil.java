package com.lz.test;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException; 
import java.security.NoSuchAlgorithmException; 
import java.security.SecureRandom; 
import java.security.spec.InvalidKeySpecException;   
import javax.crypto.BadPaddingException; 
import javax.crypto.Cipher; 
import javax.crypto.IllegalBlockSizeException; 
import javax.crypto.KeyGenerator; 
import javax.crypto.NoSuchPaddingException; 
import javax.crypto.SecretKey; 
import javax.crypto.SecretKeyFactory; 
import javax.crypto.spec.DESKeySpec; 

public class DESUtil {
	/** ��ȫ��Կ */
	private String keyData = "1f4$1eg14#23xfdxfsdfsdfds423#gjkd3#sdf2#";

	/**
	 * ���ܣ�����
	 * 
	 * @author lizhen
	 * @date 2017-11-03
	 */
	public DESUtil() {
	}

	/**
	 * ���ܣ�����
	 * 
	 * @author lizhen
	 * @date 2017-11-03
	 * @param keyData
	 *  key
	 */
	public DESUtil(String key) {
		this.keyData = key;
	}

	/**
	 * ���ܣ����� (UTF-8)
	 * 
	 * @author lizhen
	 * @date 2017-11-03
	 * @param source
	 *  Դ�ַ���
	 * @param charSet
	 *  ����
	 * @return String
	 * @throws UnsupportedEncodingException
	 *  �����쳣
	 */
	public String encrypt(String source) throws UnsupportedEncodingException {
		return encrypt(source, "UTF-8");
	}

	/**
	 * 
	 * ���ܣ����� (UTF-8)
	 * 
	 * @author lizhen
	 * @date 2017-11-03
	 * @param encryptedData
	 *  �����ܺ���ַ���
	 * @return String
	 * @throws UnsupportedEncodingException
	 *  �����쳣
	 */
	public String decrypt(String encryptedData)
			throws UnsupportedEncodingException {
		return decrypt(encryptedData, "UTF-8");
	}

	/**
	 * ���ܣ�����
	 * 
	 * @author lizhen
	 * @date 2017-11-03
	 * @param source
	 *  Դ�ַ���
	 * @param charSet
	 *  ����
	 * @return String
	 * @throws UnsupportedEncodingException
	 *  �����쳣
	 */
	public String encrypt(String source, String charSet)
			throws UnsupportedEncodingException {
		String encrypt = null;
		byte[] ret = encrypt(source.getBytes(charSet));
		encrypt = new String(Base64.encode(ret));
		return encrypt;
	}

	/**
	 * 
	 * ���ܣ�����
	 * 
	 * @author lizhen
	 * @date 2017-11-03
	 * @param encryptedData
	 *  �����ܺ���ַ���
	 * @param charSet
	 *  ����
	 * @return String
	 * @throws UnsupportedEncodingException
	 *  �����쳣
	 */
	public String decrypt(String encryptedData, String charSet)
			throws UnsupportedEncodingException {
		String descryptedData = null;
		byte[] ret = descrypt(Base64.decode(encryptedData.toCharArray()));
		descryptedData = new String(ret, charSet);
		return descryptedData;
	}

	/**
	 * �������� �����ɵ���Կ����ԭʼ����
	 * 
	 * @param primaryData
	 *  ԭʼ����
	 * @return byte[]
	 * @author lizhen
	 * @date 2017-11-03
	 */
	private byte[] encrypt(byte[] primaryData) {

		/** ȡ�ð�ȫ��Կ */
		byte rawKeyData[] = getKey();

		/** DES�㷨Ҫ����һ�������ε������Դ */
		SecureRandom sr = new SecureRandom();

		/** ʹ��ԭʼ��Կ���ݴ���DESKeySpec���� */
		DESKeySpec dks = null;
		try {
			dks = new DESKeySpec(keyData.getBytes());
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}

		/** ����һ����Կ���� */
		SecretKeyFactory keyFactory = null;
		try {
			keyFactory = SecretKeyFactory.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		/** ����Կ������DESKeySpecת����һ��SecretKey���� */
		SecretKey key = null;
		try {
			key = keyFactory.generateSecret(dks);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}

		/** Cipher����ʵ����ɼ��ܲ��� */
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}

		/** ����Կ��ʼ��Cipher���� */
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key, sr);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}

		/** ��ʽִ�м��ܲ��� */
		byte encryptedData[] = null;
		try {
			encryptedData = cipher.doFinal(primaryData);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}

		/** ���ؼ������� */
		return encryptedData;
	}

	/**
	 * ����Կ��������
	 * 
	 * @param encryptedData
	 *  ���ܺ������
	 * @return byte[]
	 * @author lizhen
	 * @date 2017-11-03
	 */
	private byte[] descrypt(byte[] encryptedData) {

		/** DES�㷨Ҫ����һ�������ε������Դ */
		SecureRandom sr = new SecureRandom();

		/** ȡ�ð�ȫ��Կ */
		byte rawKeyData[] = getKey();

		/** ʹ��ԭʼ��Կ���ݴ���DESKeySpec���� */
		DESKeySpec dks = null;
		try {
			dks = new DESKeySpec(keyData.getBytes());
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}

		/** ����һ����Կ���� */
		SecretKeyFactory keyFactory = null;
		try {
			keyFactory = SecretKeyFactory.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		/** ����Կ������DESKeySpecת����һ��SecretKey���� */
		SecretKey key = null;
		try {
			key = keyFactory.generateSecret(dks);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}

		/** Cipher����ʵ����ɼ��ܲ��� */
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}

		/** ����Կ��ʼ��Cipher���� */
		try {
			cipher.init(Cipher.DECRYPT_MODE, key, sr);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}

		/** ��ʽִ�н��ܲ��� */
		byte decryptedData[] = null;
		try {
			decryptedData = cipher.doFinal(encryptedData);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}

		return decryptedData;
	}

	/**
	 * ȡ�ð�ȫ��Կ �˷�������,��Ϊÿ��key���ɶ���һ�����½��ܼ����õ���Կ����һ���� �Ӷ�����Given final block not
	 * properly padded����.
	 * 
	 * @return byte����
	 * @author lizhen
	 * @date 2017-11-03
	 */
	private byte[] getKey() {

		/** DES�㷨Ҫ����һ�������ε������Դ */
		SecureRandom sr = new SecureRandom();

		/** Ϊ����ѡ���DES�㷨����һ����Կ���������� */
		KeyGenerator kg = null;
		try {
			kg = KeyGenerator.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		kg.init(sr);

		/** ������Կ������ */
		SecretKey key = kg.generateKey();

		/** ������Կbyte���� */
		byte rawKeyData[] = key.getEncoded();

		return rawKeyData;
	}

}
