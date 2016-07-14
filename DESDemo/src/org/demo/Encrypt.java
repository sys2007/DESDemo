package org.demo;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 
 * @author whzhaochao
 * 
 *         DES���ܽ���
 * 
 */
public class Encrypt {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		// ����������
		String str = "zhddweb";
		// ���룬����Ҫ��8�ı���
		String password = "9876543210";

		String codeStr = CodeStr(str, password);
		System.out.println("ԭʼ���ݣ�" + str);
		System.out.println("�������ݣ�" + codeStr);
		String deCodeStr = DcodeStr(codeStr, password);
		System.out.println("�������ݣ�" + deCodeStr);

	}

	/**
	 * 
	 * @param str
	 *            �������ַ���
	 * @param password
	 *            ����
	 * @return
	 */

	public static String CodeStr(String str, String password) {

		byte[] result = desCrypto(str.getBytes(), password);

		char[] chreslut = new char[result.length];
		for (int i = 0; i < result.length; i++) {
			char ch = (char) result[i];
			chreslut[i] = ch;
		}
		String strres = new String(chreslut);
		return strres;
	}

	/**
	 * 
	 * @param strres
	 *            �������ַ���
	 * @param password
	 *            ����
	 * @return
	 * @throws Exception
	 */
	public static String DcodeStr(String strres, String password)
			throws Exception {
		byte[] decode = new byte[strres.length()];

		for (int i = 0; i < strres.length(); i++) {
			char ch = strres.charAt(i);
			byte b = (byte) ch;
			decode[i] = b;

		}
		byte[] decryResult = decrypt(decode, password);
		return new String(decryResult);
	}

	/**
	 * 
	 * @param datasource
	 * @param password
	 * @return
	 */
	public static byte[] desCrypto(byte[] datasource, String password) {

		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			// ����һ���ܳ׹�����Ȼ��������DESKeySpecת����
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher����ʵ����ɼ��ܲ���
			Cipher cipher = Cipher.getInstance("DES");
			// ���ܳ׳�ʼ��Cipher����
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// ���ڣ���ȡ���ݲ�����
			// ��ʽִ�м��ܲ���
			return cipher.doFinal(datasource);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param src
	 * @param password
	 * @return
	 * @throws Exception
	 */

	private static byte[] decrypt(byte[] src, String password) throws Exception {
		// DES�㷨Ҫ����һ�������ε������Դ
		SecureRandom random = new SecureRandom();
		// ����һ��DESKeySpec����
		DESKeySpec desKey = new DESKeySpec(password.getBytes());
		// ����һ���ܳ׹���
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// ��DESKeySpec����ת����SecretKey����
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher����ʵ����ɽ��ܲ���
		Cipher cipher = Cipher.getInstance("DES");
		// ���ܳ׳�ʼ��Cipher����
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		// ������ʼ���ܲ���
		return cipher.doFinal(src);
	}
}
