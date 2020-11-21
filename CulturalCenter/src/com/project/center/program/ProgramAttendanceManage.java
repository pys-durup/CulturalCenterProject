package com.project.center.program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

import com.project.center.user.User;

import data.Path;

/**
 * @author youngsu 
 * 관리자의 프로그램 출결 정보 조회기능
 * 
 */
public class ProgramAttendanceManage {
	
	private ArrayList<Program> pList; // 프로그램 정보 출력을위해 필요
	private ArrayList<ProgramStudent> pstudentList; // 정원, 프로그램모집상태 위해 필요
	private ArrayList<ProgramState> pstateList; // 프로그램 진행중,종료됨 을 얻기위해
	private ArrayList<ProgramAttendance> paList;
	
	
	public ProgramAttendanceManage() {

		this.pList = ProgramRegistrationList.loadProgramData(Path.PROGRAMLIST);
		this.pstudentList = ProgramRegistrationList.loadProgramStudentData(Path.PROGRAMSTUDENT);
		this.pstateList = ProgramRegistrationList.loadProgramStateData(Path.PROGRAMSTATE);
		this.paList = ProgramRegistrationList.loadProgramAttendance(Path.PROGRAMATTENDANCE);
	}
	
	// 출결 정보 조회기능 진행
	public void createAttendanceMenu() {
		int num = 0;
		// 출결 조회 메뉴를 보여준다
		while (true) {
			num = showAttednanceMenu();
			
			if(num == 1) { // 진행중 프로그램
				findProgram("진행중");
			} else if (num == 2) { // 종료된 프로그램
				findProgram("종료됨");
			} else if (num == 3) { // 회원번호로 검색
				findUserCode();
			} else if (num == 4) { // 뒤로가기
				break;
			} else {
				break;
			}
			
		}
	}
	// 회원번호로 검색
	private void findUserCode() {
		
			System.out.print("회원 번호를 입력하세요 : ");
			int num = selectNum();
		
		while(true) {
			
			// 존재하는 회원코드면
			if(isUserCode(num)) {
				
				String userCode = String.valueOf(num);
				HashMap<String, Integer> programCount = new HashMap<String, Integer>(); // 진행중, 종료됨 프로그램 갯수체크
				ArrayList<String> programCodes = new ArrayList<String>(); // 구매내역에 있는 프로그램 코드들
				
				String onGoingProgram = ""; // 진행중인 프로그램 코드
				ArrayList<String> exitProgram = new ArrayList<String>(); // 종료된 프로그램 코드들
				
				Program onGoingProgramObject = null; // 진행중인 프로그램 객체
				ArrayList<Program> exitProgramObjects = new ArrayList<Program>(); // 종료된 프로그램 객체들
				
			
				// 프로그램결제.txt를 읽어온다
				ArrayList<ProgramPaymentInfo> paymentList = new ArrayList<ProgramPaymentInfo>();
				paymentList = ProgramRegistrationList.loadProgramPaymentData(Path.PROGRAMPAYMENT);
				
				// 결제내역에서 구매한 프로그램의 code ArrayList를 리턴
				for (ProgramPaymentInfo ppi : paymentList) {
					if(ppi.getUserCode().equals(userCode)) {
						programCodes.add(ppi.getProgramCode()); // 구매했던 코드들
					}
				}
				
				// 프로그램 상태.txt에서 구매한 프로그램이 진행중, 종료됨 프로그램인지 탐색
				for (String programCode : programCodes) {
					for (ProgramState ps : this.pstateList) {
						if (ps.getCode().equals(programCode)) {
							if (ps.getState().equals("진행중")) {
								// 진행중, 종료됨 프로그램 갯수체크
								programCount.put("진행중", 1);
								// 진행중인 프로그램 코드
								onGoingProgram = programCode;
							} else if(ps.getState().equals("종료됨")) {
								// 종료된 프로그램 코드들
								exitProgram.add(programCode);
								// 진행중, 종료됨 프로그램 갯수체크
								if(programCount.containsKey("종료됨")) {
									programCount.put("종료됨", programCount.get("종료됨") + 1);
								} else {
									programCount.put("종료됨", 1);
								}
							}
						}
					}					
				}
				
				
				// onGoingProgram 진행중인 프로그램 코드를 이용해서 해당 프로그램 객체 만들기
				if(onGoingProgram != "") {
					for (Program p : this.pList) {
						if(p.getCode().equals(onGoingProgram)) {
							onGoingProgramObject = new Program(p.getCode(), p.getName(), p.getTeacher(), p.getClassRoom(), p.getStartDate(), p.getEndDate(), p.getCapacity(), p.getPrice());
						}
					}
				}
				
				// exitProgram 종료된 프로그램 코드를 이용해서 해당 프로그램 객체 ArrayList 만들기
				if(exitProgram.size() != 0) {
					for (String s : exitProgram) {
						for (Program p : this.pList) {
							if(p.getCode().equals(s)) {
								exitProgramObjects.add(new Program(p.getCode(), p.getName(), p.getTeacher(), p.getClassRoom(), p.getStartDate(), p.getEndDate(), p.getCapacity(), p.getPrice()));
							}
						}					
					}
				}
				
				
				// 회원번호로 조회 메뉴를 출력
				int num1 = showUserNumMenu(userCode);

				if (num1 == 1) { // 진행중인 프로그램
					if (onGoingProgram == "") { // 진행중인 프로그램이 없다면,
						System.out.println("현재 진행중인 프로그램이 없는 회원입니다");
						pause();
					} else { // 진행중인 프로그램이 있으면
						// 진행중인 프로그램의 출결정보를 출력하는 메서드
						showProgramAttendance(onGoingProgramObject);						
					}
				} else if (num1 == 2) { // 종료된 프로그램
					if (exitProgram.size() == 0) { // 종료된 프로그램이 없다면,
						System.out.println("수강했떤 이력이 없는 회원입니다");
						pause();
					} else { // 종료된 프로그램이 있으면
						//
						int selectNum = showProgramList(exitProgramObjects,"종료됨");
						if(selectNum < 0) {
							break;
						} else { // 받아온 번호로 해당 프로그램의 출결정보를 출력하는 메서드
							showProgramAttendance(exitProgramObjects.get(num-1));
						}
					}
					
				} else if (num1 == 3) { // 뒤로가기
					break;
				} else {
					System.out.println("올바르지 않은 입력");
					pause();
					break;
				}
				
				
			} else { // 존재하는 회원코드가 아니면
				System.out.println("존재하지 않는 회원번호입니다");
				pause();
				break;
			}
		}
		
	}
	
	
	// 회원번호로 조회 메뉴를 출력
	private int showUserNumMenu(String userCode) {
		String userName = getUserName(userCode);
		System.out.println();
		System.out.println("=====================================");
		System.out.printf("  <%s(%s번) 님의 출결조회>\n",userName, userCode);
		System.out.println("=====================================");
		System.out.println("\t1. 진행중인 프로그램");
		System.out.println("\t2. 종료된 프로그램");
		System.out.println("\t3. 뒤로가기");
		System.out.println("=====================================");
		System.out.println();
		System.out.print("번호를 입력하세요 : ");
		
		
		return selectNum();
	}
	
