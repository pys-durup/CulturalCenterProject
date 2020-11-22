package com.project.center.faciltiy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

import com.project.center.user.User;

import data.Path;

public class GymReservation {
	
	static Calendar c = Calendar.getInstance();
	
	static ArrayList<Gym> gymList = new ArrayList<Gym>();
	
	public static void reservationGym(User login) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.println("================목록================");
		System.out.println("\t  체육 시설 목록");
		System.out.println("\t1. 축구\t  2. 농구");
		System.out.println("\t3. 풋살\t  4. 탁구");
		System.out.println("\t5. 테니스 6. 배드민턴");
		System.out.println("\t7. 요가\t  8. 뒤로가기");
		System.out.println("====================================");
		System.out.print("번호를 선택하세요 : ");
		int room = scan.nextInt();
		System.out.println();
		
		try {

			BufferedReader reader = new BufferedReader(new FileReader(Path.GYMRESERVATION));
			
			String line = null;
			
			gymList = new ArrayList<Gym>();
			
			while ((line = reader.readLine()) != null) {
				String[] list = line.split(",");
				if (room == 1) {
					if (list[0].equals("축구")) {
						dataGym(gymList, list);
					}
				} else if (room == 2) {
					if (list[0].equals("농구")) {
						dataGym(gymList, list);
					}
				} else if (room == 3) {
					if (list[0].equals("풋살")) {
						dataGym(gymList, list);
					}
				} else if (room == 4) {
					if (list[0].equals("탁구")) {
						dataGym(gymList, list);
					}
				} else if (room == 5) {
					if (list[0].equals("테니스")) {
						dataGym(gymList, list);
					}
				} else if (room == 6) {
					if (list[0].equals("배드민턴")) {
						dataGym(gymList, list);
					}
				} else if (room == 7) {
					if (list[0].equals("요가")) {
						dataGym(gymList, list);
					}
				} else if (room == 8) {
					return;
				}
			}
			
			reader.close();
			

		} catch (Exception e) {
			System.out.println("GymReservation.main()");
			e.printStackTrace();
		}
		
