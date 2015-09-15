/**<p>项目名：</p>
 * <p>包名：	cn.singno.validatorDemo.constants</p>
 * <p>文件名：CommonsErrorConst.java</p>
 * <p>版本信息：</p>
 * <p>日期：2014年7月24日-下午3:25:27</p>
 * Copyright (c) 2014singno公司-版权所有
 */
package cn.singno.commonsframework.constant;

import cn.singno.commonsframework.constants.Describable;


/**<p>名称：CommonsErrorConst.java</p>
 * <p>描述：通用错误常量</p>
 * <pre>
 *         
 * </pre>
 * @author 周光暖
 * @date 2014年7月24日 下午3:25:27
 * @version 1.0.0
 */
public enum ResultStateEnum implements Describable
{
	// 120000-129999
	SUCCESS(120000, "成功"),
	FAIL(120001, "失敗"),
	SYSTEM_ERROR(120002, "系统异常"),
	AUTHORIZED_ERROR(120003, "没有权限"),
	PARAMES_ERROR(120004, "参数异常"),
	UPLOAD_ERROR(120005, "文件上传异常"),
	DOWNLOAD_ERROR(120006, "文件下载异常"),
	KEY_ERROR(120007, "身份认证失败"),
	DES_ERROR(120008, "异或码校验失败"), 
	LEN_ERROR(120009, "数据长度校验失败"), 
	NUL_ERROR(120010,"缺少参数或参数为空"),
	EXISTS_EMOJI(120011,"输入的内容里存在系统不支持的字符")
	;
	
	private Integer code;
	private String message;

	private ResultStateEnum(Integer code, String message)
	{
		this.code = code;
		this.message = message;
	}
	
	public Integer getCode()
	{
		return this.code;
	}

	public String getMessage()
	{
		return this.message;
	}
}
