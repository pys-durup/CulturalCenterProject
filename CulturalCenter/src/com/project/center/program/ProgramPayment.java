package com.project.center.program;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import com.project.center.user.User;

import data.Path;

public class ProgramPayment {

	private ProgramList paymentProgram;
	private User user; // 로그인 한 유저
	
	public ProgramPayment(ProgramList paymentProgram, User user) {
		this.paymentProgram = paymentProgram;
		this.user = user;
	}
	
	// ProgramList >[프로그램 이름][강사명][강의실][시작 날짜][종료 날짜][정원][현재상태][가격]
	// ProgramPaymentInfo > 결제코드, 프로그램코드, 회원번호, 결제일, 가격, 결제수단
	
	// 결제 진행하는 메서드
	public void createPayment() {
		/*
			[신청 프로그램 정보]
			프로그램 이름 : 테스트 프로그램
			가격 : 1,000,000원
			마일리지 적립액 : 50,000원
		 */
		
		System.out.println("[신청 프로그램 정보]");
		System.out.printf("프로그램 이름 : %s\n", this.paymentProgram.getName());
		System.out.printf("가격 : %,d원\n",this.paymentProgram.getPrice());
		System.out.printf("적립 마일리지 : %,d점\n", (int)(this.paymentProgram.getPrice() * 0.05));
		
		System.out.println("결제 수단을 선택하세요");
		System.out.println("1. 휴대폰  2. 카드");
		System.out.print("번호를 입력하세요 : ");
		int num = selectNum();
		
		if (num == 1) {// 휴대폰
			if(phonePayment()) { // 휴대폰 결제 성공시
				System.out.println("결제 성공");
			} else { // 휴대폰 결제 실패시
				System.out.println("결제 실패");
			}
		}else if (num == 2) { // 카드
			cardPayment();
			
		}
	}
	
	// 휴대폰 결제 메서드
	private boolean phonePayment() {
		System.out.println("휴대폰 결제 메서드 입니다");
		System.out.print("'-'를 제외한 휴대폰 번호를 입력하세요 : ");
		Scanner scan = new Scanner(System.in);
		String phone = scan.nextLine();
		
		if(this.user.getTel().equals(phone)) {
			// 휴대폰 결제 성공 > 프로그램결제정보.txt에 추가하는 메서드
			System.out.println("결제 성공 > 프로그램결제정보.txt에 추가하는 메서드");
			// 오늘 날짜
			Calendar now = Calendar.getInstance();
			// 저장할 데이터 생성
			// 결제코드, 프로그램코드, 회원번호, 결제일, 가격, 결제수단
			String paymentData = "CODE" + ","
										+ this.paymentProgram.getCode() + ","// 프로그램 코드
										+ this.user.getCode() + ","
										+ calToStirng(now) + ","
										+ this.paymentProgram.getPrice() + ","
										+ "1" + "\n";
			
			savePaymentData(paymentData);
			
			pause();
			return true;
		} else { // 휴대폰 결제 실패
			return false;
		}
	}
	
	// 카드 결제 메서드
	private boolean cardPayment() {
		System.out.println("카드 결제 메서드 입니다");
		pause();
		return false;
	}
	
	// 프로그램결제정보.txt에 추가하는 메서드
	private void savePaymentData(String data) {
		try {
			System.out.println("savePaymentData method");
			BufferedWriter writer = new BufferedWriter(new FileWriter(Path.PROGRAMPAYMENT, true));
			writer.write(data);
			writer.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryProgramPayment.ensavePaymentData()");
			e.printStackTrace();
		}
	}
	
	// 프로그램수강생.txt 파일 수정하는 메서드
	
	
	// Calendar -> String
	public static String calToStirng(Calendar c) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
	}
	
	// 페이지 클리어
	private static void clearPage() {
		for (int i = 0; i < 15; i++) {
			System.out.println();
		}

	}

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
		System.out.println("일시정지");
		scan.nextLine();
		clearPage();

	}
}
