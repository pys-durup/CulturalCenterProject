package com.project.center.faciltiy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.project.center.user.User;

import data.Path;

public class LockerManage {
	
	/**
	 * @author Daeun
	 * 		관리자모드 - 사물함관리
	 */
	
	//락커 사용 현황 정보를 담는 l ArrayList
	private static ArrayList<Locker> l = new ArrayList<Locker>();
	
	public static void main(String[] args) throws IOException {
		
		
		
		readLocker();
		printLocker();
		
	}//main(임시)
	
	
	public static void readLocker() throws IOException {

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
		
		System.out.print();
		
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
		
		
	}
}
