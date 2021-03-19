package Pieces;

import java.awt.Point;
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

	public ArrayList<Point> getPossibleMoves(boolean checkForKingCheck, Board board) {
		ArrayList<Point> allowableCoords = new ArrayList<Point>();

		allowableCoords = Pathing.getAllowableDiagonalMoves(x, y, player, true, board);
		allowableCoords.addAll(Pathing.getAllowableVerticalAndHorizontalMoves(x, y, player, true, board));
		if (checkForKingCheck == true) {

			// checking if these allowable coordinates put their own king into check
			return Pathing.checkMoveForVulnerableCheck(allowableCoords, "Queen", player, x, y);
		}
		return allowableCoords;
	}

	public ArrayList<Point> getDangerousMoves(Board board) {
		ArrayList<Point> potentialCoords = new ArrayList<Point>();

		potentialCoords = Pathing.getPotentialVerticleAndHorizontalMoves(x, y, true, true, board);
		potentialCoords.addAll(Pathing.getPotentialDiagonalMoves(x, y, true, true, board));
		return potentialCoords;
	}

	public ArrayList<Point> getPotentialDangerousMoves(Board board) {
		ArrayList<Point> potentialCoords = new ArrayList<Point>();

		potentialCoords = Pathing.getPotentialVerticleAndHorizontalMoves(x, y, true, false, board);
		potentialCoords.addAll(Pathing.getPotentialDiagonalMoves(x, y, true, false, board));
		return potentialCoords;
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
