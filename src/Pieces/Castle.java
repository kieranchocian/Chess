package Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Chess.Board;
import Chess.Pathing;

public class Castle extends Piece {

	public Castle(int x, int y, int player) {
		super.x = x;
		super.y = y;
		super.player = player;
		switch (player) {
		case 1:
			pieceLocation = "/Images/whiteCastle.png";
			break;
		case 2:
			pieceLocation = "/Images/blackCastle.png";
			break;
		}
	}

	public ArrayList<Point> getPossibleMoves(boolean checkForKingCheck, Board board) {
		ArrayList<Point> allowableCoords = new ArrayList<Point>();

		allowableCoords = Pathing.getAllowableVerticalAndHorizontalMoves(x, y, player, true, board);

		if (checkForKingCheck == true) {
			
			// checking if these allowable coordinates put their own king into check
			return Pathing.checkMoveForVulnerableCheck(allowableCoords, "Castle", player, x, y);
		}

		return allowableCoords;
	}

	public ArrayList<Point> getDangerousMoves(Board board) {
		return Pathing.getPotentialVerticleAndHorizontalMoves(x, y, true, true, board);
	}

	public ArrayList<Point> getPotentialDangerousMoves(Board board) {
		return Pathing.getPotentialVerticleAndHorizontalMoves(x, y, true, false, board);
	}
	
	/* the below methods are used when checking if a piece can intercept a king
	 * being vulnerable from an opponent's piece
	 */

	public ArrayList<Point> getPotentialHorizontalLeftMoves(Board board) {
		return Pathing.getPotentialHorizontalLeftMoves(x, y, true, false, board);
	}

	public ArrayList<Point> getPotentialHorizontalRightMoves(Board board) {
		return Pathing.getPotentialHorizontalRightMoves(x, y, true, false, board);
	}

	public ArrayList<Point> getPotentialVerticalUpMoves(Board board) {
		return Pathing.getPotentialVerticalUpMoves(x, y, true, false, board);
	}

	public ArrayList<Point> getPotentialVerticalDownMoves(Board board) {
		return Pathing.getPotentialVerticalDownMoves(x, y, true, false, board);
	}

}
