package com.project.center.user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.center.main.CulturalCenter;

import data.Path;

public class UserRegister {
	
	public static void infoRegister() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("===================회원 가입 안내===================");
		System.out.println("1. ID는 4~16자만 가능합니다");
		System.out.println("2. ID는 영소문자와 숫자만 사용이 가능합니다.");
		System.out.println("3. ID는 첫 글자가 영소문자여야 합니다.");
		System.out.println("5. ID는 중복된 아이디 사용이 불가능합니다.");
		System.out.println("6. PW는 10~16자만 가능합니다.");
		System.out.println("7. PW는 영어대소문자, 숫자, 특수문자만 사용가능합니다.");
		System.out.println("8. 이름은 2~5자 한글만 사용 가능합니다.");
		System.out.println("9. 전화번호는 '-' 유무와 상관없이 작성가능합니다.");
		System.out.println("10. 생년월일은 'ex)XXXX-XX-XX' 의 형식입니다.");
		System.out.println("11. 주소는 'ex)XX시 XX구 XX동' 의 형식입니다.");
		System.out.println("===================================================");
		
		System.out.println("회원가입 하시겠습니까? (Y/N)");
		String check = scan.nextLine();
		
		if (check.equals("Y") || check.equals("y")) {
			insertUser();
		} else {
			CulturalCenter.main(null);
		}
		
		
	}
	
	public static void insertUser() {
		
		String name;
		String id;
		String pw;
		String birth;
		String gender;
		String tel;
		String address;
		String group;
		

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(Path.USERLIST, true));
			BufferedReader reader = new BufferedReader(new FileReader(Path.USERLIST));
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("==============회원 가입==============");
			
			//코드 마지막 번호 구하기
			String line = null;
			String lastcode = "";
			while ((line = reader.readLine()) != null) {
				String[] list = line.split(","); 
				lastcode = list[0];
			}
			int lastcodenum = Integer.parseInt(lastcode);
			
			System.out.print("이    름 : ");
			name = scan.nextLine();
			//이름 유효성 검사
			nameCheck(name);
			
			System.out.print("아 이 디 : ");
			id = scan.nextLine();
			//아이디 유효성 검사
			idCheck(id, reader);
			
			System.out.print("비밀번호 : ");
			pw = scan.nextLine();
			//비밀번호 유효성 검사
			pwCheck(pw);
			
			System.out.print("생년월일 : ");
			birth = scan.nextLine();
			//생년월일 유효성 검사
			birth = birthCheck(birth);
			
			System.out.print("성    별 (1.남 2.여) : ");
			gender = scan.nextLine();
			//성별 유효성 검사
			genderCheck(gender);
			
			System.out.print("전화번호 : ");
			tel = scan.nextLine();
			//전화번호 유효성 검사
			tel = telCheck(tel);
			
			System.out.print("주    소 : ");
			address = scan.nextLine();
			//주소 유효성 검사
			addressCheck(address);
			
			System.out.println("1.일반 2.기초생활수급자 3.차상위계층");
			System.out.print("분    류 : ");
			group = scan.nextLine();
			//분류 유효성 검사
			groupCheck(group);
			
			writer.write(String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s\r\n"
										,lastcodenum+1, name, birth, id, pw, gender, tel, group, address));
			
			System.out.println("=====================================");
			
			System.out.println("회원가입이 완료되었습니다.!");
			
			
			writer.close();
			

		} catch (Exception e) {
			System.out.print("UserRegister.main()");
			e.printStackTrace();
		}	
	}

	//그룹 유효성 검사
	private static void groupCheck(String group) {
		
		//1번과 2번과 3번만 체크 가능
		if (group.equals("1") && group.equals("2") && group.equals("3")) {	
		} else {
			System.out.println("선택지에서 선택해주시길 바랍니다.");
			insertUser();
		}
	}

	//주소 유효성 검사
	private static void addressCheck(String address) {
		
		//시 구 동을 입력해야함
		if (address.indexOf("시") == -1) {
			System.out.println("'시'를 입력해주세요.");
			insertUser();
		} else if (address.indexOf("구") == -1) {
			System.out.println("'구'를 입력해주세요.");
			insertUser();
		} else if (address.indexOf("동") == -1) {
			System.out.println("'동'을 입력해주세요.");
			insertUser();
		}
	}

	//전화번호 유효성 검사
	private static String telCheck(String tel) {
		
		//숫자와 '-'만 입력이 가능
		for (int i = 0; i < tel.length(); i++) {
			char c = tel.charAt(i);
			if (c >= '0' && c <= '9' || c == '-') {
				if (i == tel.length() - 1) {
					
					//하이픈은 입력유무와 상관이 없음
					tel = tel.replace("-", "");
					
				}
			} else {
				System.out.println("전화번호는 숫자와 '-'만 가능합니다");
				insertUser();
			}
		}
		return tel;
	}

	//성별 유효성 검사
	private static void genderCheck(String gender) {
		
		//1, 2, 남, 여 이외에는 거름
		if (gender.equals("1") || gender.equals("2")) {	
		} else if (gender.equals("남") || gender.equals("여")) {	
		} else {
			System.out.println("1.남 2.여 에서 선택해주십시오.");
			insertUser();
		}
	}

	//생년월일 유효성 검사
	private static String birthCheck(String birth) {
		
		//XXXXXXXX 일 경우 번호 중간에 하이픈 추가
		if (birth.length() == 8) {
			birth = String.format("%s-%s-%s"
									, birth.substring(0, 4)
									, birth.substring(4, 6)
									, birth.substring(6, 8));
		} else if (birth.length() == 10) { //XXXX-XX-XX 일 경우는 넘김
		} else {
			System.out.println("생년월일의 형식은 XXXX-XX-XX 입니다.");
			insertUser();
		}
		return birth;
	}

	//비밀번호 유효성 체크
	private static void pwCheck(String pw) {
		
		//비밀번호는 10-16자만 가능
		if (pw.length() < 8 || pw.length() > 16) {
			System.out.println("비밀번호는 10-16자 입니다.");
			insertUser();
		}
		
		//비밀번호는 영문자, 숫자, 특수문자만 입력이 가능
		for (int i = 0; i < pw.length(); i++) {
			char c = pw.charAt(i);
			if (c > '가' && c < '힣') {
				System.out.println("비밀번호는 영문자, 숫자, 특수문자만 입력이 가능합니다.");
				insertUser();
			}
		}
	}

	//아이디 유효성 체크
	private static void idCheck(String id, BufferedReader reader) throws IOException {
		
		//아이디는 4-16자만 가능
		String line = null;
		if (id.length() < 4 || id.length() > 16) {
			System.out.println("아이디는 4-16자 입니다.");
			insertUser();
		}
		
		//아이디의 첫 글자는 소문자만 가능
		if (id.charAt(0) < 'a' || id.charAt(0) > 'z') {
			System.out.println("아이디의 첫 글자는 소문자만 가능합니다.");
			insertUser();
		}
		
		//아이디는 영어 소문자와 숫자만 가능
		for (int i = 0; i < id.length(); i++) {
			char c = id.charAt(i);
			if (c >= 'a' && c <= 'z' || c >= '0' && c <= '9') {
				
			} else {
				System.out.println("아이디는 영어 소문자와 숫자만 가능합니다.");
				insertUser();
			}
		}
		
		//동일한 아이디는 사용 불가능
		try {

			reader = new BufferedReader(new FileReader(Path.USERLIST));
			
			while ((line = reader.readLine()) != null) {
				String[] list = line.split(","); 
				if (id.equals(list[3])) {
					System.out.println("동일한 아이디가 존재합니다.");
					insertUser();
				}
			}

		} catch (Exception e) {
			System.out.println("UserRegister.idCheck()");
			e.printStackTrace();
		}

	}
	
	//이름 유효성 체크
	private static void nameCheck(String name) {
		
		//이름은 2-5자만 가능
		if (name.length() < 2 || name.length() > 5) {
			System.out.println("이름은 2-5자 입니다.");
			insertUser();
		}
		
		//이름은 한글만 사용 가능
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (c < '가' || c > '힣') {
				System.out.println("이름은 한글만 가능합니다.");
				insertUser();
			}
		}
	}
	
	
}
