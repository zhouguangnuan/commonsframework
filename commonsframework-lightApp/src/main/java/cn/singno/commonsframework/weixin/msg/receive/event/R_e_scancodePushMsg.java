package cn.singno.commonsframework.weixin.msg.receive.event;

import cn.singno.commonsframework.weixin.msg.receive.ReceiveMsg;

/**
 * <p>名称：R_e_subscribeMsg.java</p>
 * <p>描述：扫码推事件</p>
 * <pre>
 *    接收事件推送
 *    {                                                                         
 *		  "ToUserName": "gh_6691ddd26886",                                        
 *		  "FromUserName": "oJ6pEw7ML27fvjNuSEAncgyh1f70",                         
 *		  "CreateTime": "1442567293",                                             
 *		  "MsgType": "event",                                                     
 *		  "Event": "scancode_waitmsg",                                            
 *		  "EventKey": "rselfmenu_0_0",                                            
 *		  "ScanCodeInfo": {                                                       
 *		      "ScanType": "qrcode",                                               
 *		      "ScanResult": "http://weixin.qq.com/r/8Th2bp7EcMMPrdoN923j"         
 *		  }  
 *	  }                                                                     
 * </pre>
 * @author 周光暖
 * @date 2015-5-25 下午5:57:56
 * @version 1.0.0
 */
public class R_e_scancodePushMsg extends ReceiveMsg {
	
//	scancode_push// 扫码推事件
//	scancode_waitmsg// 扫码推事件且弹出“消息接收中”提示框
	private String event;// 事件类型
	
	private String eventKey;// 事件KEY值
	
	private ScanCodeInfo scanCodeInfo;// 二维码信息
	
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
	
	public ScanCodeInfo getScanCodeInfo() {
		return scanCodeInfo;
	}
	public void setScanCodeInfo(ScanCodeInfo scanCodeInfo) {
		this.scanCodeInfo = scanCodeInfo;
	}

	class ScanCodeInfo{
		private String scanType;
		
		private String scanResult;

		public String getScanType() {
			return scanType;
		}

		public void setScanType(String scanType) {
			this.scanType = scanType;
		}

		public String getScanResult() {
			return scanResult;
		}

		public void setScanResult(String scanResult) {
			this.scanResult = scanResult;
		}
	}
}
