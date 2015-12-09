/*
 * @(#)SpringUtil.java 2015-2-28 下午2:22:05
 * Copyright 2015 周光暖, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.singno.commonsframework.utils;

import java.io.FileNotFoundException;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.WebUtils;

import cn.singno.commonsframework.constants.DefaultSystemConst;

/**
 * <p>File：SpringUtils.java</p>
 * <p>Title: Spring ApplicationContext管理工具类</p>
 * <p>Description:
 *              以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候取出ApplicaitonContext
 *              使用前需要在 spring-config.xml 注册
 * </p>
 * <p>Copyright: Copyright (c) 2015 2015-2-28 下午2:25:09</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
@SuppressWarnings("all")
public class SpringUtils implements ApplicationContextAware, DisposableBean
{
    private static final Logger       logger             = Logger.getLogger(SpringUtils.class);

    private static ApplicationContext applicationContext = null;

    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext()
    {
        assertContextInjected();
        return applicationContext;
    }
    
    /**
     * 取得站点根目录路径，如：E:/WorkSpace/tomcat/webapps/root/
     */
    public static String getRootPath()
    {
        String path = DefaultSystemConst.SYS_SEPARATOR;
        try
        {
                WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
            if (null != webApplicationContext)
                        {
                ServletContext servletContext = webApplicationContext.getServletContext();
                path = WebUtils.getRealPath(servletContext, DefaultSystemConst.SYS_SEPARATOR);
                        }
        }
        catch (FileNotFoundException e)
        {
            logger.error(e);
        }
        return path;
    }

    /**
     * 根据类型名称取得bean
     * @param name 类型名称
     * @return T bean
     */
    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T)applicationContext.getBean(name);
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     * @param requiredType 类型
     * @return T bean
     */
    public static <T> T getBean(Class<T> requiredType)
    {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }
    
    /**
     * 清除SpringContextHolder中的ApplicationContext为Null.
     */
    public static void clearHolder()
    {
        logger.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
        applicationContext = null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
    {
        if (SpringUtils.applicationContext != null)
        {
            logger.warn("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:" + SpringUtils.applicationContext);
        }
        SpringUtils.applicationContext = applicationContext;
    }
    
    @Override
    public void destroy() throws Exception
    {
        SpringUtils.clearHolder();
    }

    /**
     * 检查ApplicationContext不为空.
     */
    private static void assertContextInjected()
    {
        Validate.validState(applicationContext != null, "applicaitonContext属性未注入, 请在applicationContext.xml中定义SpringContextHolder.");
    }
}