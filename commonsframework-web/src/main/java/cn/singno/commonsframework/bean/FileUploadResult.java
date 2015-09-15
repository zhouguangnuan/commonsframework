package cn.singno.commonsframework.bean;

import java.io.File;
import java.io.Serializable;

import cn.singno.commonsframework.utils.MultipartUtils.FileType;

/**
 * <p>File：FileUploadResult.java</p>
 * <p>Title: 文件上传后返回的结果对象</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-3-3 下午2:15:28</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
@SuppressWarnings("all")
public class FileUploadResult implements Serializable {

	private String fileName;// 文件名
	
	private String newFileName;// 新文件名
	
	private String relativePath;// 文件的相对路径
	
	private FileType fileType;// 文件类型
	
	public FileUploadResult(Multipart multipart, FileType fileType) 
	{
		this.fileName = multipart.getFile().getOriginalFilename();
		this.newFileName = multipart.getNewFileName();
		this.relativePath = multipart.getRelativePath();
		this.fileType = fileType;
	}
	
	public FileUploadResult(File file, FileType fileType) 
	{
		this.fileName = file.getName();
		this.newFileName = file.getName();
		this.relativePath = file.getPath();
		this.fileType = fileType;
	}

	public String getFileName()
	{
		return fileName;
	}

	public String getNewFileName()
	{
		return newFileName;
	}

	public String getRelativePath()
	{
		return relativePath;
	}

	public FileType getFileType()
	{
		return fileType;
	}
}
