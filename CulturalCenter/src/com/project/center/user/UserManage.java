package com.project.center.user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import data.Path;

public class UserManage {

	/**
	 * @author Daeun
	 * 		관리자모드 - 회원관리
	 */
	
	//회원 정보 담는 ulist와 초기화
	private static ArrayList<User> uList = new ArrayList<User>();

	//삭제된 회원의 정보를 담는 deletedUserList
	private static ArrayList<User> deletedUserList = new ArrayList<User>();

	static boolean mainFlag = true;
	
	public void userManageMain() {
	
		while(mainFlag)	{
			
			showMain(); //회원관리 메인창 출력
			
			// 사용자가 입력한 숫자를 받아온다
			int Num = selectNum(); 
			
			if(Num == 1) { 	//1. 회원 목록 조회
				getUserInfo();
				viewUserList();
				//break;
				
			} else if(Num == 2) { //2. 회원 정보 검색
				searchUser();
				//break;
				
			} else if(Num == 3) { //3. 회원 정보 수정
				changeUserInfo();
				//break;
				
			} else if(Num == 4) { //4. 회원 정보 삭제
				deleteUser();
				//break;
				
			} else if(Num == 5) { //5. 삭제 회원 목록
				getUserInfo();
				deletedUserList();
				//break;
				
			} else {
				break;
			}
			
		}
		
		System.out.println("프로그램 진행 . . . .");
	
	} //userManageMain
	
	
	//삭제된 회원의 정보를 출력하는 메서드
	private void deletedUserList() {
		

			System.out.println("===============================================================================");
			System.out.println("                                 삭제 회원 정보 조회");
			System.out.println("===============================================================================");
			
			System.out.println(" [번호] [회원명] [생년월일]\t[아이디]\t [비밀번호] [성별]\t[전화번호]\t  [계층]\t   [주소]");
			
			System.out.println("-------------------------------------------------------------------------------");

			
				for (int j=0; j<deletedUserList.size(); j++) {
					
					System.out.printf("%5s %4s %s %s %s %s %s %3s\t%s\r\n"
							, deletedUserList.get(j).getCode()	
							, deletedUserList.get(j).getName()
							, deletedUserList.get(j).getBirth()	
							, deletedUserList.get(j).getId()	
							, deletedUserList.get(j).getPw()	
							, deletedUserList.get(j).getGender()	
							, deletedUserList.get(j).getTel()
							, deletedUserList.get(j).getGroup()
							, deletedUserList.get(j).getAddress());
						
					}
				
				System.out.println("===============================================================================");
				
				pause();

		
	}


	// 프로그램의 메인 화면을 출력하는 메서드
	public static void showMain() {
		
		System.out.println("========================================");
		System.out.println("\t   회원 관리");
		System.out.println("========================================");
		System.out.println("\t1. 회원 목록 조회");
		System.out.println("\t2. 회원 정보 검색");
		System.out.println("\t3. 회원 정보 수정");
		System.out.println("\t4. 회원 정보 삭제");
		System.out.println("\t5. 삭제 회원 목록");
		System.out.println("\n\t이전으로 가고 싶으면 0번을 입력하세요.");
		System.out.println("========================================");
		                                                                                    
	}

	
	// 번호를 입력받는 메서드
	public static int selectNum() {
		
		// 사용자에게 번호를 입력받는다
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.print("   번호를 선택하세요 : ");
		return Integer.parseInt(scan.nextLine());
		
	}
	

