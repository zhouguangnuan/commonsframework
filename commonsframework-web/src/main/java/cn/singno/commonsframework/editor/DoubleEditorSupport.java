package cn.singno.commonsframework.editor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 * <p>File：DoubleEditorSupport.java</p>
 * <p>Title: Double型参数处理</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-2-28 下午4:32:49</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
@Deprecated
public class DoubleEditorSupport extends PropertyEditorSupport
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
            	super.setValue(Double.parseDouble(text));
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
        Double value = (Double) super.getValue();
        if (null == value)
            return null;
        else
            return value.toString();
    }
}
