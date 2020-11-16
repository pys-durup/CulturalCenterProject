package com.project.center.program;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import data.Path;

public class ProgramManage {
	
	public static void main(String[] args) {
		
		programMain();
		
		while(true) {
			int Num;
			// 원하는 번호입력
			Num = selectNum();
			
			if(Num == 1) {//프로그램 조회
			
				System.out.println("프로그램 조회 선택");
				System.out.println("1.진행중인 프로그램\n\n2.종료된 프로그램");
				int Num2;
				Num2 = selectNum();
			
					if(Num2 ==1 ) {
				
					
					System.out.println("진행중인 프로그램 조회 선택");
					findProgramOnList();
					break;
					
					}else if (Num2 == 2) {
					
					System.out.println("종료된 프로그램 조회 선택");
					findProgramOffList();
					break;
					}else {
						
						while(Num2 !=1 || Num2 !=2) {
						
						
						System.out.println();
						System.out.println("\t\t\t올바른 번호를 입력해 주세요.");
						System.out.println("1.진행중인 프로그램\n\n2.종료된 프로그램");
						Num2 = selectNum();
						
						}
					
					}
			 
			}else if(Num == 2) { //프로그램 등록
			
				System.out.println("프로그램 등록 선택");
				insertProgram();
			
			
			}else if(Num == 3) { //프로그램 수정
		
				System.out.println("프로그램 수정 선택");
				modifyProgram();
				modifyNumprogram();
				
				
			}else if(Num == 4) { //프로그램 삭제
				
				System.out.println("프로그램 삭제 선택");
				deleteProgram();
				deleteNumProgram();
				
			}
			else if(Num == 5) { //종료
		
				System.out.println("프로그램을 종료합니다.");
				break;
			}else {
				
				programMain();
				System.out.println();
				System.out.println("\t\t\t올바른 번호를 입력해 주세요.");
				
			}
			


			
			
			
		}
		
	}//main

	
	
	private static void insertProgram() {
		// TODO Auto-generated method stub
		
	}



	private static void deleteNumProgram() {
		// TODO Auto-generated method stub
		
	}



	private static void deleteProgram() {
		// TODO Auto-generated method stub
		
	}



	private static void modifyNumprogram() {
		// TODO Auto-generated method stub
		
	}



	private static void modifyProgram() {
		// TODO Auto-generated method stub
		
	}

	private static void findProgramOffList() {
		
		System.out.println("종료됨");
		
	}


	private static void findProgramOnList() {

		final String PATH = Path.PROGRAMLIST;
		File file = new File(PATH);
		
		try {
			
			
			BufferedReader reader = new BufferedReader(new FileReader(PATH));
			
			String line =null;
			
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split(",");
				
				System.out.printf("%s\t%s\t%-18s\t%s\t%s\n"
						,temp[3]
						,temp[2]
						,temp[1]
						,temp[4]
						,temp[5]);
			}
			
			reader.close();
			
			
			
		} catch (Exception e) {
			System.out.println("읽기종료");
			
		}
		
	}



	// 프로그렘 관리 메인 메서드
	public static void programMain() {

		System.out.println("1.프로그램 조회\n\n2.프로그램 등록\n\n3.프로그램 수정\n\n4.프로그램 삭제\n\n5.종료");
	}
	
	// 번호 입력 메서드
	private static int selectNum() {
		
		// 사용자에게 번호를 입력받는다
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.print("번호를 선택하세요 : ");
		return Integer.parseInt(scan.nextLine());
	}
	
	//뒤로 가기 메서드
	private static void back() {
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.print("뒤로 가시려면 B를 눌러주세요 : ");
		String temp = scan.nextLine();
		if(temp.equals("b") || temp.equals("B")) {
			
			programMain();
		}
	}	

}
