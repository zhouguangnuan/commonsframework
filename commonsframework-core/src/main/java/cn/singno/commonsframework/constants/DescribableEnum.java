/**<p>项目名：</p>
 * <p>包名：	cn.singno.validatorDemo.constants</p>
 * <p>文件名：DescribableExceptionConstants.java</p>
 * <p>版本信息：</p>
 * <p>日期：2014年7月24日-下午3:19:12</p>
 * Copyright (c) 2014singno公司-版权所有
 */
package cn.singno.commonsframework.constants;

/**<p>名称：DescribableExceptionConstants.java</p>
 * <p>描述：</p>
 * <pre>
 *    可描述的错误常量接口     
 * </pre>
 * @author 周光暖
 * @date 2014年7月24日 下午3:19:12
 * @version 1.0.0
 */
public interface DescribableEnum
{ 
	/**
	 * 描述：  
	 * <pre>状态代码</pre>
	 * @return
	 */
	Number getCode();
	
	/**
	 * 描述：
	 * <pre>状态描述信息</pre>
	 * @return
	 */
	String getMessage();
}
