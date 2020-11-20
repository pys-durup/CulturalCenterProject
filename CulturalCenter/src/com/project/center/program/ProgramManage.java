package com.project.center.program;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.project.center.user.User;

import data.Path;


public class ProgramManage {
	
	private ArrayList<Program> pList; // 프로그램 정보 출력을위해 필요
	private ArrayList<ProgramStudent> psList; // 정원, 프로그램모집상태 위해 필요
	private ArrayList<ProgramList> pshowList; // 프로그램 조회용
	private User user; // 유저 객체

	public ProgramManage() {
		// 생성과 동시에 데이터를 읽어옴
		this.pList = loadProgramData(Path.PROGRAMLIST);
		this.psList = loadPsData(Path.PROGRAMSTUDENT);
		this.pshowList = new ArrayList<ProgramList>();
		this.user = null;
		// 테스트용 유저 객체
		//this.user = new User("50001", "박영수", "1993-08-17","tteesstt", "tteesstt",  "1", "01077743635",  "1" , "주소");
		//String code, String name, String birth, String id, String pw, String gender, String tel, String group, String address)
	}


	
	/**
	 *  프로그램 신청이 진행되는 메서드
	 * 
	 */
	public void createApplyProgram(User user) {
		this.user = user;
		while(true) {
			// 프로그램 신청 메뉴
			showMenu();
			
			// 입력 번호를 받아옴
			System.out.print("번호를 입력하세요 : ");
			int num = selectNum();
			System.out.println();
			
			if(num == 1) { // 검색어로 찾기
				System.out.println("=====================================");
				System.out.println("\t  <검색어로 찾기>");
				System.out.println("=====================================");
				System.out.print("검색어를 입력하세요 : ");
				String text = getSearchString();
				showSearchList(text);
				break;
			} else if(num == 2) { // 연령별 목록
				showAgeResult();
			} else if(num == 3) { // 테마별 목록
//				System.out.println("테마별");
				showThemeResult();
			} else if(num == 4) { // 월별 목록
//				System.out.println("월별");
				showMonthResult();
			} else if(num == 5){
				System.out.println("뒤로가기");
				break;
			} else {
				pause();
			}
 		}
	}

	
	
	/**
	 *  프로그램 신청 메뉴를 보여주는 메서드
	 * 
	 */
	private void showMenu() {
		clear();
		System.out.println("=====================================");
		System.out.println("\t  <프로그램 신청>");
		System.out.println("=====================================");
		System.out.println("\t1. 검색어로 찾기");
		System.out.println("\t2. 연령별 추천 목록");
		System.out.println("\t3. 테마별 추천 목록");
		System.out.println("\t4. 월별 선택");
		System.out.println("\t5. 뒤로가기");
		System.out.println("=====================================");
		System.out.println();
	}
	
	
	/**
	 *  검색어를 받아오는 메서드 
	 *   
	 */
	private String getSearchString() {
//		System.out.println("getSearchString()");
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}
	
	/**
	 * 	검색 결과를 ArrayList에 담아두는 메서드 
	 *  @param text : 검색한 검색어
	 *  
	 */
	private void showSearchList(String text) {
//		System.out.println("showSearchList(text)");
		// 검색어 
		String keyword = text.trim();
		// 검색된 결과를 담을 리스트
		ArrayList<Program> resultList = new ArrayList<Program>();
		
		// 검색된 결과가 있을시 리스트에 저장
		for(Program data : pList) {
			if(data.getName().indexOf(text) > 0) {
				resultList.add(data);
			}
		}
		
		// 검색 결과를 출력하는 메서드
		showSearchresult(resultList, text);
		
	}
	
	/**
	 * 	검색 결과를 담아두는 메서드
	 *  @param list : 검색어로 검색한 프로그램을 담아둔 ArrayList
	 *  @param text : 검색한 검색어
	 */
	private void showSearchresult(ArrayList<Program> list, String text) {
		while (true) {
			this.pshowList.clear(); // 검색결과 리스트 초기화
			
			if(list.size() == 0) { // 검색 결과가 없다면
				System.out.println("검색 결과가 존재하지 않습니다");
				break;
			} else {

				
//				System.out.println("[번호]  [프로그램 이름]\t\t\t[강사명]    [강의실]    [시작 날짜]\t[종료 날짜]\t[정원]\t  [현재상태]\t[가격]");
				
				// 프로그램 목록을 출력하고 pshowList에 출력데이터를 담는다
				showProgramList(list);
				
				System.out.println();
				
				// 신청 설정하는 메서드
				setApplyProgram(text);
				break;
				
			}
		}		
	}
	
