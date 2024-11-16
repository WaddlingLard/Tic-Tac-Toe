import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

/**
 * CS 121 Project 4: TicTacToeGame
 *
 * This lets you play TicTacToe if you have the GUI and has several methods to test for who wins and see what moves have been played. 
 *
 * @author BSU CS 121 Instructors
 * @author BrianWu
 */
public class TicTacToeGame implements TicTacToe {
	private TicTacToe.GameState gameState;
	private TicTacToe.BoardChoice player;
	private Point[] moves;
	private BoardChoice[][] gameBoard;
	private int pointCounter = 0;

	private final int WIDTH_OF_BOARD = 3;
	private final int HEIGHT_OF_BOARD = 3;

	/**
	 * The default constructor that creates a new gameBoard and moves array via calling the newGame() method.
	 */
	public TicTacToeGame() { 
		// this.gameBoard = new BoardChoice[3][3];
		// this.moves = new Point[9];
		newGame();
	}

	/**
	 * The method that starts a newGame by reseting values to their default state. Also sets the game to IN_PROGRESS.
	 */
	@Override
	public void newGame() {

		// Reinitializing gameboard and moves when new game is pushed
		gameBoard = new BoardChoice[3][3];
		moves = new Point[9];
		pointCounter = 0;
		player = TicTacToe.BoardChoice.OPEN;

		for(int i = 0; i < gameBoard.length; i++) 
			for(int j = 0; j < gameBoard.length; j++) 
				gameBoard[i][j] = BoardChoice.OPEN; 
		gameState = TicTacToe.GameState.IN_PROGRESS;
	}

	/**
	 * This method makes sure that when a move is played it is valid under specific conditions and calls the gameOver() method to see if the last move made was winning or a tie
	 * @param player the person who is currently going (X,O)
	 * @param row the row where the move is being made
	 * @param col the col where the move is being made
	 * @return boolean returns true if the move is valid, false if not
	 */
	@Override
	public boolean choose(TicTacToe.BoardChoice player, int row, int col) { 
		
		if (this.player == player) {
			System.out.println("PLAYER ERROR");
			return false;
		}

		if (gameBoard[row][col] != TicTacToe.BoardChoice.OPEN) {
			System.out.println("Called move on row: " + row + ", and col: " + col);
			System.out.println("GAMEBOARD ERROR");
			return false;
		}
		
		if (!(pointCounter < 9)) {
			System.out.println("POINT COUNTER ERROR");
			return false;
		}

		if (gameOver()) {
			System.out.println("GAME OVER ERROR");
			return false;
		}



		// if(this.player == player || gameBoard[row][col] != TicTacToe.BoardChoice.OPEN || !(pointCounter < 9) || gameOver()) {
		// 	System.out.println("CANNOT OUTPUT MOVE!");
		// 	return false;
		// }else {
		// 	Point p = new Point(row, col);
		// 	moves[pointCounter] = p;
		// 	pointCounter++;
		// 	this.player = player;
		// 	gameBoard[row][col] = player;
		// 	gameOver();
		// 	return true;
		// }

		Point p = new Point(row, col);
		moves[pointCounter] = p;
		pointCounter++;
		this.player = player;
		gameBoard[row][col] = player;
		gameOver();
		return true;
	}

