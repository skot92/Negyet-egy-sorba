package GameTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Game.Grid;
import Game.Player;
import Game.PlayerInformations;

public class GridTest {

	Grid grid;

	@Before
	public void setUp() {
		grid = new Grid();
	}

	// 0 0 1 0 0 0 0
	// 0 2 1 0 0 0 0
	// 0 2 1 0 0 0 0
	// 0 1 1 0 0 0 0
	// 0 2 2 1 1 1 2
	// 0 2 1 2 2 2 1
	@Test
	public void testWin0() {
		assertEquals(false, grid.hasWon());
		grid.drop(2);
		grid.toggleCurrentPlayer();
		grid.drop(1);
		grid.drop(3);
		grid.drop(4);
		grid.drop(5);
		grid.toggleCurrentPlayer();
		grid.drop(6);
		grid.toggleCurrentPlayer();
		assertEquals(false, grid.hasWon());

		grid.drop(1);
		grid.drop(2);
		grid.drop(6);
		grid.toggleCurrentPlayer();
		grid.drop(3);
		grid.drop(4);
		grid.drop(5);
		assertEquals(false, grid.hasWon());

		grid.drop(1);
		grid.drop(2);
		grid.toggleCurrentPlayer();
		assertEquals(false, grid.hasWon());

		grid.drop(1);
		grid.toggleCurrentPlayer();
		grid.drop(2);
		grid.toggleCurrentPlayer();
		assertEquals(false, grid.hasWon());

		grid.drop(1);
		grid.toggleCurrentPlayer();
		grid.drop(2);
		assertEquals(false, grid.hasWon());

		grid.drop(2);
		assertEquals(true, grid.hasWon());

	}

	// 0 0 0 0 0 0 0
	// 0 0 0 0 0 0 0
	// 0 0 0 0 0 0 0
	// 0 0 0 0 0 0 0
	// 2 0 0 0 0 0 2
	// 1 1 1 1 0 0 2
	@Test
	public void testWin1() {
		assertEquals(false, grid.hasWon());

		grid.drop(0);
		grid.toggleCurrentPlayer();

		assertEquals(false, grid.hasWon());

		grid.drop(0);
		grid.toggleCurrentPlayer();

		assertEquals(false, grid.hasWon());

		grid.drop(1);
		grid.toggleCurrentPlayer();

		assertEquals(false, grid.hasWon());

		grid.drop(6);
		grid.toggleCurrentPlayer();

		assertEquals(false, grid.hasWon());

		grid.drop(2);
		grid.toggleCurrentPlayer();

		assertEquals(false, grid.hasWon());

		grid.drop(6);
		grid.toggleCurrentPlayer();

		assertEquals(false, grid.hasWon());

		grid.drop(3);
		grid.toggleCurrentPlayer();

		assertEquals(true, grid.hasWon());

	}

	// 2 0 0 0 0 0 0
	// 1 0 0 0 0 0 0
	// 2 2 0 0 1 0 0
	// 1 1 0 0 1 2 0
	// 2 2 0 0 1 2 0
	// 1 1 0 0 1 2 0
	@Test
	public void testWin2() {
		assertEquals(false, grid.hasWon());
		this.putTogglePlayer(0, 6);
		assertEquals(false, grid.hasWon());
		this.putTogglePlayer(0, 4);
		assertEquals(false, grid.hasWon());
		this.put(4, 4);
		this.put(5, 3);
		assertEquals(true, grid.hasWon());
	}

	// 0 0 0 0 0 0 0
	// 0 0 0 0 0 0 0
	// 0 0 0 0 0 0 2
	// 0 0 0 0 0 2 1
	// 2 0 0 0 2 1 1
	// 1 2 1 2 1 2 1
	@Test
	public void testWin3() {
		assertEquals(false, grid.hasWon());
		grid.drop(0);
		grid.toggleCurrentPlayer();
		grid.drop(1);
		grid.toggleCurrentPlayer();
		grid.drop(2);
		grid.toggleCurrentPlayer();
		grid.drop(3);
		grid.toggleCurrentPlayer();
		grid.drop(4);
		grid.toggleCurrentPlayer();
		grid.drop(5);
		grid.toggleCurrentPlayer();
		grid.drop(6);
		grid.toggleCurrentPlayer();
		assertEquals(false, grid.hasWon());
		
		grid.drop(4);
		grid.toggleCurrentPlayer();
		grid.drop(5);
		grid.toggleCurrentPlayer();
		grid.drop(0);
		grid.toggleCurrentPlayer();
		grid.drop(6);
		grid.toggleCurrentPlayer();
		assertEquals(false, grid.hasWon());
		
		grid.drop(5);
		grid.toggleCurrentPlayer();
		grid.drop(6);
		grid.toggleCurrentPlayer();
		assertEquals(false, grid.hasWon());
		
		grid.drop(6);
		grid.toggleCurrentPlayer();
		assertEquals(true, grid.hasWon());
		
	}

