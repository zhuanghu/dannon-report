package com.yiye.dannon.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.yiye.model.Criteria;
import com.yiye.model.DateRange;
import com.yiye.model.TbOrder;
import com.yiye.utils.*;


/**
 * 订单记录
 * @author tony
 *
 */
public class TbOrderDao {
	
	public void save(TbOrder order) {
		if (order.getOrderId() == null) return;
		
		TbOrder existed_order = findOrder(order.getOrderId());
		if (existed_order != null) {
			deleteOrder(order.getOrderId());
		}
		
		try {
			Connection conn = BoneCPManager.getConnectionPool().getConnection();
			conn.setAutoCommit(true);
			PreparedStatement stat = conn.prepareStatement("insert into dannon_tborder (tradeId,orderId,status,title,price,itemId,number,skuId,sellerNick,totalFee,payment,created,modified,payTime,endTime,refundStatus) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stat.setLong(1, order.getTradeId());
			stat.setLong(2, order.getOrderId());
			stat.setString(3, order.getStatus());
			stat.setString(4, order.getTitle());
			stat.setDouble(5, order.getPrice());
			stat.setLong(6, order.getItemId());
			stat.setLong(7, order.getNumber());
			stat.setString(8, order.getSkuId());
			stat.setString(9, order.getSellerNick());
			stat.setDouble(10, order.getTotalFee());
			stat.setDouble(11, order.getPayment());
			if (order.getCreated() == null) {
				stat.setTimestamp(12, null);
			} else {
				stat.setTimestamp(12, new Timestamp(order.getCreated().getTime()));
			}
			if (order.getModified() == null) {
				stat.setTimestamp(13, null);
			} else {
				stat.setTimestamp(13, new Timestamp(order.getModified().getTime()));
			}
			if (order.getPayTime() == null) {
				stat.setTimestamp(14, null);
			} else {
				stat.setTimestamp(14, new Timestamp(order.getPayTime().getTime()));
			}
			if (order.getEndTime() == null) {
				stat.setTimestamp(15, null);
			} else {
				stat.setTimestamp(15, new Timestamp(order.getEndTime().getTime()));
			}
			stat.setString(16, order.getRefundStatus());
			stat.executeUpdate();
			//conn.commit();
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
 	}
	
	public void deleteOrder(Long orderId) {
		try {
			Connection conn = BoneCPManager.getConnectionPool().getConnection();
			conn.setAutoCommit(true);
			PreparedStatement stat = conn.prepareStatement("delete from dannon_tborder where orderId=?");
			stat.setLong(1, orderId);
			stat.executeUpdate();
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public TbOrder findOrder(Long orderId) {
		try {
			Connection conn = BoneCPManager.getConnectionPool().getConnection();
			PreparedStatement stat = conn.prepareStatement("select * from dannon_tborder where orderId = ? limit 1");
			stat.setLong(1, orderId);
			ResultSet rs = stat.executeQuery();
			TbOrder tborder = null;
			if (rs.next()) {
				tborder = new TbOrder();
				tborder.setTradeId(rs.getLong("tradeId"));
				tborder.setOrderId(rs.getLong("orderId"));
				tborder.setStatus(rs.getString("status"));
				tborder.setTitle(rs.getString("title"));
				tborder.setPrice(rs.getDouble("price"));
				tborder.setItemId(rs.getLong("itemId"));
				tborder.setNumber(rs.getLong("number"));
				tborder.setSkuId(rs.getString("skuId"));
				tborder.setSellerNick(rs.getString("sellerNick"));
				tborder.setTotalFee(rs.getDouble("totalFee"));
				tborder.setPayment(rs.getDouble("payment"));
				tborder.setCreated(rs.getTimestamp("created"));
				tborder.setModified(rs.getTimestamp("modified"));
				tborder.setPayTime(rs.getTimestamp("payTime"));
				tborder.setEndTime(rs.getTimestamp("endTime"));
				tborder.setRefundStatus(rs.getString("refundStatus"));
			}
			conn.commit();
			stat.close();
			conn.close();
			return tborder;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<TbOrder> findOrders(Criteria criteria) {
		List<TbOrder> list = new ArrayList<TbOrder>();
		try {
			Connection conn = BoneCPManager.getConnectionPool().getConnection();
			StringBuilder sb = new StringBuilder("select * from dannon_tborder where 1=1");
			
			Long tradeId = criteria.getTradeId();
			if (tradeId != null) {
				sb.append(" and tradeId=").append(tradeId);
			}
			
			Long orderId = criteria.getOrderId();
			if (orderId != null) {
				sb.append(" and orderId=").append(orderId);
			}
			
			Long itemId = criteria.getItemId();
			if (itemId != null) {
				sb.append(" and itemId=").append(itemId);
			}
			
			String skuId = criteria.getSkuId();
			if (!StringUtils.isEmpty(skuId)) {
				sb.append(" and skuId='").append(skuId).append("'");
			}
			
			String sellerNick = criteria.getSellerNick();
			if (!StringUtils.isEmpty(sellerNick)) {
				sb.append(" and sellerNick='").append(sellerNick).append("'");
			}
			
			String title = criteria.getTitle();
			if (!StringUtils.isEmpty(title)) {
				sb.append(" and title like '%").append(StringEscapeUtils.escapeSql(title)).append("'");
			}
			
			DateRange dtcreated = criteria.getCreated();
			if (dtcreated != null) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dt_start = sf.format(new Date(dtcreated.getStart()));
				String dt_end = sf.format(new Date(dtcreated.getEnd()));
				sb.append(" and created between '").append(dt_start).append("' and '").append(dt_end).append("'");
			}
			
			DateRange dtpaytime = criteria.getPayTime();
			if (dtpaytime != null) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dt_start = sf.format(new Date(dtpaytime.getStart()));
				String dt_end = sf.format(new Date(dtpaytime.getEnd()));
				sb.append(" and payTime between '").append(dt_start).append("' and '").append(dt_end).append("'");
			}
			
			DateRange dtendtime = criteria.getEndTime();
			if (dtendtime != null) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dt_start = sf.format(new Date(dtendtime.getStart()));
				String dt_end = sf.format(new Date(dtendtime.getEnd()));
				sb.append(" and endTime between '").append(dt_start).append("' and '").append(dt_end).append("'");
			}

			String refundStatus = criteria.getRefundStatus();
			if (!StringUtils.isEmpty(refundStatus)) {
				sb.append(" and refundStatus='").append(refundStatus).append("'");
			}
			
			System.out.println(sb.toString());
			
			PreparedStatement stat = conn.prepareStatement(sb.toString());
			ResultSet rs = stat.executeQuery();
			
			while (rs.next()) {
				TbOrder tborder = new TbOrder();
				tborder.setTradeId(rs.getLong("tradeId"));
				tborder.setOrderId(rs.getLong("orderId"));
				tborder.setStatus(rs.getString("status"));
				tborder.setTitle(rs.getString("title"));
				tborder.setPrice(rs.getDouble("price"));
				tborder.setItemId(rs.getLong("itemId"));
				tborder.setNumber(rs.getLong("number"));
				tborder.setSkuId(rs.getString("skuId"));
				tborder.setSellerNick(rs.getString("sellerNick"));
				tborder.setTotalFee(rs.getDouble("totalFee"));
				tborder.setPayment(rs.getDouble("payment"));
				tborder.setCreated(rs.getTimestamp("created"));
				tborder.setModified(rs.getTimestamp("modified"));
				tborder.setPayTime(rs.getTimestamp("payTime"));
				tborder.setEndTime(rs.getTimestamp("endTime"));
				tborder.setRefundStatus(rs.getString("refundStatus"));
				list.add(tborder);
			}
			
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
