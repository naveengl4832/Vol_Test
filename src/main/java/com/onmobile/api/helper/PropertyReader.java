package com.onmobile.api.helper;

import com.onmobile.api.config.Constants;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertyReader implements Constants {
	static String sourceClass = PropertyReader.class.getName();
	static Logger LOGGER = Logger.getLogger(sourceClass);
	static String sourceMethod;

	/**
	 * Get value from properties files
	 * 
	 */

	public static String getProperty(String path, String key) {
		sourceMethod = "getProperty";
		LOGGER.entering(sourceClass, sourceMethod);

		LOGGER.info("key" + key);
		String propertyValue = "";
		try {

			Properties p = new Properties();
			p.load(new FileInputStream(path));
			propertyValue = p.getProperty(key);

		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("propertyValue = " + propertyValue);
		LOGGER.exiting(sourceClass, sourceMethod);
		return propertyValue;

	}

	public static String getPropertyValue(String propertypath, String propertykey) {
		sourceMethod = "getPropertyValue";
		LOGGER.entering(sourceClass, sourceMethod);

		LOGGER.info("key" + propertykey);
		String propertyValue = PropertyReader.getProperty(propertypath, propertykey);

		LOGGER.info("propertyValue = " + propertyValue);
		LOGGER.exiting(sourceClass, sourceMethod);

		return propertyValue + ENVIRONMENT;
	}
}
