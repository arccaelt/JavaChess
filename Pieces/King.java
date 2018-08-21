package Pieces;

import static Board.Board.*;
import static Pieces.PieceValue.*;
import static java.lang.Math.abs;

import java.io.Serializable;

import Board.Move;
import Pieces.Piece;

public class King extends Piece implements Serializable
{
	private boolean wasChecked;
	
	public King(int color)
	{
		super(KING.getValue(), color, KING.getMoveCells(), true, true, true, "King");
	}
	
	public boolean isValidMove(Move mv, Piece board[][])
	{
		int x = mv.x;
		int y = mv.y;
		int nx = mv.nx;
		int ny = mv.ny;
		
		int distance_x = abs(x - nx);
		int distance_y = abs(y - ny);
		
		if(distance_x > board[x][y].move_cells || distance_y > board[x][y].move_cells)
		{
			System.out.println("overmove");
			return false;
		}
		
		if(board[nx][ny] != null && board[nx][ny].isSame(board[x][y]))
		{
			System.out.println("the same");
			return false;
		}
		
		board[nx][ny] = board[x][y];
		board[x][y] = null;
		if(isAttacked(nx, ny, board))
		{
			System.out.println("king is in check there");
			board[x][y] = board[nx][ny];
			board[nx][ny] = null;
			return false;
		}
		board[x][y] = board[nx][ny];
		board[nx][ny] = null;	
		
		return true;
	}

	// NOTE: Even tho the board has a instance of a King object in it
	// that instance is being referenced by a variable of its superclass, 'Piece'
	// and because of that, thru that reference i can access only the methods and fields
	// defined in the Piece class and not the unique stuff i've added here.
	// The magic of upcasting....
	@Override
	public boolean isAttacked(int x, int y, Piece board[][])
	{
		// NOTE: The code here is kinda duplicated because i modify the checkDiagonal.., checkColumn
		// methods just a bit.
		// TODO: Find a better way to handle this(maybe a rewrite from scratch)
		
		// Check the king positions for attacks from pieces on diagonals(all four).
		// NOTE: If we find a friendly piece of diagonal then we stop searching because
		// that piece will stop all attacks from that diagonal
		// NOTE: If an enemy piece cannot attack on diagonal then stop searching(that piece will stop all attacks)
		// NOTE: If we find an enemy piece THAT CAN ATTACK on diagonal and that piece can't move
		// enough cells to reach the king(only one case: PAWN) then also stop searching. Otherwise, the king is in check
		for(int i = x - 1, j = y + 1; i != -1 && j != 8; i--, j++)
		{
			if(board[i][j] != null)
			{
				if(board[i][j].isSame(board[x][y]))
				{
					return false;
				}
				else
				{
					if(board[i][j].can_move_on_diagonals && abs(i - x) <= board[i][j].move_cells_diagonals)
	 				{
						System.out.println("check1");
						return true;
					}
					else
					{
						break;
					}
				}
			}
		}
		
		for(int i = x - 1, j = y - 1; i != -1 && j != -1; i--, j--)
		{
			if(board[i][j] != null)
			{
				if(board[i][j].isSame(board[x][y]))
				{
					return false;
				}
				else
				{
					System.out.printf("here %d %d\n", abs(i - x), board[i][j].move_cells_diagonals);
					if(board[i][j].can_move_on_diagonals && abs(i - x) <= board[i][j].move_cells_diagonals)
	 				{
						System.out.println("check2");
						return true;
					}
					else
					{
						break;
					}
				}
			}
		}

		for(int i = x + 1, j = y + 1; i != 8 && j != 8; i++, j++)
		{
			if(board[i][j] != null)
			{
				if(board[i][j].isSame(board[x][y]))
				{
					return false;
				}
				else
				{
					if(board[i][j].can_move_on_diagonals && abs(i - x) <= board[i][j].move_cells_diagonals)
	 				{
						System.out.println("check3");
						return true;
					}
					else
					{
						break;
					}
				}
			}
		}
		
		for(int i = x + 1, j = y - 1; i != 8 && j != -1; i++, j--)
		{
			if(board[i][j] != null)
			{
				if(board[i][j].isSame(board[x][y]) && abs(i - x) <= board[i][j].move_cells_diagonals)
				{
					return false;
				}
				else
				{
					if(board[i][j].can_move_on_diagonals)
	 				{
						System.out.println("check4");
						return true;
					}
					else
					{
						break;
					}
				}
			}
		}
		
		// check the columns and lines as well
		for(int i = x - 1; i >= 0; i--)
		{
			if(board[i][y] != null)
			{
				if(board[i][y].isSame(board[x][y]))
				{
					return false;
				}
				else
				{
					if(board[i][y].can_move_forward && board[i][y].move_cells >= abs(x - i))
	 				{
						System.out.println("check5");
						return true;
					}
					else
					{
						return false;
					}
				}
			}
		}
		for(int i = x + 1; i <= 7; i++)
		{
			if(board[i][y] != null)
			{
				if(board[i][y].isSame(board[x][y]))
				{
					return false;
				}
				else
				{
					if(board[i][y].can_move_forward && board[i][y].move_cells >= abs(x - i))
	 				{
						System.out.println("check6");
						return true;
					}
					else
					{
						return false;
					}
				}
			}
		}
		
		for(int i = y - 1; i >= 0; i--)
		{
			if(board[x][i] != null)
			{
				if(!board[x][i].isSame(board[x][y]))
				{
					if(board[x][i] instanceof Rook || board[x][y] instanceof Queen)
						return true;
				}
				else
				{
					return false;
				}
			}
		}
		
		for(int i = y + 1; i <= 8; i++)
		{
			if(board[x][i] != null)
			{
				if(board[x][i].isSame(board[x][y]))
				{
					if(board[x][i] instanceof Rook || board[x][y] instanceof Queen)
						return true;
				}
				else
				{
					return false;
				}
			}
		}
		
		return false;
	}
}
