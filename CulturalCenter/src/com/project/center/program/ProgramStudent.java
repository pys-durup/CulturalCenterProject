package com.project.center.program;

import java.util.ArrayList;

/**
 *   @author youngsu
 *   프로그램의 현재신청인원, 등록가능/마감 상태, 수강생 목록을 담는 객체
 *
 */

public class ProgramStudent {

	private String code;
	private int count; // 수강중인 인원
	private String state; // 등록가능/마감
	private ArrayList<String> userCodes;
	
	
	/**
	 * 	ProgramStudent 생성자 
	 *  @param 
	 *  
	 */
	
	public ProgramStudent(String code, int count, String state, ArrayList<String> userList) {
		
		this.code = code;
		this.count = count;
		this.state = state;
		this.userCodes = userList;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public ArrayList<String> getUserCodes() {
		return userCodes;
	}


	public void setUserCodes(ArrayList<String> userCodes) {
		this.userCodes = userCodes;
	}
	
	
	
	
}
