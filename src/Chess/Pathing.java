package Chess;

import java.awt.Point;
import java.util.ArrayList;
import org.apache.commons.lang3.SerializationUtils;

import Pieces.Bishop;
import Pieces.Castle;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Queen;

public abstract class Pathing {

	private static boolean continueChecking;
	private static boolean opponentPiece;

	/*
	 * Potential dangerous coordinates are those that a cell has the potential to
	 * move into if there were no other piece on the board. E.g. a queen could
	 * potentially move to all spaces vertically, horizontally, diagonally and
	 * anti-diagonally. This also gives the option for 'dangerous moves', which are
	 * moves that a piece can make if an opponent's piece is there
	 */

	public static ArrayList<Point> getPotentialHorizontalLeftMoves(int x, int y, boolean multipleSquares,
			boolean getDangerous, Board board) {

		ArrayList<Point> potentialCoords = new ArrayList<Point>();

		for (int xCoord = x - 1; xCoord > -1; xCoord--) {
			potentialCoords.add(new Point(xCoord, y));
			if (multipleSquares == false) {
				break;
			}
			if (!board.isCellEmpty(xCoord, y)) {
				if (getDangerous == true) {
					break;
				}
			}

		}
		return potentialCoords;
	}

	public static ArrayList<Point> getPotentialHorizontalRightMoves(int x, int y, boolean multipleSquares,
			boolean getDangerous, Board board) {

		ArrayList<Point> potentialCoords = new ArrayList<Point>();

		for (int xCoord = x + 1; xCoord < 8; xCoord++) {
			potentialCoords.add(new Point(xCoord, y));
			if (multipleSquares == false) {
				break;
			}
			if (!board.isCellEmpty(xCoord, y)) {
				if (getDangerous == true) {
					break;
				}
			}
		}

		return potentialCoords;
	}

	public static ArrayList<Point> getPotentialVerticalUpMoves(int x, int y, boolean multipleSquares,
			boolean getDangerous, Board board) {

		ArrayList<Point> potentialCoords = new ArrayList<Point>();

		for (int yCoord = y + 1; yCoord < 8; yCoord++) {
			potentialCoords.add(new Point(x, yCoord));
			if (multipleSquares == false) {
				break;
			}
			if (!board.isCellEmpty(x, yCoord)) {
				if (getDangerous == true) {
					break;
				}
			}
		}
		return potentialCoords;
	}

	public static ArrayList<Point> getPotentialVerticalDownMoves(int x, int y, boolean multipleSquares,
			boolean getDangerous, Board board) {

		ArrayList<Point> potentialCoords = new ArrayList<Point>();

		for (int yCoord = y - 1; yCoord > -1; yCoord--) {
			potentialCoords.add(new Point(x, yCoord));
			if (multipleSquares == false) {
				break;
			}
			if (!board.isCellEmpty(x, yCoord)) {
				if (getDangerous == true) {
					break;
				}
			}
		}
		return potentialCoords;
	}

	public static ArrayList<Point> getPotentialVerticleAndHorizontalMoves(int x, int y, boolean multipleSquares,
			boolean getDangerous, Board board) {

		ArrayList<Point> potentialCoords = new ArrayList<Point>();

		potentialCoords.addAll(getPotentialHorizontalLeftMoves(x, y, multipleSquares, getDangerous, board));
		potentialCoords.addAll(getPotentialHorizontalRightMoves(x, y, multipleSquares, getDangerous, board));
		potentialCoords.addAll(getPotentialVerticalUpMoves(x, y, multipleSquares, getDangerous, board));
		potentialCoords.addAll(getPotentialVerticalDownMoves(x, y, multipleSquares, getDangerous, board));

		return potentialCoords;
	}

