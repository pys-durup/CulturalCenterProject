package com.project.center.faciltiy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Seminar {
	
	
	private String number; //세미나실 호수
	private String user; //예약자
	private String date; //날짜
	private String time; //시간
	private String capacity; //인원
	private String price; //가격
	private String pay; //지불방식
	private String state; //예약상태
	
	private Calendar dateC = Calendar.getInstance(); //날짜 캘린더형변환
	private int dateI;
	
	public Seminar(String number, String user, String date, String time, String capacity, String price,
			String pay) {

		this.number = number;
		this.user = user;
		this.date = date;
		this.time = time;
		this.capacity = capacity;
		this.price = price;
		this.pay = pay;
		
		if(this.user.equals("Null")) {
			this.state ="예약가능";
			
		} else {
			this.state ="예약완료";
		}
		
		this.setDateI(Integer.parseInt(date.substring(8)));
//		this.dateC.set(year, month-1, dateI);
		this.dateC.set(2020, 10, dateI); // 2020-11-date
	

	}
	
	public Calendar getDateC() {
		return dateC;
	}
	
	public void setDateC(Calendar dateC) {
		
		this.dateC = dateC;
	
	}


	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
		
		if(this.user.equals("")) {
			this.state ="예약가능";
			
		} else {
			this.state ="예약완료";
		}
		
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	public int getDateI() {
		return dateI;
	}

	public void setDateI(int dateI) {
		this.dateI = dateI;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "{number=" + number + ", user=" + user + ", date=" + date + ", time=" + time + ", capacity=" + capacity
				+ ", price=" + price + ", pay=" + pay + "}\n";
	}

//	public static void main(String[] args) {
//		
//		
//		ArrayList<SeminarReservation> slist = new ArrayList<SeminarReservation>();
//		
//		
//		
//		System.out.print("날짜 입력 : ");
//		Scanner scan = new Scanner(System.in);
//		String input = scan.nextLine();
//		
//		Calendar c = Calendar.getInstance();
//		c.set(2020, 10, 30);
//		int count=0; 
//		
//		for (int i=0; i <slist.size(); i++) {
//			
//			if(slist.get(i).dateC.compareTo(c)==0) {
//				count++;
//			}
//			
//		}
//		
//		System.out.println(count);
//		
//		
//	}
	
	
	


}