	/**
	 * 	프로그램 리스트 출력 & 프로그램 신청 설정하는 메서드
	 *  @param list : 출력할 리스트
	 *  
	 */
	private void setApplyProgram(String text) {
		
		int index = 1;
		int startIndex = 1;
		int endIndex = 10;
		int pagesize = 10; // 10개씩 출력
		
		if(this.pshowList.size() < 10) {
			endIndex = this.pshowList.size();
		} 

		while(true) {
			// TA, AD, OD
			int size = this.pshowList.size();
			clear();
			System.out.println("============================================================================================================================================");
			System.out.println("\t  <선택한 프로그램의 목록>");
//			if (text.equals("TA")) {
//				System.out.println("\t  '청소년'에게 추천하는 프로그램 검색 결과입니다");
//			} else if (text.equals("AD")) { 
//				System.out.println("\t  '성인'에게 추천하는 프로그램 검색 결과입니다");
//			} else if (text.equals("OD")) { 
//				System.out.println("\t  '중장년'에게 추천하는 프로그램 검색 결과입니다");
//			} else if (text.equals("01")) {
//				
//			} else if () {
//				{
//			}
//				System.out.println("asdf");
//			} else {
//				System.out.printf("\t  '%s'로 검색한 결과 입니다\n", text);
//			}
			
			switch (text) {
			case "TA": System.out.println("\t  '청소년'에게 추천하는 프로그램 검색 결과입니다"); break;
			case "AD": System.out.println("\t  '성인'에게 추천하는 프로그램 검색 결과입니다"); break;
			case "OD": System.out.println("\t  '중장년'에게 추천하는 프로그램 검색 결과입니다"); break;
			case "01": System.out.println("\t  '요리' 테마의 프로그램 검색 결과입니다"); break;
			case "02": System.out.println("\t  '스포츠' 테마의 프로그램 검색 결과입니다"); break;
			case "03": System.out.println("\t  '언어' 테마의 프로그램 검색 결과입니다"); break;
			case "04": System.out.println("\t  '건강' 테마의 프로그램 검색 결과입니다"); break;
			case "05": System.out.println("\t  '원예' 테마의 프로그램 검색 결과입니다"); break;
			case "06": System.out.println("\t  '미술' 테마의 프로그램 검색 결과입니다"); break;
			case "07": System.out.println("\t  '댄스' 테마의 프로그램 검색 결과입니다"); break;
			case "08": System.out.println("\t  '악기' 테마의 프로그램 검색 결과입니다"); break;
			case "09": System.out.println("\t  '컴퓨터' 테마의 프로그램 검색 결과입니다"); break;
			case "2020-12": System.out.println("\t  2020년 12월의 프로그램 검색 결과입니다"); break;
			case "2021-1": System.out.println("\t  2021년 1월의 프로그램 검색 결과입니다"); break;
			default: System.out.printf("\t  '%s'로 검색한 결과 입니다\n", text); break;
			}
			
			
			System.out.println("============================================================================================================================================");
			System.out.println("프로그램의 개수 : " + size);
			
			System.out.println("[번호]  [프로그램 이름]\t\t\t\t [강사명]     [강의실]    [시작 날짜]\t[종료 날짜]\t [정원]   [현재상태]       [가격]");
			for(int i=startIndex ; i<=endIndex ; i++) {
				//  [프로그램 이름]   [강사명]    [강의실]     [시작 날짜]   [종료 날짜]   [정원]   [현재상태]     [가격]
				System.out.printf("%3d\t%-25s\t%5s\t%s\t   %s\t%s\t(%d/%d)\t    %s\t %,d원\n"
						, index
						, this.pshowList.get(i-1).getName()
						, this.pshowList.get(i-1).getTeacher()
						, this.pshowList.get(i-1).getClassRoom()
						, this.pshowList.get(i-1).getStartDate()
						, this.pshowList.get(i-1).getEndDate()
						, this.pshowList.get(i-1).getCount() , this.pshowList.get(i-1).getCapacity()
						, this.pshowList.get(i-1).getState()
						, this.pshowList.get(i-1).getPrice());
					index ++;					
			}

			// 중간점검
//			System.out.println("현재 index -> " + index);
//			System.out.println("현재 startIndex -> " + startIndex);
//			System.out.println("현재 endIndex -> " + endIndex);
			
			// 검색 개수에 따라서 개수 조절
			System.out.println();
			if(this.pshowList.size() < 10) {
				System.out.println("============================================================================================================================================\n");
				System.out.println("1. 프로그램 신청하기\t 4. 뒤로가기");
				System.out.println();
			} else {
				System.out.println("============================================================================================================================================\n");
				System.out.println("1. 프로그램 신청하기 2. 이전 목록 3. 다음 목록 4. 뒤로가기");
				System.out.println();
			}
			
			System.out.print("번호를 입력하세요 : ");
			int num = selectNum();
//			System.out.println("pshowList size : " + pshowList.size());
			
			if(num == 1) { // 1. 프로그램 신청하기
				System.out.print("신청할 프로그램의 번호 입력 : ");
				int programNum = selectNum();
				if( programNum > 0 && programNum <= pshowList.size()) {
					// 올바른 프로그램 번호 입력
					// 프로그램의 수강인원이 가득 찬 경우 신청을 못한다
					if(this.pshowList.get(programNum-1).getCount() != this.pshowList.get(programNum-1).getCapacity()) {
						// 프로그램 신청을 진행하는 메서드
						applyProgram(this.pshowList.get(programNum-1));
						break;
					} else {
						// 신청 실패
						System.out.println("모집이 마감된 프로그램 입니다");
						pause();
						break;
					}
					
				} else {
					// 올바르지 않은 프로그램 번호 입력
					System.out.println("올바르지 않은 번호입니다");
				}
				
			} else if (num == 2) { // 2. 이전 목록
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
			} else if (num == 3) { // 3. 다음 목록
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
			} else if (num == 4) { // 4. 뒤로가기
				break;
			} else {
				pause();
				break;
			}
		}
	}
	
