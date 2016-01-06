package cn.singno.commonsframework.utils;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import cn.singno.commonsframework.constants.DefaultSystemConst;

/**
 * <p>File：EncryptUtils.java</p>
 * <p>Title: 加密算法工具类</p>
 * <p>Description:主要功能：提供对称加密（DES）、非对称加密、散列算法</p>
 * <p>Copyright: Copyright (c) 2015 2015-3-5 上午9:37:44</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
public class fCryptoUtils2
{
	private static final Logger logger = Logger.getLogger(fCryptoUtils2.class);

	// 编码格式
	private static final String CHARSET 		= 		DefaultSystemConst.DEFAULT_UNICODE;

	// 加密算法常量
	public class ALGORITHM
	{
		// 散列算法
		public class HASH
		{
			public static final String SHA_1 			= 		"SHA-1";
			public static final String SHA_256 			= 		"SHA-256";
			public static final String SHA_384			= 		"SHA-384";
			public static final String SHA_512 			= 		"SHA-512";
			public static final String MD2 			= 		"MD2";
			public static final String MD5 			= 		"MD5";
		}
		
		// 对称加密算法
		public class SYMMETRY
		{
			private static final String DES 			= 		"DES";
		}
		
		// 非对称加密算法
		public class ASYMMERTIC
		{
			private static final String RSA 			= 		"RSA";
		}
	}

	// RSA Padding算法
//	private static final String RSA_ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";
	private static final String RSA_ECB_PKCS1_PADDING = "RSA";

	// BouncyCastle加密实现
	private static final Provider provider = new BouncyCastleProvider();

	// 私有构造器，防止类的实例化
	private fCryptoUtils2()
	{
		super();
	}

	/**
	 * <p>描述：散列算法加密（摘要算法）</p>
	 * <pre>
	 *    返回经过散列算法加密的字符串（不可逆）
	 * </pre>
	 * @param 	plaintext		明文
	 * @param 	algorithm 	（SHA-256、SHA-51、MD2、MD5等散列算法）
	 * @return 	ciphertext 	密文
	 */
	public static String HASHencrypt(String plaintext, String algorithm)
	{
		String ciphertext = null;
		byte[] unencodedPlaintext = plaintext.getBytes(Charset.forName(CHARSET));
		MessageDigest md = null;
		try
		{
			md = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e)
		{
			logger.error(e);
		}
		if (null != md)
		{
			md.update(unencodedPlaintext);
			byte[] encodedPlaintext = md.digest();
			StringBuffer buf = new StringBuffer();
			int iLen = encodedPlaintext.length;
			for (int i = 0; i < iLen; i++)
			{
				if ((encodedPlaintext[i] & 0xff) < 0x10)
					buf.append("0");
				buf.append(Long.toString(encodedPlaintext[i] & 0xff, 16));
			}
			ciphertext = buf.toString();
		}
		return ciphertext;
	}

	/**
	 * <p>描述：对称加密</p>
	 * <pre>
	 * 	DES算法实现
	 * </pre>
	 * @param 	plaintext 		明文
	 * @param	secretKey		秘钥
	 * @return 	ciphertext 	密文
	 */
	public static String DESencrypt(String plaintext, String secretKey)
	{
		byte[] byteCiphertext = null;
		byte[] bytePlaintext = null;
		String ciphertext = "";
		try
		{
			bytePlaintext = plaintext.getBytes(CHARSET);
			byteCiphertext = encryptByte(bytePlaintext, secretKey);
			ciphertext = Base64.encodeBase64String(byteCiphertext);
		} catch (Exception e)
		{
			throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
		} finally
		{
			bytePlaintext = null;
			byteCiphertext = null;
		}
		return ciphertext;
	}

	/**
	 * <p>描述：对称解密</p>
	 * <pre>
	 * 	DES算法实现
	 * </pre>
	 * @param 	ciphertext	密文
	 * @param	secretKey		秘钥
	 * @return 	plaintext 		明文
	 */
	public static String DESdecrypt(String ciphertext, String secretKey)
	{
		byte[] bytePlaintext = null;
		byte[] byteCiphertext = null;
		String plaintext = "";
		try
		{
			byteCiphertext = Base64.decodeBase64(ciphertext);
			bytePlaintext = decryptByte(byteCiphertext, secretKey);
			plaintext = new String(bytePlaintext, CHARSET);
		} catch (UnsupportedEncodingException e)
		{
			logger.error(e);
		} finally
		{
			bytePlaintext = null;
			byteCiphertext = null;
		}
		return plaintext;
	}

	/**
	 * <p>描述：生成密匙对</p>
	 * <pre>
	 *    跟具给定长度生成 （公匙、私匙）密匙对
	 * </pre>
	 * @param 	keyLength	密匙长度（大于等于512）
	 * @return	KeyPair	        （公匙、私匙）密匙对
	 */
	public static KeyPair generateRsaKeypair(int keyLength)
	{
		KeyPairGenerator kpg;
		try
		{
			kpg = KeyPairGenerator.getInstance(fCryptoUtils2.ALGORITHM.ASYMMERTIC.RSA);
			kpg.initialize(keyLength);
			KeyPair keyPair = kpg.generateKeyPair();
			return keyPair;
		} catch (NoSuchAlgorithmException e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * <p>描述：非对称加密</p>
	 * <pre>
	 *    根据RSA公钥加密 
	 * </pre>
	 * @param 	bytePlaintext		明文字节数据
	 * @param 	publicKey			RSA公钥
	 * @return	byteCiphertext		密文字节数据
	 * @throws Exception
	 */
	public static byte[] RSAencrypt(byte[] bytePlaintext, PublicKey publicKey) throws Exception
	{
		try
		{
			Cipher cipher = Cipher.getInstance(RSA_ECB_PKCS1_PADDING, provider);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			return cipher.doFinal(bytePlaintext);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <p>描述：非对称解密</p>
	 * <pre>
	 *    根据RSA私匙解密
	 * </pre>
	 * @param 	byteCiphertext		密文字节数据
	 * @param 	privateKey		RSA私匙
	 * @return	bytePlaintext		明文字节数据
	 */
	public static byte[] RSAdecrypt(byte[] byteCiphertext, PrivateKey privateKey)
	{
		try
		{
			Cipher cipher = Cipher.getInstance(RSA_ECB_PKCS1_PADDING, provider);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			int blockSize = cipher.getBlockSize();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(64);
			int j = 0;
			while (byteCiphertext.length - j * blockSize > 0)
			{
				outputStream.write(cipher.doFinal(byteCiphertext, j * blockSize, blockSize));
				j++;
			}
			return outputStream.toByteArray();
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <p>描述：16进制加密</p>
	 * <pre>
	 *    自定义加密算法（明文-16进制转码-反转-异或）
	 * </pre>
	 * @param 	plaintext		明文
	 * @return	ciphertext	密文
	 */
	public static String HEXencrypt(String plaintext)
	{
		String ciphertext = null;
		if (!StringUtils.isBlank(plaintext))
		{
			String hexStr = EncodeUtils.stringToHex(plaintext, CHARSET);
			String reverStr = StringUtils.reverse(hexStr);
			ciphertext = reverStr + EncodeUtils.getXorString(reverStr, CHARSET);
		}
		return ciphertext;
	}
	
	/**
	 * <p>描述：16进制解密</p>
	 * <pre>
	 *    自定义加密算法（明文-16进制转码-反转-异或）
	 * </pre>
	 * @param 	ciphertext	密文
	 * @return	plaintext		明文
	 */
	public static String HEXdecrypt(String ciphertext)
	{
		String plaintext = null;
		if (!StringUtils.isBlank(ciphertext))
		{
			int lenth = ciphertext.length();
			String lastStr = ciphertext.substring(0, lenth - 2);
			String reverStr = StringUtils.reverse(lastStr);
			plaintext = EncodeUtils.hexToString(reverStr, CHARSET);
		}
		return plaintext;
	}

	/**
	 * Return public RSA key modulus
	 * 
	 * @param keyPair
	 *                RSA keys
	 * @return modulus value as hex string
	 */
	public static String getPublicKeyHexModulus(PublicKey publicKey)
	{
		RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
		return rsaPublicKey.getModulus().toString(16);
	}

	/**
	 * Return public RSA key exponent
	 * 
	 * @param keyPair
	 *                RSA keys
	 * @return public exponent value as hex string
	 */
	public static String getPublicKeyHexExponent(KeyPair keyPair)
	{
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		return publicKey.getPublicExponent().toString(16);
	}

	// =========================================================================================

	private static byte[] encryptByte(byte[] byteS, String secretKey)
	{
		byte[] byteFina = null;
		Cipher cipher;
		try
		{
			cipher = Cipher.getInstance(fCryptoUtils2.ALGORITHM.SYMMETRY.DES);
			cipher.init(Cipher.ENCRYPT_MODE, generatorKey(secretKey));
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e)
		{
			throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
		} finally
		{
			cipher = null;
		}
		return byteFina;
	}

	private static byte[] decryptByte(byte[] byteD, String secretKey)
	{
		Cipher cipher;
		byte[] byteFina = null;
		try
		{
			cipher = Cipher.getInstance(fCryptoUtils2.ALGORITHM.SYMMETRY.DES);
			cipher.init(Cipher.DECRYPT_MODE, generatorKey(secretKey));
			byteFina = cipher.doFinal(byteD);
		} catch (Exception e)
		{
			throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
		} finally
		{
			cipher = null;
		}
		return byteFina;
	}

	private static Key generatorKey(String secretKey)
	{
		Key key = null;
		KeyGenerator generator = null;
		try
		{
			generator = KeyGenerator.getInstance(fCryptoUtils2.ALGORITHM.SYMMETRY.DES);
		} catch (NoSuchAlgorithmException e)
		{
			logger.error(e);
		}
		if (null != generator)
		{
			SecureRandom secureRandom = null;
			try
			{
				secureRandom = SecureRandom.getInstance("SHA1PRNG");
			} catch (NoSuchAlgorithmException e)
			{
				logger.error(e);
			}
			secureRandom.setSeed(secretKey.getBytes(Charset.forName(CHARSET)));
			generator.init(secureRandom);
			key = generator.generateKey();
			generator = null;
		}
		return key;
	}
}
