package Game;

/**
 * Ez az osztály reprezentálja a táblát.
 * 
 * @author skot92
 */
public class Grid {
	/**
	 * Kétdimenziós tömb, ez reprezentálja a táblát.
	 */
	private Player[][] grid;

	/**
	 * Aktuális játékos.
	 */
	private Player currentPlayer;

	/**
	 * Az A játékos adatait tartalmazó objektum.
	 */
	private PlayerInformations playerA;

	/**
	 * A B játékos adatait tartalmazó objektum.
	 */
	private PlayerInformations playerB;

	/**
	 * Az adott sor, ahol az esemény történt.
	 */
	private int row = 0;

	/**
	 * Sorok száma.
	 */
	private final static int MAX_ROW = 6;

	/**
	 * Oszlopok száma.
	 */
	private final static int MAX_COL = 7;

	/**
	 * Tábla iniciálása.
	 * 
	 * <pre>
	 * Grid grid = new Grid();
	 * </pre>
	 */
	public Grid() {
		currentPlayer = Player.ONE;
		grid = new Player[MAX_ROW][MAX_COL];
		for (int row = 0; row < MAX_ROW; row++) {
			for (int column = 0; column < MAX_COL; column++) {
				grid[row][column] = Player.NONE;
			}
		}
	}

	/**
	 * Visszaadja az aktuális sort.
	 * 
	 * @return Sor szám.
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Visszaadja az A játékos adatait.
	 * 
	 * @return A játékos.
	 */
	public PlayerInformations getPlayerA() {
		return playerA;
	}

	/**
	 * Beállítja az A játékost.
	 * 
	 * @param playerA
	 *            A átékos.
	 */
	public void setPlayerA(PlayerInformations playerA) {
		this.playerA = playerA;
	}

	/**
	 * Visszaadja a B játékos adatait.
	 * 
	 * @return B játékos.
	 */
	public PlayerInformations getPlayerB() {
		return playerB;
	}

	/**
	 * Beállítja a B játékost.
	 * 
	 * @param playerB
	 *            B játékos.
	 */
	public void setPlayerB(PlayerInformations playerB) {
		this.playerB = playerB;
	}

	/**
	 * Visszaadja az aktuális játékos adatait.
	 * 
	 * @return Az aktuális játékos.
	 */
	public PlayerInformations getCurrentPlayer() {
		if (currentPlayer == Player.ONE)
			return playerA;
		else
			return playerB;
	}

	/**
	 * Visszadja az aktuális játékost.
	 * 
	 * @return Aktuális játéko.
	 */
	public Player getCurrentPlayerAB() {
		return currentPlayer;
	}

	/**
	 * Játékosok cseréjét végző metódus.
	 * 
	 * <pre>
	 * grid.toggleCurrentPlayer();
	 * </pre>
	 */
	public void toggleCurrentPlayer() {
		if (currentPlayer == Player.ONE)
			currentPlayer = Player.TWO;
		else if (currentPlayer == Player.TWO)
			currentPlayer = Player.ONE;
	}

	/**
	 * Játékos lerakása az adott oszlopba.
	 * 
	 * @param column
	 *            Ebbe az oszlopba kerül a játékos.
	 */
	public void drop(int column) {
		for (row = MAX_ROW - 1; row > -1; row--) {
			if (grid[row][column] == Player.NONE) {
				grid[row][column] = currentPlayer;
				break;
			}
		}
	}

	/**
	 * Az adott oszlop tele van-e.
	 * 
	 * @param column
	 *            Oszlop sorszáma.
	 * 
	 * @return {@code true}, ha tele van, különben {@code false}.
	 */
	public boolean isFull(int column) {
		return grid[0][column] != Player.NONE;
	}

	/**
	 * Megnyerte-e valamelyik játékos a meccset.
	 * 
	 * @return {@code true}, ha valamelyik játékos nyert, különben {@code false}
	 *         .
	 */

