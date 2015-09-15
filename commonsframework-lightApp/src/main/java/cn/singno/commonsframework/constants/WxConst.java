/**
 * <p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.constants</p>
 * <p>文件名：WxConst.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-6-2-下午11:08:14</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.constants;

/**
 * <p>名称：WxConst.java</p>
 * <p>描述：微信接口相关常量</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-6-2 下午11:08:14
 * @version 1.0.0
 */
public class WxConst
{
	// 消息类型
	public static enum MsgType
	{
		text,// 文本消息
		image,// 图片消息
		voice,// 语音消息
		video,// 视频消息
		shortvideo,// 小视频消息
		location,// 地理位置消息
		link,// 链接消息
		event,// 事件消息
		music,// 音乐消息
		news,// 图文消息
		;
	}
	// 事件类型
	public static enum Event
	{
		subscribe,// 订阅事件
		unsubscribe,// 取消订阅事件
		SCAN,// 扫描事件
		LOCATION,// 上报地理位置事件
		CLICK,// 自定义菜单拉取消息事件
		VIEW,// 自定义菜单链接跳转事件
		;
	}
}
