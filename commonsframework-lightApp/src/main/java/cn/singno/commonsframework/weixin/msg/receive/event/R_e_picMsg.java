package cn.singno.commonsframework.weixin.msg.receive.event;

import java.util.Map;

import cn.singno.commonsframework.weixin.msg.receive.ReceiveMsg;

/**
 * <p>名称：R_e_subscribeMsg.java</p>
 * <p>描述：弹出(系统拍照、拍照或者相册、微信相册)发图事件</p>
 * <pre>
 *  {
 *	    "ToUserName": "gh_6691ddd26886",
 *	    "FromUserName": "oJ6pEw7ML27fvjNuSEAncgyh1f70",
 *	    "CreateTime": "1442568555",
 *	    "MsgType": "event",
 *	    "Event": "pic_sysphoto", // pic_photo_or_album、pic_weixin
 *	    "EventKey": "rselfmenu_1_0",// rselfmenu_1_1、rselfmenu_1_2
 *	    "SendPicsInfo": {
 *          "Count": "1",
 *          "PicList": {
 *              "item": {
 *                  "PicMd5Sum": "7044a0cd15578247d6c67b27f277eb5b"
 *              }
 *          }
 *	    }
 *	} 
 * </pre>
 * @author 周光暖
 * @date 2015-5-25 下午5:57:56
 * @version 1.0.0
 */
public class R_e_picMsg extends ReceiveMsg {
	
	// pic_sysphoto、pic_photo_or_album、pic_weixin
	private String event;// 事件类型
	
	// rselfmenu_1_0、rselfmenu_1_1、rselfmenu_1_2
	private String eventKey;// 事件KEY值
	
	private SendPicsInfo sendPicsInfo;
	
	public String getEvent()
	{
		return event;
	}
	public void setEvent(String event)
	{
		this.event = event;
	}
	public String getEventKey()
	{
		return eventKey;
	}
	public void setEventKey(String eventKey)
	{
		this.eventKey = eventKey;
	}
	
	public SendPicsInfo getSendPicsInfo() {
		return sendPicsInfo;
	}
	public void setSendPicsInfo(SendPicsInfo sendPicsInfo) {
		this.sendPicsInfo = sendPicsInfo;
	}

	class SendPicsInfo
	{
		private Integer count;
		
		private PicList picList;

		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}

		public PicList getPicList() {
			return picList;
		}

		public void setPicList(PicList picList) {
			this.picList = picList;
		}
	}
	
	class PicList
	{
		private Item item;

		public Item getItem() {
			return item;
		}

		public void setItem(Item item) {
			this.item = item;
		}
	}
	
	class Item
	{
		private String picMd5Sum;

		public String getPicMd5Sum() {
			return picMd5Sum;
		}

		public void setPicMd5Sum(String picMd5Sum) {
			this.picMd5Sum = picMd5Sum;
		}
	}
}
