package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import Board.Board;

public class BishopTests {

	private Board b = new Board();
	
	@After
	public void resetBoard()
	{
		b.initBoard();
	}
	
	@Test
	public void testMoveForward()
	{
		assertFalse("A bishop can't move forward like a pawn", b.move(0, 2, 2, 2));
		assertFalse("A bishop can't move forward like a pawn", b.move(7, 2, 5, 2));
	}
	
	@Test
	public void testMoveDiagonals()
	{
		b.move(1, 3, 3, 3);
		assertTrue("A bishop SHOULD move on diagonals", b.move(0, 2, 3, 5));
		assertTrue("A bishop SHOULD move on diagonals", b.move(3, 5, 5, 3));
		assertTrue("A bishop SHOULD move on diagonals", b.move(5, 3, 2, 0));	
		
		assertFalse("This should be illegal", b.move(0, 5, 1, 4));
		assertFalse("This should be illegal", b.move(0, 5, 4, 1));
	}
}
