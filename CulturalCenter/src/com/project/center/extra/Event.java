package com.project.center.extra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.project.center.faciltiy.Seminar;

public class Event {
	
	
	static int index = 0;
	
	
	public static void main(String[] args) {
		

			
			
		File file = new File("src\\data\\이벤트.txt");
		System.out.println(file.exists());
		
		System.out.println("=========================");
		System.out.println("\t이벤트");
		System.out.println("=========================");
		
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		
		while(loop) {
			System.out.println("1. 이벤트 조회");
			System.out.println("2. 이벤트 등록");

			System.out.println("3. 종료");
			System.out.printf("번호를 선택하세요 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				findEventList();
				pause();
			} else if (sel.equals("2")) {
				insertEvent();
				pause();
			} else if (sel.equals("3")) {
				loop = false;
			}
			
			
		}
		
		System.out.println("프로그램 종료");
	

	
	
		
		
		
		
	}
	
	private static void pause() {

		Scanner scan = new Scanner(System.in);
		System.out.println("계속하려면 엔터를 입력하세요.");
		scan.nextLine();
		
	}
	
	
		
	
	
	//이벤트 목록조회
	public static void findEventList() {
		
		System.out.println("이벤트 목록");
		System.out.println("[No]\t[제목]\t\t\t\t\t\t[시작일]\t[종료일]\t[마일리지]");
		
		int index = 0;
		
		try {
			
			BufferedReader reader 
			= new BufferedReader(new FileReader("src\\data\\이벤트.txt"));
						
			String line = "";
			
			while((line = reader.readLine()) != null) {
				
				String[] temp = line.split(",");
				
				index++;
				System.out.printf("%d\t%-30s\t%-7s\t%-7s\t%s\r\n"
								, index //No
								, temp[1] //제목
								, temp[2] //시작일
								, temp[3] //종료일
								, temp[4]);//마일리지

			}
			Event.index = index+1;
			reader.close();
							
			
		} catch (Exception e) {
			System.out.println("findNoticeList Exception");
			e.printStackTrace();
		}
		
	
		
		
		
		
		
		
		
	}
	
	//이벤트 등록
	public static void insertEvent() {
		
		
		System.out.println("이벤트 등록");
		
		try {
			BufferedWriter writer
				= new BufferedWriter(new FileWriter("src\\data\\이벤트.txt", true)); // 이어쓰기모드
			
			Scanner scan = new Scanner(System.in);
			
			boolean loop = true;
//			while(loop) {
				
				System.out.print("제목 : ");
				String title = scan.nextLine();
//				if (title.length() <= 20) {
//					
//				} else {
//					System.out.println("잘못된 입력입니다.");
//					
//				}
				
				System.out.print("시작일 : ");
				String start = scan.nextLine();
				
				System.out.print("종료일 : ");
				String end = scan.nextLine();
				
				System.out.print("마일리지 : ");
				String mileage = scan.nextLine();
		
//			}
			

			
			writer.write(String.format("%d,%s,%s,%s,%s\r\n"
										, index
										, title
										, start
										, end
										, mileage));
			

			writer.close();
			
			System.out.println("저장 완료");
					
		} catch (IOException e) {
			System.out.println("insertNotice Exception");
			e.printStackTrace();
		}
		
		
		
	} //insertEvent()
	
	
	
	
	//이벤트 삭제
	public static void deleteNotice() {
		
		
		
		
	}
	
	
	
//	private static String birthCheck(String birth) {
//
//		//XXXXXXXX 일 경우 번호 중간에 하이픈 추가
//		if (birth.length() == 8) {
//			birth = String.format("%s-%s-%s"
//									, birth.substring(0, 4)
//									, birth.substring(4, 6)
//									, birth.substring(6, 8));
//		} else if (birth.length() == 10) { //XXXX-XX-XX 일 경우는 넘김
//		} else {
//			System.out.println("생년월일의 형식은 XXXX-XX-XX 입니다.");
//			insertUser();
//		}
//		return birth;
//	}
	


}
