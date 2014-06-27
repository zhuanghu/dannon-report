package com.yiye.model;

import java.util.Date;

import com.taobao.api.domain.Order;

/**
 * Order
 * @author tony
 *
 */
public class TbOrder {

	private Long tradeId;
	private Long orderId;
	private String status;
	private String title;
	private Double price;
	private Long itemId;//商品ID
	private Long number;//购买数量
	private String skuId;
	private String sellerNick;
	private Double totalFee;
	private Double payment;
	private Date created;
	private Date modified;
	private Date payTime;
	private Date endTime;
	private String refundStatus;

	public Long getTradeId() {
		return tradeId;
	}


	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}


	public Long getOrderId() {
		return orderId;
	}


	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public Long getItemId() {
		return itemId;
	}


	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}


	public Long getNumber() {
		return number;
	}


	public void setNumber(Long number) {
		this.number = number;
	}


	public String getSkuId() {
		return skuId;
	}


	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}


	public String getSellerNick() {
		return sellerNick;
	}


	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}


	public Double getTotalFee() {
		return totalFee;
	}


	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}


	public Double getPayment() {
		return payment;
	}


	public void setPayment(Double payment) {
		this.payment = payment;
	}


	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}


	public Date getModified() {
		return modified;
	}


	public void setModified(Date modified) {
		this.modified = modified;
	}


	public Date getPayTime() {
		return payTime;
	}


	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public String getRefundStatus() {
		return refundStatus;
	}


	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}


	public static TbOrder convert(Order order) {
		TbOrder tborder = new TbOrder();
		tborder.orderId = order.getOid();
		tborder.status = order.getStatus();
		tborder.title = order.getTitle();
		tborder.itemId = order.getNumIid();
		tborder.number = order.getNum();
		tborder.skuId = order.getSkuId();
		tborder.sellerNick = order.getSellerNick();
		if (order.getPrice() != null) {
			tborder.price = Double.parseDouble(order.getPrice());
		}
		if (order.getTotalFee() != null) {
			tborder.totalFee = Double.parseDouble(order.getTotalFee());
		}
		if (order.getPayment() != null) {
			tborder.payment = Double.parseDouble(order.getPayment());
		}
		tborder.refundStatus = order.getRefundStatus();
		return tborder;
	}

}
