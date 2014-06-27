package com.yiye.dannon.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskService {
	
	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(100);
		
	public static void serve() {
		try {
			if (!scheduler.isShutdown()) {
//				TradeService
//				
//				//将cache中的statistics数据append到redis
//				HourlyStatisticsWriter writer = new HourlyStatisticsWriter();
//				scheduler.scheduleWithFixedDelay(writer, 1, 5, TimeUnit.MINUTES);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void shutdown() {		
		scheduler.shutdown();
	}
	
}
