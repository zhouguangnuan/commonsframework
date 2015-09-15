/**<p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.editor</p>
 * <p>文件名：MyWebBindingInitializer.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-4-13-下午3:29:52</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.initializer;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import cn.singno.commonsframework.bean.ClientParameter;
import cn.singno.commonsframework.editor.BooleanEditorSupport;
import cn.singno.commonsframework.editor.ByteEditorSupport;
import cn.singno.commonsframework.editor.DoubleEditorSupport;
import cn.singno.commonsframework.editor.FloatEditorSupport;
import cn.singno.commonsframework.editor.IntegerEditorSupport;
import cn.singno.commonsframework.editor.LongEditorSupport;
import cn.singno.commonsframework.editor.ShortEditorSupport;
import cn.singno.commonsframework.editor.StringEditorSupport;

/**<p>名称：CommonsWebBindingInitializer.java</p>
 * <p>描述：web参数绑定初始化器</p>
 * <pre>
 *    拥有批量注册PropertyEditor，将所有传递进来的String进行HTML编码，防止XSS攻击
 * </pre>
 * @author 周光暖
 * @date 2015-4-13 下午3:29:52
 * @version 1.0.0
 */
@Deprecated
public class CommonsWebBindingInitializer implements WebBindingInitializer
{
	@Override
	public void initBinder(WebDataBinder binder, WebRequest request)
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
}
