/**<p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.MQ.metaQ</p>
 * <p>文件名：ConsumerInfo.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-4-6-上午11:42:32</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.MQ.metaQ;

import com.taobao.metamorphosis.client.consumer.ConsumerConfig;
import com.taobao.metamorphosis.client.consumer.MessageListener;

/**<p>名称：ConsumerInfo.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-4-6 上午11:42:32
 * @version 1.0.0
 */
public class ConsumerInfo
{
	private String topic;
	private int maxSize;
	private MessageListener messageListener;
	private ConsumerConfig consumerConfig;
	
	public String getTopic()
	{
		return topic;
	}

	public void setTopic(String topic)
	{
		this.topic = topic;
	}

	public int getMaxSize()
	{
		return maxSize;
	}

	public void setMaxSize(int maxSize)
	{
		this.maxSize = maxSize;
	}

	public MessageListener getMessageListener()
	{
		return messageListener;
	}

	public void setMessageListener(MessageListener messageListener)
	{
		this.messageListener = messageListener;
	}

	public ConsumerConfig getConsumerConfig()
	{
		return consumerConfig;
	}

	public void setConsumerConfig(ConsumerConfig consumerConfig)
	{
		this.consumerConfig = consumerConfig;
	}
}