	/**
	 * 	ArrayList<ProgramList> pshowList에 출력데이터를 담는다
	 *  @param list : 담을 리스트
	 *  
	 */
	private void showProgramList(ArrayList<Program> list) {
		this.pshowList.clear(); // 초기화
		for(Program p : list) { // 검색 결과가 있을때 출력내용 생성
			int count = 0; // 현재 수강중인 인원
			String state = ""; // 현재 프로그램 모집상태(모집중 / 마감)
			
			// 프로그램의 시작날짜가 오늘보다 늦은거
			Calendar now = Calendar.getInstance();
			Calendar startDate = stringToCal(p.getStartDate());
			if(!now.after(startDate)) {
				
				for(ProgramStudent ps : this.psList) {
					if(p.getCode().equals(ps.getCode())) {
						count = ps.getCount();
						state = ps.getState();
						break;
					}
				}
				// 출력을 위한 ArrayList<ProgramList> 에 정보들을 넣어놓는다
				this.pshowList.add(new ProgramList(p.getCode()
						,p.getName()
						,p.getTeacher()
						,p.getClassRoom()
						,p.getStartDate()
						,p.getEndDate()
						,count
						,p.getCapacity()
						,state
						,p.getPrice()));
				}
			}
			
	}
	
	
	/**
	 * 	프로그램 신청을 진행하는 메서드
	 *  @param program : 사용자가 선택한 ProgramList 객체
	 *  
	 */
	private void applyProgram(ProgramList program) {
		System.out.println("applyProgram method in");
		/*
			프로그램 이름 : 테스트 프로그램 
			강사명 : 테스트
			강의실 : 101호 
			시작 날짜 : 2020-10-20
			종료 날짜 : 2020-11-20
			정원 : 20/30
			현재상태 : 모집중   
			가격 : 1,000,000원
			1. 프로그램 신청하기 2. 뒤로가기

		 */
		while(true) {
			clear();
			System.out.println("======================================================");
			System.out.println("\t<프로그램 신청하기>");
			System.out.println("======================================================");
			System.out.println("\t[선택한 프로그램의 상세정보]");
			System.out.printf("프로그램 이름 : %s\n", program.getName());
			System.out.printf("강사명 : %s\n",program.getTeacher());
			System.out.printf("강의실 : %s\n",program.getClassRoom());
			System.out.printf("시작날짜 : %s\n",program.getStartDate());
			System.out.printf("종료날짜 : %s\n",program.getEndDate());
			System.out.printf("정원 : %d/%d\n", program.getCount() , program.getCapacity());
			System.out.printf("가격 : %,d원\n",program.getPrice());
			System.out.println("=====================================================");
			System.out.println();
			System.out.println("1.프로그램 결제하기  2.취소하기");
			System.out.print("번호를 입력하세요 : ");
			int num = selectNum();
			
			if(num == 1) {
				// 프로그램 결제 메서드
				// ProgramList program객체를 넘겨준다
				ProgramPayment pp = new ProgramPayment(program, user);
				// 프로그램 결제 진행 메서드 호출 *************************
				pp.createPayment();
				break;
			} else if(num == 2) { // 뒤로가기
				break;
			} else {
				pause();
				break;
			}
			
		}
		
	}



