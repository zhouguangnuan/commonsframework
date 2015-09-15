/**<p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.MQ.metaQ</p>
 * <p>文件名：ProducerInfo.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-4-7-下午8:45:38</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.MQ.metaQ;

import java.util.Set;

/**<p>名称：ProducerInfo.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-4-7 下午8:45:38
 * @version 1.0.0
 */
public class ProducerInfo
{
	private Set<String> topics;

	public Set<String> getTopics()
	{
		return topics;
	}

	public void setTopics(Set<String> topics)
	{
		this.topics = topics;
	}
}
