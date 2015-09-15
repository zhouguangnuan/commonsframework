package cn.singno.commonsframework.exception;

import cn.singno.commonsframework.constants.Describable;


/**
 * <p>名称：ConstraintViolationException.java</p>
 * <p>描述：约束验证异常</p>
 * <pre>
 *         
 * </pre>
 * @author 周光暖
 * @date 2014-11-7 下午10:26:00
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class ConstraintViolationException extends DescribableException{

	public ConstraintViolationException(Describable describableInfo) {
		super(describableInfo);
	}
	
	public ConstraintViolationException(Describable describableInfo, String errorDetails) {
		super(describableInfo, errorDetails);
	}
}
