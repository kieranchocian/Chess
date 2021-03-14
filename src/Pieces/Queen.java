package Pieces;

import java.util.ArrayList;

import Chess.Board;
import Chess.Pathing;

public class Queen extends Piece {

	public Queen(int x, int y, int player) {
		super.x = x;
		super.y = y;
		super.player = player;
		switch (player) {
		case 1:
			pieceLocation = "/Images/whiteQueen.png";
			break;
		case 2:
			pieceLocation = "/Images/blackQueen.png";
			break;
		}

	}

	public ArrayList<ArrayList<Integer>> getPossibleMoves(boolean checkForKingCheck, Board board) {
		ArrayList<ArrayList<Integer>> allowableCoords = new ArrayList<ArrayList<Integer>>();

		allowableCoords = Pathing.getAllowableDiagonalMoves(x, y, player, true, board);
		allowableCoords.addAll(Pathing.getAllowableVerticalAndHorizontalMoves(x, y, player, true, board));
		if (checkForKingCheck == true) {

			// checking if these allowable coordinates put their own king into check
			return Pathing.checkMoveForVulnerableCheck(allowableCoords, "Queen", player, x, y);
		}
		return allowableCoords;
	}

	public ArrayList<ArrayList<Integer>> getDangerousMoves(Board board) {
		ArrayList<ArrayList<Integer>> potentialCoords = new ArrayList<ArrayList<Integer>>();

		potentialCoords = Pathing.getPotentialVerticleAndHorizontalMoves(x, y, true, true, board);
		potentialCoords.addAll(Pathing.getPotentialDiagonalMoves(x, y, true, true, board));
		return potentialCoords;
	}

	public ArrayList<ArrayList<Integer>> getPotentialDangerousMoves(Board board) {
		ArrayList<ArrayList<Integer>> potentialCoords = new ArrayList<ArrayList<Integer>>();

		potentialCoords = Pathing.getPotentialVerticleAndHorizontalMoves(x, y, true, false, board);
		potentialCoords.addAll(Pathing.getPotentialDiagonalMoves(x, y, true, false, board));
		return potentialCoords;
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

	public ArrayList<ArrayList<Integer>> getPotentialHorizontalLeftMoves(Board board) {
		return Pathing.getPotentialHorizontalLeftMoves(x, y, true, false, board);
	}

	public ArrayList<ArrayList<Integer>> getPotentialHorizontalRightMoves(Board board) {
		return Pathing.getPotentialHorizontalRightMoves(x, y, true, false, board);
	}

	public ArrayList<ArrayList<Integer>> getPotentialVerticalUpMoves(Board board) {
		return Pathing.getPotentialVerticalUpMoves(x, y, true, false, board);
	}

	public ArrayList<ArrayList<Integer>> getPotentialVerticalDownMoves(Board board) {
		return Pathing.getPotentialVerticalDownMoves(x, y, true, false, board);
	}

}
