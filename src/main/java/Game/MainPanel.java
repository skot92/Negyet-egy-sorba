package Game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainPanel extends JPanel {
	private JButton[] numButton;
	private JPanel numPanel;
	private GridGUI gridGui;
	private Logger logger = LoggerFactory.getLogger(MainPanel.class);

	public MainPanel() {
		numButton = new JButton[7];
		for (int i = 0; i < numButton.length; i++) {
			numButton[i] = new JButton("" + (i + 1));
			numButton[i].setPreferredSize(new Dimension(70, 20));
			numButton[i].addActionListener(new ButtonListener());
		}

		numPanel = new JPanel();
		gridGui = new GridGUI();

		for (int i = 0; i < numButton.length; i++)
			numPanel.add(numButton[i]);

		setLayout(new BorderLayout());
		this.add(numPanel, BorderLayout.NORTH);
		this.add(gridGui, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(550, 550));

	}

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent evt) {
			for (int i = 0; i < numButton.length; i++)
				if (evt.getSource() == numButton[i])
					this.handleNumButton(i);

		}

		private void handleNumButton(int col) {

			if (gridGui.isFull(col)) {
				JOptionPane.showMessageDialog(null,
						"That column is full, pick another");
				logger.debug(col + " column is full.");
			} else {

				gridGui.drop(col);
				if (gridGui.isTie()) {
					numPanel.setVisible(false);
					JOptionPane.showMessageDialog(null, "The game is Tie");

					this.update(gridGui.getCurrentPlayer().getName(), Result.TIE);
					gridGui.toggleCurrentPlayer();
					this.update(gridGui.getCurrentPlayer().getName(), Result.TIE);
					logger.debug("The game is Tie.");
				}
				if (gridGui.hasWon()) {
					numPanel.setVisible(false);
					JOptionPane.showMessageDialog(null, gridGui
							.getCurrentPlayer().getName() + " " + " Wins");

					this.update(gridGui.getCurrentPlayer().getName(), Result.WIN);
					gridGui.toggleCurrentPlayer();
					this.update(gridGui.getCurrentPlayer().getName(), Result.LOOS);
					
					logger.debug("The game is Win.");
				}
				gridGui.toggleCurrentPlayer();
			}
		}

		private void update(String name, Result res) {
			try {
				JDBC conn = new JDBC();
				conn.putPlayers(name, res);
			} catch (MyDBException e) {
				logger.warn(e.getMessage());
			}
		}
	}
}