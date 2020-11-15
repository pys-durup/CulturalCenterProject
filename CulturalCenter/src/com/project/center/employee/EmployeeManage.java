package com.project.center.employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import data.Path;

public class EmployeeManage {

	private boolean check = false;
	Scanner scan = new Scanner(System.in);
	
	public void checkEmployeeManage() {
		
		check = false;
		
		try {

			BufferedReader reader = new BufferedReader(new FileReader(Path.MASTER));
			
			String[] masterList = reader.readLine().split(",");
			String masterID = masterList[0];
			String masterPassword = masterList[1];
			
			System.out.println("==========================[직원 관리]==========================");
			System.out.println();
			System.out.printf("\t직원 관리 메뉴에 접근하기 위해, 관리자 아이디와\n"
					+ "\t\t비밀번호를 다시 입력해주세요.\n");
			System.out.println();
			System.out.print("관리자 아이디 :");
			String inputID = scan.nextLine();
			System.out.print("관리자 비밀번호 :");
			String inputPassword = scan.nextLine();
			System.out.println();
			
			if (inputID.equals(masterID) && inputPassword.equals(masterPassword)) {
				check = true;
			} else {
				System.out.println();
				System.out.println("\t\t\t*** 경고 ***\t\t");
				System.out.println("올바르지 않은 관리자 아이디 혹은 비밀번호를 입력하셨습니다!");
				System.out.println("\t  엔터를 눌러 메인화면으로 이동해주세요.");
				System.out.println("\t\t\t*** 경고 ***\t\t");
				scan.nextLine();
				check = false;
			}

		} catch (Exception e) {
			System.out.println("EmployeeManage.checkEmployeeManage()");
			e.printStackTrace();
		}
		
	}//유효성검사 메서드
	
	public void viewEmployeeManage() {
		
		while (check) {
			
			System.out.println("==========================[직원 관리]==========================");
			System.out.println();
			System.out.println("관리자님, 반갑습니다. 원하시는 메뉴를 입력해주세요.");
			System.out.println();
			System.out.println("1. 직원등록   2. 직원 정보 수정\n"
					+ "3. 직원 정보 조회   4. 메인 메뉴로 돌아가기");
			System.out.println();
			System.out.print("메뉴 입력 :");
			String inputMenu = scan.nextLine();
			
			switch(inputMenu) {
				case "1":
					insertEmployee();
					break;
				case "2":
					updateEmployee();
					break;
				case "3":
					findEmployee();
					break;
				default:
					System.out.println("직원 관리를 종료합니다.");
					break;
			}
			
			
		}
		
	}

	private void findEmployee() {
	}

	private void updateEmployee() {
	}

	private void insertEmployee() {
	}
	
	
	
	
}//직원관리 메서드
