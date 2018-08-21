package Board;

// Abstract the concept of a move for a chess game
// NOTE: The class has to be public because i want to access it from other
// packages as well(for example, the package in which i store test files)
public final class Move
{
	public int x;
	public int y;
	public int nx;
	public int ny;
	
	public Move(int x, int y, int a, int b)
	{
		this.nx = a;
		this.ny = b;
		this.x = x;
		this.y = y;
	}
	
	public boolean isValid()
	{
		if(x < 0 || y < 0 || nx < 0 || ny < 0)
		{
			System.out.printf("invalid: %d %d %d %d\n", x, y, nx, ny);
			return false;
		}
		
		if(x == 0 && y == 0 && nx == 0 && ny == 0)
			return false;
		
		if(x > 8 || y > 8 || nx > 8 || ny > 8)
		{
			System.out.printf("invalid: %d %d %d %d\n", x, y, nx, ny);
			return false;
		}
			
		return true;
	}
	
	@Override
	public String toString()
	{
		return String.format("Move: %d-%d => %d-%d\n", x, y, nx, ny);
	}
}
