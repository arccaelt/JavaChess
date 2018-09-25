package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import Board.Board;

public class KingTests 
{
	private Board b = new Board();
	
	@After
	public void resetBoard()
	{
		b.initBoard();
	}

	@Test
	public void testMovementBot()
	{
		b.move(6, 3, 4, 3);
		b.move(1, 3, 3, 3);
		
		assertTrue(b.move(7, 3, 6, 3));
		assertTrue(b.move(6, 3, 5, 4));
		assertTrue(b.move(5, 4, 5, 3));
		assertTrue(b.move(5, 3, 5, 2));
		assertTrue(b.move(5, 2, 5, 3));
		assertTrue(b.move(5, 3, 6, 3));
	}
	
	@Test
	public void testMovementTop()
	{
		b.move(1, 3, 3, 3);
		assertTrue(b.move(0, 3, 1, 3));
	}
	
	@Test
	public void testMoveInCheckBot()
	{
		b.move(6, 3, 4, 3);
		b.move(1, 3, 3, 3);
		
		assertTrue(b.move(7, 3, 6, 3));
		assertTrue(b.move(6, 3, 5, 4));
		assertFalse(b.move(5, 4, 4, 4));
		assertTrue(b.move(5, 4, 5, 5));
		assertTrue(b.move(5, 5, 5, 6));
		assertFalse(b.move(5, 3, 6, 3));
	}
	
	@Test
	public void testCastleTop()
	{
		assertTrue(b.move(1, 4, 2, 4));
		assertTrue(b.move(0, 5, 4, 1));
		assertTrue(b.move(0, 6, 2, 5));
		assertTrue(b.move(0, 4, 0, 7));
	}
}
