package Pieces;

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

	public ArrayList<ArrayList<Integer>> getPossibleMoves(boolean checkForKingCheck, Board board) {
		ArrayList<ArrayList<Integer>> allowableCoords = new ArrayList<ArrayList<Integer>>();

		allowableCoords = Pathing.getAllowableDiagonalMoves(x, y, player, true, board);

		if (checkForKingCheck == true) {
			
			// checking if these allowable coordinates put their own king into check
			return Pathing.checkMoveForVulnerableCheck(allowableCoords, "Bishop", player, x, y);
		}

		return allowableCoords;
	}

	public ArrayList<ArrayList<Integer>> getDangerousMoves(Board board) {
		return Pathing.getPotentialDiagonalMoves(x, y, true, true, board);
	}

	public ArrayList<ArrayList<Integer>> getPotentialDangerousMoves(Board board) {
		return Pathing.getPotentialDiagonalMoves(x, y, true, false, board);
	}
	
	/* the below methods are used when checking if a piece can intercept a king
	 * being vulnerable from an opponent's piece
	 */

	public ArrayList<ArrayList<Integer>> getPotentialDiagonalLeftMoves(Board board) {
		return Pathing.getPotentialDiagonalLeftMoves(x, y, true, false, board);
	}

	public ArrayList<ArrayList<Integer>> getPotentialDiagonalRightMoves(Board board) {
		return Pathing.getPotentialDiagonalRightMoves(x, y, true, false, board);
	}

	public ArrayList<ArrayList<Integer>> getPotentialAntiDiagonalLeftMoves(Board board) {
		return Pathing.getPotentialAntiDiagonalLeftMoves(x, y, true, false, board);
	}

	public ArrayList<ArrayList<Integer>> getPotentialAntiDiagonalRightMoves(Board board) {
		return Pathing.getPotentialAntiDiagonalRightMoves(x, y, true, false, board);
	}

}
