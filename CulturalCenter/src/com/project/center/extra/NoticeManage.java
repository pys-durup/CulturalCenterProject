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
	
	
	//공지사항 ArrayList 전역변수선언
	public static ArrayList<Notice> list;

	
	public static void main(String[] args) {
			
		
		boolean loop = true;
		
		while(loop) {
			
			
			//공지사항.txt에서 읽어온다.
			list = new ArrayList<Notice>();
			getNotice();
			
			System.out.println("=====================================");
			System.out.println("\t\t공지사항");
			System.out.println("=====================================");
			
			System.out.println("\t1. 공지사항 조회");
			System.out.println("\t2. 공지사항 등록");
			System.out.println("\t3. 공지사항 삭제");
			System.out.println("\t4. 메인으로");
			System.out.println("=====================================");
			System.out.println();
			System.out.print("번호를 선택하세요 : ");
			System.out.println();
			
			
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
	
	
	//회원모드에서 공지사항을 확인할 수 있는 메서드
	public static void userNotice() {
		
		list = new ArrayList<Notice>();
		getNotice();
		findNoticeList();
		
	}
	


	private static void pause() {

		Scanner scan = new Scanner(System.in);
		System.out.println("계속하려면 엔터를 입력하세요.");
		scan.nextLine();
		for(int i=0; i<10; i++) {
			System.out.println();
		}
		
	}
	
	
	
	
	//공지사항 공지사항.txt에서 읽어오는 메서드
	public static void getNotice() {
	
		try {

			BufferedReader reader 
			= new BufferedReader(new FileReader(Path.NOTICE)); 
			
		
			String[] temp = new String[5]; //공지사항 항목 1개 저장될 배열
			int index = 0;
			String content = ""; //내용 저장할 변수 
			String line = "";

			while((line = reader.readLine()) != null) {
				
				if(!line.equals("====")) { //공지사항 항목 구분기호 "=====" (이전까지 읽어서 항목1개씩 Arraylist인자로 저장 됨)
		
					//내용(constent)저장
					if (index == 3) 
					{						
						if (line.equals("#")) { // # 이나오면 내용 끝
							temp[index] = content; 
							index ++;
							
						} else {
							content += line + "\n"; //내용 변수에 line을 계속저장함		
						}
					
						
					// content 아닐때 (제목,작성일 등 기타) 한line씩 저장하며 index++;
					} else {	
						temp[index] = line;
						index ++;
					}
				
				}
				
				//공지사항 항목1개 -> list에 Notice객체로 추가
				if(index == 5) { 
					
					list.add(new Notice(temp[0] // 번호
							, temp[1] // 제목
							, temp[2] // 날짜
							, temp[3] // 내용
							, temp[4])); // 비밀번호
					
					temp = new String[5]; // 항목1개 저장후 temp 초기화
					index = 0; // index 초기화
					content = ""; // content 초기화
				}
				
			}
		
			reader.close();
			//System.out.println(list.size()); //저장확인용 (항목갯수 맞는지확인)
		
		
		
			
		} catch (Exception e) {
			System.out.println("getNotice()");
			e.printStackTrace();
		}
	
		
	}

	
	//공지사항 목록조회
	public static void findNoticeList() {
		
		System.out.println();
		System.out.println("==========================================================================");
		System.out.println("\t\t\t공지사항 조회");
		System.out.println("==========================================================================");
		System.out.println("[No]\t\t[제목]\t\t\t[작성일]\t[작성자]");
		
		for(int i=0; i<list.size(); i++) {
			
	
			System.out.println(String.format("%2s  %-20s\t%s\t 관리자"
										,list.get(i).getNum()
										,list.get(i).getTitle()
										,list.get(i).getDate()));
		}
		
		System.out.println("==========================================================================");
		System.out.println();
		System.out.print("확인할 공지사항을 선택하세요: ");
		Scanner scan = new Scanner(System.in);
		int num = Integer.parseInt(scan.nextLine());
		
		//공지내용조회
		findNoticeContent(num-1);
		
	
	}
	
	
	
	//공지내용 조회
	public static void findNoticeContent(int num) {
		
		System.out.println();
		System.out.println("==========================================================================");
		System.out.printf("[No] %s\n", list.get(num).getNum());
		System.out.printf("[제목] %s\n", list.get(num).getTitle());
		System.out.printf("[내용]\n\n%s\n", list.get(num).getContent());
		System.out.printf("[작성일] %s\n", list.get(num).getDate());
		System.out.println("==========================================================================");
		System.out.println();
		
	}
	
	
	
	
	
	
	
	//공지사항 등록 메소드
	public static void insertNotice() {
		
		System.out.println();
		System.out.println("=====================================");
		System.out.println("\t   공지사항 등록");
		System.out.println("=====================================");
		System.out.println();
		
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
										, list.size()+1 //번호
										, title //제목
										, now //작성일
										, content //내용
										, password)); //비번
		
			writer.close();
			
			System.out.println();
			System.out.println("저장 완료되었습니다.");
			System.out.println();
			
			
		} catch (IOException e) {
			System.out.println("insertNotice Exception");
			e.printStackTrace();
		}
		
		
		
	} //insertNotice()
	
	
	
	
	//공지사항 삭제 메소드
	public static void deleteNotice() {
		
		System.out.println();
		System.out.println("=====================================");
		System.out.println("\t   공지사항 삭제");
		System.out.println("=====================================");
		System.out.println();
		
		//삭제할 번호선택
		int num = selectNum();

		boolean loop = true;
		while(loop) {
			
			//번호가 존재할때
			if(num >=1 && num <= list.size()) {
				
				//해당글 내용 확인
				findNoticeContent(num-1);
				
				System.out.println("해당 글을 삭제하시겠습니까?");
				System.out.println("1.삭제 2.재선택");
				Scanner scan = new Scanner(System.in);
				int sel = Integer.parseInt(scan.nextLine());
				
				if(sel == 1) { //삭제
					break;
				
				} else { //재선택
					
					//삭제할 번호 재선택
					num = selectNum();
					continue;
				}
					
				
				
			//번호가 존재하지 않을때	
			} else {
				System.out.println();
				System.out.println("존재하지 않는 번호 입니다.");
				System.out.println("1.재입력 2.처음으로 돌아가기");
				Scanner scan = new Scanner(System.in);
				int sel = Integer.parseInt(scan.nextLine());
				
				if(sel == 1) {
					num = selectNum();
				} else {
					loop = false;
				}
				
			}
		}
		
		
		
		while (loop) {
			
			
			System.out.print("비밀번호를 입력해주세요 : ");
			Scanner scan = new Scanner(System.in);
			String password = scan.nextLine();
			
			//비밀번호 유효성 판정
			if (list.get(num-1).getPassword().equals(password)) {
			
				//Arraylist에서 해당항목 삭제
				for(int i=0; i<list.size(); i++) {
					
					if(num == Integer.parseInt(list.get(i).getNum())) { // 입력받은 번호 1번 -> index =0
						
						
						list.remove(i);
						
					}
				}
				
				try {
					BufferedWriter writer
						= new BufferedWriter(new FileWriter(Path.NOTICE)); 
					
					
					//예약정보 수정된 s.list 덮어쓰기
					for(int i=0; i<list.size(); i++) {
						
		
						writer.write(String.format("%d\n%s\n%s\n%s#\n%s\n====\r\n"
								, i+1
								, list.get(i).getTitle()
								, list.get(i).getDate()
								, list.get(i).getContent()
								, list.get(i).getPassword()));
							
					}
					
					writer.close();
					
					System.out.println();
					System.out.println("삭제가 완료되었습니다.");
					System.out.println();
					break;
					
					
				} catch (IOException e) {
					System.out.println("deleteNotice");
					e.printStackTrace();
				}
			
			//비밀번호 틀렸을 때, 그냥 재입력해야됨
			} else {
				
				System.out.println("비밀번호가 틀렸습니다.");
				System.out.println();
			}
			
		}

	}



	private static int selectNum() {
		System.out.print("번호를 선택해주세요 : ");
		Scanner scan = new Scanner(System.in);
		int num = Integer.parseInt(scan.nextLine());
		return num;
	}

}
