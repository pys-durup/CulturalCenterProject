package com.project.center.user;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import data.Path;

public class UserInfo {
	
	
	//회원 정보 담는 ulist와 초기화
	private static ArrayList<User> uList = new ArrayList<User>();

	
<<<<<<< Updated upstream
//	//관리자가 메뉴에서 회원 정보조회 시작할 때 생성자로 매서드 실행
=======
	//관리자가 메뉴에서 회원 정보조회 시작할 때 생성자로 매서드 실행
>>>>>>> Stashed changes
//	public UserInfo() {
//		getUserInfo();
//		viewUserList();
//	}
<<<<<<< Updated upstream

=======
	
	
>>>>>>> Stashed changes
	//회원정보를 회원정보.txt에서 읽어오는 메서드
	public void getUserInfo() {
		
		Scanner scan = new Scanner(System.in);
		uList.clear();
		
		try {
			
			//회원 파일 경로 읽어오기
			BufferedReader reader = new BufferedReader(new FileReader(Path.USERLIST));
			
			String line = null;

			while ((line = reader.readLine()) != null) {

				//기본형태
				//1,박영수,1993-11-15,gmosfi20,Hq!GqaC88,1,01060169587,1,서울시 용산구 한남동
				String[] temp = line.split(",");

				uList.add(new User(temp[0]	//회원번호
								, temp[1]	//회원명
								, temp[2]	//생년월일
								, temp[3]	//아이디
								, temp[4]	//비밀번호
								, temp[5].equals("1") ? "남자" : "여자" //성별
								, temp[6]	//번호
								, temp[7].equals("1") ? "일반" : 
											temp[7].equals("2") ? "차상위" : "기초" //계층
								, temp[8]));	//주소));
				
				
			}

			reader.close();
			
			
		} catch (Exception e) {
			System.out.println("UserInfo.getUserInfo()");
			e.printStackTrace();
		}
		
	}
	
	
	//전체 회원 정보를 조회하는 메서드 
	public void viewUserList() {
<<<<<<< Updated upstream
=======
		Scanner scan = new Scanner(System.in);
>>>>>>> Stashed changes
		
		for (int i=0; i<uList.size()/100+1;) {

			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("                                 전체 회원 정보 조회");
			System.out.println("-------------------------------------------------------------------------------");
			
			System.out.println(" [번호] [회원명] [생년월일]\t[아이디]\t [비밀번호] [성별]\t[전화번호]\t  [계층]\t   [주소]");

			
				for (int j=0+(i*100); j<100+(i*100); j++) {
					if (j>=uList.size()) {
						break;
					}
					
					System.out.printf("%5s %4s %s %s %s %s %s %3s\t%s\r\n"
							, uList.get(j).getCode()	
							, uList.get(j).getName()
							, uList.get(j).getBirth()	
							, uList.get(j).getId()	
							, uList.get(j).getPw()	
							, uList.get(j).getGender()	
							, uList.get(j).getTel()
							, uList.get(j).getGroup()
							, uList.get(j).getAddress());
						
				}
				
				System.out.println("-------------------------------------------------------------------------------");
	
				System.out.printf("\t\t\t현재 %d 페이지 입니다. 최대 %d 페이지까지 있습니다.\n\n", i+1, uList.size()/100+1);
				System.out.println("\t\t\t1. 이전 페이지");
				System.out.println("\t\t\t2. 다음 페이지");
				System.out.println("\t\t\t3. 원하는 페이지로");
				System.out.println("\t\t\t4. 뒤로가기");
				System.out.print("\t\t\t메뉴 번호입력 : ");
				
				String sel = scan.nextLine();
				
				if (sel.equals("1")) {	//이전 페이지로 가기
					System.out.println("");
					//현재 페이지가 첫째장일 경우 이전장으로 불가능, 아닐경우 가능
					if (i!=0) {	
						i--;
					} else {
						System.out.println("\t\t\t더 이상 페이지를 이전으로 넘길 수 없습니다.");
						UserManage.pause();
					}
				} else if (sel.equals("2")) {	//다음 페이지로 가기
					//현재 페이지가 마지막 페이지일 경우 다음장으로 불가능, 아닐경우 가능
					if (i>=uList.size()/100+1) {
						System.out.println("\t\t\t더 이상 페이지를 다음으로 넘길 수 없습니다.");
						UserManage.pause();
					} else {
						i++;
					}
				} else if (sel.equals("3")) {	//원하는 페이지로 이동
					System.out.printf("\t\t\t원하는 페이지를 입력하세요. : ");
					
					String pageN = scan.nextLine();		//원하는 페이지 pageN변수에 저장

					//TODO pageN에 음수 또는 최대페이지 초과해서 입력할 때 (->유효성검사)
//					if (i>uList.size()/100 + 1 && i<0) {
//						System.out.println("\t\t\t없는 페이지입니다.");
//						UserManage.pause();
//					} else {
//						if (Integer.parseInt(pageN)>uList.size() || Integer.parseInt(pageN)<0) {
//							System.out.println("없다");
//						} else {
//						i = Integer.parseInt(pageN) - 1;
//					
//						}
//					}
					
					if (i>uList.size()/100 + 1 && i<0) {
						System.out.println("\t\t\t없는 페이지입니다.");
						UserManage.pause();
					} else {
						i = Integer.parseInt(pageN) - 1;
					}
					
				} else if (sel.equals("4")) {	//뒤로가기
					break;
				}
				
				
		}
		
	}//UserList()
	


}
