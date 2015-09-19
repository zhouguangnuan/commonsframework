package cn.singno.commonsframework.weixin.msg.receive.event;

import cn.singno.commonsframework.weixin.msg.receive.ReceiveMsg;

/**
 * <p>名称：R_e_subscribeMsg.java</p>
 * <p>描述：弹出地理位置选择器</p>
 * <pre>
 *    接收事件推送
 * {
 *      "ToUserName": "gh_6691ddd26886",
 *	    "FromUserName": "oJ6pEw7ML27fvjNuSEAncgyh1f70",
 *	    "CreateTime": "1442638454",
 *	    "MsgType": "event",
 *	    "Event": "location_select",
 *	    "EventKey": "rselfmenu_2_0",
 *	    "SendLocationInfo": {
 *	        "Location_X": "29.96318817138672",
 *	        "Location_Y": "121.53370666503906",
 *	        "Scale": "16",
 *	        "Label": "姹熷寳鍖哄簞妗ュ疄楠屽鏍¤タ(涓滀繚绾胯タ)",
 *          "Poiname": "[浣嶇疆]"
 *     }
 * }
 * @version 1.0.0
 */
public class R_e_locationSelectMsg extends ReceiveMsg 
{
	// location_select
	private String event;// 事件类型
	
	private String eventKey;// rselfmenu_2_0
	
	private SendLocationInfo sendLocationInfo;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventKey() {
		return eventKey;
	}
	
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public SendLocationInfo getSendLocationInfo() {
		return sendLocationInfo;
	}

	public void setSendLocationInfo(SendLocationInfo sendLocationInfo) {
		this.sendLocationInfo = sendLocationInfo;
	}

	class SendLocationInfo
	{
		private Double location_X;// 地理位置纬度
		private Double location_Y;// 地理位置经度
		private Integer scale;// 缩放比例
		private String label;// 
		private String poiname;//
		
		public Double getLocation_X() {
			return location_X;
		}
		public void setLocation_X(Double location_X) {
			this.location_X = location_X;
		}
		public Double getLocation_Y() {
			return location_Y;
		}
		public void setLocation_Y(Double location_Y) {
			this.location_Y = location_Y;
		}
		public Integer getScale() {
			return scale;
		}
		public void setScale(Integer scale) {
			this.scale = scale;
		}
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
		public String getPoiname() {
			return poiname;
		}
		public void setPoiname(String poiname) {
			this.poiname = poiname;
		}
	}
}
