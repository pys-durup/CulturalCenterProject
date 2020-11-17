package com.project.center.faciltiy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import data.Path;

public class SeminarReservation {

	
	
	//세미나실 정보(예약정보) ArrayList
	ArrayList<Seminar> list = new ArrayList<Seminar>();
	
	
	//세미나실 예약정보를 예약정보.txt에서 읽어오는 메서드
	public void getSeminarReservation() {
		
		
		
		try {

			BufferedReader reader 
			= new BufferedReader(new FileReader(Path.SEMINARRESERVATION)); 
			
						
			String line = "";
			
			//ArrayList<Seminar> list = new ArrayList<Seminar>();

			while((line = reader.readLine()) != null) {
				
				String[] temp = line.split(",");
	
				this.list.add(new Seminar(temp[0] //호실
											,temp[1] //예약자 회원코드
											,temp[2] //날짜
											,temp[3] //시간
											,temp[4] //정원
											,temp[5] //가격
											,temp[6])); //지불방법


			}
		
			reader.close();
			System.out.println(list.get(0));
			System.out.println(list.size());
			//System.out.println(list);
			
		
		
			
		} catch (Exception e) {
			System.out.println("getSeminarReservation()");
			e.printStackTrace();
		}
	
		
	}

	

	

	
	
}

