package com.yiye.dannon.service;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.taobao.api.*;
import com.taobao.api.domain.Order;
import com.taobao.api.domain.Trade;
import com.taobao.api.internal.util.WebUtils;
import com.taobao.api.request.TradesSoldGetRequest;
import com.taobao.api.request.TradesSoldIncrementvGetRequest;
import com.taobao.api.response.TradesSoldGetResponse;
import com.taobao.api.response.TradesSoldIncrementvGetResponse;
import com.yiye.dannon.dao.*;
import com.yiye.model.Config;
import com.yiye.model.LoginToken;
import com.yiye.model.TbOrder;
import com.yiye.model.TokenCache;

/**
 * http://open.taobao.com/doc/detail.htm?id=43
 * 商家后台系统每天调用次数100000次（可增加流量包），所以采用每天调用taobao.trades.sold.incrementv.get得到每天的增量订单
 * 
 */
public class TradeService {
	
	private static Logger logger_report_tracker = LoggerFactory.getLogger("report_tracker");
	
	public void getRemoteTradeData(String sellerNick) {
		String accessToken = getAccessToken(sellerNick);
		System.out.println("use access token : " + accessToken);
		if (accessToken == null) {
			return;
		}
		
		try {
			TaobaoClient client = new DefaultTaobaoClient(Config.API_URL, Config.APPKEY, Config.APPSECRET);
			TradesSoldGetRequest req = new TradesSoldGetRequest();
			//req.setFields("num,title,type,price,total_fee,created,paytime,end_time,sell_nick,payment,received_payment,orders");
			req.setFields("seller_nick, buyer_nick, title, type, created, tid, seller_rate,seller_can_rate, buyer_rate,can_rate, status, payment, discount_fee, adjust_fee, post_fee, total_fee, pay_time, end_time, modified, consign_time, buyer_obtain_point_fee, point_fee, real_point_fee, received_payment, pic_path, num_iid, num, price, cod_fee, cod_status, shipping_type,seller_flag,alipay_no,is_lgtype,is_force_wlb,is_brand_sale,buyer_area,has_buyer_message, credit_card_fee, lg_aging_type, lg_aging, step_trade_status,step_paid_fee,mark_desc,has_yfx,yfx_fee,yfx_id,yfx_type,trade_source,send_time,is_daixiao,is_wt,is_part_consign,orders");
			
			Date startDate = SimpleDateFormat.getDateTimeInstance().parse(
					"2013-09-01 00:00:00");
			req.setStartCreated(startDate);
			Date endDate = SimpleDateFormat.getDateTimeInstance().parse(
					"2013-09-30 23:59:59");
			req.setEndCreated(endDate);
			//req.setStatus("ALL_WAIT_PAY");
			//req.setType("guarantee_trade");
			//req.setExtType("service");
			req.setPageNo(1L);
			req.setPageSize(100L);
			TradesSoldGetResponse response = client.execute(req, accessToken);
			
			List<Trade> trades = response.getTrades();
			if (CollectionUtils.isEmpty(trades)) {
				logger_report_tracker.info("trades=0");
			} else {
				TbOrderDao dao = new TbOrderDao();
				logger_report_tracker.info("trades=" + trades.size());
				for (Trade trade : trades) {
					List<Order> orders = trade.getOrders();
					for (Order order : orders) {
						TbOrder tborder = TbOrder.convert(order);
						tborder.setTradeId(trade.getTid());
						tborder.setSellerNick(trade.getSellerNick());
						tborder.setCreated(trade.getCreated());
						tborder.setModified(trade.getModified());
						tborder.setPayTime(trade.getPayTime());
						tborder.setEndTime(trade.getEndTime());
						String json = JSON.toJSONString(tborder);
						System.out.println(json);
						dao.save(tborder);
						logger_report_tracker.info("order " + tborder.getOrderId() + " saved");
					} 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getRemoteIncrementTradeData(String sellerNick) {
		String accessToken = getAccessToken(sellerNick);
		System.out.println("use access token : " + accessToken);
		if (accessToken == null) {
			return;
		}
		
		try {
			TaobaoClient client = new DefaultTaobaoClient(Config.API_URL, Config.APPKEY, Config.APPSECRET);
			TradesSoldIncrementvGetRequest req = new TradesSoldIncrementvGetRequest();
			//req.setFields("num,title,type,price,total_fee,created,paytime,end_time,sell_nick,payment,received_payment,orders");
			req.setFields("seller_nick, buyer_nick, title, type, created, tid, seller_rate,seller_can_rate, buyer_rate,can_rate, status, payment, discount_fee, adjust_fee, post_fee, total_fee, pay_time, end_time, modified, consign_time, buyer_obtain_point_fee, point_fee, real_point_fee, received_payment, pic_path, num_iid, num, price, cod_fee, cod_status, shipping_type,seller_flag,alipay_no,is_lgtype,is_force_wlb,is_brand_sale,buyer_area,has_buyer_message, credit_card_fee, lg_aging_type, lg_aging, step_trade_status,step_paid_fee,mark_desc,has_yfx,yfx_fee,yfx_id,yfx_type,trade_source,send_time,is_daixiao,is_wt,is_part_consign,orders");
			
			Date startDate = SimpleDateFormat.getDateTimeInstance().parse(
					"2013-09-01 00:00:00");
			req.setStartCreate(startDate);
			Date endDate = SimpleDateFormat.getDateTimeInstance().parse(
					"2013-09-30 23:59:59");
			req.setEndCreate(endDate);
			//req.setStatus("ALL_WAIT_PAY");
			//req.setType("guarantee_trade");
			//req.setExtType("service");
			req.setPageNo(1L);
			req.setPageSize(100L);
			TradesSoldIncrementvGetResponse res = client.execute(req, accessToken);
			
			List<Trade> trades = res.getTrades();
			if (CollectionUtils.isEmpty(trades)) {
				logger_report_tracker.info("trades=0");
			} else {
				TbOrderDao dao = new TbOrderDao();
				logger_report_tracker.info("trades=" + trades.size());
				for (Trade trade : trades) {
					List<Order> orders = trade.getOrders();
					for (Order order : orders) {
						TbOrder tborder = TbOrder.convert(order);
						TbOrder existed = dao.findOrder(tborder.getOrderId());
						if (existed == null) {
							tborder.setTradeId(trade.getTid());
							tborder.setSellerNick(trade.getSellerNick());
							tborder.setCreated(trade.getCreated());
							tborder.setModified(trade.getModified());
							tborder.setPayTime(trade.getPayTime());
							tborder.setEndTime(trade.getEndTime());
							String json = JSON.toJSONString(tborder);
							System.out.println(json);
							dao.save(tborder);
							logger_report_tracker.info("order " + tborder.getOrderId() + " saved");
						} else {
							logger_report_tracker.info("order " + tborder.getOrderId() + " existed");
						}
					} 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getAccessToken(String sellerNick) {
		TokenCache cache = TokenCache.getInstance();
		LoginToken token = cache.get(sellerNick);
		if (token != null) {
			return token.getAccess_token();
		}
		return null;
	}
	
}
