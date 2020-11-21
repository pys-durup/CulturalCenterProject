package com.project.center.program;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

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
				
			} else if (num == 4) { // 뒤로가기
				break;
			} else {
				break;
			}
			
		}
	}
	
	// 진행중인 프로그램 목록
	private void findProgram(String state) {
		
		ArrayList<Program> pshowList = makeProgrmaList(state);
		
		while(true) {
			int num = showProgramList(pshowList);
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
		
		
		System.out.println("[프로그램 정보]");
		System.out.println("프로그램 이름 : " + program.getName());
		System.out.println("강사명 : " + program.getTeacher());
		System.out.println("시작일 : " + program.getStartDate());
		System.out.println("종료일 : " + program.getEndDate());
		System.out.println("총 수업일수 : " + allClassDays + "일");
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
			
			System.out.printf("<%s의 수업출석률(%d%%)>\n",userCode , (int)(rate1*100));
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
	private int showProgramList(ArrayList<Program> pshowList) {
		
		
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
				System.out.println("1. 출결정보 조회하기\t 4. 뒤로가기");
			} else {
				System.out.println("1. 출결정보 조회하기 2. 이전 목록 3. 다음 목록 4. 뒤로가기");
			}
			
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
				return -1 ;
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
	
	// 출결 조회 메뉴를 보여준다
	private int showAttednanceMenu() {
		System.out.println("==================================");
		System.out.println("[출결조회 방법 선택]");
		System.out.println("1. 진행중인 프로그램");
		System.out.println("2. 종료된 프로그램");
		System.out.println("3. 회원번호로");
		System.out.println("4. 뒤로가기");		
		System.out.println("==================================");
		System.out.println();
		System.out.print("번호를 선택하세요 :");
		
		return selectNum();
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
	
	// 번호를 입력받는 메서드
	private static int selectNum() {
		// 사용자에게 번호를 입력받는다
		Scanner scan = new Scanner(System.in);
		return Integer.parseInt(scan.nextLine());
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
