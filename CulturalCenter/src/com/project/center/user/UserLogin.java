package com.project.center.user;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import data.Path;

public class UserLogin {
	
	public void checkUser() {
		
		
		User user = new User();
		
		ArrayList<User> userList = new ArrayList<>();
		
		try {
	
			BufferedReader reader = new BufferedReader(new FileReader(Path.USERLIST));
			
			String line = null;
			
			Scanner scan = new Scanner(System.in);
			
	
			while ((line = reader.readLine()) != null) {
				
				String[] list = line.split(",");
	
			}	
					
			
			System.out.print("아이디 : ");
			String id = scan.nextLine();
			System.out.print("비밀번호 : ");
			String pw = scan.nextLine();
			
			
	//		int idIndex = idList.indexOf(id);
	//		int pwIndex = pwList.indexOf(pw);
	//				
	//		String checkid = idList.substring(idIndex, idIndex + id.length());
	//		String checkpw = pwList.substring(pwIndex, pwIndex + pw.length());
	//		
	//		if (id.equals(checkid)) {
	//			if(pw.equals(checkpw)) {
	//				System.out.println("통과");			
	//			} else {
	//				System.out.println("비밀번호가 틀렸습니다.");
	//			}
	//		} else {
	//			System.out.println("아이디가 틀렸습니다.");
	//		}
			
			
			
			reader.close();
	
		} catch (Exception e) {
			System.out.println("UserLogin.main()");
			e.printStackTrace();
		}
		
	}
	
}

