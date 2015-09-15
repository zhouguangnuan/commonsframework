/*
 * @(#)FileUploadException.java 2015-3-3 下午1:08:24
 * Copyright 2015 周光暖, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.singno.commonsframework.exception;

import cn.singno.commonsframework.constants.Describable;


/**
 * <p>File：FileDownloadException.java</p>
 * <p>Title: 文件下载异常类</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-3-3 下午1:08:24</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
@SuppressWarnings("all")
public class FileDownloadException extends DescribableException
{
	public FileDownloadException(Describable describableInfo) {
		super(describableInfo);
	}
	
	public FileDownloadException(Describable describableInfo, String errorDetails) {
		super(describableInfo, errorDetails);
	}
}
