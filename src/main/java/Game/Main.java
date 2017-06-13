package Game;

import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Ez az osztály tartalmazza a main metódust.
 * 
 * @author skot92
 */
public class Main {
	
	static JFrame frame;

	/**
	 * A program belépési pontja.
	 * 
	 * @param args
	 *            Parancssori argumentumok.
	 */
	public static void main(String[] args) {

		SetDB.set();
		JDBC.setConn();
		Menu menu = new Menu();

		frame = new JFrame();
		frame.setPreferredSize(new Dimension(550, 550));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setJMenuBar(menu);
		menu.controll();

		frame.setVisible(true);

	}
}
