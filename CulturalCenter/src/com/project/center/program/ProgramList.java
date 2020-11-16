package com.project.center.program;

/**
 *   @author youngsu
 *   프로그램 조회시 해당 정보들을 담는 객체
 *   프로그램 코드, 프로그램 이름, 강사명, 강의실, 시작날짜, 종료날짜, 신청인원, 수강정원, 현재상태, 가격
 * 
 */
public class ProgramList {
	
	private String code;
	private String name;
	private String teacher;
	private String classRoom;
	private String startDate;
	private String endDate;
	private int count; // 신청인원
	private int capacity; // 수강정원
	private String state; // 모집중 or 모집마감
	private int price;
	
	public ProgramList(String code, String name, String teacher, String classRoom, String startDate, String endDate,
			int count, int capacity, String state, int price) {

		this.code = code;
		this.name = name;
		this.teacher = teacher;
		this.classRoom = classRoom;
		this.startDate = startDate;
		this.endDate = endDate;
		this.count = count;
		this.capacity = capacity;
		this.state = state;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	
}
