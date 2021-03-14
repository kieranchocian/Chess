package Chess;

import java.io.Serializable;
import java.util.ArrayList;

import Pieces.Castle;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Queen;
import Pieces.Bishop;

public class Board implements Serializable {

	private ArrayList<ArrayList<Cell>> board = new ArrayList<ArrayList<Cell>>();
	private ArrayList<Integer> playerOneKingCoords = new ArrayList<Integer>();
	private ArrayList<Integer> playerTwoKingCoords = new ArrayList<Integer>();

	public Board() {
		createBoard();
	}

	public void createBoard() {

		Cell currentCell;
		Cell emptyCell;

		for (int y = 0; y < 8; y++) {
			ArrayList<Cell> row = new ArrayList<Cell>();
			for (int x = 0; x < 8; x++) {
				switch (y) {

				case 0:

					// adding white non-pawn cells
					switch (x) {

					case 0:
						currentCell = new Cell(x, y, 1, "Castle");
						row.add(currentCell);

						break;
					case 1:
						currentCell = new Cell(x, y, 1, "Knight");
						row.add(currentCell);
						break;

					case 2:
						currentCell = new Cell(x, y, 1, "Bishop");
						row.add(currentCell);
						break;

					case 3:
						currentCell = new Cell(x, y, 1, "Queen");
						row.add(currentCell);
						break;

					case 4:
						currentCell = new Cell(x, y, 1, "King");
						row.add(currentCell);
						break;

					case 5:
						currentCell = new Cell(x, y, 1, "Bishop");
						row.add(currentCell);
						break;

					case 6:
						currentCell = new Cell(x, y, 1, "Knight");
						row.add(currentCell);
						break;

					case 7:
						currentCell = new Cell(x, y, 1, "Castle");
						row.add(currentCell);
						break;

					}

					break;
				// end of adding white non-pawn cells

				case 1:
					currentCell = new Cell(x, y, 1, "Pawn");
					row.add(currentCell);
					break;

				case 2:
					emptyCell = new Cell(x, y);
					row.add(emptyCell);
					break;

				case 3:
					emptyCell = new Cell(x, y);
					row.add(emptyCell);
					break;

				case 4:
					emptyCell = new Cell(x, y);
					row.add(emptyCell);
					break;

				case 5:
					emptyCell = new Cell(x, y);
					row.add(emptyCell);
					break;

				case 6:
					currentCell = new Cell(x, y, 2, "Pawn");
					row.add(currentCell);
					break;

				case 7:

					// start of adding black non-pawn cells
					switch (x) {
					case 0:
						currentCell = new Cell(x, y, 2, "Castle");
						row.add(currentCell);
						break;
					case 1:
						currentCell = new Cell(x, y, 2, "Knight");
						row.add(currentCell);
						break;

					case 2:
						currentCell = new Cell(x, y, 2, "Bishop");
						row.add(currentCell);
						break;

					case 3:
						currentCell = new Cell(x, y, 2, "Queen");
						row.add(currentCell);
						break;

					case 4:
						currentCell = new Cell(x, y, 2, "King");
						row.add(currentCell);
						break;

					case 5:
						currentCell = new Cell(x, y, 2, "Bishop");
						row.add(currentCell);
						break;

					case 6:
						currentCell = new Cell(x, y, 2, "Knight");
						row.add(currentCell);
						break;

					case 7:
						currentCell = new Cell(x, y, 2, "Castle");
						row.add(currentCell);
						break;

					}
					break;
				// end of adding black non-pawn cells
				}

			}
			board.add(row);
			System.out.println(board);
		}

	}

