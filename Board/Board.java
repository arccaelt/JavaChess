package Board;

import static java.lang.Math.*;

import java.io.Serializable;
import Board.Move.*;

import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;

public final class Board implements Serializable
{
	// TODO: Use bitboards
	// Here we use the runtime polymorhism in Java.
	// Basically, we define a vector of vectors of variables that are
	// able to referece objects of the superclass 'Piece' and we use
	// these variables to also reference objects of its subclasses.
	// NOTE: If the subclasses adds something new that is not defined
	// in the superclass, i won't be able to access those new thing
	// thru these variables
	// NOTE: Get rid of this upcasting because it makes the code ugly
	// and hard to maintain
	private Piece board[][] = null;
	
	public static final int black = 0;
	public final int white = 1;
	
	public Board()
	{
		// init the board
		board = new Piece[8][8];
		initBoard();
	}
	
	public boolean move(int x, int y, int nx, int ny)
	{
		// TODO: This is for the legacy code in the tests. Change them to
		// use the Move objects as well
		return move(new Move(x, y, nx, ny));
	}
	
	public boolean move(Move mv)
	{
		int x = mv.x;
		int y = mv.y;
		int nx = mv.nx;
		int ny = mv.ny;
		if(mv.isValid())
		{
			if(isValidMove(mv))
			{
				board[nx][ny] = board[x][y];
				board[x][y] = null;
				
				if(board[nx][ny] instanceof Pawn)
				{
					// if it's the first time we move the piece then make sure
					// we update the variables accordingly
					if(!board[nx][ny].was_moved)
					{
						board[nx][ny].was_moved = true;
						board[nx][ny].move_cells = 1; // only 1 cells from now
					}
				}
			}
			else
			{
				System.out.println("WARNING! INVALID MOVE");
				return false;
			}
		}
		else
		{
			System.out.println("WARNING! INVALID MOVE");
			return false;
		}
		return true;
	}
	
	public static boolean checkColumnUp(int x, int y, int nx, Piece board[][])
	{
		for(int i = x + 1; i <= nx; i++)
		{
			if(board[i][y] != null)
			{
				if(board[i][y].isSame(board[x][y]))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean checkColumnDown(int x, int y, int nx, Piece board[][])
	{
		for(int i = x - 1; i >= nx; i--)
		{
			if(board[i][y] != null)
			{
				if(board[i][y].isSame(board[x][y]))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean checkLineLeft(int x, int y, int ny, Piece board[][])
	{
		for(int i = y - 1; i >= ny; i--)
		{
			if(board[x][i] != null)
			{
				if(board[x][i].isSame(board[x][y]))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean checkLineRight(int x, int y, int ny, Piece board[][])
	{
		for(int i = y + 1; i <= ny; i++)
		{
			if(board[x][i] != null)
			{
				if(board[x][i].isSame(board[x][y]))
				{
					System.out.println("here");
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean checkDiagonalNE(int x, int y, int nx, int ny, Piece board[][])
	{
		for(int i = x - 1, j = y + 1; i != nx && j != ny; i--, j++)
		{
			if(board[i][j] != null)
			{
				if(board[i][j].isSame(board[x][y]))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean checkDiagonalNV(int x, int y, int nx, int ny, Piece board[][])
	{
		for(int i = x - 1, j = y - 1; i != nx && j != ny; i--, j--)
		{
			if(board[i][j] != null)
			{
				if(board[i][j].isSame(board[x][y]))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean checkDiagonalSE(int x, int y, int nx, int ny, Piece board[][])
	{
		for(int i = x + 1, j = y + 1; i != nx && j != ny; i++, j++)
		{
			if(board[i][j] != null)
			{
				if(board[i][j].isSame(board[x][y]))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean checkDiagonalSV(int x, int y, int nx, int ny, Piece board[][])
	{
		for(int i = x + 1, j = y - 1; i != nx && j != ny; i++, j--)
		{
			if(board[i][j] != null)
			{
				if(board[i][j].isSame(board[x][y]))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean isValidMove(Move mv)
	{
		int x = mv.x;
		int y = mv.y;
		int nx = mv.nx;
		int ny = mv.ny;
		
		// we can't move an empty cell
		if(board[x][y] == null)
		{
			System.out.println("WARNING! CAN'T MOVE AN EMPTY CELL");
			return false;
		}
		
		// if we try to go on diagonals
		if(x != nx && y != ny)
			// check if the piece can move on diagonals
			if(!board[x][y].can_move_on_diagonals)
				return false;
		
		// if we try to go forward
		if(nx < x && y == ny)
			// check if we can go there
			if(!board[x][y].can_move_forward)
				return false;
		
		// also, make sure we stay in the board region
		if(nx < 0 || ny < 0 || nx >= 8 || ny >= 8)
			return false;
		
		// The following parts starts implementing check logic
		return board[x][y].isValidMove(mv, board);
	}
	
	/*
	 * Will reset the board to an initial state where no move was performed.
	 * TODO: Currently the black pieces are always on top and the white ones on bottom so add
	 *       support for alternating players, that is: white top and black bottom
	 */
	public void initBoard()
	{
		// init the board manually
		board[0][0] = new Rook(black);
		board[0][1] = new Knight(black);
		board[0][2] = new Bishop(black);
		board[0][3] = new Queen(black);
		board[0][4] = new King(black);
		board[0][5] = new Bishop(black);
		board[0][6] = new Knight(black);
		board[0][7] = new Rook(black);
			
		// add the pawns
		for(int i = 0; i < 8; i++)
		{
			board[1][i] = new Pawn(black);
		}
				
		// add empty cells that
		for(int i = 2; i < 6; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				board[i][j] = null;
			}
		}
				
		// add the pawns
		for(int i = 0; i < 8; i++)
		{
			board[6][i] = new Pawn(white);
		}

		board[7][0] = new Rook(white);
		board[7][1] = new Knight(white);
		board[7][2] = new Bishop(white);
		board[7][3] = new King(white);
		board[7][4] = new Queen(white);
		board[7][5] = new Bishop(white);
		board[7][6] = new Knight(white);
		board[7][7] = new Rook(white);
	}
	
	// override the toString method inherited from the Object class
	// to be able to print the board using System.out.println
	@Override
	public String toString()
	{
		String str_rep = "  ";

		for(char c = '0'; c <= '7'; c++)
		{
			str_rep += c + " ";
		}
		str_rep += "\n";
		
		int current_idx = 0;
		for(Piece []prow : board)
		{
			str_rep += current_idx + " ";
			for(Piece piece : prow)
			{
				if(piece != null)
					str_rep += piece.toString() + " ";
				else
					str_rep += "- ";
			}
			str_rep += "\n";
			current_idx++;
		}
		
		str_rep += "  ";
		
		for(char c = '0'; c <= '7'; c++)
		{
			str_rep += c + " ";
		}
		str_rep += "\n";
		
		return str_rep;
	}
}
