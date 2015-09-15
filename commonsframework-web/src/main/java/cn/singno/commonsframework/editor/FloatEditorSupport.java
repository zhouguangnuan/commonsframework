package cn.singno.commonsframework.editor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 * <p>File：FloatEditorSupport.java</p>
 * <p>Title: Float型参数处理</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-2-28 下午4:33:25</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
@Deprecated
public class FloatEditorSupport extends PropertyEditorSupport
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
            	super.setValue(Float.parseFloat(text));
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
        Integer value = (Integer) super.getValue();
        if (null == value)
            return null;
        else
            return value.toString();
    }
}
