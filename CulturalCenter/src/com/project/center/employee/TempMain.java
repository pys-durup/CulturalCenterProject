package com.project.center.employee;

import java.util.Calendar;

public class TempMain {

	public static void main(String[] args) {
		
		EmployeeManage m = new EmployeeManage();
		EmployeeAttendanceManage n = new EmployeeAttendanceManage();
		
		boolean loop = true;
		
		Calendar c = Calendar.getInstance();
		
		//관리자의 기능
		while (loop) {
//			
//			m.checkEmployeeManage();
//			m.viewEmployeeManage();
//			
//			if(n.isEmployeeAccess()) {
//				n.viewEmployeeAttendance();
//				loop = false;
//			};
			
			n.findEmployeeAttendanceList();
			System.out.println("메인메뉴");
			
//			
		}
		
	}
	
}
