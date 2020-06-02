package main.java.dataProviders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

	private final String propertyFilePath = "configs//Configuration.properties";
	FileInputStream reader = null;
	static Properties properties = null;

	public ConfigFileReader() {
		try {
			reader = new FileInputStream(propertyFilePath);
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}
	}

	public static String getBaseURL_Magento() {
		String baseURL_Magento = properties.getProperty("baseURL_Magento");
		if (baseURL_Magento != null)
			return baseURL_Magento;
		else
			throw new RuntimeException("baseURL_Magento not specified in the Configuration.properties file.");
	}

	public static String getBaseURL_localhost() {
		String baseURL_localhost = properties.getProperty("baseURL_localhost");
		if (baseURL_localhost != null)
			return baseURL_localhost;
		else
			throw new RuntimeException("baseURL_localhost not specified in the Configuration.properties file.");
	}

	public static String getBaseURL_GR() {
		String baseURL_GR = properties.getProperty("baseURL_GR");
		if (baseURL_GR != null)
			return baseURL_GR;
		else
			throw new RuntimeException("baseURL_Magento not specified in the Configuration.properties file.");
	}

	public static String getUserName() {
		String username = properties.getProperty("username");
		if (username != null)
			return username;
		else
			throw new RuntimeException("username not specified in the Configuration.properties file.");
	}

	public static String getPassword() {
		String password = properties.getProperty("password");
		if (password != null)
			return password;
		else
			throw new RuntimeException("password not specified in the Configuration.properties file.");
	}
}
