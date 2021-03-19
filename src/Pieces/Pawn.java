package Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Chess.Board;
import Chess.Pathing;

public class Pawn extends Piece {

	private ArrayList<Point> potentialDangerousMoves = new ArrayList<Point>();
	private ArrayList<Point> allowableMoves = new ArrayList<Point>();
	private ArrayList<Point> dangerousMoves = new ArrayList<Point>();
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
				potentialDangerousMoves.add(new Point(x, y + 1));
			}
			if (x > -1 && x < 8 && y + 2 > -1 && y + 2 < 8) {
				potentialDangerousMoves.add(new Point(x, y + 2));
			}

			if (x + 1 > -1 && x + 1 < 8 && y + 1 > -1 && y + 1 < 8) {
				potentialDangerousMoves.add(new Point(x + 1, y + 1));
				dangerousMoves.add(new Point(x + 1, y + 1));
			}
			if (x - 1 > -1 && x - 1 < 8 && y + 1 > -1 && y + 1 < 8) {
				potentialDangerousMoves.add(new Point(x - 1, y + 1));
				dangerousMoves.add(new Point(x - 1, y + 1));
			}
			break;
		case 2:
			pieceLocation = "/Images/blackPawn.png";
			direction = "down";
			if (x > -1 && x < 8 && y - 1 > -1 && y - 1 < 8) {
				potentialDangerousMoves.add(new Point(x, y - 1));
			}
			if (x > -1 && x < 8 && y - 2 > -1 && y - 2 < 8) {
				potentialDangerousMoves.add(new Point(x, y - 2));
			}
			if (x + 1 > -1 && x + 1 < 8 && y - 1 > -1 && y - 1 < 8) {
				potentialDangerousMoves.add(new Point(x + 1, y - 1));
				dangerousMoves.add(new Point(x + 1, y - 1));
			}
			if (x - 1 > -1 && x - 1 < 8 && y - 1 > -1 && y - 1 < 8) {
				potentialDangerousMoves.add(new Point(x - 1, y - 1));
				dangerousMoves.add(new Point(x - 1, y - 1));
			}
			break;
		}

		if ((y == 1 && player == 1) || (y == 6 && player == 2)) {
			hasMoved = false;
		} else {
			hasMoved = true;
		}
	}

	public ArrayList<Point> getPossibleMoves(boolean checkForKingCheck, Board board) {
		allowableMoves = Pathing.getAllowablePawnMoves(x, y, potentialDangerousMoves, player, hasMoved, direction, board);

		if (checkForKingCheck == true) {
			
			// checking if these allowable coordinates put their own king into check
			return Pathing.checkMoveForVulnerableCheck(allowableMoves, "Pawn", player, x, y);

		}
		return allowableMoves;
	}

	public ArrayList<Point> getPotentialDangerousMoves(Board board) {

		return potentialDangerousMoves;
	}

	public ArrayList<Point> getDangerousMoves(Board board) {

		return dangerousMoves;
	}
	
}
