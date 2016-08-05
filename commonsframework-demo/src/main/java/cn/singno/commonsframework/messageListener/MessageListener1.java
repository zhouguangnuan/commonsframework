/**<p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.MQ.metaQ</p>
 * <p>文件名：MyMessageListener1.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-4-7-上午8:59:01</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.messageListener;

import java.util.concurrent.Executor;

import org.springframework.util.SerializationUtils;

import cn.singno.commonsframework.module.app.entity.User;

import com.alibaba.fastjson.JSON;
import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.consumer.MessageListener;

/**<p>名称：MyMessageListener1.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-4-7 上午8:59:01
 * @version 1.0.0
 */
public class MessageListener1 implements MessageListener
{

	/* (non-Javadoc)
	 * @see com.taobao.metamorphosis.client.consumer.MessageListener#recieveMessages(com.taobao.metamorphosis.Message)
	 */
	@Override
	public void recieveMessages(Message message)
	{
//		System.out.println("Consumer Receive message " + new String(message.getData()));
		
		byte[] objStream = message.getData();
		User user = (User) SerializationUtils.deserialize(objStream);
		System.out.println(JSON.toJSONString(user));
	}

	/* (non-Javadoc)
	 * @see com.taobao.metamorphosis.client.consumer.MessageListener#getExecutor()
	 */
	@Override
	public Executor getExecutor()
	{
		
		return null;
	}

}
