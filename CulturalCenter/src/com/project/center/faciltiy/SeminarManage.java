package com.project.center.faciltiy;

import java.io.BufferedReader;
import java.io.FileReader;



public class SeminarManage {
	
	
	public static void main(String[] args) {
		
		
		findSeminarList();
	}
	
	
	
	
	public static void findSeminarList() {
		
		System.out.println("세미나실 목록");
		System.out.println("[No]\t[인원]\t[가격]");
		
		//int index = 0;
		
		try {
			
			BufferedReader reader 
			= new BufferedReader(new FileReader("src\\data\\세미나실.txt"));
						
			String line = "";
			
			while((line = reader.readLine()) != null) {
				
				String[] temp = line.split(",");
				
//				index++;
				System.out.printf("%s\t%s\t%s\t\r\n"
								, temp[0] //세미나실번호
								, temp[1] //수용인원
								, temp[2]); //가격


			}
		//	Event.index = index+1;
			reader.close();
							
			
		} catch (Exception e) {
			System.out.println("findSeminarList Exception");
			e.printStackTrace();
		}
	}
	
	

}
