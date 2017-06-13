package Game;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SetDB {

	public static void set() {

		JLabel jPassword = new JLabel("Database Password");
		JTextField password = new JPasswordField();
		Object[] ob = { jPassword, password };
		int result = JOptionPane.showConfirmDialog(null, ob,
				"Please input password for database",
				JOptionPane.CANCEL_OPTION);
		JDBC.setPw(password.getText());
	}

}
