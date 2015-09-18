package cn.singno.commonsframework.bean;

import java.io.Serializable;
import java.util.Map;

import cn.singno.commonsframework.constants.Describable;

/**
 * <p>名称：JsonResult.java</p>
 * <p>描述：结果对象</p>
 * <pre>
 *    封装Http请求处理返回的Jsont结果对象
 * </pre>
 * @author 周光暖
 * @date 2015-3-30 下午9:38:30
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class JsonResult<T> implements Serializable 
{
	
	public JsonResult() 
	{
	}

	public JsonResult(Object code, String message) 
	{
		this.code = code;
		this.message = message;
	}
	
	public JsonResult(Describable describableInfo) 
	{
		this.code = describableInfo.getCode();
		this.message = describableInfo.getMessage();
	}
	
	public JsonResult(Describable describableInfo, Map<String, Object> data) 
	{
		this.code = describableInfo.getCode();
		this.message = describableInfo.getMessage();
		this.data = data;
	}
	
	public JsonResult(Describable describableInfo, T page)
	{
		this.code = describableInfo.getCode();
		this.message = describableInfo.getMessage();
		this.page = page;
	}
	
	public JsonResult(Describable describableInfo, T page, Map<String, Object> data) 
	{
		this.code = describableInfo.getCode();
		this.message = describableInfo.getMessage();
		this.page = page;
		this.data = data;
	}
	
	private Object code;// 描述代码

	private String message;// 描述信息

	private Map<String, Object> data;// 提供内容封装
	
	private T page;// 分页对象

	public Object getCode() {
		return code;
	}

	public void setCode(Object code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public T getPage() {
		return page;
	}

	public void setPage(T page) {
		this.page = page;
	}
}
