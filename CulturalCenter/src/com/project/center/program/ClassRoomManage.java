package com.project.center.program;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import data.Path;

public class ClassRoomManage {
	
	public static void main(String[] args) throws IOException {
		
		//강의실 관리 메인화면 출력
		
		classRoomMain();
		while(true) {
			int Num;
			
			// 원하는 번호입력
			Num = selectNum();
			
			if(Num == 1) {//강의실 조회
			
				System.out.println("강의실 조회 선택");
				findClassRoom();
				back();
			
			}else if(Num == 2) { //강의실 수정
			
				System.out.println("강의실 수정 선택");
				System.out.println();
				modifyClassRoom();
				modifyContent();
				findClassRoom();
				System.out.println("수정이 완료 되었습니다.");
//				while(true) {
//					classRoomMain();
//					Num2 = selectNum();
//				}

			}else if(Num == 3) { //강의실 삭제
		
				System.out.println("강의실 삭제 선택");
				deleteClassRoom();
				deleteNumClassRoom();
				
				
			}else if(Num == 4) { //종료
		
				System.out.println("프로그램을 종료합니다.");
				break;
			}else {
				
				classRoomMain();
				System.out.println();
				System.out.println("\t\t\t올바른 번호를 입력해 주세요.");
				
			}
			
	}
			
			
			
		
	}//main

	//뒤로 가기 메서드
	private static void back() {
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.print("뒤로 가시려면 B를 눌러주세요 : ");
		String temp = scan.nextLine();
		
		while(true) {
			
			if(temp.equals("b") || temp.equals("B")) {
				
				classRoomMain();
				break;
			}else if (!temp.equals("b") || temp.equals("B")) {
				System.out.print("뒤로 가시려면 B를 눌러주세요 : ");
				temp = scan.nextLine();
				
				
			}
		}
	
	
	}
	
	
	
	// 강의실 관리 메인 메서드
	public static void classRoomMain() {

		System.out.println("\t1. 강의실 조회\t2.강의실 수정\t3.강의실 삭제\t4.종료");
		                                                                                    
	}	
	//강의실삭제 번호 입력 메서드
	private static int deleteNumClassRoom() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.print("삭제하실 강의실 번호를 선택하세요 : ");
		return Integer.parseInt(scan.nextLine());
	}
		
	//강의실수정 번호 입력 메서드
	private static int selectNumClassRoom() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.print("수정하실 강의실 번호를 선택하세요 : ");
		return Integer.parseInt(scan.nextLine());
	}
	
	
	// 번호 입력 메서드
	private static int selectNum() {

		// 사용자에게 번호를 입력받는다
	
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.print("번호를 선택하세요 : ");
		String temp = scan.nextLine();
		return Integer.parseInt(temp);
		//return Integer.parseInt(scan.nextLine());
		
	}
	
	//강의실 조회 메서드
	public static void findClassRoom() {
		
		System.out.println("강의실번호\t프로그램코드\t프로그램명\t\t\t강사명\t시작날짜\t\t종료날짜\t\t정원");
		final String PATH = Path.CLASSROOM;
		File file = new File(PATH);
		
		try {
			
			
			BufferedReader reader = new BufferedReader(new FileReader(PATH));
			
			String line =null;
			
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split(",");
				
				System.out.printf("%s\t%s\t%-18s\t%s\t%s\t%s\t%s\n"
						,temp[0]
						,temp[1]
						,temp[2]
						,temp[3]
						,temp[4]
						,temp[5]
						,temp[6]);
				
			}
			
			reader.close();
			
			
			
		} catch (Exception e) {
			System.out.println("읽기종료");
			
		}
	}

	//강의실 수정 문구 메서드
	private static void modifyContent() throws IOException {
		
		String fileName = "src\\data\\강의실.txt";
		InputStream in = System.in;
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		System.out.print("수정하고 싶은 강의실 정보 입력 : ");
		String target = reader.readLine();
		
		System.out.print("수정 내용 입력: ");
		  String change = reader.readLine();

		  BufferedReader outinfo = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		  String temp = null;
		  ArrayList info = new ArrayList();
		  
		  while((temp=outinfo.readLine()) != null ) {
		   info.add(temp.replaceAll(target, change));
		  }
		  
		  BufferedWriter newInfo = new BufferedWriter(new FileWriter("src\\data\\강의실.txt", false)); 
		  for(int i=0; i < info.size(); i++) {
		   newInfo.write((String)info.get(i));
		   newInfo.newLine();
		  }
		  
		  newInfo.flush();
		  newInfo.close();
		  outinfo.close();
		  reader.close();
		  
		  
		

	}
	
	
	//강의실 수정 메서드
	public static void modifyClassRoom() {
		System.out.println("강의실번호\t프로그램코드\t프로그램명\t\t\t강사명\t시작날짜\t\t종료날짜\t\t정원");
		final String PATH = Path.CLASSROOM;
		File file = new File(PATH);
		
		try {
			
			
			BufferedReader reader = new BufferedReader(new FileReader(PATH));
			
			String line =null;
			
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split(",");
				
				System.out.printf("%s\t%s\t%-18s\t%s\t%s\t%s\t%s\n"
						,temp[0]
						,temp[1]
						,temp[2]
						,temp[3]
						,temp[4]
						,temp[5]
						,temp[6]);
				
			}
			
			reader.close();
			
			
			
		} catch (Exception e) {
			System.out.println("읽기종료");
			
		}
		
		
			
	}
	
	//강의실 삭제 메서드
	public static void deleteClassRoom() {
		System.out.println("강의실번호\t프로그램코드\t프로그램명\t\t\t강사명\t시작날짜\t\t종료날짜\t\t정원");		
		final String PATH = Path.CLASSROOM;
		File file = new File(PATH);
		
		try {
			
			
			BufferedReader reader = new BufferedReader(new FileReader(PATH));
			
			String line =null;
			
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split(",");
			
				System.out.printf("%s\t%s\t%-18s\t%s\t%s\t%s\t%s\n"
						,temp[0]
						,temp[1]
						,temp[2]
						,temp[3]
						,temp[4]
						,temp[5]
						,temp[6]);
				
			}
			
			reader.close();
			
			
			
		} catch (Exception e) {
			System.out.println("읽기종료");
			
		}
		
	}
	
	

	
	
	
	
	

}//classRoomManage
