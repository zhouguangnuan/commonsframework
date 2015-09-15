/*
 * @(#)GenericController.java 2015-2-28 下午3:44:19
 * Copyright 2015 周光暖, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.singno.commonsframework.generic;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import cn.singno.commonsframework.bean.ClientParameter;
import cn.singno.commonsframework.editor.BooleanEditorSupport;
import cn.singno.commonsframework.editor.ByteEditorSupport;
import cn.singno.commonsframework.editor.DoubleEditorSupport;
import cn.singno.commonsframework.editor.FloatEditorSupport;
import cn.singno.commonsframework.editor.IntegerEditorSupport;
import cn.singno.commonsframework.editor.LongEditorSupport;
import cn.singno.commonsframework.editor.ShortEditorSupport;
import cn.singno.commonsframework.editor.StringEditorSupport;
import cn.singno.commonsframework.utils.NetworkUtils;

/**
 * <p>名称：GenericController.java</p>
 * <p>描述：spring控制器支持类</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-4-13 下午5:39:31
 * @version 1.0.0
 */
@SuppressWarnings("all")
@Deprecated
public class GenericController
{
	/**
	 * Logger for this class
	 */
	private static final Logger	logger	= Logger.getLogger(GenericController.class);
    
    /**
     * 初始化参数绑定，将所有传递进来的String进行HTML编码，防止XSS攻击
     * @param binder WebDataBinder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder)
    {
        binder.setAutoGrowCollectionLimit(1024);
    	
        String data = null;
        
        // 第三方接口参数传递时，不需要初始化参数绑定
        Object object = binder.getTarget();
        if (object instanceof ClientParameter) 
        {
        	try
			{
				data = (String) PropertyUtils.getSimpleProperty(object, "data");
			} catch (Exception e) { }
        }	
        
        if (null == data)
        {
        	// Byte
            binder.registerCustomEditor(Byte.class, new ByteEditorSupport());
            // Float
            binder.registerCustomEditor(Float.class, new FloatEditorSupport());
            // Double
            binder.registerCustomEditor(Double.class, new DoubleEditorSupport());
            // Long
            binder.registerCustomEditor(Long.class, new LongEditorSupport());
            // Integer
            binder.registerCustomEditor(Integer.class, new IntegerEditorSupport());
            // Boolean
            binder.registerCustomEditor(Boolean.class, new BooleanEditorSupport());
            // String
            binder.registerCustomEditor(String.class, new StringEditorSupport());
            // Short
            binder.registerCustomEditor(Short.class, new ShortEditorSupport());
        }
    }

    /**
     * 取得客户端IP地址并转化为整数
     * @param request HttpServletRequest
     * @return int 整数格式的客户端IP地址
     */
    protected int getIpAddr(HttpServletRequest request)
    {
        return NetworkUtils.getRemortIpInt(request);
    }
}