	// adding dangerousCells and potentialDangerousCells to each cell in board
	public void initialiseDangerousCells() {

		ArrayList<ArrayList<Integer>> potentialEndangeredCells;
		ArrayList<ArrayList<Integer>> endangeredCells;

		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {

				int player = getCellPlayer(x, y);
				String piece = getCellPiece(x, y);

				if (piece != null) {

					switch (piece) {

					case "Castle":
						Castle castle = new Castle(x, y, player);
						castle.setPieceGUI();
						potentialEndangeredCells = castle.getPotentialDangerousMoves(Game.getBoard());
						System.out.println("Castle's potential dangerous cells at coords: (" + x + ", " + y + "): "
								+ potentialEndangeredCells);
						addPotentialEndangeredCells(x, y, potentialEndangeredCells);

						endangeredCells = castle.getDangerousMoves(Game.getBoard());
						System.out.println(
								"Castle's dangerous cells at coords: (" + x + ", " + y + "): " + endangeredCells);
						addEndangeredCells(x, y, endangeredCells);

						// System.out.println("Dangerous cells at coords (0, 2): " +
						// Game.getBoard().getDangerousCells(0, 2));
						// System.out.println("Potential dangerous cells at coords (0, 2): " +
						// Game.getBoard().getPotentialDangerousCells(0, 2));

						break;

					case "Knight":
						Knight knight = new Knight(x, y, player);
						knight.setPieceGUI();
						potentialEndangeredCells = knight.getPotentialDangerousMoves(Game.getBoard());
						addPotentialEndangeredCells(x, y, potentialEndangeredCells);
						System.out.println("Knight's potential dangerous cells at coords: (" + x + ", " + y + "): "
								+ potentialEndangeredCells);
						endangeredCells = knight.getDangerousMoves(Game.getBoard());
						addEndangeredCells(x, y, endangeredCells);
						System.out.println(
								"Knight's dangerous cells at coords: (" + x + ", " + y + "): " + endangeredCells);

						break;

					case "Bishop":
						Bishop bishop = new Bishop(x, y, player);
						bishop.setPieceGUI();
						potentialEndangeredCells = bishop.getPotentialDangerousMoves(Game.getBoard());
						addPotentialEndangeredCells(x, y, potentialEndangeredCells);
						System.out.println("Bishop's potential dangerous cells at coords: (" + x + ", " + y + "): "
								+ potentialEndangeredCells);
						endangeredCells = bishop.getDangerousMoves(Game.getBoard());
						addEndangeredCells(x, y, endangeredCells);
						System.out.println(
								"Bishop's dangerous cells at coords: (" + x + ", " + y + "): " + endangeredCells);
						break;

					case "Queen":
						Queen queen = new Queen(x, y, player);
						queen.setPieceGUI();
						potentialEndangeredCells = queen.getPotentialDangerousMoves(Game.getBoard());
						addPotentialEndangeredCells(x, y, potentialEndangeredCells);
						System.out.println("Queen's potential dangerous cells at coords: (" + x + ", " + y + "): "
								+ potentialEndangeredCells);
						endangeredCells = queen.getDangerousMoves(Game.getBoard());
						addEndangeredCells(x, y, endangeredCells);
						System.out.println(
								"Queen's dangerous cells at coords: (" + x + ", " + y + "): " + endangeredCells);
						break;

					case "King":
						King king = new King(x, y, player);
						king.setPieceGUI();
						potentialEndangeredCells = king.getPotentialDangerousMoves(Game.getBoard());
						addPotentialEndangeredCells(x, y, potentialEndangeredCells);
						System.out.println("King's potential dangerous cells at coords: (" + x + ", " + y + "): "
								+ potentialEndangeredCells);
						endangeredCells = king.getDangerousMoves(Game.getBoard());
						addEndangeredCells(x, y, endangeredCells);
						System.out.println(
								"King's dangerous cells at coords: (" + x + ", " + y + "): " + endangeredCells);
						break;

					case "Pawn":
						Pawn pawn = new Pawn(x, y, player);
						pawn.setPieceGUI();
						potentialEndangeredCells = pawn.getPotentialDangerousMoves(Game.getBoard());
						addPotentialEndangeredCells(x, y, potentialEndangeredCells);
						System.out.println("Pawn's potential dangerous cells at coords: (" + x + ", " + y + "): "
								+ potentialEndangeredCells);
						endangeredCells = pawn.getDangerousMoves(Game.getBoard());
						addEndangeredCells(x, y, endangeredCells);
						System.out.println(
								"Pawn's dangerous cells at coords: (" + x + ", " + y + "): " + endangeredCells);
						break;

					}
				}
			}
		}
	}

	public int getCellPlayer(int x, int y) {
		return board.get(y).get(x).getPlayer();

	}

	public String getCellPiece(int x, int y) {
		return board.get(y).get(x).getPiece();
	}

	public ArrayList<ArrayList<Integer>> getPotentialDangerousCells(int x, int y) {
		Cell test = board.get(y).get(x);
		return test.getPotentialDangerousCells();
	}

	public ArrayList<ArrayList<Integer>> getDangerousCells(int x, int y) {
		Cell test = board.get(y).get(x);
		return test.getDangerousCells();
	}

	public boolean isCellEmpty(int x, int y) {

		return board.get(y).get(x).isEmpty();
	}

	public void changeCell(int x, int y, int player, String piece) {

		Cell cell = new Cell(x, y, player, piece);
		board.get(y).set(x, cell);

	}

	public void changeCell(int x, int y, int player, String piece, ArrayList<ArrayList<Integer>> potentialDangerousCell,
			ArrayList<ArrayList<Integer>> dangerousCells) {

		Cell cell = new Cell(x, y, player, piece, potentialDangerousCell, dangerousCells);
		board.get(y).set(x, cell);

	}

	public void removeCell(int x, int y, ArrayList<ArrayList<Integer>> potentialDangerousCell,
			ArrayList<ArrayList<Integer>> dangerousCells) {

		Cell emptyCell = new Cell(x, y, potentialDangerousCell, dangerousCells);
		board.get(y).set(x, emptyCell);
	}

	public void addDangerousCell(int x, int y, int dangerousCellX, int dangerousCellY) {
		board.get(y).get(x).addDangerousCell(dangerousCellX, dangerousCellY);
	}

	public void addPotentialDangerousCell(int x, int y, int potentialDangerousCellX, int potentialDangerousCellY) {
		board.get(y).get(x).addPotentialDangerousCell(potentialDangerousCellX, potentialDangerousCellY);
	}

	public void removePotentialDangerousCell(int x, int y, int potentialDangerousCellX, int potentialDangerousCellY) {
		board.get(y).get(x).removePotentialDangerousCell(potentialDangerousCellX, potentialDangerousCellY);
	}

	public void removeDangerousCell(int x, int y, int dangerousCellX, int dangerousCellY) {
		board.get(y).get(x).removeDangerousCell(dangerousCellX, dangerousCellY);
	}

	public void addEndangeredCells(int x, int y, ArrayList<ArrayList<Integer>> endangeredCells) {
		for (int i = 0; i < endangeredCells.size(); i++) {
			int potentialEndangeredCellX = endangeredCells.get(i).get(0);
			int potentialEndangeredCellY = endangeredCells.get(i).get(1);
			board.get(potentialEndangeredCellY).get(potentialEndangeredCellX).addDangerousCell(x, y);
		}
	}

	public void addPotentialEndangeredCells(int x, int y, ArrayList<ArrayList<Integer>> potentialEndangeredCells) {
		for (int i = 0; i < potentialEndangeredCells.size(); i++) {
			int potentialEndangeredCellX = potentialEndangeredCells.get(i).get(0);
			int potentialEndangeredCellY = potentialEndangeredCells.get(i).get(1);
			board.get(potentialEndangeredCellY).get(potentialEndangeredCellX).addPotentialDangerousCell(x, y);
			// System.out.println("After adding " + i + ": Potential dangerous cells at
			// coords (0, 2): " + Game.getBoard().getPotentialDangerousCells(0, 2));
		}
	}

	public void removeEndangeredCells(int x, int y, ArrayList<ArrayList<Integer>> endangeredCells) {
		for (int i = 0; i < endangeredCells.size(); i++) {
			int endangeredCellX = endangeredCells.get(i).get(0);
			int endangeredCellY = endangeredCells.get(i).get(1);
			board.get(endangeredCellY).get(endangeredCellX).removeDangerousCell(x, y);
		}
	}

	public void removePotentialEndangeredCells(int x, int y, ArrayList<ArrayList<Integer>> potentialEndangeredCells) {
		for (int i = 0; i < potentialEndangeredCells.size(); i++) {
			int potentialEndangeredCellX = potentialEndangeredCells.get(i).get(0);
			int potentialEndangeredCellY = potentialEndangeredCells.get(i).get(1);
			board.get(potentialEndangeredCellY).get(potentialEndangeredCellX).removePotentialDangerousCell(x, y);
		}
	}

	public void removeAllDangerAtCell(int x, int y) {
		ArrayList<ArrayList<Integer>> dangerousCells = getDangerousCells(x, y);
		for (int i = 0; i < dangerousCells.size(); i++) {
			int dangerousCellX = dangerousCells.get(i).get(0);
			int dangerousCellY = dangerousCells.get(i).get(1);
			board.get(y).get(x).removeDangerousCell(dangerousCellX, dangerousCellY);
		}

	}
	
	public void changePlayerOneKingCoords(int x, int y) {
		ArrayList<Integer> coords = new ArrayList<Integer>();
		coords.add(x);
		coords.add(y);

		playerOneKingCoords = coords;
	}

	public void changePlayerTwoKingCoords(int x, int y) {
		ArrayList<Integer> coords = new ArrayList<Integer>();
		coords.add(x);
		coords.add(y);

		playerTwoKingCoords = coords;
	}

	public ArrayList<Integer> getPlayerOneKingCoords() {
		return playerOneKingCoords;
	}

	public ArrayList<Integer> getPlayerTwoKingCoords() {
		return playerTwoKingCoords;
	}

}
