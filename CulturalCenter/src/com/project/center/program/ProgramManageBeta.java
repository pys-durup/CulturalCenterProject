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

public class ProgramManageBeta {
	
	public static void main(String[] args) throws IOException {
		
		programMain();
		
		while(true) {
			int Num;
			// 원하는 번호입력
			Num = selectNum();
			
			if(Num == 1) {//프로그램 조회
			
				System.out.println();
				System.out.println("프로그램 조회 선택");
				System.out.println("==============================================================================================");
				System.out.println("1.진행중인 프로그램\n\n2.종료된 프로그램");
				System.out.println("==============================================================================================");

				int Num2;
				Num2 = selectNum();
			
					while(true) {
						
						if(Num2 ==1 ) {
							
							
							System.out.println("진행중인 프로그램 조회 선택");
							findProgramOnList();
							back();
							break;
						}else if (Num2 == 2) {
							
							System.out.println("종료된 프로그램 조회 선택");
							findProgramOffList();
							back();
							break;
						}else {
							
							
							
							if(Num2 !=1 || Num2 !=2) {
								
								System.out.println();
								System.out.println("\t\t\t올바른 번호를 입력해 주세요.");
								System.out.println("1.진행중인 프로그램\n\n2.종료된 프로그램");
								Num2 = selectNum();
							}
					}
							
						
						
						
					
					}
			 
			}else if(Num == 2) { //프로그램 등록
			
				System.out.println("프로그램 등록 선택");
				System.out.println();
				findProgramOnList();
				insertProgram();
				System.out.println("프로그램 등록이 완료 되었습니다.");
				programMain();
			
			}else if(Num == 3) { //프로그램 수정
		
				System.out.println("프로그램 수정 선택");
				modifyProgram();
				modifyNumprogram();
				findProgramOnList();
				System.out.println("프로그램 수정이 완료 되었습니다.");
				programMain();
				
				
			}else if(Num == 4) { //종료
		
				System.out.println("프로그램을 종료합니다.");
				break;
			}else {
				
				programMain();
				System.out.println();
				System.out.println("\t\t\t올바른 번호를 입력해 주세요.");
				
			}
			


			
			
			
		}
		
	}//main

	
	//프로그램 등록 메서드
	private static void insertProgram() throws IOException {

		final String PATH = Path.PROGRAMLIST;
		File file = new File(PATH);
		System.out.println();
		System.out.println("==============================================================================================");
		System.out.println();
		System.out.println("예시 : AD020188,[고급]신나고즐거운종이접기,유진준,511,2020-12-1,2020-12-31,40,400000");
		System.out.println();
		System.out.println("등록할 프로그램 정보를 입력하여 주세요.");

		Scanner scan = new Scanner(System.in);
		String temp = scan.nextLine();
		try {
			FileWriter writer = new FileWriter(file,true);
			writer.write(temp);
			writer.write("\n");
			writer.close();
			
				

		} catch (Exception e) {
			System.out.println("");
			e.printStackTrace();
		}
		
	}



	//프로그램 수정 정보입력 메서드
	private static void modifyNumprogram() throws IOException {
		
		String fileName = "src\\data\\프로그램.txt";
		InputStream in = System.in;
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		System.out.print("수정하고 싶은 프로그램 정보 입력 : ");
		String target = reader.readLine();
		
		System.out.print("수정 내용 입력: ");
		  String change = reader.readLine();

		  BufferedReader outinfo = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		  String temp = null;
		  ArrayList info = new ArrayList();
		  
		  while((temp=outinfo.readLine()) != null ) {
		   info.add(temp.replaceAll(target, change));
		  }
		  
		  BufferedWriter newInfo = new BufferedWriter(new FileWriter("src\\data\\프로그램.txt", false)); 
		  for(int i=0; i < info.size(); i++) {
		   newInfo.write((String)info.get(i));
		   newInfo.newLine();
		  }
		  
		  newInfo.flush();
		  newInfo.close();
		  outinfo.close();
		  reader.close();
		  
		  
		
		
	}


	
	private static void modifyProgram() {
		
		findProgramOnList();
		
	}
	//종료된 프로그램 조회 메서드
	private static void findProgramOffList() {
		
		System.out.println("종료된 프로그램코드");
		final String PATH = Path.PROGRAMSTATE;
		File file = new File(PATH);
		
		try {
			
			
			BufferedReader reader = new BufferedReader(new FileReader(PATH));
			
			String line =null;
			
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split(",");
				if(temp[3].equals("종료됨")){
					
					System.out.printf("%s\t%s\t%s\t%s\t\n"
							,temp[0]
							,temp[1]
							,temp[2]
							,temp[3]);}
						
					
				
				}
			
			reader.close();
			
			
			
		} catch (Exception e) {
			System.out.println();
			
		}
				
		
	}

	//진행중 프로그램 조회메서드
	private static void findProgramOnList() {

		final String PATH = Path.PROGRAMLIST;
		File file = new File(PATH);
		
		try {
			
			
			BufferedReader reader = new BufferedReader(new FileReader(PATH));
			
			String line =null;
			
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split(",");
		
				System.out.printf("%-8s%-18s\t%s\t%s\t%s\t%s\t%s\t%s\n"
						,temp[0]
						,temp[1]
						,temp[2]
						,temp[3]
						,temp[4]
						,temp[5]
						,temp[6]
						,temp[7]);
			}
			
			reader.close();
			
			
			
		} catch (Exception e) {
			System.out.println();
			
		}
		
	}



	// 프로그렘 관리 메인 메서드
	public static void programMain() {
		System.out.println("==============================================================================================");	
		System.out.println("1.프로그램 조회\n\n2.프로그램 등록\n\n3.프로그램 수정\n\n4.종료");
		System.out.println("==============================================================================================");
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
		System.out.println();
		System.out.println("==============================================================================================");
		while(true) {
			System.out.print("뒤로 가시려면 B를 눌러주세요 : ");
			
			Scanner scan = new Scanner(System.in);
			String temp = scan.nextLine();
			if(temp.equals("b") || temp.equals("B")) {
				
				programMain();
				break;
			}
			
		}
		
	}	

}
