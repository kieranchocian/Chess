package Pieces;

import java.util.ArrayList;

import Chess.Board;
import Chess.Pathing;

public class Pawn extends Piece {

	private ArrayList<ArrayList<Integer>> potentialDangerousMoves = new ArrayList<ArrayList<Integer>>();
	private ArrayList<ArrayList<Integer>> allowableMoves = new ArrayList<ArrayList<Integer>>();
	private ArrayList<ArrayList<Integer>> dangerousMoves = new ArrayList<ArrayList<Integer>>();
	private boolean hasMoved;
	private String direction;

	public Pawn(int x, int y, int player) {
		super.x = x;
		super.y = y;
		super.player = player;

		switch (player) {
		case 1:
			pieceLocation = "/Images/whitePawn.png";
			direction = "up";
			if (x > -1 && x < 8 && y + 1 > -1 && y + 1 < 8) {
				potentialDangerousMoves.add(getArrayListCoords(x, y + 1));
			}
			if (x > -1 && x < 8 && y + 2 > -1 && y + 2 < 8) {
				potentialDangerousMoves.add(getArrayListCoords(x, y + 2));
			}

			if (x + 1 > -1 && x + 1 < 8 && y + 1 > -1 && y + 1 < 8) {
				potentialDangerousMoves.add(getArrayListCoords(x + 1, y + 1));
				dangerousMoves.add(getArrayListCoords(x + 1, y + 1));
			}
			if (x - 1 > -1 && x - 1 < 8 && y + 1 > -1 && y + 1 < 8) {
				potentialDangerousMoves.add(getArrayListCoords(x - 1, y + 1));
				dangerousMoves.add(getArrayListCoords(x - 1, y + 1));
			}
			break;
		case 2:
			pieceLocation = "/Images/blackPawn.png";
			direction = "down";
			if (x > -1 && x < 8 && y - 1 > -1 && y - 1 < 8) {
				potentialDangerousMoves.add(getArrayListCoords(x, y - 1));
			}
			if (x > -1 && x < 8 && y - 2 > -1 && y - 2 < 8) {
				potentialDangerousMoves.add(getArrayListCoords(x, y - 2));
			}
			if (x + 1 > -1 && x + 1 < 8 && y - 1 > -1 && y - 1 < 8) {
				potentialDangerousMoves.add(getArrayListCoords(x + 1, y - 1));
				dangerousMoves.add(getArrayListCoords(x + 1, y - 1));
			}
			if (x - 1 > -1 && x - 1 < 8 && y - 1 > -1 && y - 1 < 8) {
				potentialDangerousMoves.add(getArrayListCoords(x - 1, y - 1));
				dangerousMoves.add(getArrayListCoords(x - 1, y - 1));
			}
			break;
		}

		if ((y == 1 && player == 1) || (y == 6 && player == 2)) {
			hasMoved = false;
		} else {
			hasMoved = true;
		}
	}

	public ArrayList<ArrayList<Integer>> getPossibleMoves(boolean checkForKingCheck, Board board) {
		allowableMoves = Pathing.getAllowablePawnMoves(x, y, potentialDangerousMoves, player, hasMoved, direction, board);

		if (checkForKingCheck == true) {
			
			// checking if these allowable coordinates put their own king into check
			return Pathing.checkMoveForVulnerableCheck(allowableMoves, "Pawn", player, x, y);

		}
		return allowableMoves;
	}

	public ArrayList<ArrayList<Integer>> getPotentialDangerousMoves(Board board) {

		return potentialDangerousMoves;
	}

	public ArrayList<ArrayList<Integer>> getDangerousMoves(Board board) {

		return dangerousMoves;
	}
	
}
