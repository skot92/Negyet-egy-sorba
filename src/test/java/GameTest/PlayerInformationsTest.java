package GameTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Game.PlayerInformations;

public class PlayerInformationsTest {

	@Test
	public void getTest() {
		PlayerInformations pf = new PlayerInformations("David");
		assertEquals("David", pf.getName());
		assertEquals(0, pf.getTies());
		assertEquals(0, pf.getWins());
		assertEquals(0, pf.getLoos());
	}

	@Test
	public void setTest() {
		PlayerInformations pf = new PlayerInformations("David");
		assertEquals("David", pf.getName());
		pf.setName("Fanni");
		assertEquals("Fanni", pf.getName());
		pf.setLoos(4);
		pf.setTies(2);
		pf.setWins(6);
		assertEquals(2, pf.getTies());
		assertEquals(6, pf.getWins());
		assertEquals(4, pf.getLoos());
	}
	

	@Test
	public void newPlayerInformationsTest() {
		PlayerInformations pf = new PlayerInformations("Niki",123,75,6);
		assertEquals(6, pf.getTies());
		assertEquals(123, pf.getWins());
		assertEquals(75, pf.getLoos());
		assertEquals("Niki", pf.getName());
	}
}