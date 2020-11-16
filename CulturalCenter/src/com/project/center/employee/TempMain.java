package com.project.center.employee;

import java.util.Calendar;

public class TempMain {

	public static void main(String[] args) {
		
		EmployeeManage m = new EmployeeManage();
		EmployeeAttendanceManage n = new EmployeeAttendanceManage();
		
		boolean loop = true;
		
		Calendar c = Calendar.getInstance();
		
		System.out.println(String.format("%tF", c));
		
		//관리자의 기능
//		while (loop) {
//			
//			m.checkEmployeeManage();
//			m.viewEmployeeManage();
//			
//			
//		}
		
	}
	
}
