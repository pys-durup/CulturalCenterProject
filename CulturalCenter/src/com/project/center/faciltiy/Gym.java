package com.project.center.faciltiy;

import java.util.Calendar;

public class Gym {
	
	private String name;
	private String userCode;
	private String date;
	private String time;
	private String price;
	private String pay;
	
	public Gym(String time) {
		this.time = time;
	}

	public Gym(String name, String price) {
		super();
		this.name = name;
		this.price = price;
	}

	public Gym(String name, String date, String time, String price) {
		this.name = name;
		this.date = date;
		this.time = time;
		this.price = price;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	@Override
	public String toString() {
		return "Gym [name=" + name + ", userCode=" + userCode + ", date=" + date + ", time=" + time + ", price=" + price
				+ ", pay=" + pay + "]" + "\n";
	}

	
	
}
