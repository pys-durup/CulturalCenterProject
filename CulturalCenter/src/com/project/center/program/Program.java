package com.project.center.program;

public class Program {

	private String code;
	private String name;
	private String teacher;
	private String classRoom;
	private String startDate;
	private String endDate;
	private int capacity; // 수강정원
	private int price;
	
	public Program(String code, String name, String teacher, String classRoom, String startDate, String endDate,
			int capacity, int price) {
		
		this.code = code;
		this.name = name;
		this.teacher = teacher;
		this.classRoom = classRoom;
		this.startDate = startDate;
		this.endDate = endDate;
		this.capacity = capacity;
		this.price = price;
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

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
