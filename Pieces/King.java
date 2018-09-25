package Pieces;

import static Board.Board.*;
import static Pieces.PieceData.*;
import static java.lang.Math.abs;

import java.io.Serializable;

import Board.Move;
import Pieces.Piece;

public class King extends Piece implements Serializable, Movable
{
	public boolean was_moved;
	
	public King(int color)
	{
		super(color, PieceData.KING);
	}
	
	@Override
	public boolean isMoved()
	{
		return was_moved;
	}
	
	@Override
	public void setMoved()
	{
		was_moved = true;
	}
	
	public boolean isValidMove(Move mv, Piece board[][])
	{
		int x = mv.x;
		int y = mv.y;
		int nx = mv.nx;
		int ny = mv.ny;
		
		int distance_x = abs(x - nx);
		int distance_y = abs(y - ny);
		
		if(distance_x > board[x][y].info.move_cells || distance_y > board[x][y].info.move_cells)
		{
			return false;
		}
		
		if(board[nx][ny] != null && board[nx][ny].equals(board[x][y]))
		{
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
	
	/*
	 * Method that test if the king can castle/move
	 * You cannot castle if:
	 *   Your king has been moved earlier in the game.
	 *   The rook that you would castle with has been moved earlier in the game.
	 *   There are pieces standing between your king and rook.
	 *   The king is in check.
	 *   The king moves through a square that is attacked by a piece of the opponent.
	 *   The king would be in check after castling. 
	 */
	public boolean canCastle(Move mv, Piece board[][])
	{
		// If the king or rook was moved then we cannot be castled
		if(was_moved)
			return false;
		
		if(board[mv.x][mv.y] instanceof Rook)
			if(((Rook)board[mv.x][mv.y]).was_moved)
				return false;
		
		if(board[mv.nx][mv.ny] instanceof Rook)
			if(((Rook)board[mv.nx][mv.ny]).was_moved)
				return false;
		
		System.out.println("not moved");
		// make sure we try to move then horizontally
		if(mv.x != mv.nx)
			return false;
		
		// also, make sure the distance is right
		if(mv.ny > mv.y)
		{
			if(mv.ny - mv.y != 3)
			{
				System.out.println("failed here");
				return false;
			}
		}
		else
		{
			if(mv.ny - mv.y != 4)
				return false;
		}
		
		System.out.println("pass 2");
		
		// check if the king is in check
		if(isAttacked(mv.x, mv.y, board))
			return false;
		
		// check if there are pieces between the rook and the king
		// also, check if the king moves thry attacked squares
		if(mv.ny > mv.y)
		{
			for(int i = mv.y + 1; i < mv.ny; i++)
			{
				if(board[mv.x][i] != null)
					return false;
			}
		}
		else
		{
			for(int i = mv.y - 1; i > mv.ny; i--)
			{
				if(board[mv.x][i] != null)
					return false;
			}
		}
		
		// also, check if the king moves thru attacked squares
		// NOTE: Ugly hack because we create King objects at some locations and then
		// use the isAttacked method to test if it's attacked
		if(mv.ny > mv.y)
		{
			for(int i = mv.y + 1; i < mv.ny; i++)
			{
				board[mv.x][i] = new King(board[mv.x][mv.y].color);
				if(isAttacked(mv.x, i, board))
				{
					board[mv.x][i] = null;
					return false;
				}
				board[mv.x][i] = null;
			}
		}
		else
		{
			for(int i = mv.y - 1; i > mv.ny; i--)
			{
				board[mv.x][i] = new King(board[mv.x][mv.y].color);
				if(isAttacked(mv.x, i, board))
				{
					board[mv.x][i] = null;
					return false;
				}
				board[mv.x][i] = null;
			}
		}
		
		// check if the king would be in check after castle
		if(isAttacked(mv.nx, mv.ny, board))
			return false;
		
		return true;
	}

	// NOTE: Even tho the board has a instance of a King object in it
	// that instance is being referenced by a variable of its superclass, 'Piece'
	// and because of that, thru that reference i can access only the methods and fields
	// defined in the Piece class and not the unique stuff i've added here.
	// The magic of upcasting....
	@Override
	boolean isAttacked(int x, int y, Piece board[][])
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
				if(board[i][j].equals(board[x][y]))
				{
					return false;
				}
				else
				{
					if(board[i][j].info.can_move_on_diagonals && abs(i - x) <= board[i][j].info.move_cells_diagonals)
	 				{
						System.out.println("fail1");
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
				if(board[i][j].equals(board[x][y]))
				{
					return false;
				}
				else
				{
					if(board[i][j].info.can_move_on_diagonals && abs(i - x) <= board[i][j].info.move_cells_diagonals)
	 				{
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
				if(board[i][j].equals(board[x][y]))
				{
					return false;
				}
				else
				{
					if(board[i][j].info.can_move_on_diagonals && abs(i - x) <= board[i][j].info.move_cells_diagonals)
	 				{
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
				if(board[i][j].equals(board[x][y]) && abs(i - x) <= board[i][j].info.move_cells_diagonals)
				{
					return false;
				}
				else
				{
					if(board[i][j].info.can_move_on_diagonals)
	 				{
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
				if(board[i][y].equals(board[x][y]))
				{
					return false;
				}
				else
				{
					if(board[i][y].info.can_move_forward && board[i][y].info.move_cells >= abs(x - i))
	 				{
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
				if(board[i][y].equals(board[x][y]))
				{
					return false;
				}
				else
				{
					if(board[i][y].info.can_move_forward && board[i][y].info.move_cells >= abs(x - i))
	 				{
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
				if(!board[x][i].equals(board[x][y]))
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
				if(!board[x][i].equals(board[x][y]))
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
