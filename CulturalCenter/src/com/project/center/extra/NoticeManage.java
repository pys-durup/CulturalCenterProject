package com.project.center.extra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;


import data.Path;


public class NoticeManage {
	
	
	//공지사항  ArrayList
	public static ArrayList<Notice> list = new ArrayList<Notice>();

	

	public static void main(String[] args) {
		

//			
//		File file = new File(Path.NOTICE);
//		System.out.println(file.exists());
		
		
		
		//공지사항.txt에서 읽어온다.
		getNotice();
		
		boolean loop = true;
		
		while(loop) {
			
			
			System.out.println("=========================");
			System.out.println("\t공지사항");
			System.out.println("=========================");
			
			System.out.println("1. 공지사항 조회");
			System.out.println("2. 공지사항 등록");
			System.out.println("3. 공지사항 삭제");
			System.out.println("4. 종료");
			System.out.println();
			System.out.print("번호를 선택하세요 : ");

			Scanner scan = new Scanner(System.in);
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
	
	
	
	
	//공지사항 공지사항.txt에서 읽어오는 메서드
	public static void getNotice() {
		
				
		//공지사항  ArrayList
		//ArrayList<Notice> list = new ArrayList<Notice>();
		
		
		try {

			BufferedReader reader 
			= new BufferedReader(new FileReader(Path.NOTICE)); 
			
		
			String[] temp = new String[5]; //공지사항 객체1개 저장될 배열
			int index = 0;
			String content = ""; //내용 저장할 변수 
			String line = "";

			while((line = reader.readLine()) != null) {
				
				if(!line.equals("====")) { // 공지사항 항목 구분기호 : "=====" (이전까지 읽어서 ArrayList에 저장 하도록)
		
					// 공지내용저장
					if (index == 3) 
					{						
						if (line.equals("#")) { // # 이나오면 내용 끝
							temp[index] = content; 
							index ++;
							
						} else {
							content += line + "\n"; //내용 변수에 line을 계속저장함		
						}
					
						
					// 내용이 아닐때는 한line씩 저장하며 index++;
					} else {	
						temp[index] = line;
						index ++;
					}
				
				}
				
				//공지사항 항목1개 list에 객체로 추가
				if(index == 5) { 
					
					list.add(new Notice(temp[0] // 번호
							, temp[1] // 제목
							, temp[2] // 날짜
							, temp[3] // 내용
							, temp[4])); // 비밀번호
					
					temp = new String[5]; // 공지1개 저장후 temp 초기화
					index = 0; // index 초기화
					content = ""; // content 초기화
				}
				
			}
		
			reader.close();
			System.out.println(list.size()); //나중에지울것
		
		
		
			
		} catch (Exception e) {
			System.out.println("getNotice()");
			e.printStackTrace();
		}
	
		
	}

	
	//공지사항 목록조회
	public static void findNoticeList() {
		
	
		System.out.println("[No][제목]\t\t\t\t[작성일]\t[작성자]");
		
		for(int i=0; i<list.size(); i++) {
			
	
			System.out.println(String.format("%2d  %-20s\t%s\t 관리자"
										,i+1
										,list.get(i).getTitle()
										,list.get(i).getDate()));
		}
		
		System.out.print("확인할 공지사항을 선택하세요: ");
		Scanner scan = new Scanner(System.in);
		int num = Integer.parseInt(scan.nextLine());
		
		//공지내용조회
		findNoticeContent(num-1);
		
		
		
		
	}
	
	
	//공지내용 조회
	public static void findNoticeContent(int num) {
		
		System.out.println();
		System.out.println("=========================");
		System.out.printf("[No] %d\n", num+1);
		System.out.printf("[제목] %s\n", list.get(num).getTitle());
		System.out.printf("[내용]\n\n%s\n", list.get(num).getContent());
		System.out.printf("[작성일] %s\n", list.get(num).getDate());
		System.out.println("=========================");
		System.out.println();
		pause();
	}
	
	
	
	
	
	
	
	//공지사항 등록 메소드
	public static void insertNotice() {
		
		
		System.out.println("공지사항 등록");
		
		try {
			BufferedWriter writer
				= new BufferedWriter(new FileWriter("src\\data\\공지사항.txt", true)); // 이어쓰기모드
			
			Scanner scan = new Scanner(System.in);

			System.out.print("제목 : ");
			String title = scan.nextLine();
	
			System.out.print("내용 : ");
			String content = scan.nextLine();
			
			System.out.print("비밀번호 : ");
			String password = scan.nextLine();
			
			Calendar now = Calendar.getInstance();
			
			writer.write(String.format("%d\n%s\n%tF\n%s\n#\n%s\n====\r\n"
										, list.size()-1 //번호
										, title //제목
										, now //작성일
										, content //내용
										, password)); //비번
		
			writer.close();
			
			System.out.println("저장 완료");
		
			
			
		} catch (IOException e) {
			System.out.println("insertNotice Exception");
			e.printStackTrace();
		}
		
		
		
	} //insertNotice()
	
	
	
	
	//공지사항 삭제
	public static void deleteNotice() {
		
		
		
		
	}

	

}
