package com.project.center.program;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.center.main.Path;

public class ProgramManage {
	
	private ArrayList<Program> pList; // 프로그램 정보 출력을위해 필요
	private ArrayList<ProgramStudent> psList; // 정원, 프로그램모집상태 위해 필요

	public ProgramManage() {
		// 생성과 동시에 데이터를 읽어옴
		this.pList = loadProgramData(Path.PROGRAMLIST);
		this.psList = loadPsData(Path.PROGRAMSTUDENT);
	}


	/**
	 *  프로그램 신청이 진행되는 메서드
	 * 
	 */
	public void applyProgram() {
	
		while(true) {
			// 프로그램 신청 메뉴
			showMenu();
			
			// 입력 번호를 받아옴
			System.out.print("번호를 입력하세요 : ");
			int num = selectNum();
			
			if(num == 1) {
				System.out.println("검색어를 입력하세요 : ");
				String text = getSearchString();
				showSearchList(text);

			} else if(num == 2) {
				System.out.println("연령별");
			} else if(num == 3) {
				System.out.println("테마별");
			} else if(num == 4) {
				System.out.println("월별");
			} else if(num == 5){
				System.out.println("뒤로가기");
				break;
			} else {
				pause();
			}
 		}
	}

	
	
	/**
	 *  프로그램 신청 메뉴를 보여주는 메서드
	 * 
	 */
	private void showMenu() {
		
		System.out.println("1. 검색어로 찾기");
		System.out.println("2. 연령별 추천 목록");
		System.out.println("3. 테마별 추천 목록");
		System.out.println("4. 월별 선택");
		System.out.println("5. 뒤로가기");
	}
	
	
	/**
	 *  검색어를 받아오는 메서드 
	 *   
	 */
	private String getSearchString() {
//		System.out.println("getSearchString()");
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}
	
	/**
	 * 	검색 결과를 ArrayList에 담아두는 메서드 
	 *  @param text : 사용자가 검색한 검색어
	 *  
	 */
	private void showSearchList(String text) {
//		System.out.println("showSearchList(text)");
		// 검색어 
		String keyword = text.trim();
		// 검색된 결과를 담을 리스트
		ArrayList<Program> resultList = new ArrayList<Program>();
		
		// 검색된 결과가 있을시 리스트에 저장
		for(Program data : pList) {
			if(data.getName().indexOf(text) > 0) {
				resultList.add(data);
			}
		}
		
		// 검색 결과를 출력하는 메서드
		showProgramList(resultList);
		
	}
	
	/**
	 * 	검색 결과를 출력하는 메서드 
	 *  @param list : 검색어로 검색한 프로그램을 담아둔 ArrayList
	 *  
	 */
	private void showProgramList(ArrayList<Program> list) {
//		System.out.println("showProgramList()");
		int index = 1; // 프로그램 번호
		System.out.println("[번호]  [프로그램 이름]\t\t\t[강사명]    [강의실]    [시작 날짜]\t[종료 날짜]\t[정원]\t  [현재상태]\t[가격]");
		
		if(list.size() == 0) { // 검색 결과가 없다면
			System.out.println("검색 결과가 존재하지 않습니다");
		} else {
			
			for(Program p : list) { // 검색 결과가 있을때 출력내용 생성
				System.out.println();
				int count = 0; // 현재 수강중인 인원
				String state = ""; // 현재 프로그램 모집상태(모집중 / 마감)
				
				for(ProgramStudent ps : this.psList) {
					if(p.getCode().equals(ps.getCode())) {
						count = ps.getCount();
						state = ps.getState();
						break;
					}
				}
				
				//  [프로그램 이름]   [강사명]    [강의실]     [시작 날짜]   [종료 날짜]   [정원]   [현재상태]     [가격]
				System.out.printf("%3d\t%-15s\t%5s\t%s\t%s\t%s\t(%d/%d)\t    %s\t %,d원"
												, index
												, p.getName()
												, p.getTeacher()
												, p.getClassRoom()
												, p.getStartDate()
												, p.getEndDate()
												, count , p.getCapacity()
												, state
												, p.getPrice());
				index++;
			}
			System.out.println();
			System.out.println("검색어에 대한 프로그램 출력 완료");
			
			System.out.println("1. 프로그램 신청하기\t 2. 뒤로가기");
			System.out.print("번호를 입력하세요 : ");
			int num = selectNum();
			
			if(num == 1) {
				System.out.print("신청할 프로그램의 번호 입력 : ");
				num = selectNum();
			} else if (num == 2) {
//				clearPage();
			} else {
				pause();
			}
			
			
			
		}		
	}
	
	// 연령별 추천 리스트를 출력
	// 테마별 추천 리스트를 출력
	// 월별 프로그램을 출력
	
	
	
	// 번호를 입력받는 메서드
	private static int selectNum() {
		
		// 사용자에게 번호를 입력받는다
		Scanner scan = new Scanner(System.in);
		System.out.println();
		return Integer.parseInt(scan.nextLine());
	}
	
	// 일시정지
	private static void pause() {
		Scanner scan = new Scanner(System.in);
		System.out.println("잘못된 숫자입니다. 엔터키를 누르면 이전화면으로 돌아갑니다.");
		scan.nextLine();
		for(int i=0 ; i<10 ; i++) {
			System.out.println();
		}
	}

	priva
	
	// 프로그램.txt에서 데이터를 읽어온다
	private ArrayList<Program> loadProgramData(String path) {
		
		ArrayList<Program> list = new ArrayList<Program>();
		File file = new File(path);
		
		if(file.exists()) {
			
			try {
				BufferedReader reader = new BufferedReader(new FileReader(path));
				
				String line = null;
				
				while((line = reader.readLine()) != null) {
					String[] temp = line.split(",");
					
					list.add(new Program(temp[0]
							, temp[1]
							, temp[2]
							, temp[3]
							, temp[4]
							, temp[5]
							, Integer.parseInt(temp[6])
							, Integer.parseInt(temp[7])));
				}
				
				reader.close();
				return list;
				
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("primaryMyPath.enloadData()");
				e.printStackTrace();
				return null;
			}
			
		} else {
			System.out.println("파일이 존재하지 않음");
			return null;
		}
		
	}
	
	// 프로그램수강생.txt에서 데이터를 읽어온다
	private ArrayList<ProgramStudent> loadPsData(String path) {
		
		File file = new File(path);

		if (file.exists()) {

			try {
				ArrayList<ProgramStudent> list = new ArrayList<ProgramStudent>();
				BufferedReader reader = new BufferedReader(new FileReader(path));

				String line = null;

				while ((line = reader.readLine()) != null) {
					String[] temp = line.split(",");
					ArrayList<String> userCodes = new ArrayList<String>();

					String[] users = temp[3].split("■");
					for (int i = 0; i < users.length; i++) {
						userCodes.add(users[i]);
					}

					list.add(new ProgramStudent(temp[0]
												, Integer.parseInt(temp[1])
												, temp[2]
												, userCodes));
				}

				reader.close();
				return list;

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("primaryMyPath.enloadData()");
				e.printStackTrace();
				return null;
			}

		} else {
			System.out.println("파일이 존재하지 않음");
			return null;
		}
	}


}
