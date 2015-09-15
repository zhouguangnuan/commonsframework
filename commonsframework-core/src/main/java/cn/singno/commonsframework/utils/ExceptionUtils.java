package cn.singno.commonsframework.utils;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import cn.singno.commonsframework.exception.DescribableException;

/**
 * <p>名称：ExceptionUtil.java</p>
 * <p>描述：</p>
 * <pre>
 *         
 * </pre>
 * @author 周光暖
 * @date 2014-11-7 下午10:33:07
 * @version 1.0.0
 */
public class ExceptionUtils {
	
	private static String SEPARATOR = "：";// 默认分隔符
	
	/**
	 * 描述：将可描述的异常转为提示信息
	 * 
	 * <pre>
	 * 提示信息格式：
	 *     参数异常 [年龄不能小于18岁，姓名不能为空，性别不存在]
	 * </pre>
	 * 
	 * @param e
	 * @return
	 */
	public static String promptInfo(DescribableException e) {
		return promptInfo(e, true);
	}

	/**
	 * 描述：将可描述的异常转为提示信息
	 * 
	 * <pre>
	 * 提示信息格式：
	 *     hidCode：true		
	 *     		参数异常 [年龄不能小于18岁，姓名不能为空，性别不存在]
	 *     hidCode：false	
	 *     		120002:参数异常 [年龄不能小于18岁，姓名不能为空，性别不存在]
	 * </pre>
	 * 
	 * @param e
	 * @return
	 */
	public static String promptInfo(DescribableException e, boolean hidCode) {
		StringBuffer promptInfo = new StringBuffer();
		if (!hidCode) {
			promptInfo.append(e.getExceptionDescribable().getCode() + SEPARATOR);
		}
		promptInfo.append(StringEscapeUtils.escapeHtml4(e.getMessage()));
		String errorDetails = StringEscapeUtils.escapeHtml4(e.getErrorDetails());
		if (StringUtils.isNotBlank(errorDetails)) {
			if (!StringUtils.startsWith(errorDetails, "[")
					&& !StringUtils.endsWith(errorDetails, "]")) {
				promptInfo.append("[");
			};
			promptInfo.append(errorDetails);
			if (!StringUtils.startsWith(errorDetails, "[")
					&& !StringUtils.endsWith(errorDetails, "]")) {
				promptInfo.append("]");
			};
		}
		return promptInfo.toString();
	}
}
