package Chess;

import java.awt.Color;
import java.util.ArrayList;

import GUI.BoardGUI;
import Pieces.Bishop;
import Pieces.Castle;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Queen;

public class Game {

	private static Board board;
	private static BoardGUI window;
	private static Controller controller;
	private static ArrayList<ArrayList<Integer>> allowableCoords = new ArrayList<ArrayList<Integer>>();
	private static ArrayList<ArrayList<Integer>> previousDangerousCoords = new ArrayList<ArrayList<Integer>>();
	private static ArrayList<ArrayList<Integer>> previousPotentialDangerousCoords = new ArrayList<ArrayList<Integer>>();
	private static boolean piecePreviouslySelected = false;
	private static String previousPieceSelectedLocation;
	private static int previouslySelectedX;
	private static int previouslySelectedY;
	private static int previouslySelectedPlayer;
	private static String previouslySelectedPiece;

	public static void main(String[] args) {
		window = new BoardGUI();
		System.out.println("BoardGUI created");
		board = new Board();
		System.out.println("Board created");
		board.initialiseDangerousCells();
		System.out.println("Dangerous cells added");
		controller = new Controller();
		controller.setGameComplete(false);
		controller.setPlayerTurn(1);
		board.changePlayerOneKingCoords(4, 0);
		board.changePlayerTwoKingCoords(4, 7);
	}

	public static Board getBoard() {
		return board;
	}

	public static String getBoardPiece(int x, int y) {
		return board.getCellPiece(x, y);
	}

	public static int getBoardPlayer(int x, int y) {
		return board.getCellPlayer(x, y);
	}

	public static boolean getBoardEmpty(int x, int y) {
		return board.isCellEmpty(x, y);
	}

	public static void changeBoardGUICellColour(int x, int y, Color colour) {
		window.changeCellColour(x, y, colour);
	}

	public static void changeBoardGUICellColour(ArrayList<ArrayList<Integer>> allowableCoords, Color colour) {
		for (int i = 0; i < allowableCoords.size(); i++) {
			window.changeCellColour(allowableCoords.get(i).get(0), allowableCoords.get(i).get(1), colour);

		}
	}

	public static void changeBoardGUICellPiece(int x, int y, String pieceLocation) {
		window.changeCellPiece(x, y, pieceLocation);
	}

	public static void changeBoardCell(int x, int y, int player, String piece, String pieceLocation,
			ArrayList<ArrayList<Integer>> potentialDangerousCells, ArrayList<ArrayList<Integer>> dangerousCells) {
		board.changeCell(x, y, player, piece, potentialDangerousCells, dangerousCells);
		changeBoardGUICellPiece(x, y, pieceLocation);
	}

	public static void removeBoardCell(int x, int y, ArrayList<ArrayList<Integer>> potentialDangerousCells,
			ArrayList<ArrayList<Integer>> dangerousCells) {
		board.removeCell(x, y, potentialDangerousCells, dangerousCells);
		changeBoardGUICellPiece(x, y, "null");
	}

	public static void showMessage(String message) {
		BoardGUI.showMessage(message);
	}

