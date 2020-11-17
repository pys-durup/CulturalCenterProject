package com.project.center.employee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;

import data.Path;

public class EmployeeAttendanceManage {
	
	private boolean check = false;
	
	EmployeeAttendance employee = new EmployeeAttendance();
	
	private String code = "";
	private String name = "";

	public boolean isEmployeeAccess() {
		
		employee.setCode("");
		employee.setName("");
		
		Scanner scan = new Scanner(System.in);
		
		try {

			BufferedReader reader = new BufferedReader(new FileReader(Path.EMPLOYEELIST));
			
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
			
			String line = null;
			String[] list = new String[6];
			
			while ((line = reader.readLine()) != null) {
				list = line.split(",");
				if (inputCode.equals(list[0]) && inputPassword.equals(list[3])) {
					check = true;
					
					employee.setCode(list[0]);
					employee.setName(list[1]);
					break;
				} else {
					check = false;
					employee.setCode("");
					employee.setName("");
				}
			}
			
			reader.close();
			
			if (check == false) {
				mainToByFailure();
				return false;
			}
			
		} catch (Exception e) {
			System.out.println("EmployeeAttendanceManage.checkEmployeeAccess()");
			e.printStackTrace();
		}
		
		return true;
		
	} //checkEmployeeAccess()

	private void mainToByFailure() {
		Scanner scan = new Scanner(System.in);
		System.out.println("\t\t\t*** 경고 ***\t\t");
		System.out.println("\t잘못된 정보를"
						+ " 입력했습니다!");
		System.out.println("\t  엔터를 눌러 메인화면으로"
						+ " 이동해주세요.");
		System.out.println("\t\t\t*** 경고 ***\t\t");
		System.out.println();
		scan.nextLine();
	}
	
