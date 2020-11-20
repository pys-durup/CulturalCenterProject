package com.project.center.program;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.project.center.user.User;

import data.Path;

public class ProgramPayment {

	private ProgramList paymentProgram;
	private User user; // 로그인 한 유저
	private ArrayList<ProgramStudent> psList;

	
	public ProgramPayment(ProgramList paymentProgram, User user) {
		this.paymentProgram = paymentProgram;
		this.user = user;
		psList = loadProgramStudentData();
	}
	
	public ProgramPayment(User user) {
		this.user = user;
	}
	
	// ProgramList >[프로그램 이름][강사명][강의실][시작 날짜][종료 날짜][정원][현재상태][가격]
	// ProgramPaymentInfo > 결제코드, 프로그램코드, 회원번호, 결제일, 가격, 결제수단
	
	/**
	 * 	프로그램 결제가 진행되는 메서드
	 *  
	 */
	public void createPayment() {
		/*
			[신청 프로그램 정보]
			프로그램 이름 : 테스트 프로그램
			가격 : 1,000,000원
			마일리지 적립액 : 50,000원
		 */
		clear();
		System.out.println("======================================================");
		System.out.println("\t<프로그램 결제하기>");
		System.out.println("======================================================");
		System.out.println("\t[신청 프로그램 정보]");
		System.out.printf("프로그램 이름 : %s\n", this.paymentProgram.getName());
		System.out.printf("가격 : %,d원\n",this.paymentProgram.getPrice());
		System.out.printf("적립 마일리지 : %,d점\n", (int)(this.paymentProgram.getPrice() * 0.05));
		System.out.println("======================================================");
		System.out.println();
		System.out.println("결제 수단을 선택하세요");
		System.out.println("1. 휴대폰  2. 카드  3.결제 취소하기");
		System.out.print("번호를 입력하세요 : ");
		int num = selectNum();
		
		if (num == 1) {// 휴대폰
			if(phonePayment()) { // 휴대폰 결제 성공시
//				System.out.println("휴대폰 결제 성공");
			} else { // 휴대폰 결제 실패시
				System.out.println();
				System.out.println("======================================================");
				System.out.println("결제에 실패했습니다");
				System.out.println("휴대폰 결제 실패");
				System.out.println("======================================================");
				pause();
			}
		}else if (num == 2) { // 카드
			if(cardPayment()) { // 카드 결제 성공시
//				System.out.println("카드 결제 성공");
			} else { // 카드 결제 실패시
				System.out.println();
				System.out.println("======================================================");
				System.out.println("결제에 실패했습니다");
				System.out.println("카드 결제 실패");
				System.out.println("======================================================");
				pause();
			}
			
		}else if (num == 3) {
			// 뒤로가기
			pause();
		}else {
			//올바르지 않은 번호
		}
	}
	
	/**
	 * 	휴대폰 결제 메서드
	 *  
	 */
	private boolean phonePayment() {
		System.out.println();
		System.out.println("======================================================");
		System.out.println("휴대폰 결제를 시작합니다");
		System.out.println("======================================================");
		System.out.print("'-'를 제외한 휴대폰 번호를 입력하세요 : ");
		Scanner scan = new Scanner(System.in);
		String phone = scan.nextLine();
		
		if(this.user.getTel().equals(phone)) {
			// 휴대폰 결제 성공 > 프로그램결제정보.txt에 추가하는 메서드
			// ============================ 결제에서 중복되는 부분 =====================================================
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
			
			// String paymentCode, String programCode, String userCode, String paymentDate, int price, String paymentType
			// ProgramPaymentInfo 결제정보 객체 생성
			ProgramPaymentInfo paymentObject = new ProgramPaymentInfo("CODE"
																	,this.paymentProgram.getCode()
																	,this.user.getCode()
																	,calToStirng(now)
																	,this.paymentProgram.getPrice()
																	,"1");
			// 프로그램결제정보.txt에 결제내용 추가하는 메서드
			savePaymentData(paymentData);
			// 프로그램수강생.txt 파일 수정하는 메서드
			modifyStudentData(paymentObject);
			System.out.println();
			System.out.println("======================================================");
			System.out.println("결제에 성공했습니다");
			System.out.println("휴대폰 결제 성공");
			System.out.println("======================================================");
			pause();
			// ============================ 결제에서 중복되는 부분 =====================================================
			return true;
		} else { // 휴대폰 결제 실패
			return false;
		}
	}
	
