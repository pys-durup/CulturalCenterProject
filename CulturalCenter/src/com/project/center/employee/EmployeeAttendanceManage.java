package com.project.center.employee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Scanner;

import data.Path;

public class EmployeeAttendanceManage {
	
	private boolean check = false;
	
	EmployeeAttendance employee = new EmployeeAttendance();
	
	private String code = "";
	private String name = "";
	
	public void checkEmployeeAccess() {
		
		Scanner scan = new Scanner(System.in);
		
		try {

			BufferedReader reader = new BufferedReader(new FileReader(Path.EMPLOYEELIST));
			
			String line = null;
			String[] list = new String[6];
			
			System.out.println("============================[근태 기록]===========================");
			System.out.println();
			System.out.println("근태 기록을 위해, 사원코드와 비밀번호를 다시 입력해주세요.");
			System.out.println();
			System.out.print("사원코드 :");
			String inputCode = scan.nextLine();
			System.out.println();
			System.out.print("비밀번호 :");
			String inputPassword = scan.nextLine();
			System.out.println();
			
			while ((line = reader.readLine()) != null) {
				list = line.split(",");
				if (inputCode.equals(list[0]) && inputPassword.equals(list[3])) {
					this.check = true;
					
					employee.setCode(list[0]);
					employee.setName(list[1]);
				}
			}
			
			reader.close();
			
		} catch (Exception e) {
			System.out.println("EmployeeAttendanceManage.checkEmployeeAccess()");
			e.printStackTrace();
		}
		
		
	} //checkEmployeeAccess()
	
	public void viewEmployeeAttendance() {
		
		Scanner scan = new Scanner(System.in);
		
		
		System.out.println("============================[근태 기록]===========================");
		System.out.println();
		System.out.printf("%s님, 반갑습니다. 원하는 메뉴 번호를 입력해주세요.", this.name);
		System.out.println();
		System.out.println("1. 출근 기록");
		System.out.println();
		System.out.println("2. 퇴근 기록");
		System.out.println();
		System.out.println("3. 메인 메뉴로 돌아가기");
		System.out.println();
		System.out.print("메뉴 입력 :");
		String inputMenu = scan.nextLine();
		System.out.println();
		
		switch(inputMenu) {
		case "1":
			setEmployeeAttendanceByClockIn();
			break;
		case "2":
			updateEmployeeAttendance();
			break;
		case "3":
			mainTo();
			break;
		default:
			System.out.println("직원 관리를 종료합니다.");
			break;
		}
	}

	private void setEmployeeAttendanceByClockIn() {
		
		Calendar today = Calendar.getInstance();
		String[] list = new String[6];
		employee.setDate(String.format("%tF", today));
		employee.setCommuteTime(String.format("%tT", today));
		
		try {

			BufferedReader reader = new BufferedReader(new FileReader(Path.EMPLOYEEATTENDANCE));
			
			String line = null;
			String dummy = "";
			boolean isRenewal = false;
			
			while ((line = reader.readLine()) != null) {
				list = line.split(",");
				if (employee.getDate().equals(list[2]) && list[0].equals(this.code)) {
					
					isRenewal = true;
					
				} else {
					dummy += line + "\r\n";
					
				}
			}
			
			reader.close();
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(Path.EMPLOYEEATTENDANCE));
			
			dummy += String.format("%s, %s, %s, %s, %s ,%s"
									, employee.getCode()
									, employee.getName()
									, employee.getDate()
									, employee.getCommuteTime()
									, employee.getWorkingTime()
									, employee.getAttendance());
			
			
			if (isRenewal) {
				writer.write(dummy);
				System.out.printf("%d월 %d일의 출근 시각이 갱신되었습니다."
						, today.get(Calendar.MONTH) + 1
						, today.get(Calendar.DATE)
						);
			} else {
				dummy = null;
				employee.setEmployeeAttendanceIntoData();
				
				System.out.printf("%d월 %d일의 출근 시각이 기록되었습니다."
						, today.get(Calendar.MONTH) + 1
						, today.get(Calendar.DATE)
						);
			}
			
			writer.close();
			
		} catch (Exception e) {
			System.out.println("EmployeeAttendanceManage.setEmployeeAttendanceByClockIn()");
			e.printStackTrace();
		}
		
		
	}

	private void updateEmployeeAttendance() {
		
	}

	private void mainTo() {
		
	}

}
