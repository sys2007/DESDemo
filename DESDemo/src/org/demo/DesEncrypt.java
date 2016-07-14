package org.demo;

/*
 * DesEncrypt.java
 * 
 * Created on 2007-9-20, 16:10:47
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

//˼·�� ��Ϊ   ����һ���ַ����������������ֽڱ�ʾ�ģ�ÿ���ֽ�ʵ�ʾ���һ��
//              ��8λ�Ľ���������
//      ����Ϊ   һ��8λ����������������λ16�����ַ�����ʾ.
//        ���   ����һ���ַ�����������λ16�����ַ�����ʾ��
//          ��   DES�Ƕ�8λ�����������м��ܣ����ܡ�
//        ����   ��DES���ܽ���ʱ�����԰Ѽ������õ�8λ����������ת��
//               ��λ16���������б��棬���䡣
//    ���巽����1 ��һ���ַ���ת��8λ������������DES���ܣ��õ�8λ����������
//                ����
//              2 Ȼ��ѣ���1�����õ�����ת����λʮ�������ַ���
//              3 ����ʱ���ѣ���2)���õ���λʮ�������ַ�����ת����8λ������
//                ��������
//              4 ����3���õ����ģ���DES���н��ܣ��õ�8λ����������ʽ�����ģ�
//                ��ǿ��ת�����ַ�����
// ˼����ΪʲôҪͨ����λ16�������ַ������������أ�
//       ԭ���ǣ�һ���ַ������ܺ����õ�8λ����������ͨ������ʱ�ַ����ˣ����
//              ֱ�Ӱ������������õ�8λ��������ǿ��ת���ַ������������Ϣ��Ϊ��
//              ������ʧ�����ƽ���ʧ�ܡ�����Ҫ�����8λ��������ֱ����������ʽ
//              ������������ͨ��������λʮ����������ʾ��

import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

/**
 * 
 * ʹ��DES���������,�ɶ�byte[],String���ͽ��м�������� ���Ŀ�ʹ��String,byte[]�洢.
 * 
 * ����: void getKey(String strKey)��strKey����������һ��Key
 * 
 * String getEncString(String strMing)��strMing���м���,����String���� String
 * getDesString(String strMi)��strMin���н���,����String����
 * 
 * byte[] getEncCode(byte[] byteS)byte[]�͵ļ��� byte[] getDesCode(byte[]
 * byteD)byte[]�͵Ľ���
 */

public class DesEncrypt {
	static{
		getKey("bocom9876543210");
	}
	private static Key key;

	/**
	 * ���ݲ�������KEY
	 * 
	 * @param strKey
	 */
	public static void getKey(String strKey) {
		try {
			KeyGenerator _generator = KeyGenerator.getInstance("DES");
			_generator.init(new SecureRandom(strKey.getBytes()));
			key = _generator.generateKey();
			_generator = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����String��������,String�������
	 * 
	 * @param strMing
	 * @return
	 */
	public static String getEncString(String strMing) {
		byte[] byteMi = null;
		byte[] byteMing = null;
		String strMi = "";
		try {
			return byte2hex(getEncCode(strMing.getBytes()));
			// byteMing = strMing.getBytes("UTF8");
			// byteMi = this.getEncCode(byteMing);
			// strMi = new String( byteMi,"UTF8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			byteMing = null;
			byteMi = null;
		}
		return strMi;
	}

	/**
	 * ���� ��String��������,String�������
	 * 
	 * @param strMi
	 * @return
	 */
	public static String getDesString(String strMi) {
		byte[] byteMing = null;
		byte[] byteMi = null;
		String strMing = "";
		try {
			return new String(getDesCode(hex2byte(strMi.getBytes())));
			// byteMing = this.getDesCode(byteMi);
			// strMing = new String(byteMing,"UTF8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			byteMing = null;
			byteMi = null;
		}
		return strMing;
	}

	/**
	 * ������byte[]��������,byte[]�������
	 * 
	 * @param byteS
	 * @return
	 */
	private static byte[] getEncCode(byte[] byteS) {
		byte[] byteFina = null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * ������byte[]��������,��byte[]�������
	 * 
	 * @param byteD
	 * @return
	 */
	private static byte[] getDesCode(byte[] byteD) {
		Cipher cipher;
		byte[] byteFina = null;
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byteFina = cipher.doFinal(byteD);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * ������ת�ַ���
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) { // һ���ֽڵ�����
		// ת��16�����ַ���
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			// ����ת��ʮ�����Ʊ�ʾ
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase(); // ת�ɴ�д
	}

	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("���Ȳ���ż��");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			// ��λһ�飬��ʾһ���ֽ�,��������ʾ��16�����ַ�������ԭ��һ�������ֽ�
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}

		return b2;
	}

	public static void main(String[] args) {

		System.out.println("hello");
		String strEnc = DesEncrypt.getEncString("yjzhrule1");// �����ַ���,����String������
		System.out.println(strEnc);

		String strDes = DesEncrypt.getDesString("E1DDB482DB2E761F2056572D11E2878F");// ��String ���͵����Ľ���
		System.out.println(strDes);
		new DesEncrypt();
	}

}