	/**
	 * 	카드 결제 메서드
	 *  
	 */
	private boolean cardPayment() {
		System.out.println("카드 결제 메서드 입니다");
		System.out.print("'-'를 포함한 카드번호를 입력하세요 : ");
		Scanner scan = new Scanner(System.in);
		String cardNum = scan.nextLine();
		
		// 카드번호 16자리인지만 검사
		if(Pattern.matches("[0-9]{16}", cardNum.replace("-", ""))) {
			// 카드 비밀번호 입력받는다
			System.out.print("카드 비밀번호를 입력하세요 : ");
			scan = new Scanner(System.in);
			String password = scan.nextLine();
			if(password.equals("1234")) { 
				
				// ============================ 결제에서 중복되는 부분 =====================================================
				// 오늘 날짜
				Calendar now = Calendar.getInstance();
				
				
				// 저장할 데이터 생성
				// 결제코드, 프로그램코드, 회원번호, 결제일, 가격, 결제수단
				String paymentData = "CODE" + ","
											+ this.paymentProgram.getCode() + ","// 프로그램 코드
											+ this.user.getCode() + ","
											+ calToStirng(now) + ","
											+ this.paymentProgram.getPrice() + ","
											+ "2" + "\n";
				
				// String paymentCode, String programCode, String userCode, String paymentDate, int price, String paymentType
				// ProgramPaymentInfo 결제정보 객체 생성
				ProgramPaymentInfo paymentObject = new ProgramPaymentInfo("CODE"
																		,this.paymentProgram.getCode()
																		,this.user.getCode()
																		,calToStirng(now)
																		,this.paymentProgram.getPrice()
																		,"2");
				// 프로그램결제정보.txt에 결제내용 추가하는 메서드
				savePaymentData(paymentData);
				// 프로그램수강생.txt 파일 수정하는 메서드
				modifyStudentData(paymentObject);
				System.out.println();
				System.out.println("======================================================");
				System.out.println("결제에 실패했습니다");
				System.out.println("카드 결제 실패");
				System.out.println("======================================================");
				pause();
				// ============================ 결제에서 중복되는 부분 =====================================================
				return true;
			} else {
//				System.out.println("카드 비밀번호가 일치하지 않음");
				return false;
			}
		} else {
			System.out.println("실패");
			return false;
		}
		
	}
	
	// 
	/**
	 * 	프로그램결제정보.txt에 결제내용 추가하는 메서드
	 *  @param data : 결제를 완료한 데이터
	 *  
	 */
	private void savePaymentData(String data) {
		try {
//			System.out.println("savePaymentData method");
			BufferedWriter writer = new BufferedWriter(new FileWriter(Path.PROGRAMPAYMENT, true));
			writer.write(data);
			writer.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryProgramPayment.ensavePaymentData()");
			e.printStackTrace();
		}
	}
	
	/**
	 * 	프로그램수강생.txt 파일 수정하는 메서드
	 *  @param paymentInfo : 결제정보가 담긴 ProgramPaymentInfo 객체
	 *  
	 */
	private void modifyStudentData(ProgramPaymentInfo paymentInfo) {
		// this.psList -> 프로그램수강생.txt를 읽어온 리스트
		String programCode = paymentInfo.getProgramCode(); // 결제한 프로그램의 코드
		String userCdoe = paymentInfo.getUserCode(); // 결제한 사람의 회원 번호
		for(ProgramStudent ps : this.psList) {
			if(ps.getCode().equals(programCode)) {
				ps.setCount(ps.getCount()+1); // 수강정원 1 증가
				ps.getUserCodes().add(userCdoe); // 수강생 목록에 추가
				if(ps.getCount() == 20) {
					ps.setState("모집마감");
				}
			}
		}
		
		// 저장할 데이터를 만든다
		String data="";
		for(ProgramStudent ps : this.psList) {
			String userCodedata = "";
			ArrayList<String> temp = ps.getUserCodes();
			for(int i=0 ; i < temp.size() ; i++) {
				userCodedata += temp.get(i);
				if(i != temp.size() - 1) {
					userCodedata+="■";
				}
			}
			data += ps.getCode() + "," + ps.getCount() + "," + ps.getState() + "," + userCodedata + "\n";
		}
		
		// this.psList의 data를 프로그램수강생.txt 파일에 저장하는 메서드
		saveProgramsStudentData(data);
	}
	
