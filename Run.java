import com.engine.board.*;
import java.util.Scanner;
import com.engine.algorithms.*;

class Run {
	public static void main(String[] args)
	{
		Board b = new Board();
		Scanner scn = new Scanner(System.in);
		
		boolean end = false;
		
		int x, y, nx, ny;
		while(1 == 1)
		{
			//System.out.print("End? (1 = yes/0 = no): ");
			//int q = scn.nextInt();
			
			if(0 == 1)
			{
				end = true;
			}
			else
			{
				System.out.println(b);
				System.out.print("Enter move (x, y, new_x, new_y): ");
				x = scn.nextInt();
				y = scn.nextInt();
				nx = scn.nextInt();
				ny = scn.nextInt();
				b.move(x, y, nx, ny);
			}
		}
		
		//scn.close();
		//System.out.println("Game over");
	}
}
