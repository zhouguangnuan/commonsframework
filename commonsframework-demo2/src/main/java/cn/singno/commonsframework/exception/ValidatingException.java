package cn.singno.commonsframework.exception;

import cn.singno.commonsframework.constants.Describable;

/**
 * <p>名称：ValidatingException.java</p>
 * <p>描述：</p>
 * <pre>
 *         
 * </pre>
 * @author 周光暖
 * @date 2014-11-7 下午10:26:00
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class ValidatingException extends DescribableException{

	public ValidatingException(Describable describable) {
		super(describable);
	}
	
	public ValidatingException(Describable describable, String errorDetails) {
		super(describable, errorDetails);
	}
}