	public static void actionPerformed(int selectedX, int selectedY) {

		System.out.println("Player one in check: " + controller.isPlayerInCheck(1));
		System.out.println("Player two in check: " + controller.isPlayerInCheck(2));

		int selectedPlayer = getBoardPlayer(selectedX, selectedY);
		String selectedPiece = getBoardPiece(selectedX, selectedY);
		boolean isEmpty = Game.getBoardEmpty(selectedX, selectedY);
		boolean continueOn = true;

		System.out.println("player selected: " + selectedPlayer);

		if (controller.isGameComplete() == false) {

			if (piecePreviouslySelected) {

				// user wants to move previously-selected piece into selected cell

				boolean pieceMoved = false;

				for (int i = 0; i < allowableCoords.size(); i++) {
					if (allowableCoords.get(i).get(0) == selectedX && allowableCoords.get(i).get(1) == selectedY) {

						// piece is allowed to move into selected cell

						selectedPiece = board.getCellPiece(selectedX, selectedY);
						selectedPlayer = board.getCellPlayer(selectedX, selectedY);

						ArrayList<ArrayList<Integer>> selectedCellDangerousCoords = board.getDangerousCells(selectedX,
								selectedY);
						ArrayList<ArrayList<Integer>> previousCellDangerousCoords = board
								.getDangerousCells(previouslySelectedX, previouslySelectedY);

						// changing board to reflect the piece's move
						changeBoardCell(selectedX, selectedY, previouslySelectedPlayer, previouslySelectedPiece,
								previousPieceSelectedLocation, board.getPotentialDangerousCells(selectedX, selectedY),
								board.getDangerousCells(selectedX, selectedY));
						removeBoardCell(previouslySelectedX, previouslySelectedY,
								board.getPotentialDangerousCells(previouslySelectedX, previouslySelectedY),
								board.getDangerousCells(previouslySelectedX, previouslySelectedY));

						// checking next possible moves of piece in case of check
						ArrayList<ArrayList<Integer>> newDangerousCoords = null;
						ArrayList<ArrayList<Integer>> newPotentialDangerousCoords = null;

						switch (previouslySelectedPiece) {

						case "Castle":
							Castle castle = new Castle(selectedX, selectedY, previouslySelectedPlayer);
							castle.setPieceGUI();
							newDangerousCoords = castle.getDangerousMoves(Game.getBoard());
							newPotentialDangerousCoords = castle.getPotentialDangerousMoves(Game.getBoard());
							checkForCheck(castle.getPossibleMoves(false, Game.getBoard()), previouslySelectedPlayer);
							break;

						case "Bishop":
							Bishop bishop = new Bishop(selectedX, selectedY, previouslySelectedPlayer);
							bishop.setPieceGUI();
							newDangerousCoords = bishop.getDangerousMoves(Game.getBoard());
							newPotentialDangerousCoords = bishop.getPotentialDangerousMoves(Game.getBoard());
							checkForCheck(bishop.getPossibleMoves(false, Game.getBoard()), previouslySelectedPlayer);
							break;

						case "Queen":
							Queen queen = new Queen(selectedX, selectedY, previouslySelectedPlayer);
							queen.setPieceGUI();
							newDangerousCoords = queen.getDangerousMoves(Game.getBoard());
							newPotentialDangerousCoords = queen.getPotentialDangerousMoves(Game.getBoard());
							checkForCheck(queen.getPossibleMoves(false, Game.getBoard()), previouslySelectedPlayer);
							break;

						case "King":
							King king = new King(selectedX, selectedY, previouslySelectedPlayer);
							king.setPieceGUI();
							newDangerousCoords = king.getDangerousMoves(Game.getBoard());
							newPotentialDangerousCoords = king.getPotentialDangerousMoves(Game.getBoard());
							checkForCheck(king.getPossibleMoves(false, Game.getBoard()), previouslySelectedPlayer);

							if (previouslySelectedPlayer == 1) {
								board.changePlayerOneKingCoords(selectedX, selectedY);
							} else if (previouslySelectedPlayer == 2) {
								board.changePlayerTwoKingCoords(selectedX, selectedY);
							}

							break;

						case "Pawn":

							// promote to queen if at end of board
							if ((selectedY == 7 && previouslySelectedPlayer == 1)
									|| (selectedY == 0 && previouslySelectedPlayer == 2)) {
								Queen queenPawn = new Queen(selectedX, selectedY, previouslySelectedPlayer);
								queenPawn.setPieceGUI();

								changeBoardCell(selectedX, selectedY, previouslySelectedPlayer, "Queen",
										queenPawn.pieceLocation, board.getPotentialDangerousCells(selectedX, selectedY),
										board.getDangerousCells(selectedX, selectedY));
								newDangerousCoords = queenPawn.getDangerousMoves(Game.getBoard());
								newPotentialDangerousCoords = queenPawn.getPotentialDangerousMoves(Game.getBoard());
								checkForCheck(queenPawn.getPossibleMoves(false, Game.getBoard()),
										previouslySelectedPlayer);
								break;
							} else {
								Pawn pawn = new Pawn(selectedX, selectedY, previouslySelectedPlayer);
								pawn.setPieceGUI();
								newDangerousCoords = pawn.getDangerousMoves(Game.getBoard());
								newPotentialDangerousCoords = pawn.getPotentialDangerousMoves(Game.getBoard());
								checkForCheck(pawn.getPossibleMoves(false, Game.getBoard()), previouslySelectedPlayer);

							}
							break;

						case "Knight":
							Knight knight = new Knight(selectedX, selectedY, previouslySelectedPlayer);
							knight.setPieceGUI();
							newDangerousCoords = knight.getDangerousMoves(Game.getBoard());
							newPotentialDangerousCoords = knight.getPotentialDangerousMoves(Game.getBoard());
							checkForCheck(knight.getPossibleMoves(false, Game.getBoard()), previouslySelectedPlayer);
							break;

						}

						// removing danger created by piece in old position
						board.removeEndangeredCells(previouslySelectedX, previouslySelectedY, previousDangerousCoords);
						board.removePotentialEndangeredCells(previouslySelectedX, previouslySelectedY,
								previousPotentialDangerousCoords);

						// adding danger created by piece in new position
						board.addEndangeredCells(selectedX, selectedY, newDangerousCoords);
						board.addPotentialEndangeredCells(selectedX, selectedY, newPotentialDangerousCoords);

						/*
						 * taking the pieces that were dangerous to the new and old cells, and
						 * recalculating their danger to other cells. This is done because a piece
						 * moving alters where other pieces can go
						 */
						recalculateDangerAtCell(selectedX, selectedY, selectedCellDangerousCoords);
						recalculateDangerAtCell(previouslySelectedX, previouslySelectedY, previousCellDangerousCoords);

						if (controller.isPlayerInCheck(1)) {

							ArrayList<Integer> selectedCoords = new ArrayList<Integer>();
							selectedCoords.add(selectedX);
							selectedCoords.add(selectedY);

							if (Pathing.isCheckmate(board.getPlayerOneKingCoords(), selectedCoords, 1, board)) {
								BoardGUI.showMessage("Checkmate - player 2 wins!");
								controller.setGameComplete(true);
							} else {
								System.out.println("Not checkmate");
							}
						}

						if (controller.isPlayerInCheck(2)) {

							ArrayList<Integer> selectedCoords = new ArrayList<Integer>();
							selectedCoords.add(selectedX);
							selectedCoords.add(selectedY);

							if (Pathing.isCheckmate(board.getPlayerTwoKingCoords(), selectedCoords, 2, board)) {
								BoardGUI.showMessage("Checkmate - player 1 wins!");
								controller.setGameComplete(true);
							} else {
								System.out.println("Not checkmate");
							}
						}

						// changing player turn

						if (previouslySelectedPlayer == 1) {
							controller.setPlayerTurn(2);
						} else if (previouslySelectedPlayer == 2) {
							controller.setPlayerTurn(1);
						}
						pieceMoved = true;
						continueOn = false;

						// TODO: check for a draw

					}

				}

				if (!pieceMoved) {

					// piece is not allowed to move into selected cell
					continueOn = true;

				}

				// reset selected piece and remove highlighted cells

				piecePreviouslySelected = false;

				for (int j = 0; j < allowableCoords.size(); j++) {

					int xCoord = allowableCoords.get(j).get(0);
					int yCoord = allowableCoords.get(j).get(1);

					window.removeRedCell(xCoord, yCoord);

				}

				window.removeRedCell(previouslySelectedX, previouslySelectedY);
			}

			if (continueOn) {
				if (piecePreviouslySelected == false) {

					// no piece was previously selected so the user wants to move selected cell

					if (selectedPlayer == controller.getPlayerTurn()) {
						if (!isEmpty) {

							// selecting piece to move and displaying possible moves

							switch (selectedPiece) {

							case "Castle":
								Castle castle = new Castle(selectedX, selectedY, selectedPlayer);
								allowableCoords = castle.getPossibleMoves(true, Game.getBoard());
								System.out.println("Allowable coords for " + selectedPiece + ": " + allowableCoords);
								previousPieceSelectedLocation = castle.pieceLocation;
								previousDangerousCoords = castle.getDangerousMoves(Game.getBoard());
								previousPotentialDangerousCoords = castle.getPotentialDangerousMoves(Game.getBoard());
								break;

							case "Bishop":
								Bishop bishop = new Bishop(selectedX, selectedY, selectedPlayer);
								allowableCoords = bishop.getPossibleMoves(true, Game.getBoard());
								System.out.println("Allowable coords for " + selectedPiece + ": " + allowableCoords);
								previousPieceSelectedLocation = bishop.pieceLocation;
								previousDangerousCoords = bishop.getDangerousMoves(Game.getBoard());
								previousPotentialDangerousCoords = bishop.getPotentialDangerousMoves(Game.getBoard());
								break;

							case "Queen":
								Queen queen = new Queen(selectedX, selectedY, selectedPlayer);
								allowableCoords = queen.getPossibleMoves(true, Game.getBoard());
								System.out.println("Allowable coords for " + selectedPiece + ": " + allowableCoords);
								previousPieceSelectedLocation = queen.pieceLocation;
								previousDangerousCoords = queen.getDangerousMoves(Game.getBoard());
								previousPotentialDangerousCoords = queen.getPotentialDangerousMoves(Game.getBoard());
								break;

							case "King":
								King king = new King(selectedX, selectedY, selectedPlayer);
								allowableCoords = king.getPossibleMoves(true, Game.getBoard());
								System.out.println("Allowable coords for " + selectedPiece + ": " + allowableCoords);
								previousPieceSelectedLocation = king.pieceLocation;
								previousDangerousCoords = king.getDangerousMoves(Game.getBoard());
								previousPotentialDangerousCoords = king.getPotentialDangerousMoves(Game.getBoard());
								break;

							case "Pawn":
								Pawn pawn = new Pawn(selectedX, selectedY, selectedPlayer);
								allowableCoords = pawn.getPossibleMoves(true, Game.getBoard());
								System.out.println("Allowable coords for " + selectedPiece + ": " + allowableCoords);
								previousPieceSelectedLocation = pawn.pieceLocation;
								previousDangerousCoords = pawn.getDangerousMoves(Game.getBoard());
								previousPotentialDangerousCoords = pawn.getPotentialDangerousMoves(Game.getBoard());

								break;

							case "Knight":
								Knight knight = new Knight(selectedX, selectedY, selectedPlayer);
								allowableCoords = knight.getPossibleMoves(true, Game.getBoard());
								System.out.println("Allowable coords for " + selectedPiece + ": " + allowableCoords);
								previousPieceSelectedLocation = knight.pieceLocation;
								previousDangerousCoords = knight.getDangerousMoves(Game.getBoard());
								previousPotentialDangerousCoords = knight.getPotentialDangerousMoves(Game.getBoard());

								break;
							}

							changeBoardGUICellColour(allowableCoords, Color.RED);
							piecePreviouslySelected = true;
							previouslySelectedX = selectedX;
							previouslySelectedY = selectedY;
							previouslySelectedPlayer = selectedPlayer;
							previouslySelectedPiece = selectedPiece;

						}
					}
				}
			}
		}

	}