	public static ArrayList<Point> getPotentialDiagonalLeftMoves(int x, int y, boolean multipleSquares,
			boolean getDangerous, Board board) {

		ArrayList<Point> potentialCoords = new ArrayList<Point>();
		continueChecking = true;

		int xCoord = x - 1;
		int yCoord = y + 1;
		while (continueChecking) {

			if (xCoord > -1 && yCoord < 8) {
				potentialCoords.add(new Point(xCoord, yCoord));

				if (multipleSquares == false) {
					break;
				}
				if (!board.isCellEmpty(xCoord, yCoord)) {
					if (getDangerous == true) {
						break;
					}
				}
				xCoord--;
				yCoord++;
			} else {
				break;
			}
		}
		return potentialCoords;
	}

	public static ArrayList<Point> getPotentialDiagonalRightMoves(int x, int y, boolean multipleSquares,
			boolean getDangerous, Board board) {

		ArrayList<Point> potentialCoords = new ArrayList<Point>();
		continueChecking = true;

		int xCoord = x + 1;
		int yCoord = y - 1;
		while (continueChecking) {

			if (xCoord < 8 && yCoord > -1) {
				potentialCoords.add(new Point(xCoord, yCoord));

				if (multipleSquares == false) {
					break;
				}

				if (!board.isCellEmpty(xCoord, yCoord)) {
					if (getDangerous == true) {
						break;
					}
				}
				xCoord++;
				yCoord--;
			} else {
				break;
			}

		}
		return potentialCoords;
	}

	public static ArrayList<Point> getPotentialAntiDiagonalLeftMoves(int x, int y, boolean multipleSquares,
			boolean getDangerous, Board board) {

		ArrayList<Point> potentialCoords = new ArrayList<Point>();
		continueChecking = true;

		int xCoord = x - 1;
		int yCoord = y - 1;
		while (continueChecking) {

			if (xCoord > -1 & yCoord > -1) {
				potentialCoords.add(new Point(xCoord, yCoord));

				if (multipleSquares == false) {
					break;
				}
				if (!board.isCellEmpty(xCoord, yCoord)) {
					if (getDangerous == true) {
						break;
					}
				}
				xCoord--;
				yCoord--;
			} else {
				break;
			}

		}

		return potentialCoords;
	}

	public static ArrayList<Point> getPotentialAntiDiagonalRightMoves(int x, int y, boolean multipleSquares,
			boolean getDangerous, Board board) {

		ArrayList<Point> potentialCoords = new ArrayList<Point>();
		continueChecking = true;

		int xCoord = x + 1;
		int yCoord = y + 1;
		while (continueChecking) {
			if (xCoord < 8 && yCoord < 8) {
				potentialCoords.add(new Point(xCoord, yCoord));

				if (multipleSquares == false) {
					break;
				}
				System.out.println();
				if (!board.isCellEmpty(xCoord, yCoord)) {
					if (getDangerous == true) {
						break;
					}
				}
				xCoord++;
				yCoord++;
			} else {
				break;
			}

		}

		return potentialCoords;
	}

	public static ArrayList<Point> getPotentialDiagonalMoves(int x, int y, boolean multipleSquares,
			boolean getDangerous, Board board) {

		ArrayList<Point> potentialCoords = new ArrayList<Point>();

		potentialCoords.addAll(getPotentialDiagonalLeftMoves(x, y, multipleSquares, getDangerous, board));
		potentialCoords.addAll(getPotentialDiagonalRightMoves(x, y, multipleSquares, getDangerous, board));
		potentialCoords.addAll(getPotentialAntiDiagonalLeftMoves(x, y, multipleSquares, getDangerous, board));
		potentialCoords.addAll(getPotentialAntiDiagonalRightMoves(x, y, multipleSquares, getDangerous, board));
		return potentialCoords;
	}

	/*
	 * Allowable coordinates are similar to potential dangerous coordinates, but
	 * stop when another piece is reached. If the piece reached is their opponent's,
	 * the coordinate is allowable. However, it does NOT take into account making
	 * your own king vulnerable, which is checked later on
	 */

