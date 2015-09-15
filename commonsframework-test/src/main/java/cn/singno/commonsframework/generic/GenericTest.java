/**<p>项目名：</p>
 * <p>包名：	cn.singno.commonsframework.test</p>
 * <p>文件名：AbstractTest.java</p>
 * <p>版本信息：</p>
 * <p>日期：2014年8月10日-下午11:49:14</p>
 * Copyright (c) 2014singno公司-版权所有
 */
package cn.singno.commonsframework.generic;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**<p>名称：AbstractTest.java</p>
 * <p>描述：</p>
 * <pre>
 *         
 * </pre>
 * @author 周光暖
 * @date 2014年8月10日 下午11:49:14
 * @version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy(@ContextConfiguration(name = "parent", locations = {"classpath:spring-mvc.xml", "classpath:spring-config.xml"}))
public abstract class GenericTest extends AbstractJUnit4SpringContextTests
{
	
}
