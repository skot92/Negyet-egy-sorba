package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Menu extends JMenuBar {

	private JMenuItem mntmNew;
	private JMenuItem mntmStat;
	private JMenuItem mntmSaveStat;

	public Menu() {
		JMenu mnGame = new JMenu("Game");
		this.add(mnGame);

		mntmNew = new JMenuItem("New");
		mnGame.add(mntmNew);
		mntmStat = new JMenuItem("Stat");
		mnGame.add(mntmStat);
		mntmSaveStat = new JMenuItem("Save stat");
		mnGame.add(mntmSaveStat);
	}

	public void controll() {

		
		
		mntmNew.addActionListener(e -> {
			Main.frame.getContentPane().removeAll();
			MainPanel mainPanel = new MainPanel();
			Main.frame.getContentPane().add(mainPanel);
			SwingUtilities.updateComponentTreeUI(Main.frame);
			
		});

		mntmStat.addActionListener(e -> {
			HighScore hs;
			try {
				hs = new HighScore();
				hs.setVisible(true);
			} catch (MyDBException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());

			}
		});

		mntmSaveStat.addActionListener(e -> {
			XmlSave save = new XmlSave();
			try {
				save.newXml();
				JOptionPane.showMessageDialog(null, "Success.");
			} catch (MySaveException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (MyDBException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}

		});
	}

}
