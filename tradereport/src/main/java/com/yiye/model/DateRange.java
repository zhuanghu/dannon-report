package com.yiye.model;

public class DateRange {

	private Long start;
	private Long end;

	public DateRange() {
		super();
	}

	public DateRange(Long start, Long end) {
		super();
		this.start = start;
		this.end = end;
	}

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public Long getEnd() {
		return end;
	}

	public void setEnd(Long end) {
		this.end = end;
	}

}
