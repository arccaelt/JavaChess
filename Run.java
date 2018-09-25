import java.util.*;
import Board.*;
import java.io.*;
import GUI.*;

class Run {
	private static void displayHelpMessage()
	{
		System.out.println("Options are:");
		System.out.println("\t 0 for quit(while ingame)");
		System.out.println("\t 1 for continue");
		System.out.println("\t 2 to save the current game");
		System.out.println("\t 3 to load a game");
	}
	
	private static void mainloop(Board b, Scanner scn) throws IOException
	{
		boolean end = false;
		
		int x, y, nx, ny;
		while(!end)
		{
			try
			{
				System.out.println("> ");
				int q = scn.nextInt();
			
				if(q == 0)
				{
					end = true;
				}
				else if(q == 2)
				{
					ObjectOutputStream ow = new ObjectOutputStream(new FileOutputStream(new File("./savegame.dat")));
					ow.writeObject(b);
					ow.close();
					
					System.out.println("Game saved!");
				}
				else
				{
					System.out.println(b);
					System.out.print("Enter move (x, y, new_x, new_y): ");
					x = scn.nextInt();
					y = scn.nextInt();
					nx = scn.nextInt();
					ny = scn.nextInt();
					
					
					if(!b.move(new Move(x, y, nx, ny)))
						System.out.println("Invalid move");
				}
			}
			catch(InputMismatchException exo)
			{
				System.out.println("Invalid input");
				break;
			}
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, IOException
	{
		Board b = new Board();
		BoardUI gui = new BoardUI(b);
//		Scanner scn = new Scanner(System.in);
//		displayHelpMessage();
//		
//		int q = scn.nextInt();
//		if(q == 1)
//		{
//			mainloop(new Board(), scn);
//		}
//		else if(q == 3)
//		{
//			System.out.println("Give path to save game: ");
//			String pth = scn.next();
//			System.out.println("Path: " + pth);
//			
//			ObjectInputStream or = new ObjectInputStream(new FileInputStream(new File(pth)));
//			mainloop((Board)or.readObject(), scn);
//			or.close();
//		}
//		
//		scn.close();
//		System.out.println("Game over");
		
	}
}
