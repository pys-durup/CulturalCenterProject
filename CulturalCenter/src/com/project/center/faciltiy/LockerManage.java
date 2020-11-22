package com.project.center.faciltiy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;

import com.project.center.user.User;
import com.project.center.user.UserManage;

import data.Path;

public class LockerManage {
	
	/**
	 * @author Daeun
	 * 		관리자모드 - 사물함관리
	 */
	
	//락커 사용 현황 정보를 담는 l ArrayList
	private static ArrayList<Locker> l = new ArrayList<Locker>();
	//회원 정보를 담는 u ArrayList
	private static ArrayList<User> u = new ArrayList<User>();
	
	
	//락커 사용 현황 정보를 담는 l ArrayList -temp
	private static ArrayList<Locker> lTemp = new ArrayList<Locker>();
	//회원 정보를 담는 u ArrayList -temp
	private static ArrayList<User> uTemp = new ArrayList<User>();
		
	public void lockerManageMain () {

		lockerTemp();
		while(true)	{
			
			showMain(); //회원관리 메인창 출력
			
			// 사용자가 입력한 숫자를 받아온다
			int Num = UserManage.selectNum(); 
			
			if(Num == 1) {	//1. 사물함 사용 현황
				lockerList();
				UserManage.pause();
				//break;
			} else if(Num == 2) { 	//2. 사물함 배정
				getLocker();
				UserManage.pause();
				//break;
			} else if(Num == 3) { 	//3. 회원별 사물함 정보보기
				myLockerInfo();
				UserManage.pause();
				//break;
			} else {
				break;
			}
			
		}
		
		
	}//lockerManageMain
	

	//회원별 사물함 사용정보를 읽는 메서드
	private static void myLockerInfo() {
		
		try {
			boolean check = false;
			String lNum = null, uCode = null, sDate = null, eDate = null, uName = null;

			Scanner scan = new Scanner(System.in);
			
			
			System.out.println("=======================================================");
			System.out.println("\t     회원별 사물함 정보");
			System.out.println("=======================================================");
			System.out.print("\t회원번호를 입력해주세요 : ");
			int userCode = Integer.parseInt(scan.nextLine());	//숫자비교 하기위해 int로 형변환
			
			if (userCode > uTemp.size() || userCode < 0) {		//사물함 끝번호가 넘어가는 숫자 입력 불가능
				System.out.println("\n\t틀린번호입니다. 다시 시도해주세요.");
				
			} else {
				String sUserCode = String.format("%d", userCode); //다시 String으로 형변환

				for (Locker locker : lTemp) {
					for (User user : uTemp) {
						if (locker.getUserCode().equals(sUserCode)) {
							check = true;
							lNum = locker.getLockerNum();
							uCode = locker.getUserCode();
							sDate = locker.getStartDate();
							eDate = locker.getEndDate();	//해당회원이 사물함을 이용한다면 각 변수에 회원정보 저장
							if (locker.getUserCode().equals(user.getCode())) {
							uName = user.getName();
							}
						}
					}
				}
				if (check == true) {
					System.out.println("=======================================================");
					System.out.println("\t1.사물함 번호 : " + lNum);
					System.out.println("\t2.회원 번호 :  " + uCode);
					System.out.println("\t3.회원 이름 :  " + uName);
					System.out.printf("\t4.이용 기간 : %s ~ %s\n", sDate, eDate);
					System.out.println("=======================================================");
				} else {
					System.out.println("\n\t해당 회원이 현재 사물함을 이용하지 않습니다.");
				}
			}

		} catch (Exception e) {
			System.out.println("LockerManage.myLockerInfo()");
			e.printStackTrace();
		}
		
	}

	

