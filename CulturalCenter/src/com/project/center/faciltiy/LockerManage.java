package com.project.center.faciltiy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.project.center.user.User;

import data.Path;

public class LockerManage {

	//회원 정보 담는 ulist와 초기화
	private static ArrayList<Locker> l = new ArrayList<Locker>();

	
	
	public static void main(String[] args) throws IOException {
		
		//lockerPrint l = new lockerPrint();
		
		lockerPrint();
		printLocker();
		
	}//main(임시)
	
	
	public static void lockerPrint() throws IOException {

		System.out.println("============================================================================");
		System.out.println("                                   사 물 함");
		System.out.println("============================================================================");
		System.out.println("[]\t[월]\t[화]\t[수]\t[목]\t[금]\t[토]\t[목]\t[금]\t[토]");

		try {

			BufferedReader reader = new BufferedReader(new FileReader(Path.LOCKER));

			String line = null;

			while ((line = reader.readLine()) != null) {

				String[] temp = line.split(",");

				l.add(new Locker(temp[0]	//락커번호
								, temp[1]	//회원번호
								, temp[2]	//시작일
								, temp[3])); //끝
				
				//잘 읽어왔나 확인
//				System.out.printf("%s\t%s\t%s\t%s\r\n"
//									,temp[0]
//									,temp[1]
//									,temp[2]
//									,temp[3]);
			
			}
			
			
			System.out.println();

			reader.close();
			
		} catch (Exception e) {
			System.out.println("LockerManage.lockerPrint()");
			e.printStackTrace();
		}

	}//락커 읽어오기
	
	
	private static void printLocker() {
		
//		//1일을 요일 위치와 맞추기 위해 탭 추가
//		for (int i=0; i<10; i++) {
//			System.out.print("\t");
//		}
//		
//		//날짜 출력
//		for (int i=1; i<10; i++) {
//			System.out.printf("%4d\t", i);
//			
//			//현재 출력하는 날짜(i)가 토요일인지?
//			//if (i % 7 == 3) {
//			if ((i + day_of_week) % 7 == 0) {
//				System.out.println();
//			}
//			
//		}
		
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				if (j!=9) {
					System.out.printf("%5d\t", j+1);
				} else {
					System.out.println("\n");
					
				}
			}
		}
		
		
	}
}
