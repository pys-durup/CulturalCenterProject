package com.project.center.employee;

import java.io.BufferedWriter;
import java.io.FileWriter;

import data.Path;

public class EmployeeAttendance {
	
	private String code;
	private String name;
	private String date;
	private String commuteTime;
	private String workingTime;
	private String attendance;
	
	public EmployeeAttendance() {
		this.code = null;
		this.name = null;
		this.date = "9999-99-99";
		this.commuteTime = "99:99";
		this.workingTime = "99:99";
		this.attendance = "결근";
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCommuteTime() {
		return commuteTime;
	}

	public void setCommuteTime(String commuteTime) {
		this.commuteTime = commuteTime;
	}

	public String getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(String workingTime) {
		this.workingTime = workingTime;
	}

	public String getAttendance() {
		return attendance;
	}

	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	
	public void setEmployeeAttendanceIntoData() {
		
		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(Path.EMPLOYEELIST, true));
			
			writer.newLine();
			writer.write(String.format("%s,%s,%s,%s,%s,%s"
					, this.code
					, this.name
					, this.date
					, this.commuteTime
					, this.workingTime
					, this.attendance));
			
			writer.close();

		} catch (Exception e) {
			System.out.println("Employee.setEmployeeIntoData()");
			e.printStackTrace();
		}
		
	}
	
	
}
