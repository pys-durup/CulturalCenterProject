package com.project.center.employee;

import java.io.BufferedReader;
import java.io.FileReader;

import com.project.center.user.UserLogin;

import data.Path;

public class EmployeeLogin {
	
	//본 클래스는 UserLogin 클래스에 이식되는 것을 염두에 두고 작성되었습니다.

	//회원 id는 가입 시 반드시 첫 문자가 영어 소문자인에 반해, 직원의 사원코드는
	//모두 숫자로 이루어진 문자열이다. 이 점을 이용하여 ...
	//아래 메서드는 첫 문자가 숫자일 때, true를 반환한다.
	
	//입력한 아이디가 직원의 것인지 알아내는 메서드.
	public boolean isEmployee(String id) {
		
		int firstCharCode = (int)id.charAt(0);
		
		if (firstCharCode >= 48 && firstCharCode <= 57) {
			return true;
		} else {
			return false;
		}

	}
	
	//직원 로그인 메서드
	//매개변수받은 아이디와 비밀번호를 직원 정보의 것과 비교하여
	//같은 것이 하나라도 있다면, true를 반환. 같은 것이 하나도 없다면 false를 반환.
	public boolean employeeLogin(String id, String pw) {
		
		try {

			BufferedReader reader = new BufferedReader(new FileReader(Path.EMPLOYEELIST));
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				String[] list = line.split(",");
				
				if (id.equals(list[0]) && pw.equals(list[3])) {
					return true;
				}
			}
			reader.close();

		} catch (Exception e) {
			System.out.println("EmployeeLogin.employeeLogin()");
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
	
	//활용 제안
	
	
	//checkUser() 메서드를 userType인 1, 2, 3을 반환하도록 하자!
	// userType은 CulturalCenter 클래스(메인)에서 선언되었다.
	// - 1 = 회원, 2 = 직원, 3 = 관리자.
	
	//1. checkUser() 메서드 반환 자료형 바꾸기!
	// - public static void checkUser() {}
	// 	 -> public static int checkUser() {}
	
	
	//2. 버퍼드 리더와 isEmployee()메서드로 직원과 관리자를 구별해내자!
	// - UserLogin.java 중에서..

// (이상 생략)...
//
//	System.out.print("아이디 : ");
//	String id = scan.nextLine();
//	
//	System.out.print("비밀번호 : ");
//	String pw = scan.nextLine();
//
	
	// 이 부분을 추가!
	/*
	BufferedReader bossReader = new BufferedReader (new FileReader(Path.MASTER)); 
	String[] boss = reader.readLine().split(","); //boss[0] == master
												  //boss[1] == imtheboss123
	
	if (id.equals(boss[0]) && pw.equals(boss[1])) { 
		bossReader.close();
		return 3;
	}
	bossReader.close();
	
 	if (isEmployee(id)) {
 		
 		if (employeeLogin(id, pw)) {
 			return 2;
 		}
 	}	
	 */
	// 
	
//
//	String idList = "";
//	String pwList = "";
//
// ...(이하 생략)

	

	//이 제안대로 하면...
	
	//1. CulturalCenter 클래스의
	
		//if(Num == 1) { // 로그인
		//System.out.println("로그인 선택");
		//UserLogin.checkUser();
	
		//부분을
	
		//if(Num == 1) { // 로그인
		//System.out.println("로그인 선택");
		//userType = UserLogin.checkUser();
	
		//로 바꾸어야 합니다.
	
	//2. EmployeeAttendanceManage 클래스를 소폭 수정해야합니다.
	
}
