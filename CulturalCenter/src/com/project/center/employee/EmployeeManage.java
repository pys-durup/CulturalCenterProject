package com.project.center.employee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
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
			
			System.out.println("============================[직원 관리]===========================");
			System.out.println();
			System.out.printf("\t직원 관리 메뉴에 접근하기 위해, 관리자 아이디와\n"
					+ "\t\t비밀번호를 다시 입력해주세요.\n");
			System.out.println();
			System.out.print("관리자 아이디 :");
			String inputID = scan.nextLine();
			System.out.print("관리자 비밀번호 :");
			String inputPassword = scan.nextLine();
			System.out.println();
			
			reader.close();
			
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
		
		boolean toMain = false;
		while (check) {
			
			System.out.println("============================[직원 관리]===========================");
			System.out.println();
			System.out.println("관리자님, 반갑습니다. 원하시는 메뉴를 입력해주세요.");
			System.out.println();
			System.out.println("1. 직원등록   2. 직원 정보 수정\n"
					+ "3. 직원 정보 조회   4. 메인 메뉴로 돌아가기");
			System.out.println();
			System.out.print("메뉴 입력 :");
			String inputMenu = scan.nextLine();
			System.out.println();
			
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
					toMain = true;
					break;
			}

			if (toMain) {
				break;
			}
			
		}
		
	}

	private void insertEmployee() {
		
		System.out.println("============================[직원 등록]===========================");
		System.out.println();
		System.out.println("이름은 한글 2자 ~ 5자로 입력해야 합니다.");
		System.out.printf("이름\t\t:");
		String inputName = scan.nextLine();
		System.out.println("비밀번호는 10 ~ 16자 이내의 영어 대소문자, 숫자만 사용하여 입력해야 합니다.");
		System.out.printf("비밀번호\t:");
		String inputPassword = scan.nextLine();
		System.out.println("급여는 반점(,)없이 숫자로만 입력해주세요.");
		System.out.printf("월 급여\t\t:");
		String inputMonthlyIncome = scan.nextLine();
		System.out.printf("직책\t\t:");
		String inputPosition = scan.nextLine();
		System.out.println();
		
		Employee recruit = new Employee();
		
		boolean check = false;
		int inputCharCode;
		String resultName = "";
		String resultPassword = "";
		String resultMonthlyIncome = "";
		
		//직원의 이름은 한글 2자 이상 5자로 입력해야 한다.
		for (int i=0; i<inputName.length(); i++) {

			if (inputName.length()<2 || inputName.length()>5) {
				check = false;
				break;
			}
			
			inputCharCode = (int)inputName.charAt(i);
			
			if ((inputCharCode>=44032 && inputCharCode<=55203)) {
				resultName += inputName.charAt(i);
			} else {
				check = false;
				break;
			}
			
			check = true;
			
		}
		
		//비밀번호는 10 ~ 16자 이내의 영어 대소문자, 숫자만 사용하여 입력해야한다.
		for (int i=0; i<inputPassword.length(); i++) {
			
			if (inputPassword.length()<10 || inputPassword.length()>16) {
				check = false;
				break;
			}
			
			inputCharCode = (int)inputPassword.charAt(i);
			
			if ((inputCharCode>=65 && inputCharCode<=90) ||
				(inputCharCode>=97 && inputCharCode<=122) ||
				(inputCharCode>=48 && inputCharCode<=57)) {
				resultPassword += inputPassword.charAt(i);
			} else {
				check = false;
				break;
			}
			
			//check = true;
			
		}
		
		//월 급여는 숫자로만 입력해야한다.
		for (int i=0; i<inputMonthlyIncome.length(); i++) {
			
			inputCharCode = (int)inputMonthlyIncome.charAt(i);
			
			if (inputCharCode>=48 && inputCharCode<=57) {
				resultMonthlyIncome += inputMonthlyIncome.charAt(i);
			} else {
				check = false;
				break;
			}
			
			//check = true;
			
		}
		
		//사원 코드와 입사날짜를 계산합니다.
		Calendar today = Calendar.getInstance();
		String employeeCode = today.get(Calendar.YEAR) + getRegistrationNumber();
	
		String startDate = String.format("%04d-%02d-%02d"
											,today.get(Calendar.YEAR)
											,today.get(Calendar.MONTH) + 1
											,today.get(Calendar.DATE));
		

		if (check) {
			recruit.setCode(employeeCode);
			recruit.setName(resultName);
			recruit.setPosition(inputPosition);
			recruit.setPassword(resultPassword);
			recruit.setMonthlyIncome(resultMonthlyIncome);
			recruit.setStartDate(startDate);

			recruit.setEmployeeIntoData();
			
			System.out.printf("\t%s %s의 등록을 완료하였습니다.\n", resultName, inputPosition);
			System.out.println("\t엔터를 누르시면, 관리 메뉴로 돌아갑니다.");
			scan.nextLine();
			
		} else {
			System.out.println();
			System.out.println("\t\t\t*** 경고 ***\t\t");
			System.out.println("\t  올바르지 않은 직원 정보를 입력하셨습니다!");
			System.out.println("\t   엔터를 눌러 관리 화면으로 이동해주세요.");
			System.out.println("\t\t\t*** 경고 ***\t\t");
			scan.nextLine();
		}
		
	}

	private String getRegistrationNumber() {
		
		String result = null;
		String[] list = { "00000000", "", "", "", "", "" };
		
		try {

			BufferedReader reader = new BufferedReader(new FileReader(Path.EMPLOYEELIST));
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				result = list[0].substring(4, 8);
				list = line.split(",");
			}
			
			reader.close();
			
		} catch (Exception e) {
			System.out.println("EmployeeManage.getRegistrationNumber()");
			e.printStackTrace();
		}
		
		int temp = Integer.parseInt(result) + 2;
		result = String.format("%04d", temp);
		
		return result;
		
	}


	private void findEmployee() {
		
		System.out.println("=========================[직원 정보 조회]=========================");
		System.out.println();
		System.out.println("====[사원코드]====[직원이름]====[월 급여]====[직책]====[입사일]===");
		
		try {

			BufferedReader reader = new BufferedReader(new FileReader(Path.EMPLOYEELIST));
			
			String line = null;
			String[] list = new String[6];
			
			while ((line = reader.readLine()) != null) {
				
				list = line.split(",");
				
				System.out.printf("     %s       %s\t%s(원)   %s\t%s\n"
								,list[0]
								,list[1]
								,list[4]
								,list[2]
								,list[5]);
			}
			
			reader.close();
			
			System.out.println("=========================[직원 조회 완료]=========================");
			System.out.println();
			System.out.println("\t\t엔터를 누르시면 관리 메뉴로 돌아갑니다.");
			System.out.println();
			scan.nextLine();

		} catch (Exception e) {
			System.out.println("EmployeeManage.findEmployee()");
			e.printStackTrace();
		}
		
	}
	
	private void updateEmployee() {
		
		boolean loop = true;
		boolean check = false;
		boolean escapeCheck = false;
		int inputCharCode = 0;
		String resultEditedPassword = "";
		String resultEditedMonthlyIncome = "";
		String resultEditedPosition = "";
		
		while (loop) {
			
			resultEditedPassword = "";
			resultEditedMonthlyIncome = "";
			resultEditedPosition = "";
			
			System.out.println("=========================[직원 정보 수정]=========================");
			System.out.println();
			System.out.println("직원 정보 수정을 위해, 수정할 직원의 사원 코드를 입력해주세요.");
			System.out.print("수정할 직원의 사원 코드 입력 : ");
			String inputCode = scan.nextLine();
			
			try {
				
				BufferedReader reader = new BufferedReader(new FileReader(Path.EMPLOYEELIST));
				
				String line = null;
				
				while ((line = reader.readLine()) != null) {
					if(inputCode.equals(line.substring(0, 8))) {
						check = true;
						System.out.println(line.substring(0, 8));
						break;
					} 
				}
				
				if (check) {
					
					System.out.println("수정할 직원의 비밀번호, 시급, 직책을 입력해주세요.");
					System.out.println();
					System.out.println("비밀번호는 10 ~ 16자 이내의 영어 대소문자, 숫자만을 이용하여 입력해주세요.");
					System.out.printf("비밀번호 :");
					String inputEditedPassword = scan.nextLine();
					System.out.println("급여는 반점(,)없이 숫자로만 입력해주세요.");
					System.out.printf("월 급여 :");
					String inputEditedMonthlyIncome = scan.nextLine();
					System.out.printf("직책 :");
					resultEditedPosition = scan.nextLine();
					System.out.println();
					
					if (inputEditedPassword.length() == 0
							|| inputEditedMonthlyIncome.length() == 0) {
						check = false;
					}
					
					for (int i=0; i<inputEditedPassword.length(); i++) {
						
						if (inputEditedPassword.length()<10 || inputEditedPassword.length()>16) {
							check = false;
							break;
						}
						
						inputCharCode = (int)inputEditedPassword.charAt(i);
						
						if ((inputCharCode>=65 && inputCharCode<=90) 
						|| (inputCharCode>=97 && inputCharCode<=122) 
						||(inputCharCode>=48 && inputCharCode<=57)) {
							resultEditedPassword += inputEditedPassword.charAt(i);
						} else {
							check = false;
							break;
						}
						//check = true;
						
					}
					
					for (int i=0; i<inputEditedMonthlyIncome.length(); i++) {
						
						inputCharCode = (int)inputEditedMonthlyIncome.charAt(i);
						
						if (inputCharCode>=48 && inputCharCode<=57) {
							resultEditedMonthlyIncome += inputEditedMonthlyIncome.charAt(i);
						} else {
							check = false;
							break;
						}
						
						//check = true;
						
					}
					
				}
				reader.close();
				
				String dummy = "";
				boolean editFlag = false;
				String[] list = new String[6];
				BufferedReader readerAgain = new BufferedReader(new FileReader(Path.EMPLOYEELIST));
				
				if (check) {
				
				
					line = null;
					while((line = readerAgain.readLine()) != null) {
						
						list = line.split(",");
						if (inputCode.equals(list[0])) {
							dummy += String.format(
									"%s,%s,%s,%s,%s,%s"
									,list[0] ,list[1]
											,resultEditedPosition
											,resultEditedPassword
											,resultEditedMonthlyIncome
											,list[5]) + "\r\n";
							
						} else {
							dummy += line + "\r\n";
						}
						
						
					}
					
					dummy = dummy.substring(0, dummy.length()- 2);
					editFlag = true;
				
				} else {
					System.out.println("잘못된 정보를 입력하셨습니다!");
					System.out.println();
					System.out.println("1. 다시 입력하기");
					System.out.println();
					System.out.println("2. 메인 메뉴로 돌아가기");
					System.out.println();
					System.out.print("메뉴 입력 :");
					String menuInput = scan.nextLine();
					
					if(menuInput.equals("2")) {
						loop = false;
					} else if(menuInput.equals("1")) {
						loop = true;
					} else {
						System.out.println("잘못된 메뉴 입력입니다! 엔터를 누르면 관리 메뉴로 돌아갑니다.");
						scan.nextLine();
						loop = false;
					}
				}
				
				if(editFlag) {
					
					readerAgain.close();
					BufferedWriter writer = new BufferedWriter(new FileWriter(Path.EMPLOYEELIST));
					
					writer.write(dummy);
					
					System.out.printf("해당 직원의 정보 수정이 완료되었습니다.");
					System.out.println();
					System.out.println("엔터를 누르시면 관리 메뉴로 돌아갑니다.");
					writer.close();
					loop = false;
					scan.nextLine();
					System.out.println();
					
				} 
				
			} catch (Exception e) {
				System.out.println("EmployeeManage.updateEmployee()");
				e.printStackTrace();
			}
			
			
		} //반복문(loop) 및 사원코드 유효성 검사

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
					loop = false;
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
		boolean loop = true;
		int workingDays = 0;
		
		try {

			ArrayList<String[]> attendanceBoard = new ArrayList<String[]>();

			BufferedReader readerAttendance = new BufferedReader(new FileReader(Path.EMPLOYEEATTENDANCE));

			String line = null;
			String[] list = new String[6];
			int m = 0;

			while ((line = readerAttendance.readLine()) != null) {
				list = line.split(",");
				if (inputCode.equals(list[0])) {
					workingDays++;
					attendanceBoard.add(list);
				}
			}

			int pageMove = 0;

			for(int pageIndex = 0; pageIndex<attendanceBoard.size() / 20 + 1; pageIndex+=pageMove) {

				System.out.println("[사원코드]====[직원이름]====[출근일자]====[근무시간]====[근태상황]");

				for(int i=pageIndex * 20; i<20 + pageIndex * 20; i++) {

					if (i == attendanceBoard.size()) {
						break;
					}

					System.out.printf("%s       %s       %s        %s         %s\n"
										,attendanceBoard.get(i)[0]
										,attendanceBoard.get(i)[1]
										,attendanceBoard.get(i)[2]
										,attendanceBoard.get(i)[4]
										,attendanceBoard.get(i)[5]
										);

				}
				System.out.println("================================================================");
				System.out.println("해당 직원의");
				System.out.printf("총 출근일 : %d일\n"
								+ "총 결근일 : %d일\n"
								, workingDays
								, getEmployeeAbsenceDays(inputCode));
				System.out.println("================================================================");
				System.out.printf("\t\t\t- %d 페이지 -", pageIndex + 1);
				System.out.println();
				System.out.println("1. 다음 페이지");
				System.out.println("2. 이전 페이지");
				System.out.println("3. 메인 메뉴로 돌아가기");
				System.out.print("메뉴 입력 :");
				switch (scan.nextLine()) {

					case "1"
					: if (pageIndex == (attendanceBoard.size() / 20)) {
						System.out.println("현재 페이지가 마지막 페이지 입니다.");
						System.out.println("엔터를 누르시고 메뉴를 다시 입력해주세요.");
						pageMove = 0;
						scan.nextLine();
						break;
					} else {
						pageMove = 1;
						break;
					}

					case "2"
					: if (pageIndex <= 0) {
						System.out.println("현재 페이지가 첫 페이지 입니다.");
						System.out.println("엔터를 누르시고 메뉴를 다시 입력해주세요.");

						pageMove = 0;
						scan.nextLine();
						break;
					} else {
						pageMove = -1;
						break;
					}

					default
					: loop = false;
					System.out.println("메인 메뉴로 돌아갑니다.");
					break;
				};

				if (loop) {
					
				} else {
					break;
				}
				
			}

			readerAttendance.close();

		} catch (Exception e) {
			System.out.println("EmployeeAttendanceManage.viewEmployeeAttendanceList()");
			e.printStackTrace();
		}
		
	}
	
	private int getEmployeeAbsenceDays(String inputCode) {
		Calendar today = Calendar.getInstance();
		int absenceDays = 0;
		
		try {

			BufferedReader readerDate = new BufferedReader(new FileReader(Path.EMPLOYEEATTENDANCE));
			
			String line = null;
			String[] list = new String[6];
			String date = null;
			int dateToNum = 0;
			int leastDateNum = 99999999;
			
			while ((line = readerDate.readLine()) != null) {
				list = line.split(",");
				date = list[2].replaceAll("-", "");
				dateToNum = Integer.parseInt(date);
				leastDateNum = leastDateNum < dateToNum ? leastDateNum : dateToNum;
			}
			readerDate.close();
			
			String leastDateTemp = leastDateNum + "";
			Calendar leastDate = Calendar.getInstance();
			
			leastDate.set(Integer.parseInt(leastDateTemp.substring(0,4))
						, Integer.parseInt(leastDateTemp.substring(4, 6))
						, Integer.parseInt(leastDateTemp.substring(6))
						);
			
			BufferedReader reader = new BufferedReader(new FileReader(Path.EMPLOYEEATTENDANCE));
			
			line = null;
			list = new String[6];
			int night = 0;
			Calendar temp = leastDate;
			boolean check = false;
			
			for (int i=0; String.format("%tF", today).equals(String.format("%tF", leastDate)); i++) {
				
				if (temp.get(Calendar.DAY_OF_WEEK) != 7
						&& temp.get(Calendar.DAY_OF_WEEK) != 1) {
					
					while ((line = reader.readLine()) != null) {
						list = line.split(",");
						if (inputCode.equals(list[0]) 
								&& String.format("%tF", temp).equals(list[2])) {
							check = true;
							break;
						}
						check = false;
					}
					
					if (!check) {
						absenceDays++;
					}
					
				}
				
				temp = leastDate;
				i++;
				temp.set(Calendar.DATE, i);
				
			}
			
			reader.close();
			
			BufferedReader readerStatus = new BufferedReader(new FileReader(Path.EMPLOYEEATTENDANCE));
			
			line = null;
			list = new String[6];
			
			while ((line = readerStatus.readLine()) != null) {
				list = line.split(",");
				if (list[5].equals("결근")) {
					absenceDays++;
				}
			}
			
			readerStatus.close();

		} catch (Exception e) {
			System.out.println("EmployeeManage.getEmployeeAbsenceDays()");
			e.printStackTrace();
		}
		
		return absenceDays;
	}
	
}