	//사물함 정보 읽는 메서드 
	private static void lockerTemp() {
		
		try {
			
			BufferedReader lockerReader = new BufferedReader(new FileReader(Path.LOCKER));
			BufferedReader userReader = new BufferedReader(new FileReader(Path.USERLIST));
			
			String line = null;

			while ((line = lockerReader.readLine()) != null) {

				String[] ltemp = line.split(","); // 락커정보 임시 배열

				lTemp.add(new Locker(ltemp[0] // 락커번호
						, ltemp[1] // 회원번호
						, ltemp[2] // 시작일
						, ltemp[3])); // 끝일
			}
			
			while ((line = userReader.readLine()) != null) {
				
				String[] utemp = line.split(","); // 회원정보 임시 배열
				
				uTemp.add(new User(utemp[0] // 회원번호
							, utemp[1]		// 회원이름
							, utemp[2]		// 회원id (<-필요없는 정보지만 생성자 뺏김ㅠ)
							, utemp[3])); 	// 회원pw (<-필요없는 정보지만 생성자 뺏김ㅠ)
				
			}
	
			lockerReader.close();
			userReader.close();

		} catch (Exception e) {
			System.out.println("LockerManage.lockerTemp()");
			e.printStackTrace();
		}
		
	}

	
	

	//사물함 배정 메서드
	private static void getLocker() {
		
		Scanner scan = new Scanner(System.in);

		System.out.println("[사물함 배정]");
		
		try {
			
			//startDate를 현재날짜 기준으로 c를 다음달 1일로 셋팅
			Calendar c = Calendar.getInstance();
			c.set(Calendar.MONTH, c.get(Calendar.MONTH)+1);
			c.set(Calendar.DATE, 1);
			String startDate = String.format("%tF", c);
			
			//이용기간이 무조건 한달이므로 endDate는 이용 시작일 이후 1달로 지정 
			c.set(Calendar.MONTH, c.get(Calendar.MONTH)+1);
			String endDate = String.format("%tF", c);
			
			System.out.println("==============================================================");
			System.out.println("\t     사물함 배정");
			System.out.println("==============================================================");
			System.out.print("\t배정을 원하시는 사물함 번호를 입력하세요. : ");
			int lockerNum = Integer.parseInt(scan.nextLine());	//숫자비교 하기위해 int로 형변환
			
			if (lockerNum > lTemp.size() || lockerNum < 0) {		//사물함 끝번호가 넘어가는 숫자 입력 불가능
				System.out.println("틀린번호입니다. 다시 시도해주세요.");
				
			} else {
				
				String slockerNum = String.format("%d", lockerNum); //다시 String으로 형변환
				
				for (Locker locker : lTemp) {
					//System.out.println(locker.getLockerNum());
					if (locker.getLockerNum().equals(slockerNum)) {

//						System.out.printf("사물함번호:%s, 회원번호:%s, 시작:%s, 끝:%s\n"
//								, locker.getLockerNum()
//								, locker.getUserCode()
//								, locker.getStartDate()
//								, locker.getEndDate());

						System.out.print("\t회원 번호를 입력하세요. : ");
						int userCode = Integer.parseInt(scan.nextLine());
						System.out.println("==============================================================");
						
						if (userCode > uTemp.size() || userCode < 0) {
							System.out.println("올바르지 않은 회원번호입니다.");
							
						} else {
							String sUserCode = String.format("%d", userCode);
							locker.setLockerNum(slockerNum);	//원하는 사물함 번호
							locker.setUserCode(sUserCode);		//사물함 배정받는 회원 번호
							locker.setStartDate(startDate);		//다음달부터 사용가능 -> 다음달 1일
							locker.setEndDate(endDate);			//이용기간 한달 -> 다다음달 1일
							
							System.out.println("\n\t사물함 배정이 완료되었습니다.");
							System.out.println("\t*사물함을 배정 받으신 회원님은 다음달 1일부터 사물함 이용이 가능합니다.*\n");
							System.out.println("==============================================================");
							break;
						}
					}
				}
				//사물함번호, 회원번호, 이용 시작하는 날짜, 이용 끝나는 날짜
				String lockerData = "";
				for (Locker locker : lTemp) {
					lockerData += locker.getLockerNum() + ","
							    + locker.getUserCode() + ","
							    + locker.getStartDate() + ","
							    + locker.getEndDate() + "\n";
				}
				
				BufferedWriter writer = new BufferedWriter(new FileWriter(Path.LOCKER)); //Append
				writer.write(lockerData); //수정된 사물함 데이터 쓰기
				writer.close();
			}
		} catch (Exception e) {
			System.out.println("LockerManage.getLocker()");
			e.printStackTrace();
		}

	}

	
	//사물함 메인화면 출력 메서드
	private static void showMain() {
		System.out.println("=========================================");
		System.out.println("\t  사물함 관리");
		System.out.println("=========================================");
		System.out.println("\t1. 사물함 사용 현황");
		System.out.println("\t2. 사물함 배정");
		System.out.println("\t3. 회원별 사물함 정보");
		System.out.println("\n\t이전으로 가고 싶으면 0번을 입력하세요.");
		System.out.println("=========================================");
		
	}


