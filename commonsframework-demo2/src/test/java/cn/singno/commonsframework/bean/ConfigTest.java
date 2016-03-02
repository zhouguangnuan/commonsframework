package cn.singno.commonsframework.bean;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfigTest
{
        @Test
        public void testName2() throws Exception
        {
                Integer APP_ID = Config.weixin.getInt("APP_ID");
                System.out.println(APP_ID);
        }
        
        @Test
        public void testName() throws Exception
        {
                for (int i = 0; i < 100; i++)
                {
                        new Thread(new Runnable()
                        {
                                @Override
                                public void run()
                                {
                                        System.out.println(Config.weixin.getInt("APP_ID"));
                                }
                        }).run();
                }
        }
}