	/*
	 * This method takes a cell and the coordinates that WERE dangerous to it. For
	 * each of these coordinates, it recalculates the cells it is dangerous to.
	 * 
	 */

	public static void recalculateDangerAtCell(int x, int y, ArrayList<ArrayList<Integer>> dangerousCoordsAtPoint) {

		System.out.println("Dangerous coords at point: " + dangerousCoordsAtPoint);
		int numberOfDangerousCoords = dangerousCoordsAtPoint.size();

		for (int i = 0; i < numberOfDangerousCoords; i++) {

			int xCoord = dangerousCoordsAtPoint.get(i).get(0);
			int yCoord = dangerousCoordsAtPoint.get(i).get(1);
			int player = getBoardPlayer(xCoord, yCoord);
			String piece = getBoardPiece(xCoord, yCoord);
			ArrayList<ArrayList<Integer>> dangerousCoords = null;
			ArrayList<ArrayList<Integer>> potentialCoords = null;

			if (piece != null) {

				switch (piece) {

				case "Castle":
					Castle castle = new Castle(xCoord, yCoord, player);
					dangerousCoords = castle.getDangerousMoves(Game.getBoard());
					potentialCoords = castle.getPotentialDangerousMoves(Game.getBoard());
					break;

				case "Bishop":
					Bishop bishop = new Bishop(xCoord, yCoord, player);
					dangerousCoords = bishop.getDangerousMoves(Game.getBoard());
					potentialCoords = bishop.getPotentialDangerousMoves(Game.getBoard());
					break;

				case "Queen":
					Queen queen = new Queen(xCoord, yCoord, player);
					dangerousCoords = queen.getDangerousMoves(Game.getBoard());
					potentialCoords = queen.getPotentialDangerousMoves(Game.getBoard());
					break;

				case "King":
					King king = new King(xCoord, yCoord, player);
					dangerousCoords = king.getDangerousMoves(Game.getBoard());
					break;

				case "Pawn":
					Pawn pawn = new Pawn(xCoord, yCoord, player);
					dangerousCoords = pawn.getDangerousMoves(Game.getBoard());
					break;

				case "Knight":
					Knight knight = new Knight(xCoord, yCoord, player);
					dangerousCoords = knight.getDangerousMoves(Game.getBoard());
					break;

				}

				System.out.println(piece + "'s dangerous coords: " + dangerousCoords);

				// removing each piece's current dangerous coords from the board (using
				// potential coords as it's easier)

				if (potentialCoords != null) {

					for (int k = 0; k < potentialCoords.size(); k++) {
						int xxx = potentialCoords.get(k).get(0);
						int yyy = potentialCoords.get(k).get(1);

						board.removeDangerousCell(xxx, yyy, xCoord, yCoord);

					}

				}

				// adding the each piece's dangerous coords to the board

				for (int j = 0; j < dangerousCoords.size(); j++) {

					int xx = dangerousCoords.get(j).get(0);
					int yy = dangerousCoords.get(j).get(1);

					board.addDangerousCell(xx, yy, xCoord, yCoord);

				}
			}
		}
	}

	/*
	 * This method takes a piece's allowable coordinates and checks if the
	 * opponent's king is within them. if so, the opponent is in check. It is used
	 * after a move is made and the piece's new allowable coordinates are calculated
	 */

	public static void checkForCheck(ArrayList<ArrayList<Integer>> allowableMoves, int player) {

		ArrayList<Integer> opponentKingCoords = new ArrayList<Integer>();
		boolean check = false;

		switch (player) {

		case 1:
			opponentKingCoords = board.getPlayerTwoKingCoords();
			break;

		case 2:
			opponentKingCoords = board.getPlayerOneKingCoords();
			break;

		}

		for (int i = 0; i < allowableMoves.size(); i++) {
			if (allowableMoves.get(i).equals(opponentKingCoords)) {
				switch (player) {

				case 1:
					controller.setPlayerCheck(2, true);
					check = true;
					BoardGUI.showMessage("Player 2 is in check!");
					break;

				case 2:
					controller.setPlayerCheck(1, true);
					check = true;
					BoardGUI.showMessage("Player 1 is in check!");
					break;

				}
			}

		}

		if (check == false) {
			controller.setPlayerCheck(1, false);
			controller.setPlayerCheck(2, false);
		}
	}

}
