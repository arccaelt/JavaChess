package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import com.engine.board.Board;

public class PawnTests {

	private Board b = new Board();
	
	@After
	public void resetBoard()
	{
		b.resetBoard();
	}
	
	@Test
	public void testMoveForwardOneCell()
	{
		assertTrue("Can't move forward one cell", b.move(1, 0, 2, 0));
		assertTrue("Can't move forward one cell", b.move(6, 1, 5, 1));
	}

	@Test
	public void testMoveForwardTwoCell()
	{
		assertTrue("Can't move forward two cell", b.move(1, 0, 3, 0));
		assertTrue("Can't move forward two cell", b.move(6, 2, 4, 2));
	}
	
	@Test
	public void testMoveForwardMoreCell()
	{
		assertFalse("Can't move forward one cell", b.move(1, 0, 5, 0));
		assertFalse("Can't move forward one cell", b.move(6, 7, 3, 7));
	}
	
	@Test
	public void testMoveBackwards()
	{
		// first move the pawns forward
		b.move(1, 2, 3, 2);
		b.move(6, 2, 5, 2);
		assertFalse("A pawn shouldn't move backwards", b.move(3, 2, 1, 2));
		assertFalse("A pawn shouldn't move backwards", b.move(5, 2, 6, 2));
	}
	
	@Test
	public void testMoveDiagonals()
	{
		// TODO: Add more tests
		assertFalse("A pawn shouldn't move on diagonals unless it is capturing pieces", 
				    b.move(1, 0, 2, 1));
		assertFalse("A pawn shouldn't move on diagonals unless it is capturing pieces", 
			    b.move(6, 0, 5, 1));
	}
	
	@Test
	public void testCaptureDiagonal()
	{
		// A pawn can move on diagonals only if it will capture another piece
		// TODO: This checks only the SV diagonal, check the others too
		b.move(1, 0, 3, 0);
		b.move(6, 1, 4, 1);
		assertTrue(b.move(3, 0, 4, 1));
	}
	
	@Test
	public void testLimitingMoves()
	{
		// Test that a pawn can be moved only once 2 cells(at the beggining)
		b.move(1, 0, 2, 0);
		assertFalse("After a pawn is moved once it shouldn't be able to move 2 cells again", b.move(2, 0, 4, 0));
	}
}
