//package Tests;
//
//import static org.junit.Assert.*;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Scanner;
//import java.util.Map;
//import java.util.HashMap;
//import java.util.Set;
//
//import com.engine.board.*;
//
///*
// * These tests should provide full games and the moves from these
// * games will be tested to see it everyting is working as expected.
// */
//
//public class BoardGamesTests {
//
//	private Board b = new Board();
//	
//	// In this table we will store (key, value) pairs that looks like:
//	//   a => 0
//	//   b => 1
//	// so that we can convert moves like e2e4 into something like 5 2 5 4
//	Map<String, Integer> table = new HashMap<>();
//
//	public BoardGamesTests()
//	{
//		// init the table
//		int i = 0;
//		for(char c = 'a'; c <= 'h'; c++, i++)
//		{
//			table.put(String.valueOf(c), i);
//		}
//	}
//	
//	@After
//	public void resetBoard()
//	{
//		b.initBoard();
//	}
//	
//	private Move convertMove(String raw)
//	{
//		// converts a move from something like "e2e4" into 2 1 2 4
//		// 'top' variables controls the location of the player
//		// and if top is true then he is at the top so we have to increment
//		// rows otherwise we have to decrement rows
//
//		return new Move(8 - Integer.parseInt(String.valueOf(raw.charAt(1))), 
//			            table.get(String.valueOf(raw.charAt(0))),
//			            8 - Integer.parseInt(String.valueOf(raw.charAt(3))), 
//			            table.get(String.valueOf(raw.charAt(2))));
//	}
//	
//	@Test
//	public void testGame1()
//	{
//		Scanner scn = null;
//		File path = new File("C:/Users/arco/workspace2/ChessEngine/src/Tests");
//		try
//		{
//			scn = new Scanner(new File(path, "game1.txt"));
//			
//			String []moves = null;
//			
////			while(scn.hasNextLine())
////			{
////				moves = scn.nextLine().split(" ");
////				System.out.printf("%s %s\n", moves[0], moves[1]);
////				// translate the moves into Move objects and test them	
////				System.out.printf("newmove: %s", convertMove(moves[0]));
////				System.out.printf("newmove: %s", convertMove(moves[1]));
////				assertTrue(b.move(convertMove(moves[0])));
////				assertTrue(b.move(convertMove(moves[1])));
////				System.out.println(b);
////			}
//		}
//		catch(Exception exo)
//		{
//			System.out.println("Error: " + exo.getMessage());
//			exo.printStackTrace();
//		}
//		finally
//		{
//			scn.close();
//		}
//	}
//}
