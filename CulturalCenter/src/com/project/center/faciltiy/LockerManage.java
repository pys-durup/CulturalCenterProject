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
		
	public static void main(String[] args) throws IOException {

		while(true)	{
			
			showMain(); //회원관리 메인창 출력
			
			// 사용자가 입력한 숫자를 받아온다
			int Num = UserManage.selectNum(); 
			
			if(Num == 1) {
			
				lockerList();
				UserManage.pause();
				//break;
				
			} else if(Num == 2) { 

				getLocker();
				//UserManage.pause();
				
			} else if(Num == 3) {
				//break;
				lockerInfo();
				UserManage.pause();
				
			} else {
				UserManage.pause();
				break;
			}
			
			
			lockerTemp();
		}
		
		
	}//main(임시)
	


	private static void lockerInfo() {

		try {
			
			Scanner scan = new Scanner(System.in);
			
			boolean flag = true;

			lockerTemp();
			
			System.out.print("배정을 원하는 사물함 번호를 입력하세요. : ");
			String lockerNum = scan.nextLine();

			while (flag) {

				for (Locker l : lTemp) {
					
					if (l.getLockerNum().equals(lockerNum)) {


						System.out.printf("[사물함번호]%s\t[회원번호]%s\t[시작날짜]%s\t[종료날짜]%s\n"
											,l.getLockerNum()
											,l.getUserCode()
											,l.getStartDate()
											,l.getEndDate());

						System.out.println(
								"-------------------------------------------------------------------------------");


					} else {
						System.out.println("틀린 번호");
						break;
					}

					UserManage.pause();
				}
			}

		} catch (Exception e) {
			System.out.println("LockerManage.lockerInfo()");
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
							, utemp[1])); // 회원이름
				
			}
			
			lockerReader.close();
			userReader.close();

		} catch (Exception e) {
			System.out.println("LockerManage.lockerTemp()");
			e.printStackTrace();
		}
		
	}

	
	
	
	
	
	//사물함 배정 (실험1)
	private static void setLocker() {

		try {

			boolean flag = true;
			Scanner scan = new Scanner(System.in);
			lockerTemp();	
			
			while (flag) {
				
				System.out.print("배정을 원하는 사물함 번호를 입력하세요. : ");
				String lockerNum = scan.nextLine();
				
				for (Locker l : lTemp) {
					if (l.getLockerNum().equals(lockerNum)) {
						setLockerSub(lockerNum);
						//updateUser(userCode);
						flag = false;
						break;
					} 
				}
			
		}
		
		
		} catch (Exception e) {
			System.out.println("UserManage.changeUserInfo()");
			e.printStackTrace();
		}
		
	}
	
	
	
	
	//사물함 배정 (하위)
	private static void setLockerSub(String lockerNum) {
		
		try {
		
			Scanner scan = new Scanner(System.in);
			
			boolean flag = true;

			String changedInfo = "";
			
			lockerTemp();

			while (flag) {

				for (Locker l : lTemp) {
					
					if (l.getLockerNum().equals(lockerNum)) {


						System.out.printf("[사물함번호]%s\t[회원번호]%s\t[시작날짜]%s\t[종료날짜]%s\n"
											,l.getLockerNum()
											,l.getUserCode()
											,l.getStartDate()
											,l.getEndDate());

						System.out.println(
								"-------------------------------------------------------------------------------");


						//startDate를 현재날짜 기준으로 c를 다음달 1일로 셋팅
						Calendar c = Calendar.getInstance();
						c.set(Calendar.MONTH, c.get(Calendar.MONTH)+1);
						c.set(Calendar.DATE, 1);
						String startDate = String.format("%tF", c);
						
						//이용기간이 무조건 한달이므로 endDate는 이용 시작일 이후 1달로 지정 
						c.set(Calendar.MONTH, c.get(Calendar.MONTH)+1);
						String endDate = String.format("%tF", c);
						
						
						System.out.print("회원번호를 입력하세요. : ");
						String userCode = scan.nextLine();

						l.setLockerNum(lockerNum);
						l.setUserCode(userCode);
						l.setStartDate(startDate);
						l.setEndDate(endDate);

					}
				}
				UserManage.pause();
			}

		} catch (Exception e) {
			System.out.println("UserManage.updateUser()");
			e.printStackTrace();
		}
		
		
	}
	
	
	
	

	//사물함 배정 메서드
	private static void getLocker() {
		
		Scanner scan = new Scanner(System.in);
		//lockerList();
		System.out.println("[사물함 배정]");
		
		
		try {

			//BufferedWriter writer = new BufferedWriter (new FileWriter(Path.LOCKER, true));
			
			//startDate를 현재날짜 기준으로 c를 다음달 1일로 셋팅
			Calendar c = Calendar.getInstance();
			c.set(Calendar.MONTH, c.get(Calendar.MONTH)+1);
			c.set(Calendar.DATE, 1);
			String startDate = String.format("%tF", c);
			
			//이용기간이 무조건 한달이므로 endDate는 이용 시작일 이후 1달로 지정 
			c.set(Calendar.MONTH, c.get(Calendar.MONTH)+1);
			String endDate = String.format("%tF", c);

			lockerTemp();

			System.out.println("lTemp size :" + lTemp.size());
			System.out.print("배정을 원하시는 사물함 번호를 입력하세요. : ");
			String lockerNum = scan.nextLine();

			if (lockerNum.compareTo(String.valueOf(lTemp.size())) < 0) {
				System.out.println("틀린번호");
				
			} else {
				System.out.println("맞는번호");
				for (Locker locker : lTemp) {
					System.out.println(locker.getLockerNum());
					if (locker.getLockerNum().equals(lockerNum)) {

						System.out.printf("사물함번호:%s, 회원번호:%s, 시작:%s, 끝:%s\n"
								, locker.getLockerNum()
								, locker.getUserCode()
								, locker.getStartDate()
								, locker.getEndDate());

						System.out.print("회원 번호를 입력하세요. : ");
						String userCode = scan.nextLine();

						locker.setLockerNum(lockerNum);
						locker.setUserCode(userCode);
						locker.setStartDate(startDate);
						locker.setEndDate(endDate);

						System.out.println(locker.getUserCode());
						System.out.println(locker.getLockerNum());
						UserManage.pause();
						break;
					}

				}
				// 45,144,2020-10-01,2020-11-01
				String lockerData = "";
				for (Locker locker : lTemp) {
					lockerData += locker.getLockerNum() + ","
							    + locker.getUserCode() + ","
							    + locker.getStartDate() + ","
							    + locker.getEndDate() + "\n";
				}
				
				BufferedWriter writer = new BufferedWriter(new FileWriter(Path.LOCKER));
				writer.write(lockerData);
				writer.close();
				System.out.println("데이터 수정 성공");

			}
			
		} catch (Exception e) {
			System.out.println("LockerManage.getLocker()");
			e.printStackTrace();
		}

	}

	//사물함 메인화면 출력 메서드
	private static void showMain() {
		
		System.out.println("\n\t  [사물함 관리]\n");
		System.out.println("\t1. 사물함 사용 현황");
		System.out.println("\t2. 사물함 배정");
		System.out.println("\t3. 내 사물함?");
		
	}


	//사물함 현재 현황 출력
	private static void lockerList() {
		
		try {

			BufferedReader lockerReader = new BufferedReader(new FileReader(Path.LOCKER));
			BufferedReader userReader = new BufferedReader(new FileReader(Path.USERLIST));
			
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
