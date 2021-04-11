package Chess;

import java.io.Serializable;

public class Controller implements Serializable{

	private int playerTurn;
	private int fiftyMoveRulesMoves = 0;
	private boolean playerOneCheck = false;
	private boolean playerTwoCheck = false;
	private boolean gameComplete = false;
	
	public Controller() {
		setGameComplete(false);
		setPlayerTurn(1);
	}
	

	public int getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(int playerTurn) {
		this.playerTurn = playerTurn;
	}
	
	public boolean isPlayerInCheck(int player) {
		boolean answer = false;
		
		switch (player) {
		
		case 1:
			answer = playerOneCheck;
			break;
		
		case 2:
			answer = playerTwoCheck;
			break;
		}
		
		return answer;
		
	}
	
	public void setPlayerCheck(int player, boolean check) {
		
		switch (player) {
			
		case 1:
			playerOneCheck = check;
			break;
		
		case 2:
			playerTwoCheck = check;
			break;
		}
	}

	public boolean isGameComplete() {
		return gameComplete;
	}

	public void setGameComplete(boolean gameComplete) {
		this.gameComplete = gameComplete;
	}
	
	public int getFiftyMoveRulesMoves() {
		return fiftyMoveRulesMoves;
	}

	public void incrementFiftyMoveRulesMoves() {
		this.fiftyMoveRulesMoves = this.fiftyMoveRulesMoves + 1;
	}
	
	public void resetFiftyMoveRulesMoves() {
		this.fiftyMoveRulesMoves = 0;
	}
	

}
