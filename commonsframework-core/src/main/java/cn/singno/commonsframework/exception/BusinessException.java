/**
 * <p>包名：	cn.singno.validatorDemo.exception</p>
 * <p>文件名：BusinessException.java</p>
 * <p>版本信息：</p>
 * <p>日期：2014年7月24日-下午2:51:22</p>
 * Copyright (c) 2014singno公司-版权所有
 */
package cn.singno.commonsframework.exception;

import cn.singno.commonsframework.constants.DescribableEnum;


/**
 * <p>名称：BusinessException.java</p>
 * <p>描述：</p>
 * <pre>
 *         
 * </pre>
 * @author 周光暖
 * @date 2014-11-7 下午10:22:27
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class BusinessException extends DescribableException
{
	public BusinessException(DescribableEnum exceptionDescribable) {
		super(exceptionDescribable);
	}
	
	public BusinessException(DescribableEnum exceptionDescribable, String errorDetails) {
		super(exceptionDescribable, errorDetails);
	}
}