	public static ArrayList<Point> getAllowableHorizontalLeftMoves(int x, int y, int player, boolean multipleSquares,
			Board board) {

		Point currentCoords = new Point();
		ArrayList<Point> allowableCoords = new ArrayList<Point>();
		continueChecking = true;

		// starting by testing the left side of the current cell

		for (int xCoord = x - 1; xCoord > -1; xCoord--) {

			if (continueChecking) {
				currentCoords = checkIfCellAllowable(xCoord, y, player, board);
				if (currentCoords != null) {

					// piece is allowed to move into current cell
					allowableCoords.add(currentCoords);
				}

			} else {

				// piece cannot move into current cell

				break;
			}

			if (!multipleSquares) {
				continueChecking = false;
				break;
			}
		}

		return allowableCoords;
	}

	public static ArrayList<Point> getAllowableHorizontalRightMoves(int x, int y, int player, boolean multipleSquares,
			Board board) {

		Point currentCoords = new Point();
		ArrayList<Point> allowableCoords = new ArrayList<Point>();
		continueChecking = true;

		for (int xCoord = x + 1; xCoord < 8; xCoord++) {

			if (continueChecking) {
				currentCoords = checkIfCellAllowable(xCoord, y, player, board);
				if (currentCoords != null) {

					// piece is allowed to move into current cell
					allowableCoords.add(currentCoords);
				}
			} else {
				break;
			}

			if (!multipleSquares) {
				continueChecking = false;
				break;
			}
		}
		return allowableCoords;
	}

	public static ArrayList<Point> getAllowableVerticalUpMoves(int x, int y, int player, boolean multipleSquares,
			Board board) {

		Point currentCoords = new Point();
		ArrayList<Point> allowableCoords = new ArrayList<Point>();
		continueChecking = true;

		for (int yCoord = y + 1; yCoord < 8; yCoord++) {

			if (continueChecking) {
				currentCoords = checkIfCellAllowable(x, yCoord, player, board);
				if (currentCoords != null) {

					// piece is allowed to move into current cell
					allowableCoords.add(currentCoords);
				}
			} else {
				break;
			}

			if (!multipleSquares) {
				continueChecking = false;
				break;
			}
		}

		return allowableCoords;

	}

	public static ArrayList<Point> getAllowableVerticalDownMoves(int x, int y, int player, boolean multipleSquares,
			Board board) {

		Point currentCoords = new Point();
		ArrayList<Point> allowableCoords = new ArrayList<Point>();
		continueChecking = true;

		for (int yCoord = y - 1; yCoord > -1; yCoord--) {

			if (continueChecking) {
				currentCoords = checkIfCellAllowable(x, yCoord, player, board);
				if (currentCoords != null) {

					// piece is allowed to move into current cell
					allowableCoords.add(currentCoords);
				}
			} else {
				break;
			}

			if (!multipleSquares) {
				continueChecking = false;
				break;
			}
		}

		return allowableCoords;

	}

	public static ArrayList<Point> getAllowableVerticalAndHorizontalMoves(int x, int y, int player,
			boolean multipleSquares, Board board) {

		ArrayList<Point> allowableCoords = new ArrayList<Point>();

		allowableCoords.addAll(getAllowableHorizontalLeftMoves(x, y, player, multipleSquares, board));
		allowableCoords.addAll(getAllowableHorizontalRightMoves(x, y, player, multipleSquares, board));
		allowableCoords.addAll(getAllowableVerticalUpMoves(x, y, player, multipleSquares, board));
		allowableCoords.addAll(getAllowableVerticalDownMoves(x, y, player, multipleSquares, board));
		return allowableCoords;

	}

	public static ArrayList<Point> getAllowableDiagonalLeftMoves(int x, int y, int player, boolean multipleSquares,
			Board board) {

		Point currentCoords = new Point();
		ArrayList<Point> allowableCoords = new ArrayList<Point>();
		continueChecking = true;

		int xCoord = x - 1;
		int yCoord = y + 1;
		while (continueChecking) {

			if (xCoord > -1) {
				if (yCoord < 8) {
					currentCoords = checkIfCellAllowable(xCoord, yCoord, player, board);
					if (currentCoords != null) {
						allowableCoords.add(currentCoords);
						xCoord--;
						yCoord++;
					}
				} else {
					continueChecking = false;
					break;
				}
			} else {
				continueChecking = false;
				break;
			}

			if (!multipleSquares) {
				continueChecking = false;
			}
		}

		return allowableCoords;

	}

