package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Board.Move;

public class TestMoves 
{
	@Test
	public void testMoves()
	{
		// test the move class
		assertFalse((new Move(-1, 0, 3, 4)).isValid());
		assertFalse("Shouldn't be able to make this move", (new Move(0, 0, 0, 0)).isValid());
		assertFalse((new Move(0, 9, 3, 4)).isValid());
		assertFalse((new Move(2, 0, -1, 4)).isValid());
		assertFalse((new Move(-1, 0, -3, 9)).isValid());
	}

}