	/**
	 * 	연령별 추천 리스트를 출력하는 메서드
	 *  
	 */
	private void showAgeResult() {
		
		// 생년월일로 나이 계산
		String birthYear = this.user.getBirth().substring(0, 4);
		Calendar now = Calendar.getInstance();
		int age =  now.get(Calendar.YEAR) - Integer.parseInt(birthYear);
		String ageCode = "";
		
		// 나이 -> 나이코드
		if(age >= 13 && age < 20) {
			ageCode = "TA"; // 청소년
		} else if (age >= 20 && age <= 40) {
			ageCode = "AD"; // 성인
		} else if (age > 40) {
			ageCode = "OD"; // 중장년
		}
		
		
		// 나이코드에 맞는 결과를 담을 리스트
		ArrayList<Program> resultList = new ArrayList<Program>();

		// 나이코드에 맞는 프로그램을 리스트에 저장
		for (Program data : this.pList) {
			// 연령코드랑 일치하면 리스트에 저장
			if (data.getCode().substring(0, 2).equals(ageCode)) {
				resultList.add(data);
			}
		}
	
		while (true) {
			this.pshowList.clear();
			
			if(resultList.size() == 0) { // 결과가 없다면
				System.out.println("연령에 맞는추천 프로그램이 없습니다");
				break;
			} else {
				System.out.println("[번호]  [프로그램 이름]\t\t\t[강사명]    [강의실]    [시작 날짜]\t[종료 날짜]\t[정원]\t  [현재상태]\t[가격]");
				
				// 연령별 프로그램 목록을 출력하고 pshowList에 출력데이터를 담는다
				showProgramList(resultList);
				
				System.out.println();
				
				// 신청 설정하는 메서드
				setApplyProgram(ageCode);
				break;
				
			}
		
		}
		
		
	}
	
	
	/**
	 * 	테마별 추천 리스트를 출력하는 메서드
	 *  
	 */
	private void showThemeResult() {
		while(true) {
			// 사용자가 선택한 번호를 테마코드로 변환해서 가져온다
			String themeCode = getTheme();
			
			if(themeCode != "") {
				// 테마코드에 맞는 결과를 담을 리스트
				ArrayList<Program> resultList = new ArrayList<Program>();

				// 테마코드에 맞는 프로그램을 리스트에 저장
				for (Program data : this.pList) {
					// 테마코드랑 일치하면 리스트에 저장
					if (data.getCode().substring(2, 4).equals(themeCode)) {
						resultList.add(data);
					}
				}
				
				while (true) {
					this.pshowList.clear();
					
					if(resultList.size() == 0) { // 결과가 없다면
						System.out.println("테마에 맞는추천 프로그램이 없습니다");
						break;
					} else {
						System.out.println("[번호]  [프로그램 이름]\t\t\t[강사명]    [강의실]    [시작 날짜]\t[종료 날짜]\t[정원]\t  [현재상태]\t[가격]");
						
						// 테마코드별 프로그램 목록을 출력하고 pshowList에 출력데이터를 담는다
						showProgramList(resultList);
						
						System.out.println();
						
						// 신청 설정하는 메서드
						setApplyProgram(themeCode);
						break;
						
					}
				}
	
			} else {
				// 뒤로가기
				break;
			}
		}
		
	}
	
