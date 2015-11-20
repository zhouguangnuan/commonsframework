package cn.singno.commonsframework.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import cn.singno.commonsframework.bean.FileUploadResult;
import cn.singno.commonsframework.bean.Multipart;
import cn.singno.commonsframework.bean.MultipartFiles;
import cn.singno.commonsframework.constants.DefaultDescribableEnum;
import cn.singno.commonsframework.exception.FileDownloadException;
import cn.singno.commonsframework.exception.FileUploadException;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.io.ByteStreams;

/**
 * <p>File：MultipartUtils.java</p>
 * <p>Title: 文件上传工具类</p>
 * <p>Description:
 * 		1.上传的所有文件都不做删除操作
 * 		2.删除将以任务调度器的形式去执行
 * 		3.任务调用器形式删除3个月以上或者更久的临时文件夹下的文件
 * 		4.文件夹目录说明： img/年/年月日/32.格式文件
 * 		5.这里文件的校验只做了类型和后缀的判断。如果有人恶意在图片或者在文件插入恶意脚本和代码。请在生产环境中设置目录权限。
 *    	    如果是图片插入恶意脚本，请压缩图片，将恶意代码去掉。这个方式有性能的损耗这里不做考虑
 * </p>
 * <p>Copyright: Copyright (c) 2015 2015-3-5 上午9:38:49</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
@SuppressWarnings("all")
public class MultipartUtils {
	
	private static Log logger = LogFactory.getLog(MultipartUtils.class);
	
	// ======================================== 允许的 文件contentType ========================================
	//图片类型
	private static Set<String> TYPE_IMAGE = Sets.newHashSet("application/octet-stream","image/gif", "image/jpeg", "application/x-jpg", "application/x-png", "image/png","image/bmp");
	
	//文本类型
	private static Set<String> TYPE_TEXT = Sets.newHashSet("application/octet-stream", "application/pdf", "application/x-ppt", "application/msword", "text/plain");
	
	//视频类型
	private static Set<String> TYPE_VIDEO = Sets.newHashSet("video/mpeg4", "video/mpg", "video/x-mpeg", "video/mpg","application/octet-stream","audio/amr");
	
	//压缩文件类型
	private static Set<String> TYPE_ZIP = null;
	
	//app文件类型
	private static Set<String> TYPE_APP = Sets.newHashSet("application/vnd.android.package-archive", "application/iphone-package-archive", "application/octet-stream", "application/vnd.iphone");
	
	// ======================================== 允许的  文件suffix ========================================
	//图片后缀
	private static Set<String> SUFFIX_IMAGE = Sets.newHashSet("jpg", "png",  "jpeg");
	
	//文本后缀
	private static Set<String> SUFFIX_TEXT = Sets.newHashSet("doc", "txt");
	
	//视频后缀
	private static Set<String> SUFFIX_VIDEO = Sets.newHashSet("amr");
	
	//压缩后缀
	private static Set<String> SUFFIX_ZIP = null;
	
	//APP文件后缀
	private static Set<String> SUFFIX_APP = Sets.newHashSet("apk","ipa");
	
	
	public static enum FileType{
		IMAGE(SUFFIX_IMAGE, TYPE_IMAGE), // 图片
		TEXT(SUFFIX_TEXT, TYPE_TEXT), // 文本
		VIDEO(SUFFIX_VIDEO, TYPE_VIDEO), // 视频
		ZIP(SUFFIX_ZIP, TYPE_ZIP), // 压缩文件
		APP(SUFFIX_APP, TYPE_APP) // app应用
		;
		
		private Set<String> suffixSet;
		private Set<String> contentTypeSet;
		
		private FileType(Set<String> suffixSet, Set<String> contentTypeSet)
		{
			this.suffixSet = suffixSet;
			this.contentTypeSet = contentTypeSet;
		}

		public Set<String> getSuffixSet()
		{
			return suffixSet;
		}

		public Set<String> getContentTypeSet()
		{
			return contentTypeSet;
		}
	}
	
	// 加载配置文件初始化 （文件类型/文件后缀）
	static{
		// TODO N 后期扩展配置文件初始化
	}
	
	/**
	 * 图片上传
	 * @param file
	 * @return
	 * @author 周光暖
	 */
	public static FileUploadResult uploadImage(MultipartFile file)
	{
		return uploadFile(file, FileType.IMAGE);
	}
	
	/**
	 * 批量：图片上传
	 * @param file
	 * @return
	 * @author 周光暖
	 */
	public static List<FileUploadResult> uploadImage(MultipartFiles files)
	{
		return uploadFile(files, FileType.IMAGE);
	}
	
	/**
	 * 视频上传
	 * 说明：视频在上传的时候需要进行视频的解码和转码，转成flv的格式，这样才能在前台页面中显示
	 * java不具备这种能力，需要通过第三方的工具来完成
	 * @param file
	 * @return
	 * @author 周光暖
	 */
	public static FileUploadResult uploadVideo(MultipartFile file)
	{
		return uploadFile(file, FileType.VIDEO);
	}
	
	/**
	 * 批量：视频上传
	 * 说明：视频在上传的时候需要进行视频的解码和转码，转成flv的格式，这样才能在前台页面中显示
	 * java不具备这种能力，需要通过第三方的工具来完成
	 * @param file
	 * @return
	 * @author 周光暖
	 */
	public static List<FileUploadResult> uploadVideo(MultipartFiles files)
	{
		return uploadFile(files, FileType.VIDEO);
	}
	
	/**
	 * 文本上传
	 * @param file
	 * @return
	 * @author 周光暖
	 */
	public static FileUploadResult uploadTxt(MultipartFile file)
	{
		return uploadFile(file, FileType.TEXT);
	}
	
	/**
	 * 批量：文本上传
	 * @param file
	 * @return
	 * @author 周光暖
	 */
	public static List<FileUploadResult> uploadTxt(MultipartFiles files)
	{
		return uploadFile(files, FileType.TEXT);
	}
	
	/**
	 * 压缩文件上传
	 * @param file
	 * @return
	 * @author 周光暖
	 */
	public static FileUploadResult uploadZip(MultipartFile file)
	{
		return uploadFile(file, FileType.ZIP);
	}
	
	/**
	 * 批量：压缩文件上传
	 * @param file
	 * @return
	 * @author 周光暖
	 */
	public static List<FileUploadResult> uploadZip(MultipartFiles files)
	{
		return uploadFile(files, FileType.ZIP);
	}
	
	/**
	 * 通用文件上传
	 * 相比其它上传，多些循环查询，在不确定上传文件的类型时使用
	 * @param file
	 * @return
	 * @author 周光暖
	 */
	public static FileUploadResult uploadCommon(MultipartFile file)
	{
		return uploadFile(file, null);
	}
	
	/**
	 * 批量：通用文件上传
	 * 相比其它上传，多些循环查询，在不确定上传文件的类型时使用
	 * @param files
	 * @return
	 * @author 周光暖
	 */
	public static List<FileUploadResult> uploadCommon(MultipartFiles files)
	{
		return  uploadFile(files, null);
	}
	
	/**
	 * 描述：文件下载<br>
	 * <pre></pre>
	 * @param file 		下载的文件
	 * @param fileName 	文件名（可为空）
	 * @return
	 */
	public static ResponseEntity<byte[]> downFile(File file, String fileName){
		if (null == file)
		{
			throw new FileDownloadException(DefaultDescribableEnum.DOWNLOAD_ERROR, "下载文件不存在");
		}
		byte[] fileByte = null;
		try {
			if(StringUtils.isNotBlank(fileName)){
				fileName = new String(clean(fileName).getBytes(), CharEncoding.ISO_8859_1);
			}
			fileByte = FileUtils.readFileToByteArray(file);
		}  catch (IOException e) {
			logger.error(e);
			throw new FileDownloadException(DefaultDescribableEnum.DOWNLOAD_ERROR, "下载文件不存在");
		}
		return downFile(fileByte, fileName);
	}
	
	/**
	 * 文件下载，支持http方式的文件下载
	 * @param url 		http://baidu.com/img/123.jpg
	 * @param fileName 	文件名（可为空）
	 * @return
	 * @throws FileDownloadException 
	 */
	public static ResponseEntity<byte[]> downFile(URL url, String fileName)
	{
		if (null == url)
		{
			throw new FileDownloadException(DefaultDescribableEnum.DOWNLOAD_ERROR, "下载文件不存在");
		}
		byte[] fileByte = null;
		try {
			if(StringUtils.isNotBlank(fileName)){
				fileName = new String(clean(fileName).getBytes(), "iso-8859-1");
			}
			fileByte = IOUtils.toByteArray(url.openStream());
		}  catch (IOException e) {
			logger.error(e);
			throw new FileDownloadException(DefaultDescribableEnum.DOWNLOAD_ERROR, "下载文件不存在");
		}
		return downFile(fileByte, fileName);
	}

	/*********************************private 访法****************************************************/
	
	private static FileUploadResult uploadFile(MultipartFile file, FileType fileType)
	{
		if (null==file || file.isEmpty())
		{
			throw new FileUploadException(DefaultDescribableEnum.UPLOAD_ERROR, "上传文件不能为空");
		}
		
		// 1. 验证后缀
		if (null == fileType)
		{
			fileType = getFileType(file);
		}
		else
		{
			validSuffix(file, fileType);
		}
		
		// 2. 验证内容类型
		validContentType(file, fileType);
		
		// 3. 保存文件
		Multipart multipart = new Multipart(file, fileType);
		String absolutePath = multipart.getAbsolutePath();
		File savedFile = getSaveFile(absolutePath);
		try
		{
			file.transferTo(savedFile);
		} catch (Exception e)
		{
			logger.error(e);
			throw new FileUploadException(DefaultDescribableEnum.UPLOAD_ERROR, "文件保存失败");
		}
		return new FileUploadResult(multipart, fileType);
	}
	
	public static List<FileUploadResult> uploadFile(MultipartFiles files, FileType fileType)
	{
		if (null == files)
		{
			throw new FileUploadException(DefaultDescribableEnum.UPLOAD_ERROR, "上传文件不能为空");
		}
		List<FileUploadResult> uploadResult = Lists.newArrayList();
		for (MultipartFile file : files.getFiles())
		{
			uploadResult.add(uploadFile(file, fileType));
		}
		return  uploadResult;
	}
	
	private static ResponseEntity<byte[]> downFile(byte[] file, String fileName)
	{
		Assert.notNull(file, "byte[] file not be null");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		if(StringUtils.isNotBlank(fileName)){
			headers.setContentDispositionFormData("attachment", fileName);
		}
		return new ResponseEntity<byte[]>(file, headers, HttpStatus.OK);
	}
	
	/**
	 * 清除字符串中的斜杠
	 * 防止文件夹访问漏洞
	 * @param str
	 * @return
	 */
	private static String clean(String str){
		return str.replace("/", "").replace("\\", "");
	}
	
	/**
	 * 获得 FileType
	 * @param file
	 * @return
	 * @throws FileUploadException
	 * @author 周光暖
	 */
	private static FileType getFileType(MultipartFile file) throws FileUploadException
	{
		String fileName = file.getOriginalFilename();
		String fileSuffix = FilenameUtils.getExtension(fileName);
		
		List<FileType> list = EnumUtils.getEnumList(FileType.class);
		for (FileType fileType : list)
		{
			if (validSuffix(fileSuffix, fileType))
			{
				return fileType;
			}
		}
		throw new FileUploadException(DefaultDescribableEnum.UPLOAD_ERROR, "文件类型不支持");
	}
	
	/**
	 * 验证 文件suffix
	 * @param file
	 * @param fileType
	 * @throws FileUploadException
	 * @author 周光暖
	 */
	private static void validSuffix(MultipartFile file, FileType fileType) throws FileUploadException
	{
		String fileName = file.getOriginalFilename();
		String fileSuffix = FilenameUtils.getExtension(fileName);
		if (!validSuffix(fileSuffix, fileType))
		{
			throw new FileUploadException(DefaultDescribableEnum.UPLOAD_ERROR, "文件类型不支持");
		}
	}
	
	/**
	 * 验证 文件suffix
	 * @param file
	 * @param fileType
	 * @author 周光暖
	 */
	private static boolean validSuffix(String fileSuffix, FileType fileType)
	{
		Set<String> suffixSet = fileType.getSuffixSet();
		if (!suffixSet.contains(fileSuffix))
		{
			return false;
		}
		return true;
	}
	
	/**
	 * 验证 文件ContentType
	 * @param file
	 * @param fileType
	 * @author 周光暖
	 */
	private static void validContentType(MultipartFile file, FileType fileType) {
		String contentType = file.getContentType();
		Set<String> contentTypeSet = fileType.getContentTypeSet();
		if (!contentTypeSet.contains(contentType))
		{
			throw new FileUploadException(DefaultDescribableEnum.UPLOAD_ERROR, "文件类型不支持");
		}
	}
	
	/**
	 * 获得文件保存的File
	 * 如果文件目录不存在则自动创建
	 * @param savePath	文件保存的路径
	 * @return
	 * @author 周光暖
	 */
	private static File getSaveFile(String savePath)
	{
		File saveFile = FileUtils.getFile(savePath);
		
		String extension = FilenameUtils.getExtension(savePath);
		
		Assert.hasText(extension, "文件保存的路径异常");
		
		File parentFile = saveFile.getParentFile();
		if (!parentFile.exists())
		{
			if (!parentFile.mkdirs())
			{
				throw new FileUploadException(DefaultDescribableEnum.UPLOAD_ERROR, "文件保存目录创建失败");
			}
		}
		return saveFile;
	}
}
