package com.project.center.extra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.project.center.user.User;

import data.Path;

/**
 * @author youngsu 
 * 마일리지 확인시 이용하는 객체
 * 
 */
public class MileageConfirm {
	
	private ArrayList<Mileage> mList = new ArrayList<Mileage>();
	private User user;
	
	public MileageConfirm() {
		this.mList = loadMileageData(Path.MILEAGE);
	}
	
	// 회원이 보유한 마일리지를 보여주는 메서드
	public void showMyMileage(User user) {
		this.user = user;
		int mileage = 0;
		
		for(Mileage m : mList) {
			if(m.getUserCode().equals(this.user.getCode())) {
				mileage = m.getMileage();
				break;
			}
		}
		
		while(true) {
			System.out.println("[마일리지 정보]");
			System.out.printf("내가 보유한 마일리지 : %d\n\n", mileage);
			System.out.println("엔터키를 누르면 뒤로 이동합니다.");
			Scanner scan = new Scanner(System.in);
			scan.nextLine();
			break;
		}
		
	}
	
	// 마일리지 데이터를 불러오는 메서드
	private ArrayList<Mileage> loadMileageData(String path) {
		
		try {
			ArrayList<Mileage> tempList = new ArrayList<Mileage>();
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			String line = "";
			while((line = reader.readLine()) != null){
				String[] temp = line.split(",");
				tempList.add(new Mileage(temp[0], Integer.parseInt(temp[1])));
			}
			
			reader.close();
			return tempList;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryMileageConfirm.enloadMileageData()");
			e.printStackTrace();
			return null;
		}
	}
	
	// 이벤트.txt파일을 불러와서 이벤트를 출력해준다
	public void showEvent() {
		// 1,해양환경체험교실,2020-11-01,2021-11-15,1000
		
		Calendar now = Calendar.getInstance(); // 오늘날
		Calendar endDate = null;
		
		String onGoing = ""; // 진행중인 이벤트
		String exit = ""; // 종료된 이벤트
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(Path.EVENT));
			
			String line = "";
			while((line = reader.readLine()) != null) {
				String[] temp = line.split(",");
				
				endDate = stringToCal(temp[3]);
				
				if(!now.after(endDate)) {
					onGoing += String.format("%-30s \t%12s\t\t%s\t %5s\n", temp[1], temp[2], temp[3], temp[4]);
				} else {
					exit += String.format("%-30s \t%12s\t\t%s\t %5s\n", temp[1], temp[2], temp[3], temp[4]);
				}
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryMileageConfirm.enshowEvent()");
			e.printStackTrace();
		}
		
		System.out.println("===============================================================================================");
		System.out.println("\t\t\t<진행중인 이벤트>");
		System.out.println("===============================================================================================");
		System.out.println("[이벤트 제목]\t\t\t\t\t[이벤트 시작일]\t   [이벤트 종료일]   [적립 마일리지]");
		System.out.println(onGoing);
		
		
		System.out.println();
		System.out.println("===============================================================================================");
		System.out.println("\t\t\t<종료된 이벤트>");
		System.out.println("===============================================================================================");
		System.out.println("[이벤트 제목]\t\t\t\t\t[이벤트 시작일]\t   [이벤트 종료일]   [적립 마일리지]");
		System.out.println(exit);
		System.out.println();
		
		pause();
	}
	
	// 일시정지
	private static void pause() {
		Scanner scan = new Scanner(System.in);
		System.out.println("엔터키를 누르면 이전화면으로 돌아갑니다");
		scan.nextLine();
		for(int i=0 ; i<20 ; i++) {
			System.out.println();
		}
	}
	
	
	
	// String -> Calendar
	public static Calendar stringToCal(String s) {
		// "YYYY-MM-DD"
		String[] list = s.split("-");
		int month = 0;
		String isZero = list[1].substring(0, 1);
		String change = list[1].substring(1);
		if(isZero.equals("0")) {
			month = Integer.parseInt(change);
		} else {
			month = Integer.parseInt(list[1]);
		}
		int year = Integer.parseInt(list[0]);
		
		int date = Integer.parseInt(list[2]);

		Calendar temp = Calendar.getInstance();
		temp.set(year, month - 1, date);
		return temp;

	}
}
