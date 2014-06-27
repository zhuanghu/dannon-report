package com.yiye.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * 交易状态
 * 
 * @author tony
 * 
 */
public class TradeStatus {
	
	private final static Set<String> FINISH_STATUS;
	private final static Set<String> CLOSE_STATUS;
	
	static {
		String[] success_status = { "TRADE_FINISHED", "TRADE_BUYER_SIGNED" };
		FINISH_STATUS = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(success_status)));
		String[] close_status = { "TRADE_CLOSED", "TRADE_CLOSED_BY_TAOBAO" };
		CLOSE_STATUS = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(close_status)));
	}

	public static boolean isFinishStatus(String status) {
		return FINISH_STATUS.contains(status);
	}
	
	public static boolean isCloseStatus(String status) {
		return CLOSE_STATUS.contains(status);
	}
}
