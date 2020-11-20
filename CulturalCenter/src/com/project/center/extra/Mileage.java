package com.project.center.extra;
/**
 * @author youngsu 
 * 마일리지 객체
 * 
 */
public class Mileage {
	
	private String userCode;
	private int mileage;
	
	public Mileage(String userCode, int mileage) {
		this.userCode = userCode;
		this.mileage = mileage;
	}

	protected String getUserCode() {
		return userCode;
	}

	protected void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	protected int getMileage() {
		return mileage;
	}

	protected void setMileage(int mileage) {
		this.mileage = mileage;
	}

}
