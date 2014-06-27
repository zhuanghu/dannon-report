package com.yiye.model;

/**
 * 指定的商品(通过itemId或者title进行指定)
 * 
 * @author tony
 * 
 */
public class Item {

	private Long itemId;
	private String title;

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

}
