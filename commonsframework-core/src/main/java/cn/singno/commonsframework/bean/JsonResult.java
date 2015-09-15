package cn.singno.commonsframework.bean;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;

import cn.singno.commonsframework.constants.DescribableEnum;

/**
 * <p>名称：JsonResult.java</p>
 * <p>描述：json结果对象</p>
 * <pre>
 *    分装json格式的数据对象，供ajax请求返回数据的封装
 * </pre>
 * @author 周光暖
 * @date 2015-3-30 下午9:38:30
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class JsonResult<T> implements Serializable {
	
	public JsonResult() {
	}

	public JsonResult(Object code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public JsonResult(DescribableEnum enumDescribable) {
		this.code = enumDescribable.getCode();
		this.message = enumDescribable.getMessage();
	}
	
	public JsonResult(DescribableEnum enumDescribable, Object object) {
		this.code = enumDescribable.getCode();
		this.message = enumDescribable.getMessage();
		this.object = object;
	}
	
	public JsonResult(DescribableEnum enumDescribable, Page<T> pageResult) 
	{
		this.code = enumDescribable.getCode();
		this.message = enumDescribable.getMessage();
		if (null != pageResult)
		{
			this.isPageResult = true;
			this.rows = pageResult.getContent();
			this.currentPage = pageResult.getNumber();
			this.pageSize = pageResult.getSize();
			this.numberOfElements = pageResult.getNumberOfElements();
			this.totalPages = pageResult.getTotalPages();
			this.totalElements = pageResult.getTotalElements();
			this.hasPreviousPage = pageResult.hasPreviousPage();
			this.hasNextPage = pageResult.hasNextPage();
			this.isFirstPage = pageResult.isFirstPage();
			this.isLastPage = pageResult.isLastPage();
		}
	}
	
	public JsonResult(DescribableEnum enumDescribable, Page<T> pageResult, Object object) 
	{
		this.code = enumDescribable.getCode();
		this.message = enumDescribable.getMessage();
		if (null != pageResult)
		{
			this.isPageResult = true;
			this.rows = pageResult.getContent();
			this.currentPage = pageResult.getNumber();
			this.pageSize = pageResult.getSize();
			this.numberOfElements = pageResult.getNumberOfElements();
			this.totalPages = pageResult.getTotalPages();
			this.totalElements = pageResult.getTotalElements();
			this.hasPreviousPage = pageResult.hasPreviousPage();
			this.hasNextPage = pageResult.hasNextPage();
			this.isFirstPage = pageResult.isFirstPage();
			this.isLastPage = pageResult.isLastPage();
		}
		this.object = object;
	}
	
	private Object code;// 状态码

	private String message;// 状态描述信息

	private Object object;// 提供内容封装
	
	private Boolean isPageResult = false;// 是否是分页请求结果

	// 分页查询结果相关信息====================================================
	private List<?> rows;// 分页列表内容

	private Integer currentPage;// 当前页（0表示第一页）
	
	private Integer pageSize;// 分页尺码（每页显示的记录数）

	private Integer numberOfElements;// 当前页的记录数
	
	private Integer totalPages;// 总页数
	
	private Long totalElements;// 总记录数
	
	private Boolean hasPreviousPage;// 是否有上一页
	
	private Boolean hasNextPage;// 是否有下一页
	
	private Boolean isFirstPage;// 是否是第一页
	
	private Boolean isLastPage;// 是否是最后一页
	// ===================================================================

	public Object getCode()
	{
		return code;
	}

	public void setCode(Object code)
	{
		this.code = code;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public Object getObject()
	{
		return object;
	}

	public void setObject(Object object)
	{
		this.object = object;
	}

	public List<?> getRows()
	{
		return rows;
	}

	public void setRows(List<?> rows)
	{
		this.rows = rows;
	}

	public Integer getCurrentPage()
	{
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage)
	{
		this.currentPage = currentPage;
	}

	public Integer getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

	public Integer getNumberOfElements()
	{
		return numberOfElements;
	}

	public void setNumberOfElements(Integer numberOfElements)
	{
		this.numberOfElements = numberOfElements;
	}

	public Integer getTotalPages()
	{
		return totalPages;
	}

	public void setTotalPages(Integer totalPages)
	{
		this.totalPages = totalPages;
	}

	public Long getTotalElements()
	{
		return totalElements;
	}

	public void setTotalElements(Long totalElements)
	{
		this.totalElements = totalElements;
	}

	public Boolean getHasPreviousPage()
	{
		return hasPreviousPage;
	}

	public void setHasPreviousPage(Boolean hasPreviousPage)
	{
		this.hasPreviousPage = hasPreviousPage;
	}

	public Boolean getHasNextPage()
	{
		return hasNextPage;
	}

	public void setHasNextPage(Boolean hasNextPage)
	{
		this.hasNextPage = hasNextPage;
	}

	public Boolean getIsFirstPage()
	{
		return isFirstPage;
	}

	public void setIsFirstPage(Boolean isFirstPage)
	{
		this.isFirstPage = isFirstPage;
	}

	public Boolean getIsLastPage()
	{
		return isLastPage;
	}

	public void setIsLastPage(Boolean isLastPage)
	{
		this.isLastPage = isLastPage;
	}

	public Boolean getIsPageResult()
	{
		return isPageResult;
	}

	public void setIsPageResult(Boolean isPageResult)
	{
		this.isPageResult = isPageResult;
	}
}
