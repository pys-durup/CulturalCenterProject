package com.project.center.faciltiy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;



public class SeminarReservation {

	ArrayList<SeminarReservation> slist = new ArrayList<SeminarReservation>();
	
	private String number;
	private String user;
	private String date;
	private String time;
	private String capacity;
	private String price;
	private String pay;
	private Calendar dateC;


	public SeminarReservation(String number, String user, String date, String time, String capacity, String price,
			String pay) {

		this.number = number;
		this.user = user;
		this.date = date;
		this.time = time;
		this.capacity = capacity;
		this.price = price;
		this.pay = pay;
		
		Calendar dateC = Calendar.getInstance(); //2020-11-16
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5, 7));
		int dateI = Integer.parseInt(date.substring(8));
		dateC.set(year, month+1, dateI);
		
	}
	
	@Override
	public String toString() {
		return "{number=" + number + ", user=" + user + ", date=" + date + ", time=" + time + ", capacity=" + capacity
				+ ", price=" + price + ", pay=" + pay + "}\n";
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

	
	public static void main(String[] args) {
		
		
		ArrayList<SeminarReservation> slist = new ArrayList<SeminarReservation>();
		
		loadSeminarReservation();
		
		System.out.print("날짜 입력 : ");
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		
		Calendar c = Calendar.getInstance();
		c.set(2020, 10, 30);
		int count=0; 
		
		for (int i=0; i <slist.size(); i++) {
			
			if(slist.get(i).dateC.compareTo(c)==0) {
				count++;
			}
			
		}
		
		System.out.println(count);
		
		
	}
	

	

	public static void loadSeminarReservation() {
		
	
		try {

			
			BufferedReader reader 
			= new BufferedReader(new FileReader("D:\\세미나실예약1.txt"));
			
						
			String line = "";
			
			ArrayList<SeminarReservation> slist = new ArrayList<SeminarReservation>();

			while((line = reader.readLine()) != null) {
				
				String[] temp = line.split(",");
			
				
				slist.add(new SeminarReservation(temp[0],temp[1],temp[2],temp[3],temp[4],temp[5], temp[6]));


			}
		
			reader.close();
			System.out.println(slist.get(50));
			System.out.println(slist.size());
			System.out.println(slist);
		
			
		} catch (Exception e) {
			System.out.println("SeminarReservation Exception");
			e.printStackTrace();
		}
		
	
	
		
	}


	
	
}
