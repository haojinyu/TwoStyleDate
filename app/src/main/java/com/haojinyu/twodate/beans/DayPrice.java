package com.haojinyu.twodate.beans;

public class DayPrice {
	private String day;
	private String price;
	private boolean canClick;

	public DayPrice() {
		super();
	}

	public DayPrice(String day, String price, boolean canClick) {
		super();
		this.day = day;
		this.price = price;
		this.canClick = canClick;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public boolean isCanClick() {
		return canClick;
	}

	public void setCanClick(boolean canClick) {
		this.canClick = canClick;
	}

}
