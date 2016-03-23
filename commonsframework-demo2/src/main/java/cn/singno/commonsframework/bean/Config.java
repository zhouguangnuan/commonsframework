package cn.singno.commonsframework.bean;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Config
{
        public static PropertiesConfiguration weixin = null;
        
        static{
                try
                {
                        weixin = new PropertiesConfiguration("test/weixin.properties");
                } catch (ConfigurationException e)
                {
                        e.printStackTrace();
                }
        }
}
