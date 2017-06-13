package Game;

/**
 * Ez az osztály reprezentálja a játékosok adatait.
 * 
 * @author skot92
 */
public class PlayerInformations {
	/**
	 * Játékos neve.
	 */
	private String name;

	/**
	 * Győzelmek száma.
	 */
	private int wins;

	/**
	 * Vereségek száma.
	 */
	private int loos;

	/**
	 * Döntetlenek száma.
	 */
	private int ties;

	/**
	 * Játékos iniciálása.
	 * 
	 * @param name
	 *            Játékos neve.
	 * @param wins
	 *            Játékos győzelmeinek száma.
	 * @param loos
	 *            Játékos vereségeinek száma.
	 * @param ties
	 *            Játékos döntetlennek száma
	 */
	public PlayerInformations(String name, int wins, int loos, int ties) {
		super();
		this.name = name;
		this.wins = wins;
		this.loos = loos;
		this.ties = ties;
	}

	/**
	 * Játékos iniciálása.
	 * 
	 * @param name
	 *            Játékos neve.
	 */
	public PlayerInformations(String name) {
		super();
		this.name = name;
	}

	/**
	 * Aktuális játékos döntetlennek számát adja vissza.
	 * 
	 * @return döntetlennek száma.
	 */
	public int getTies() {
		return ties;
	}

	/**
	 * Aktuális játékos döntetlennek számát állítja be.
	 * 
	 * @param ties
	 *            Döntetlenek száma.
	 */
	public void setTies(int ties) {
		this.ties = ties;
	}

	/**
	 * Aktuális játékos nevét adja vissza.
	 * 
	 * @return A játékos neve.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Aktuális játékos nevét állítja be.
	 * 
	 * @param name
	 *            A játékos neve.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Aktuális játékos győzelmeinek számát adja vissza.
	 * 
	 * @return A győzelmek száma.
	 */
	public int getWins() {
		return wins;
	}

	/**
	 * Aktuális játékos győzelmeinek számát állítja be.
	 * 
	 * @param wins
	 *            Győzelmek száma.
	 */
	public void setWins(int wins) {
		this.wins = wins;
	}

	/**
	 * Aktuális játékos vereségeinek számát adja vissza.
	 *
	 * @return Vereségek száma.
	 */
	public int getLoos() {
		return loos;
	}

	/**
	 * Aktuális játékos vereségeinek számát állítja be.
	 * 
	 * @param loos
	 *            Vereségek száma.
	 */
	public void setLoos(int loos) {
		this.loos = loos;
	}

}
