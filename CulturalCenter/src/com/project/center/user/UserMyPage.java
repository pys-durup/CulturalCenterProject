package com.project.center.user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import data.Path;

public class UserMyPage {
	
	public static void showMyPage(User login) {
		
		Scanner scan = new Scanner(System.in);
		
		boolean loop = true;
		
		while (loop) {
		
			System.out.println("\n\t   [회원 정보]\n");
			System.out.println("\t1. 회원 정보 조회");
			System.out.println("\t2. 회원 정보 수정");
			System.out.println("\t3. 뒤  로  가  기\r\n");
			
			System.out.println("번호를 입력해주세요.");
			System.out.print("입력 : ");
			String num = scan.nextLine();
			
			//1번을 고른다면 회원 정보 조회
			if (num.equals("1")) {
				selectUser(login);
			
			//2번을 고른다면 회원 정보 수정
			} else if (num.equals("2")) {
				updateUser(login);
				
			//3번을 고른다면 뒤로가기
			} else if (num.equals("3")) {
				loop = false;
				
			//그 외에 번호를 고른다면 다시 화면 띄우기
			} else {
				System.out.println("잘못된 번호입니다.");
				showMyPage(login);
			}
			
		}
		
	}
	
	public static void selectUser(User login) {
		
		System.out.println();
		System.out.println("==================회원 정보 조회==================");
		System.out.println("회원번호 : " + login.getCode());
		System.out.println("사 용 자 : " + login.getName());
		System.out.println("아 이 디 : " + login.getId());
		System.out.println("비밀번호 : " + login.getPw());
		System.out.println("생년월일 : " + login.getBirth());
		System.out.println("성    별 : " + (login.getGender().equals("1") ? "남자" : "여자") );
		System.out.println("전화번호 : " + login.getTel());
		System.out.println("거 주 지 : " + login.getAddress());
		System.out.println("분    류 : " + (login.getGroup().equals("1") ? "일반" :
															login.getGroup().equals("2") ? "차상위계층" : "기초생활수급자") );
		System.out.println("==================================================");
		
		Scanner scan = new Scanner(System.in);
		
		//수정을 할건지 물어보기
		System.out.println("수정하시겠습니까? (Y/N)");
		System.out.print("입력 : ");
		String num = scan.nextLine(); 
		
		//Y를 누른다면 수정화면으로 넘어가기
		if (num.equalsIgnoreCase("Y")) {
			updateUser(login);
			
		//N을 누른다면 전 화면으로 돌아가기	
		} else if (num.equalsIgnoreCase("N")) {
			showMyPage(login);
			
		//그 외에 다른걸 누른다면 다시 회원 정보 조회 화면 보여주기	
		} else {
			System.out.println("잘못된 번호입니다. 다시 입력해주십시오.");
			selectUser(login);
		}
	}
	