	public static ArrayList<Point> getAllowableDiagonalRightMoves(int x, int y, int player, boolean multipleSquares,
			Board board) {

		Point currentCoords = new Point();
		ArrayList<Point> allowableCoords = new ArrayList<Point>();
		continueChecking = true;

		int xCoord = x + 1;
		int yCoord = y - 1;
		while (continueChecking) {

			if (xCoord < 8) {
				if (yCoord > -1) {
					currentCoords = checkIfCellAllowable(xCoord, yCoord, player, board);
					if (currentCoords != null) {
						allowableCoords.add(currentCoords);
						xCoord++;
						yCoord--;
					}
				} else {
					continueChecking = false;
					break;
				}
			} else {
				continueChecking = false;
				break;
			}

			if (!multipleSquares) {
				continueChecking = false;
			}
		}

		return allowableCoords;
	}

	public static ArrayList<Point> getAllowableAntiDiagonalLeftMoves(int x, int y, int player, boolean multipleSquares,
			Board board) {

		Point currentCoords = new Point();
		ArrayList<Point> allowableCoords = new ArrayList<Point>();
		continueChecking = true;

		int xCoord = x - 1;
		int yCoord = y - 1;
		while (continueChecking) {

			if (xCoord > -1) {
				if (yCoord > -1) {
					currentCoords = checkIfCellAllowable(xCoord, yCoord, player, board);
					if (currentCoords != null) {
						allowableCoords.add(currentCoords);
						xCoord--;
						yCoord--;
					}
				} else {
					continueChecking = false;
					break;
				}
			} else {
				continueChecking = false;
				break;
			}

			if (!multipleSquares) {
				continueChecking = false;
			}
		}

		return allowableCoords;
	}

	public static ArrayList<Point> getAllowableAntiDiagonalRightMoves(int x, int y, int player, boolean multipleSquares,
			Board board) {

		Point currentCoords = new Point();
		ArrayList<Point> allowableCoords = new ArrayList<Point>();
		continueChecking = true;

		int xCoord = x + 1;
		int yCoord = y + 1;
		while (continueChecking) {

			if (xCoord < 8) {
				if (yCoord < 8) {
					currentCoords = checkIfCellAllowable(xCoord, yCoord, player, board);
					if (currentCoords != null) {
						allowableCoords.add(currentCoords);
						xCoord++;
						yCoord++;

					}
				} else {
					continueChecking = false;
					break;
				}
			} else {
				continueChecking = false;
				break;
			}

			if (!multipleSquares) {
				continueChecking = false;
			}
		}

		return allowableCoords;

	}

	public static ArrayList<Point> getAllowableDiagonalMoves(int x, int y, int player, boolean multipleSquares,
			Board board) {

		ArrayList<Point> allowableCoords = new ArrayList<Point>();

		allowableCoords.addAll(getAllowableDiagonalLeftMoves(x, y, player, multipleSquares, board));
		allowableCoords.addAll(getAllowableDiagonalRightMoves(x, y, player, multipleSquares, board));
		allowableCoords.addAll(getAllowableAntiDiagonalLeftMoves(x, y, player, multipleSquares, board));
		allowableCoords.addAll(getAllowableAntiDiagonalRightMoves(x, y, player, multipleSquares, board));
		return allowableCoords;

	}

	// this method returns all allowable moves that a knight can make

	public static ArrayList<Point> getAllowableKnightMoves(ArrayList<Point> potentialMoves, int player, Board board) {

		Point currentCoords = new Point();
		ArrayList<Point> allowableCoords = new ArrayList<Point>();

		for (int i = 0; i < potentialMoves.size(); i++) {
			int xCoord = potentialMoves.get(i).x;
			int yCoord = potentialMoves.get(i).y;

			if (xCoord > -1 && xCoord < 8 && yCoord > -1 && yCoord < 8) {
				currentCoords = checkIfCellAllowable(xCoord, yCoord, player, board);
				if (currentCoords != null) {
					allowableCoords.add(currentCoords);

				}
			}
		}
		return allowableCoords;
	}

