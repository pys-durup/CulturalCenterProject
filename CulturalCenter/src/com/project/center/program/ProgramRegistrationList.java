package com.project.center.program;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
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
	private ArrayList<ProgramStudent> pstList;
	private User login;
	
	public ProgramRegistrationList() {
		this.login = new User("7848", "박영수", "1993-08-17","tteesstt", "tteesstt",  "1", "01077743635",  "1" , "주소");
	}
	
	public ProgramRegistrationList(User login) {
		// 테스트용 유저 객체
		this.login = new User("7848", "박영수", "1993-08-17","tteesstt", "tteesstt",  "1", "01077743635",  "1" , "주소");
		//this.login = login;
	}

	public void createProgramRegistorList() {
		
		while(true) {
			// 사용자가 선택한 번호
			int selectNum = userSelectNum();
			
			if(selectNum == 1) { // 1. 진행중인 프로그램
				System.out.println("진행중인 프로그램 코드 : " + myProgramState());
				showGoingProgram(myProgramState());
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

	private void showGoingProgram(String programCode) {
	
		String programName = "";
		String teacher = "";
		String classRoom = "";
		String startDate = "";
		String endDate = "";
		int classDays = 0; // 수업일수
		int attendanceDays = 0; // 출석일수
		int absentDays = 0; // 결석일수 
		int allClassDays = 0; // 총 수업일수 
		Calendar now = Calendar.getInstance(); // 오늘
		
		// 추가적인 작업이 이루어지면 여기서 while문
		this.paList = loadProgramAttendance(Path.PROGRAMATTENDANCE);
		this.pList = loadProgramData(Path.PROGRAMLIST);
		System.out.println("로그인한 회원의 회원번호 " + login.getCode());
//		System.out.println(this.paList.get(108).getCode());
		
		// 프로그램 정보 가져오기
		for(Program p : this.pList) {
			if(p.getCode().equals(programCode)) {
				programName = p.getName();
				teacher = p.getTeacher();
				classRoom = p.getClassRoom();
				startDate = p.getStartDate();
				endDate = p.getEndDate();
				break;
			}
			
		}
		Calendar calStartDate = stringToCal(startDate);
		Calendar calEndDate = stringToCal(endDate);
		
//		System.out.printf("%tF", calStartDate);
//		System.out.printf("%tF", calEndDate);
		
		// 총 수업일수 구하기
		while(!calStartDate.after(calEndDate)) {
			int day = calStartDate.get(Calendar.DAY_OF_WEEK); // 1이면 일요일 7이면 토요일
			if((day != Calendar.SATURDAY) && (day != Calendar.SUNDAY)) {
				allClassDays++; // 총수업일수 증가
			}
			calStartDate.add(Calendar.DATE, 1); // 하루 증가
		}
		
		
		// 진행중인 프로그램의 출결정보 구하기
		for(ProgramAttendance ps : paList) {
			if(ps.getCode().equals(programCode)) {
//				System.out.println(ps.getDate() + " 출결 : " + ps.getAttendance().get(login.getCode()));
				classDays++; // 수업일수 증가
				if(ps.getAttendance().get(login.getCode()).equals("T")) {
					attendanceDays++;  // 출석일수 증가
				} else {
					absentDays++; // 결석일수 증가
				}
			}
		}
		
		System.out.println("[진행중인 프로그램]");
		System.out.println("프로그램 이름 : " + programName);
		System.out.println("강사명 : " + teacher);
		System.out.println("강의실 : " + classRoom);
		System.out.println("시작일 : " + startDate);
		System.out.println("종료일 : " + endDate);
		
//		System.out.println("출석일수 : " + attendanceDays);
//		System.out.println("결석일수 : " + absentDays);
//		System.out.println("수업일수 : " + classDays);
//		System.out.println("총 수업일수 : " + allClassDays); 
		
//		System.out.printf("출석률 : %f",attendanceDays/(float)classDays);
//		System.out.printf("진행률 : %f",classDays/(float)allClassDays);
		
		float rate1 = attendanceDays/(float)classDays;
		float rate2 = classDays/(float)allClassDays;
		
		// □ ■  출석/수업일수 > 출석률   수업일수/총수업 > 진행률
		int count = 25;
		// 출석률
		System.out.printf("<수업출석률(%d%%)>\n",(int)(rate1*100));
		for(int i=0 ; i<count*rate1 ; i++) {
			System.out.print("■");
		}
		for(int i=0 ; i<count-(count*rate1) ; i++) {
			System.out.print("□");
		}
		System.out.printf("(%d/%d)\n", attendanceDays, classDays);
		// 진행률
		System.out.printf("<수업진행률(%d%%)>\n",(int)(rate2*100));
		for(int i=0 ; i<count*rate2 ; i++) {
			System.out.print("■");
		}
		for(int i=0 ; i<count-(count*rate2) ; i++) {
			System.out.print("□");
		}
		System.out.printf("(%d/%d)\n", classDays, allClassDays);
		
		System.out.println();
		System.out.println("1. ㅁㅁㅁㅁ 2. 뒤로가기");
		System.out.print("번호를 입력하세요 : ");
	}

	/**
	 *  수업 현황
	 * 
	 */
	private String myProgramState() {
		this.paymentList = loadProgramPaymentData(Path.PROGRAMPAYMENT); // 프로그램결제.txt 데이터
		this.psList = loadProgramStateData(Path.PROGRAMSTATE); // 프로그램상태.txt 데이터
		this.pList = loadProgramData(Path.PROGRAMLIST); // 프로그램.txt 데이터
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
			return null;
		} else {
			System.out.println("결제내역이 존재하지 않음");
			System.out.println("진행중인 프로그램이 없습니다");
			pause();
			return null;
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
		System.out.println("1. 진행중 프로그램");
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
	public ArrayList<ProgramAttendance> loadProgramAttendance(String path) {
		// HashMap <프로그램코드, 프로그램수강하는 회원번호>
		HashMap<String, ArrayList<String>> code = new HashMap<String, ArrayList<String>>();
		
		
		
		File file = new File(path);

		if (file.exists()) {

			try {
				BufferedReader reader = new BufferedReader(new FileReader(Path.PROGRAMSTUDENT));

				String line = null;

				while ((line = reader.readLine()) != null) {
					ArrayList<String> userCodes = new ArrayList<String>();
					String[] temp = line.split(",");
					String[] users = temp[3].split("■");
					
					for (int i = 0; i < users.length; i++) {
						userCodes.add(users[i]);
					}
					code.put(temp[0], userCodes); // HashMap <프로그램코드, 프로그램수강하는 회원번호>
					
				}
				
//				System.out.println("code 내용 확인 : " + code.get("AD090108"));

				reader.close();
				
				
				ArrayList<ProgramAttendance> list = new ArrayList<ProgramAttendance>();
				reader = new BufferedReader(new FileReader(path));

				line = null;
				
				// String code, String date, HashMap<String, Boolean>
				while ((line = reader.readLine()) != null) {
					HashMap<String, String> attendanceHash = new HashMap<String, String>();
					ArrayList<String> userCodes = new ArrayList<String>();
					String[] temp = line.split(",");
					userCodes = code.get(temp[0]); // 해당 프로그램 코드의 수강생 리스트

					String[] attendance = temp[2].split("■"); // 수강리스트 별 출결정보
					for (int i = 0; i < attendance.length; i++) {
						attendanceHash.put(userCodes.get(i), attendance[i]);
					}
					
					list.add(new ProgramAttendance(temp[0], temp[1], attendanceHash));
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
	
	// String -> Calendar
	public static Calendar stringToCal(String s) {
		// "YYYY-MM-DD"
		String[] list = s.split("-");
		int year = Integer.parseInt(list[0]);
		int month = Integer.parseInt(list[1]);
		int date = Integer.parseInt(list[2]);

		Calendar temp = Calendar.getInstance();
		temp.set(year, month - 1, date);
		return temp;

	}

	
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
	
	
	

