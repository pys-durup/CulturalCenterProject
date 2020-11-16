package com.project.center.user;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import data.Path;

public class UserLogin {
	
	public static void checkUser() {
		
		Scanner scan = new Scanner(System.in);
		
		User user = new User(null, null, null, null, null, null, null, null, null);
		
		try {

			BufferedReader reader = new BufferedReader(new FileReader(Path.USERLIST));
			
			String line = null;
			
			System.out.print("아이디 : ");
			String id = scan.nextLine();
			
			System.out.print("비밀번호 : ");
			String pw = scan.nextLine();
			
			String idList = "";
			String pwList = "";
			while ((line = reader.readLine()) != null) {
				String[] list = line.split(",");
				idList = list[3];
				pwList = list[4];
				if (id.equals(idList) && pw.equals(pwList)) {
					user = new User(list[0], list[1], list[2], list[3], list[4], list[5], list[6], list[7], list[8]);
					System.out.println("로그인 성공");
					break;
				} 
			}
			
//			public static void userselect(User user) {
//				
//				System.out.println(user.getName());
//				
//			}
			
			if (!id.equals(idList) || !pw.equals(pwList)) {
				System.out.println("로그인 실패");
			}
			
			reader.close();
			

		} catch (Exception e) {
			System.out.println("UserLogin.checkUser()");
			e.printStackTrace();
		}
		
	}

}