	/**
	 * This method checks the whole board to see whether it is a victory for X, O, or its a tie. 
	 * @return boolean returns true if X,O,TIE exist, false if not
	 */
	@Override
	public boolean gameOver() {

		Point[] diagonals = new Point[6];//Array for Diagonal Points to test
		diagonals[0] = new Point(0,0);
		diagonals[1] = new Point(1,1);
		diagonals[2] = new Point(2,2);
		diagonals[3] = new Point(2,0);
		diagonals[4] = new Point(1,1);
		diagonals[5] = new Point(0,2);
		String pleaseWork = "";
		for(int i = 0; i < gameBoard.length; i++) {//Testing for horizontals
			for(int j = 0; j < gameBoard.length; j++)
				if(gameBoard[i][j] == TicTacToe.BoardChoice.X) {
					pleaseWork += "1";
				}else if(gameBoard[i][j] == TicTacToe.BoardChoice.O) {
					pleaseWork += "2";
				}else {
					pleaseWork += "3";
				}
			pleaseWork += " ";
		}
		for(int i = 0; i < gameBoard.length; i++) {//Testing for verticals
			for(int j = 0; j < gameBoard.length; j++)
				if(gameBoard[j][i] == TicTacToe.BoardChoice.X) {
					pleaseWork += "1";
				}else if(gameBoard[j][i] == TicTacToe.BoardChoice.O) {
					pleaseWork += "2";
				}else {
					pleaseWork += "3";
				}
			pleaseWork += " ";
		}
		int counter = 0;
		for(int i = 0; i < diagonals.length; i++) {//Testing the diagonals with the temp array
			if(counter == 3)
				pleaseWork += " ";
			if(gameBoard[diagonals[i].x][diagonals[i].y] == TicTacToe.BoardChoice.X) {
				pleaseWork += "1";
			}else if(gameBoard[diagonals[i].x][diagonals[i].y] == TicTacToe.BoardChoice.O) {
				pleaseWork += "2";
			}else {
				pleaseWork += "3";
			}
			counter++;
		}
		if(pleaseWork.contains("111")) { //Checker for the string
			this.gameState = TicTacToe.GameState.X_WON;
			return true;
		}else if(pleaseWork.contains("222")){
			this.gameState = TicTacToe.GameState.O_WON;
			return true;
		}else if(pointCounter == 9){
			this.gameState = TicTacToe.GameState.TIE;
			return true;
		}else {
			return false;
		}

		// // BFS Implementation
		// Queue<CheckPath> search = new LinkedList<CheckPath>();

		// // 5 points to check all winning states
		// CheckPath[] checkPaths = {new CheckPath(0,0), new CheckPath(0, 1), new CheckPath(0, 2), new CheckPath (1, 0), new CheckPath(2, 0)};
		// // Only evaluate game over when legally possible, after move 5 (3 X's, 2 0's)
		// if (pointCounter >= 5) {
		// 	// Evaluate game state
		// 	for (CheckPath root : checkPaths) {
		// 		search.add(root);
		// 	}
		// }

		// while (!search.isEmpty()) {

		// 	/*
		// 	 * NOTE: INDEXING 2D ARRAY
		// 	 * ARRAY[ROW][COLUMN]
		// 	 */

		// 	CheckPath check = search.remove();
		// 	int currentPointX = (int) check.currentPoint().getX();
		// 	int currentPointY = (int) check.currentPoint().getY();
		// 	TicTacToe.BoardChoice currentPlayer = gameBoard[currentPointY][currentPointX]; 

		// 	if (currentPlayer == TicTacToeGame.BoardChoice.OPEN) {
		// 		// Skip as there is nothing here
		// 		continue;
		// 	}

		// 	if (check.isValid() == 1) {
		// 		System.out.println("VALID GAME FOUND!");
		// 		if (currentPlayer == TicTacToe.BoardChoice.X) {
		// 			this.gameState = TicTacToe.GameState.X_WON;
		// 		} else {
		// 			System.out.println("O_WON!");
		// 			this.gameState = TicTacToe.GameState.O_WON;
		// 		}
		// 		System.out.println("GAME OVER!");
		// 		return true;
		// 	} else if (check.isValid() == 0) {
		// 		return false;
		// 	}

		// 	// Moving right
		// 	if (currentPointX + 1 < WIDTH_OF_BOARD) {
		// 		Point right = new Point(currentPointX + 1, currentPointY);
		// 		TicTacToe.BoardChoice newPlayer = gameBoard[currentPointY][currentPointX + 1]; 

		// 		// No Point to Continue
		// 		if (newPlayer == currentPlayer) {
		// 			Point[] currentPath = check.getPoints();
		// 			CheckPath add = new CheckPath(currentPath);
		// 			if (add.addToPath(right)) {
		// 				search.add(add);
		// 			}
		// 		}	
		// 	}

		
		// 	// Moving down
		// 	if (currentPointY + 1 < HEIGHT_OF_BOARD) {
		// 		Point down = new Point(currentPointX, currentPointY + 1);
		// 		TicTacToe.BoardChoice newPlayer = gameBoard[currentPointY + 1][currentPointX]; 

		// 		if (newPlayer == currentPlayer) {
		// 			Point[]currentPath = check.getPoints();
		// 			CheckPath add = new CheckPath(currentPath);
		// 			if (add.addToPath(down)) {
						
		// 				search.add(add);
		// 			}
		// 		}
		// 	}

		// 	// Moving diagonal (to right)
		// 	if (currentPointX + 1 < WIDTH_OF_BOARD && currentPointY + 1 < HEIGHT_OF_BOARD) {
		// 		Point diagonal = new Point(currentPointX + 1, currentPointY + 1);
		// 		TicTacToe.BoardChoice newPlayer = gameBoard[currentPointY + 1][currentPointX + 1]; 

		// 		if (newPlayer == currentPlayer) {
		// 			Point[] currentPath = check.getPoints();
		// 			CheckPath add = new CheckPath(currentPath);
		// 			if (add.addToPath(diagonal)) {
		// 				search.add(add);
		// 			}
		// 		}
		// 	}

		// 	// Moving diagonal (to left)
		// 	if (currentPointX - 1 >= 0 && currentPointY + 1 < HEIGHT_OF_BOARD) {
		// 		Point diagonal = new Point(currentPointX - 1, currentPointY + 1);
		// 		TicTacToe.BoardChoice newPlayer = gameBoard[currentPointY + 1][currentPointX - 1]; 

		// 		if (newPlayer == currentPlayer) {
		// 			Point[] currentPath = check.getPoints();
		// 			CheckPath add = new CheckPath(currentPath);
		// 			if (add.addToPath(diagonal)) {
		// 				search.add(add);
		// 				}
		// 		}
		// 	}
		// }
		
		// // If there is a draw
		// if (pointCounter == 9) {
		// 	this.gameState = TicTacToe.GameState.TIE;
		// 	return true;
		// }

		// return false;
	}

