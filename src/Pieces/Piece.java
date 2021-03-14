package Pieces;

import java.util.ArrayList;

import Chess.Game;

public abstract class Piece {

	protected int x, y;
	protected int player;
	public String pieceLocation;

	public void setPieceGUI() {
		Game.changeBoardGUICellPiece(x, y, pieceLocation);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getPlayer() {
		return player;
	}

	protected ArrayList<Integer> getArrayListCoords(int x, int y) {
		ArrayList<Integer> coords = new ArrayList<Integer>();
		coords.add(x);
		coords.add(y);
		return coords;
	}

	

}
