/*
 * @(#)ClientParameter.java 2015-2-28 下午4:48:06
 * Copyright 2015 周光暖, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.singno.commonsframework.bean;

/**
 * <p>File：ClientParameter.java</p>
 * <p>Title: API开发平台接口参数对象</p>
 * <p>Description:系统对接封装参数的对象</p>
 * <p>Copyright: Copyright (c) 2015 2015-2-28 下午4:48:16</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
public class ClientParameter
{
    //构造器
    public ClientParameter()
    {
        super();
    }
    
    // MD5或者异或检验码
    private String userDes;
    
    // 第三方key
    private String userKey;
    
    // 参数长度
    private Integer dataLen;
    
    // 参数内容
    private String data;

    public String getUserDes()
    {
        return userDes;
    }

    public void setUserDes(String userDes)
    {
        this.userDes = userDes;
    }

    public String getUserKey()
    {
        return userKey;
    }

    public void setUserKey(String userKey)
    {
        this.userKey = userKey;
    }

    public Integer getDataLen()
    {
        return dataLen;
    }

    public void setDataLen(Integer dataLen)
    {
        this.dataLen = dataLen;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }    
}