	public static void updateUser(User login) {
		
		System.out.println();
		System.out.println("==================회원 정보 수정==================");
		System.out.println("1. 사 용 자 : " + login.getName());
		System.out.println("2. 아 이 디 : " + login.getId());
		System.out.println("3. 비밀번호 : " + login.getPw());
		System.out.println("4. 생년월일 : " + login.getBirth());
		System.out.println("5. 성    별 : " + (login.getGender().equals("1") ? "남자" : "여자") );
		System.out.println("6. 전화번호 : " + login.getTel());
		System.out.println("7. 거 주 지 : " + login.getAddress());
		System.out.println("==================================================");
		
		Scanner scan = new Scanner(System.in);
		
		
		//수정할 번호를 입력받기
		System.out.println("수정할 번호를 눌러주십시오. 원치 않으시면 엔터를 눌러주십시오.");
		System.out.print("입력 : ");
		String num = scan.nextLine();
		
		//바꿀 정보 담기
		String nameChange = "";
		String idChange = "";
		String pwChange = "";
		String birthChange = "";
		String telChange = "";
		String addressChange = "";
		
		if (num.equals("1")) {
			System.out.println("기  존 사용자 : " + login.getName());
			System.out.print("수정할 사용자 : ");
			nameChange = scan.nextLine();
			
			//이름은 2-5자만 가능
			if (nameChange.length() < 2 || nameChange.length() > 5) {
				System.out.println("이름은 2-5자 입니다.");
				updateUser(login);
			}
			
			//이름은 한글만 사용 가능
			for (int i = 0; i < nameChange.length(); i++) {
				char c = nameChange.charAt(i);
				if (c < '가' || c > '힣') {
					System.out.println("이름은 한글만 가능합니다.");
					updateUser(login);
				}
			}
			
		} else if (num.equals("2")) {
			System.out.println("기  존 아이디 : " + login.getId());
			System.out.print("수정할 아이디 : ");
			idChange = scan.nextLine();
			
			//아이디는 4-16자만 가능
			String line = null;
			if (idChange.length() < 4 || idChange.length() > 16) {
				System.out.println("아이디는 4-16자 입니다.");
				updateUser(login);
			}
			
			//아이디의 첫 글자는 소문자만 가능
			if (idChange.charAt(0) < 'a' || idChange.charAt(0) > 'z') {
				System.out.println("아이디의 첫 글자는 소문자만 가능합니다.");
				updateUser(login);
			}
			
			//아이디는 영어 소문자와 숫자만 가능
			for (int i = 0; i < idChange.length(); i++) {
				char c = idChange.charAt(i);
				if (c >= 'a' && c <= 'z' || c >= '0' && c <= '9') {
					
				} else {
					System.out.println("아이디는 영어 소문자와 숫자만 가능합니다.");
					updateUser(login);
				}
			}
			
			//동일한 아이디는 사용 불가능
			try {

				BufferedReader reader = new BufferedReader(new FileReader(Path.USERLIST));
				
				while ((line = reader.readLine()) != null) {
					String[] list = line.split(","); 
					if (idChange.equals(list[3])) {
						System.out.println("동일한 아이디가 존재합니다.");
						updateUser(login);
					}
				}

			} catch (Exception e) {
				System.out.println("UserMyPage.idCheck()");
				e.printStackTrace();
			}
			
		} else if (num.equals("3")) {
			System.out.println("기  존 비밀번호 : " + login.getPw());
			System.out.print("수정할 비밀번호 : ");
			pwChange = scan.nextLine();
			
			//비밀번호는 10-16자만 가능
			if (pwChange.length() < 8 || pwChange.length() > 16) {
				System.out.println("비밀번호는 10-16자 입니다.");
				updateUser(login);
			}
			
			//비밀번호는 영문자, 숫자, 특수문자만 입력이 가능
			for (int i = 0; i < pwChange.length(); i++) {
				char c = pwChange.charAt(i);
				if (c > '가' && c < '힣') {
					System.out.println("비밀번호는 영문자, 숫자, 특수문자만 입력이 가능합니다.");
					updateUser(login);
				}
			}
			
			//아이디와 비밀번호는 동일할 수 없음
			if (pwChange.equals(login.getId())) {
				System.out.println("아이디와 비밀번호는 동일할 수 없습니다.");
				updateUser(login);
			}
			
		} else if (num.equals("4")) {
			
			System.out.println("기  존 생년월일 : " + login.getBirth());
			System.out.print("수정할 생년월일 : ");
			birthChange = scan.nextLine();
			
			//XXXXXXXX 일 경우 번호 중간에 하이픈 추가
			if (birthChange.length() == 8) {
				birthChange = String.format("%s-%s-%s"
										, birthChange.substring(0, 4)
										, birthChange.substring(4, 6)
										, birthChange.substring(6, 8));
			} else if (birthChange.length() == 10) { //XXXX-XX-XX 일 경우는 넘김
			} else {
				System.out.println("생년월일의 형식은 XXXX-XX-XX 입니다.");
				updateUser(login);
			}

			
		} else if (num.equals("5")) { 
			
			System.out.println("성별은 수정할 수 없습니다.");
			
		} else if (num.equals("6")) {
			System.out.println("기  존 전화번호 : " + login.getTel());
			System.out.print("수정할 전화번호 : ");
			telChange = scan.nextLine();
			
			//숫자와 '-'만 입력이 가능
			for (int i = 0; i < telChange.length(); i++) {
				char c = telChange.charAt(i);
				if (c >= '0' && c <= '9' || c == '-') {
					if (i == telChange.length() - 1) {
						
						//하이픈은 입력유무와 상관이 없음
						telChange = telChange.replace("-", "");
						
					}
				} else {
					System.out.println("전화번호는 숫자와 '-'만 가능합니다");
					updateUser(login);
				}
			}
			
		} else if (num.equals("7")) {
			System.out.println("기  존 거주지 : " + login.getAddress());
			System.out.print("수정할 거주지 : ");
			addressChange = scan.nextLine();
			
			//시 구 동을 입력해야함
			if (addressChange.indexOf("시") == -1) {
				System.out.println("'시'를 입력해주세요.");
				updateUser(login);
			} else if (addressChange.indexOf("구") == -1) {
				System.out.println("'구'를 입력해주세요.");
				updateUser(login);
			} else if (addressChange.indexOf("동") == -1) {
				System.out.println("'동'을 입력해주세요.");
				updateUser(login);
			}
			
		} else {
			return;
		}
		
		try {
			
			
			BufferedReader reader = new BufferedReader(new FileReader(Path.USERLIST));
			
			String line = null;
			
			String data = "";
			while ((line = reader.readLine()) != null) {
				String[] list = line.split(",");
					//이름 바꾸기
					if (num.equals("1") && list[0].equals(login.getCode()) && list[1].equals(login.getName())){
						line = line.replace(login.getName(), nameChange);
						login.setName(nameChange);
					//아이디 바꾸기
					} else if (num.equals("2") && list[0].equals(login.getCode()) && list[3].equals(login.getId())) {
						line = line.replace(login.getId(), idChange);
						login.setId(idChange);
					//비밀번호 바꾸기	
					} else if (num.equals("3") && list[0].equals(login.getCode()) && list[4].equals(login.getPw())) {
						line = line.replace(login.getPw(), pwChange);
						login.setPw(pwChange);
					//생년월일 바꾸기	
					} else if (num.equals("4") && list[0].equals(login.getCode()) && list[2].equals(login.getBirth())) {
						line = line.replace(login.getBirth(), birthChange);
						login.setBirth(birthChange);
					//전화번호 바꾸기	
					} else if (num.equals("6") && list[0].equals(login.getCode()) && list[6].equals(login.getTel())) {
						line = line.replace(login.getTel(), telChange);
						login.setTel(telChange);
					//주소 바꾸기	
					} else if (num.equals("7") && list[0].equals(login.getCode()) && list[8].equals(login.getAddress())) {
						line = line.replace(login.getAddress(), addressChange);
						login.setAddress(addressChange);
					}
				data += line + "\n";
			}
			
			reader.close();
			
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(Path.USERLIST));
			writer.write(data);
			writer.close();
			
			updateUser(login);


		} catch (Exception e) {
			System.out.println("UserMyPage.updateUser()");
			e.printStackTrace();
		}
		
		
		
	}


