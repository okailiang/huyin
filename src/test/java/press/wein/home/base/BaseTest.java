package press.wein.home.base;

import com.alibaba.fastjson.JSON;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试基本类
 */
@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = {"classpath:spring/applicationContext-test.xml"})
public class BaseTest extends TestCase {

    @BeforeClass
    public static void doInit() {
        System.setProperty("global.config.path", System.getProperty("user.dir") + "/src/main/resources/properties");
    }


    public String toJson(Object object) {
        return JSON.toJSONString(object);
    }

}
