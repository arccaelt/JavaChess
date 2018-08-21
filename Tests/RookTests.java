package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import Board.Board;

public class RookTests {

	private Board b = new Board();
	
	@After
	public void resertBoard()
	{
		b.initBoard();
	}
	
	@Test
	public void testMoves()
	{
		b.move(1, 0, 3, 0);
		assertTrue("Rook should be able to move forward", b.move(0, 0, 2, 0));
		
		// can't skip over pieces
		assertFalse(b.move(2, 0, 3, 0));
		
		assertTrue(b.move(2, 0, 2, 5));
		assertTrue(b.move(2, 5, 5, 5));
		assertTrue(b.move(5, 5, 2, 5));
		assertTrue(b.move(2, 5, 2, 0));
		assertTrue(b.move(2, 0, 0, 0));
	}
	
	@Test
	public void testMoveDiagonals()
	{
		b.move(1, 0, 3, 0);
		
		assertFalse("A rook can't move on diagonals", b.move(0, 0, 1, 1));
		
		b.move(0, 0, 2, 0);
		
		assertFalse("A rook can't move on diagonals", b.move(2, 0, 3, 1));
	}
}
