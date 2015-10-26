package cn.singno.commonsframework.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 */
@SuppressWarnings("all")
public class Page<T> implements Serializable 
{
	Integer number = 0;// 当前页数（从0开始）
	
	Integer size = 10;// 每页记录数
	
	Integer totalPages;// 总页数
	
	Long totalElements;// 总记录数
	
	private List<T> content;// 内容
	
	public Page()
	{
		super();
	}

	public Page(Integer number, Integer size)
	{
		super();
		this.number = number;
		this.size = size;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	public List<?> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}
}