package com.project.center.program;

import java.util.HashMap;

/**
 * @author youngsu 
 * 로그램 출결 정보 조회시 해당 정보들을 담는 객체 
 * 프로그램 코드, 출결날짜, 출결정보(T, F, T ,F....) 
 * 정보 조회시 프로그램수강생.txt를 참조해서 불러와야 한다
 * 
 */
public class ProgramAttendance {

	private String code;
	private String date;
	private HashMap<String, String> attendance;

	public ProgramAttendance(String code, String date, HashMap<String, String> attendance) {

		this.code = code;
		this.date = date;
		this.attendance = attendance;

	}

	protected String getCode() {
		return code;
	}

	protected void setCode(String code) {
		this.code = code;
	}

	protected String getDate() {
		return date;
	}

	protected void setDate(String date) {
		this.date = date;
	}

	protected HashMap<String, String> getAttendance() {
		return attendance;
	}

	protected void setAttendance(HashMap<String, String> attendance) {
		this.attendance = attendance;
	}
	
	// 

}