	// 0 0 0 0 0 0 0
	// 0 0 0 0 0 0 0
	// 0 0 0 1 0 0 0
	// 0 0 0 2 1 2 0
	// 0 0 0 1 2 1 0
	// 0 0 0 2 1 2 1
	@Test
	public void testWin4() {
		assertEquals(false, grid.hasWon());
		grid.drop(6);
		grid.toggleCurrentPlayer();
		grid.drop(5);
		grid.toggleCurrentPlayer();
		grid.drop(4);
		grid.toggleCurrentPlayer();
		grid.drop(3);
		grid.toggleCurrentPlayer();
		assertEquals(false, grid.hasWon());

		grid.drop(5);
		grid.toggleCurrentPlayer();
		grid.drop(4);
		grid.toggleCurrentPlayer();
		grid.drop(3);
		grid.toggleCurrentPlayer();
		assertEquals(false, grid.hasWon());

		grid.drop(5);
		grid.toggleCurrentPlayer();
		grid.drop(4);
		grid.toggleCurrentPlayer();
		grid.drop(3);
		grid.toggleCurrentPlayer();
		assertEquals(false, grid.hasWon());

		grid.drop(3);
		assertEquals(true, grid.hasWon());
	}

	// 0 0 0 0 0 0 0
	// 0 1 0 0 0 0 0
	// 0 2 1 0 0 0 0
	// 0 1 2 1 0 0 0
	// 0 2 1 1 1 0 0
	// 0 1 2 2 2 0 2
	@Test
	public void testWin5() {
		this.putTogglePlayer(1, 5);
		assertEquals(false, grid.hasWon());
		
		this.putTogglePlayer(2, 4);
		assertEquals(false, grid.hasWon());
		
		grid.drop(3);
		grid.toggleCurrentPlayer();
		grid.drop(3);
		grid.toggleCurrentPlayer();
		grid.drop(6);
		grid.toggleCurrentPlayer();
		grid.drop(3);
		grid.toggleCurrentPlayer();
		grid.drop(4);
		grid.toggleCurrentPlayer();
		grid.drop(4);
		grid.toggleCurrentPlayer();
		
		assertEquals(true, grid.hasWon());


		

	}

	// 0 0 0 0 1 0 0
	// 0 0 0 1 2 0 0
	// 0 0 1 2 1 0 0
	// 0 1 2 2 2 0 0
	// 0 2 1 1 1 0 0
	// 0 1 2 2 2 0 1
	@Test
	public void testWin6() {
		grid.drop(1);
		grid.toggleCurrentPlayer();
		grid.drop(1);
		grid.toggleCurrentPlayer();
		grid.drop(1);
		grid.toggleCurrentPlayer();
		assertEquals(false, grid.hasWon());

		grid.drop(2);
		grid.toggleCurrentPlayer();
		grid.drop(2);
		grid.toggleCurrentPlayer();
		grid.drop(2);
		grid.toggleCurrentPlayer();
		grid.drop(2);
		grid.toggleCurrentPlayer();
		assertEquals(false, grid.hasWon());

		grid.drop(3);
		grid.toggleCurrentPlayer();
		grid.drop(3);
		grid.toggleCurrentPlayer();
		grid.drop(3);
		grid.drop(3);
		grid.toggleCurrentPlayer();
		grid.drop(3);
		grid.toggleCurrentPlayer();
		assertEquals(false, grid.hasWon());

		grid.drop(4);
		grid.toggleCurrentPlayer();
		grid.drop(4);
		grid.toggleCurrentPlayer();
		grid.drop(4);
		grid.toggleCurrentPlayer();
		grid.drop(4);
		grid.toggleCurrentPlayer();
		grid.drop(4);
		grid.toggleCurrentPlayer();
		grid.drop(4);
		grid.toggleCurrentPlayer();
		assertEquals(true, grid.hasWon());

	}

