package cn.singno.commonsframework.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 */
@SuppressWarnings("all")
public class Page<T> implements Serializable 
{
	int number;// 当前页数（从0开始）
	
	int size;// 每页记录数
	
	int totalPages;// 总页数
	
	int numberOfElements;// 当前记录所在下标
	
	long totalElements;// 总记录数
	
	private List<T> content;// 内容

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}
}