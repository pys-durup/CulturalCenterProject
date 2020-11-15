package com.project.center.employee;

import java.util.Calendar;

public class Employee {
	
	private String code;
	private String name;
	private String position;
	private String password;
	private int monthlyIncome;
	private Calendar startDate = Calendar.getInstance();
			
	public Employee() {
		
		this.code = null;
		this.name = null;
		this.position = null;
		this.password = null;
		this.monthlyIncome = 0;
		this.startDate = Calendar.getInstance();
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public int getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(int monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
		
}

	
	


