package com.haojinyu.twodate.beans;

import java.util.List;

public class Month {
	private String name;
	private List<DayPrice> days;
	
	
	public Month(String name, List<DayPrice> days) {
		super();
		this.name = name;
		this.days = days;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<DayPrice> getDays() {
		return days;
	}
	public void setDays(List<DayPrice> days) {
		this.days = days;
	}
	
}