		calendarGym(login, c, c.get(Calendar.YEAR), c.get(Calendar.MONTH));
		
	}

	//ArrayList gymList에 String name, String date, String time, String price 데이터 넣기
	private static void dataGym(ArrayList<Gym> gymList, String[] list) {
		
		Gym gym;
		gym = new Gym(list[0], list[2], list[3], list[4]);
		gymList.add(gym);
		
	}

	//달력 출력하기
	private static void calendarGym(User login, Calendar c, int year, int month) {
		
		Scanner scan = new Scanner(System.in);
		
		c.set(year, month, 1);
		
		System.out.printf("=========================[%s]=========================\n", gymList.get(0).getName());
		System.out.printf("\t\t%11d년 %d월\n", year, month + 1);
		System.out.println("========================================================");
		System.out.println("[일]\t[월]\t[화]\t[수]\t[목]\t[금]\t[토]\t");
		
		int dateEnd = c.getActualMaximum(Calendar.DATE);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		
		//Day와 Count값을 넣을 HashMap
		HashMap<Integer, Integer> dateState = new HashMap<Integer, Integer>();
		
		//달력 출력
		//그 달의 첫 째 날의 요일을 구하고 그만큼 앞에 \t
		for (int i = 1; i < dayOfWeek; i++) {
			System.out.print("\t");
		}
		
		int count = 0;
		
		//달력에 일 찍기
		for (int i = 1; i <= dateEnd; i++) {
			
			//1. date condition 날짜 상태 확인
			//2. reader 리더로 읽어와서
			//3. loop 반복문으로 그 날짜 옆에 날짜 세기
			//4. count++; 카운트
			count = 0;
			
			//XXXX-XX-XX이 같으면 count++;
			for (int j = 0; j < gymList.size(); j++) {
				if (Integer.parseInt(gymList.get(j).getDate().substring(0,4)) == c.get(Calendar.YEAR)
						&& Integer.parseInt(gymList.get(j).getDate().substring(5, 7)) == (c.get(Calendar.MONTH) + 1)
						&& Integer.parseInt(gymList.get(j).getDate().substring(8, 10)) == i) {
					if (count < 4) {
						count++;
					}
				}
			}	
			
			// 만약 일이 한자리수일경우 스페이스바 추가(칸 맞추기)
			if (i < 10) {
				System.out.print(" ");
			}
			
			//날짜와 count 출력
			System.out.printf("%d(%d/%d)\t", i, count, 4);
		
			
			//날짜와 count값을 HashMap에 추가
			dateState.put(i, count);
			
			//토요일이 되면 한칸 아래로 엔터, 토요일이 마지막날이면 엔터X
			if ((i + dayOfWeek - 1) % 7 == 0 && i == dateEnd) {
			} else if ((i + dayOfWeek - 1) % 7 == 0) {
				System.out.println();
			}
			
		}
		
		System.out.println();
		System.out.println("========================================================");
		System.out.println("1. 이전 페이지\t2. 다음페이지\t3. 날짜선택  4. 뒤로가기");
		System.out.println("========================================================");
		System.out.print("번호를 선택하세요 : ");
		int num = scan.nextInt();
		
		System.out.println("========================================================");
		
		//이전 페이지
		if (num == 1) {
			
			//년도 바꾸기
			if (month == 0) {
				calendarGym(login, c, year-1, month +11);
			} else {
				calendarGym(login, c, year, month-1);
			}
		//다음 페이지
		} else if (num == 2) {
			
			//년도 바꾸기
			if (month == 11) {
				calendarGym(login, c, year+1, month-11);
			} else {
				calendarGym(login, c, year, month+1);
			}
			
		//날짜 정하기
		} else if (num == 3) {
			dateGym(login, month, dateState);
			return;
			
		//뒤로 가기
		} else if (num == 4) {
			
			count = 0;
			//뒤로가면서 ArrayList에 있는 name값 초기화
			gymList = new ArrayList<Gym>();
			return;
		}
	
	}
	
	//그 날에 가능한 날인지 체크하는 메서드
	private static void dateGym(User login, int month, HashMap<Integer, Integer> dateState) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("원하시는 날짜를 선택하세요 : ");
		int day = scan.nextInt();
		
		if (day <= 0 || day > 31) {
			System.out.println("존재하지 않는 날짜입니다.");
			dateGym(login, month, dateState);
		} 
		
		if (dateState.get(day) < 4 && dateState.get(day) >= 0) {
			System.out.println("===============================");
			System.out.printf("[%d일]을 선택했습니다.\n", day);
			timeGym(login, month, day);
			
		} else if (dateState.get(day) >= 4) {
			System.out.println("===============================");
			System.out.printf("%[d일]은 자리가 없는 날짜입니다.", day);
			dateGym(login, month, dateState);
		}
		
		
		dateState = new HashMap<Integer, Integer>();
		
		return;

	}
	
	private static void timeGym(User login, int month, int day) {
		
		Scanner scan = new Scanner(System.in);
		
		ArrayList<Gym> gymTimeList = new ArrayList<Gym>();
		
		//그 날짜의 시간대만 List에 다시 추가
		for (int i = 0; i < gymList.size(); i++) {
			if (Integer.parseInt(gymList.get(i).getDate().substring(0,4)) == c.get(Calendar.YEAR)
					&& Integer.parseInt(gymList.get(i).getDate().substring(5, 7)) == (month + 1)
					&& Integer.parseInt(gymList.get(i).getDate().substring(8, 10)) == day) {
				
				Gym timeGym = new Gym(gymList.get(i).getTime());
				gymTimeList.add(timeGym);
				
			}
		}
		
		//default 값은 예약가능
		String checkeight = "예약가능";
		String checkten = "예약가능";
		String checktwo = "예약가능";
		String checkfour = "예약가능";
		String available = "예약가능";
		String completed = "예약완료";
		
		//만약 값이 있다면 예약완료로 변경
		for (int i = 0; i < gymTimeList.size(); i++) {
			if (gymTimeList.get(i).getTime().indexOf("08:00") > -1) {
				checkeight = completed;
			}
			
			if (gymTimeList.get(i).getTime().indexOf("10:00") > -1) {
				checkten = completed;
			}
			
			if (gymTimeList.get(i).getTime().indexOf("14:00") > -1) {
				checktwo = completed;
			}	
			
			if (gymTimeList.get(i).getTime().indexOf("16:00") > -1) {
				checkfour = completed;
			}
		}
		
		//시간을 넘겨주기 위한 값
		String eight = "08:00 ~ 10:00";
		String ten = "10:00 ~ 12:00";
		String two = "14:00 ~ 16:00";
		String four = "16:00 ~ 18:00";
		
		System.out.println("===============================");
		System.out.printf("[%d월 %d일 %s 체육시설 시간표]\n", month + 1, day, gymList.get(0).getName());
		System.out.printf("1. 08:00 ~ 10:00 [%s]\n", checkeight);
		System.out.printf("2. 10:00 ~ 12:00 [%s]\n", checkten);
		System.out.printf("3. 14:00 ~ 16:00 [%s]\n", checktwo);
		System.out.printf("4. 16:00 ~ 18:00 [%s]\n", checkfour);
		System.out.println("===============================");
		System.out.println("1. 예약하기\t2. 뒤로가기");
		System.out.println("===============================");
		System.out.print("번호를 선택하세요 : ");
		int num = scan.nextInt();
		System.out.println("====================================");
		
		//예약하기를 선택하면 받는 값들
		if (num == 1) {
			System.out.print("예약할 시간의 번호를 선택하세요 : ");
			int reservation = scan.nextInt();
			System.out.println("====================================");
			
			//08:00 ~ 10:00
			if (reservation == 1) {
				
				if (checkeight.equals(available)) {
					paymentGym(login, month, day, eight);
				} else {
					System.out.println("이미 예약이 완료된 시간입니다.");
					timeGym(login, month, day);
				}
			
			//10:00 ~ 12:00
			} else if (reservation == 2) {
				
				if (checkten.equals(available)) {
					paymentGym(login, month, day, ten);
				} else {
					System.out.println("이미 예약이 완료된 시간입니다.");
					timeGym(login, month, day);
				}
				
			//14:00 ~ 16:00
			} else if (reservation == 3) {
				
				if (checktwo.equals(available)) {
					paymentGym(login, month, day, two);
				} else {
					System.out.println("이미 예약이 완료된 시간입니다.");
					timeGym(login, month, day);
				}
			
			//16:00 ~ 18:00
			} else if (reservation == 4) {
				
				if (checkfour.equals(available)) {
					paymentGym(login, month, day, four);
				} else {
					System.out.println("이미 예약이 완료된 시간입니다.");
					timeGym(login, month, day);
				}
			}
			
		} else {
			System.out.println("뒤로가기");
			System.out.println("=================================");
			return;
		}
		
	}
	
	private static void paymentGym(User login, int month, int day, String time) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("====================");
		System.out.println("[체육시설 예약 정보]");
		System.out.printf("날짜 : %d월 %d일\n", month + 1, day);
		System.out.printf("시설 : %s실\n", gymList.get(0).getName());
		System.out.printf("시간 : %s\n", time);
		System.out.printf("가격 : %,d원\n", Integer.parseInt(gymList.get(0).getPrice()));
		System.out.println("====================");
		
		System.out.println();
		
		System.out.println("=================================");
		System.out.println("[결제 수단 선택]");
		System.out.println("1. 카드\t 2. 휴대폰   3. 결제 취소");
		System.out.println("=================================");
		System.out.print("결제 수단을 입력하세요 : ");
		int pay = scan.nextInt();
		System.out.println("=================================");
		
		if (pay == 1) {
			
			//휴대폰 결제
			cardpayGym(login, month, day, time);
			
		} else if (pay == 2) {
			
			//카드 결제
			phonePayGym(login, month, day, time);
			
		} else if (pay == 3){
			
			//결제 취소
			System.out.println("결제를 취소합니다.");
			System.out.println("===================");
			return;
		}
		
	}
	

	private static void cardpayGym(User login, int month, int day, String time) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("================");
		System.out.println("[카드 결제 진행]");
		System.out.println("================");
		System.out.print("카드 번호 입력 : ");
		String cardNumber = scan.nextLine();
		System.out.print("카드 비밀번호 입력 : ");
		String cardPw = scan.nextLine();

		System.out.println();

		System.out.println("결제 진행중...");

		System.out.println();

		System.out.println("결제가 완료되었습니다.");
		System.out.println("==============================");
		System.out.printf("%s실 예약에 성공했습니다.", gymList.get(0).getName());
		System.out.println("==============================");
		String type = "1";
		
		reservationgGymData(login, month, day, time, type);
		
	}

	private static void phonePayGym(User login, int month, int day, String time) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("==================");
		System.out.println("[휴대폰 결제 진행]");
		System.out.println("==================");
		System.out.print("휴대폰 번호 입력 : ");
		String phoneNumber = scan.nextLine();
		System.out.print("결제 비밀번호 입력 : ");
		String phonePw = scan.nextLine();
		
		System.out.println();
		
		System.out.println("\t결제 진행중...");
		
		System.out.println();
		
		System.out.println("결제가 완료되었습니다.");
		System.out.println("==============================");
		System.out.printf("%s실 예약에 성공했습니다.\n", gymList.get(0).getName());
		System.out.println("==============================");
		String type = "2";
		
		reservationgGymData(login, month, day, time, type);
		
	}

	
	private static void reservationgGymData(User login, int month, int day, String time, String type) {
		
		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(Path.GYMRESERVATION, true));
			
			if (time.equals("08:00 ~ 10:00")) {
				time = "08:00";
			} else if (time.equals("10:00 ~ 12:00")) {
				time = "10:00";
			} else if (time.equals("14:00 ~ 16:00")) {
				time = "14:00";
			} else if (time.equals("16:00 ~ 18:00")) {
				time = "16:00";
			}
			
			//농구,3587,2020-11-01,08:00,30000,2
			writer.write(String.format("%s,%s,%d-%d-%02d,%s,%s,%s\r\n"
										, gymList.get(0).getName()
										, login.getCode()
										, c.get(Calendar.YEAR)
										, month + 1
										, day
										, time
										, gymList.get(0).getPrice()
										, type));
			
			writer.close();

		} catch (Exception e) {
			System.out.println("GymReservation.cardpayGym()");
			e.printStackTrace();
		}
	}
	
}