	// 유저코드가 존재하는 유저코드인지 확인
	private boolean isUserCode(int num) {
		String userCode = String.valueOf(num);
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Path.USERLIST));
			
			String line = null;
			while((line = reader.readLine()) != null) {
				String[] temp = line.split(",");
				if(temp[0].equals(userCode)) {
					return true;
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryProgramAttendanceManage.engetUserName()");
			e.printStackTrace();
		}
		
		
		return false;
		
	}

	// 진행중인 프로그램 목록
	private void findProgram(String state) {
		
		ArrayList<Program> pshowList = makeProgrmaList(state);
		
		while(true) {
			int num = showProgramList(pshowList, state);
			if(num < 0) {
				break;
			} else { // 받아온 번호로 해당 프로그램의 출결정보를 출력하는 메서드
				showProgramAttendance(pshowList.get(num-1));
			}
		}
		
		
	}
	
	// 받아온 프로그램의 정보 + 출결정보를 출력한다
	private void showProgramAttendance(Program program) {
		int classDays = 0; // 현재 수업일수
		int allClassDays = 0; // 총 수업일수 
		int attendanceDays = 0; // 출석일수
		int count = 25; // 출력용 count
		ArrayList<String> userCodes = new ArrayList<String>();// 수강생 목록
		Calendar calStartDate = stringToCal(program.getStartDate()); // 수업시작일
		Calendar calEndDate = stringToCal(program.getEndDate()); // 수업종료일
		
		// 해당 프로그램의 수강생 목록을 받아온다
		for(ProgramStudent ps : this.pstudentList) {
			if(ps.getCode().equals(program.getCode())) {
				userCodes = ps.getUserCodes();
			}
		}
		
		// 총 수업일수 구하기
		while(!calStartDate.after(calEndDate)) {
			int day = calStartDate.get(Calendar.DAY_OF_WEEK); // 1이면 일요일 7이면 토요일
			if((day != Calendar.SATURDAY) && (day != Calendar.SUNDAY)) {
				allClassDays++; // 총수업일수 증가
			}
			calStartDate.add(Calendar.DATE, 1); // 하루 증가
		}
		
		System.out.println("==========================================================");
		System.out.println("\t\t<선택한 프로그램 출결 정보>");
		System.out.println("==========================================================");
		System.out.println("[프로그램 정보]");
		System.out.println("프로그램 이름 : " + program.getName());
		System.out.println("강사명 : " + program.getTeacher());
		System.out.println("시작일 : " + program.getStartDate());
		System.out.println("종료일 : " + program.getEndDate());
		System.out.println("총 수업일수 : " + allClassDays + "일");
		System.out.println("==========================================================");
		System.out.println();
		
		String view1 = ""; // 출석일 / 수업일수
		for (String userCode : userCodes) {
			// 초기화
			classDays = 0;
			attendanceDays = 0;
			for (ProgramAttendance pa : this.paList) {
				// 출결 데이터중에 프로그램 코드가 같다면
				if(program.getCode().equals(pa.getCode())) {
					classDays++; // 수업일수 증가
					if(pa.getAttendance().get(userCode).equals("T")) {
						attendanceDays++;  // 출석일수 증가
					} 
				}
			}
			// 여기서 출력후 
			float rate1 = attendanceDays/(float)classDays;
			
			System.out.printf("<%s(%s)의 수업출석률(%d%%)>\n",userCode , getUserName(userCode), (int)(rate1*100));
			for(int i=0 ; i<Math.floor(count*rate1) ; i++) {
				System.out.print("■");
			}
			for(int i=0 ; i<count-Math.floor(count*rate1) ; i++) {
				System.out.print("□");
			}
			System.out.printf("(%d/%d)\n", attendanceDays, classDays);
		}
		
		
		
		pause();
		
		
	}
	
	// 프로그램 리스트를 출력한다
	private int showProgramList(ArrayList<Program> pshowList, String state) {
		
		
		int index = 1;
		int startIndex = 1;
		int endIndex = 10;
		int pagesize = 10; // 10개씩 출력
		
		if(pshowList.size() < 10) {
			endIndex = pshowList.size();
		} 
		
		
		while(true) {
			
			int size = pshowList.size();
//			clear();
			System.out.println("============================================================================================");
			if(state.equals("진행중")) System.out.println("\t\t\t<진행중인 프로그램 목록>");
			if(state.equals("종료됨")) System.out.println("\t\t\t<종료된 프로그램 목록>");			
			System.out.println("============================================================================================");
			System.out.println("프로그램의 개수 : " + size);
			
			System.out.println("[번호]  [프로그램 이름]\t\t\t\t [강사명]    [시작 날짜]\t[종료 날짜]");
			for(int i=startIndex ; i<=endIndex ; i++) {
				//  [번호]  [프로그램 이름]\t\t\t\t [강사명]    [시작 날짜]\t[종료 날짜]
				System.out.printf("%3d\t%-25s\t%5s\t%s\t%s\n"
						, index
						, pshowList.get(i-1).getName()
						, pshowList.get(i-1).getTeacher()
						, pshowList.get(i-1).getStartDate()
						, pshowList.get(i-1).getEndDate()
						);
					index ++;					
			}
			
			// 검색 개수에 따라서 개수 조절
			System.out.println();
			if(pshowList.size() < 10) {
				System.out.println("============================================================================================");
				System.out.println();
				System.out.println("1. 출결정보 조회하기\t 4. 뒤로가기");
			} else {
				System.out.println("============================================================================================");
				System.out.println();
				System.out.println("1. 출결정보 조회하기 2. 이전 목록 3. 다음 목록 4. 뒤로가기");
			}
			System.out.println();
			System.out.print("번호를 입력하세요 : ");
			int num = selectNum();
			
			if (num == 1) {
				System.out.print("조회할 프로그램의 번호 입력 : ");
				int programNum = selectNum();
				if( programNum > 0 && programNum <= pshowList.size()) {
					return programNum;
				} else {
					System.out.println("올바르지 않은 프로그램 번호입니다");
				}
			} else if (num == 2) { // 이전목록
				if(index == 11) {
					System.out.println("처음 페이지 입니다");
					index = 1;
					pause();
				} else if (size == endIndex) {
					int tempNum = index - startIndex;
					index -= (pagesize + tempNum);
					startIndex -= pagesize;
					endIndex -= tempNum;
				} else {
					index -= pagesize * 2;
					startIndex -= pagesize;
					endIndex -= pagesize;
				}
			} else if (num == 3) { // 다음목록
				if(endIndex/10 == size/10 && size != endIndex) { // 끝의 전페이지 인경우
					startIndex += pagesize;
					endIndex += (size - startIndex + 1);
					
				} else if(size == endIndex ) { // 끝페이지인 경우
					System.out.println("마지막페이지 입니다");
					index -= (endIndex - startIndex + 1);
					pause();
				} else { 
					startIndex += pagesize;
					endIndex += pagesize;
				}
			} else if (num == 4) {
				return -1;
			} else {
				return -1;
			}
		}
		
	}
	
	// 진행중 or 종료된 프로그램 리스트를 ArrayList에 담는다
	private ArrayList<Program> makeProgrmaList(String state) {
		
		ArrayList<String> programCode = new ArrayList<String>();
		ArrayList<Program> pshowList = new ArrayList<Program>();
		
		for (ProgramState ps : pstateList) {
			if(ps.getState().equals(state)) {
				// 진행중 or 종료됨인 프로그램 코드를 저장해둠
				programCode.add(ps.getCode());
			}
		}
		
		
		// 진행중 or 종료됨인 프로그램의 정보를 담을 ArrayList 객체 만들어서 반환
		for (String s : programCode) {
			for(Program p : this.pList) {
				if(p.getCode().equals(s)) {
					pshowList.add(new Program(p.getCode()
												, p.getName()
												, p.getTeacher()
												, p.getStartDate()
												, p.getEndDate()));
				}
			}
		}
		
		
		return pshowList;
		
	}
	
	
	// 유저코드를 유저이름으로 변환해서 돌려주는 메서드
	private String getUserName(String userCode) {
		ArrayList<User> userList = new ArrayList<User>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Path.USERLIST));
			
			String line = null;
			while((line = reader.readLine()) != null) {
				String[] temp = line.split(",");
				if(temp[0].equals(userCode)) {
					return temp[1];
				}
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryProgramAttendanceManage.engetUserName()");
			e.printStackTrace();
		}
		
		return "";
	}
	
	
	
	// 출결 조회 메뉴를 보여준다
	private int showAttednanceMenu() {
		
		System.out.println("=====================================");
		System.out.println("\t<출결조회 방법 선택>");
		System.out.println("=====================================");
		System.out.println("\t1. 진행중인 프로그램");
		System.out.println("\t2. 종료된 프로그램");
		System.out.println("\t3. 회원번호로");
		System.out.println("\t4. 뒤로가기");		
		System.out.println("=====================================");
		System.out.println();
		System.out.print("번호를 선택하세요 :");
		
		return selectNum();
	}
	
	// 일시정지
	private static void pause() {
		Scanner scan = new Scanner(System.in);
		System.out.println("엔터키를 누르면 뒤로 이동합니다");
		scan.nextLine();
		for(int i=0 ; i<50 ; i++) {
			System.out.println();
		}
	}
	
	// 번호를 입력받는 메서드
	private static int selectNum() {
		// 사용자에게 번호를 입력받는다
		Scanner scan = new Scanner(System.in);
		String userInput = scan.nextLine();
		if(userInput.equals("")) {
			System.out.println("잘못된 입력입니다");
			pause();
			return -1;
		} else {
			return Integer.parseInt(userInput);			
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
}
