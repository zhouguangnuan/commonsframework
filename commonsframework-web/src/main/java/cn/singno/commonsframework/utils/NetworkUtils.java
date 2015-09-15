package cn.singno.commonsframework.utils;

import org.apache.log4j.Logger;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import sun.net.util.IPAddressUtil;

/**
 * <p>File：NetworkUtils.java</p>
 * <p>Title: 网络处理工具类</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-2-28 下午5:10:13</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
@SuppressWarnings("all")
public class NetworkUtils
{
    private static final Logger logger = Logger.getLogger(NetworkUtils.class);

    // 私有构造器，防止类的实例化
    private NetworkUtils()
    {
        super();
    }

    	/**
    	 * 将IP地址转换为整数类型
    	 * @param addr 字符串类型的IP地址
    	 * @return 整数
    	 */
	public static int ipToInt(final String ipStr)
	{
		int ip = 0;
		if (!IPAddressUtil.isIPv4LiteralAddress(ipStr))
		{
			String[] string = ipStr.split(",");
			int iLen = string.length;
			String ipStr_ = null;
			if (iLen > 0)
				ipStr_ = StringUtils.trimToEmpty(string[iLen - 1]);
			if (!IPAddressUtil.isIPv4LiteralAddress(ipStr))
			{
				return ip;
			}
		}
	
		if (StringUtils.isNotBlank(ipStr))
		{
			final String[] addressBytes = ipStr.split("\\.");
			ip = 0;
			for (int i = 0; i < 4; i++)
			{
				ip <<= 8;
				ip |= Integer.parseInt(addressBytes[i]);
			}
		}
		return ip;
	}

    /**
     * 将整数类型的IP地址转换为字符串类型的IP地址
     * @param i 整数
     * @return IP地址
     */
    public static String ipToStr(int ipInt)
    {
        if (ipInt == 0)
            return "";
        return ((ipInt >> 24) & 0xFF) + "." + ((ipInt >> 16) & 0xFF) + "."
                + ((ipInt >> 8) & 0xFF) + "." + (ipInt & 0xFF);
    }

    /**
     * 根据网卡取本机配置的IP
     * 如果是双网卡的，则取出外网IP
     * @return
     */
    public static String getNetIp()
    {
        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        String netip = null;// 外网IP
        try
        {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface
                    .getNetworkInterfaces();
            InetAddress ip = null;
            boolean finded = false;// 是否找到外网IP
            while (netInterfaces.hasMoreElements() && !finded)
            {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();
                while (address.hasMoreElements())
                {
                    ip = address.nextElement();
                    if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
                            && ip.getHostAddress().indexOf(":") == -1)
                    {// 外网IP
                        netip = ip.getHostAddress();
                        finded = true;
                        break;
                    }
                    else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
                            && ip.getHostAddress().indexOf(":") == -1)
                    {// 内网IP
                        localip = ip.getHostAddress();
                    }
                }
            }
        }
        catch (SocketException e)
        {
            logger.error(e);
        }
        if (netip != null && !"".equals(netip))
        {
            return netip;
        }
        else
        {
            return localip;
        }
    }
    
    /**
    * 根据网卡取本机配置的内网IP
    * 如果是双网卡的，则取出内网IP
    * @return String 内网IP地址
    */
    public static String getLocalIp()
    {
        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        try
        {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface
                    .getNetworkInterfaces();
            InetAddress ip = null;
            boolean finded = false;// 是否找到外网IP
            while (netInterfaces.hasMoreElements() && !finded)
            {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();
                while (address.hasMoreElements())
                {
                    ip = address.nextElement();
                    if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
                            && ip.getHostAddress().indexOf(":") == -1)
                    {
                        localip = ip.getHostAddress();
                        finded = true;
                        break;
                    }
                }
            }
        }
        catch (SocketException e)
        {
            logger.error(e);
        }
        return localip;
    }
    
    /**
     * 获取客户端（访问者）IP地址
     * @param request
     * @return
     */
    public static String getRemortIpStr(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(StringUtils.isBlank(ip)||ip.equalsIgnoreCase("unknown")){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(StringUtils.isBlank(ip)||ip.equalsIgnoreCase("unknown")){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(StringUtils.isBlank(ip)||ip.equalsIgnoreCase("unknown")){
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    /**
     * 获取客户端（访问者）IP地址
     * @param request
     * @return
     */
    public static int getRemortIpInt(HttpServletRequest request){
        String ip = getRemortIpStr(request);
        return ipToInt(ip);
    }
}
