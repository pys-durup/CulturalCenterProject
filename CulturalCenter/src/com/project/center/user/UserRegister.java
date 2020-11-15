package com.project.center.user;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

import data.Path;

public class UserRegister {
		
	public void insertUser() {
	
			User user = new User();

			try {

				BufferedWriter writer = new BufferedWriter(new FileWriter(Path.USERLIST, true));
				
				Scanner scan = new Scanner(System.in);
				
				System.out.println("==============회원 가입==============");
				
				//제일 마지막 코드를 불러서 읽어온다음 그 코드의 다음 수도 추가
				
				System.out.print("이    름 : ");
				String name = scan.nextLine();
				
				System.out.print("아 이 디 : ");
				String id = scan.nextLine();
				
				System.out.print("비밀번호 : ");
				String pw = scan.nextLine();
				
				System.out.print("생년월일 : ");
				String birth = scan.nextLine();
				
				System.out.print("성    별 (1.남 2.여) : ");
				String gender = scan.nextLine();
				
				System.out.print("전화번호 : ");
				String tel = scan.nextLine();
				
				System.out.print("주    소 : ");
				String address = scan.nextLine();
				
				System.out.println("1.일반 2.기초생활수급자 3.차상위계층");
				System.out.print("분    류 : ");
				String group = scan.nextLine();
				
				writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s\r\n"
											,name ,id , pw, birth, gender, tel, address, group));
				
				System.out.println("=====================================");
				
				System.out.println("회원가입이 완료되었습니다.!");
				
				
				writer.close();
				

			} catch (Exception e) {
				System.out.print("UserRegister.main()");
				e.printStackTrace();
			}
			
			
		}

}