	/*
	 * this method returns all allowable moves that a pawn can make. However, it
	 * does NOT take into account making your own king vulnerable, which is checked
	 * later on
	 */

	public static ArrayList<Point> getAllowablePawnMoves(int x, int y, ArrayList<Point> potentialMoves, int player,
			boolean hasMoved, String direction, Board board) {

		Point currentCoords = new Point();
		ArrayList<Point> allowableCoords = new ArrayList<Point>();

		for (int i = 0; i < potentialMoves.size(); i++) {
			int xCoord = potentialMoves.get(i).x;
			int yCoord = potentialMoves.get(i).y;

			if (xCoord > -1 && xCoord < 8 && yCoord > -1 && yCoord < 8) {

				currentCoords = checkIfCellAllowable(xCoord, yCoord, player, board);

				if (currentCoords != null) {

					// checking pawn move of 1 square forwards

					if ((yCoord == y + 1 && xCoord == x) || (yCoord == y - 1 && xCoord == x)) {
						if (!opponentPiece) {
							allowableCoords.add(currentCoords);
						}
					}

					// checking pawn move of 2 squares forward

					if ((yCoord == y + 2 && xCoord == x) || (yCoord == y - 2 && xCoord == x)) {
						if (hasMoved == false) {
							if (!opponentPiece) {
								// making sure the move is not obstructed
								switch (direction) {
								case "up":
									if (board.isCellEmpty(x, y + 1)) {
										allowableCoords.add(currentCoords);
									}
									break;
								case "down":
									if (board.isCellEmpty(x, y - 1)) {
										allowableCoords.add(currentCoords);
									}
									break;
								}
							}
						}
					}

					// checking pawn move diagonally (taking piece)

					if ((xCoord == x + 1 && yCoord == y + 1) || (xCoord == x - 1 && yCoord == y + 1)
							|| (xCoord == x + 1 && yCoord == y - 1) || (xCoord == x - 1 && yCoord == y - 1)) {
						if (opponentPiece) {
							allowableCoords.add(currentCoords);
						}
					}

				}
			}

		}

		return allowableCoords;
	}

	/*
	 * this method is used in the 'getAllowable' methods and checks if a piece is
	 * allowed to move into a target cell and returns coordinates of the target cell
	 * if it is allowed. It also alters a global variable to stop the 'getAllowable'
	 * methods from continuing checking that line due to having hit a piece
	 */
	public static Point checkIfCellAllowable(int x, int y, int player, Board board) {

		Point currentCoords = new Point();
		opponentPiece = false;

		if (board.isCellEmpty(x, y)) {

			// tile is empty
			currentCoords.x = x;
			currentCoords.y = y;
			return currentCoords;

		} else {

			// tile is not empty
			int targetCellPlayer = board.getCellPlayer(x, y);

			if (player == targetCellPlayer) {

				// piece in target cell is friendly so move is not permissible

				continueChecking = false;
				return null;

			} else {

				// piece in target cell is opponent's so move is permissible
				opponentPiece = true;
				currentCoords.x = x;
				currentCoords.y = y;
				continueChecking = false;
				return currentCoords;
			}
		}

	}

	// checks if a move will put their own king into check (and remove it from list)