	//사물함 현재 현황 출력
	private static void lockerList() {
		
		try {
			BufferedReader lockerReader = new BufferedReader(new FileReader(Path.LOCKER));
			BufferedReader userReader = new BufferedReader(new FileReader(Path.USERLIST));
			
			System.out.println("==============================================================================");
			System.out.println("                                   사 물 함");
			System.out.println("==============================================================================");
			
			String line = null;

			while ((line = lockerReader.readLine(
					)) != null) {

				String[] ltemp = line.split(","); // 락커정보 임시 배열

				l.add(new Locker(ltemp[0] // 락커번호
						, ltemp[1] // 회원번호
						, ltemp[2] // 시작일
						, ltemp[3])); // 끝일
			}
			
			while ((line = userReader.readLine()) != null) {
				
				String[] utemp = line.split(","); // 회원정보 임시 배열
				
				u.add(new User(utemp[0] // 회원번호
							, utemp[1]	 // 회원이름
							, utemp[2]		// 회원id
							, utemp[3])); 	// 회원pw
			}
			
			
			//Locker present state printing
			Calendar c = Calendar.getInstance();
			String present = String.format("%tF", c); //현재 날짜
			
			String line1 = "";
			String line2 = "";

			int a = 0;

			for (int i = 0; i < l.size(); i++) {
				for (int j = 0; j < u.size(); j++) {
					
					if (l.get(i).getUserCode().equals(u.get(j).getCode())) {
						
						//l(사물함)의 사물함 번호 출력
						line1 += String.format("[ %3s]\t", l.get(i).getLockerNum());
						a++;
						
						//현재날짜가 startDate(이용시작날짜)와 endDate(이용끝날짜) 사이에 존재한다.
						//	-> 현재 본 회원이 사물함을 이용중이다. => 이용중인 사물함
						if (present.compareTo(l.get(i).getStartDate()) > 0
								&& present.compareTo(l.get(i).getEndDate()) < 0) {
							line2 += String.format("%4s\t", u.get(j).getName());
							
						} else {	// 	-> 이용중이지 않다면 빈 문자열 출력 => 이용 가능한 사물함	
							line2 += String.format("   \t");
						}

						//line1, line2에 저장해 놓은 정보 출력(10개 단위)-> 초기화 -> 다시 반복
						if (a % 10 == 0) {// 10으로 나누어 떨어지면 개행
							System.out.println(line1);
							System.out.println(line2);
							System.out.println();
							line1 = "";		//문자열 초기화
							line2 = "";		//문자열 초기화
						}
					}
				}
			}
			
			lockerReader.close();
			userReader.close();
			System.out.println("==============================================================================");

		} catch (Exception e) {
			System.out.println("LockerManage.rocker()");
			e.printStackTrace();
		}
		
	}


}
