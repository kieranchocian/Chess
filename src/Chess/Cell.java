package Chess;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Cell implements Serializable {

	private int x;
	private int y;
	private int player;
	private String piece;
	private boolean empty;
	private ArrayList<ArrayList<Integer>> potentialDangerousCells = new ArrayList<ArrayList<Integer>>();
	private LinkedHashSet<ArrayList<Integer>> dangerousCells = new LinkedHashSet<ArrayList<Integer>>();

	public Cell(int x, int y, int player, String piece, ArrayList<ArrayList<Integer>> potentialDangerousCells,
			ArrayList<ArrayList<Integer>> dangerousCells) {
		this.x = x;
		this.y = y;
		this.player = player;
		this.empty = false;
		this.piece = piece;
		this.potentialDangerousCells = potentialDangerousCells;
		this.dangerousCells = new LinkedHashSet<ArrayList<Integer>>(dangerousCells);
	}

	public Cell(int x, int y, ArrayList<ArrayList<Integer>> potentialDangerousCells,
			ArrayList<ArrayList<Integer>> dangerousCells) {
		this.x = x;
		this.y = y;
		this.empty = true;
		this.potentialDangerousCells = potentialDangerousCells;
		this.dangerousCells = new LinkedHashSet<ArrayList<Integer>>(dangerousCells);
		;
	}

	public Cell(int x, int y, int player, String piece) {
		this.x = x;
		this.y = y;
		this.player = player;
		this.piece = piece;
		this.empty = false;
		this.piece = piece;
	}

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
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

	public ArrayList<ArrayList<Integer>> getPotentialDangerousCells() {
		return potentialDangerousCells;
	}

	public ArrayList<ArrayList<Integer>> getDangerousCells() {
		return new ArrayList<ArrayList<Integer>>(dangerousCells);
	}

	public void addPotentialDangerousCell(int potentialDangerousCellX, int potentialDangerousCellY) {
		ArrayList<Integer> potentialDangerousCell = new ArrayList<Integer>();
		potentialDangerousCell.add(potentialDangerousCellX);
		potentialDangerousCell.add(potentialDangerousCellY);
		potentialDangerousCells.add(potentialDangerousCell);
	}

	public void removePotentialDangerousCell(int potentialDangerousCellX, int potentialDangerousCellY) {
		ArrayList<Integer> potentialDangerousCell = new ArrayList<Integer>();
		potentialDangerousCell.add(potentialDangerousCellX);
		potentialDangerousCell.add(potentialDangerousCellY);
		potentialDangerousCells.remove(potentialDangerousCell);
	}

	public void addDangerousCell(int dangerousCellX, int dangerousCellY) {
		ArrayList<Integer> dangerousCell = new ArrayList<Integer>();
		dangerousCell.add(dangerousCellX);
		dangerousCell.add(dangerousCellY);
		dangerousCells.add(dangerousCell);
	}

	public void removeDangerousCell(int dangerousCellX, int dangerousCellY) {
		ArrayList<Integer> dangerousCell = new ArrayList<Integer>();
		dangerousCell.add(dangerousCellX);
		dangerousCell.add(dangerousCellY);
		dangerousCells.remove(dangerousCell);
	}

}
