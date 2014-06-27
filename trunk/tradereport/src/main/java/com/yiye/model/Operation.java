package com.yiye.model;

import java.util.Date;

/**
 * 操作
 * @author tony
 *
 */
public class Operation {

	private String name;
	private String content;
	private String operator;
	private Date optime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getOptime() {
		return optime;
	}

	public void setOptime(Date optime) {
		this.optime = optime;
	}

}
