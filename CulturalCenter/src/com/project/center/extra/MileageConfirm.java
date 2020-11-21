package com.project.center.extra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
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
}
