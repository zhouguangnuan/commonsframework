package cn.singno.commonsframework.utils;


/**
 * 名称：BeanUtils.java<br>
 * 描述：实体工具类<br>
 * 
 * <pre></pre>
 * 
 * @author 周光暖
 * @date 2015-1-13 下午9:57:15
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class ClassUtils
{
	/**
	 * 描述：判断是否属于八大基本类型或其包装类型<br>
	 * 
	 * <pre>
	 * byte、short、int、long、float、double、char、boolean
	 * </pre>
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isPrimitive(Object obj)
	{
		if (null == obj)
		{
			return false;
		}
		return org.apache.commons.lang3.ClassUtils.isPrimitiveOrWrapper(obj.getClass());
	}
}
