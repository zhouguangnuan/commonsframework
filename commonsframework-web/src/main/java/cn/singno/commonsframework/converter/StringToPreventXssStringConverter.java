/**<p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.converter</p>
 * <p>文件名：StringToPreventXssStringConverter.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-4-13-下午5:01:55</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.converter;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.core.convert.converter.Converter;

/**<p>名称：StringToPreventXssStringConverter.java</p>
 * <p>描述：将String转换成XSS安全的String</p>
 * <pre>
 *    对控制器方法String类型的参数进行转换，转成XSS安全的String
 * </pre>
 * @author 周光暖
 * @date 2015-4-13 下午5:01:55
 * @version 1.0.0
 */
public class StringToPreventXssStringConverter implements Converter<String, String>
{

	private final static Whitelist user_content_filter = Whitelist.relaxed();
	    static
	    {
	        user_content_filter.addTags("embed", "object", "param", "span", "div");
	        user_content_filter.addAttributes(":all", "style", "class", "id", "name");
	        user_content_filter.addAttributes("object", "width", "height", "classid", "codebase");
	        user_content_filter.addAttributes("param", "name", "value");
	        user_content_filter.addAttributes("embed", "src", "quality", "width", "height", "allowFullScreen", "allowScriptAccess", "flashvars","name", "type", "pluginspage");
	    }
	    
	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public String convert(String source)
	{
		if (null != source)
	        {
	            source = Jsoup.clean(source, user_content_filter);
	        }
		return source;
	}

}
