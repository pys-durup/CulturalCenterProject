package com.project.center.employee;

import java.util.Calendar;

public class TempMain {

	public static void main(String[] args) {
		
		EmployeeManage m = new EmployeeManage();
		EmployeeAttendanceManage n = new EmployeeAttendanceManage();
		
		boolean loop = true;
		
		Calendar c = Calendar.getInstance();
		
		while (loop) {
			
			//관리자의 기능
//			m.checkEmployeeManage();
//			m.viewEmployeeManage();
			
			//직원의 기능
//			if(n.isEmployeeAccess()) {
//				n.viewEmployeeAttendance();
//				loop = false;
//			};
			
			//관리자의 기능 중, 직원 근태 조회
			n.findEmployeeAttendanceList();
			System.out.println("메인메뉴");
			
//			
		}
		
	}
	
}
