import java.awt.Point;

public class TicTacToeDriver {

	public static void main(String[] args) {
		TicTacToeGame ttt = new TicTacToeGame();
		System.out.println(ttt.printTable());
		ttt.choose(TicTacToe.BoardChoice.O, 1, 2);
		ttt.choose(TicTacToe.BoardChoice.O, 0, 0);
		System.out.println(ttt.printTable());
		ttt.choose(TicTacToe.BoardChoice.X, 2, 2);
		System.out.println(ttt.printTable());
		Point[] test = ttt.getMoves();
		for(int i = 0; i < test.length; i++) {
			System.out.println(test[i]);
		}
		ttt.gameOver();
	}

}
