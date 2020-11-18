package com.project.center.user;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import data.Path;

public class UserLogin {
	
	public static User checkUser() {
		
		Scanner scan = new Scanner(System.in);
		
		User manage = new User(null, null);
		User employee = new User(null, null);
		User user = new User(null, null, null, null, null, null, null, null, null);
		
		try {

			BufferedReader manageList = new BufferedReader(new FileReader(Path.MASTER));
			BufferedReader employeeList = new BufferedReader(new FileReader(Path.EMPLOYEELIST));
			BufferedReader userList = new BufferedReader(new FileReader(Path.USERLIST));			
						
			String line = null;
			
			System.out.print("아이디 : ");
			String id = scan.nextLine();
			
			System.out.print("비밀번호 : ");
			String pw = scan.nextLine();
			
			//idList와 pwList 받아오기
			String idList = "";
			String pwList = "";
			
			//관리자 로그인
			while((line = manageList.readLine()) != null) {
				String[] list = line.split(",");
				if (id.equals(list[0]) && pw.equals(list[1])) {
					manage = new User(list[0], list[1]);
					manage.setType(3);
					manageList.close();
					employeeList.close();
					userList.close();
					return manage;
				}
			}
			
			//직원 로그인
			while((line = employeeList.readLine()) != null) {
				String[] list = line.split(",");
				if (id.equals(list[0]) && pw.equals(list[1])) {
					employee = new User(list[0], list[1]);
					employee.setType(2);
					manageList.close();
					employeeList.close();
					userList.close();					
					return employee;
				}
			}
			
			
			//회원 로그인
			while ((line = userList.readLine()) != null) {
				String[] list = line.split(",");
				idList = list[3];
				pwList = list[4];
				
				//id와 pw의 정보가 맞다면 if문 실행
				if (id.equals(idList) && pw.equals(pwList)) {
					
					//로그인한 User의 정보를 받아서 담기
					user = new User(list[0], list[1], list[2], list[3], list[4], list[5], list[6], list[7], list[8]);
					user.setType(1);
					System.out.println("로그인 성공");
					manageList.close();
					employeeList.close();
					userList.close();
					return user;
				} 
			}
			
			//id나 pw가 다르면 다시 메인화면으로
			if (!id.equals(idList) || !pw.equals(pwList)) {
				System.out.println("로그인 실패");
				userList.close();
				return null;
			}
			
			
			

		} catch (Exception e) {
			System.out.println("UserLogin.checkUser()");
			e.printStackTrace();
		}
		
		return null;
		
	}

}
