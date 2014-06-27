package com.yiye.model;

import com.yiye.utils.AppConfig;

public class Config {

	public static String APPKEY = AppConfig.getProperty("APPKEY");
	public static String APPSECRET = AppConfig.getProperty("APPSECRET");
	public static String AUTHORIZE_URL = AppConfig.getProperty("AUTHORIZE_URL");
	public static String TOKEN_URL = AppConfig.getProperty("TOKEN_URL");
	public static String API_URL = AppConfig.getProperty("API_URL");
	
	public static String AUTH_DEFAULT_STAT = "copythat";
	
	public static String TOKEN_DUMPFILE = AppConfig.getProperty("TOKEN_DUMPFILE");
	
}
