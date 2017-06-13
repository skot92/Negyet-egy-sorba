package Game;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Eredmény táblát megvalósító osztály.
 * 
 * @author skot92
 */
public class HighScore extends JDialog {

	
	private final JPanel contentPanel = new JPanel();
	
	private JTable table;

	public HighScore() throws MyDBException {
		setTitle("Stat");
		setBounds(100, 100, 468, 343);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		List<PlayerInformations> players = new ArrayList<PlayerInformations>();
		
		try {
			JDBC stat = new JDBC();
			players = stat.getPlayers();
		} catch (Exception e) {
			throw new MyDBException();
		}

		Object[][] o = new Object[players.size()][4];

		for (int i = 0; i < players.size(); i++) {
			o[i][0] = players.get(i).getName();
			o[i][1] = players.get(i).getWins();
			o[i][2] = players.get(i).getLoos();
			o[i][3] = players.get(i).getTies();
		}

		table = new JTable(new DefaultTableModel(o, new String[] { "Names",
				"Wins", "Loses", "Ties" }));
		JScrollPane scrollPane = new JScrollPane(table);
		table.setEnabled(false);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));

		contentPanel.add(scrollPane);
	}

}
