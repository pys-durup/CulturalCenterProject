package com.project.center.faciltiy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.project.center.user.User;

import data.Path;

public class FacilityReservation {

	public static void findReservationList(User user) {
		
		Scanner scan = new Scanner(System.in);
		
		try {

			boolean loop = true;
			
			while(loop) {
				
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
					break;
				case "2":
					reservationBoard(gymMyReservationList, 0);
					break;
				default:
					loop = false;
					break;
				}
				
				readerGym.close();
				readerSeminar.close();
			}
			
			
		} catch (Exception e) {
			System.out.println("FacilityReservation.main()");
			e.printStackTrace();
		}
		
	}
	
	
	//예약 내역 띄워주는 메서드
	private static void reservationBoard(ArrayList<String[]> resList, int index) {
		
		String room = "";
		
		if (index == 1) {
			System.out.println("[세미나실 예약 내역]");
			System.out.println();
			System.out.println("\t  [날짜]     [장소]     [시간]       [가격]");
			room = "호";
		} else {
			System.out.println("[체육시설 예약 내역]");
			System.out.println();
			System.out.println("\t  [날짜]     [장소]     [시간]       [가격]");
		}
		
		Scanner scan = new Scanner(System.in);
		
		for (int i=0; i<resList.size(); i++) {
			
			System.out.printf("%d.\t %s월 %s일    %s%s    %s ~ %s  %,d 원\n"
							,i + 1
							,resList.get(i)[2].substring(5, 7)
							,resList.get(i)[2].substring(8)
							,resList.get(i)[0]
							,room
							,resList.get(i)[3]
							,String.format("%d:00"
									,Integer.parseInt(resList.get(i)[3].substring(0, 2)) + 2)
							,Integer.parseInt(resList.get(i)[4 + index])
							);
			
		}
		
		System.out.println("----------------------------------------------------");
		System.out.println("1. 환불 신청하기\t2.뒤로 가기");
		System.out.print("메뉴 입력 :");
		String inputMenu = scan.nextLine();
		System.out.println();
		
		Calendar today = Calendar.getInstance();
		
		if (inputMenu.equals("1")) {

			System.out.print("환불을 신청할 항목의 번호를 입력해주세요 :");
			String input = scan.nextLine();
			System.out.println();
			boolean checkNumber = false;
			
			for (int i=0; i<resList.size(); i++) {
				
				if (input.equals(i + 1 + "")
						&& Integer.parseInt(resList.get(i)[2].replaceAll("-", "")) - 1 
						> Integer.parseInt(String.format("%tF", today).replaceAll("-", ""))
						) {
					refundReservation(resList.get(i), index);
					checkNumber = true;
				}
				
			}
			
			if(!checkNumber) {
				System.out.println();
				System.out.println("해당 항목은 존재하지 않거나, 환불이 불가능합니다.");
				System.out.println("환불은 예약날짜 하루 전에는 불가능합니다.");
				System.out.println();
				System.out.println("엔터를 누르면 조회 메뉴로 돌아갑니다.");
				scan.nextLine();
			}
			
		} else if (inputMenu.equals("2")) {
		} else {
			System.out.println("잘못된 입력입니다! 엔터를 누르면 조회 메뉴로 돌아갑니다.");
			scan.nextLine();

		}
		
	}
	
	private static void refundReservation(String[] reservation, int index) {
		
		Scanner scan = new Scanner(System.in);
		
		String room = "";
		
		if (index == 1) {
			System.out.println("[선택한 세미나실 정보]");
			room = "호";
		} else {
			System.out.println("[선택한 체육시설 정보]");
		}
		
		System.out.printf("날짜 : %s월 %s일\n"
						+ "장소 : %s%s\n"
						+ "시간 : %s ~ %s\n"
						+ "가격 : %,d 원\n"
						,reservation[2].substring(5, 7)
						,reservation[2].substring(8)
						,reservation[0]
						,room
						,reservation[3]
						,String.format("%d:00"
								,Integer.parseInt(reservation[3].substring(0, 2)) + 2)
						,Integer.parseInt(reservation[4 + index])
						);
		System.out.println();
		
		
		String inputRefund = "";
		String refundWay = "";
		
		if (reservation[5 + index].equals("1")) {
			System.out.println("선택하신 시설을 예약하실 때 카드로 결제하셨습니다.");
			System.out.println("환불을 진행하시겠습니까?");
			System.out.println();
			System.out.println("1. 예 2. 메인 메뉴로 돌아가기");
			System.out.print("메뉴 입력 :");
			inputRefund = scan.nextLine();
			refundWay = "카드";
			
		} else if (reservation[5 + index].equals("2")) {
			System.out.println("선택하신 시설을 예약하실 때 휴대폰으로 결제하셨습니다.");
			System.out.println("환불을 진행하시겠습니까?");
			System.out.println();
			System.out.println("1. 예 2. 메인 메뉴로 돌아가기");
			System.out.print("메뉴 입력 :");
			inputRefund = scan.nextLine();
			refundWay = "휴대폰";
			
		}
		
		System.out.println();
		
		switch(inputRefund) {
			case "1" :
			
				if (index == 1) {
					System.out.println("[세미나실 환불 정보]");
					room = "호";
				} else {
					System.out.println("[체육시설 환불 정보]");
				}
				
				System.out.printf("날짜 : %s월 %s일\n"
								+ "장소 : %s%s\n"
								+ "시간 : %s ~ %s\n"
								+ "가격 : %,d 원\n"
								+ "결제 수단 : %s\n"
								,reservation[2].substring(5, 7)
								,reservation[2].substring(8)
								,reservation[0]
								,room
								,reservation[3]
								,String.format("%d:00"
										,Integer.parseInt(reservation[3].substring(0, 2)) + 2)
								,Integer.parseInt(reservation[4 + index])
								,refundWay
								);
				System.out.println();
				System.out.println("환불 진행중.. (약 5초 ~ 1분 소요)");
				updateReservationList(reservation, index);
				System.out.println();
				System.out.println("환불이 완료되었습니다.");
				System.out.println();
				System.out.println("엔터를 입력하면 메인 메뉴로 돌아갑니다.");
				scan.nextLine();
			case "2" :
				System.out.println("메인 메뉴로 돌아갑니다.");
			default :
				System.out.println("잘못된 입력입니다. 메인 메뉴로 돌아갑니다.");
			
		}
		
	}


	private static void updateReservationList(String[] reservation, int index) {
		
		String path = "";
		
		if (index==1) {
			path = Path.SEMINARRESERVATION;
		} else {
			path = Path.GYMRESERVATION;
		}
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			String dummy = "";
			String line = null;

			
			
			while ((line = reader.readLine()) != null) {
				
				String[] list = line.split(",");
				
				if (reservation[0].equals(list[0])
					&& (reservation[1]).equals(list[1])
					&& (reservation[2]).equals(list[2])
					&& (reservation[3]).equals(list[3])
					) {
					dummy += "";
				} else {
					dummy += line + "\r\n";
				}
				
			}
			
			dummy = dummy.substring(0, dummy.length() - 2);
			
			reader.close();
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			
			writer.write(dummy);
			
			writer.close();

		} catch (Exception e) {
			System.out.println("FacilityReservation.updateReservationList()");
			e.printStackTrace();
		}
		
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


