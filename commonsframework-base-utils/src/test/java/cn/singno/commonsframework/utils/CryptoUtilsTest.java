/**<p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.utils</p>
 * <p>文件名：EncryptUtilsTest.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-3-28-下午9:07:35</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.utils;

import static org.junit.Assert.*;

import java.security.KeyPair;

import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.CharSetUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**<p>名称：EncryptUtilsTest.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-3-28 下午9:07:35
 * @version 1.0.0
 */
public class CryptoUtilsTest
{
	private static String secretKey = "singno";
	
	@Test
	public void testHASHencrypt() throws Exception
	{
		String encryptedStr = CryptoUtils.HASHencrypt(SerialUtils.buildRefrenceId(), CryptoUtils.ALGORITHM.HASH.MD5);// ef1fedf5d32ead6b7aaf687de4ed1b71
//		String encryptedStr = CryptoUtils.HASHencrypt("123", CryptoUtils.ALGORITHM.HASH.Base64);
//		String encryptedStr = CryptoUtils.HASHencrypt("123", CryptoUtils.ALGORITHM.HASH.MD2);// ef1fedf5d32ead6b7aaf687de4ed1b71
//		String encryptedStr = CryptoUtils.HASHencrypt("123", CryptoUtils.ALGORITHM.HASH.MD5);// 202cb962ac59075b964b07152d234b70
//		String encryptedStr = CryptoUtils.HASHencrypt("123", CryptoUtils.ALGORITHM.HASH.SHA_1);// 40bd001563085fc35165329ea1ff5c5ecbdbbeef
//		String encryptedStr = CryptoUtils.HASHencrypt("123", CryptoUtils.ALGORITHM.HASH.SHA_256);// a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3
//		String encryptedStr = CryptoUtils.HASHencrypt("123", CryptoUtils.ALGORITHM.HASH.SHA_384);// 9a0a82f0c0cf31470d7affede3406cc9aa8410671520b727044eda15b4c25532a9b5cd8aaf9cec4919d76255b6bfb00f
//		String encryptedStr = CryptoUtils.HASHencrypt("123", CryptoUtils.ALGORITHM.HASH.SHA_512);// 3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2
		System.out.println(encryptedStr);
	}
	
	@Test
	public void testName() throws Exception {
		String plaintext = "100" + System.currentTimeMillis();
		String s = CryptoUtils.HASHencrypt(plaintext, CryptoUtils.ALGORITHM.HASH.MD5);
		System.out.println(s + "，length：" + s.length());
	}
	
	@Test
	public void testDESencrypt_DESdecrypt() throws Exception
	{
		String ciphertext = CryptoUtils.DESencrypt("123", secretKey);
		System.out.println(ciphertext);// Oj6SGTpjju4=
		
		String plaintext = CryptoUtils.DESdecrypt(ciphertext, secretKey);
		System.out.println(plaintext);// 123
	}
	
	@Test
	public void testRSAencrypt_RSAdecrypt() throws Exception
	{
		KeyPair keyPair = CryptoUtils.generateRsaKeypair(512);
		
		System.out.println(CryptoUtils.getPublicKeyHexModulus(keyPair.getPublic()));
		
		
		byte[] bytePlaintext = "你好".getBytes(CharEncoding.UTF_8);
		
		byte[] byteCiphertext = CryptoUtils.RSAencrypt(bytePlaintext, keyPair.getPublic());
		System.out.println(new String(byteCiphertext, CharEncoding.UTF_8));
		
		bytePlaintext = CryptoUtils.RSAdecrypt(byteCiphertext, keyPair.getPrivate());
		System.out.println(new String(bytePlaintext, CharEncoding.UTF_8));
	}
	
	@Test
	public void testname() throws Exception
	{
		String ciphertext = CryptoUtils.HEXencrypt("你好");
		String plaintext = CryptoUtils.HEXdecrypt(ciphertext);
		System.out.println(ciphertext);// DB5A5E0ADB4E04
		System.out.println(plaintext);// 你好
		
//		System.out.println(EncodeUtils.getXorString("A", CharEncoding.UTF_8));// 41
	}
}