	/**
	 * 	사용자가 선택한 번호를 테마코드로 변환해서 리턴하는 메서드
	 *  
	 */
	private String getTheme() {
		clear();
		System.out.println("======================================================");
		System.out.println("\t<프로그램 테마 선택하기>");
		System.out.println("======================================================");
		System.out.println("\t1. 요리\t\t2. 스포츠");
		System.out.println("\t3. 언어\t\t4. 건강");
		System.out.println("\t5. 원예\t\t6. 미술");
		System.out.println("\t7. 댄스\t\t8. 악기");
		System.out.println("\t9. 컴퓨터\t0. 뒤로가기");
		System.out.println("======================================================");
		
		System.out.println();
		System.out.print("테마 번호를 선택하세요 :");
		int num = selectNum();
		
		switch (num) {
			case 1: return "01";
			case 2: return "02";
			case 3: return "03";
			case 4: return "04";
			case 5: return "05";
			case 6: return "06";
			case 7: return "07";
			case 8: return "08";
			case 9: return "09";
			case 0: return "";
			default: return ""; 
		}

	}
	
	
	/**
	 * 	월별 프로그램을 출력하는 메서드
	 *  
	 */ 
	private void showMonthResult() {
		while(true) {
			String yearMonth = getYearMonth();
//			System.out.println("yearMonth : " + yearMonth);
//			System.out.println("Plist startDate : " + pList.get(1).getStartDate());
//			System.out.println(pList.get(1).getStartDate().indexOf(yearMonth));
//			pause();
			if(yearMonth != "") {
				// 선택한 년월에 맞는 결과를 담을 리스트
				ArrayList<Program> resultList = new ArrayList<Program>();

				// 선택한 년월에 맞는 프로그램을 리스트에 저장
				for (Program data : this.pList) {
					// 선택한 년월이랑 일치하면 리스트에 저장
					if (data.getStartDate().indexOf(yearMonth) >= 0) {
						resultList.add(data);
					}
				}
				
				while (true) {
					this.pshowList.clear();
					
					if(resultList.size() == 0) { // 결과가 없다면
						System.out.println("해당 월에는 프로그램이 없습니다");
						break;
					} else {
						System.out.println("[번호]  [프로그램 이름]\t\t\t[강사명]    [강의실]    [시작 날짜]\t[종료 날짜]\t[정원]\t  [현재상태]\t[가격]");
						
						// 테마코드별 프로그램 목록을 출력하고 pshowList에 출력데이터를 담는다
						showProgramList(resultList);
						
						System.out.println();
						
						// 신청 설정하는 메서드
						setApplyProgram(yearMonth);
						break;
						
					}
				}
	
			} else {
				// 뒤로가기
				System.out.println("뒤로가기");
//				pause();
				break;
			}
		}
	}
	
	/**
	 * 	사용자가 선택한 번호를 년-월로 변환해서 리턴하는 메서드
	 *  
	 */
	private String getYearMonth() {
		clear();
		System.out.println("=============================================");
		System.out.println("\t<프로그램 시작월 선택하기>");
		System.out.println("=============================================");
		System.out.println("\t1. 2020-12");
		System.out.println("\t2. 2021-01");
		System.out.println("\t3. 뒤로가기");
		System.out.println("=============================================");
		
		System.out.println();
		System.out.print("번호를 선택하세요 :");
		int num = selectNum();
		
		switch (num) {
			case 1: return "2020-12";
			case 2: return "2021-1";
			case 3: return "";			
			default: return ""; 
		}
	}
	
	
	// 페이지 클리어
	private void clearPage() {
		for(int i=0 ; i<15 ; i++) {
			System.out.println();
		}
		
	}

	// 번호를 입력받는 메서드
	private static int selectNum() {
		
		// 사용자에게 번호를 입력받는다
		Scanner scan = new Scanner(System.in);
		return Integer.parseInt(scan.nextLine());
	}
	
	// 일시정지
	private static void pause() {
		Scanner scan = new Scanner(System.in);
		System.out.println("엔터키를 누르면 이전화면으로 돌아갑니다");
		scan.nextLine();
		for(int i=0 ; i<20 ; i++) {
			System.out.println();
		}
	}

	
	// 프로그램.txt에서 데이터를 읽어온다
	private ArrayList<Program> loadProgramData(String path) {
		
		ArrayList<Program> list = new ArrayList<Program>();
		File file = new File(path);
		
		if(file.exists()) {
			
			try {
				BufferedReader reader = new BufferedReader(new FileReader(path));
				
				String line = null;
				
				while((line = reader.readLine()) != null) {
					String[] temp = line.split(",");
					
					list.add(new Program(temp[0]
							, temp[1]
							, temp[2]
							, temp[3]
							, temp[4]
							, temp[5]
							, Integer.parseInt(temp[6])
							, Integer.parseInt(temp[7])));
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
	private ArrayList<ProgramStudent> loadPsData(String path) {
		
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

					list.add(new ProgramStudent(temp[0]
												, Integer.parseInt(temp[1])
												, temp[2]
												, userCodes));
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

	
	private void clear() {
		for(int i=0 ; i<40 ; i++) {
			System.out.println();
		}
	}
}




