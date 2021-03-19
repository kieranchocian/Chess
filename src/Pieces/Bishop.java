package Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Chess.Board;
import Chess.Pathing;

public class Bishop extends Piece {

	public Bishop(int x, int y, int player) {
		super.x = x;
		super.y = y;
		super.player = player;
		switch (player) {
		case 1:
			pieceLocation = "/Images/whiteBishop.png";
			break;
		case 2:
			pieceLocation = "/Images/blackBishop.png";
			break;
		}
	}

	public ArrayList<Point> getPossibleMoves(boolean checkForKingCheck, Board board) {
		ArrayList<Point> allowableCoords = new ArrayList<Point>();

		allowableCoords = Pathing.getAllowableDiagonalMoves(x, y, player, true, board);

		if (checkForKingCheck == true) {
			
			// checking if these allowable coordinates put their own king into check
			return Pathing.checkMoveForVulnerableCheck(allowableCoords, "Bishop", player, x, y);
		}

		return allowableCoords;
	}

	public ArrayList<Point> getDangerousMoves(Board board) {
		return Pathing.getPotentialDiagonalMoves(x, y, true, true, board);
	}

	public ArrayList<Point> getPotentialDangerousMoves(Board board) {
		return Pathing.getPotentialDiagonalMoves(x, y, true, false, board);
	}
	
	/* the below methods are used when checking if a piece can intercept a king
	 * being vulnerable from an opponent's piece
	 */

	public ArrayList<Point> getPotentialDiagonalLeftMoves(Board board) {
		return Pathing.getPotentialDiagonalLeftMoves(x, y, true, false, board);
	}

	public ArrayList<Point> getPotentialDiagonalRightMoves(Board board) {
		return Pathing.getPotentialDiagonalRightMoves(x, y, true, false, board);
	}

	public ArrayList<Point> getPotentialAntiDiagonalLeftMoves(Board board) {
		return Pathing.getPotentialAntiDiagonalLeftMoves(x, y, true, false, board);
	}

	public ArrayList<Point> getPotentialAntiDiagonalRightMoves(Board board) {
		return Pathing.getPotentialAntiDiagonalRightMoves(x, y, true, false, board);
	}

}
