package cn.singno.commonsframework.exception;

import cn.singno.commonsframework.constants.Describable;


/**
 * <p>名称：WxException.java</p>
 * <p>描述：微信接口调用异常</p>
 * <pre>
 *         
 * </pre>
 * @author 周光暖
 * @date 2014-11-7 下午10:22:27
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class WxException extends DescribableException
{
	public WxException(Describable describableInfo) {
		super(describableInfo);
	}
	
	public WxException(Describable describableInfo, String errorDetails) {
		super(describableInfo, errorDetails);
	}
}
