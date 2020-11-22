package com.project.center.faciltiy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.project.center.user.User;

import data.Path;

public class SeminarManage {

	public static void main(String[] args) {

		// 세미나예약클래스에서 예약정보 읽어오기
		SeminarReservation s = new SeminarReservation();
		s.getSeminarReservation();
		
		
		Scanner scan = new Scanner(System.in);
		
		int year = 2020;
		int month = 11;

		while (true) {
			
			
			System.out.println("====================================================================");
			System.out.println("\t\t\t세미나실 대관");
			System.out.println("====================================================================");
			
			// 세미나실 조회(달력, 세미나실 빈시간수 조회)
			showSeminarRoom(year, month, s);
			System.out.println();

			//예약할 날짜를 입력한다. 
			System.out.printf("조회할 날짜를 입력하세요. : ");
			int date = Integer.parseInt(scan.nextLine());
			
			Calendar now = Calendar.getInstance();
			int nowDate = now.get(Calendar.DATE);
			//날짜유효성 (현재날짜 이전 또는 31초과 숫자 입력X)
			if (date < nowDate || date > 31) {
				System.out.println();
				System.out.println("잘못된 날짜입니다.");
				pause();
				continue;
			}
			
			//해당 날짜의 세미나실 예약현황 전체를 출력
			System.out.println();
			System.out.println("=================================================");
			System.out.printf("\t%d-%2d-%02d 세미나실 대관현황\n", year, month, date);
			System.out.println("=================================================");
			System.out.println();
			showSeminarReservation(s, date); 
			
			System.out.println("번호를 선택하세요.");
			System.out.println();
			System.out.printf("1. %d-%2d-%02d 예약\n", year, month, date);
			System.out.println("2. 다른날짜 선택");
			scan = new Scanner(System.in);
			int sel = Integer.parseInt(scan.nextLine());
			
			if(sel == 1) {
				
				//해당 날짜에서 예약가능한 세미나실 목록만 출력
				SeminarReservation(s, date);
				pause();
			
			} else {	

				continue;
				
			}
			
			
//			System.out.println("예약가능한 세미나실을 확인하려면 엔터를 입력하세요.");
//			scan = new Scanner(System.in);
//			scan.nextLine();
			
			
		}
		
		
	} // main
	
	

	
	

	//입력받은 날짜의 세미나실 예약현황 출력 메소드
	private static void showSeminarReservation(SeminarReservation s, int date) {

		int index = 1;
	
		System.out.println("[No][세미나실] [시간] [정원] [예약자] [상태]");
		for (int i=0; i<s.list.size(); i++) {

			if (date == s.list.get(i).getDateI()) {

				// 해당 날짜의 세미나실 시간표 출력
				System.out.printf("%3d   [%s호]  [%s] %3s %6s   %s\n"
															,index
															,s.list.get(i).getNumber()
															,s.list.get(i).getTime()
															,s.list.get(i).getCapacity()
															,s.list.get(i).getUser().equals("Null")? "" : s.list.get(i).getUser()
															,s.list.get(i).getState());
				index++;
				
			}
			
		}
		System.out.println("=================================================");
		System.out.println();
		


	}
	

	
	
