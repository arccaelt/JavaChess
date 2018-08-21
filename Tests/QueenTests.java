package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import Board.Board;

public class QueenTests {

	private Board b = new Board();
	
	@After
	public void resertBoard()
	{
		b.initBoard();
	}
	
	@Test
	public void testMoveForward()
	{
		b.move(1, 3, 3, 3);
		
		assertTrue(b.move(0, 3, 2, 3));
	}
	
	@Test
	public void testMoveSideways()
	{
		b.move(1, 3, 3, 3);
		
		assertTrue(b.move(0, 3, 2, 3));
		assertTrue(b.move(2, 3, 2, 5));
		assertTrue(b.move(2, 5, 2, 0));
	}
	
	@Test
	public void testMoveDiagonals()
	{
		b.move(1, 3, 3, 3);
		
		assertTrue(b.move(0, 3, 2, 3));
		assertTrue(b.move(2, 3, 5, 5));
		assertTrue(b.move(5, 5, 2, 3));
	}
}
