package cn.singno.commonsframework.editor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 * <p>File：IntegerEditorSupport.java</p>
 * <p>Title: Integer型参数处理</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-2-28 下午4:34:16</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
@Deprecated
public class IntegerEditorSupport extends PropertyEditorSupport
{
    @Override
    public void setAsText(String text) throws IllegalArgumentException
    {
        if (StringUtils.isBlank(text))
        {
            super.setValue(null);
        }
        else
        {
            text = Jsoup.clean(text, Whitelist.relaxed());
            try
            {
            	super.setValue(Integer.parseInt(text));
            }
            catch (NumberFormatException e)
            {
            	super.setValue(null);
            }
        }
    }

    @Override
    public String getAsText()
    {
        Integer value = (Integer)super.getValue();
        if (null == value)
            return null;
        else
            return value.toString();
    }
}
