/**<p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.MQ.metaQ</p>
 * <p>文件名：ConsumerFactory.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-4-6-上午11:40:52</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.MQ.metaQ;

import java.util.List;

import com.taobao.metamorphosis.client.MessageSessionFactory;
import com.taobao.metamorphosis.client.consumer.MessageConsumer;
import com.taobao.metamorphosis.client.consumer.MessageListener;
import com.taobao.metamorphosis.exception.MetaClientException;

/**<p>名称：ConsumerFactory.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-4-6 上午11:40:52
 * @version 1.0.0
 */
public class ConsumerFactory
{
	private MessageSessionFactory messageSessionFactory;
	private List<ConsumerInfo> consumerInfos;
	
	public void init() throws MetaClientException
	{
		for (ConsumerInfo consumerInfo : consumerInfos)
		{
			String topic = consumerInfo.getTopic();
			int maxSize = consumerInfo.getMaxSize();
			MessageListener messageListener = consumerInfo.getMessageListener();
			MessageConsumer consumer = messageSessionFactory.createConsumer(consumerInfo.getConsumerConfig());
			consumer.subscribe(topic, maxSize, messageListener);
			consumer.completeSubscribe();
		}
	}
	
	public List<ConsumerInfo> getConsumerInfos()
	{
		return consumerInfos;
	}

	public void setConsumerInfos(List<ConsumerInfo> consumerInfos)
	{
		this.consumerInfos = consumerInfos;
	}

	public MessageSessionFactory getMessageSessionFactory()
	{
		return messageSessionFactory;
	}

	public void setMessageSessionFactory(MessageSessionFactory messageSessionFactory)
	{
		this.messageSessionFactory = messageSessionFactory;
	}
}
