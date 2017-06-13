package Game;

import java.beans.Transient;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Adatbázis műveleteket megvalósító osztály.
 * 
 * @author skot92
 */
public class JDBC {
	/**
	 * Az adott osztály naplózója.
	 * 
	 */
	private static Logger logger = LoggerFactory.getLogger(JDBC.class);

	/**
	 * Az adatbázis kapcsolat.
	 * 
	 */
	private static Connection conn;

	private static String pw;

	/**
	 * Konstruktor.
	 * @throws MyDBException 
	 */
	public JDBC() throws MyDBException {
		try {
			if (conn == null || !conn.isValid(5) ) {
				throw new MyDBException();
			}
		} catch (SQLException e) {
			;
		}
	}

	public static void setPw(String pw) {
		JDBC.pw = pw;
	}

	
	/**
	 * Adatbázis kapcsolat létrehozása.
	 */
	public static void setConn() {
		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@db.inf.unideb.hu:1521:ora11g",
					"H_JAMEAG", pw);
			logger.info("Connection successful.");
		} catch (SQLException e) {
			logger.error("Connection unsuccessful.");
		}
	}

	/**
	 * A játékos statisztikák lekérdezése.
	 * 
	 * @return A játékosok adatait adja vissza egy listában.
	 * @throws MyDBException 
	 */
	public List<PlayerInformations> getPlayers() throws MyDBException {
		List<PlayerInformations> players = new ArrayList<PlayerInformations>();
		try {
			
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt
					.executeQuery("SELECT * FROM PLAYERS ORDER BY PLAYER_NAME");
			while (rset.next()) {
				players.add(new PlayerInformations(rset
						.getString("PLAYER_NAME"), rset.getInt("PLAYER_WINS"),
						rset.getInt("PLAYER_LOSES"), rset.getInt("PLAYER_TIE")));
			}
			logger.info("Successful select.");
		} catch (SQLException e) {
			logger.warn("Unsuccessful select.");
			throw new MyDBException();
		}

		return players;
	}

	/**
	 * Az adott játékos frissítés/beillesztése az adatbázisba.
	 * 
	 * @param name
	 *            Az adott játékos neve.
	 * @param res
	 *            Az adott játékos eredménye.
	 * 
	 *            <pre>
	 *  Például: 
	 * 	conn.putPlayers("David",Result.WIN)
	 * </pre>
	 * @throws MyDBException 
	 */
	public void putPlayers(String name, Result res) throws MyDBException {
		int wins = 0;
		int loses = 0;
		int ties = 0;
		String expression = "SELECT * FROM PLAYERS WHERE PLAYER_NAME = ?";
		boolean isExist = false;
		try {
			PreparedStatement pstm = conn.prepareStatement(expression);
			pstm.setString(1, name);
			ResultSet rset = pstm.executeQuery();
			while (rset.next()) {
				wins = rset.getInt("PLAYER_WINS");
				loses = rset.getInt("PLAYER_LOSES");
				ties = rset.getInt("PLAYER_TIE");
				isExist = true;
			}

		} catch (SQLException | NullPointerException  e) {
			logger.warn("Unsuccessful select.");
		} 

		if (isExist) {
			String update;
			if (res == Result.WIN) {
				update = "UPDATE PLAYERS SET PLAYER_WINS = " + (wins + 1)
						+ " WHERE PLAYER_NAME = '" + name + "'";
			} else if (res == Result.LOOS) {
				update = "UPDATE PLAYERS SET PLAYER_LOSES = " + (loses + 1)
						+ " WHERE PLAYER_NAME = '" + name + "'";
			} else {
				update = "UPDATE PLAYERS SET PLAYER_TIE = " + (ties + 1)
						+ " WHERE PLAYER_NAME = '" + name + "'";
			}
			commit(update);

		} else {
			String insert;
			if (res == Result.WIN) {
				insert = "INSERT INTO PLAYERS VALUES('" + name + "'," + "1,"
						+ "0," + "0)";
			} else if (res == Result.LOOS) {
				insert = "INSERT INTO PLAYERS VALUES('" + name + "'," + "0,"
						+ "1," + "0)";
			} else {
				insert = "INSERT INTO PLAYERS VALUES('" + name + "'," + "0,"
						+ "0," + "1)";
			}
			commit(insert);
		}
	}

	/**
	 * A műveletet végrehajtó metódus.
	 * 
	 * @param sql
	 *            Sql kifejezés amit végre kell hajtani.
	 */
	private void commit(String sql) {
		try {
			Statement ps = conn.createStatement();
			ps.executeUpdate(sql);
			conn.commit();
			logger.info("Player insert/update.");
		} catch (SQLException | NullPointerException e) {
			logger.warn("Commit unsuccessful.");
		}
	}

}
