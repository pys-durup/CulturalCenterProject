package com.project.center.user;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.center.main.CulturalCenter;

import data.Path;

public class UserFind {
	
	static ArrayList<User> userList = new ArrayList<User>(); 
	
	public static void findUser() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("=================목록=================");
		System.out.println("\t   1. 아 이 디 찾기");
		System.out.println("\t   2. 비밀번호 찾기");
		System.out.println("\t   3. 뒤 로 가 기");
		System.out.println("======================================");
		System.out.print("번호를 선택하세요 : ");
		int num = scan.nextInt();
		
		if (num == 1) {
			findUserId();
		} else if (num == 2) {
			findUserPw();
		} else if (num == 3) {
			CulturalCenter.main(null);			
		}
		
		
	}

	private static void findUserId() {
		
		Scanner scan = new Scanner(System.in);
		
		findUserList();
		
		System.out.println("=================아 이 디 찾기=================");
		System.out.println("찾고자 하는 아이디의 회원 정보를 입력해주세요.");
		System.out.print("이     름 : ");
		String name = scan.nextLine();
		System.out.print("생년 월일 : ");
		String birth = scan.nextLine();
		
		String findName = "";
		String findBirth = "";
		String findId = "";
		
		if (birth.length() == 8) {
			birth = String.format("%s-%s-%s", birth.substring(0, 4), birth.substring(4, 6), birth.substring(6, 8));
		}
		
		for (int i = 0; i < userList.size(); i++) {
			if (name.equals(userList.get(i).getName()) && birth.equals(userList.get(i).getBirth())) {
				findName = userList.get(i).getName();
				findBirth = userList.get(i).getBirth();
				findId = userList.get(i).getId();
				if (birth.length() == 8) {
					findBirth = String.format("%s-%s-%s", birth.substring(0, 4), birth.substring(4, 6), birth.substring(6, 8));
				}
				break;
			}
		}
		
		
		if (name.equals(findName) && birth.equals(findBirth)) {
			System.out.println("================================================");
			System.out.printf("[%s] 님의 아이디는 [%s] 입니다.\n", findName, findId);
			System.out.println("================================================");
		} else {
			System.out.println("========================================");
			System.out.println("입력하신 정보와 동일한 정보가 없습니다.");
			System.out.println("========================================");
		}
		
		CulturalCenter.main(null);
		
	}
	
	private static void findUserPw() {
		
		Scanner scan = new Scanner(System.in);
		
		findUserList();
		
		System.out.println("==================비밀번호 찾기=================");
		System.out.println("찾고자 하는 비밀번호의 회원 정보를 입력해주세요.");
		System.out.print("아 이 디 : ");
		String id = scan.nextLine();
		System.out.print("전화번호 : ");
		String tel = scan.nextLine();
		
		String findId = "";
		String findTel = "";
		String findPw = "";
		
		if (tel.length() == 13) {
			tel = tel.replace("-", "");
		}
		
		for (int i = 0; i < userList.size(); i++) {
			if (id.equals(userList.get(i).getId()) && tel.equals(userList.get(i).getTel())) {
				findId = userList.get(i).getId();
				findTel = userList.get(i).getTel();
				findPw = userList.get(i).getPw();
			}
		}
		
		
		if (id.equals(findId) && tel.equals(findTel)) {
			System.out.println("================================================");
			System.out.printf("[%s] 의 비밀번호는 [%s] 입니다.\n", findId, findPw);
			System.out.println("================================================");
		} else {
			System.out.println("========================================");
			System.out.println("입력하신 정보와 동일한 정보가 없습니다.");
			System.out.println("========================================");
		}
		
		CulturalCenter.main(null);
		
		
	}

	private static void findUserList() {
		
		
		userList = new ArrayList<User>();
		
		
		try {

			User userData = new User(null, null, null, null, null, null, null, null, null);
			
			BufferedReader reader = new BufferedReader(new FileReader(Path.USERLIST));
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				String[] list = line.split(",");
				
				userData = new User(list[0], list[1], list[2], list[3], list[4], list[5], list[6], list[7], list[8]);
				
				userList.add(userData);
				
			}
			
			
			
		} catch (Exception e) {
			System.out.println("UserFind.fineUserId()");
			e.printStackTrace();
		}
	}
}



















