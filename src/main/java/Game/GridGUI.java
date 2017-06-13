package Game;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GridGUI extends JPanel {

	private JLabel[][] slots;
	private Grid grid;
	private Logger logger = LoggerFactory.getLogger(GridGUI.class);

	private final int MAX_ROW = 6, MAX_COL = 7;

	public GridGUI() {

		grid = new Grid();

		playersNames();

		setLayout(new GridLayout(MAX_ROW, MAX_COL));
		slots = new JLabel[MAX_ROW][MAX_COL];
		for (int row = 0; row < MAX_ROW; row++) {
			for (int column = 0; column < MAX_COL; column++) {
				slots[row][column] = new JLabel();

				slots[row][column]
						.setHorizontalAlignment(SwingConstants.CENTER);
				slots[row][column].setVerticalAlignment(SwingConstants.CENTER);
				slots[row][column].setBorder(new LineBorder(Color.black));
				slots[row][column].setOpaque(true);
				add(slots[row][column]);

			}
		}
		setSize(833, 524);
		setVisible(true);
	}

	private void draw(int row, int column) {
		if (grid.getCurrentPlayerAB() == Player.ONE) {
			slots[row][column].setBackground(Color.RED);
		} else {
			slots[row][column].setBackground(Color.BLUE);
		}
	}

	public void drop(int column) {
		grid.drop(column);
		logger.debug("Drop the " + grid.getRow() + " row and " + column
				+ " column");
		draw(grid.getRow(), column);
	}

	public boolean isFull(int column) {
		return grid.isFull(column);
	}

	public boolean hasWon() {
		return grid.hasWon();
	}

	public boolean isTie() {
		return grid.isTie();
	}

	public PlayerInformations getCurrentPlayer() {
		return grid.getCurrentPlayer();
	}

	public void toggleCurrentPlayer() {
		grid.toggleCurrentPlayer();
	}

	private void playersNames() {
		PlayersName in = new PlayersName();
		String[] names = in.setNames();
		logger.info(names[0] + " vs " + names[1]);
		grid.setPlayerA(new PlayerInformations(names[0]));
		grid.setPlayerB(new PlayerInformations(names[1]));

	}

}