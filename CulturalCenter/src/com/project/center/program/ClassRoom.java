package com.project.center.program;

public class ClassRoom {
	
	private String code; // 프로그램 코드
	private String name; //프로그램 이름
	private String teacher; // 강사명
	private String classRoom;
	private String startDate;
	private String endDate;
	private String programState; //프로그램 상태
	private int capacity; // 수강정원
	private int classRoomNum; //강의실번호

	
	public ClassRoom(String code, String name, String teacher, String classRoom, String startDate, String endDate,String programState,
			int capacity, int classRoomNum) {
		super();
		this.code = code;
		this.name = name;
		this.teacher = teacher;
		this.classRoom = classRoom;
		this.startDate = startDate;
		this.endDate = endDate;
		this.programState = programState;
		this.capacity = capacity;
		this.classRoomNum = classRoomNum;
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

	public String getProgramState() {
		return programState;
	}
	
	public void setProgramState(String programState) {
		this.programState = programState;
	}
	
	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	public int getClassRoomNum() {
		return classRoomNum;
	}


	public void setClassRoomNum(int classRoomNum) {
		this.classRoomNum = classRoomNum;
	}
	
	
	

}//class
