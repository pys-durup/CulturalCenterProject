package com.project.center.user;

import java.util.Scanner;

public class UserManage {
	
	public static void main(String[] args) {

		
		while(true)	{
			
			showMain(); //회원관리 메인창 출력
			
			// 사용자가 입력한 숫자를 받아온다
			int Num = selectNum(); 
			
			if(Num == 1) {
				//System.out.println("\t1. 회원 목록 조회");
<<<<<<< Updated upstream
				UserInfo uInfo = new UserInfo(); 
=======
				UserInfo uInfo = new UserInfo();
>>>>>>> Stashed changes
				uInfo.getUserInfo();
				uInfo.viewUserList();
				break;
				
			} else if(Num == 2) { 
				System.out.println("\t2. 회원 정보 검색");
				//UserSelect()
				break;
				
			} else if(Num == 3) { 
				System.out.println("\t3. 회원 정보 수정");
				//UserUpdate()
				break;
				
			} else if(Num == 4) { 
				System.out.println("\t4. 회원 정보 삭제");
				UserDelete dUser = new UserDelete();
				dUser.deleteUser();
				//UserDelete()
				break;
				
			} else {
				pause();
			}
			
		}
		
		
		System.out.println("프로그램 진행 . . . .");
	
		
		
		
	} //main
	
	
	// 프로그램의 메인 화면을 출력하는 메서드
	public static void showMain() {
		
		System.out.println("\n\t   [회원 관리]\n");
		System.out.println("\t1. 회원 목록 조회");
		System.out.println("\t2. 회원 정보 검색");
		System.out.println("\t3. 회원 정보 수정");
		System.out.println("\t4. 회원 정보 삭제");
		                                                                                    
	}

	
	// 번호를 입력받는 메서드
	private static int selectNum() {
		
		// 사용자에게 번호를 입력받는다
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.print("번호를 선택하세요 : ");
		return Integer.parseInt(scan.nextLine());
		
	}
	

	// 일시정지
	public static void pause() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("\n 엔터키를 누르면 이전화면으로 돌아갑니다...");
		scan.nextLine();
		for(int i=0 ; i<10 ; i++) {
			System.out.println();
		}
		
	}
	
	

}//UserManage
