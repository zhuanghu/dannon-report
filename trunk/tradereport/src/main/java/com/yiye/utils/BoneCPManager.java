package com.yiye.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class BoneCPManager {
	private static BoneCP connectionPool;
	private static final Logger logger = LoggerFactory.getLogger(BoneCPManager.class);
	static {
		try {
			Class.forName(AppConfig.getProperty("mysql_driver"));
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl(AppConfig.getProperty("ds_url")); 
			config.setUsername(AppConfig.getProperty("ds_username"));
			config.setPassword(AppConfig.getProperty("ds_password"));
			config.setMinConnectionsPerPartition(Integer.parseInt(AppConfig.getProperty("mysql_minConnections")));
			config.setMaxConnectionsPerPartition(Integer.parseInt(AppConfig.getProperty("mysql_maxConnections")));
			config.setPartitionCount(Integer.parseInt(AppConfig.getProperty("mysql_partitionCount")));
			connectionPool = new BoneCP(config);
		} catch (Exception e) {
			logger.error("Initial BoneCP creation failed." + e);
		}
	}

	public static BoneCP getConnectionPool() {
		return connectionPool;
	}
	
}