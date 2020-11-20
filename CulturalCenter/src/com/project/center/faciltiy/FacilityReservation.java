package com.project.center.faciltiy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.center.user.User;

import data.Path;

public class FacilityReservation {

	public static void findReservationList(User user) {
		
		Scanner scan = new Scanner(System.in);
		
		try {

			BufferedReader readerSeminar = new BufferedReader(new FileReader(Path.SEMINARRESERVATION));
			BufferedReader readerGym = new BufferedReader(new FileReader(Path.GYMRESERVATION));
			
			ArrayList<String[]> seminarMyRervationList = getMyReservationList(readerSeminar, user);
			ArrayList<String[]> gymMyReservationList = getMyReservationList(readerGym, user);
			
			System.out.println();
			System.out.println("시설 예약 내역을 조회할 항목을 선택해주세요.");
			System.out.println("----------------------------------------------------");
			System.out.println("1. 세미나실");
			System.out.println("2. 체육시설");
			System.out.println("3. 메인 메뉴로 돌아가기");
			System.out.println("----------------------------------------------------");
			System.out.print("메뉴 입력 :");
			String input = scan.nextLine();
			System.out.println();
			
			switch(input) {
				case "1":
					reservationBoard(seminarMyRervationList, 1);
					System.out.println();
					System.out.println("엔터를 누르면 메인 메뉴로 돌아갑니다.");
					scan.nextLine();
					System.out.println();
					break;
				case "2":
					reservationBoard(gymMyReservationList, 0);
					System.out.println();
					System.out.println("엔터를 누르면 메인 메뉴로 돌아갑니다.");
					scan.nextLine();
					System.out.println();
					break;
				default:
					break;
			}
			
			readerGym.close();
			readerSeminar.close();
			
		} catch (Exception e) {
			System.out.println("FacilityReservation.main()");
			e.printStackTrace();
		}
		
	}
	
	
	//예약 내역 띄워주는 메서드
	private static void reservationBoard(ArrayList<String[]> resList, int index) {
		
		if (index == 1) {
			System.out.println("[세미나실 예약 내역]");
			System.out.println();
			System.out.println("\t  [날짜]     [장소]     [시간]       [가격]");
		} else {
			System.out.println("[체육시설 예약 내역]");
			System.out.println();
			System.out.println("\t  [날짜]     [장소]     [시간]       [가격]");
		}
		
		
		//환불을 하려면 개별 예약 당 예약 번호가 필요할 것 같습니다.
//		String[] refundCursor = new String[resList.size()];
		
		for (int i=0; i<resList.size(); i++) {
			
			System.out.printf("%d.\t %s월 %s일    %s    %s ~ %s  %s 원\n"
							,i + 1
							,resList.get(i)[2].substring(5, 7)
							,resList.get(i)[2].substring(8)
							,resList.get(i)[0]
							,resList.get(i)[3]
							,String.format("%d:00"
									, Integer.parseInt(resList.get(i)[3].substring(0, 2)) + 2)
							,resList.get(i)[4 + index]);
			
		}
		System.out.println("----------------------------------------------------");
		
	}
	
	//로그인한 회원의 예약 리스트를 반환하는 메서드
	private static ArrayList<String[]> getMyReservationList(BufferedReader reader, User user) throws IOException {
		
		ArrayList<String[]> result = new ArrayList<String[]>();
		
		String line = null;
		
		while ((line = reader.readLine()) != null) {
			String[] list = line.split(",");
			if (user.getCode().equals(list[1])) {
				result.add(list);
			}
		}
		
		reader.close();

		return result;
	}
	
	
}























