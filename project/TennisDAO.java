package com.test.project;

import java.util.ArrayList;
import java.util.Scanner;

//DTO의 데이터를 가지고 직접적으로 기능하는 메서드들을 모아놓은 클래스
public class TennisDAO {
	
	Scanner scan = new Scanner(System.in);
	TennisDTO dto = new TennisDTO();
	boolean flag = true;
	int yesOrNo = 0;
	int sets = 0;
	String matchType = "";
	ArrayList<String> player = new ArrayList<>(); // DTO에 선언된 ArrayList<String> playerList에 들어갈 ArrayList 초기화 작업
	
	

	//기록 추가 메서드-일자, 형식, 세트수, 이름
	public void getInfo() {
		System.out.println();
		System.out.println("계수기에 경기 기록을 추가합니다.");
		System.out.println("경기 데이터 추가를 위해 몇 가지 사전 정보를 입력받겠습니다.");
		
		infoDate(); // 경기 날짜
		infoMatchType();
		infoMatchSets();
		infoPlayerName();
		System.out.println("사전 정보 입력이 완료되었습니다.");
		System.out.println();
	}
	
	public void infoDate() {
		resetTriggers();
		
		while (flag) {
			System.out.print("1. 경기 날짜를 8자리로 입력해주세요.(ex-20001231) :");
			dto.setMatchDate(scan.nextLine());
			
			if (dto.getMatchDate().length() == 8) {
				System.out.printf("입력하신 데이터는 %s 입니다.\n", dto.getMatchDate());
				wantEdit();
			} else {
				System.out.print("잘못된 형식의 데이터입니다.\n");
			}
		}
		System.out.println("날짜 입력이 완료되었습니다.\n");
	}

	public void infoMatchType() {
		resetTriggers();
		while(flag) {
			System.out.print("경기의 형식을 입력해주세요.(단식/복식) : ");
			matchType = scan.nextLine();
			dto.setMatchType(matchType);
			if (dto.getMatchType().equals("단식")) {
				System.out.println("입력하신 데이터는 단식 입니다.");
				wantEdit();
			} else if (dto.getMatchType().equals("복식")) {
				System.out.println("입력하신 데이터는 복식 입니다.");
				wantEdit();
			} else {
				System.out.println("잘못된 형식의 데이터입니다.");
			}
		}
		System.out.printf("경기 형식 입력이 완료되었습니다.\n\n");
		
	}

	public void infoMatchSets() {
		resetTriggers();
		
		while(flag) {
			System.out.print("세트 수 구분을 위해 숫자 3 또는 5를 입력해주세요.: ");
			dto.setMatchSets(scan.nextInt());
			scan.nextLine();
			if (dto.getMatchSets() == 3) {
				System.out.println("3세트전으로 입력했습니다.");
				wantEdit();
			} else if (dto.getMatchSets() == 5) {
				System.out.println("5세트전으로 입력했습니다.");
				wantEdit();
			} else {
				System.out.println("잘못된 형식의 데이터입니다.");
			}
		}
		System.out.println("세트 수 입력이 완료되었습니다.\n");
	}

	public void infoPlayerName() {
		resetTriggers();
		
		System.out.println("선수 이름을 입력받겠습니다.");
		
		while(flag) {
			if (dto.getMatchType().equals("단식")) {
					for (int i = 1; i <= 2; i++) {
						System.out.printf("%d번째 선수 이름을 입력해주세요. :", i);
						player.add(scan.nextLine());
						dto.setPlayerList(player);
					}
				} else if (dto.getMatchType().equals("복식")) {
					for (int i = 1; i <= 4; i++) {
						System.out.printf("%d번째 선수 이름을 입력해주세요."
								+ "\n1,2번과 3,4번이 팀이 되도록 선수 이름을 입력해주세요. : ", i);
						player.add(scan.nextLine());
						dto.setPlayerList(player);
					}
				}
						
			System.out.println("입력받은 선수 이름은 다음과 같습니다.");
			for(int i = 0; i < dto.getPlayerList().size(); i++) {
				System.out.printf("%d. %s\n", i+1, dto.getPlayerList().get(i));
			}
			wantEdit();
		}
		System.out.println("이름 입력이 완료되었습니다.");
	}
	
	public void wantEdit() {
		System.out.print("입력값이 맞으면 1, 다시 입력하시려면 2를 입력해주세요. :");
		yesOrNo = scan.nextInt();
		scan.nextLine();
		if (yesOrNo == 1) {
			flag = false;
		} else if (yesOrNo == 2) {
			flag = true;
			scan.nextLine();
		} else {
			
		}
	}
	
	public void resetTriggers() {
		flag = true;
		yesOrNo = 0;
	}
}