	// 예약가능한 세미나실 목록확인, 예약하는 메소드
	private static void SeminarReservation(SeminarReservation s, int date) {
		
		
		System.out.println("=================================================");
		System.out.println("\t대관가능한 세미나실 목록");
		System.out.println("=================================================");
		System.out.println();
		System.out.println("[No][세미나실] [시간] [정원] [예약자] [상태]");
		
		int index = 1;
		for (int i=0; i < s.list.size(); i++) {

			if (date == s.list.get(i).getDateI()) {
				
				//예약가능한 세미나실 출력
				if(s.list.get(i).getState().equals("예약가능")) {

					// 해당 날짜의 세미나실 시간표 출력
					System.out.printf("%3d   [%s호]  [%s] %3s %6s   %s\n"
																,index
																,s.list.get(i).getNumber()
																,s.list.get(i).getTime()
																,s.list.get(i).getCapacity()
																,s.list.get(i).getUser().equals("Null")? "" : s.list.get(i).getUser()
																,s.list.get(i).getState());
					index++;
				}	
			}
		}
		System.out.println("=================================================");
		
		//세미나실 예약
		System.out.println();
		System.out.print("예약할 세미나실 번호 입력 :");
		int num = selectNum();
		
		System.out.println();
		System.out.print("회원코드 입력 :");
		Scanner scan = new Scanner(System.in);
		String code = scan.nextLine();
		System.out.println("지불방식 선택 1.카드결제 2.현금결제 ");
		
		scan = new Scanner(System.in);
		String pay = scan.nextLine();
		
		
		index = 0;
		//int indexTemp = 0;
		for (int i=0; i < s.list.size(); i++) {
			if (date == s.list.get(i).getDateI()) {
				if(s.list.get(i).getState().equals("예약가능")) {
					
					index ++;
					
					//선택된 세미나실에 입력된 예약자 회원코드 s.list에 저장
					if(num == index) {
						s.list.get(i).setUser(code);
						s.list.get(i).setPay(pay);
						//indexTemp = i;
					}
					

				}
				
			}
		}
		//세미나실예약.txt update
		WriteSeminarReservatrion(s);
		
		System.out.println();
		System.out.println();
		
		
	}
	
	
	
	
	//세미나실예약.txt update 메소드
	private static void WriteSeminarReservatrion(SeminarReservation s) {
		
		try {
			BufferedWriter writer
				= new BufferedWriter(new FileWriter(Path.SEMINARRESERVATION)); 
			
			
			//예약정보 수정된 s.list 덮어쓰기
			for(int i=0; i<s.list.size(); i++) {
				
				writer.write(String.format("%s,%s,%s,%s,%s,%s,%s\r\n"
						, s.list.get(i).getNumber()
						, s.list.get(i).getUser()
						, s.list.get(i).getDate()
						, s.list.get(i).getTime()
						, s.list.get(i).getCapacity()
						, s.list.get(i).getPrice()
						, s.list.get(i).getPay()));
					
			}
			
			writer.close();
			
			System.out.println();
			System.out.println("예약이 완료되었습니다.");
		
			
			
		} catch (IOException e) {
			System.out.println("WriteSeminarReservation");
			e.printStackTrace();
		}

	}



	// 세미나실 조회 (달력,빈시간수 출력) 메소드
	private static void showSeminarRoom(int year, int month, SeminarReservation s) {

		// 캘린더 생성
		createCalendar(year, month, s);

	}

	// 달력출력 메소드
	private static void createCalendar(int year, int month, SeminarReservation s) {

		int lastDay = 0; // 마지막일
		int day_of_week = 0; // 요일

		Calendar c1 = Calendar.getInstance();
		c1.set(year, month - 1, 1); // 2020-10-01

		lastDay = c1.getActualMaximum(Calendar.DATE); // getLastDay 메서드 동일
		day_of_week = c1.get(Calendar.DAY_OF_WEEK) - 1; // getDayOfWeek 메서드 동일

		// 출력하기
		System.out.println();
		System.out.println("--------------------------------------------------------------------");
		System.out.printf("\t\t           %d년 %d월\n", year, month);
		System.out.println("--------------------------------------------------------------------");
		System.out.println("   [일]      [월]      [화]      [수]      [목]      [금]      [토]");

		// 1일을 요일 위치와 맞추기 위해 탭추가
		for (int i = 1; i <= day_of_week*10; i++) {
			System.out.print(" ");
		}

		// 날짜 출력
		for (int i = 1; i <= lastDay; i++) {

			// 세미나실 예약가능 수 계산
			int count = countSeminarReservation(s, year, month, i);

			System.out.printf("%2d(%02d/40) ", i, count);

			// 현재 출력하는 날짜(i)가 토요일인지?
			// if(i % 7 ==3) {
			if ((i + day_of_week) % 7 == 0) {
				System.out.println();
			}
		}
		System.out.println();
		System.out.println("--------------------------------------------------------------------");
		System.out.println("(예약가능수/전체)");
	}

	
	// 예약가능 세미나실 개수 계산하는 메소드
	private static int countSeminarReservation(SeminarReservation s, int year, int month, int date) {
		
		int count = 0;

		Calendar now = Calendar.getInstance();
		long nowTick = now.getTimeInMillis();
		
		Calendar c = Calendar.getInstance();
		c.set(year, month-1, date);
		long cTick = c.getTimeInMillis();
		
		//현재시간 이전 예약가능 수 : 0 으로 셋팅
		if(nowTick > cTick) {
			return 0;
		
		//예약가능한 세미나실 개수 계산
		} else {
			
			for (int i = 0; i < s.list.size(); i++) {
				
				if (date == s.list.get(i).getDateI()) {
					
					if (s.list.get(i).getState().equals("예약가능")) {
						
						count++;
					}
				}
			}
			return count;
		}

	}

	// pause 메서드
	private static void pause() {

		Scanner scan = new Scanner(System.in);
		System.out.println("계속하려면 엔터를 입력하세요.");
		scan.nextLine();

	}

	// 번호를 입력 받는 메서드
	private static int selectNum() {
		// 사용자에게 번호를 입력받는다.
		Scanner scan = new Scanner(System.in);
//		System.out.println("번호를 선택하세요 : ");
		return Integer.parseInt(scan.nextLine());
	}

} // SeminarManage
