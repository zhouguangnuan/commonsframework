package cn.singno.commonsframework.bean;

import org.apache.commons.io.FilenameUtils;
import org.joda.time.DateTime;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import cn.singno.commonsframework.constants.DefaultSystemConst;
import cn.singno.commonsframework.utils.MultipartUtils.FileType;
import cn.singno.commonsframework.utils.SerialUtils;
import cn.singno.commonsframework.utils.SpringUtils;

public class Multipart {
	
	private MultipartFile file;// 文件对象
	
	private String newFileName;// 新文件名
	
	private String relativePath;// 文件的相对路径
	
	private String absolutePath;// 文件的绝对路径
	
	public Multipart(MultipartFile file, FileType fileType) 
	{
		Assert.notNull(file, "MultipartFile file not be null");
		Assert.isTrue(!file.isEmpty(), "MultipartFile file not be empty");
		
		this.file = file;
		this.newFileName = buildNewFileName(file);
		this.relativePath = buildFileRelativePath(file, fileType);
		this.absolutePath = SpringUtils.getRootPath() + this.relativePath;
	}

	public MultipartFile getFile()
	{
		return file;
	}

	public String getNewFileName()
	{
		return newFileName;
	}

	public String getRelativePath()
	{
		return relativePath;
	}

	public String getAbsolutePath()
	{
		return absolutePath;
	}
	
	// private ===============================================================================================================

	/**
	 * 构建文件相对对路径
	 * 格式：/img/年/年月日/32.格式文件
	 * @param file
	 * @return
	 * @author 周光暖
	 */
	private String buildFileRelativePath(MultipartFile file, FileType fileType)
	{
		DateTime now = DateTime.now();
		StringBuffer sb = new StringBuffer(DefaultSystemConst.SYS_SEPARATOR)
			.append("upload").append(DefaultSystemConst.SYS_SEPARATOR)
			.append(fileType.toString()).append(DefaultSystemConst.SYS_SEPARATOR)
			.append(now.toString("yyyy")).append(DefaultSystemConst.SYS_SEPARATOR)
			.append(now.toString("yyyyMMdd")).append(DefaultSystemConst.SYS_SEPARATOR)
			.append(this.newFileName);
		return sb.toString();
	}
	
	/**
	 * 获取32位UUID文件名
	 * @return
	 */
	private String buildNewFileName(MultipartFile file) {
		return SerialUtils.buildRefrenceId() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
	}
}
