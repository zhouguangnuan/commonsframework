package cn.singno.commonsframework.exception;

import cn.singno.commonsframework.constants.Describable;

/**
 * <p>名称：AuthenticationException.java</p>
 * <p>描述：认证异常</p>
 * <pre>
 *    验证用户是否已登录失败时抛出
 * </pre>
 * @author 周光暖
 * @date 2015-5-10 下午9:22:30
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class AuthenticationException extends DescribableException
{
	public AuthenticationException(Describable describableInfo) {
		super(describableInfo);
	}

	public AuthenticationException(Describable describableInfo, String detail) {
		super(describableInfo, detail);
	}
}