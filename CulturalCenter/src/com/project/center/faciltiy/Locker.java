package com.project.center.faciltiy;

public class Locker {
	
		
	private String lockerNum;
	private String userCode;
	private String startDate;
	private String endDate;
	
	
	public Locker(String lockerNum, String userCode, String startDate, String endDate) {
		super();
		this.lockerNum = lockerNum;
		this.userCode = userCode;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	
	public String getLockerNum() {
		return lockerNum;
	}
	public void setLockerNum(String lockerNum) {
		this.lockerNum = lockerNum;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
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
	

}
