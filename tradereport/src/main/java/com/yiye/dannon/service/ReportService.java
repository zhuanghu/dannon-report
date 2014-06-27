package com.yiye.dannon.service;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.taobao.api.*;
import com.taobao.api.domain.Order;
import com.taobao.api.domain.Trade;
import com.taobao.api.internal.util.WebUtils;
import com.taobao.api.request.TradesSoldGetRequest;
import com.taobao.api.response.TradesSoldGetResponse;
import com.yiye.dannon.dao.*;
import com.yiye.model.Config;
import com.yiye.model.Criteria;
import com.yiye.model.DateRange;
import com.yiye.model.Item;
import com.yiye.model.SellerStatistics;
import com.yiye.model.TbOrder;
import com.yiye.model.TradeStatus;
import com.yiye.utils.CommonUtils;

public class ReportService {
	
	private static Logger logger_report_tracker = LoggerFactory.getLogger("report_tracker");
	
	public List<TbOrder> getTbOrders(Criteria criteria) {
		TbOrderDao dao = new TbOrderDao();
		List<TbOrder> orders = dao.findOrders(criteria);
		return orders;
	}
	
	public List<SellerStatistics> getSellerReport(DateRange created) {
		Criteria criteria = new Criteria();
		criteria.setCreated(created);
		
		List<TbOrder> tborders = getTbOrders(criteria);
		if (tborders == null) return null;
		
		ItemDao itemDao = new ItemDao();
		List<Item> items = itemDao.findAllItems();
		if (CollectionUtils.isEmpty(items)) return null;
		
		Map<Long, String> mapItem = new HashMap<Long, String>();
		for (Item item : items) {
			mapItem.put(item.getItemId(), item.getTitle());
		}
		
		Map<String, SellerStatistics> map = new HashMap<String, SellerStatistics>();		
		for (TbOrder tbOrder : tborders) {
			Long itemId = tbOrder.getItemId();
			if (!mapItem.containsKey(itemId)) continue;
			
			String key = buildKey(tbOrder);
			SellerStatistics obj;
			if (map.containsKey(key)) {
				obj = map.get(key);
			} else {
				obj = new SellerStatistics();
				obj.setSellerNick(tbOrder.getSellerNick());
				obj.setRecordTime(tbOrder.getCreated());
				obj.setItemId(tbOrder.getItemId());
				obj.setTitle(mapItem.get(itemId));
			}
			Long number = tbOrder.getNumber();
			String status = tbOrder.getStatus();
			if (TradeStatus.isFinishStatus(status)) {
				obj.addSalesNumber(number);
			} else if (TradeStatus.isCloseStatus(status)) {
				obj.addRefundNumber(number);
			}
			obj.addOrderNumber(tbOrder.getNumber());
			map.put(key, obj);
		}
		
		List<SellerStatistics> statistics = new ArrayList<SellerStatistics>();
		statistics.addAll(map.values());
		return statistics;
	}
	
	private String buildKey(TbOrder tbOrder) {
		StringBuilder sb = new StringBuilder();
		sb.append(tbOrder.getSellerNick()).append("|");
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String k_date = sf.format(tbOrder.getCreated());
		sb.append(k_date).append("|");
		sb.append(tbOrder.getItemId());
		return sb.toString();
	}
		
}
