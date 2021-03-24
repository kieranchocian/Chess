package Chess;

import java.awt.Point;
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
	private Point playerOneKingCoords = new Point();
	private Point playerTwoKingCoords = new Point();
	private ArrayList<Point> playerOnePiecesCoords = new ArrayList<Point>();
	private ArrayList<Point> playerTwoPiecesCoords = new ArrayList<Point>();

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
		}

	}

	/*
	 * This adds dangerousCells and potentialDangerousCells to each cell in board.
	 * Additionally, it adds coords of each player's pieces to playerXPiecesCoords
	 * in order to save time creating a new method and going through all pieces
	 * again
	 */
	public void initialiseDangerousCells() {

		ArrayList<Point> potentialEndangeredCells;
		ArrayList<Point> endangeredCells;

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

					// adding coords of each player's pieces to playerXPiecesCoords

					switch (player) {
					case 1:
						playerOnePiecesCoords.add(new Point(x, y));
						break;

					case 2:
						playerTwoPiecesCoords.add(new Point(x, y));
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

	public ArrayList<Point> getPotentialDangerousCells(int x, int y) {
		Cell test = board.get(y).get(x);
		return test.getPotentialDangerousCells();
	}

	public ArrayList<Point> getDangerousCells(int x, int y) {
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

	public void changeCell(int x, int y, int player, String piece, ArrayList<Point> potentialDangerousCell,
			ArrayList<Point> dangerousCells) {

		Cell cell = new Cell(x, y, player, piece, potentialDangerousCell, dangerousCells);
		board.get(y).set(x, cell);

	}

	public void removeCell(int x, int y, ArrayList<Point> potentialDangerousCell, ArrayList<Point> dangerousCells) {

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

	public void addEndangeredCells(int x, int y, ArrayList<Point> endangeredCells) {
		for (int i = 0; i < endangeredCells.size(); i++) {
			int potentialEndangeredCellX = endangeredCells.get(i).x;
			int potentialEndangeredCellY = endangeredCells.get(i).y;
			board.get(potentialEndangeredCellY).get(potentialEndangeredCellX).addDangerousCell(x, y);
		}
	}

	public void addPotentialEndangeredCells(int x, int y, ArrayList<Point> potentialEndangeredCells) {
		for (int i = 0; i < potentialEndangeredCells.size(); i++) {
			int potentialEndangeredCellX = potentialEndangeredCells.get(i).x;
			int potentialEndangeredCellY = potentialEndangeredCells.get(i).y;
			board.get(potentialEndangeredCellY).get(potentialEndangeredCellX).addPotentialDangerousCell(x, y);
			// System.out.println("After adding " + i + ": Potential dangerous cells at
			// coords (0, 2): " + Game.getBoard().getPotentialDangerousCells(0, 2));
		}
	}

	public void removeEndangeredCells(int x, int y, ArrayList<Point> endangeredCells) {
		for (int i = 0; i < endangeredCells.size(); i++) {
			int endangeredCellX = endangeredCells.get(i).x;
			int endangeredCellY = endangeredCells.get(i).y;
			board.get(endangeredCellY).get(endangeredCellX).removeDangerousCell(x, y);
		}
	}

	public void removePotentialEndangeredCells(int x, int y, ArrayList<Point> potentialEndangeredCells) {
		for (int i = 0; i < potentialEndangeredCells.size(); i++) {
			int potentialEndangeredCellX = potentialEndangeredCells.get(i).x;
			int potentialEndangeredCellY = potentialEndangeredCells.get(i).y;
			board.get(potentialEndangeredCellY).get(potentialEndangeredCellX).removePotentialDangerousCell(x, y);
		}
	}

	public void removeAllDangerAtCell(int x, int y) {
		ArrayList<Point> dangerousCells = getDangerousCells(x, y);
		for (int i = 0; i < dangerousCells.size(); i++) {
			int dangerousCellX = dangerousCells.get(i).x;
			int dangerousCellY = dangerousCells.get(i).y;
			board.get(y).get(x).removeDangerousCell(dangerousCellX, dangerousCellY);
		}

	}

	public void changePlayerOneKingCoords(int x, int y) {
		Point coords = new Point();
		coords.x = x;
		coords.y = y;

		playerOneKingCoords = coords;
	}

	public void changePlayerTwoKingCoords(int x, int y) {
		Point coords = new Point();
		coords.x = x;
		coords.y = y;

		playerTwoKingCoords = coords;
	}

	public Point getKingCoords(int player) {
		Point kingCoords = new Point();
		switch (player) {
		case 1:
			kingCoords = playerOneKingCoords;
			break;

		case 2:
			kingCoords = playerTwoKingCoords;
			break;
		}
		return kingCoords;
	}

	public ArrayList<Point> getPlayerPiecesCoords(int player) {
		ArrayList<Point> playerPiecesCoords = new ArrayList<Point>();
		switch (player) {
		case 1:
			playerPiecesCoords = playerOnePiecesCoords;
			break;

		case 2:
			playerPiecesCoords = playerTwoPiecesCoords;
			break;
		}
		return playerPiecesCoords;
	}

	public void addPlayerPiecesCoords(int player, Point coords) {
		switch (player) {
		case 1:
			playerOnePiecesCoords.add(coords);
			break;

		case 2:
			playerTwoPiecesCoords.add(coords);
			break;
		}
	}

	public void removePlayerPiecesCoords(int player, Point coords) {
		switch (player) {
		case 1:
			playerOnePiecesCoords.remove(coords);
			break;

		case 2:
			playerTwoPiecesCoords.remove(coords);
			break;
		}

	}

}
