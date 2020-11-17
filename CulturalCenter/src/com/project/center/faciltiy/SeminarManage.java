package com.project.center.faciltiy;

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

public class SeminarManage {

	public static void main(String[] args) {

		// 세미나예약클래스에서 예약정보 읽어오기
		SeminarReservation s = new SeminarReservation();
		s.getSeminarReservation();
		
		
		Scanner scan = new Scanner(System.in);
		
		int year = 2020;
		int month = 11;

		while (true) {

			// 세미나실 조회(달력,세미나실 빈시간수 조회)
			showSeminarRoom(year, month, s);
			System.out.println();

			// 예약할 날짜를 입력한다. (이번달 캘린더의 날짜 1~31일)
			System.out.printf("예약 날짜를 입력하세요 : ");
			int date = Integer.parseInt(scan.nextLine());
			checkDate(date);

			System.out.printf("%d-%d-%s의 세미나실 예약현황\n", year, month, date);
			System.out.println();

//			int count = countSeminarReservation(s, num);
//			System.out.printf("예약가능 시수 : %d/40", count);
//			System.out.println();

			showSeminarReservation(s, date);
			
			pause();

			System.out.println("예약가능한 세미나실 목록");
			SeminarReservation(s, date);
			
			
			pause();

//			Calendar c = Calendar.getInstance();
//			c.set(2020, 10, num); // 2020-11-num
//			
//			System.out.printf("%tF\n",c);
//			System.out.println();
//			

//			for(int i=0; i<s.list.size(); i++) {
//				
//				System.out.printf("%tF",s.list.get(i).getDateC());
//				
//				if(c.compareTo(s.list.get(i).getDateC()) == 0) {
//					//해당 날짜의 세미나실 시간표 출력
//					System.out.printf("%tF, %tF, %s",c ,s.list.get(i).getDateC(), s.list.get(i).getTime());
//			
//					
//				} else {
//					System.out.println("해당날짜없음");
//					//pause();
//				}
//				
//			}
			
			
			
		}
		
		
	} // main
	
	

	private static void checkDate(int date) {

		if (date < 1 || date > 31) {
			System.out.println("잘못된 날짜입니다.");
		}

	}
	
	

	//입력받은 날짜의 세미나실 예약현황 출력 메소드
	private static void showSeminarReservation(SeminarReservation s, int date) {

		int index = 1;
	
		System.out.println("[No][세미나실] [시간] [정원][예약자][상태]");
		for (int i=0; i<s.list.size(); i++) {

			if (date == s.list.get(i).getDateI()) {

				// 해당 날짜의 세미나실 시간표 출력
				System.out.printf("%3d   [%s호]  [%s] %3s %5s %s\n"
																,index
																,s.list.get(i).getNumber()
																,s.list.get(i).getTime()
																,s.list.get(i).getCapacity()
																,s.list.get(i).getUser()
																,s.list.get(i).getState());
				index++;
				
			}
			
		}
		System.out.println();
		System.out.println();


	}

	// 예약가능한 세미나실 목록, 예약
	private static void SeminarReservation(SeminarReservation s, int date) {
		
		int index = 1;
		System.out.println("[No][세미나실] [시간] [정원][예약자][상태]");
		for (int i=0; i < s.list.size(); i++) {

			if (date == s.list.get(i).getDateI()) {
				
				//예약가능한 세미나실 출력
				if(s.list.get(i).getState().equals("예약가능")) {

					// 해당 날짜의 세미나실 시간표 출력
					System.out.printf("%3d   [%s호]  [%s] %3s %5s %s\n"
																,index
																,s.list.get(i).getNumber()
																,s.list.get(i).getTime()
																,s.list.get(i).getCapacity()
																,s.list.get(i).getUser()
																,s.list.get(i).getState());
					index++;
				}
				
			}

		}
		
		
		//세미나실 예약
		System.out.print("예약할 세미나실 번호 입력 :");
		int num = selectNum();
		
		System.out.print("회원코드 입력 :");
		Scanner scan = new Scanner(System.in);
		String code = scan.nextLine();
	
		int indexTemp = 0;
		for (int i=0; i < s.list.size(); i++) {

			if (date == s.list.get(i).getDateI()) {
				//예약가능한 세미나실 출력
				if(s.list.get(i).getState().equals("예약가능")) {
					
					if(i == num) {
						s.list.get(i).setUser(code);
						indexTemp = i;
					}
					

				}
				
			}
		}
		
		WriteSeminarReservatrion(s, indexTemp, code);
		
		System.out.println();
		System.out.println();
		
		
	}
	
	
	
	private static void WriteSeminarReservatrion(SeminarReservation s, int index, String code) {
		

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
		System.out.println("====================================================================");
		System.out.printf("\t\t           %d년 %d월\n", year, month);
		System.out.println("====================================================================");
		System.out.println("   [일]      [월]      [화]      [수]      [목]      [금]      [토]");

		// 1일을 요일 위치와 맞추기 위해 탭추가
		for (int i = 1; i <= day_of_week; i++) {
			System.out.print("\t ");
		}

		// 날짜 출력
		for (int i = 1; i <= lastDay; i++) {

			// 세미나실 예약가능 수 계산
			int count = countSeminarReservation(s, i);

			System.out.printf("%2d(%02d/40) ", i, count);

			// 현재 출력하는 날짜(i)가 토요일인지?
			// if(i % 7 ==3) {
			if ((i + day_of_week) % 7 == 0) {
				System.out.println();
			}
		}
		System.out.println();
		System.out.println("====================================================================");
	}

	// 세미나실 비어있는 시간 시수 계산하는 메소드
	private static int countSeminarReservation(SeminarReservation s, int num) {

		int count = 0;

		for (int i = 0; i < s.list.size(); i++) {

			if (num == s.list.get(i).getDateI()) {

				if (s.list.get(i).getState().equals("예약가능")) {

					count++;
				}
			}
		}
		return count;
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
