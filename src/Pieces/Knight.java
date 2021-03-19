package Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Chess.Board;
import Chess.Pathing;

public class Knight extends Piece {

	private ArrayList<Point> potentialMoves = new ArrayList<Point>();
	private ArrayList<Point> allowableMoves = new ArrayList<Point>();

	public Knight(int x, int y, int player) {
		super.x = x;
		super.y = y;
		super.player = player;
		switch (player) {
		case 1:
			pieceLocation = "/Images/whiteKnight.png";
			break;
		case 2:
			pieceLocation = "/Images/blackKnight.png";
			break;
		}
		setPotentialMoves();
	}

	private void setPotentialMoves() {
		if (x - 2 > -1 && x - 2 < 8 && y + 1 > -1 && y + 1 < 8) {
			potentialMoves.add(new Point(x - 2, y + 1));
		}
		if (x - 1 > -1 && x - 1 < 8 && y + 2 > -1 && y + 2 < 8) {
			potentialMoves.add(new Point(x - 1, y + 2));
		}
		if (x + 1 > -1 && x + 1 < 8 && y + 2 > -1 && y + 2 < 8) {
			potentialMoves.add(new Point(x + 1, y + 2));
		}
		if (x + 2 > -1 && x + 2 < 8 && y + 1 > -1 && y + 1 < 8) {
			potentialMoves.add(new Point(x + 2, y + 1));
		}
		if (x + 2 > -1 && x + 2 < 8 && y - 1 > -1 && y - 1 < 8) {
			potentialMoves.add(new Point(x + 2, y - 1));
		}
		if (x + 1 > -1 && x + 1 < 8 && y - 2 > -1 && y - 2 < 8) {
			potentialMoves.add(new Point(x + 1, y - 2));
		}
		if (x - 1 > -1 && x - 1 < 8 && y - 2 > -1 && y - 2 < 8) {
			potentialMoves.add(new Point(x - 1, y - 2));
		}
		if (x - 2 > -1 && x - 2 < 8 && y - 1 > -1 && y - 1 < 8) {
			potentialMoves.add(new Point(x - 2, y - 1));
		}

	}

	public ArrayList<Point> getPossibleMoves(boolean checkForKingCheck, Board board) {
		allowableMoves = Pathing.getAllowableKnightMoves(potentialMoves, player, board);

		if (checkForKingCheck == true) {
			
			// checking if these allowable coordinates put their own king into check
			return Pathing.checkMoveForVulnerableCheck(allowableMoves, "Knight", player, x, y);
		}
		return allowableMoves;
	}

	public ArrayList<Point> getDangerousMoves(Board board) {
		return potentialMoves;
	}

	public ArrayList<Point> getPotentialDangerousMoves(Board board) {
		return potentialMoves;
	}

}
