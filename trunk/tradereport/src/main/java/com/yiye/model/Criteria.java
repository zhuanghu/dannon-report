package com.yiye.model;


/**
 * 报表搜索条件
 * 
 * @author tony
 * 
 */
public class Criteria {

	private Long tradeId;// 精确
	private Long orderId;// 精确
	private String status;// 精确
	private String title;// 模糊
	private Long itemId;// 精确
	private String skuId;// 精确
	private DateRange created;// 范围
	private DateRange payTime;// 范围
	private DateRange endTime;// 范围
	private String sellerNick;// 模糊
	private String refundStatus;// 精确

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

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public DateRange getCreated() {
		return created;
	}

	public void setCreated(DateRange created) {
		this.created = created;
	}

	public DateRange getPayTime() {
		return payTime;
	}

	public void setPayTime(DateRange payTime) {
		this.payTime = payTime;
	}

	public DateRange getEndTime() {
		return endTime;
	}

	public void setEndTime(DateRange endTime) {
		this.endTime = endTime;
	}

	public String getSellerNick() {
		return sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

}
