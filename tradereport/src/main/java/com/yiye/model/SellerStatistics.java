package com.yiye.model;

import java.util.Date;

/**
 * 针对每个店铺的实际报表的一条记录VO
 * 
 * @author tony
 * 
 */
public class SellerStatistics {

	private String sellerNick;
	private Date recordTime;
	private Long itemId;// 商品ID
	private String title;
	private Long orderNumber;// 订单数量
	private Long salesNumber;// 实际销量
	private Long refundNumber;// 退货数量
	private Long pv;
	private Long uv_new;
	private Long uv_old;

	public String getSellerNick() {
		return sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Long getSalesNumber() {
		return salesNumber;
	}

	public void setSalesNumber(Long salesNumber) {
		this.salesNumber = salesNumber;
	}

	public Long getRefundNumber() {
		return refundNumber;
	}

	public void setRefundNumber(Long refundNumber) {
		this.refundNumber = refundNumber;
	}

	public Long getPv() {
		return pv;
	}

	public void setPv(Long pv) {
		this.pv = pv;
	}

	public Long getUv_new() {
		return uv_new;
	}

	public void setUv_new(Long uv_new) {
		this.uv_new = uv_new;
	}

	public Long getUv_old() {
		return uv_old;
	}

	public void setUv_old(Long uv_old) {
		this.uv_old = uv_old;
	}
	
	public void addOrderNumber(long number) {
		if (orderNumber == null) orderNumber = 0l;
		orderNumber += number;
	}
	
	public void addSalesNumber(long number) {
		if (salesNumber == null) salesNumber = 0l;
		salesNumber += number;
	}
	
	public void addRefundNumber(long number) {
		if (refundNumber == null) refundNumber = 0l;
		refundNumber += number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result
				+ ((recordTime == null) ? 0 : recordTime.hashCode());
		result = prime * result
				+ ((sellerNick == null) ? 0 : sellerNick.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		SellerStatistics other = (SellerStatistics) obj;
		
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		
		if (recordTime == null) {
			if (other.recordTime != null)
				return false;
		} else if (!recordTime.equals(other.recordTime))
			return false;
		
		if (sellerNick == null) {
			if (other.sellerNick != null)
				return false;
		} else if (!sellerNick.equals(other.sellerNick))
			return false;
		
		return true;
	}

}
