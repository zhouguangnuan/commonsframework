package cn.singno.commonsframework.editor;

import java.beans.PropertyEditorSupport;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 * <p>File：StringEditorSupport.java</p>
 * <p>Title: String型参数处理</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-2-28 下午4:38:19</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
@Deprecated
public class StringEditorSupport extends PropertyEditorSupport
{
    private final static Whitelist user_content_filter = Whitelist.relaxed();
    static
    {
        user_content_filter.addTags("embed", "object", "param", "span", "div");
        user_content_filter.addAttributes(":all", "style", "class", "id", "name");
        user_content_filter.addAttributes("object", "width", "height", "classid", "codebase");
        user_content_filter.addAttributes("param", "name", "value");
        user_content_filter.addAttributes("embed", "src", "quality", "width", "height", 
        		"allowFullScreen", "allowScriptAccess", "flashvars",
                "name", "type", "pluginspage");
    }
    
    @Override
    public void setAsText(String text) throws IllegalArgumentException
    {
        if (null == text)
        {
            super.setValue(null);
        }
        else
        {
            text = Jsoup.clean(text, user_content_filter);
            super.setValue(text);
        }
    }

    @Override
    public String getAsText()
    {
        String value = (String)super.getValue();
        if (null == value)
            return null;
        else
            return value.toString();
    }
}
