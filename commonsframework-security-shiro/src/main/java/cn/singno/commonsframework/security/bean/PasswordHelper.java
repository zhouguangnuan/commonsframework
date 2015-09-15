/**<p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.security</p>
 * <p>文件名：PasswordHelper.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-3-27-下午2:25:27</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.security.bean;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

/**<p>名称：PasswordHelper.java</p>
 * <p>描述：密码处理帮助类</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-3-27 下午2:25:27
 * @version 1.0.0
 */
public class PasswordHelper
{
	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

	private String hashAlgorithmName = "md5";
	
	private int hashIterations = 2;
	
	/**
	 * <p>描述：获得加密后的密码</p>
	 * <pre>
	 *    将明（文密码 + 盐值）加密生成最终密码
	 * </pre>
	 * @param cleartextPassword
	 * @param salt
	 * @return
	 */
	public String encryptionCleartextPassword(String cleartextPassword, String salt)
	{
	        String encryptedPassword = new SimpleHash(
	        	hashAlgorithmName, 
	                cleartextPassword, 
	                salt,
	                hashIterations).toHex();
		return encryptedPassword;
	}
	
	/**
	 * <p>描述：获得盐值</p>
	 * <pre>
	 *    随机生成盐值
	 * </pre>
	 * @return
	 */
	public String createSalt()
	{
		return randomNumberGenerator.nextBytes().toHex();
	}
	
	
	// =============================================================================================

	/**
	 * @return the hashAlgorithmName
	 */
	public String getHashAlgorithmName()
	{
		return hashAlgorithmName;
	}

	/**
	 * @param hashAlgorithmName the hashAlgorithmName to set
	 */
	public void setHashAlgorithmName(String hashAlgorithmName)
	{
		this.hashAlgorithmName = hashAlgorithmName;
	}

	/**
	 * @return the hashIterations
	 */
	public int getHashIterations()
	{
		return hashIterations;
	}

	/**
	 * @param hashIterations the hashIterations to set
	 */
	public void setHashIterations(int hashIterations)
	{
		this.hashIterations = hashIterations;
	}
}
