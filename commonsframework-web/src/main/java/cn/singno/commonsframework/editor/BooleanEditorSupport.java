package cn.singno.commonsframework.editor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.BooleanUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 * <p>File：BooleanEditorSupport.java</p>
 * <p>Title: Boolean型参数处理</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-2-28 下午4:25:10</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
@Deprecated
public class BooleanEditorSupport extends PropertyEditorSupport
{
    private static final String INT_FALSE = "0";

    private static final String INT_TRUE  = "1";

    @Override
    public void setAsText(String text) throws IllegalArgumentException
    {
        if (null == text)
        {
            super.setValue(null);
        }
        else if (text.equals(INT_FALSE))
        {
        	super.setValue(Boolean.FALSE);
        }
        else if (text.equals(INT_TRUE))
        {
        	super.setValue(Boolean.TRUE);
        }
        else
        {
            text = Jsoup.clean(text, Whitelist.relaxed());
            setValue(BooleanUtils.toBoolean(text));
        }
    }

    @Override
    public String getAsText()
    {
        Boolean value = (Boolean) super.getValue();
        if (null == value)
            return null;
        else
            return value.toString();
    }
}