	// private class CheckPath {
	// 	private Point[] path;
	// 	private int pointCounter;

	// 	public CheckPath(int x, int y) {
	// 		this.path = new Point[3];
	// 		this.path[0] = new Point(x, y);
	// 		this.pointCounter = 1;
	// 	}

	// 	public CheckPath(Point[] points) {
	// 		this.path = new Point[3];
	// 		this.pointCounter = 0;
	// 		for (int i = 0; i < points.length; i++) {
	// 			if (points[i] == null) {
	// 				break;
	// 			}
	// 			this.path[i] = points[i];
	// 			this.pointCounter++;
	// 		}
	// 	}

	// 	/**
	// 	 * 
	// 	 * @return
	// 	 */
	// 	public Point[] getPoints() {
	// 		Point[] points = new Point[3];
	// 		for (int i = 0; i < this.path.length; i++) {
	// 			points[i] = this.path[i];
	// 		}
	// 		return points;
	// 	}

	// 	/**
	// 	 * 
	// 	 * @return
	// 	 */
	// 	public Point currentPoint() {
	// 		return new Point((int)this.path[pointCounter - 1].getX(), (int)this.path[pointCounter - 1].getY());
	// 	}

	// 	/**
	// 	 * 
	// 	 * @param newPoint
	// 	 * @return
	// 	 */
	// 	public boolean addToPath(Point newPoint) {
	// 		if (pointCounter > 2) {
	// 			return false;
	// 		}
	// 		this.path[pointCounter++] = newPoint;
	// 		return true;
	// 	}

	// 	/**
	// 	 * 
	// 	 * @return {0: Invalid, 1: Valid, 2: Undefined}
	// 	 */
	// 	public int isValid() {
	// 		if (pointCounter == 3) {
	// 			Point p1 = this.path[0];
	// 			Point p2 = this.path[1];
	// 			Point p3 = this.path[2];

	// 			int pointSlopeX = (int)(p2.getX() - p1.getX());
	// 			int pointSlopeY = (int)(p2.getY() - p1.getY());
	// 			if ((int)(p3.getX()) == (int)(pointSlopeX + p2.getX()) && (int)(p3.getY()) == (int)(pointSlopeY + p2.getY()) ) {
	// 				return 1;
	// 			} else {
	// 				return 0;
	// 			}
	// 		} else {
	// 			// Needs to have 3 points to be considered for validation
	// 			return 2;
	// 		}
	// 	}
	// }

	/**
	 * This method is a getter for the gameState
	 * @return TicTacToe.GameState whatever the gameState is currently at (Finish, IN_PROGRESS)
	 */
	@Override
	public TicTacToe.GameState getGameState() {
		if(gameOver() == true) {
			return this.gameState;
		}else {
			return this.gameState;
		}

	}

	/**
	 * This method returns a copy of the gameBoard's grid at that moment. 
	 * @return TicTacToe.BoardChoice[][] a copy of the grid
	 */
	@Override
	public TicTacToe.BoardChoice[][] getGameGrid() { 
		BoardChoice[][] copyOfBoard = new BoardChoice[3][3];
		for(int i = 0; i < gameBoard.length; i++) 
			for(int j = 0; j < gameBoard.length; j++) 
				copyOfBoard[i][j] = gameBoard[i][j];

		return copyOfBoard;
	}

	/**
	 * This method returns a copy of the moves array shortened down to a smaller size if there is less moves than a full game.
	 * @return Point[] the array that is a copy of the original one
	 */
	@Override
	public Point[] getMoves() { 
		boolean notNull = true;
		int counter = 0;
		while(notNull && !(counter > 8)) {
			if(counter > 9)
				notNull = false;
			if(moves[counter] != null) {
				counter++;
			}else{
				notNull = false;
			}
		}
		Point[] copyOfMoves = new Point[counter];
		for(int i = 0; i < counter; i++) 
			copyOfMoves[i] = moves[i];

		return copyOfMoves;
	}

	/**
	 * This method is a simple tester for development that outputs the gameBoard in a simple grid. 
	 * @return String outputs each value of the 2d array one by one
	 */
	public String printTable() { 
		String output = "";
		for(int i = 0; i < gameBoard.length; i++) {
			for(int j = 0; j < gameBoard[0].length; j++) 
				output+= gameBoard[i][j] + " ";	
			output += "\n";
		}
		return output;
	}
}
