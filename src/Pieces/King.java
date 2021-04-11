package Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Chess.Board;
import Chess.Pathing;

public class King extends Piece {

	public King(int x, int y, int player) {
		super.x = x;
		super.y = y;
		super.player = player;
		switch (player) {
		case 1:
			pieceLocation = "/Images/whiteKing.png";
			break;
		case 2:
			pieceLocation = "/Images/blackKing.png";
			break;
		}
	}

	public ArrayList<Point> getPossibleMoves(boolean kingInCheck, boolean checkForKingCheck, Board board) {
		ArrayList<Point> allowableCoords = new ArrayList<Point>();

		allowableCoords = Pathing.getAllowableDiagonalMoves(x, y, player, false, board);
		allowableCoords.addAll(Pathing.getAllowableVerticalAndHorizontalMoves(x, y, player, false, board));
		
		if (!kingInCheck) {
			allowableCoords = Pathing.checkForCastling(x, y, player, allowableCoords, board);
		}

		System.out.println("Allowable coords before checking for check status: " + allowableCoords);

		if (checkForKingCheck == true) {

			// checking if these allowable coordinates put their own king into check
			allowableCoords = Pathing.checkMoveForVulnerableCheck(allowableCoords, "King", player, x, y);
		}

		return allowableCoords;
	}

	public ArrayList<Point> getDangerousMoves(Board board) {
		ArrayList<Point> potentialCoords = new ArrayList<Point>();

		potentialCoords = Pathing.getPotentialVerticleAndHorizontalMoves(x, y, false, true, board);
		potentialCoords.addAll(Pathing.getPotentialDiagonalMoves(x, y, false, true, board));
		return potentialCoords;
	}

	public ArrayList<Point> getPotentialDangerousMoves(Board board) {
		ArrayList<Point> potentialCoords = new ArrayList<Point>();

		potentialCoords = Pathing.getPotentialVerticleAndHorizontalMoves(x, y, false, false, board);
		potentialCoords.addAll(Pathing.getPotentialDiagonalMoves(x, y, false, false, board));
		return potentialCoords;
	}

}