	public boolean hasWon() {
		boolean status = false;
		status = horizontalTest(status);
		status = verticalTest(status);
		status = diagonalPositiveTest(status);
		status = diagonalNegativTest(status);

		return status;
	}

	/**
	 * Döntetlenért felelõs metódus.
	 * 
	 * @return {@code true} ha nincs több üres mezõ, és egyik játkos sem
	 *         gyõzött, különben {@code false}.
	 */
	public boolean isTie() {
		for (int column = MAX_COL - 1; column > -1; column--) {
			if (grid[0][column] == Player.NONE)
				return false;
		}
		return true;
	}

	/**
	 * Sorok ellenőrzése, hogy van 4 db azonos szinű egymás mellet.
	 * 
	 * @param status
	 *            A tesztek eredménye.
	 * @return {@code true} ha van 4 db azonos egymás mellet, vagy
	 *         <code>status</code> {@code true} volt, egyébként {@code false}.
	 */
	private boolean horizontalTest(boolean status) {
		for (int row = 0; row < MAX_ROW; row++) {
			for (int column = 0; column < MAX_COL - 3; column++) {
				if (grid[row][column] != Player.NONE
						&& grid[row][column] == grid[row][column + 1]
						&& grid[row][column] == grid[row][column + 2]
						&& grid[row][column] == grid[row][column + 3]) {
					status = true;
				}
			}
		}
		return status;
	}

	/**
	 * Oszlopok ellenőrzése, hogy van 4 db azonos szinű egymás mellet.
	 * 
	 * @param status
	 *            A tesztek eredménye.
	 * @return {@code true} ha van 4 db azonos egymás mellet, vagy
	 *         <code>status</code> {@code true} volt, egyébként {@code false}.
	 */
	private boolean verticalTest(boolean status) {
		for (int row = 0; row < MAX_ROW - 3; row++) {
			for (int column = 0; column < MAX_COL; column++) {
				if (grid[row][column] != Player.NONE
						&& grid[row][column] == grid[row + 1][column]
						&& grid[row][column] == grid[row + 2][column]
						&& grid[row][column] == grid[row + 3][column]) {
					status = true;
				}
			}
		}
		return status;
	}

	/**
	 * Mellék átlók ellenőrzése, hogy van 4 db azonos szinű egymás mellet.
	 * 
	 * @param status
	 *            A tesztek eredménye.
	 * @return {@code true} ha van 4 db azonos egymás mellet, vagy
	 *         <code>status</code> {@code true} volt, egyébként {@code false}.
	 *         diagonalNegativTest
	 */
	private boolean diagonalNegativTest(boolean status) {
		for (int row = 0; row < MAX_ROW - 3; row++) {
			for (int column = 3; column < MAX_COL; column++) {
				if (grid[row][column] != Player.NONE
						&& grid[row][column] == grid[row + 1][column - 1]
						&& grid[row][column] == grid[row + 2][column - 2]
						&& grid[row][column] == grid[row + 3][column - 3]) {
					status = true;
				}
			}
		}
		return status;
	}

	/**
	 * Fő átlók ellenőrzése, hogy van 4 db azonos szinű egymás mellet.
	 * 
	 * @param status
	 *            A tesztek eredménye.
	 * @return {@code true} ha van 4 db azonos egymás mellet, vagy
	 *         <code>status</code> {@code true} volt, egyébként {@code false}.
	 */
	private boolean diagonalPositiveTest(boolean status) {
		for (int row = 0; row < MAX_ROW - 3; row++) {
			for (int column = 0; column < MAX_COL - 3; column++) {
				if (grid[row][column] != Player.NONE
						&& grid[row][column] == grid[row + 1][column + 1]
						&& grid[row][column] == grid[row + 2][column + 2]
						&& grid[row][column] == grid[row + 3][column + 3]) {
					status = true;
				}
			}
		}
		return status;
	}

}