	public void viewEmployeeAttendance() {

		if (check) {
			
			Scanner scan = new Scanner(System.in);
			
			
			System.out.println("============================[근태 기록]===========================");
			System.out.println();
			System.out.printf("%s님, 반갑습니다. 원하는 메뉴 번호를 입력해주세요.", employee.getName());
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
				System.out.println("근태 기록을 종료합니다.");
				break;
			}
			
		} else {
			mainToByFailure();
		}
		
	}

	private void mainTo() {
	}

	private void setEmployeeAttendanceByClockIn() {
		
		Calendar today = Calendar.getInstance();
		String[] list = new String[6];
		employee.setDate(String.format("%tF", today));
		employee.setCommuteTime(String.format("%tk:%tM", today, today));
		
		try {

			BufferedReader reader = new BufferedReader(new FileReader(Path.EMPLOYEEATTENDANCE));
			
			String line = null;
			String dummy = "";
			boolean isRenewal = false;
			
			while ((line = reader.readLine()) != null) {
				list = line.split(",");
				if (employee.getDate().equals(list[2]) && list[0].equals(this.employee.getCode())) {
					
					isRenewal = true;
					
				} else {
					dummy += line + "\r\n";
					
				}
			}
			
			reader.close();
			
			dummy += String.format("%s,%s,%s,%s,%s,%s"
									, employee.getCode()
									, employee.getName()
									, employee.getDate()
									, employee.getCommuteTime()
									, employee.getWorkingTime()
									, employee.getAttendance());
			
			if (isRenewal) {
				
				try {

					BufferedWriter writer = new BufferedWriter(new FileWriter(Path.EMPLOYEEATTENDANCE));

					writer.write(dummy);
					
					writer.close();
					
				} catch (Exception e) {
					System.out.println("EmployeeAttendanceManage.setEmployeeAttendanceByClockIn()");
					e.printStackTrace();
				}
				
				System.out.printf("%d월 %d일의 출근 시각이 갱신되었습니다."
						, today.get(Calendar.MONTH) + 1
						, today.get(Calendar.DATE)
						);
				System.out.println();
				
			} else {
				
				dummy = null;
				employee.setEmployeeAttendanceIntoData();
				
				System.out.printf("%d월 %d일의 출근 시각이 기록되었습니다."
						, today.get(Calendar.MONTH) + 1
						, today.get(Calendar.DATE)
						);
				System.out.println();
			}
			
			
		} catch (Exception e) {
			System.out.println("EmployeeAttendanceManage.setEmployeeAttendanceByClockIn()");
			e.printStackTrace();
		}
		
		
	}

	private void updateEmployeeAttendance() {
		
		Calendar clockOut = Calendar.getInstance();
		Scanner scan = new Scanner(System.in);
		boolean isClockOut = false;
		boolean check = false;
		
		try {
			String line = null;
			String[] list = new String[6];
					
			BufferedReader reader = new BufferedReader(new FileReader(Path.EMPLOYEEATTENDANCE));
					
			String[] clockInMins = new String[2];
			int clockOutMins = 60 * clockOut.get(Calendar.HOUR_OF_DAY)
					+ clockOut.get(Calendar.MINUTE);
			int workingMins = 0;
			
			while ((line = reader.readLine()) != null) {
				
				list = line.split(",");
				
				if (employee.getCode().equals(list[0]) 
						&& String.format("%tF", clockOut).equals(list[2])
						&& list[5].equals("결근")) {
					
					clockInMins = list[3].split(":");
					workingMins = clockOutMins 
							- (60 * Integer.parseInt(clockInMins[0])
									+ Integer.parseInt(clockInMins[1]));
					employee.setCommuteTime(list[3]);
					employee.setDate(list[2]);
					employee.setWorkingTime(String.format("%02d:%02d"
							, workingMins / 60
							, workingMins % 60)
							);
					employee.setAttendance(
							workingMins < 480 ? "조퇴" : "출근"
							);
					
					employee.setEmployeeAttendanceIntoData();
					
					System.out.printf("%s님, %s년 %s월 %s일 %s시에 퇴근 기록되었습니다.\n"
							, employee.getName()
							, clockOut.get(Calendar.YEAR)
							, clockOut.get(Calendar.MONTH) + 1
							, clockOut.get(Calendar.DATE)
							, clockOut.get(Calendar.HOUR_OF_DAY)
							);
					
					deleteFormal();
					
					check = true;
					System.out.println("엔터를 누르시면, 메인 메뉴로 돌아갑니다.");
					scan.nextLine();
					break;
					
				} 
				
			}
			
			if(check == false) {
				System.out.println("오늘 출근한 기록이 없습니다!");
				System.out.println();
				System.out.println("\t  엔터를 눌러 메인화면으로"
						+ " 이동해주세요.");
				System.out.println("\t\t\t*** 경고 ***\t\t");
				System.out.println();
				scan.nextLine();
			}
			
			reader.close();
					
		} catch (Exception e) {
			System.out.println("EmployeeAttendanceManage.updateEmployeeAttendance()");
			e.printStackTrace();
		}
		
	}
	
	private void deleteFormal() {
		
		Calendar c = Calendar.getInstance();
		
		try {

			BufferedReader reader = new BufferedReader(new FileReader(Path.EMPLOYEEATTENDANCE));
			String dummy = "";
			String line = null;
			String[] list = new String[6];
			
			while ((line = reader.readLine()) != null) {
				list = line.split(",");
				if (list[4].equals("99:99") 
						&& employee.getCode().equals(list[0]) 
						&& employee.getDate().equals(String.format("%tF", c))) {
				} else {
					dummy += line + "\r\n";
					
				}
				
			}
			
			reader.close();
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(Path.EMPLOYEEATTENDANCE));
			
			writer.write(dummy.substring(0, dummy.length() - 2));
			
			writer.close();

		} catch (Exception e) {
			System.out.println("EmployeeAttendanceManage.deleteFormal()");
			e.printStackTrace();
		}
		
	}
	
	public void findEmployeeAttendanceList() {
		
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		while (loop) {
			
			System.out.println("============================[근태 조회]===========================");
			System.out.println("관리자님, 반갑습니다.");
			System.out.println("근태 상황을 조회할 직원의 사원코드를 입력해주세요.");
			System.out.println();
			System.out.println("아무것도 입력하지 않으시고 엔터를 입력하시면 메인메뉴로 돌아갑니다.");
			System.out.println();
			System.out.print("사원 코드 입력 :");
			String inputCode = scan.nextLine();
			System.out.println();
			
			try {
				
				BufferedReader reader = new BufferedReader(new FileReader(Path.EMPLOYEEATTENDANCE));
				
				String line = null;
				String[] list = new String[6];
				boolean failureCheck = true;
				
				while ((line = reader.readLine()) != null) {
					list = line.split(",");
					if (inputCode.equals(list[0])) {
						viewEmployeeAttendanceList(inputCode);
						
						failureCheck = false;
						loop = false;
						break;
					}
				}
				
				if (failureCheck) {
					System.out.println("잘못된 사원코드를 입력하셨습니다!");
					System.out.println("엔터를 누르시면, 메인 메뉴로 돌아갑니다.");
					scan.nextLine();
				}
				
				reader.close();
				
			} catch (Exception e) {
				System.out.println("EmployeeAttendanceManage.findEmployeeAttendanceList()");
				e.printStackTrace();
			}
			
		}
		
	}

	private void viewEmployeeAttendanceList(String inputCode) {
		
		Scanner scan = new Scanner(System.in);
		EmployeeAttendance employeeAttendance = new EmployeeAttendance();
		
		
		try {
			
			ArrayList<EmployeeAttendance> attendanceBoard = new ArrayList<EmployeeAttendance>();
			
			BufferedReader reader2 = new BufferedReader(new FileReader(Path.EMPLOYEEATTENDANCE));
			
			String line = null;
			String[] list = new String[6];
			int m = 0;
			
			while ((line = reader2.readLine()) != null) {
				list = line.split(",");
				System.out.println(inputCode);
				System.out.println(list[0]);
				if (inputCode.equals(list[0])) {
					employeeAttendance.setCode(list[0]);
					employeeAttendance.setName(list[1]);
					employeeAttendance.setDate(list[2]);
					employeeAttendance.setCommuteTime(list[3]);
					employeeAttendance.setWorkingTime(list[4]);
					employeeAttendance.setAttendance(list[5]);
					attendanceBoard.add(employeeAttendance);
					employeeAttendance = new EmployeeAttendance();
				}
			}
			
			int pageMove = 0;
			
			for(int pageIndex = 0; pageIndex<attendanceBoard.size() / 60; pageIndex+=pageMove) {
				
				System.out.println("[사원코드]====[직원이름]====[출근일자]====[근무시간]====[근태상황]");
				
				for(int i=pageIndex; i<pageIndex + 60; i++) {
					
					System.out.printf("%s\t%s\t\t%s\t%s\t%s\n"
										,attendanceBoard.get(i).getCode()
										,attendanceBoard.get(i).getName()
										,attendanceBoard.get(i).getDate()
										,attendanceBoard.get(i).getWorkingTime()
										,attendanceBoard.get(i).getAttendance()
										);
					
				}
				System.out.println();
				System.out.printf("\t- %d 페이지 -", pageIndex);
				System.out.println();
				System.out.println("1. 다음 페이지");
				System.out.println("2. 이전 페이지");
				System.out.println("3. 메인 메뉴로 돌아가기");
				System.out.print("메뉴 입력 :");
				switch (scan.nextLine()) {
				
					case "1"
					: if (pageIndex >= attendanceBoard.size() / 60) {
						System.out.println("현재 페이지가 마지막 페이지 입니다.");
						System.out.println("엔터를 누르시고 메뉴를 다시 입력해주세요.");
						scan.nextLine();
						break;
					} else {
						pageMove++;
						break;
					}
					
					case "2"
					: if (pageIndex <= 0) {
						System.out.println("현재 페이지가 첫 페이지 입니다.");
						System.out.println("엔터를 누르시고 메뉴를 다시 입력해주세요.");
						scan.nextLine();
						break;
					} else {
						pageMove--;
						break;
					}
					
					default
					: break;
				};
				
			}
			
			reader2.close();
			

		} catch (Exception e) {
			System.out.println("EmployeeAttendanceManage.viewEmployeeAttendanceList()");
			e.printStackTrace();
		}
		
		
		
		
		
	}

}



















