package com.yiye.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ApplicationInitializer implements ServletContextListener {
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationInitializer.class);

	/**
	 * 初始化资源
	 */
	public void contextInitialized(ServletContextEvent arg0) {
	
		logger.info("Initial context");
	}

	/**
	 * 关闭资源
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
	
		logger.info("Destroy context");
	}

}
