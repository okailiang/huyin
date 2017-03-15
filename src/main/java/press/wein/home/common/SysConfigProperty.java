package press.wein.home.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * 加载系统配置的properties文件配置，需要配置在ApplicationContext中
 *
 * @author oukailiang
 * @create 2017-02-24 上午11:01
 */
public class SysConfigProperty extends PropertyPlaceholderConfigurer {

    private static Properties properties;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        setProperties(props);
    }

    public void setProperties(Properties props) {
        properties = props;
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}
