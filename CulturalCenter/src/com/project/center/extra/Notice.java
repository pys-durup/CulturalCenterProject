package com.project.center.extra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class Notice {
	
	private String title;
	private String content;
	private String name;
	private String date;
	private String password;
	

	
	public static void main(String[] args) {
		

			
			
		File file = new File("src\\data\\공지사항.txt");
		System.out.println(file.exists());
		
		System.out.println("=========================");
		System.out.println("\t공지사항");
		System.out.println("=========================");
		
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		
		while(loop) {
			System.out.println("1. 공지사항 조회");
			System.out.println("2. 공지사항 등록");
			System.out.println("3. 공지사항 삭제");
			System.out.println("4. 종료");
			System.out.printf("번호를 선택하세요 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				findNoticeList();
				pause();
			} else if (sel.equals("2")) {
				insertNotice();
				pause();
			} else if (sel.equals("3")) {
				deleteNotice();
				pause();
			} else if (sel.equals("4")) {
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
	
	
	
	//공지사항 목록조회
	public static void findNoticeList() {
		
		
		try {
			
			BufferedReader reader 
			= new BufferedReader(new FileReader("src\\data\\공지사항.txt"));
						
			String line = "";
			while((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			reader.close();
							
			
		} catch (Exception e) {
			System.out.println("findNoticeList Exception");
			e.printStackTrace();
		}
		
		
		
	}
	
	//공지사항 등록
	public static void insertNotice() {
		
		
		System.out.println("공지사항 등록");
		
		try {
			BufferedWriter writer
				= new BufferedWriter(new FileWriter("src\\data\\공지사항.txt", true)); // 이어쓰기모드
			
			Scanner scan = new Scanner(System.in);

			System.out.print("제목 : ");
			String title = scan.nextLine();
	
//			System.out.print("내용 : ");
//			String age = scan.nextLine();
//			
//			System.out.print("성별(m/f) : ");
//			String gender = scan.nextLine();
//			
//			System.out.print("연락처 : ");
//			String tel = scan.nextLine();
//			
//			System.out.print("주소 : ");
//			String address = scan.nextLine();
			
			//홍길동,20,m,010-1234-5678,서울시 강남구 역삼동
			
//			writer.write(String.format("%s,%s,%s,%s,%s\r\n"
//										, name
//										, age
//										, gender
//										, tel
//										, address));
//			
			writer.write(String.format("%s", title));
			writer.close();
			
			System.out.println("저장 완료");
			pause();
			
			
		} catch (IOException e) {
			System.out.println("insertNotice Exception");
			e.printStackTrace();
		}
		
		
		
	} //insertNotice()
	
	
	
	
	//공지사항 삭제
	public static void deleteNotice() {
		
		
		
		
	}
	
	

}