	// 0 0 0 0 0 0 0
	// 0 0 0 0 2 0 0
	// 0 1 0 0 2 0 0
	// 0 1 1 2 2 0 0
	// 0 1 1 2 2 0 0
	// 0 1 2 1 1 0 0
	@Test
	public void testWin7() {
		grid.drop(1);
		grid.toggleCurrentPlayer();
		grid.drop(2);
		grid.toggleCurrentPlayer();
		grid.drop(3);
		grid.toggleCurrentPlayer();
		grid.drop(3);
		grid.toggleCurrentPlayer();
		grid.drop(4);
		assertEquals(false, grid.hasWon());
		grid.toggleCurrentPlayer();
		grid.drop(4);
		grid.toggleCurrentPlayer();
		grid.drop(1);
		grid.toggleCurrentPlayer();
		grid.drop(4);
		grid.toggleCurrentPlayer();
		grid.drop(1);
		grid.toggleCurrentPlayer();
		assertEquals(false, grid.hasWon());
		grid.drop(3);
		grid.toggleCurrentPlayer();
		grid.drop(1);
		grid.toggleCurrentPlayer();
		grid.drop(4);
		grid.toggleCurrentPlayer();
		grid.drop(2);
		grid.toggleCurrentPlayer();
		grid.drop(4);
		grid.toggleCurrentPlayer();
		assertEquals(true, grid.hasWon());
	}

	@Test
	public void testisTie1() {
		this.putTogglePlayer(0, 6);
		this.putTogglePlayer(1, 6);
		this.putTogglePlayer(2, 6);

		grid.drop(5);

		this.putTogglePlayer(3, 6);
		this.putTogglePlayer(4, 6);
		this.putTogglePlayer(5, 5);
		this.putTogglePlayer(6, 6);
		assertEquals(true, grid.isTie());

	}

	@Test
	public void testisTie2() {
		this.put(0, 3);
		this.put(1, 3);
		this.put(2, 3);
		grid.drop(3);
		assertEquals(false, grid.isTie());
		assertEquals(true, grid.hasWon());
	}

	@Test
	public void testisFull1() {
		grid.drop(2);
		grid.drop(2);
		grid.toggleCurrentPlayer();
		grid.drop(2);
		grid.toggleCurrentPlayer();
		grid.drop(2);
		assertEquals(false, grid.isFull(2));
		grid.drop(2);
		assertEquals(false, grid.isFull(2));
		grid.drop(2);
		assertEquals(true, grid.isFull(2));
	}

	@Test
	public void testisFull2() {
		this.put(0, 6);
		assertEquals(true, grid.isFull(0));
		this.put(1, 5);
		assertEquals(false, grid.isFull(1));

	}

	@Test
	public void toggleCurrentPlayerTest() {

		PlayerInformations player;
		Player current = Player.NONE;
		assertSame(Player.NONE, current);

		grid.toggleCurrentPlayer();
		current = grid.getCurrentPlayerAB();
		assertSame(Player.TWO, current);

		player = grid.getCurrentPlayer();
		assertSame(grid.getPlayerB(), player);

		grid.toggleCurrentPlayer();
		current = grid.getCurrentPlayerAB();
		assertSame(Player.ONE, current);

		player = grid.getCurrentPlayer();
		assertSame(grid.getPlayerA(), player);

		grid.toggleCurrentPlayer();
		player = grid.getCurrentPlayer();
		assertSame(grid.getPlayerB(), player);

		grid.setPlayerB(player);
		assertSame(grid.getPlayerB(), player);

		grid.toggleCurrentPlayer();
		grid.setPlayerA(player);
		assertSame(grid.getPlayerA(), player);
	}

	@Test
	public void getRowTest() {
		assertEquals(0, grid.getRow());

		grid.drop(0);
		assertEquals(5, grid.getRow());

		grid.drop(0);
		assertEquals(4, grid.getRow());

		grid.drop(0);
		assertEquals(3, grid.getRow());

		grid.drop(0);
		assertEquals(2, grid.getRow());

		grid.drop(0);
		assertEquals(1, grid.getRow());

		grid.drop(0);
		assertEquals(0, grid.getRow());

		grid.drop(2);
		assertEquals(5, grid.getRow());

	}

	private void put(int column, int many) {
		for (int i = 0; i < many; i++) {
			grid.drop(column);
		}
	}

	private void putTogglePlayer(int column, int many) {
		for (int i = 0; i < many; i++) {
			grid.drop(column);
			grid.toggleCurrentPlayer();
		}
	}

}
