package java.cn.singno.commonsframework.log.mongodb;

import org.apache.log4j.Logger;
import org.junit.Test;

public class TestLog
{
        private static final Logger logger = Logger.getLogger(TestLog.class);
        
        @Test
        public void testname() throws Exception
        {
                logger.info("hello log4j+mongodb");
        }
}
