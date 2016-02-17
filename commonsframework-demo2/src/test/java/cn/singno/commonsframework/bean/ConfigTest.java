package cn.singno.commonsframework.bean;

import org.junit.Test;

public class ConfigTest
{
        @Test
        public void testName() throws Exception
        {
                Integer APP_ID = Config.weixin.getInt("APP_ID");
                System.out.println(APP_ID);
        }
}
