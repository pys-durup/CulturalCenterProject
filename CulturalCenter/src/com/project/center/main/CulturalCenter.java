package com.project.center.main;

import java.util.Scanner;

import com.project.center.program.ProgramManage;
import com.project.center.program.ProgramRegistrationList;
import com.project.center.user.User;
import com.project.center.user.UserLogin;
import com.project.center.user.UserRegister;

public class CulturalCenter {
	
	public static void main(String[] args) {
		
		// 프로그램 기능 테스트용 객체
		User user = new User("7948", "박영수", "1993-08-17","tteesstt", "tteesstt",  "1", "01077743635",  "1" , "주소");
		// 유저, 직원, 관리자
		
		while(true)	{
			int userType = 1; // 1 = 회원 2 = 직원 3 = 관리자
			int Num;
			
			// 메인화면 1. 로그인 2.회원가입	3.종료
			showMain();
			
			// 사용자가 입력한 숫자를 받아온다
			Num = selectNum(); 
			
			if(Num == 1) { // 로그인
				System.out.println("로그인 선택");
				UserLogin.checkUser();
				// 로그인처리 메서드
				// public void isLogin()? 로그인처리?
				// public void createAccount(){} 로그인 성공하면?
				// 로그인 완료후 프로그램 진행
				
				
			} else if(Num == 2) { // 회원가입
				System.out.println("회원가입 선택");
				UserRegister.infoRegister();
				// 회원가입처리 메서드
				// 회원 가입 완료후 다시 메인화면으로
			} else if(Num == 3) {
				// 아이디/비밀번호 찾기 메서드
				
				
				
			} else if(Num == 4) { // 종료
				// 프로그램 종료
				System.out.println("프로그램을 종료합니다");
				System.exit(0); 
			} else {
				// 다른 숫자 입력시
				pause();
			}
			
			System.out.println("프로그램 진행 . . . .");
			
			
			while (true) {
				
				
				// 1. 회원일때 2. 직원일때 3. 관리자 일때
				if(userType == 1) {
					// 회원에게 보여질 메뉴 출력
					showUserMain();
					Num = selectNum();
					
					if(Num == 1) {
						System.out.println("회원정보 조회");
						
					} else if (Num == 2) {
						System.out.println("프로그램 신청");
						ProgramManage pm = new ProgramManage();
						pm.createApplyProgram(user);
						
					} else if (Num == 3) {
						System.out.println("프로그램 등록현황");
						ProgramRegistrationList pr = new ProgramRegistrationList(user);
						pr.createProgramRegistorList();

						
					} else if (Num == 4) {
						System.out.println("시설예약");
						
					} else if (Num == 5) {
						System.out.println("시설예약 확인");
						
					} else if (Num == 6) {
						System.out.println("마일리지");
						
					} else if (Num == 7) {
						System.out.println("진행중 이벤트");
						
					} else if (Num == 8) {
						System.out.println("공지사항");
					} else if (Num == 9) {
						System.out.println("로그아웃");
						break;
					} else {
						pause();
					}
					
				} else if(userType == 2) {
					// 직원에게 보여질 메뉴 출력
				} else if(userType == 3) {
					// 관리자이게 보여질 메뉴 출력
				}
			}
		}
		
		
			
		
	} //main
	
	// 번호를 입력받는 메서드
	private static int selectNum() {
		
		// 사용자에게 번호를 입력받는다
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.print("번호를 선택하세요 : ");
		return Integer.parseInt(scan.nextLine());
		
	}
	
	// 일시정지
	private static void pause() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("엔터키를 누르면 이전화면으로 돌아갑니다.");
		scan.nextLine();
		for(int i=0 ; i<10 ; i++) {
			System.out.println();
		}
		
	}
	
	// 프로그램 로고를 출력하는 메서드
	public static void showlogo() {
		
		System.out.println(" _____         _  _                        _   _____               _               \r\n"
				+ "/  __ \\       | || |                      | | /  __ \\             | |              \r\n"
				+ "| /  \\/ _   _ | || |_  _   _  _ __   __ _ | | | /  \\/  ___  _ __  | |_   ___  _ __ \r\n"
				+ "| |    | | | || || __|| | | || '__| / _` || | | |     / _ \\| '_ \\ | __| / _ \\| '__|\r\n"
				+ "| \\__/\\| |_| || || |_ | |_| || |   | (_| || | | \\__/\\|  __/| | | || |_ |  __/| |   \r\n"
				+ " \\____/ \\__,_||_| \\__| \\__,_||_|    \\__,_||_|  \\____/ \\___||_| |_| \\__| \\___||_|   ");
		
	
	}
	
	// 프로그램의 메인 화면을 출력하는 메서드
	public static void showMain() {
		showlogo();
		System.out.println();
		System.out.println();
		System.out.println("\t\t1. 로그인\t2.회원가입\t3.아이디/비밀번호 찾기\t4.종료");
		                                                                                    
	}
	
	// 유저 회원의 메뉴를 출력하는 메서드
	public static void showUserMain() {
		
		System.out.println();
		System.out.println("1. 회원정보 조회\t2. 프로그램 신청");
		System.out.println("3. 프로그램 등록현황\t4. 시설예약");
		System.out.println("5. 시설예약 확인\t\t6. 마일리지");
		System.out.println("7. 진행중 이벤트\t8. 공지사항");
		System.out.println("9. 로그아웃");
		
	}
	
	// 직원 회원의 메뉴를 출력하는 메서드
	public static void showEmployeeMain() {
		
	}
	
	// 직원 회원의 메뉴를 출력하는 메서드
	public static void showManageMain() {
		
	}

} // class 

