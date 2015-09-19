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
		
		CLICK,// 自定义菜单拉取消息事件
		
		VIEW,// 自定义菜单链接跳转事件
		
		LOCATION,// 上报地理位置事件
		
//		用户点击按钮后，微信客户端将调起地理位置选择工具，完成选择操作后，将选择的地理位置发送给开发者的服务器，同时收起位置选择工具，随后可能会收到开发者下发的消息。
		location_select,// 弹出地理位置选择器
		
//		用户点击按钮后，微信客户端将调起扫一扫工具，完成扫码操作后显示扫描结果（如果是URL，将进入URL），且会将扫码的结果传给开发者，开发者可以下发消息。
		scancode_push,// 扫码推事件
		
//		用户点击按钮后，微信客户端将调起扫一扫工具，完成扫码操作后，将扫码的结果传给开发者，同时收起扫一扫工具，然后弹出“消息接收中”提示框，随后可能会收到开发者下发的消息。
		scancode_waitmsg,// 扫码推事件且弹出“消息接收中”提示框
		
//		用户点击按钮后，微信客户端将调起系统相机，完成拍照操作后，会将拍摄的相片发送给开发者，并推送事件给开发者，同时收起系统相机，随后可能会收到开发者下发的消息。
		pic_sysphoto,// 弹出系统拍照发图
		
//		用户点击按钮后，微信客户端将弹出选择器供用户选择“拍照”或者“从手机相册选择”。用户选择后即走其他两种流程。
		pic_photo_or_album,// 弹出拍照或者相册发图
		
//		用户点击按钮后，微信客户端将调起微信相册，完成选择操作后，将选择的相片发送给开发者的服务器，并推送事件给开发者，同时收起相册，随后可能会收到开发者下发的消息。
		pic_weixin,// 弹出微信相册发图器
		
//		9、media_id：下发消息（除文本消息）
//		用户点击media_id类型按钮后，微信服务器会将开发者填写的永久素材id对应的素材下发给用户，永久素材类型可以是图片、音频、视频、图文消息。请注意：永久素材id必须是在“素材管理/新增永久素材”接口上传后获得的合法id。
		
//		10、view_limited：跳转图文消息URL
//		用户点击view_limited类型按钮后，微信客户端将打开开发者在按钮中填写的永久素材id对应的图文消息URL，永久素材类型只支持图文消息。请注意：永久素材id必须是在“素材管理/新增永久素材”接口上传后获得的合法id。
		;
	}
	
	public static enum QRcodeType
	{
		QR_SCENE,// 临时
		QR_LIMIT_SCENE,// 永久
		QR_LIMIT_STR_SCENE, //永久的字符串参数值
	}
}
