package com.test.project;

import java.util.Scanner;

import com.github.lalyos.jfiglet.FigletFont;

public class TennisView {
	
	TennisDTO dto = new TennisDTO();
	public void mainMenu() {
		
	System.out.println();
	System.out.println("=================================================================================================");
	
	System.out.println("                                         🎾테니스 계수기🎾");
	
	
	try {
		
	    String asciiArt1 = FigletFont.convertOneLine("Tennis Counter");
	    System.out.println(asciiArt1);
		
	} catch (Exception e) {
		System.out.println("TennisView.mainMenu");
		e.printStackTrace();
	}
	
	
	System.out.println("=================================================================================================");
	System.out.println("-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-");
	System.out.println("        |1.기록 추가|  |2.기록 열람|  |3.기록 수정|  |4.기록 삭제|  |5.테니스 규칙|	|6.프로그램 종료|");
	System.out.println("-------------------------------------------------------------------------------------------------");
//		System.out.println("2.기록 열람");
//		System.out.println("3.기록 삭제"); 
//		System.out.println("4.테니스 규칙 열람"); //4. 테니스 규칙 열람 > 점수 계산법이나 용어에 대해 지식 제공
//		System.out.println("5.프로그램 종료");
//		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println();
		System.out.print("🔢실행 번호를 숫자로 입력해주세요. : ");
		
	}

	
	
	
	public void subTitle(String title) {
		System.out.println();
		System.out.println("🥎"+ title +"🥎");		
	}
	
	public void pause() { //혹시 사용할 일 있을까 싶어 만들어놨는데 사용 안하면 지워버리기

		System.out.println();
		System.out.print("계속하시려면 엔터를 치세요.");
		Scanner scan = new Scanner(System.in);
		scan.nextLine(); //Block
		
		System.out.println();
	}
	
	public void selectError() {
		System.out.print("\n옳지 않은 입력입니다.");
		
	}
	
	//***** 스코어보드와 입력한 사전정보, 경기 결과 리포트 폼(구현 부분 완성되고 변수 확정되면 넣어서 확인)
	


//	public void singleScoreBoard() {
//	    // null 체크와 초기화
//	    if (dto.getPlayerList() == null || dto.getPlayerList().length < 2) {
//	        System.out.println("플레이어 정보가 충분하지 않습니다.");
//	        return;
//	    }
//
//	    // 플레이어 이름과 점수 정보
//	    String player1Name = dto.getPlayerList()[0];
//	    String player2Name = dto.getPlayerList()[1];
//	    int player1Point = 0;  // 예시로 초기화
//	    int player2Point = 0;  // 예시로 초기화
//
//	    // 스코어보드 출력
//	    System.out.println("===================================================================================");
//	    System.out.println("|             Player 1                  |               Player 2                  |");
//	    System.out.println("===================================================================================");
//	    System.out.printf("|Name: %-20s | Name: %-20s |\n", player1Name, player2Name);
//	    System.out.printf("|Point: %-19d | Point: %-19d |\n", player1Point, player2Point);
//	    System.out.println("|                                                                                 |");
//	    System.out.println("|GameScore:                            | GameScore:                            |");
//	    System.out.println("|SetScore:                             | SetScore:                             |");
//	    System.out.println("===================================================================================");
//	}
//	
//
//		
//	
//	
//	
	// 보고서 출력 메서드
    public void preMatchInfo() {
        // 헤더 아스키 아트
    	System.out.println("🖨️╔═══════════════════════════════════════╗🖨️ ");
		System.out.println("  ║            입력한 사전 정보           ║ ");
	    System.out.println("🖨️╚═══════════════════════════════════════╝🖨️ ");



        // 경기 정보 출력
		System.out.println("\n\t\t[🗒️경기 정보]");
       	System.out.println("==============================================");
        System.out.println("-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-");
		System.out.println();
		System.out.println("      |📆경기 날짜|: " );
		System.out.println("      |🥎경기 형식|: " );
		System.out.println("      |🔢세트 수|: " );
		
		  // 참가 선수 출력
		System.out.println("\n\t\t[🧑🏼참가 선수]");
        System.out.println("-----------------------------------------------");
//        for (int i = 0; i < 4; i++) {
//            System.out.printf("%d. %s\n", (i + 1), dto.playerList[i]);
//        }    
	    System.out.println("-----------------------------------------------");
        
    }
	
	
	  public void MatchReport() {
		  // 헤더 아스키 아트
		  System.out.println("🖨️╔═══════════════════════════════════════╗🖨️ ");
		  System.out.println("  ║               경기 결과               ║");
		  System.out.println("🖨️╚═══════════════════════════════════════╝🖨️ ");
		
		  // 경기 정보 출력
		  System.out.println("\n\t\t[🗒️경기 정보]");
	      System.out.println("==============================================");
		  System.out.println("-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-🎾-");
		  System.out.println();
		  System.out.println("      |📆경기 날짜|: " );
		  System.out.println("      |🥎경기 형식|: " );
		  System.out.println("      |🔢세트 수|: " );
		
		  // 참가 선수 출력
		  System.out.println("\n\t\t[🧑🏼참가 선수]");
		  System.out.println("-----------------------------------------------");
//		  for (int i = 0; i < 4; i++) {
//		      System.out.printf("%d. %s\n", (i + 1), dto.playerList[i]);
//	  	  }
		  System.out.println();
		  System.out.println("1선수가 세트스코어 2:0으로 이겼습니다.🎉");    
	      System.out.println("-----------------------------------------------");
	  }
	
