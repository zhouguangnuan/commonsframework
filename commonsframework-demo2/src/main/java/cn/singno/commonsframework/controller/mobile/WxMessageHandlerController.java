package cn.singno.commonsframework.controller.mobile;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.singno.commonsframework.bean.WxSendMsgBuilder;
import cn.singno.commonsframework.utils.WeiXinUtils;
import cn.singno.commonsframework.weixin.msg.receive.ReceiveMsg;
import cn.singno.commonsframework.weixin.msg.receive.common.R_c_textMsg;
import cn.singno.commonsframework.weixin.msg.receive.event.R_e_customizeMenusMsg;
import cn.singno.commonsframework.weixin.msg.receive.event.R_e_quickmarkMsg;
import cn.singno.commonsframework.weixin.msg.receive.event.R_e_reportLocationMsg;
import cn.singno.commonsframework.weixin.msg.receive.event.R_e_subscribeMsg;
import cn.singno.commonsframework.weixin.msg.receive.event.R_e_unsubscribeMsg;
import cn.singno.commonsframework.weixin.msg.send.SendMsg;
import cn.singno.commonsframework.weixin.msg.send.passiveReply.Item;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

@Controller
@RequestMapping("/mobile/wx")
public class WxMessageHandlerController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WxMessageHandlerController.class);
	
	/**
	 * <p>描述：验证接入微信申请</p>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/message/handler", method=RequestMethod.GET)
	public void validationAccessWx(HttpServletRequest request, HttpServletResponse response)
	{
		WeiXinUtils.accessWx(request, response);
	}
	
	/**
	 * <p>描述：处理用户发送到公众账号的消息</p>
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	@RequestMapping(value="/message/handler", method=RequestMethod.POST)
	public void handleMessage(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// 验证消息来自微信
		if (!WeiXinUtils.isWxMsg(request))
		{
			return;
		}
		
		// 接收到的消息
		ReceiveMsg receiveMsg = WeiXinUtils.receiveMsg(request);
		SendMsg sendMsg = null;
		
		logger.debug(JSON.toJSONString(receiveMsg));
		
		if (receiveMsg instanceof R_c_textMsg) {
			logger.debug("=========================== 文本消息 ============================");
			
			R_c_textMsg textMsg = (R_c_textMsg)receiveMsg;
			String content = textMsg.getContent();
			if ("1".equalsIgnoreCase(content.trim())) {
				sendMsg = WxSendMsgBuilder.buildTextMsg(receiveMsg, "你好,我是文本消息!");
			}
			else if ("2".equalsIgnoreCase(content.trim()))
			{
				sendMsg = WxSendMsgBuilder.buildImageMsg(receiveMsg, "QokIuFdJCISeXJReq5bQDv0DzwKGRbMmU5RIyLi7Zytd4Kv7XVL7UOs0RYGMTuuN");
			}
			else if ("3".equalsIgnoreCase(content.trim()))
			{
				sendMsg = WxSendMsgBuilder.buildVoiceMsg(receiveMsg, "IvKLOugMj-VGUW6UhKwyb12NuHeLrg4iC9djw9rOHEyvngTcrUVkZmzd8bxrr8Lx");
			}
			else if ("4".equalsIgnoreCase(content.trim()))
			{
				// mp4
				String title = "小视频";
				String description = "视频";
				String mediaId = "zq6-gltk4gDCW7r7SEivYfrMynMbZmhH53skPsnX75b6JjXad4tl0-a5iAjzs82W";
				sendMsg = WxSendMsgBuilder.buildVideoMsg(receiveMsg, mediaId, title, description);
			}
			else if ("5".equalsIgnoreCase(content.trim()))
			{
				String title = "大海";
				String description = "音乐";
				String musicURL = "http://weixincourse-weixincourse.stor.sinaapp.com/mysongs.mp3";
				String hQMusicUrl = "http://weixincourse-weixincourse.stor.sinaapp.com/mysongs.mp3"; 
				String thumbMediaId = "QokIuFdJCISeXJReq5bQDv0DzwKGRbMmU5RIyLi7Zytd4Kv7XVL7UOs0RYGMTuuN"; 
				sendMsg = WxSendMsgBuilder.buildMusicMsg(receiveMsg, title, description, musicURL, hQMusicUrl, thumbMediaId);
			}
			else 
			{
				Integer articleCount = 2;
				List<Item> articles = Lists.newArrayList();
				Item item1 = new Item();
				item1.setTitle("Title1");
				item1.setDescription("Description1");
				item1.setPicUrl("http://60.190.32.54/images/doc.jpg");
				item1.setUrl("www.baidu.com");
				Item item2 = new Item();
				item2.setTitle("Title2");
				item2.setDescription("Description2");
				item2.setPicUrl("http://60.190.32.54/images/doc.jpg");
				item2.setUrl("http://60.190.32.54/images/doc.jpg");
				articles.add(item1);
				articles.add(item2);
				sendMsg = WxSendMsgBuilder.buildNewsMsg(receiveMsg, articleCount, articles);			
			}
		} 
		else if(receiveMsg instanceof R_e_customizeMenusMsg){
			logger.debug("=========================== 自定义菜单事件 ============================");
		}
		else if(receiveMsg instanceof R_e_quickmarkMsg){
			logger.debug("=========================== 扫描带参数二维码事件 ============================");
		}
		else if(receiveMsg instanceof R_e_reportLocationMsg){
			logger.debug("=========================== 上报地理位置事件  ============================");
		}
		else if(receiveMsg instanceof R_e_subscribeMsg){
			logger.debug("=========================== 关注事件 ============================");
		}
		else if(receiveMsg instanceof R_e_unsubscribeMsg){
			logger.debug("=========================== 取消关注事件 ============================");
		}
		 
		// 回复消息
		WeiXinUtils.sendMsg(sendMsg, response);
	}
}
