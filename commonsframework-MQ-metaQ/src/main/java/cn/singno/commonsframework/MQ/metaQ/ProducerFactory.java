/**<p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.MQ.metaQ</p>
 * <p>文件名：ProducerFactory.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-4-7-下午12:49:46</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.MQ.metaQ;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.MessageSessionFactory;
import com.taobao.metamorphosis.client.producer.MessageProducer;
import com.taobao.metamorphosis.exception.MetaClientException;

/**<p>名称：ProducerFactory.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-4-7 下午12:49:46
 * @version 1.0.0
 */
public class ProducerFactory
{
	private MessageSessionFactory messageSessionFactory;
	private List<ProducerInfo> producers;
	private Map<String, MessageProducer> map = new HashMap<String, MessageProducer>();
	
	public void init()
	{
		for (ProducerInfo producerInfo : producers)
		{
			MessageProducer producer = messageSessionFactory.createProducer();
			Set<String> topics = producerInfo.getTopics();
			if (null != topics)
			{
				for (String topic : topics)
				{
					producer.publish(topic);
					map.put(topic, producer);
				}
			}
		}
	}
	
	public MessageProducer getByTopic(String topic)
	{
		return map.get(topic);
	}

	public MessageSessionFactory getMessageSessionFactory()
	{
		return messageSessionFactory;
	}

	public void setMessageSessionFactory(MessageSessionFactory messageSessionFactory)
	{
		this.messageSessionFactory = messageSessionFactory;
	}

	public List<ProducerInfo> getProducers()
	{
		return producers;
	}

	public void setProducers(List<ProducerInfo> producers)
	{
		this.producers = producers;
	}
}
