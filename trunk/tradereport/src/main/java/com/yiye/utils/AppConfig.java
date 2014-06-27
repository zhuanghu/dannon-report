package com.yiye.utils;

import java.io.IOException;
import java.util.Properties;

public class AppConfig {

	private static Properties prop = new Properties();

	static {
		try {
			prop.load(AppConfig.class.getResourceAsStream("/config.properties"));
			prop.load(AppConfig.class.getResourceAsStream("/jdbc.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return (String) prop.get(key);
	}

	public static int getIntegerProperty(String key) {
		String v = getProperty(key);
		return (v == null) ? 0 : Integer.parseInt(v);
	}

	public static boolean getBooleanProperty(String key) {
		String v = getProperty(key);
		return (v != null && v.equals("true"));
	}

	public static double getDoubleProperty(String key) {
		String v = getProperty(key);
		return (v == null) ? 0 : Double.parseDouble(v);
	}
}