	/**
	 * 	프로그램수강생.txt 파일을 읽어와서 ArrayList<ProgramStudent>를 반환하는 메서드
	 *    
	 */
	private ArrayList<ProgramStudent> loadProgramStudentData() {
		
		File file = new File(Path.PROGRAMSTUDENT);

		if (file.exists()) {

			try {
				ArrayList<ProgramStudent> list = new ArrayList<ProgramStudent>();
				BufferedReader reader = new BufferedReader(new FileReader(Path.PROGRAMSTUDENT));

				String line = null;

				while ((line = reader.readLine()) != null) {
					String[] temp = line.split(",");
					ArrayList<String> tempArray = new ArrayList<String>();

					String[] users = temp[3].split("■");
					for (int i = 0; i < users.length; i++) {
						tempArray.add(users[i]);
					}

					list.add(new ProgramStudent(temp[0], Integer.parseInt(temp[1]), temp[2], tempArray));
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
	
	/**
	 * 	this.psList를 프로그램수강생.txt 파일에 저장하는 메서드
	 *  @param data 저장할 데이터 
	 *    
	 */
	private void saveProgramsStudentData(String data) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(Path.PROGRAMSTUDENT));
			writer.write(data);
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryProgramPayment.ensaveProgramsStudentData()");
			e.printStackTrace();
		}
		
				
		
	}
	
	/**
	 * 	환불 진행 메서드
	 *  
	 */
	public void programRefund(Program program, ArrayList<ProgramPaymentInfo> paymentList) {
		
		while(true) {
			System.out.println();
			System.out.println("================================================");
			System.out.println("\t    <프로그램 환불진행>");
			System.out.println("================================================");
			System.out.println("[결제 정보]");
			System.out.println("프로그램이름 :" + program.getName());
			String paymentType = "";
			ProgramPaymentInfo refundInfo = null; // 프로그램환불.txt에 저장할 객체
			Calendar now = Calendar.getInstance(); // 오늘
			int index = 0;
			
			// 결제정보.txt 에서 제거할 인덱스 탐색
			for(int i=0 ; i<paymentList.size() ; i++) {
				if(paymentList.get(i).getProgramCode().equals(program.getCode()) && paymentList.get(i).getUserCode().equals(this.user.getCode())){
					paymentType = paymentList.get(i).getPaymentType(); // 결제시 사용한 결제수단 > 환불
					index = i;
					System.out.printf("결제일 : %s\n", paymentList.get(i).getPaymentDate());
					System.out.printf("가격 : %,d원\n",paymentList.get(i).getPrice());
					System.out.printf("결제수단 : %s\n" ,paymentList.get(i).getPaymentType().equals("1") ? "휴대폰" : "카드");
					refundInfo = new ProgramPaymentInfo("TTTT"
							, paymentList.get(i).getProgramCode()
							, paymentList.get(i).getUserCode()
							, calToStirng(now)
							, paymentList.get(i).getPrice()
							, paymentList.get(i).getPaymentType());
				}
			}
			System.out.println("================================================");
			System.out.println();
			System.out.println("1. 계속진행하기 2. 뒤로가기");
			System.out.print("번호를 입력하세요 : ");
			int num = selectNum();
			
			if(num == 1) { // 환불진행
				if(paymentType.equals("1")) { // 휴대폰결제 환불
					System.out.println();
					System.out.println("================================================");
					System.out.println("휴대폰결제 환불을 진행합니다");
					System.out.print("휴대폰번호 입력(- 제외) : ");
					String phone = selectString();
					if(phone.equals(this.user.getTel())) {
						// 환불계속진행
					} else {
						System.out.println();
						System.out.println("================================================");
						System.out.println("휴대폰번호가 일치하지 않습니다");
						System.out.println("프로그램 환불에 실패했습니다");
						pause();
						break;
					}
					
				} else { // 카드결제 환불
					System.out.println();
					System.out.println("================================================");
					System.out.println("카드결제 환불을 진행합니다");
					System.out.print("카드번호 입력 : ");
					String card = selectString();
					if(card.equals(this.user.getTel())) {
						// 환불계속진행
					} else {
						System.out.println();
						System.out.println("================================================");
						System.out.println("카드번호가 일치하지 않습니다");
						System.out.println("프로그램 환불에 실패했습니다");
						pause();
						break;
					}
				}
				
				// 여기서 환불진행
				
				// 환불한 프로그램 결제내역을 삭제
				paymentList.remove(index);
				
				
				// 프로그램수강생.txt 에서 제거 (프로그램코드로 탐색후 지워야함)
				ArrayList<ProgramStudent> psList = ProgramRegistrationList.loadProgramStudentData(Path.PROGRAMSTUDENT);
				
				for(ProgramStudent ps : psList) {
					if(ps.getCode().equals(program.getCode())) {
//						System.out.println("프로그램수강생.txt 수정");
						ps.getUserCodes().remove(this.user.getCode());
						int count = ps.getCount();
//						System.out.println("현재 수강인원" + count);
						count--;
						ps.setCount(count);
//						System.out.println("환불후 수강인원" + count);
						ps.setState("모집중");
					}
				}
				
				
				// 결제정보.txt에 저장할 데이터
				String paymentdata = "";
				for(ProgramPaymentInfo pinfo : paymentList) {
					paymentdata += pinfo.getPaymentCode() + ","
							+ pinfo.getProgramCode() + ","
							+ pinfo.getUserCode() + ","
							+ pinfo.getPaymentDate() + ","
							+ pinfo.getPrice() + ","
							+ pinfo.getPaymentType() + "\n";
				}
				
				// paymentdata를 결제정보.txt에 다시 저장
				saveData(paymentdata, Path.PROGRAMPAYMENT, false);
				
				
				
				
				// psList를 프로그램수강생.txt에 다시 저장
				// 저장할 데이터를 만든다
				String data="";
				for(ProgramStudent ps : psList) {
					String userCodedata = "";
					ArrayList<String> temp = ps.getUserCodes();
					for(int i=0 ; i < temp.size() ; i++) {
						userCodedata += temp.get(i);
						if(i != temp.size() - 1) {
							userCodedata+="■";
						}
					}
					data += ps.getCode() + "," + ps.getCount() + "," + ps.getState() + "," + userCodedata + "\n";
				}
				
				// this.psList의 data를 프로그램수강생.txt 파일에 저장하는 메서드
				saveProgramsStudentData(data);
				
				
				// TTTT,AD070199,5474,2020-11-13,200000,1
				// 프로그램환불.txt에 추가 : refundInfo객체
				String refundData = refundInfo.getPaymentCode() + ","
						+ refundInfo.getProgramCode() + ","
						+ refundInfo.getUserCode() + ","
						+ refundInfo.getPaymentDate() + ","
						+ refundInfo.getPrice() + ","
						+ refundInfo.getPaymentType() + "\n";
				
				
				
				// refundData를 프로그램환불.txt에 저장
				saveData(refundData, Path.PROGRAMREFUND, true);
				
				System.out.println("프로그램 환불이 완료되었습니다");
				pause();
				break;
				
			} else if(num == 2) { // 뒤로가기
				break;
			} else {
				break;
			}
		}
	}
	
	// 데이터를 저장하는 메서드
	private void saveData(String data, String path, Boolean append) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path, append));
			
			writer.write(data);
			
			writer.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryProgramPayment.ensavePaymemtData()");
			e.printStackTrace();
		}
	}
	

	
	
	
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
		return Integer.parseInt(scan.nextLine());
	}
	
	private static String selectString() {

		// 사용자에게 번호를 입력받는다
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}

	// 일시정지
	private static void pause() {
		Scanner scan = new Scanner(System.in);
		System.out.println("엔터키를 누르면 돌아갑니다.");
		scan.nextLine();
		clearPage();

	}
	
	private void clear() {
		for (int i=0 ; i<40 ; i++) {
			System.out.println();
		}
	}
}
