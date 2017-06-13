package Game;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * A játékosok neveiért felelős osztály.
 * 
 * @author skot92
 */
public class PlayersName extends JOptionPane {

	private JLabel jname;
	private JTextField name;
	private Object[] obs;

	/**
	 * Játékosok neveinek meghatározása, a játék indításnál.
	 * 
	 * @return A játékosok nevei.
	 */
	public String[] setNames() {
		String[] names = new String[2];

		names[0] = this.name("Player A");
		names[1] = this.name("Player B");
		
		return names;
	}
	
	private String name(String player) {
		String res;
		jname = new JLabel(player);
		name = new JTextField();
		obs = new Object[] { jname, name };
		JOptionPane.showConfirmDialog(null, obs,
				"Input" + player + " names",
				JOptionPane.DEFAULT_OPTION);
		
		if (name.getText() == null || name.getText().equals("")) {
			res = player;
		} else {
			res = name.getText();
		}
		return res;
	}
}
