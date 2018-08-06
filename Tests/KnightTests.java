package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import com.engine.board.Board;

public class KnightTests {

	private Board b = new Board();
	
	@After
	public void resetBoard()
	{
		b.resetBoard();
	}

	@Test
	public void testMoves()
	{
		assertFalse(b.move(0, 1, 1, 0));
		assertFalse(b.move(0, 1, 2, 1));
		assertTrue(b.move(0, 1, 2, 0));
		assertTrue(b.move(2, 0, 3, 2));
		assertTrue(b.move(3, 2, 4, 0));
	}
}
