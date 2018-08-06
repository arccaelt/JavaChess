package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import com.engine.board.Board;

public class BoardGamesTests {

	private Board b = new Board();

	@After
	public void resetBoard()
	{
		b.resetBoard();
	}
	
	@Test
	public void testGame1()
	{
		
	}
}
