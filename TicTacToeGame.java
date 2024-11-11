import java.awt.Point;

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

	/**
	 * The default constructor that creates a new gameBoard and moves array. Also calls the newGame() method to reset the board and fill it with OPEN values.
	 */
	public TicTacToeGame() { 
		this.gameBoard = new BoardChoice[3][3];
		this.moves = new Point[9];
		newGame();
	}

	/**
	 * The method that starts a newGame by reseting values to their default state. Also sets the game to IN_PROGRESS.
	 */
	@Override
	public void newGame() { 
		for(int i = 0; i < gameBoard.length; i++) 
			for(int j = 0; j < gameBoard.length; j++) 
				gameBoard[i][j] = BoardChoice.OPEN; 
		this.gameState = TicTacToe.GameState.IN_PROGRESS;
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
		if(this.player == player || gameBoard[row][col] != TicTacToe.BoardChoice.OPEN || !(pointCounter < 9) || gameOver()) {
			return false;
		}else {
			Point p = new Point(row, col);
			moves[pointCounter] = p;
			pointCounter++;
			this.player = player;
			gameBoard[row][col] = player;
			gameOver();
			return true;
		}
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
	}

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
