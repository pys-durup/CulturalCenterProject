package com.project.center.employee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.project.center.user.User;

import data.Path;

public class EmployeeAttendanceManage {
	
	private boolean check = false;
	EmployeeAttendance employee = new EmployeeAttendance();
	
	public void viewEmployeeAttendance(User login) {
		
		employee.setCode(login.getId());
		employee.setName(login.getName());

		Scanner scan = new Scanner(System.in);
		
		System.out.println("==================================================================");
		System.out.println("\t\t\t  <근태 기록>");
		System.out.println("==================================================================");
		System.out.println();
		System.out.printf("%s님, 반갑습니다. 원하는 메뉴 번호를 입력해주세요.\n", employee.getName());
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
			System.out.println("근태 기록을 종료합니다.");
			System.out.println("==============================================================");
			break;
		default:
			System.out.println("근태 기록을 종료합니다.");
			System.out.println("==============================================================");
			break;
		}
		
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
				System.out.println("==============================================================");
				System.out.println();
				
			} else {
				
				dummy = null;
				employee.setEmployeeAttendanceIntoData();
				
				System.out.printf("%d월 %d일의 출근 시각이 기록되었습니다."
						, today.get(Calendar.MONTH) + 1
						, today.get(Calendar.DATE)
						);
				System.out.println("==============================================================");
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
					System.out.println("==============================================================");
					System.out.println("엔터를 누르시면, 메인 메뉴로 돌아갑니다.");
					scan.nextLine();
					break;
					
				} 
				
			}
			
			if(check == false) {
				System.out.println("오늘 출근한 기록이 없습니다!");
				System.out.println();
				System.out.println("==============================================================");
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
		
}
















