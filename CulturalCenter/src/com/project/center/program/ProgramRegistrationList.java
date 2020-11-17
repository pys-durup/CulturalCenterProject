package com.project.center.program;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.project.center.user.User;

import data.Path;

/**
 * @author youngsu 
 * 수업등록현황을 관리하는 클래스
 * 
 */
public class ProgramRegistrationList {
	
	private ArrayList<ProgramPaymentInfo> paymentList;
	private ArrayList<ProgramState> psList;
	private ArrayList<Program> pList;
	private ArrayList<ProgramAttendance> paList;
	private User login;
	
	public ProgramRegistrationList() {
		this.login = new User("50001", "박영수", "1993-08-17","tteesstt", "tteesstt",  "1", "01077743635",  "1" , "주소");
	}
	
	public ProgramRegistrationList(User login) {
		// 테스트용 유저 객체
		this.login = new User("50001", "박영수", "1993-08-17","tteesstt", "tteesstt",  "1", "01077743635",  "1" , "주소");
		//this.login = login;
	}

	public void createProgramRegistorList() {
		
		while(true) {
			// 사용자가 선택한 번호
			int selectNum = userSelectNum();
			
			if(selectNum == 1) { // 1. 수업 현황
				System.out.println("진행중인 프로그램 코드 : " + myProgramState());
				pause();
			} else if (selectNum == 2) { // 2. 수업 이력
				myProgramHistory();
			} else if (selectNum == 3) { // 3. 수업 환불
				myProgramRefund();
			} else {
				System.out.println("createProgramRegistorList 올바르지 않은 입력");
				break;
			}
			
		}
	}

	/**
	 *  수업 현황
	 * 
	 */
	private String myProgramState() {
		this.paymentList = loadProgramPaymentData(Path.PROGRAMPAYMENT); // 프로그램결제.txt 데이터
		this.psList = loadProgramStateData(Path.PROGRAMPAYMENT); // 프로그램상태.txt 데이터
		this.pList = loadProgramData(Path.PROGRAMLIST);
		String haveProgramCode = "";
		
		// 결제 내역에 내가 구매한 프로그램이 있는지? 
		ArrayList<String> myPayment = havePaymentHistory();
		System.out.println(myPayment);
		
		if(myPayment.size() > 0) { // 결제내역 존재
			for(String code : myPayment) {
				for(ProgramState ps : this.psList) {
					if(code.equals(ps.getCode()) && ps.getState().equals("진행중")) {
						// 진행중인 프로그램이 있다면
						haveProgramCode = ps.getCode();
						System.out.println("진행중인 프로그램이 있다");
						return haveProgramCode;
					}
				}
			}
			System.out.println("결제한프로그램이 있는데 진행중이지 않을때");
			return haveProgramCode;
		} else {
			System.out.println("결제내역이 존재하지 않음");
			System.out.println("진행중인 프로그램이 없습니다");
			pause();
			return haveProgramCode;
		}
	}
	
	// 결제 내역에 내가 구매한 프로그램이 있는지? 
	// 만약 있으면 여러개가 있을수 있어서 ArrayList로 반환해와야 하는듯 없으면 Null
	private ArrayList<String> havePaymentHistory() {
		ArrayList<String> temp = new ArrayList<String>();
		for(ProgramPaymentInfo p : this.paymentList) {
			// 결제내역 회원번호 = 로그인 회원번호
			if(p.getUserCode().equals(this.login.getCode())) { 
				temp.add(p.getProgramCode()); // 신청내역에 있는 코드들을 담는다
			}
		}
		return temp;
	}

	/**
	 *  수업 이력
	 * 
	 */
	private void myProgramHistory() {
		
	}

	/**
	 *  수업 환불
	 * 
	 */
	private void myProgramRefund() {
		
	}


	// 목록에서 선택한 번호 리턴 
	private int userSelectNum() {
		System.out.println("1. 수업 현황");
		System.out.println("2. 수업 이력");
		System.out.println("3. 수업 환불");
		System.out.println();
		System.out.print("번호를 입력하세요 : ");
		
		return selectNum();
	}
	
