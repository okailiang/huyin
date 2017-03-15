package press.wein.home;

import org.apache.log4j.Logger;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成数据表对应的实体类和mapper文件
 */
public class MybatisGenerator {

	private static final Logger LOG = Logger.getLogger(MybatisGenerator.class);

	public static void main(String[] args) {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		String genCfg = "/generatorConfig.xml"; //src的一级目录下
		File configFile = new File(MybatisGenerator.class.getResource(genCfg).getFile());
		ConfigurationParser cp = new ConfigurationParser(warnings);
		org.mybatis.generator.config.Configuration config = null;
		try {
			config = cp.parseConfiguration(configFile);
		} catch (IOException e) {
			LOG.error("MybatisGenerator has IOException" , e);
		} catch (XMLParserException e) {
			LOG.error("MybatisGenerator has XMLParserException" , e);
		}
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = null;
		try {
			myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		} catch (InvalidConfigurationException e) {
			LOG.error("MybatisGenerator has InvalidConfigurationException", e);
		}
		try {
			if(myBatisGenerator != null){
				myBatisGenerator.generate(null);
			}
		} catch (SQLException e) {
			LOG.error("MybatisGenerator has SQLException", e);
		} catch (IOException e) {
			LOG.error("MybatisGenerator has IOException 2", e);
		} catch (InterruptedException e) {
			LOG.error("MybatisGenerator has InterruptedException", e);
		}
	}
}
