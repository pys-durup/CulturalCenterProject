package com.project.center.user;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import data.Path;

public class UserDelete {
	
	private static ArrayList<User> uList = new ArrayList<User>();
	
	UserInfo u = new UserInfo();
	//ArrayList<User> uList = u.uList;
	Scanner scan = new Scanner(System.in);

	
	public void deleteUser() {
		
		
		try {

			boolean flag = false;
			
			
			BufferedWriter writer = new BufferedWriter (new FileWriter(Path.USERLIST, true));
			
			System.out.print("삭제할 회원의 회원번호를 입력하세요. : ");
			String userCode = scan.nextLine();
			
			for (User u : uList) {
				
				System.out.println(u.getCode());
				if (u.getCode().equals(userCode)) {
					writer.write(String.format("%5s %4s %s %s %s %s %s %3s\t%s\r\n"
												,u.getCode()
												,u.getName()
												,u.getBirth()
												,u.getId()
												,u.getPw()
												,u.getGender()
												,u.getTel()
												,u.getGroup()
												,u.getAddress()));
					
					writer.newLine();
					writer.close();
					uList.remove(u);
					flag = true;
					break;
				}
			}
			if (flag == false) {
				System.out.println("회원 코드가 없습니다.");
			}
			
			if (flag == true) {
				saveDeleteUser();
				System.out.println("삭제");
			}

		} catch (Exception e) {
			System.out.println("UserDelete.deleteUser()");
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public void saveDeleteUser() {
		
		try {

			BufferedWriter sWriter = new BufferedWriter (new FileWriter(Path.DELETEUSERLIST));
			
			for (User u : uList) {
				sWriter.write(String.format("%5s %4s %s %s %s %s %s %3s\t%s\r\n"
												,u.getCode()
												,u.getName()
												,u.getBirth()
												,u.getId()
												,u.getPw()
												,u.getGender()
												,u.getTel()
												,u.getGroup()
												,u.getAddress()));
			
				sWriter.newLine();
				sWriter.close();
			}

		} catch (Exception e) {
			System.out.println("UserDelete.savedeleteUser()");
			e.printStackTrace();
		}
	}

}


//			if (UserInfo.uList.get(0).equals(userCode)) {
//				UserInfo.uList.remove(Integer.parseInt(userCode));
//			}