	//단식 스코어보드
	public void singleScoreBoard() {
	System.out.printf("===================================================================================\r\n");
	System.out.printf("|             Player 1                  |               Player 2                  |\r\n");
	System.out.printf("===================================================================================\r\n");
	System.out.printf("|Name:       	   |Point:              |Name:             |Point:                |\r\n");
	System.out.printf("|                                                                                 |\r\n");
	System.out.printf("|GameScore:        |SetScore:           |GameScore:        |SetScore:             |\r\n");
	System.out.printf("===================================================================================\r\n");
}
	
	//복식 스코어보드
	public void doubleScoreBoard()	{
	System.out.printf("===================================================================================\r\n");
	System.out.printf("|              Team 1                   |                Team 2                   |\r\n");
	System.out.printf("===================================================================================\r\n");
	System.out.printf("|Name1:        	 |Point:                |Name3:              |Point:              |\r\n");
	System.out.printf("|Name2:                                  Name4:                                   |\r\n");
	System.out.printf("|GameScore:      |SetScore:             |GameScore:          |SetScore:           |\r\n");
	System.out.printf("===================================================================================\r\n");
	}
	
	
	public void printCharcter() {
	System.out.println("\r\n"
	+ "⠀⠀⠀⠀                       ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀     ⠀⠀⠀⠀⠀⠀⠀\r\n"
	+ "⠀⠀⠀                             ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
	+ "⠀⠀⠀                             ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⠀⠀⣀⣀⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣿⣶⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
	+ "⠀⠀⠀                             ⠀⠀⠀⠀⠀⠀⠀⠀⠚⠋⢉⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣦⡄⠀⠀⠀⠀⠙⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
	+ "⠀⠀⠀                             ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⣿⣿⣷⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
	+ "⠀⠀⠀                             ⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⣿⣿⣿⣿⣿⣿⡿⠁⠀⠀⢸⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
	+ "⠀⠀⠀                             ⠀⠀⠀⠀⣠⣴⣶⣶⣿⣿⣿⣿⣿⣿⣿⡿⣿⣛⣿⠂⠸⠟⣯⣿⣿⡟⠀⠀⠀⡐⣶⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
	+ "⠀⠀⠀                             ⠀⠀⠀⣰⣿⢿⣻⡹⣿⣿⣿⣿⣿⣿⣿⡇⠺⣿⣿⠀⠀⠀⠹⢿⣿⠃⠀⠀⢰⠀⢁⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
	+ "⠀⠀⠀                             ⠀⠀⢠⣿⡟⢏⣧⢧⢻⣿⣿⣿⣿⣿⡇⠁⠀⢁⣀⠀⠀⢀⣀⠀⠀⡆⢀⠔⠀⡔⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
	+ "⠀⠀⠀                             ⠀⠀⠀⢿⣿⡝⡼⠞⢻⣿⣿⣿⣿⣿⣇⠀⠀⣿⣿⣿⣿⣿⣿⠁⠠⡠⠂⢀⠜⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
	+ "⠀⠀⠀                             ⠀⠀⠀⠈⢿⣿⡴⠚⣌⢿⣿⣿⣿⣧⣬⣤⡀⠀⠙⠿⠻⠟⢁⣤⣿⡇⣠⠎⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
	+ "⠀⠀⠀                             ⠀⠀⠀⠀⠈⠻⣷⣷⣝⣞⣽⡿⠻⠟⠻⣿⣿⣷⣶⣶⣶⣾⣿⣿⣿⣿⠿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
	+ "⠀⠀⠀                             ⠀⠀⠀⠀⠀⠀⠈⠙⠻⣿⣟⡇⠀⠀⠀⠈⢩⣾⣿⣿⣿⣿⣿⣿⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
	+ "⠀⠀⠀                             ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢻⣿⡀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
	+ "⠀⠀⠀                             ⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⢻⣷⣀⣀⣰⣿⣿⣿⣿⣿⣿⣿⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
	+ "⠀⠀⠀                             ⠀⠀⠀⠀⢺⣳⠀⣸⣤⡄⠀⠘⣽⣿⠉⣉⣁⡬⢹⣿⣿⣿⣿⣿⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
	+ "⠀⠀⠀                             ⠀⠀⠀⠀⢨⣿⡎⣹⠲⣄⠀⠀⠘⠿⠃⠈⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
	+ "⠀⠀⠀                             ⠀⠀⠀⠀⠘⠋⠀⠁⢀⣈⡃⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
	+ "⠀⠀⠀                             ⠀⠀⠀⠀⠀⠐⣟⣡⣬⠵⠇⠀⠀⠀⠀⣾⣿⣶⠿⢿⣿⡿⠁⠟⠉⢁⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
	+ "⠀⠀⠀                             ⠀⠀⠀⠀⠀⢩⣹⠷⠘⠄⢖⡀⠀⠀⢸⣿⣿⢇⡀⠀⣠⣿⣦⡄⠄⠊⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
	+ "⠀⠀⠀                             ⠀⠀⠀⠀⠀⠀⠛⠛⠛⠋⠉⠀⠀⠀⠈⠛⣋⠀⠈⢹⣿⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
	+ "⠀⠀⠀                             ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡄⠀⡇⠀⢠⠞⠁⠀⠀⠘⠻⠿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
	+ "⠀⠀⠀                             ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⡀⡇⢠⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
	+ "⠀⠀⠀                             ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠁⠘⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
	+ "⠀⠀⠀                             ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
	+ "⠀⠀⠀⠀                       ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀         ⠀⠀⠀⠀⠀⠀\r\n"
	+ "");
	
	}
	
}