	public static ArrayList<Point> checkMoveForVulnerableCheck(ArrayList<Point> allowableMoves, String piece,
			int player, int startingX, int startingY) {

		ArrayList<Point> coordsToRemove = new ArrayList<Point>();

		for (int i = 0; i < allowableMoves.size(); i++) {

			Board tempBoard = SerializationUtils.clone(Game.getBoard()); // create temporary board to make changes

			int allowableX = allowableMoves.get(i).x;
			int allowableY = allowableMoves.get(i).y;

			ArrayList<Point> allowableCellDangerousCells = tempBoard.getDangerousCells(allowableX, allowableY);
			ArrayList<Point> allowableCellPotentialDangerousCells = tempBoard.getPotentialDangerousCells(allowableX,
					allowableY);

			ArrayList<Point> startingCellDangerousCells = tempBoard.getDangerousCells(startingX, startingY);
			ArrayList<Point> startingCellPotentialDangerousCells = tempBoard.getPotentialDangerousCells(startingX,
					startingY);

			// changing temp board to reflect allowable move

			tempBoard.changeCell(allowableX, allowableY, player, piece, allowableCellPotentialDangerousCells,
					allowableCellDangerousCells);
			tempBoard.removeCell(startingX, startingY, startingCellPotentialDangerousCells, startingCellDangerousCells);

			if (piece.equals("King")) {

				switch (player) {
				case 1:
					tempBoard.changePlayerOneKingCoords(allowableX, allowableY);
					break;

				case 2:
					tempBoard.changePlayerTwoKingCoords(allowableX, allowableY);
					break;
				}

			}

			Point kingCoords = tempBoard.getKingCoords(player);
			System.out.println("Temp board player " + player + " King coords: " + kingCoords);
				

			ArrayList<Point> kingPotentialDangerousCells = tempBoard.getPotentialDangerousCells(kingCoords.x,
					kingCoords.y);

			// going through each of the king's potentially dangerous cells

			for (int j = 0; j < kingPotentialDangerousCells.size(); j++) {

				int kingPotentialDangerousX = kingPotentialDangerousCells.get(j).x;
				int kingPotentialDangerousY = kingPotentialDangerousCells.get(j).y;

				String kingPotentialDangerousPiece = tempBoard.getCellPiece(kingPotentialDangerousX,
						kingPotentialDangerousY);
				int kingPotentialDangerousPlayer = tempBoard.getCellPlayer(kingPotentialDangerousX,
						kingPotentialDangerousY);

				ArrayList<Point> kingPotentialDangerousPieceAllowableMoves = new ArrayList<Point>();

				// retrieving allowable moves from the king's potentially dangerous cell

				if (kingPotentialDangerousPiece != null) {

					kingPotentialDangerousPieceAllowableMoves = getAllowableMoves(kingPotentialDangerousX,
							kingPotentialDangerousY, kingPotentialDangerousPlayer, kingPotentialDangerousPiece, false, tempBoard);

					// checking if the allowable moves from the king's potentially dangerous cell
					// includes the player's king

					for (int k = 0; k < kingPotentialDangerousPieceAllowableMoves.size(); k++) {

						System.out.println(kingPotentialDangerousPieceAllowableMoves.get(k) + " vs " + kingCoords);

						if (kingPotentialDangerousPieceAllowableMoves.get(k).equals(kingCoords)
								&& kingPotentialDangerousPlayer != player) {

							coordsToRemove.add(new Point(allowableX, allowableY));
							break;

						}
					}

				}

			}

		}

		for (int l = 0; l < coordsToRemove.size(); l++) {
			allowableMoves.remove(new Point(coordsToRemove.get(l).x, coordsToRemove.get(l).y));
		}

		return allowableMoves;
	}

	// checks if a player is in checkmate (used only after check is confirmed)