	// 번호를 입력받는 메서드
	private static int selectNum() {
		// 사용자에게 번호를 입력받는다
		Scanner scan = new Scanner(System.in);
		System.out.println();
		return Integer.parseInt(scan.nextLine());
	}
	
	
	
	
	// 프로그램.txt에서 데이터를 읽어온다
	private ArrayList<Program> loadProgramData(String path) {

		ArrayList<Program> list = new ArrayList<Program>();
		File file = new File(path);

		if (file.exists()) {

			try {
				BufferedReader reader = new BufferedReader(new FileReader(path));

				String line = null;

				while ((line = reader.readLine()) != null) {
					String[] temp = line.split(",");

					list.add(new Program(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5],
							Integer.parseInt(temp[6]), Integer.parseInt(temp[7])));
				}

				reader.close();
				return list;

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("primaryMyPath.enloadData()");
				e.printStackTrace();
				return null;
			}

		} else {
			System.out.println("파일이 존재하지 않음");
			return null;
		}

	}
	
	// 프로그램수강생.txt에서 데이터를 읽어온다
	private ArrayList<ProgramStudent> loadProgramStudentData(String path) {

		File file = new File(path);

		if (file.exists()) {

			try {
				ArrayList<ProgramStudent> list = new ArrayList<ProgramStudent>();
				BufferedReader reader = new BufferedReader(new FileReader(path));

				String line = null;

				while ((line = reader.readLine()) != null) {
					String[] temp = line.split(",");
					ArrayList<String> userCodes = new ArrayList<String>();

					String[] users = temp[3].split("■");
					for (int i = 0; i < users.length; i++) {
						userCodes.add(users[i]);
					}

					list.add(new ProgramStudent(temp[0], Integer.parseInt(temp[1]), temp[2], userCodes));
				}

				reader.close();
				return list;

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("primaryMyPath.enloadData()");
				e.printStackTrace();
				return null;
			}

		} else {
			System.out.println("파일이 존재하지 않음");
			return null;
		}
	}

	// 프로그램상태.txt에서 데이터를 읽어온다
	public ArrayList<ProgramState> loadProgramStateData(String path) {
		File file = new File(path);

		if (file.exists()) {

			try {
				ArrayList<ProgramState> list = new ArrayList<ProgramState>();
				BufferedReader reader = new BufferedReader(new FileReader(path));

				String line = null;

				while ((line = reader.readLine()) != null) {
					String[] temp = line.split(",");
					list.add(new ProgramState(temp[0], temp[1], temp[2], temp[3]));
				}

				reader.close();
				return list;

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("primaryMyPath.enloadData()");
				e.printStackTrace();
				return null;
			}

		} else {
			System.out.println("파일이 존재하지 않음");
			return null;
		}
	}
	
	// 프로그램결제.txt에서 데이터를 읽어온다
	public ArrayList<ProgramPaymentInfo> loadProgramPaymentData(String path) {
		
		File file = new File(path);

		if (file.exists()) {

			try {
				ArrayList<ProgramPaymentInfo> list = new ArrayList<ProgramPaymentInfo>();
				BufferedReader reader = new BufferedReader(new FileReader(path));

				String line = null;

				while ((line = reader.readLine()) != null) {
					String[] temp = line.split(",");
					list.add(new ProgramPaymentInfo(temp[0]
													, temp[1]
													, temp[2]
													, temp[3]
													, Integer.parseInt(temp[4])
													, temp[5]));
				}

				reader.close();
				return list;

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("primaryMyPath.enloadData()");
				e.printStackTrace();
				return null;
			}

		} else {
			System.out.println("파일이 존재하지 않음");
			return null;
		}
	}	
	
	// 프로그램출결.txt에서 데이터를 읽어온다
//	public ArrayList<ProgramAttendance> loadProgramAttendance(String path) {
//		File file = new File(path);
//		
//		ArrayList<ProgramStudent> psList = loadProgramStudentData(Path.PROGRAMSTUDENT);
//		
//		for(ProgramStudent ps : psList ) {
//			if(this.login.get)
//			String[] users = temp[3].split("■");
//			for (int i = 0; i < users.length; i++) {
//				userCodes.add(users[i]);
//			}
//		}
//		
//		
//		if (file.exists()) {
//
//			try {
//				
//				ArrayList<ProgramAttendance> list = new ArrayList<ProgramAttendance>();
//				BufferedReader reader = new BufferedReader(new FileReader(path));
//
//				String line = null;
//
//				// String code, String date, HashMap<String, Boolean>
//				while ((line = reader.readLine()) != null) {
//					String[] temp = line.split(",");
//					ArrayList<String> userCodes = new ArrayList<String>();
//
//					String[] attendance = temp[2].split("■");
//					for (int i = 0; i < attendance.length; i++) {
//						userCodes.add(attendance[i]);
//					}
//					
//					String[] temp = line.split(",");
//					list.add(new ProgramState(temp[0], temp[1], temp[2], temp[3]));
//				}
//
//				reader.close();
//				return list;
//
//			} catch (Exception e) {
//				// TODO: handle exception
//				System.out.println("primaryMyPath.enloadData()");
//				e.printStackTrace();
//				return null;
//			}
//
//		} else {
//			System.out.println("파일이 존재하지 않음");
//			return null;
//		}

	
	// 일시정지
	private static void pause() {
		Scanner scan = new Scanner(System.in);
		System.out.println("일시정지");
		scan.nextLine();
		for(int i=0 ; i<20 ; i++) {
			System.out.println();
		}
	}
}
	
	
	

