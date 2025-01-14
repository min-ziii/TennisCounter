package com.test.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

//기능 구현 Class
	public class TennisService {
	Scanner scan = new Scanner(System.in);
	TennisView view = new TennisView();
	
	int point = 0, previousPoint = 0, winningStreakA = 0, winningStreakB = 0;
	int ralleyPointA = 0, ralleyPointB = 0;
	String ralleyPointA_Visible = "";
	String ralleyPointB_Visible = "";
	int gamePointA = 0, gamePointB = 0;
	int setPointA = 0, setPointB = 0;
	int ralleyCount = 1, setCount = 1, gameCount = 1;
	StringBuilder pointData = new StringBuilder();
	boolean loop = true;
	String path = "Dummy\\Dummy.txt";
	
	// 경기 데이터를 계수기 프로그램에 입력
	public void recordCreate(TennisDTO dto, TennisDAO dao) {
				
		dao.getInfo(); // 점수 데이터를 입력하기 전에 경기와 관련된 사전 정보를 입력받음.
		//dao.getInfo()는 TennisDAO 클래스 안에서 TennisDTO의 Setter를 사용해 사용자로부터 입력받은 각종 데이터를 TennisDTO(인스턴스화되지 않은 순수 데이터 필드)로 넣는 역할을 한다.
		//TennisDTO가 전달받은 데이터들은 TennisDAO 안에서는 실제 데이터로 살아있을 수 있다. TennisDTO가 TennisDAO 안에서 dto = new TennisDTO(); 라는 실존하는 객체로 선언되었기 때문이다. 
		//하지만 DTO 필드 내의 값들은 TennisDAO가 아닌 TennisService 클래스에서는 이용할 수 없다. 이 클래스에서 새로 DTO객체를 선언한다고 해도 이미 받은 값들은 날아갈 뿐이다.
		//이것은 dao.getInfo()의 실행이 종료되면서 [객체.변수](ex-이름을 입력받고 DTO로 값을 넘겨주는 ArrayList<String>형 인스턴스 dao.player) 형식으로 담겨지지 않은 나머지 DTO값들이 사라지기 때문인 것으로 보인다.
		//..예를 들면 int라고 해도 값을 직접 넣지 말고 int형 변수를 따로 만들어서 그 int형 변수가 DTO를 참조하게 만드는 느낌인듯...?
		
		dto.setPlayerList(dao.player);
		dto.setGamePointA(point);
		dto.setGamePointB(point);
		
		System.out.println("경기 데이터를 입력받겠습니다.");
				
		while(loop) {
			
			if (dao.player.size() == 2) {
				checkRalleyPointVisible();
				System.out.printf("%s(%s) VS %s(%s)\n", dto.getPlayerList().get(0), ralleyPointA_Visible, dto.getPlayerList().get(1), ralleyPointB_Visible);
			} else if (dao.player.size() == 4) {
				checkRalleyPointVisible();
				System.out.printf("%s, %s(%d) VS %s, %s(%d)\n",
								  dao.player.get(0), dao.player.get(1), ralleyPointA_Visible, dao.player.get(2), dao.player.get(3), ralleyPointB_Visible);
			}
			
			System.out.printf("%d세트 %d게임의 %d번째 랠리에서 왼쪽이 득점했다면 0을, 오른쪽이 득점했다면 1을 입력해주세요.: "
							  ,setCount, gameCount, ralleyCount);
			point = scan.nextInt();
			

			startRalley(dto, dao);
			isGameEnds(dto, dao);
			isSetEnds(dto, dao);
			isMatchEnds(dto, dao);

			
		}
	}
	
	public void checkRalleyPointVisible() {
		if (ralleyPointA == 0) {
			ralleyPointA_Visible = "0점";
		} else if (ralleyPointA == 1) {
			ralleyPointA_Visible = "15점";
		} else if (ralleyPointA == 2) {
			ralleyPointA_Visible = "30점";
		} else if (ralleyPointA == 3) {
			ralleyPointA_Visible = "40점";
		} else if (ralleyPointA == 4) {
			ralleyPointA_Visible = "match point";
		}
		if (ralleyPointB == 0) {
			ralleyPointB_Visible = "0점";
		} else if (ralleyPointB == 1) {
			ralleyPointB_Visible = "15점";
		} else if (ralleyPointB == 2) {
			ralleyPointB_Visible = "30점";
		} else if (ralleyPointB == 3) {
			ralleyPointB_Visible = "40점";
		} else if (ralleyPointB == 4) {
			ralleyPointB_Visible = "match point";
		}
	}
	
	public void startRalley(TennisDTO dto, TennisDAO dao) {
		
		if (dao.player.size() == 2) {
			if (point == 0) { // 좌측 팀 득점
				System.out.printf("입력 완료. %s의 득점.\n\n", dao.player.get(0));
				pointData.append(Integer.toString(point)); // 01011...식 경기결과 데이터를 만들기 위해
				ralleyCount++; 						// 랠리 진행도
				if (ralleyPointA < 3) { 			// 40점까지는
					ralleyPointA++; 				// 카운트를 올려도 됨
					winningStreakB = 0;				// A가 이겼으니까 B의 연승 카운트 초기화
				}
				if (ralleyPointA == 3) { 			// 40점에 도달하면
					winningStreakA++;	 			// 연승카운트로 승리 조건 검사
					winningStreakB = 0;				// A가 이겼으니까 B의 연승 카운트 초기화
					if (winningStreakA == 2) { 	 	// 2연승이면
						ralleyPointA++; 			// match point(승리)
						checkRalleyPointVisible();
					}
				}
			}
			else if (point == 1) { // 우측 팀 득점
				System.out.printf("입력 완료. %s의 득점.\n\n", dao.player.get(1));
				pointData.append(Integer.toString(point));
				ralleyCount++;
				if (ralleyPointB < 3) {
					ralleyPointB++;
					winningStreakA = 0;
				}
				if (ralleyPointB == 3) {
					winningStreakB++;
					winningStreakA = 0;
					if (winningStreakB == 2) {
						ralleyPointB++;
						checkRalleyPointVisible();
					}
				}
			} else { // point를 0도 아니고 1도 아닌 값으로 입력받았으면
				System.out.println("올바르지 않은 데이터입니다. 다시 입력해주세요.");
			}
		} else if (dao.player.size() == 4) {
			if (point == 0) { // 좌측 팀 득점
				System.out.printf("입력 완료. %s, %s 팀의 득점.\n", dao.player.get(0),dao.player.get(1));
				pointData.append(Integer.toString(point));
				ralleyCount++;
				if (ralleyPointA < 3) {
					ralleyPointA++;
					winningStreakB = 0;
				}
				if (ralleyPointA == 3) {
					winningStreakA++;
					winningStreakB = 0;
					if (winningStreakA == 2) {
						ralleyPointA++; 
						checkRalleyPointVisible();
					}
				}
			} else if (point == 1) {
				System.out.printf("입력 완료. %s, %s 팀의 득점.\n", dao.player.get(2),dao.player.get(3));
				pointData.append(Integer.toString(point));
				ralleyCount++;
				if (ralleyPointB < 3) {
					ralleyPointB++;
					winningStreakA = 0;
				}
				if (ralleyPointB == 3) {
					winningStreakB++;
					winningStreakA = 0;
					if (winningStreakB == 2) {
						ralleyPointB++;
						checkRalleyPointVisible();
					}
				}
			} else {
				System.out.println("올바르지 않은 데이터입니다. 다시 입력해주세요.");
			}
		}
		
	}

	public void isGameEnds(TennisDTO dto, TennisDAO dao) {
		// 한 쪽이 2연승을 해서 match point를 땄으면 한 '게임'의 승패가 가려짐
		if (dao.player.size() == 2) { // 단식 경기
			if (ralleyPointA == 4) {
				System.out.printf("%s(%s) VS %s(%s)\n", dto.getPlayerList().get(0), ralleyPointA_Visible, dto.getPlayerList().get(1), ralleyPointB_Visible);
				gamePointA++;
				System.out.printf("%s(이)가 연승하여 %d세트 %d게임의 승자가 되었습니다.\n\n", dao.player.get(0), setCount, gameCount);
				gameCount++;
				resetRalleys();
			} else if (ralleyPointB == 4) {
				System.out.printf("%s(%s) VS %s(%s)\n", dto.getPlayerList().get(0), ralleyPointA_Visible, dto.getPlayerList().get(1), ralleyPointB_Visible);
				gamePointB++;
				System.out.printf("%s(이)가 연승하여 %d세트 %d게임의 승자가 되었습니다.\n\n", dao.player.get(1), setCount, gameCount);
				gameCount++;
				resetRalleys();
			}
		} else if (dao.player.size() == 4) { // 복식 경기
			if (ralleyPointA == 4) {
				System.out.printf("%s(%s) VS %s(%s)\n", dto.getPlayerList().get(0), ralleyPointA_Visible, dto.getPlayerList().get(1), ralleyPointB_Visible);
				gamePointA++;
				System.out.printf("%s, %s 팀이 연승하여 %d세트 %d게임의 승자가 되었습니다.\n\n", dao.player.get(0), dao.player.get(1), setCount, gameCount);
				gameCount++;
				resetRalleys();
			} else if (ralleyPointB == 4) {
				System.out.printf("%s(%s) VS %s(%s)\n", dto.getPlayerList().get(0), ralleyPointA_Visible, dto.getPlayerList().get(1), ralleyPointB_Visible);
				gamePointB++;
				System.out.printf("%s, %s 팀이 연승하여 %d세트 %d게임의 승자가 되었습니다.\n\n", dao.player.get(2), dao.player.get(3), setCount, gameCount);
				gameCount++;
				resetRalleys();
			}
		}
			
	}

	public void resetRalleys() {
		ralleyPointA = 0;
		ralleyPointB = 0;
		winningStreakA = 0;
		winningStreakB = 0;
		ralleyCount = 1;
	}

	public void isSetEnds(TennisDTO dto, TennisDAO dao) {
	    System.out.println("gamePointA: " + gamePointA + ", gamePointB: " + gamePointB); // 값 확인용 로그
	    // 두 팀의 게임 점수가 '모두' 6점에 도달하면 타이브레이크 룰을 적용함
	    if (gamePointA == 6 && gamePointB == 6) {
	        System.out.println("타이브레이크가 시작됩니다");
	        tieBreak(dto, dao);
		} else if (gamePointA >= 6 || gamePointB >= 6) { // 둘 중 하나의 게임포인트가 6에 도달하면
			if ((Math.abs(gamePointA - gamePointB) >= 2)) { // 게임스코어 4이하:6 또는 6:4이하인 상황
				if (dao.player.size() == 2) {
					if (gamePointA > gamePointB) {
						setPointA++;
						System.out.printf("%d세트의 승자는 %s입니다.\n\n", setCount, dao.player.get(0));
						setCount++;
						gameCount = 1;
						resetRalleys();
					}
					else if (gamePointA < gamePointB) {
						setPointB++;
						System.out.printf("%d세트의 승자는 %s입니다.\n\n", setCount, dao.player.get(1));
						setCount++;
						gameCount = 1;
						resetRalleys();
					}
				} else if (dao.player.size() == 4) {
					if (gamePointA > gamePointB) {
						setPointA++;
						System.out.printf("%d세트의 승리팀은 %s, %s 팀입니다.\n\n", setCount, dao.player.get(0), dao.player.get(1));
						setCount++;
						gameCount = 1;
						resetRalleys();
					}
					else if (gamePointA < gamePointB) {
						setPointB++;
						System.out.printf("%d세트의 승리팀은 %s, %s 팀입니다.\n\n", setCount, dao.player.get(2), dao.player.get(3));
						setCount++;
						gameCount = 1;
						resetRalleys();
					}
				}
				
			} else {
			}
			
		} 
	}

	void isMatchEnds(TennisDTO dto, TennisDAO dao) {
		ArrayList<String> winPlayer = new ArrayList<>();
		StringBuilder multiWinners = new StringBuilder();
		
		if (dao.sets == 3) { // 3세트전 경기일 경우
			if (dao.matchType == "단식") {
				if (setPointA == 2) { // A팀이 승리
					System.out.println("계수가 종료되었습니다.\n");
					System.out.printf("경기의 승리자는 %s입니다.\n", dao.player.get(0));
					winPlayer.add(dao.player.get(0)); // 승리자의 이름을 winPlayer ArrayList에 따로 추가
					dto.setWinnerList(winPlayer); // dto에 데이터 적재
					dto.setWinnerName(winPlayer.get(0)); // String형 승리자명만 따로 dto.winnerName에 저장
					loop = false;
				} else if (setPointB == 2) { // B팀이 승리
					System.out.println("계수가 종료되었습니다.\n");
					System.out.printf("경기의 승리자는 %s입니다\n.", dao.player.get(1));
					winPlayer.add(dao.player.get(1));
					dto.setWinnerList(winPlayer);
					dto.setWinnerName(winPlayer.get(1));
					loop = false;
				} else {
					return;
				}
			} else if (dao.matchType == "복식") {
				if (setPointA == 2) { // A팀이 승리
					System.out.println("계수가 종료되었습니다.\n");
					System.out.printf("경기의 승리자는 %s, %s 팀입니다.\n", dao.player.get(0), dao.player.get(1));
					winPlayer.add(dao.player.get(0)); // 승리자의 이름을 winPlayer ArrayList에 따로 추가
					winPlayer.add(dao.player.get(1)); // 처음 입력받을 때 팀 관계인 두 명을 같이 입력받았으니까
					dto.setWinnerList(winPlayer); // dto에 데이터 적재
					multiWinners.append(winPlayer.get(0));
					multiWinners.append(",");
					multiWinners.append(winPlayer.get(1));
					dto.setWinnerName(multiWinners.toString());
					loop = false;
				} else if (setPointB == 2) {
					System.out.println("계수가 종료되었습니다.\n");
					System.out.printf("경기의 승리자는 %s, %s 팀입니다.\n", dao.player.get(2), dao.player.get(3));
					winPlayer.add(dto.getPlayerList().get(2));
					winPlayer.add(dto.getPlayerList().get(3));
					dto.setWinnerList(winPlayer); // dto에 데이터 적재
					multiWinners.append(winPlayer.get(2));
					multiWinners.append(",");
					multiWinners.append(winPlayer.get(3));
					dto.setWinnerName(multiWinners.toString());
					loop = false;
				} else {
					return;  
				}
			}
			
		} else if (dao.sets == 5) {  // 5세트전 경기일 경우
			if (dto.getMatchType() == "단식") {
				if (setPointA == 3) { // A팀이 승리
					System.out.println("계수가 종료되었습니다.\n");
					System.out.printf("경기의 승리자는 %s입니다.\n", dao.player.get(0));
					winPlayer.add(dao.player.get(0));
					dto.setWinnerList(winPlayer);
					dto.setWinnerName(winPlayer.get(0));
					loop = false;
				} else if (setPointB == 3) { // B팀이 승리
					System.out.println("계수가 종료되었습니다.\n");
					System.out.printf("경기의 승리자는 %s입니다.\n", dao.player.get(1));
					winPlayer.add(dao.player.get(1));
					dto.setWinnerList(winPlayer);
					dto.setWinnerName(winPlayer.get(1));
					loop = false;
				} else {
					return;
				}
			} else if (dto.getMatchType() == "복식") {
				if (setPointA == 3) { // A팀이 승리
					System.out.println("계수가 종료되었습니다.\n");
					System.out.printf("경기의 승리자는 %s, %s 팀입니다.\n", dao.player.get(0), dao.player.get(1));
					winPlayer.add(dao.player.get(0)); // 승리자의 이름을 winPlayer ArrayList에 따로 추가
					winPlayer.add(dao.player.get(1)); // 처음 입력받을 때 팀 관계인 두 명을 같이 입력받았으니까
					dto.setWinnerList(winPlayer); // dto에 데이터 적재
					multiWinners.append(winPlayer.get(0));
					multiWinners.append(",");
					multiWinners.append(winPlayer.get(1));
					dto.setWinnerName(multiWinners.toString());
					loop = false;
				} else if (setPointB == 3) {
					System.out.println("계수가 종료되었습니다.\n");
					System.out.printf("경기의 승리자는 %s, %s 팀입니다.", dao.player.get(2), dao.player.get(3));
					winPlayer.add(dto.getPlayerList().get(2));
					winPlayer.add(dto.getPlayerList().get(3));
					dto.setWinnerList(winPlayer); // dto에 데이터 적재
					multiWinners.append(winPlayer.get(2));
					multiWinners.append(",");
					multiWinners.append(winPlayer.get(3));
					dto.setWinnerName(multiWinners.toString());
					loop = false;
				} else {
					return;  
				}
			}
		}
	}
	
	public void tieBreak(TennisDTO dto, TennisDAO dao) {
		System.out.println("=======Tiebreak에 돌입합니다.======");
		resetRalleys();
		while(loop){
			System.out.print("입력: "); //승패 결정: 0(왼쪽: A), 1(오른쪽: B) 승 입력
			point = scan.nextInt();
			
			try {
	            // 입력 값이 0 또는 1만 가능하도록 처리
	            if (point != 0 && point != 1) {
	                throw new IllegalArgumentException("올바른 값을 입력하세요.");
	            }

	            // 플레이어 이름을 가져오는 부분
	            String playerName = (point == 0) ? dao.player.get(0) : dao.player.get(1);
	            
	            // 득점 출력
	            if (point == 0) {
	                System.out.println(playerName + "가 득점하였습니다.");
	                dto.setRalleyPointA(ralleyCount);
	                ralleyCount++;
	            } else if (point == 1) {
	                System.out.println(playerName + "가 득점하였습니다.");
	                dto.setRalleyPointB(ralleyCount);
	                ralleyCount++;
	            }
				
				System.out.println();
				//현재 점수 현황
				System.out.printf("[현재 점수] %d : %d%n", dto.getRalleyPointA(), dto.getRalleyPointB()); 
				System.out.println();
				
				if((dto.getRalleyPointA() >= 7 || dto.getRalleyPointB() >= 7) && (Math.abs(dto.getRalleyPointA() - dto.getRalleyPointB()) >= 2)) { //abs절댓값 표현 //==7하면 7점이 될때까지 끝나지 않음
					System.out.printf("%d : %d 의 결과로 경기가 종료되었습니다.",dto.getRalleyPointA(), dto.getRalleyPointB());
					break;
				}
				
				//Deuce 6:6 => 연속해서 2점 & 불연속시 연속 2점이 될때까지 진행 // Deuce 멘트 동점 상황에 출력될 수 있게 
				
				if(dto.getRalleyPointA() >= 6 && dto.getRalleyPointB() >= 6 && dto.getRalleyPointA() == dto.getRalleyPointB()) {
					System.out.println("==========Deuce=========");
					if (Math.abs(dto.getRalleyPointA() - dto.getRalleyPointB()) >= 2 ) {
						System.out.printf("%d : %d 의 결과로 경기가 종료되었습니다.",dto.getRalleyPointA(), dto.getRalleyPointB());
						break;
					}					
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println();
			}
					
		}
	}
	
	public void recordRead() {
		
		view.subTitle("기록열람");
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("[경기날짜]");
		
		collectDateSet();

		//사용자로부터 입력받기
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.print("열람하고 싶은 경기날짜(yyyy-MM-dd)를 입력해주세요: ");
		String input = scan.nextLine().trim();
		input = input.replace("-", "");	

		searchRecord(input);	
		
		view.pause();
	
	}	
	
	
	String line = null;
	String date = "";		//더미에서 가져온 날짜(ex:20001231) (2번째 더미: 날짜)
	String formattedDate = "";
	String name = "";		//더미에서 가져온 선수이름 저장		(3번째 더미: 선수이름)
	String data = "";		//더미에서 가져온 matchData 저장용	(마지막 더미: matchData)
	String result = "";		//승자, 패자 저장
	String dump = "";		//BufferedReader로 읽어온 line 저장용
	private void searchRecord(String input) {

		
		try {
			
			ArrayList<String> foundRecords = new ArrayList<String>();	//동일 날짜의 경기 정보 저장하기 위한 ArrayList
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			while ((line = reader.readLine()) != null) {
	
				String[] temp = line.split(",");
				date = temp[1];
				formattedDate = date.substring(0,4) + "-" + date.substring(4,6) + "-" + date.substring(6,8); //date 가공(ex: 2000-12-31)
				name = temp[2];
				data = temp[3];
				
				if(date.equals(input)) {
					dump = line;	//dump에 한줄씩 저장하면서 내려옴
					printRecord(input);
				}
			}
			
			if(dump.isEmpty()) {
				System.out.println("\n입력하신 날짜와 일치하는 정보가 없습니다.");
				System.out.print("다시 입력해주세요: ");	
				input = scan.nextLine().trim();	//재검색을 위한 재입력
				searchRecord(input);
			}
			for(String record : foundRecords) {
				dump = record;
				printRecord(record);
			}
			
			reader.close();
		
		} catch (Exception e) {
			System.out.println("TennisService.recordRead");
			e.printStackTrace();
		}	
		
	}
	

	private void printRecord(String input) {
		char lastData = data.charAt(data.length()-1);
		result = (lastData == '1') ? "승리" : "패배";
	
		System.out.println();
		System.out.println("경기 날짜: " + formattedDate);
		System.out.println("선수 이름: " + name);
		System.out.println("경기 결과: " + result);
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------------------------");		
	}

	
	private void collectDateSet() {
		
		try {

			HashSet<String> dateHashSet = new HashSet<String>(); 	//더미에 있는 모든 날짜 > dateHashSet
			BufferedReader reader = new BufferedReader(new FileReader(path));

			String line = null;
			
			while((line = reader.readLine()) != null){
				
				String[] temp = line.split(",");
				String date = temp[1];;				//date: 더미데이터에서 가져온 날짜 저장 (2번째 더미: 날짜)
				dateHashSet.add(date);	
			}	
			
			List<String> sortedDateList =new ArrayList<>(dateHashSet); 	//sortDateList: dateHashSet을 정렬하기 위한 list 
			Collections.sort(sortedDateList);							//오름차순 정렬
			

			String formattedDate = "";
			
			for (String date : sortedDateList) {
				formattedDate = date.substring(0,4) + "-" + date.substring(4,6) + "-" + date.substring(6,8); //date 포맷 수정
				System.out.printf("%s\n",formattedDate);
			}

			reader.close();
			
		} catch (Exception e) {
			System.out.println("TennisService.recordRead");
			e.printStackTrace();
		}	
		
	}	

	public void recordUpdate() {
	    view.subTitle("기록수정");

	    System.out.println("-----------------------------------------------------------------------------------------------");
	    System.out.println("[경기날짜]");

	    collectDateSet(); // 사용자가 수정할 날짜를 선택할 수 있도록 출력

	    System.out.println("-----------------------------------------------------------------------------------------------");
	    System.out.print("수정하고 싶은 경기날짜(yyyy-MM-dd)를 입력해주세요: ");
	    String input = scan.nextLine().trim();
	    input = input.replace("-", ""); // 입력받은 날짜를 더미 데이터와 비교하기 위해 포맷팅

	    updateRecord(input);

	    view.pause();
	}

	private void updateRecord(String input) {
	    try {
	        BufferedReader reader = new BufferedReader(new FileReader(path));
	        StringBuilder fileContent = new StringBuilder();
	        boolean recordFound = false;
	        String line;

	        while ((line = reader.readLine()) != null) {
	            String[] temp = line.split(",");
	            String date = temp[1].trim(); // trim 추가

	            if (date.equals(input)) {
	                recordFound = true;
	                updateRecordDetails(line, input);
	                fileContent.append(line).append("\n");
	            } else {
	                fileContent.append(line).append("\n");
	            }
	        }

	        if (!recordFound) {
	            System.out.println("입력하신 날짜와 일치하는 정보가 없습니다.");
	        } else {
	            saveUpdatedRecords(fileContent.toString().trim()); // 수정된 내용을 기존 파일에 저장
	            System.out.println("기록이 성공적으로 수정되었습니다.");
	        }

	        reader.close();

	    } catch (Exception e) {
	        System.out.println("TennisService.recordUpdate");
	        e.printStackTrace();
	    }
	}

	private void updateRecordDetails(String line, String input) {
	    String[] temp = line.split(",");
	    String date = temp[1].trim();
	    String name = temp[2].trim();
	    String data = temp[3].trim();

	    char lastData = data.charAt(data.length() - 1);
	    result = (lastData == '1') ? "승리" : "패배";

	    formattedDate = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
	    System.out.println();
	    System.out.println("경기 날짜: " + formattedDate);
	    System.out.println("선수 이름: " + name);
	    System.out.println("경기 결과: " + result);
	    System.out.println();
	    System.out.println("-----------------------------------------------------------------------------------------------");

	    // 수정할 필드 선택 및 수정 구현
	    System.out.println("1. 경기 날짜 수정");
	    System.out.println("2. 선수 이름 수정");
	    System.out.println("3. 경기 결과 수정");
	    System.out.println("4. 기록 수정 종료");
	    System.out.print("수정할 항목의 번호를 입력하세요: ");

	    int select = scan.nextInt();
	    scan.nextLine(); // 버퍼 비우기

	    switch (select) {
	        case 1:
	            System.out.print("수정할 경기 날짜를 입력하세요(yyyy-MM-dd): ");
	            String updatedDate = scan.nextLine().trim();
	            updateDateInFile(input, updatedDate);
	            break;
	        case 2:
	            System.out.print("수정할 선수 이름을 입력하세요: ");
	            String updatedName = scan.nextLine().trim();
	            updateNameInFile(input, updatedName);
	            break;
	        case 3:
	            System.out.print("수정할 경기 결과를 입력하세요(승리: 1, 패배: 0): ");
	            int updatedResult = scan.nextInt();
	            scan.nextLine(); // 버퍼 비우기
	            updateResultInFile(input, updatedResult);
	            break;
	        case 4:
	            break;
	        default:
	            System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
	            updateRecordDetails(line, input); // 잘못된 입력이면 다시 호출하여 수정할 항목 선택
	            break;
	    }
	}

	private void updateDateInFile(String inputDate, String updatedDate) {
	    try {
	        BufferedReader reader = new BufferedReader(new FileReader(path));
	        StringBuilder fileContent = new StringBuilder();
	        String line;

	        while ((line = reader.readLine()) != null) {
	            String[] temp = line.split(",");
	            String date = temp[1].trim();

	            if (date.equals(inputDate)) {
	                line = line.replace(date, updatedDate); // 날짜 업데이트
	            }
	            fileContent.append(line).append("\n");
	        }

	        saveUpdatedRecords(fileContent.toString().trim());
	        System.out.println("경기 날짜가 성공적으로 수정되었습니다.");

	        reader.close();

	    } catch (Exception e) {
	        System.out.println("TennisService.updateDateInFile");
	        e.printStackTrace();
	    }
	}

	private void updateNameInFile(String inputDate, String updatedName) {
	    try {
	        BufferedReader reader = new BufferedReader(new FileReader(path));
	        StringBuilder fileContent = new StringBuilder();
	        String line;

	        while ((line = reader.readLine()) != null) {
	            String[] temp = line.split(",");
	            String date = temp[1].trim();

	            if (date.equals(inputDate)) {
	                line = line.replace(temp[2].trim(), updatedName); // 이름 업데이트
	            }
	            fileContent.append(line).append("\n");
	        }

	        saveUpdatedRecords(fileContent.toString().trim());
	        System.out.println("선수 이름이 성공적으로 수정되었습니다.");

	        reader.close();

	    } catch (Exception e) {
	        System.out.println("TennisService.updateNameInFile");
	        e.printStackTrace();
	    }
	}

	private void updateResultInFile(String inputDate, int updatedResult) {
	    try {
	        BufferedReader reader = new BufferedReader(new FileReader(path));
	        StringBuilder fileContent = new StringBuilder();
	        String line;

	        while ((line = reader.readLine()) != null) {
	            String[] temp = line.split(",");
	            String date = temp[1].trim();

	            if (date.equals(inputDate)) {
	                line = line.replace(temp[3].trim(), String.valueOf(updatedResult)); // 결과 업데이트
	            }
	            fileContent.append(line).append("\n");
	        }

	        saveUpdatedRecords(fileContent.toString().trim());
	        System.out.println("경기 결과가 성공적으로 수정되었습니다.");

	        reader.close();

	    } catch (Exception e) {
	        System.out.println("TennisService.updateResultInFile");
	        e.printStackTrace();
	    }
	}

	private void saveUpdatedRecords(String filecontent) {
	    try {
	        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
	        
	        writer.write(filecontent);
	        writer.close();
	        
	    } catch (IOException e) {
	        System.out.println("파일을 저장하는 도중 오류가 발생했습니다.");
	        e.printStackTrace();
	    }
	}

	
	
	public void recordDelete() {
		
		view.subTitle("기록 삭제");
		System.out.println();

		String dateToDelete;
		boolean isValidDate = false;

		do {
		  System.out.print("삭제하고 싶은 경기일정을 입력하세요 (YYYYMMDD): ");
		  dateToDelete = scan.nextLine();

		  if (!dateToDelete.matches("\\d{8}")) {//\d{8}는 정규 표현식으로, \d는 숫자(0-9)를 의미하고, {8}은 8번 반복을 의미함. 8개의 숫자가 연속되는 패턴
		    System.out.println("잘못된 날짜 형식입니다. YYYYMMDD 형식으로 입력해주세요.");
		  } else {
		    try {
		      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		      dateFormat.setLenient(false);
		      dateFormat.parse(dateToDelete);
		      isValidDate = true;
		    } catch (ParseException e) {
		      System.out.println("유효하지 않은 날짜입니다.");
		    }
		  }
		} while (!isValidDate);

		boolean isDeleted = false;

		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
		  String line;
		  StringBuilder temp = new StringBuilder();

		  while ((line = reader.readLine()) != null) {
		    line = line.trim(); // 앞뒤 공백 제거 (선택 사항)

		    if (line.length() >= 8) {
		      String dateInFile = line.substring(1, 9); // 쉼표(`,`) 다음 8자리를 가져옴

		      if (!dateInFile.equals(dateToDelete)) {
		        temp.append(line).append("\n");
		      } else {
		        isDeleted = true;
		        // break; // 삭제할 경기 일정을 찾았으므로 while 루프 종료 (제거)
		      }
		    } else {
		      System.out.println("잘못된 데이터 형식: " + line);
		    }
		  }

		  // 삭제 성공 시에만 파일 덮어쓰기
		  if (isDeleted) {
		    try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, false))) {
		      writer.write(temp.toString());
		    }
		    System.out.println("기록이 성공적으로 삭제되었습니다.");
		  } else {
		    System.out.println("해당 경기 일정을 찾을 수 없습니다.");
		    recordDelete(); // 재귀 호출
		  }
		} catch (IOException e) {
		  System.out.println("TennisService.recordDelete");
		  e.printStackTrace();
		  System.out.println("기록 삭제에 실패했습니다. 다시 시도해주세요.");
		}
	}
	public void tennisRules() {
	String path2 ="Rules\\TennisRules.txt";
	
	try {
		BufferedReader reader = new BufferedReader(new FileReader(path2));
	
		String line ;
		
		while((line = reader.readLine())!= null) {
			System.out.println(line);
		}
		view.pause();
		reader.close();
		
	} catch (Exception e) {
		System.out.println("TennisService.tennisRules");
		e.printStackTrace();
	
	}
}



	}
