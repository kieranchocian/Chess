package Chess;

import java.awt.Color;
import java.awt.Point;
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
	private static ArrayList<Point> allowableCoords = new ArrayList<Point>();
	private static ArrayList<Point> previousDangerousCoords = new ArrayList<Point>();
	private static ArrayList<Point> previousPotentialDangerousCoords = new ArrayList<Point>();
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
		board.changePlayerOneKingCoords(4, 0);
		board.changePlayerTwoKingCoords(4, 7);
	}

	public static Board getBoard() {
		return board;
	}

	public static void changeBoardGUICellColour(int x, int y, Color colour) {
		window.changeCellColour(x, y, colour);
	}

	public static void changeBoardGUICellColour(ArrayList<Point> allowableCoords, Color colour) {
		for (int i = 0; i < allowableCoords.size(); i++) {
			window.changeCellColour(allowableCoords.get(i).x, allowableCoords.get(i).y, colour);

		}
	}

	public static void changeBoardGUICellPiece(int x, int y, String pieceLocation) {
		window.changeCellPiece(x, y, pieceLocation);
	}

	public static void changeBoardCell(int x, int y, int player, String piece, String pieceLocation,
			ArrayList<Point> potentialDangerousCells, ArrayList<Point> dangerousCells) {
		board.changeCell(x, y, player, piece, potentialDangerousCells, dangerousCells, true);
		changeBoardGUICellPiece(x, y, pieceLocation);
	}

	public static void removeBoardCell(int x, int y, ArrayList<Point> potentialDangerousCells,
			ArrayList<Point> dangerousCells) {
		board.removeCell(x, y, potentialDangerousCells, dangerousCells);
		changeBoardGUICellPiece(x, y, "null");
	}

	public static boolean isPlayerInCheck(int player) {
		return board.getController().isPlayerInCheck(player);
	}

	public static void showMessage(String message) {
		BoardGUI.showMessage(message);
	}

	public static void actionPerformed(int selectedX, int selectedY) {

		System.out.println("Player one pieces: " + board.getPlayerPiecesCoords(1));
		System.out.println("Player two pieces: " + board.getPlayerPiecesCoords(2));

		int selectedPlayer = board.getCellPlayer(selectedX, selectedY);
		String selectedPiece = board.getCellPiece(selectedX, selectedY);
		boolean isEmpty = board.isCellEmpty(selectedX, selectedY);
		boolean continueOn = true;

		System.out.println("player selected: " + selectedPlayer);

		if (board.getController().isGameComplete() == false) {

			if (piecePreviouslySelected) {

				// user wants to move previously-selected piece into selected cell

				boolean pieceMoved = false;
				boolean castlingLeft = false;
				boolean castlingRight = false;

				for (int i = 0; i < allowableCoords.size(); i++) {
					if (allowableCoords.get(i).x == selectedX && allowableCoords.get(i).y == selectedY) {

						// piece is allowed to move into selected cell

						// getting cell's dangerous coords pre-update

						ArrayList<Point> previousCellDangerousCoords = board.getDangerousCells(previouslySelectedX,
								previouslySelectedY);
						ArrayList<Point> selectedCellDangerousCoords = board.getDangerousCells(selectedX, selectedY);

						// getting pre-update information for castling

						ArrayList<Point> castleOldCellDangerousCoords = new ArrayList<Point>();
						ArrayList<Point> castleNewCellDangerousCoords = new ArrayList<Point>();
						String castlePieceLocation = null;
						ArrayList<Point> castleOldDangerousCoords = new ArrayList<Point>();
						ArrayList<Point> castleOldPotentialDangerousCoords = new ArrayList<Point>();
						ArrayList<Point> castleNewDangerousCoords = new ArrayList<Point>();
						ArrayList<Point> castleNewPotentialDangerousCoords = new ArrayList<Point>();

						if (previouslySelectedPiece.equals("King") && selectedX == previouslySelectedX - 2) {
							castlingLeft = true;
							castleNewCellDangerousCoords = board.getDangerousCells(3, previouslySelectedY);
							castleOldCellDangerousCoords = board.getDangerousCells(0, previouslySelectedY);
							Castle castledCastle = new Castle(3, previouslySelectedY, previouslySelectedPlayer);
							castlePieceLocation = castledCastle.pieceLocation;
							castleOldDangerousCoords = castledCastle.getDangerousMoves(board);
							castleOldPotentialDangerousCoords = castledCastle.getPotentialDangerousMoves(board);
						}

						if (previouslySelectedPiece.equals("King") && selectedX == previouslySelectedX + 2) {
							castlingRight = true;
							castleNewCellDangerousCoords = board.getDangerousCells(5, previouslySelectedY);
							castleOldCellDangerousCoords = board.getDangerousCells(7, previouslySelectedY);
							Castle castledCastle = new Castle(5, previouslySelectedY, previouslySelectedPlayer);
							castlePieceLocation = castledCastle.pieceLocation;
							castleOldDangerousCoords = castledCastle.getDangerousMoves(board);
							castleOldPotentialDangerousCoords = castledCastle.getPotentialDangerousMoves(board);
						}

						// changing board to reflect the piece's move

						updateBoardPiece(previouslySelectedX, previouslySelectedY, previouslySelectedPiece,
								previousPieceSelectedLocation, selectedX, selectedY, previouslySelectedPlayer,
								selectedPlayer);

						// changing board to reflect castle moving if castling

						if (castlingLeft) {
							updateBoardPiece(0, previouslySelectedY, "Castle", castlePieceLocation, 3,
									previouslySelectedY, previouslySelectedPlayer, previouslySelectedPlayer);
						}

						if (castlingRight) {
							updateBoardPiece(7, previouslySelectedY, "Castle", castlePieceLocation, 5,
									previouslySelectedY, previouslySelectedPlayer, previouslySelectedPlayer);
						}

						// amending the 50 move rule and seeing if they have surpassed it
						if (selectedPiece == null && !previouslySelectedPiece.equals("Pawn")) {
							board.getController().incrementFiftyMoveRulesMoves();
						} else {
							board.getController().resetFiftyMoveRulesMoves();
						}

						if (board.getController().getFiftyMoveRulesMoves() > 49) {
							BoardGUI.showMessage("50 move rule reached - tie game!");
							board.getController().setGameComplete(true);
						}

						// checking next possible moves of piece in case of check
						ArrayList<Point> newDangerousCoords = null;
						ArrayList<Point> newPotentialDangerousCoords = null;

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

						case "Knight":
							Knight knight = new Knight(selectedX, selectedY, previouslySelectedPlayer);
							knight.setPieceGUI();
							newDangerousCoords = knight.getDangerousMoves(Game.getBoard());
							newPotentialDangerousCoords = knight.getPotentialDangerousMoves(Game.getBoard());
							checkForCheck(knight.getPossibleMoves(false, Game.getBoard()), previouslySelectedPlayer);
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
							checkForCheck(king.getPossibleMoves(false, false, Game.getBoard()),
									previouslySelectedPlayer);
							// kingInCheck is false in the getPossibleMoves parameters as it doesn't matter
							// here. It won't have updated though

							if (previouslySelectedPlayer == 1) {
								board.changePlayerOneKingCoords(selectedX, selectedY);
							} else if (previouslySelectedPlayer == 2) {
								board.changePlayerTwoKingCoords(selectedX, selectedY);
							}
							
							if (castlingLeft) {
								Castle castledCastle = new Castle(3, previouslySelectedY, previouslySelectedPlayer);
								castledCastle.setPieceGUI();
								castleNewDangerousCoords = castledCastle.getDangerousMoves(Game.getBoard());
								castleNewPotentialDangerousCoords = castledCastle.getPotentialDangerousMoves(Game.getBoard());
								checkForCheck(castledCastle.getPossibleMoves(false, Game.getBoard()), previouslySelectedPlayer);
							}
							
							if (castlingRight) {
								Castle castledCastle = new Castle(5, previouslySelectedY, previouslySelectedPlayer);
								castledCastle.setPieceGUI();
								castleNewDangerousCoords = castledCastle.getDangerousMoves(Game.getBoard());
								castleNewPotentialDangerousCoords = castledCastle.getPotentialDangerousMoves(Game.getBoard());
								checkForCheck(castledCastle.getPossibleMoves(false, Game.getBoard()), previouslySelectedPlayer);
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

						}

						// updating the danger and potential danger caused by movement

						updateBoardDanger(previouslySelectedX, previouslySelectedY, previousDangerousCoords,
								previousPotentialDangerousCoords, selectedX, selectedY, newDangerousCoords,
								newPotentialDangerousCoords, previousCellDangerousCoords, selectedCellDangerousCoords);
						
						if (castlingLeft) {
							updateBoardDanger(0, previouslySelectedY, castleOldDangerousCoords,
									castleOldPotentialDangerousCoords, 3, previouslySelectedY, castleNewDangerousCoords,
									castleNewPotentialDangerousCoords, castleOldCellDangerousCoords, castleNewCellDangerousCoords);
						}

						if (castlingRight) {
							updateBoardDanger(7, previouslySelectedY, castleOldDangerousCoords,
									castleOldPotentialDangerousCoords, 5, previouslySelectedY, castleNewDangerousCoords,
									castleNewPotentialDangerousCoords, castleOldCellDangerousCoords, castleNewCellDangerousCoords);
						}

						// checking if opponent is in checkmate

						if (board.getController().isPlayerInCheck(1)) {

							Point selectedCoords = new Point();
							selectedCoords.x = selectedX;
							selectedCoords.y = selectedY;

							if (Pathing.isCheckmate(board.getKingCoords(1), selectedCoords, 1, board)) {
								BoardGUI.showMessage("Checkmate - player 2 wins!");
								board.getController().setGameComplete(true);
							} else {
								System.out.println("Not checkmate");
							}
						}

						if (board.getController().isPlayerInCheck(2)) {

							Point selectedCoords = new Point();
							selectedCoords.x = selectedX;
							selectedCoords.y = selectedY;

							if (Pathing.isCheckmate(board.getKingCoords(2), selectedCoords, 2, board)) {
								BoardGUI.showMessage("Checkmate - player 1 wins!");
								board.getController().setGameComplete(true);
							} else {
								System.out.println("Not checkmate");
							}
						}

						// checking if players are in stalemate
						if (!board.getController().isGameComplete()) {
							if (checkForStalemate(previouslySelectedPlayer, board)) {
								BoardGUI.showMessage("Stalemate - tie game!");
								board.getController().setGameComplete(true);
							}
						}

						// changing player turn

						if (previouslySelectedPlayer == 1) {
							board.getController().setPlayerTurn(2);
						} else if (previouslySelectedPlayer == 2) {
							board.getController().setPlayerTurn(1);
						}
						pieceMoved = true;
						continueOn = false;

					}

				}

				if (!pieceMoved) {

					// piece is not allowed to move into selected cell
					continueOn = true;

				}

				// reset selected piece and remove highlighted cells

				piecePreviouslySelected = false;

				for (int j = 0; j < allowableCoords.size(); j++) {

					int xCoord = allowableCoords.get(j).x;
					int yCoord = allowableCoords.get(j).y;

					window.removeRedCell(xCoord, yCoord);

				}

				window.removeRedCell(previouslySelectedX, previouslySelectedY);
			}

			if (continueOn) {
				if (piecePreviouslySelected == false) {

					// no piece was previously selected so the user wants to move selected cell

					if (selectedPlayer == board.getController().getPlayerTurn()) {
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
								allowableCoords = king.getPossibleMoves(
										board.getController().isPlayerInCheck(selectedPlayer), true, Game.getBoard());
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

		System.out.println("Fifty move rule: " + board.getController().getFiftyMoveRulesMoves());

		if (selectedPiece != null) {
			System.out.println("Has piece been moved? " + board.hasPieceMoved(selectedX, selectedY));
		}

	}

	/*
	 * This method takes a cell and the coordinates that WERE dangerous to it. For
	 * each of these coordinates, it recalculates the cells it is dangerous to.
	 * 
	 */

	public static void recalculateDangerAtCell(int x, int y, ArrayList<Point> dangerousCoordsAtPoint) {

		System.out.println("Dangerous coords at point: " + dangerousCoordsAtPoint);
		int numberOfDangerousCoords = dangerousCoordsAtPoint.size();

		for (int i = 0; i < numberOfDangerousCoords; i++) {

			int xCoord = dangerousCoordsAtPoint.get(i).x;
			int yCoord = dangerousCoordsAtPoint.get(i).y;
			int player = board.getCellPlayer(xCoord, yCoord);
			String piece = board.getCellPiece(xCoord, yCoord);
			ArrayList<Point> dangerousCoords = null;
			ArrayList<Point> potentialCoords = null;

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
						int xxx = potentialCoords.get(k).x;
						int yyy = potentialCoords.get(k).y;

						board.removeDangerousCell(xxx, yyy, xCoord, yCoord);

					}

				}

				// adding the each piece's dangerous coords to the board

				for (int j = 0; j < dangerousCoords.size(); j++) {

					int xx = dangerousCoords.get(j).x;
					int yy = dangerousCoords.get(j).y;

					board.addDangerousCell(xx, yy, xCoord, yCoord);

				}
			}
		}
	}

	public static void updateBoardPiece(int previousX, int previousY, String pieceName, String pieceIconLocation,
			int newX, int newY, int player, int previousPlayer) {

		changeBoardCell(newX, newY, player, pieceName, pieceIconLocation, board.getPotentialDangerousCells(newX, newY),
				board.getDangerousCells(newX, newY));
		removeBoardCell(previousX, previousY, board.getPotentialDangerousCells(previousX, previousY),
				board.getDangerousCells(previousX, previousY));

		board.removePlayerPiecesCoords(player, new Point(previousX, previousY));
		board.addPlayerPiecesCoords(player, new Point(newX, newY));

		board.removePlayerPiecesCoords(previousPlayer, new Point(newX, newY));

	}

	public static void updateBoardDanger(int previousX, int previousY, ArrayList<Point> previousPieceDangerousCoords,
			ArrayList<Point> previousPiecePotentialDangerousCoords, int newX, int newY,
			ArrayList<Point> newPieceDangerousCoords, ArrayList<Point> newPiecePotentialDangerousCoords,
			ArrayList<Point> previousCoordsDangerousCoords, ArrayList<Point> newCoordsDangerousCoords) {

		// removing danger created by piece in old position
		board.removeEndangeredCells(previousX, previousY, previousPieceDangerousCoords);
		board.removePotentialEndangeredCells(previousX, previousY, previousPiecePotentialDangerousCoords);

		// adding danger created by piece in new position
		board.addEndangeredCells(newX, newY, newPieceDangerousCoords);
		board.addPotentialEndangeredCells(newX, newY, newPiecePotentialDangerousCoords);

		/*
		 * taking the pieces that were dangerous to the new and old cells, and
		 * recalculating their danger to other cells. This is done because a piece
		 * moving alters where other pieces can go
		 */
		recalculateDangerAtCell(newX, newY, newCoordsDangerousCoords);
		recalculateDangerAtCell(previousY, previousY, previousCoordsDangerousCoords);
	}

	/*
	 * This method takes a piece's allowable coordinates and checks if the
	 * opponent's king is within them. if so, the opponent is in check. It is used
	 * after a move is made and the piece's new allowable coordinates are calculated
	 */

	public static void checkForCheck(ArrayList<Point> allowableMoves, int player) {

		Point opponentKingCoords = new Point();
		boolean check = false;

		switch (player) {
		case 1:
			opponentKingCoords = board.getKingCoords(2);
			break;
		case 2:
			opponentKingCoords = board.getKingCoords(1);
			break;

		}

		for (int i = 0; i < allowableMoves.size(); i++) {
			if (allowableMoves.get(i).equals(opponentKingCoords)) {
				switch (player) {

				case 1:
					board.getController().setPlayerCheck(2, true);
					check = true;
					BoardGUI.showMessage("Player 2 is in check!");
					break;

				case 2:
					board.getController().setPlayerCheck(1, true);
					check = true;
					BoardGUI.showMessage("Player 1 is in check!");
					break;

				}
			}

		}

		if (check == false) {
			board.getController().setPlayerCheck(1, false);
			board.getController().setPlayerCheck(2, false);
		}
	}

	public static boolean checkForStalemate(int currentPlayer, Board board) {

		int opponentPlayer = 0;
		boolean stalemate = true;

		switch (currentPlayer) {
		case 1:
			opponentPlayer = 2;
			break;
		case 2:
			opponentPlayer = 1;
			break;

		}

		ArrayList<Point> opponentPiecesCoords = board.getPlayerPiecesCoords(opponentPlayer);

		for (int i = 0; i < opponentPiecesCoords.size(); i++) {

			Point opponentPieceCoords = opponentPiecesCoords.get(i);

			String opponentPiece = board.getCellPiece(opponentPieceCoords.x, opponentPieceCoords.y);
			if (!Pathing.getAllowableMoves(opponentPieceCoords.x, opponentPieceCoords.y, opponentPlayer, opponentPiece,
					true, board).isEmpty()) {

				stalemate = false;
				break;
			}

		}

		if (stalemate) {
			return true;

		} else {
			return false;
		}

	}

}
