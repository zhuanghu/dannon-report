package com.yiye.model;


/**
 * 报表搜索条件
 * 
 * @author tony
 * 
 */
public class SellerCriteria {

	private String title;// 模糊
	private Long itemId;// 精确
	private DateRange recordTime;// 范围
	private String sellerNick;// 模糊

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

	public DateRange getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(DateRange recordTime) {
		this.recordTime = recordTime;
	}

	public String getSellerNick() {
		return sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

}