	public static boolean isCheckmate(Point kingCoords, Point dangerousPieceCoords, int playerInCheck, Board board) {

		boolean checkmate = true;

		// checking if the threatened king can make any moves

		King king = new King(kingCoords.x, kingCoords.y, playerInCheck);

		ArrayList<Point> kingAllowableMoves = king.getPossibleMoves(true, board);
		System.out.println(!kingAllowableMoves.isEmpty());

		if (!kingAllowableMoves.isEmpty()) {
			checkmate = false;
			return false;
		}

		// checking if any pieces can take the dangerous piece

		int dangerousX = dangerousPieceCoords.x;
		int dangerousY = dangerousPieceCoords.y;

		ArrayList<Point> dangerousPieceDangerousCoords = board.getDangerousCells(dangerousX, dangerousY);

		for (int i = 0; i < dangerousPieceDangerousCoords.size(); i++) {

			int dangerousPieceDangerousX = dangerousPieceDangerousCoords.get(i).x;
			int dangerousPieceDangerousY = dangerousPieceDangerousCoords.get(i).y;
			int dangerousPieceDangerousPlayer = board.getCellPlayer(dangerousPieceDangerousX, dangerousPieceDangerousY);
			String dangerousPieceDangerousPiece = board.getCellPiece(dangerousPieceDangerousX,
					dangerousPieceDangerousY);

			if (dangerousPieceDangerousPlayer == playerInCheck) {

				ArrayList<Point> dangerousPieceDangerousPieceAllowableMoves = new ArrayList<Point>();

				dangerousPieceDangerousPieceAllowableMoves = getAllowableMoves(dangerousPieceDangerousX,
						dangerousPieceDangerousY, dangerousPieceDangerousPlayer, dangerousPieceDangerousPiece, true, board);

				for (int j = 0; j < dangerousPieceDangerousPieceAllowableMoves.size(); j++) {
					if (dangerousPieceDangerousPieceAllowableMoves.get(j).x == dangerousX
							&& dangerousPieceDangerousPieceAllowableMoves.get(j).y == dangerousY) {

						// dangerous piece's dangerous piece can take the dangerous piece
						checkmate = false;
						return false;
					}

				}

			}
		}

		// checking if any pieces can move in the way of the dangerous piece

		String dangerousPiece = board.getCellPiece(dangerousX, dangerousY);
		ArrayList<Point> coordsToCheck = new ArrayList<Point>();
		ArrayList<Point> dangerousPieceAllowableCoordsFromLine = new ArrayList<Point>();

		// start by finding out the correct line that leads to the king (only applicable
		// for queens, bishops and castles

		switch (dangerousPiece) {
		case "Queen":
			Queen queen = new Queen(dangerousX, dangerousY, playerInCheck);

			coordsToCheck = queen.getPotentialDiagonalLeftMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.x, kingCoords.y)) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}

			coordsToCheck = queen.getPotentialDiagonalRightMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.x, kingCoords.y)) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}

			coordsToCheck = queen.getPotentialAntiDiagonalLeftMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.x, kingCoords.y)) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}

			coordsToCheck = queen.getPotentialAntiDiagonalRightMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.x, kingCoords.y)) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}

			coordsToCheck = queen.getPotentialHorizontalLeftMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.x, kingCoords.y)) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}

			coordsToCheck = queen.getPotentialHorizontalRightMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.x, kingCoords.y)) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}

			coordsToCheck = queen.getPotentialVerticalUpMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.x, kingCoords.y)) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}

			coordsToCheck = queen.getPotentialVerticalDownMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.x, kingCoords.y)) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}
			break;

		case "Bishop":
			Bishop bishop = new Bishop(dangerousX, dangerousY, playerInCheck);

			coordsToCheck = bishop.getPotentialDiagonalLeftMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.x, kingCoords.y)) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}

			coordsToCheck = bishop.getPotentialDiagonalRightMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.x, kingCoords.y)) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}

			coordsToCheck = bishop.getPotentialAntiDiagonalLeftMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.x, kingCoords.y)) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}

			coordsToCheck = bishop.getPotentialAntiDiagonalRightMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.x, kingCoords.y)) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}
			break;

		case "Castle":
			Castle castle = new Castle(dangerousX, dangerousY, playerInCheck);

			coordsToCheck = castle.getPotentialHorizontalLeftMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.x, kingCoords.y)) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}

			coordsToCheck = castle.getPotentialHorizontalRightMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.x, kingCoords.y)) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}

			coordsToCheck = castle.getPotentialVerticalUpMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.x, kingCoords.y)) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}

			coordsToCheck = castle.getPotentialVerticalDownMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.x, kingCoords.y)) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}
			break;
		}

		dangerousPieceAllowableCoordsFromLine.remove(kingCoords);
		for (int k = 0; k < dangerousPieceAllowableCoordsFromLine.size(); k++) {

			int dangerousPieceAllowableX = dangerousPieceAllowableCoordsFromLine.get(k).x;
			int dangerousPieceAllowableY = dangerousPieceAllowableCoordsFromLine.get(k).y;

			// get the pieces that can potentially get in the way and check if they are able
			// to

			ArrayList<Point> dangerousPieceAllowableCoordPotentialDangerousCoords = board
					.getPotentialDangerousCells(dangerousPieceAllowableX, dangerousPieceAllowableY);
			for (int l = 0; l < dangerousPieceAllowableCoordPotentialDangerousCoords.size(); l++) {

				int dangerousPieceAllowableCoordPotentialDangerousX = dangerousPieceAllowableCoordPotentialDangerousCoords
						.get(l).x;
				int dangerousPieceAllowableCoordPotentialDangerousY = dangerousPieceAllowableCoordPotentialDangerousCoords
						.get(l).y;
				int dangerousPieceAllowableCoordPotentialDangerousPiecePlayer = board.getCellPlayer(
						dangerousPieceAllowableCoordPotentialDangerousX,
						dangerousPieceAllowableCoordPotentialDangerousY);
				String dangerousPieceAllowableCoordPotentialDangerousPiece = board.getCellPiece(
						dangerousPieceAllowableCoordPotentialDangerousX,
						dangerousPieceAllowableCoordPotentialDangerousY);

				ArrayList<Point> dangerousPieceAllowableCoordPotentialDangerousPieceAllowableCoords = null;

				if (dangerousPieceAllowableCoordPotentialDangerousPiece != null) {

					if (dangerousPieceAllowableCoordPotentialDangerousPiecePlayer == playerInCheck) {

						dangerousPieceAllowableCoordPotentialDangerousPieceAllowableCoords = getAllowableMoves(
								dangerousPieceAllowableCoordPotentialDangerousX,
								dangerousPieceAllowableCoordPotentialDangerousY,
								dangerousPieceAllowableCoordPotentialDangerousPiecePlayer,
								dangerousPieceAllowableCoordPotentialDangerousPiece, true, board);

						for (int m = 0; m < dangerousPieceAllowableCoordPotentialDangerousPieceAllowableCoords
								.size(); m++) {
							if (checkForCoords(dangerousPieceAllowableCoordPotentialDangerousPieceAllowableCoords,
									dangerousPieceAllowableX, dangerousPieceAllowableY)) {
								if (dangerousPieceAllowableCoordPotentialDangerousPiecePlayer == playerInCheck) {
									checkmate = false;
									return false;

								}

							}
						}
					}
				}
			}

		}

		return checkmate;
	}

	// checks for a coordinate within an ArrayList of coordinates

	public static boolean checkForCoords(ArrayList<Point> coords, int x, int y) {
		boolean coordsMatch = false;
		for (int i = 0; i < coords.size(); i++) {
			int potentialX = coords.get(i).x;
			int potentialY = coords.get(i).y;

			if (potentialX == x && potentialY == y) {
				// line found that includes king
				coordsMatch = true;
				break;
			} else
				coordsMatch = false;

		}
		return coordsMatch;
	}

	public static ArrayList<Point> getAllowableMoves(int x, int y, int player, String piece, boolean checkForKingCheck, Board board) {

		ArrayList<Point> allowableMoves = new ArrayList<Point>();

		switch (piece) {

		case "Castle":
			Castle castle = new Castle(x, y, player);
			allowableMoves = castle.getPossibleMoves(checkForKingCheck, board);
			break;

		case "Bishop":
			Bishop bishop = new Bishop(x, y, player);
			allowableMoves = bishop.getPossibleMoves(checkForKingCheck, board);
			break;

		case "Queen":
			Queen queen = new Queen(x, y, player);
			allowableMoves = queen.getPossibleMoves(checkForKingCheck, board);
			break;

		case "Pawn":
			Pawn pawn = new Pawn(x, y, player);
			allowableMoves = pawn.getPossibleMoves(checkForKingCheck, board);
			break;

		case "Knight":
			Knight knight = new Knight(x, y, player);
			allowableMoves = knight.getPossibleMoves(checkForKingCheck, board);
			break;

		case "King":
			King king = new King(x, y, player);
			allowableMoves = king.getPossibleMoves(checkForKingCheck, board);
			break;
		}

		return allowableMoves;

	}

}
