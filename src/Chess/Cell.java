package Chess;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Cell implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int player;
	private String piece;
	private boolean empty;
	private boolean hasMoved;
	private ArrayList<Point> potentialDangerousCells = new ArrayList<Point>();
	private LinkedHashSet<Point> dangerousCells = new LinkedHashSet<Point>();

	public Cell(int x, int y, int player, String piece, ArrayList<Point> potentialDangerousCells,
			ArrayList<Point> dangerousCells, boolean hasMoved) {
		this.player = player;
		this.empty = false;
		this.piece = piece;
		this.potentialDangerousCells = potentialDangerousCells;
		this.dangerousCells = new LinkedHashSet<Point>(dangerousCells);
		this.hasMoved = hasMoved;
	}

	public Cell(int x, int y, ArrayList<Point> potentialDangerousCells,
			ArrayList<Point> dangerousCells) {
		this.empty = true;
		this.potentialDangerousCells = potentialDangerousCells;
		this.dangerousCells = new LinkedHashSet<Point>(dangerousCells);
	}

	public Cell(int x, int y, int player, String piece) {
		this.player = player;
		this.piece = piece;
		this.empty = false;
		this.piece = piece;
		this.hasMoved = false;
	}

	public Cell(int x, int y) {
		this.empty = true;
	}

	public int getPlayer() {
		return player;
	}

	public boolean isEmpty() {
		return empty;
	}

	public String getPiece() {
		return piece;
	}

	public ArrayList<Point> getPotentialDangerousCells() {
		return potentialDangerousCells;
	}

	public ArrayList<Point> getDangerousCells() {
		return new ArrayList<Point>(dangerousCells);
	}

	public void addPotentialDangerousCell(int potentialDangerousCellX, int potentialDangerousCellY) {
		Point potentialDangerousCell = new Point();
		potentialDangerousCell.x = potentialDangerousCellX;
		potentialDangerousCell.y = potentialDangerousCellY;
		potentialDangerousCells.add(potentialDangerousCell);
	}

	public void removePotentialDangerousCell(int potentialDangerousCellX, int potentialDangerousCellY) {
		Point potentialDangerousCell = new Point();
		potentialDangerousCell.x = potentialDangerousCellX;
		potentialDangerousCell.y = potentialDangerousCellY;
		potentialDangerousCells.remove(potentialDangerousCell);
	}

	public void addDangerousCell(int dangerousCellX, int dangerousCellY) {
		Point dangerousCell = new Point();
		dangerousCell.x = dangerousCellX;
		dangerousCell.y = dangerousCellY;
		dangerousCells.add(dangerousCell);
	}

	public void removeDangerousCell(int dangerousCellX, int dangerousCellY) {
		Point dangerousCell = new Point();
		dangerousCell.x = dangerousCellX;
		dangerousCell.y = dangerousCellY;
		dangerousCells.remove(dangerousCell);
	}
	
	public boolean hasPieceMoved() {
		return hasMoved;
	}

}
