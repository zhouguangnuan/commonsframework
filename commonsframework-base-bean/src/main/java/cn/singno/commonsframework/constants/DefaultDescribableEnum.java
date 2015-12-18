/**<p>项目名：</p>
 * <p>包名：cn.singno.validatorDemo.constants</p>
 * <p>文件名：DefaultDescribableEnum.java</p>
 * <p>版本信息：</p>
 * <p>日期：2014年7月24日-下午3:25:27</p>
 * Copyright (c) 2014singno公司-版权所有
 */
package cn.singno.commonsframework.constants;


/**<p>名称：DefaultDescribableEnum.java</p>
 * <p>描述：默认的可描述枚举</p>
 * <pre>
 *         
 * </pre>
 * @author 周光暖
 * @date 2014年7月24日 下午3:25:27
 * @version 1.0.0
 */
public enum DefaultDescribableEnum implements Describable
{
	// 120000-129999
	SUCCESS(1, "成功"),
	FAIL(120001, "失敗"),
	SYSTEM_ERROR(120002, "系统异常，请稍后重试"),
	AUTHORIZED_ERROR(120003, "没有权限"),
	PARAMES_ERROR(120004, "参数异常"),
	UPLOAD_ERROR(120005, "文件上传异常"),
	DOWNLOAD_ERROR(120006, "文件下载异常"),
	KEY_ERROR(120007, "身份认证失败"),
	DES_ERROR(120008, "异或码校验失败"), 
	LEN_ERROR(120009, "数据长度校验失败"), 
	NUL_ERROR(120010,"缺少参数或参数为空"),
	EXISTS_EMOJI(120011,"输入的内容里存在系统不支持的字符"),
//	API_ERROR_SIGN_ISNULL(120012, "接口验签参数为空"),
	API_ERROR_SIGN_ISERROR(120013, "接口验签参数非法"),
//	API_ERROR_TIME_STAMP_ISNULL(120014, "接口调用时间搓参数为空"),
//	API_ERROR_NONCE_ISERROR(120015, "接口随机秘钥非法"),
//	API_ERROR_NONCE_ISNULL(120016, "接口随机秘钥未初始化"),
	ILLEGAL_REQUEST(120017, "非法请求"),
	
	;
	
	private Integer code;// 描述编码
	private String message;// 描述信息

	private DefaultDescribableEnum(Integer code, String message)
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
