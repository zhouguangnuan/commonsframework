package cn.singno.commonsframework.exception;

import cn.singno.commonsframework.constants.Describable;

/**
 * API接口参数校验异常
 * @author 周光暖
 */
@SuppressWarnings("all")
public class ApiCheckParameterException extends DescribableException
{
	public ApiCheckParameterException(Describable describableInfo) {
		super(describableInfo);
	}

	public ApiCheckParameterException(Describable describableInfo, String detail) {
		super(describableInfo, detail);
	}
}
