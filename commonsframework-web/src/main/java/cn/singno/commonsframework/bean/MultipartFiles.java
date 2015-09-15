package cn.singno.commonsframework.bean;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>File：MultipartFiles.java</p>
 * <p>Title: 批量文件上传数据封装bean</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-3-4 上午9:34:39</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
public class MultipartFiles
{
	List<MultipartFile> files;

	public MultipartFiles()
	{
		super();
	}

	public MultipartFiles(List<MultipartFile> files)
	{
		super();
		this.files = files;
	}

	public List<MultipartFile> getFiles()
	{
		return files;
	}

	public void setFiles(List<MultipartFile> files)
	{
		this.files = files;
	}
	
	public boolean isEmpty()
	{
		if (CollectionUtils.isEmpty(this.files))
		{
			return true;
		}
		for (MultipartFile file : this.files)
		{
			if (file.isEmpty())
			{
				return true;
			}
		}
		return false;
	}
}