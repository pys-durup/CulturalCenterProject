package com.project.center.faciltiy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import com.project.center.user.User;

import data.Path;

public class LockerManage {
	
	/**
	 * @author Daeun
	 * 		관리자모드 - 사물함관리
	 */
	
	//락커 사용 현황 정보를 담는 l ArrayList
	private static ArrayList<Locker> l = new ArrayList<Locker>();
	
	private static ArrayList<User> u = new ArrayList<User>();
		
		
	public static void main(String[] args) throws IOException {
		
		rockerList();
		
	}//main(임시)
	
	
	
	
	
	private static void rockerList() {
		
		try {

			BufferedReader lockerReader = new BufferedReader(new FileReader(Path.LOCKER));
			BufferedReader userReader = new BufferedReader(new FileReader(Path.USERLIST));
			
			System.out.println("==============================================================================");
			System.out.println("                                   사 물 함");
			System.out.println("==============================================================================");
			
			String line = null;

			while ((line = lockerReader.readLine()) != null) {

				String[] ltemp = line.split(","); // 락커정보 임시 배열

				l.add(new Locker(ltemp[0] // 락커번호
						, ltemp[1] // 회원번호
						, ltemp[2] // 시작일
						, ltemp[3])); // 끝일
			}
			
			while ((line = userReader.readLine()) != null) {
				
				String[] utemp = line.split(","); // 회원정보 임시 배열
				
				u.add(new User(utemp[0] // 회원번호
							, utemp[1])); // 회원이름
				
			}
			
			
			//Locker present state printing
			Calendar c = Calendar.getInstance();
			String present = String.format("%tF", c); //현재 날짜
			
			String line1 = "";
			String line2 = "";

			int b = 0;

			for (int i = 0; i < l.size(); i++) {

				for (int j = 0; j < u.size(); j++) {

					if (l.get(i).getUserCode().equals(u.get(j).getCode())) {
						//l(사물함)의 사물함 번호 출력
						line1 += String.format("[ %3s]\t", l.get(i).getLockerNum());
						b++;
						//현재날짜가 startDate(이용시작날짜)와 endDate(이용끝날짜) 사이에 존재한다.
						//	-> 현재 본 회원이 사물함을 이용중이다. => 이용중인 사물함
						if (present.compareTo(l.get(i).getStartDate()) > 0
								&& present.compareTo(l.get(i).getEndDate()) < 0) {
							line2 += String.format("%4s\t", u.get(j).getName());
						// 	-> 이용중이지 않다면 빈 문자열 출력 => 이용 가능한 사물함	
						} else {
							line2 += String.format("   \t");
						}

						//line1, line2에 저장해 놓은 정보 출력(10개 단위)-> 초기화 -> 다시 반복
						if (b % 10 == 0) {// 10으로 나누어 떨어지면 개행
							System.out.println(line1);
							System.out.println();
							System.out.println(line2);
							System.out.println("\n");
							line1 = "";		//문자열 초기화
							line2 = "";		//문자열 초기화
						}
					}
				}
			}
			
			lockerReader.close();
			userReader.close();

		} catch (Exception e) {
			System.out.println("LockerManage.rocker()");
			e.printStackTrace();
		}
		
	}//rocker() 연습


}
