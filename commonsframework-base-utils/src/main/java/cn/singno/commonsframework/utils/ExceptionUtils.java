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
	 * 描述：描述异常
	 * 
	 * <pre>
	 * 提示信息格式：
	 *     参数异常 [年龄不能小于18岁，姓名不能为空，性别不存在]
	 * </pre>
	 * 
	 * @param e
	 * @return
	 */
	public static String description(DescribableException e) {
		return description(e, true);
	}

	/**
	 * 描述：描述异常
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
	public static String description(DescribableException e, boolean hidCode) {
		StringBuffer promptInfo = new StringBuffer();
		if (!hidCode) {
			promptInfo.append(e.getCode() + SEPARATOR);
		}
		promptInfo.append(StringEscapeUtils.escapeHtml4(e.getMessage()));
		String detail = StringEscapeUtils.escapeHtml4(e.getDetail());
		if (StringUtils.isNotBlank(detail)) {
			if (!StringUtils.startsWith(detail, "[")
					&& !StringUtils.endsWith(detail, "]")) { 
				promptInfo.append("[");
			};
			promptInfo.append(detail);
			if (!StringUtils.startsWith(detail, "[")
					&& !StringUtils.endsWith(detail, "]")) {
				promptInfo.append("]");
			};
		}
		return promptInfo.toString();
	}
}
