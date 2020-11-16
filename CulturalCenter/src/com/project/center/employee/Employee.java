package com.project.center.employee;

import java.io.BufferedWriter;
import java.io.FileWriter;

import data.Path;

public class Employee {
	
	private String code;
	private String name;
	private String position;
	private String password;
	private String monthlyIncome;
	private String startDate;
			
	public Employee() {
		
		this.code = null;
		this.name = null;
		this.position = null;
		this.password = null;
		this.monthlyIncome = null;
		this.startDate = null;
		
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

	public String getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(String monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public void setEmployeeIntoData() {
		
		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(Path.EMPLOYEELIST, true));
			
			writer.newLine();
			writer.write(String.format("%s,%s,%s,%s,%s,%s"
					, this.code
					, this.name
					, this.position
					, this.password
					, this.monthlyIncome
					, this.startDate));
			
			writer.close();

		} catch (Exception e) {
			System.out.println("Employee.setEmployeeIntoData()");
			e.printStackTrace();
		}
		
	}
		
}

	
	


