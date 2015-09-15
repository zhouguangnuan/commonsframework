package cn.singno.commonsframework.exception;

import cn.singno.commonsframework.constants.DescribableEnum;

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
@SuppressWarnings("serial")
public class ValidatingException extends DescribableException{

	public ValidatingException(DescribableEnum exceptionDescribable) {
		super(exceptionDescribable);
	}
	
	public ValidatingException(DescribableEnum exceptionDescribable, String errorDetails) {
		super(exceptionDescribable, errorDetails);
	}
}
