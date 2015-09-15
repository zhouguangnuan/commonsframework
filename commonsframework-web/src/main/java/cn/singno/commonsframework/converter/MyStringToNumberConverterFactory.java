/**
 * <p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.converter</p>
 * <p>文件名：MyStringToNumberConverterFactory.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-5-18-下午9:21:39</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.NumberUtils;

/**
 * <p>名称：MyStringToNumberConverterFactory.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-5-18 下午9:21:39
 * @version 1.0.0
 */
public final class MyStringToNumberConverterFactory implements ConverterFactory<String, Number> 
{
	@Override
	public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
		return new StringToNumber<T>(targetType);
	}

	private static final class StringToNumber<T extends Number> implements Converter<String, T> {

		private final Class<T> targetType;

		public StringToNumber(Class<T> targetType) {
			this.targetType = targetType;
		}

		@Override
		public T convert(String source) {
			if (StringUtils.isBlank(source)) {
				return null;
			}
			T number =  null;
			try
			{
				number = NumberUtils.parseNumber(source, this.targetType);
			} catch (Exception e) { }
			return number;
		}
	}
}
