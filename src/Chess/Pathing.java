package Chess;

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

	public static ArrayList<ArrayList<Integer>> getPotentialHorizontalLeftMoves(int x, int y, boolean multipleSquares,
			boolean getDangerous, Board board) {

		ArrayList<ArrayList<Integer>> potentialCoords = new ArrayList<ArrayList<Integer>>();

		for (int xCoord = x - 1; xCoord > -1; xCoord--) {
			ArrayList<Integer> currentCoords = new ArrayList<Integer>();
			currentCoords.add(xCoord);
			currentCoords.add(y);
			potentialCoords.add(currentCoords);
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

	public static ArrayList<ArrayList<Integer>> getPotentialHorizontalRightMoves(int x, int y, boolean multipleSquares,
			boolean getDangerous, Board board) {

		ArrayList<ArrayList<Integer>> potentialCoords = new ArrayList<ArrayList<Integer>>();

		for (int xCoord = x + 1; xCoord < 8; xCoord++) {
			ArrayList<Integer> currentCoords = new ArrayList<Integer>();
			currentCoords.add(xCoord);
			currentCoords.add(y);
			potentialCoords.add(currentCoords);
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

	public static ArrayList<ArrayList<Integer>> getPotentialVerticalUpMoves(int x, int y, boolean multipleSquares,
			boolean getDangerous, Board board) {

		ArrayList<ArrayList<Integer>> potentialCoords = new ArrayList<ArrayList<Integer>>();

		for (int yCoord = y + 1; yCoord < 8; yCoord++) {
			ArrayList<Integer> currentCoords = new ArrayList<Integer>();
			currentCoords.add(x);
			currentCoords.add(yCoord);
			potentialCoords.add(currentCoords);
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

	public static ArrayList<ArrayList<Integer>> getPotentialVerticalDownMoves(int x, int y, boolean multipleSquares,
			boolean getDangerous, Board board) {

		ArrayList<ArrayList<Integer>> potentialCoords = new ArrayList<ArrayList<Integer>>();

		for (int yCoord = y - 1; yCoord > -1; yCoord--) {
			ArrayList<Integer> currentCoords = new ArrayList<Integer>();
			currentCoords.add(x);
			currentCoords.add(yCoord);
			potentialCoords.add(currentCoords);
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

	public static ArrayList<ArrayList<Integer>> getPotentialVerticleAndHorizontalMoves(int x, int y,
			boolean multipleSquares, boolean getDangerous, Board board) {

		ArrayList<ArrayList<Integer>> potentialCoords = new ArrayList<ArrayList<Integer>>();

		potentialCoords.addAll(getPotentialHorizontalLeftMoves(x, y, multipleSquares, getDangerous, board));
		potentialCoords.addAll(getPotentialHorizontalRightMoves(x, y, multipleSquares, getDangerous, board));
		potentialCoords.addAll(getPotentialVerticalUpMoves(x, y, multipleSquares, getDangerous, board));
		potentialCoords.addAll(getPotentialVerticalDownMoves(x, y, multipleSquares, getDangerous, board));

		return potentialCoords;
	}

	public static ArrayList<ArrayList<Integer>> getPotentialDiagonalLeftMoves(int x, int y, boolean multipleSquares,
			boolean getDangerous, Board board) {

		ArrayList<ArrayList<Integer>> potentialCoords = new ArrayList<ArrayList<Integer>>();
		continueChecking = true;

		int xCoord = x - 1;
		int yCoord = y + 1;
		while (continueChecking) {
			ArrayList<Integer> currentCoords = new ArrayList<Integer>();

			if (xCoord > -1 && yCoord < 8) {
				currentCoords.add(xCoord);
				currentCoords.add(yCoord);
				potentialCoords.add(currentCoords);

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

	public static ArrayList<ArrayList<Integer>> getPotentialDiagonalRightMoves(int x, int y, boolean multipleSquares,
			boolean getDangerous, Board board) {

		ArrayList<ArrayList<Integer>> potentialCoords = new ArrayList<ArrayList<Integer>>();
		continueChecking = true;

		int xCoord = x + 1;
		int yCoord = y - 1;
		while (continueChecking) {

			ArrayList<Integer> currentCoords = new ArrayList<Integer>();

			if (xCoord < 8 && yCoord > -1) {
				currentCoords.add(xCoord);
				currentCoords.add(yCoord);
				potentialCoords.add(currentCoords);

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

	public static ArrayList<ArrayList<Integer>> getPotentialAntiDiagonalLeftMoves(int x, int y, boolean multipleSquares,
			boolean getDangerous, Board board) {

		ArrayList<ArrayList<Integer>> potentialCoords = new ArrayList<ArrayList<Integer>>();
		continueChecking = true;

		int xCoord = x - 1;
		int yCoord = y - 1;
		while (continueChecking) {

			ArrayList<Integer> currentCoords = new ArrayList<Integer>();

			if (xCoord > -1 & yCoord > -1) {
				currentCoords.add(xCoord);
				currentCoords.add(yCoord);
				potentialCoords.add(currentCoords);

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

	public static ArrayList<ArrayList<Integer>> getPotentialAntiDiagonalRightMoves(int x, int y,
			boolean multipleSquares, boolean getDangerous, Board board) {

		ArrayList<ArrayList<Integer>> potentialCoords = new ArrayList<ArrayList<Integer>>();
		continueChecking = true;

		int xCoord = x + 1;
		int yCoord = y + 1;
		while (continueChecking) {
			ArrayList<Integer> currentCoords = new ArrayList<Integer>();
			if (xCoord < 8 && yCoord < 8) {
				currentCoords.add(xCoord);
				currentCoords.add(yCoord);
				potentialCoords.add(currentCoords);

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

	public static ArrayList<ArrayList<Integer>> getPotentialDiagonalMoves(int x, int y, boolean multipleSquares,
			boolean getDangerous, Board board) {

		ArrayList<ArrayList<Integer>> potentialCoords = new ArrayList<ArrayList<Integer>>();

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

	public static ArrayList<ArrayList<Integer>> getAllowableHorizontalLeftMoves(int x, int y, int player,
			boolean multipleSquares, Board board) {

		ArrayList<Integer> currentCoords = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> allowableCoords = new ArrayList<ArrayList<Integer>>();
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

	public static ArrayList<ArrayList<Integer>> getAllowableHorizontalRightMoves(int x, int y, int player,
			boolean multipleSquares, Board board) {

		ArrayList<Integer> currentCoords = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> allowableCoords = new ArrayList<ArrayList<Integer>>();
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

	public static ArrayList<ArrayList<Integer>> getAllowableVerticalUpMoves(int x, int y, int player,
			boolean multipleSquares, Board board) {

		ArrayList<Integer> currentCoords = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> allowableCoords = new ArrayList<ArrayList<Integer>>();
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

	public static ArrayList<ArrayList<Integer>> getAllowableVerticalDownMoves(int x, int y, int player,
			boolean multipleSquares, Board board) {

		ArrayList<Integer> currentCoords = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> allowableCoords = new ArrayList<ArrayList<Integer>>();
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

	public static ArrayList<ArrayList<Integer>> getAllowableVerticalAndHorizontalMoves(int x, int y, int player,
			boolean multipleSquares, Board board) {

		ArrayList<ArrayList<Integer>> allowableCoords = new ArrayList<ArrayList<Integer>>();

		allowableCoords.addAll(getAllowableHorizontalLeftMoves(x, y, player, multipleSquares, board));
		allowableCoords.addAll(getAllowableHorizontalRightMoves(x, y, player, multipleSquares, board));
		allowableCoords.addAll(getAllowableVerticalUpMoves(x, y, player, multipleSquares, board));
		allowableCoords.addAll(getAllowableVerticalDownMoves(x, y, player, multipleSquares, board));
		return allowableCoords;

	}

	public static ArrayList<ArrayList<Integer>> getAllowableDiagonalLeftMoves(int x, int y, int player,
			boolean multipleSquares, Board board) {

		ArrayList<Integer> currentCoords = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> allowableCoords = new ArrayList<ArrayList<Integer>>();
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

	public static ArrayList<ArrayList<Integer>> getAllowableDiagonalRightMoves(int x, int y, int player,
			boolean multipleSquares, Board board) {

		ArrayList<Integer> currentCoords = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> allowableCoords = new ArrayList<ArrayList<Integer>>();
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

	public static ArrayList<ArrayList<Integer>> getAllowableAntiDiagonalLeftMoves(int x, int y, int player,
			boolean multipleSquares, Board board) {

		ArrayList<Integer> currentCoords = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> allowableCoords = new ArrayList<ArrayList<Integer>>();
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

	public static ArrayList<ArrayList<Integer>> getAllowableAntiDiagonalRightMoves(int x, int y, int player,
			boolean multipleSquares, Board board) {

		ArrayList<Integer> currentCoords = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> allowableCoords = new ArrayList<ArrayList<Integer>>();
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

	public static ArrayList<ArrayList<Integer>> getAllowableDiagonalMoves(int x, int y, int player,
			boolean multipleSquares, Board board) {

		ArrayList<ArrayList<Integer>> allowableCoords = new ArrayList<ArrayList<Integer>>();

		allowableCoords.addAll(getAllowableDiagonalLeftMoves(x, y, player, multipleSquares, board));
		allowableCoords.addAll(getAllowableDiagonalRightMoves(x, y, player, multipleSquares, board));
		allowableCoords.addAll(getAllowableAntiDiagonalLeftMoves(x, y, player, multipleSquares, board));
		allowableCoords.addAll(getAllowableAntiDiagonalRightMoves(x, y, player, multipleSquares, board));
		return allowableCoords;

	}

	// this method returns all allowable moves that a knight can make

	public static ArrayList<ArrayList<Integer>> getAllowableKnightMoves(ArrayList<ArrayList<Integer>> potentialMoves,
			int player, Board board) {

		ArrayList<Integer> currentCoords = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> allowableCoords = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < potentialMoves.size(); i++) {
			int xCoord = potentialMoves.get(i).get(0);
			int yCoord = potentialMoves.get(i).get(1);

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

	public static ArrayList<ArrayList<Integer>> getAllowablePawnMoves(int x, int y,
			ArrayList<ArrayList<Integer>> potentialMoves, int player, boolean hasMoved, String direction, Board board) {

		ArrayList<Integer> currentCoords = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> allowableCoords = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < potentialMoves.size(); i++) {
			int xCoord = potentialMoves.get(i).get(0);
			int yCoord = potentialMoves.get(i).get(1);

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
	public static ArrayList<Integer> checkIfCellAllowable(int x, int y, int player, Board board) {

		ArrayList<Integer> currentCoords = new ArrayList<Integer>();
		opponentPiece = false;

		if (board.isCellEmpty(x, y)) {

			// tile is empty
			currentCoords.add(x);
			currentCoords.add(y);
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
				currentCoords.add(x);
				currentCoords.add(y);
				continueChecking = false;
				return currentCoords;
			}
		}

	}

	// checks if a move will put their own king into check (and remove it from list)

	public static ArrayList<ArrayList<Integer>> checkMoveForVulnerableCheck(
			ArrayList<ArrayList<Integer>> allowableMoves, String piece, int player, int startingX, int startingY) {

		ArrayList<ArrayList<Integer>> coordsToRemove = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < allowableMoves.size(); i++) {

			Board tempBoard = SerializationUtils.clone(Game.getBoard()); // create temporary board to make changes

			int allowableX = allowableMoves.get(i).get(0);
			int allowableY = allowableMoves.get(i).get(1);

			ArrayList<ArrayList<Integer>> allowableCellDangerousCells = tempBoard.getDangerousCells(allowableX,
					allowableY);
			ArrayList<ArrayList<Integer>> allowableCellPotentialDangerousCells = tempBoard
					.getPotentialDangerousCells(allowableX, allowableY);

			ArrayList<ArrayList<Integer>> startingCellDangerousCells = tempBoard.getDangerousCells(startingX,
					startingY);
			ArrayList<ArrayList<Integer>> startingCellPotentialDangerousCells = tempBoard
					.getPotentialDangerousCells(startingX, startingY);

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

			ArrayList<Integer> kingCoords = new ArrayList<Integer>();
			switch (player) {
			case 1:
				kingCoords = tempBoard.getPlayerOneKingCoords();
				System.out.println("Temp board player 1 King coords: " + kingCoords);
				break;

			case 2:
				kingCoords = tempBoard.getPlayerTwoKingCoords();
				System.out.println("Temp board player 2 King coords: " + kingCoords);
				break;
			}

			ArrayList<ArrayList<Integer>> kingPotentialDangerousCells = tempBoard
					.getPotentialDangerousCells(kingCoords.get(0), kingCoords.get(1));

			// going through each of the king's potentially dangerous cells

			for (int j = 0; j < kingPotentialDangerousCells.size(); j++) {

				int kingPotentialDangerousX = kingPotentialDangerousCells.get(j).get(0);
				int kingPotentialDangerousY = kingPotentialDangerousCells.get(j).get(1);

				String kingPotentialDangerousPiece = tempBoard.getCellPiece(kingPotentialDangerousX,
						kingPotentialDangerousY);
				int kingPotentialDangerousPlayer = tempBoard.getCellPlayer(kingPotentialDangerousX,
						kingPotentialDangerousY);

				ArrayList<ArrayList<Integer>> kingPotentialDangerousPieceAllowableMoves = new ArrayList<ArrayList<Integer>>();

				// retrieving allowable moves from the king's potentially dangerous cell

				if (kingPotentialDangerousPiece != null) {

					switch (kingPotentialDangerousPiece) {

					case "Castle":
						Castle castle = new Castle(kingPotentialDangerousX, kingPotentialDangerousY,
								kingPotentialDangerousPlayer);
						kingPotentialDangerousPieceAllowableMoves = castle.getPossibleMoves(false, tempBoard);
						break;

					case "Bishop":
						Bishop bishop = new Bishop(kingPotentialDangerousX, kingPotentialDangerousY,
								kingPotentialDangerousPlayer);
						kingPotentialDangerousPieceAllowableMoves = bishop.getPossibleMoves(false, tempBoard);
						break;

					case "Queen":
						Queen queen = new Queen(kingPotentialDangerousX, kingPotentialDangerousY,
								kingPotentialDangerousPlayer);
						kingPotentialDangerousPieceAllowableMoves = queen.getPossibleMoves(false, tempBoard);
						break;

					case "Pawn":
						Pawn pawn = new Pawn(kingPotentialDangerousX, kingPotentialDangerousY,
								kingPotentialDangerousPlayer);
						kingPotentialDangerousPieceAllowableMoves = pawn.getPossibleMoves(false, tempBoard);
						break;

					case "Knight":
						Knight knight = new Knight(kingPotentialDangerousX, kingPotentialDangerousY,
								kingPotentialDangerousPlayer);
						kingPotentialDangerousPieceAllowableMoves = knight.getPossibleMoves(false, tempBoard);
						break;

					case "King":
						King king = new King(kingPotentialDangerousX, kingPotentialDangerousY,
								kingPotentialDangerousPlayer);
						kingPotentialDangerousPieceAllowableMoves = king.getPossibleMoves(false, tempBoard);
						break;
					}

					// checking if the allowable moves from the king's potentially dangerous cell
					// includes the player's king

					for (int k = 0; k < kingPotentialDangerousPieceAllowableMoves.size(); k++) {

						System.out.println(kingPotentialDangerousPieceAllowableMoves.get(k) + " vs " + kingCoords);

						if (kingPotentialDangerousPieceAllowableMoves.get(k).equals(kingCoords)
								&& kingPotentialDangerousPlayer != player) {

							ArrayList<Integer> currentCoords = new ArrayList<Integer>();
							currentCoords.add(allowableX);
							currentCoords.add(allowableY);

							coordsToRemove.add(currentCoords);
							break;

						}
					}

				}

			}

		}

		for (int l = 0; l < coordsToRemove.size(); l++) {
			ArrayList<Integer> coordToRemove = new ArrayList<Integer>();
			coordToRemove.add(coordsToRemove.get(l).get(0));
			coordToRemove.add(coordsToRemove.get(l).get(1));

			allowableMoves.remove(coordToRemove);
		}

		return allowableMoves;
	}

	// checks if a player is in checkmate (used only after check is confirmed)

	public static boolean isCheckmate(ArrayList<Integer> kingCoords, ArrayList<Integer> dangerousPieceCoords,
			int playerInCheck, Board board) {

		boolean checkmate = true;

		// checking if the threatened king can make any moves

		int kingX = kingCoords.get(0);
		int kingY = kingCoords.get(1);

		King king = new King(kingX, kingY, playerInCheck);

		ArrayList<ArrayList<Integer>> kingAllowableMoves = king.getPossibleMoves(true, board);
		System.out.println(!kingAllowableMoves.isEmpty());

		if (!kingAllowableMoves.isEmpty()) {
			checkmate = false;
			return false;
		}

		// checking if any pieces can take the dangerous piece

		int dangerousX = dangerousPieceCoords.get(0);
		int dangerousY = dangerousPieceCoords.get(1);

		ArrayList<ArrayList<Integer>> dangerousPieceDangerousCoords = board.getDangerousCells(dangerousX, dangerousY);

		for (int i = 0; i < dangerousPieceDangerousCoords.size(); i++) {

			int dangerousPieceDangerousX = dangerousPieceDangerousCoords.get(i).get(0);
			int dangerousPieceDangerousY = dangerousPieceDangerousCoords.get(i).get(1);
			int dangerousPieceDangerousPlayer = board.getCellPlayer(dangerousPieceDangerousX, dangerousPieceDangerousY);
			String dangerousPieceDangerousPiece = board.getCellPiece(dangerousPieceDangerousX,
					dangerousPieceDangerousY);

			if (dangerousPieceDangerousPlayer == playerInCheck) {

				ArrayList<ArrayList<Integer>> dangerousPieceDangerousPieceAllowableMoves = new ArrayList<ArrayList<Integer>>();

				switch (dangerousPieceDangerousPiece) {

				case "Castle":
					Castle castle = new Castle(dangerousPieceDangerousX, dangerousPieceDangerousY,
							dangerousPieceDangerousPlayer);
					dangerousPieceDangerousPieceAllowableMoves = castle.getPossibleMoves(true, board);
					break;

				case "Bishop":
					Bishop bishop = new Bishop(dangerousPieceDangerousX, dangerousPieceDangerousY,
							dangerousPieceDangerousPlayer);
					dangerousPieceDangerousPieceAllowableMoves = bishop.getPossibleMoves(true, board);
					break;

				case "Queen":
					Queen queen = new Queen(dangerousPieceDangerousX, dangerousPieceDangerousY,
							dangerousPieceDangerousPlayer);
					dangerousPieceDangerousPieceAllowableMoves = queen.getPossibleMoves(true, board);
					break;

				case "Pawn":
					Pawn pawn = new Pawn(dangerousPieceDangerousX, dangerousPieceDangerousY,
							dangerousPieceDangerousPlayer);
					dangerousPieceDangerousPieceAllowableMoves = pawn.getPossibleMoves(true, board);
					break;

				case "Knight":
					Knight knight = new Knight(dangerousPieceDangerousX, dangerousPieceDangerousY,
							dangerousPieceDangerousPlayer);
					dangerousPieceDangerousPieceAllowableMoves = knight.getPossibleMoves(true, board);
					break;

				case "King":
					king = new King(dangerousPieceDangerousX, dangerousPieceDangerousY, dangerousPieceDangerousPlayer);
					dangerousPieceDangerousPieceAllowableMoves = king.getPossibleMoves(true, board);
					break;
				}

				for (int j = 0; j < dangerousPieceDangerousPieceAllowableMoves.size(); j++) {
					if (dangerousPieceDangerousPieceAllowableMoves.get(j).get(0) == dangerousX
							&& dangerousPieceDangerousPieceAllowableMoves.get(j).get(1) == dangerousY) {

						// dangerous piece's dangerous piece can take the dangerous piece
						checkmate = false;
						return false;
					}

				}

			}
		}

		// checking if any pieces can move in the way of the dangerous piece

		String dangerousPiece = board.getCellPiece(dangerousX, dangerousY);
		ArrayList<ArrayList<Integer>> coordsToCheck = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> dangerousPieceAllowableCoordsFromLine = new ArrayList<ArrayList<Integer>>();

		// start by finding out the correct line that leads to the king (only applicable
		// for queens, bishops and castles

		switch (dangerousPiece) {
		case "Queen":
			Queen queen = new Queen(dangerousX, dangerousY, playerInCheck);

			coordsToCheck = queen.getPotentialDiagonalLeftMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.get(0), kingCoords.get(1))) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}

			coordsToCheck = queen.getPotentialDiagonalRightMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.get(0), kingCoords.get(1))) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}

			coordsToCheck = queen.getPotentialAntiDiagonalLeftMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.get(0), kingCoords.get(1))) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}

			coordsToCheck = queen.getPotentialAntiDiagonalRightMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.get(0), kingCoords.get(1))) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}

			coordsToCheck = queen.getPotentialHorizontalLeftMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.get(0), kingCoords.get(1))) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}

			coordsToCheck = queen.getPotentialHorizontalRightMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.get(0), kingCoords.get(1))) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}

			coordsToCheck = queen.getPotentialVerticalUpMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.get(0), kingCoords.get(1))) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}

			coordsToCheck = queen.getPotentialVerticalDownMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.get(0), kingCoords.get(1))) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}
			break;

		case "Bishop":
			Bishop bishop = new Bishop(dangerousX, dangerousY, playerInCheck);

			coordsToCheck = bishop.getPotentialDiagonalLeftMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.get(0), kingCoords.get(1))) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}

			coordsToCheck = bishop.getPotentialDiagonalRightMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.get(0), kingCoords.get(1))) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}

			coordsToCheck = bishop.getPotentialAntiDiagonalLeftMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.get(0), kingCoords.get(1))) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}

			coordsToCheck = bishop.getPotentialAntiDiagonalRightMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.get(0), kingCoords.get(1))) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}
			break;

		case "Castle":
			Castle castle = new Castle(dangerousX, dangerousY, playerInCheck);

			coordsToCheck = castle.getPotentialHorizontalLeftMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.get(0), kingCoords.get(1))) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}

			coordsToCheck = castle.getPotentialHorizontalRightMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.get(0), kingCoords.get(1))) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}

			coordsToCheck = castle.getPotentialVerticalUpMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.get(0), kingCoords.get(1))) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}

			coordsToCheck = castle.getPotentialVerticalDownMoves(board);
			if (checkForCoords(coordsToCheck, kingCoords.get(0), kingCoords.get(1))) {
				dangerousPieceAllowableCoordsFromLine = coordsToCheck;
				break;
			}
			break;
		}

		dangerousPieceAllowableCoordsFromLine.remove(kingCoords);
		for (int k = 0; k < dangerousPieceAllowableCoordsFromLine.size(); k++) {

			int dangerousPieceAllowableX = dangerousPieceAllowableCoordsFromLine.get(k).get(0);
			int dangerousPieceAllowableY = dangerousPieceAllowableCoordsFromLine.get(k).get(1);

			// get the pieces that can potentially get in the way and check if they are able
			// to

			ArrayList<ArrayList<Integer>> dangerousPieceAllowableCoordPotentialDangerousCoords = board
					.getPotentialDangerousCells(dangerousPieceAllowableX, dangerousPieceAllowableY);
			for (int l = 0; l < dangerousPieceAllowableCoordPotentialDangerousCoords.size(); l++) {

				int dangerousPieceAllowableCoordPotentialDangerousX = dangerousPieceAllowableCoordPotentialDangerousCoords
						.get(l).get(0);
				int dangerousPieceAllowableCoordPotentialDangerousY = dangerousPieceAllowableCoordPotentialDangerousCoords
						.get(l).get(1);
				int dangerousPieceAllowableCoordPotentialDangerousPiecePlayer = board.getCellPlayer(
						dangerousPieceAllowableCoordPotentialDangerousX,
						dangerousPieceAllowableCoordPotentialDangerousY);
				String dangerousPieceAllowableCoordPotentialDangerousPiece = board.getCellPiece(
						dangerousPieceAllowableCoordPotentialDangerousX,
						dangerousPieceAllowableCoordPotentialDangerousY);

				ArrayList<ArrayList<Integer>> dangerousPieceAllowableCoordPotentialDangerousPieceAllowableCoords = null;
				ArrayList<Integer> currentCoords = new ArrayList<Integer>();
				currentCoords.add(dangerousPieceAllowableCoordPotentialDangerousX);
				currentCoords.add(dangerousPieceAllowableCoordPotentialDangerousY);

				if (dangerousPieceAllowableCoordPotentialDangerousPiece != null) {

					if (dangerousPieceAllowableCoordPotentialDangerousPiecePlayer == playerInCheck) {

						switch (dangerousPieceAllowableCoordPotentialDangerousPiece) {

						case "Castle":
							Castle castle = new Castle(dangerousPieceAllowableCoordPotentialDangerousX,
									dangerousPieceAllowableCoordPotentialDangerousY,
									dangerousPieceAllowableCoordPotentialDangerousPiecePlayer);
							dangerousPieceAllowableCoordPotentialDangerousPieceAllowableCoords = castle
									.getPossibleMoves(true, board);
							break;

						case "Bishop":
							Bishop bishop = new Bishop(dangerousPieceAllowableCoordPotentialDangerousX,
									dangerousPieceAllowableCoordPotentialDangerousY,
									dangerousPieceAllowableCoordPotentialDangerousPiecePlayer);
							dangerousPieceAllowableCoordPotentialDangerousPieceAllowableCoords = bishop
									.getPossibleMoves(true, board);
							break;

						case "Queen":
							Queen queen = new Queen(dangerousPieceAllowableCoordPotentialDangerousX,
									dangerousPieceAllowableCoordPotentialDangerousY,
									dangerousPieceAllowableCoordPotentialDangerousPiecePlayer);
							dangerousPieceAllowableCoordPotentialDangerousPieceAllowableCoords = queen
									.getPossibleMoves(true, board);
							break;

						case "Pawn":
							Pawn pawn = new Pawn(dangerousPieceAllowableCoordPotentialDangerousX,
									dangerousPieceAllowableCoordPotentialDangerousY,
									dangerousPieceAllowableCoordPotentialDangerousPiecePlayer);
							dangerousPieceAllowableCoordPotentialDangerousPieceAllowableCoords = pawn
									.getPossibleMoves(true, board);
							break;

						case "Knight":
							Knight knight = new Knight(dangerousPieceAllowableCoordPotentialDangerousX,
									dangerousPieceAllowableCoordPotentialDangerousY,
									dangerousPieceAllowableCoordPotentialDangerousPiecePlayer);
							dangerousPieceAllowableCoordPotentialDangerousPieceAllowableCoords = knight
									.getPossibleMoves(true, board);
							break;

						case "King":
							dangerousPieceAllowableCoordPotentialDangerousPieceAllowableCoords = king
									.getPossibleMoves(true, board);
							break;
						}

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

	public static boolean checkForCoords(ArrayList<ArrayList<Integer>> coords, int x, int y) {
		boolean coordsMatch = false;
		for (int i = 0; i < coords.size(); i++) {
			int potentialX = coords.get(i).get(0);
			int potentialY = coords.get(i).get(1);

			if (potentialX == x && potentialY == y) {
				// line found that includes king
				coordsMatch = true;
				break;
			} else
				coordsMatch = false;

		}
		return coordsMatch;
	}

}
