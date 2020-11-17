package com.project.center.user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import data.Path;

public class UserManage {
	
	//회원 정보 담는 ulist와 초기화
	private static ArrayList<User> uList = new ArrayList<User>();

	static boolean mainFlag = true;
	
	public static void main(String[] args) {

	
		while(mainFlag)	{
			
			showMain(); //회원관리 메인창 출력
			
			// 사용자가 입력한 숫자를 받아온다
			int Num = selectNum(); 
			
			
			if(Num == 1) {
				//System.out.println("\t1. 회원 목록 조회");
				getUserInfo();
				viewUserList();
				break;
				
			} else if(Num == 2) { 
				System.out.println("\t2. 회원 정보 검색");
				//UserSelect()
				break;
				
			} else if(Num == 3) { 
				System.out.println("\t3. 회원 정보 수정");
				//UserUpdate()
				break;
				
			} else if(Num == 4) { 
				//System.out.println("\t4. 회원 정보 삭제");
				deleteUser();
				//UserDelete()
				break;
				
			} else {
				pause();
				
			}
			
		}
		
		
		System.out.println("프로그램 진행 . . . .");
	
		
	} //main
	
	
	// 프로그램의 메인 화면을 출력하는 메서드
	public static void showMain() {
		
		System.out.println("\n\t   [회원 관리]\n");
		System.out.println("\t1. 회원 목록 조회");
		System.out.println("\t2. 회원 정보 검색");
		System.out.println("\t3. 회원 정보 수정");
		System.out.println("\t4. 회원 정보 삭제");
		                                                                                    
	}

	
	// 번호를 입력받는 메서드
	private static int selectNum() {
		
		// 사용자에게 번호를 입력받는다
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.print("번호를 선택하세요 : ");
		return Integer.parseInt(scan.nextLine());
		
	}
	

	// 일시정지
	private static void pause() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("\n 엔터키를 누르면 이전화면으로 돌아갑니다...");
		scan.nextLine();
		for(int i=0 ; i<10 ; i++) {
			System.out.println();
		}
		
	}
	
	
	
	
	
	//회원정보를 회원정보.txt에서 읽어오는 메서드
	private static void getUserInfo() {
	
		
		//Scanner scan = new Scanner(System.in);
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
	private static void viewUserList() {

		Scanner scan = new Scanner(System.in);

		
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
						pause();
					}
				} else if (sel.equals("2")) {	//다음 페이지로 가기
					//현재 페이지가 마지막 페이지일 경우 다음장으로 불가능, 아닐경우 가능
					if (i>=uList.size()/100+1) {
						System.out.println("\t\t\t더 이상 페이지를 다음으로 넘길 수 없습니다.");
						pause();
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
						pause();
					} else {
						i = Integer.parseInt(pageN) - 1;
					}
					
				} else if (sel.equals("4")) {	//뒤로가기
					pause();
					break;
				}
				
				
		}
		
	}//UserList()
	
	
	
	
	
	
	
	//회원 정보 삭제하는 메서드
	private static void deleteUser() {
		
		try {
			
			Scanner scan = new Scanner(System.in);
			BufferedWriter writer = new BufferedWriter (new FileWriter(Path.DELETEUSERLIST, true));
			
			boolean flag = false;
			
			System.out.println("\n\n");
			System.out.println("-----------------------------------------");
			System.out.println("               회원 정보 삭제");
			System.out.println("-----------------------------------------");
			
			System.out.print("\n삭제할 회원의 회원번호를 입력하세요. : ");
			String userCode = scan.nextLine();
			
			getUserInfo();	//회원정보를 읽어오는 메서드
			
			
			for (User u : uList) {
				//TODO 휴면회원리스트로 만들까? 아니면 그냥 삭제할까 생각해보기
				//입력한 숫자와 회원번호가 같으면 삭제한 회원을 회원삭제정보.txt에 저장 
				if (u.getCode().equals(userCode)) {
					writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s\r\n"
												,u.getCode()
												,u.getName()
												,u.getBirth()
												,u.getId()
												,u.getPw()
												,u.getGender()
												,u.getTel()
												,u.getGroup()
												,u.getAddress()));
					
				
					writer.close();
					uList.remove(u);
					flag = true;
					break;
				}
			}
			
			if (flag == false) {
				System.out.println("\n존재하지 않는 회원코드입니다.");
				pause();
			}
			
			if (flag == true) {
				saveDeleteUser();
				System.out.println("\n해당 회원의 정보를 삭제했습니다.");
				pause();
			}

		} catch (Exception e) {
			System.out.println("UserDelete.deleteUser()");
			e.printStackTrace();
		}
		
	}
	
	
	
	//삭제한 회원 제외한 나머지 회원 정보 다시 써오는 메서드
	private static void saveDeleteUser() {
		
		try {

			BufferedWriter sWriter = new BufferedWriter (new FileWriter(Path.USERLIST));
			
			for (User u : uList) {
				sWriter.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s\r\n"
												,u.getCode()
												,u.getName()
												,u.getBirth()
												,u.getId()
												,u.getPw()
												,u.getGender()
												,u.getTel()
												,u.getGroup()
												,u.getAddress()));
			
				
			}
			
			sWriter.close();

		} catch (Exception e) {
			System.out.println("UserDelete.savedeleteUser()");
			e.printStackTrace();
		}
	}

}//UserManage
