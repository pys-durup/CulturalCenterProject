package com.project.center.program;

/**
 *  @author youngsu
 *  프로그램의 결제정보를 담는 객체
 *	TTTT,TA080001,9992,2020-11-13,200000,1
 *	결제코드, 프로그램코드, 회원번호, 결제일, 가격, 결제수단
 */
public class ProgramPaymentInfo {
	
	private String paymentCode;
	private String programCode;
	private String userCode;
	private String paymentDate;
	private int price;
	private String paymentType; // 1 = 휴대폰, 2 = 카드
	
	public ProgramPaymentInfo(String paymentCode, String programCode, String userCode, String paymentDate, int price,
			String paymentType) {
		
		this.paymentCode = paymentCode;
		this.programCode = programCode;
		this.userCode = userCode;
		this.paymentDate = paymentDate;
		this.price = price;
		this.paymentType = paymentType;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
}
