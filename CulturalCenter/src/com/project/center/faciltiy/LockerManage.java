package com.project.center.faciltiy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
		
		readLocker();
		//printLocker();
		//rocker();
		
		
	}//main(임시)
	
	
	
	
	
	
	
	
	private static void rocker() {
		
		try {

			BufferedReader lockerReader = new BufferedReader(new FileReader(Path.LOCKTEST));
			BufferedReader userReader = new BufferedReader(new FileReader(Path.USERTEST));
			
			String line = null;
			String userCode = "";
			String lockerNum = "";

			int b = 0;

			while ((line = lockerReader.readLine()) != null) {

				String[] ltemp = line.split(","); // 락커정보 임시 배열

				l.add(new Locker(ltemp[0] // 락커번호
						, ltemp[1] // 회원번호
						, ltemp[2] // 시작일
						, ltemp[3])); // 끝일

				while ((line = userReader.readLine()) != null) {

					String[] utemp = line.split(","); // 회원정보 임시 배열

					u.add(new User(utemp[0] // 회원번호
							, utemp[1])); // 회원이름

					//System.out.println(ltemp[1]);
					//System.out.println(utemp[0]);

					if (ltemp[1].equals(utemp[0])) {

						System.out.printf("(%3s)", ltemp[0]);
						System.out.printf("%3s\t", utemp[1]);
						break;
					}

				}
				
				//String lock = ltemp[0];

				b++;

				if (b % 10 == 0) // 10으로 나누어 떨어지면 개행
				{
					System.out.println();
					System.out.println();
				}

			}
			
			

		} catch (Exception e) {
			System.out.println("LockerManage.rocker()");
			e.printStackTrace();
		}
		
	}//rocker() 연습








	public static void readLocker() throws IOException {

		System.out.println("============================================================================");
		System.out.println("                                   사 물 함");
		System.out.println("============================================================================");
		System.out.println("[]\t[월]\t[화]\t[수]\t[목]\t[금]\t[토]\t[목]\t[금]\t[토]");

		try {

			BufferedReader lockerReader = new BufferedReader(new FileReader(Path.LOCKTEST));
			BufferedReader userReader = new BufferedReader(new FileReader(Path.USERTEST));

			String line = null;

//			while ((line = reader.readLine()) != null) {
//
//				String[] temp = line.split(",");
//
//				l.add(new Locker(temp[0] // 락커번호
//						, temp[1] // 회원번호
//						, temp[2] // 시작일
//						, temp[3])); // 끝

				// 잘 읽어왔나 확인
//				System.out.printf("%s\t%s\t%s\t%s\r\n"
//									,temp[0]
//									,temp[1]
//									,temp[2]
//									,temp[3]);

//			int b = 0;
//			
//			for (int i = 1; i <= 100; i ++) {
//				
//				b++;
//				
//				if (b % 10 == 0) // 10으로 나누어 떨어지면 개행
//				{
//					System.out.println();
//					System.out.println();
//				}
//			}
			
			
			int b = 0;
			
				
					while ((line = lockerReader.readLine()) != null) {

						String[] temp = line.split(",");

						l.add(new Locker(temp[0] // 락커번호
								, temp[1] // 회원번호
								, temp[2] // 시작일
								, temp[3])); // 끝
						
						System.out.printf("(%3s)", temp[0]);
						System.out.printf("%3s\t", temp[1]);
						b++;

						if (b % 10 == 0) // 10으로 나누어 떨어지면 개행
						{
							System.out.println();
							System.out.println();
						}
					}
					
					
					System.out.println(l.get(0).getUserCode());
//					
//					//Arraylist 담은 객체 출력
//					for (int i=0; i<l.size(); i++) {
//						System.out.println(l.get(i).getUserCode());
//					}
			
				

//			}
			
			System.out.println();

			//reader.close();
			
		} catch (Exception e) {
			System.out.println("LockerManage.lockerPrint()");
			e.printStackTrace();
		}

	}//락커 읽어오기
	
	
	private static void printLocker() throws IOException {
		
		//1부터 100찍기 [사물함번호] 연습
		int j = 0;
		for (int i = 1; i <= 100; i ++) {

			System.out.printf("%3d\t", i);
			j++;
			
			if (j % 10 == 0) // 10으로 나누어 떨어지면 개행
			{
				System.out.println();
				System.out.println();
			}
		}

		
		readLocker();
		
		
		int b = 0;
		for (int i = 1; i <= 100; i ++) {
			
			System.out.printf("%3d\t", i);
			b++;
			
			if (b % 10 == 0) // 10으로 나누어 떨어지면 개행
			{
				System.out.println();
				System.out.println();
			}
		}
		
		for (int i = 1; i <= 100; i ++) {
			
			//System.out.printf("%3d\t", );
			b++;
			
			if (b % 10 == 0) // 10으로 나누어 떨어지면 개행
			{
				System.out.println();
				System.out.println();
			}
		}
		
	}
}
