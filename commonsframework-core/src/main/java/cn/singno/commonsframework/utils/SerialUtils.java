package cn.singno.commonsframework.utils;

import java.util.UUID;
import org.apache.commons.lang3.StringUtils;

public class SerialUtils {
	
	/**
	 * 描述：
	 * <pre>创建数据表主件</pre>
	 * @return
	 */
	public static String buildRefrenceId(){
		return StringUtils.replace(UUID.randomUUID().toString(), "-", "").toUpperCase();
	};
	
	
	
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(buildRefrenceId());
		}
	}
}
