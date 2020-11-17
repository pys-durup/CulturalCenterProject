package com.project.center.program;

/**
 * @author youngsu 
 * 프로그램 상태 정보 조회시 해당 정보들을 담는 객체 
 * 프로그램 코드, 시작날짜, 종료날짜, 프로그램상태(종료됨, 진행중, 시작전)
 * 
 */
public class ProgramState {

	private String code;
	private String startDate;
	private String endDate;
	private String state;

	public ProgramState(String code, String startDate, String endDate, String state) {
		this.code = code;
		this.startDate = startDate;
		this.endDate = endDate;
		this.state = state;
	}

	protected String getCode() {
		return code;
	}

	protected void setCode(String code) {
		this.code = code;
	}

	protected String getStartDate() {
		return startDate;
	}

	protected void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	protected String getEndDate() {
		return endDate;
	}

	protected void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	protected String getState() {
		return state;
	}

	protected void setState(String state) {
		this.state = state;
	}

}
