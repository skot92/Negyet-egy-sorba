package Game;

/**
 * Kivétel osztály adatbázis hibákhoz.
 * 
 * @author skot92
 */
public class MyDBException extends Exception {

	/**
	 * Új <code>MyDBException</code> létrehozása.
	 * 
	 * <pre>
	 * 	throw new MyDBException();
	 * </pre>
	 */
	public MyDBException() {
		super("Error connecting to database.");
	}

}
