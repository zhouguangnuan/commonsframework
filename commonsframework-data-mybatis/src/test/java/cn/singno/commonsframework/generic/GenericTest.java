package cn.singno.commonsframework.generic;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy(@ContextConfiguration(name = "parent", locations = {"classpath:config/spring-config-mybatis.xml"}))
public abstract class GenericTest extends AbstractJUnit4SpringContextTests
{
	/**
	 * Logger for this class
	 */
	protected static final Logger logger = Logger.getLogger(GenericTest.class);
	
}