	// 일시정지 메서드
	public static void pause() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("\n 엔터키를 누르면 이전화면으로 돌아갑니다...");
		scan.nextLine();
		for(int i=0 ; i<5 ; i++) {
			System.out.println();
		}
		
	}
	
	
	
	//회원정보를 회원정보.txt에서 읽어오는 메서드
	private static void getUserInfo() {
	
		uList.clear();
		deletedUserList.clear();
		
		try {
			
			//회원 파일 경로 읽어오기
			BufferedReader reader = new BufferedReader(new FileReader(Path.USERLIST));
			//삭제된 회원 파일 경로 읽어오기
			BufferedReader reader2 = new BufferedReader(new FileReader(Path.DELETEUSERLIST));
			
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
				
				
				
				String line2 = null;

				while ((line2 = reader2.readLine()) != null) {

					//기본형태
					//1,박영수,1993-11-15,gmosfi20,Hq!GqaC88,1,01060169587,1,서울시 용산구 한남동
					String[] temp2 = line2.split(",");

					deletedUserList.add(new User(temp2[0]	//회원번호
									, temp2[1]	//회원명
									, temp2[2]	//생년월일
									, temp2[3]	//아이디
									, temp2[4]	//비밀번호
									, temp2[5].equals("1") ? "남자" : "여자" //성별
									, temp2[6]	//번호
									, temp2[7].equals("1") ? "일반" : 
												temp2[7].equals("2") ? "차상위" : "기초" //계층
									, temp2[8]));	//주소));	
				
				}
			}
			
			reader2.close();
			reader.close();
			
		} catch (Exception e) {
			System.out.println("UserInfo.getUserInfo()");
			e.printStackTrace();
		}
		
	}
	
	
	//전체 회원 정보를 조회하는 메서드 
	private static void viewUserList() {

		Scanner scan = new Scanner(System.in);
		
		for (int i=0; i<uList.size()/20+1;) {

			System.out.println("===============================================================================");
			System.out.println("                                 전체 회원 정보 조회");
			System.out.println("===============================================================================");
			
			System.out.println(" [번호] [회원명] [생년월일]\t[아이디]\t [비밀번호] [성별]\t[전화번호]\t  [계층]\t   [주소]");
			
			System.out.println("-------------------------------------------------------------------------------");

			
				for (int j=0+(i*20); j<20+(i*20); j++) {
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
				
				System.out.println("===============================================================================");
	
				System.out.printf("\t\t\t현재 %d 페이지 입니다. 최대 %d 페이지까지 있습니다.\n\n", i+1, uList.size()/20+1);
				System.out.println("\t\t\t1. 이전 페이지");
				System.out.println("\t\t\t2. 다음 페이지");
				System.out.println("\t\t\t3. 원하는 페이지로");
				System.out.println("\t\t\t4. 이전 메뉴로");
				System.out.print("\n\t\t\t메뉴 번호입력 : ");
				
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
					if (i>=uList.size()/20+1) {
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
					
					if (i>uList.size()/20 + 1 && i<0) {
						System.out.println("\t\t\t없는 페이지입니다.");
						pause();
					} else {
						i = Integer.parseInt(pageN) - 1;
					}
					
				} else if (sel.equals("4")) {	//뒤로가기
					//pause();
					break;
				}
				
		}
		
	}//UserList()
	
	
	
	//TODO 아래 회원 정보 수정 메서드 2개짜리 여기에 통합해보기
	private static void changingInfo() {
		
		try {

			Scanner scan = new Scanner(System.in);
			getUserInfo();	//회원정보를 읽어오는 메서드
			
		} catch (Exception e) {
			System.out.println("UserManage.changingInfo()");
			e.printStackTrace();
		}
		
	}
	
	
	
	
	//회원 정보를 수정하는 메서드
	private static void changeUserInfo() {

		try {

			boolean flag = true;
			Scanner scan = new Scanner(System.in);
			getUserInfo();	//회원정보를 읽어오는 메서드
			
			while (flag) {

				System.out.print("   수정하고 싶은 회원의 회원번호를 입력해주세요. : ");
				String userCode = scan.nextLine();

				for (User u : uList) {
					if (u.getCode().equals(userCode)) {
						updateUser(userCode);
						flag = false;
						break;
					}
				}
			}
		
		} catch (Exception e) {
			System.out.println("UserManage.changeUserInfo()");
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	//회원 정보를 수정하는 메서드 - (하위)
	private static void updateUser(String userCode) {
		
		try {
		
			Scanner scan = new Scanner(System.in);
			
			boolean flag = true;
			String changedInfo = "";
			
			System.out.println("\n\n");
			System.out.println("===============================================================================");
			System.out.println("\t\t\t\t회원 정보 수정");
			System.out.println("===============================================================================");
			
			getUserInfo();	//회원정보를 읽어오는 메서드

			while (flag) {

				for (User u : uList) {

					if (u.getCode().equals(userCode)) {

						System.out.println();
						System.out.println(" [번호] [회원명] [생년월일]\t[아이디]\t [비밀번호] [성별]\t[전화번호]\t  [계층]\t   [주소]");
						System.out.println("-------------------------------------------------------------------------------");
						System.out.printf("%5s %4s %s %s %s %s %s %3s\t%s\r\n"
											, u.getCode()
											, u.getName()
											, u.getBirth()
											, u.getId()
											, u.getPw()
											, u.getGender()
											, u.getTel()
											, u.getGroup()
											, u.getAddress());
						System.out.println();
						System.out.println("===============================================================================");

						// 관리자는 회원들의 계층(차상위,기초생활수급)정보와 비밀번호만 수정이 가능하다.
						// 나머지 정보는 회원 본인이 스스로 수정이 가능함
						System.out.println("\n\t1.계층\t2.비밀번호");
						System.out.print("\n   수정하실 정보의 번호를 입력하세요. 종료는 0번을 누르세요. : ");
						String num = scan.nextLine();
						System.out.println("===============================================================================");

						if (num.equals("0")) {
							//saveDeleteUser();
							flag = false;
							break;

						} else if (num.equals("1") || num.equals("2")) {

							System.out.print("\t수정할 내용을 입력하세요 : ");
							changedInfo = scan.nextLine();
							
							//1 : 계층 정보 수정, 2 : 비밀번호 정보 수정
							if (num.equals("1"))
								u.setGroup(changedInfo);
							if (num.equals("2"))
								u.setPw(changedInfo);
							
							System.out.println("\n\t정보 수정이 완료되었습니다.");

						} else {
							System.out.println("\t잘못된 번호입니다.");
							//flag = false;
							break;
						}
					}
				}
				pause();
			}

		} catch (Exception e) {
			System.out.println("UserManage.updateUser()");
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	//회원 검색 메서드 - 회원번호를 입력받아 회원 정보를 호출한다.
	private static void searchUser() {
		
		try {

			Scanner scan = new Scanner(System.in);
			
			boolean flag = false;
			
			System.out.println("\n\n");
			System.out.println("===============================================================================");
			System.out.println("                                 회원 검색 ");
			System.out.println("===============================================================================");
			
			System.out.print("\n   검색할 회원의 회원번호를 입력하세요. : ");
			String userCode = scan.nextLine();
			System.out.println();
			System.out.println("===============================================================================");
			
			getUserInfo();	//회원정보를 읽어오는 메서드
			
			
			for (User u : uList) {
				//userCode에 해당하는 u배열 출력
				if (u.getCode().equals(userCode)) {
					System.out.println(" [번호] [회원명] [생년월일]\t[아이디]\t [비밀번호] [성별]\t[전화번호]\t  [계층]\t   [주소]");
					System.out.println("-------------------------------------------------------------------------------");
					System.out.printf("%5s %4s %s %s %s %s %s %3s\t%s\r\n"
										, u.getCode()
										, u.getName()
										, u.getBirth()
										, u.getId()
										, u.getPw()
										, u.getGender()
										, u.getTel()
										, u.getGroup()
										, u.getAddress());

					System.out.println("===============================================================================");
					
					//성별과 계층정보는 숫자 데이터로 저장되어 있었기 떄문에 다시 써 줄때도 숫자로 변환하여 writer를 써야함
				
					flag = true;
					break;
				}
			}
			
			if (flag == false) {
				System.out.println("\n\t존재하지 않는 회원코드입니다.");
				pause();
			}
			
			if (flag == true) {
				System.out.println("\n\t해당 회원의 정보입니다.");
				pause();
			}


		} catch (Exception e) {
			System.out.println("UserManage.updateUser()");
			e.printStackTrace();
		}
		
	}
	
	
	
	
	//회원 정보 삭제하는 메서드
	private static void deleteUser() {
		
		try {
			
			Scanner scan = new Scanner(System.in);
			BufferedWriter writer = new BufferedWriter (new FileWriter(Path.DELETEUSERLIST, true));
			
			boolean flag = false;
			
			System.out.println("\n\n");
			System.out.println("=========================================");
			System.out.println("               회원 정보 삭제");
			System.out.println("=========================================");
			
			System.out.print("\n   삭제할 회원의 회원번호를 입력하세요. : ");
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
												,u.getGender().equals("남자") ? "1" : "2"
												,u.getTel()
												,u.getGroup().equals("일반") ? "1" : u.getGroup().equals("차상위") ? "2" : "3"
												,u.getAddress()));
					
					//성별과 계층정보는 숫자 데이터로 저장되어 있었기 떄문에 다시 써 줄때도 숫자로 변환하여 writer를 써야함
				
					writer.close();
					uList.remove(u);
					flag = true;
					break;
				}
			}
			
			if (flag == false) {
				System.out.println("\n\t존재하지 않는 회원코드입니다.");
				pause();
			}
			
			if (flag == true) {
				saveDeleteUser();
				System.out.println("\n\t해당 회원의 정보를 삭제했습니다.");
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
												,u.getGender().equals("남자") ? "1" : "2"
												,u.getTel()
												,u.getGroup().equals("일반") ? "1" : u.getGroup().equals("차상위") ? "2" : "3"
												,u.getAddress()));
			
				
			}
			
			sWriter.close();

		} catch (Exception e) {
			System.out.println("UserDelete.savedeleteUser()");
			e.printStackTrace();
		}
	}

}//UserManage
