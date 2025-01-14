package com.test.project;

import java.util.ArrayList;

public class TennisDTO {
	
	public String playerName = "";    // 이름
	public ArrayList<String> playerList;  // 이름 배열(가변형)
	public ArrayList<String> winnerList;  // 승리팀의 이름 배열 (가변형)
	public String winnerName = "";	 // 승리팀의 구성원 이름
	private String matchType; 	 // 경기 형식 (단식/복식)
	private StringBuilder matchData = new StringBuilder(""); // 경기 내내 득점 상황을 진행 순서대로 기록 (예-10100010110101T1001011...)
	private int matchSets; 	 	 // 몇세트전인지 구분
	private String matchDate;	 // 경기 날짜 (yyyyMMdd의 형식을 가짐)
	private int ralleyPointA; 	 // 랠리 점수-A팀
	private int ralleyPointB; 	 // 랠리 점수-B팀
	private int gamePointA; 	 // 게임 점수-A팀
	private int gamePointB;      // 게임 점수-B팀
	private int setPointA; 		 // 세트 점수-A팀
	private int setPointB; 		 // 세트 점수-B팀
	
	
	//getter, setter
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public ArrayList<String> getPlayerList() {
		return playerList;
	}
	public void setPlayerList(ArrayList<String> playerList) {
		this.playerList = playerList;
	}
	public ArrayList<String> getWinnerList() {
		return winnerList;
	}
	public void setWinnerList(ArrayList<String> winnerList) {
		this.winnerList = winnerList;
	}
	public String getWinnerName() {
		return winnerName;
	}
	public void setWinnerName(String winnerName) {
		this.winnerName = winnerName;
	}
	public String getMatchType() {
		return matchType;
	}
	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}
	public StringBuilder getMatchData() {
		return matchData;
	}
	public void setMatchData(StringBuilder matchData) {
		this.matchData = matchData;
	}
	public int getMatchSets() {
		return matchSets;
	}
	public void setMatchSets(int matchSets) {
		this.matchSets = matchSets;
	}
	public String getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}
	public int getRalleyPointA() {
		return ralleyPointA;
	}
	public void setRalleyPointA(int ralleyPointA) {
		this.ralleyPointA = ralleyPointA;
	}
	public int getRalleyPointB() {
		return ralleyPointB;
	}
	public void setRalleyPointB(int ralleyPointB) {
		this.ralleyPointB = ralleyPointB;
	}
	public int getGamePointA() {
		return gamePointA;
	}
	public void setGamePointA(int gamePointA) {
		this.gamePointA = gamePointA;
	}
	public int getGamePointB() {
		return gamePointB;
	}
	public void setGamePointB(int gamePointB) {
		this.gamePointB = gamePointB;
	}
	public int getSetPointA() {
		return setPointA;
	}
	public void setSetPointA(int setPointA) {
		this.setPointA = setPointA;
	}
	public int getSetPointB() {
		return setPointB;
	}
	public void setSetPointB(int setPointB) {
		this.setPointB = setPointB;
	}
				
}
