package Pieces;

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

	public ArrayList<ArrayList<Integer>> getPossibleMoves(boolean checkForKingCheck, Board board) {
		ArrayList<ArrayList<Integer>> allowableCoords = new ArrayList<ArrayList<Integer>>();

		allowableCoords = Pathing.getAllowableDiagonalMoves(x, y, player, false, board);
		allowableCoords.addAll(Pathing.getAllowableVerticalAndHorizontalMoves(x, y, player, false, board));

		System.out.println("Allowable coords before checking for check status: " + allowableCoords);

		if (checkForKingCheck == true) {

			// checking if these allowable coordinates put their own king into check
			allowableCoords = Pathing.checkMoveForVulnerableCheck(allowableCoords, "King", player, x, y);
		}

		return allowableCoords;
	}

	public ArrayList<ArrayList<Integer>> getDangerousMoves(Board board) {
		ArrayList<ArrayList<Integer>> potentialCoords = new ArrayList<ArrayList<Integer>>();

		potentialCoords = Pathing.getPotentialVerticleAndHorizontalMoves(x, y, false, true, board);
		potentialCoords.addAll(Pathing.getPotentialDiagonalMoves(x, y, false, true, board));
		return potentialCoords;
	}

	public ArrayList<ArrayList<Integer>> getPotentialDangerousMoves(Board board) {
		ArrayList<ArrayList<Integer>> potentialCoords = new ArrayList<ArrayList<Integer>>();

		potentialCoords = Pathing.getPotentialVerticleAndHorizontalMoves(x, y, false, false, board);
		potentialCoords.addAll(Pathing.getPotentialDiagonalMoves(x, y, false, false, board));
		return potentialCoords;
	}

}
