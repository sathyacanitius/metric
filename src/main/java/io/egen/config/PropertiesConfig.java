package io.egen.config;

import java.util.Properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.support.PropertiesLoaderSupport;

public class PropertiesConfig extends PropertiesLoaderSupport implements InitializingBean {

	private Properties properties;

	public String getProperty(String name) {
		return properties.get(name).toString();
	}

	public void afterPropertiesSet() throws Exception {
		properties = mergeProperties();
	}

}