//	ArrayList<User> uList = loadUserData();
//	String nameChange = login.getName();
//	String idChange = login.getId();
//	String pwChange = login.getPw();
//	
//	if(num.equals("1")) {
//		System.out.println("기  존 아이디 : " + login.getId());
//		System.out.print("수정할 아이디 : ");
//		idChange = scan.nextLine();
//	}
//	
//	String data = "";
//	//탐색 = 자기정보
//	for(User u : uList) {
//		if(u.getCode().equals(login.getCode())) {
//			u.setName(idChange);
//			break;
//		}
//		data += u.getCode();
//		
//	}
//	
//	
//	try {
//
//		BufferedWriter writer = new BufferedWriter(new FileWriter(Path.USERLIST));
//		
//		writer.write(str);
//
//	} catch (Exception e) {
//		System.out.println("UserMyPage.updateUser()");
//		e.printStackTrace();
//	}	
	
//	private static ArrayList<User> loadUserData() {
//		
//		try {
//			
//			ArrayList<User> userList = new ArrayList<User>();
//			BufferedReader reader = new BufferedReader(new FileReader(Path.USERLIST));
//			
//			String line = null;
//			while((line = reader.readLine()) != null) {
//				String[] temp = line.split(",");
//				userList.add(new User(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7], temp[8] ));
//			}
//			
//			reader.close();
//			return userList;
//			
//		} catch (Exception e) {
//			System.out.println("UserMyPage.loadUserData()");
//			e.printStackTrace();
//		}
//		return null;
//	}

}










