package com.test.project;

import java.util.Scanner;

public class Tennis {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		TennisView view = new TennisView();
		TennisService service = new TennisService();
		TennisDAO dao = new TennisDAO();
		TennisDTO dto = new TennisDTO();
		
		boolean loop = true;
		
		view.printCharcter();
		
		while(loop) {
			
			view.mainMenu();
			
			String select = scan.nextLine();
			System.out.println();
			
			if (select.equals("1")) {
				service.recordCreate(dto,dao);
			} else if (select.equals("2")) {
				service.recordRead();
			} else if (select.equals("3")) {
				service.recordUpdate();
			} else if (select.equals("4")) {
				service.recordDelete();
			} else if (select.equals("5")) {
				service.tennisRules();
			} else if (select.equals("6")) {
				loop = false;
			} else {
				view.selectError();
				view.pause();
//				break;
			}

		}
		
		scan.close();
		System.out.println("\n프로그램을 종료합니다.");
		
	}

}
