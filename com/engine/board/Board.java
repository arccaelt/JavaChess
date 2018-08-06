package com.engine.board;

import static java.lang.Math.*;

public final class Board {
	// TODO: Use bitboards
	// Here we use the runtime polymorhism in Java.
	// Basically, we define a vector of vectors of variables that are
	// able to referece objects of the superclass 'Piece' and we use
	// these variables to also reference objects of its subclasses.
	// NOTE: If the subclasses adds something new that is not defined
	// in the superclass, i won't be able to access those new thing
	// thru these variables
	private Piece board[][] = null;
	
	private final int black = 0;
	private final int white = 1;
	
	public Board()
	{
		// init the board
		board = new Piece[8][8];
		
		resetBoard();
	}
	
	public boolean move(int x, int y, int nx, int ny)
	{
		if(isValidMove(x, y, nx, ny))
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
		return true;
	}
	
	private boolean checkColumnUp(int x, int y, int nx)
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
	
	private boolean checkColumnDown(int x, int y, int nx)
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
	
	private boolean checkLineLeft(int x, int y, int ny)
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
	
	private boolean checkLineRight(int x, int y, int ny)
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
	
	private boolean checkDiagonalNE(int x, int y, int nx, int ny)
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
	
	private boolean checkDiagonalNV(int x, int y, int nx, int ny)
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
	
	private boolean checkDiagonalSE(int x, int y, int nx, int ny)
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
	
	private boolean checkDiagonalSV(int x, int y, int nx, int ny)
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
	
	// NOTE: Even tho the board has a instance of a King object in it
	// that instance is being referenced by a variable of its superclass, 'Piece'
	// and because of that, thru that reference i can access only the methods and fields
	// defined in the Piece class and not the unique stuff i've added here.
	// The magic of upcasting....
	public boolean isKingInCheck(int x, int y)
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
	
	public boolean isCheckMate()
	{
		return false;
	}
	
	private boolean isValidMove(int x, int y, int nx, int ny)
	{
		int distance_x = abs(x - nx);
		int distance_y = abs(y - ny);
		
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
		
		// we can't move an empty cell
		if(board[x][y] == null)
		{
			System.out.println("WARNING! CAN'T MOVE AN EMPTY CELL");
			return false;
		}
		// The following parts starts implementing check logic
		switch(board[x][y].getChar())
		{
		case 'p':
		case 'P':			
			// If we try to move the pawn on diagonals then this is possible
			// only if we capture an enemy piece
			if(x != nx && y != ny)
			{
				if(board[nx][ny] == null || board[nx][ny].isSame(board[x][y]))
				{
					return false;
				}
				if(distance_x > 1 || distance_y > 1)
				{
					return false;
				}
			}
			else
			{
				if(board[x][y].color == black)
				{
					if(nx < x || ny != y || distance_x > board[x][y].move_cells)
					{
						return false;
					}
					if(checkColumnDown(x, y, nx))
					{
						return false;
					}
				}
				else
				{
					if(nx > x || ny != y || distance_x > board[x][y].move_cells)
					{
						return false;
					}
					if(checkColumnUp(x, y, nx))
					{
						return false;
					}
				}
			}
			

			
			break;
		case 'r':
		case 'R':
			// if we try to go sideways(on diagonals) then report an error
			if(x != nx && y != ny)
			{
				return false;
			}
			
			// if this isn't the case then check if we try to move on lines or columns
			// and if we can move there
			if(x == nx) // we stay on the same line
			{
				if(ny > y)
				{
					if(checkLineRight(x, y, ny))
					{
						return false;
					}
				}
				else
				{
					if(checkLineLeft(x, y, ny))
					{
						return false;
					}
				}
			}
			else
			{
				if(nx > x)
				{
					if(checkColumnUp(x, y, nx))
					{
						return false;
					}
				}
				else
				{
					if(checkColumnDown(x, y, nx))
					{
						return false;
					}
				}
			}
			
			break;
		case 'q':
		case 'Q':
			// handle diagonal movement
			if(x != nx && y != ny)
			{
				if(nx < x && ny > y)
				{
					if(checkDiagonalNE(x, y, nx, ny))
					{
						return false;
					}
				}
				else if(nx < x && ny < y)
				{
					if(checkDiagonalNV(x, y, nx, ny))
					{
						return false;
					}
				}
				else if(nx > x && ny > y)
				{
					if(checkDiagonalSE(x, y, nx, ny))
					{
						return false;
					}
				}
				else if(nx > x && ny < y)
				{
					if(checkDiagonalSV(x, y, nx, ny))
					{
						return false;
					}
				}
			}
			
			// check movement on lines/columns
			if(x == nx || y == ny)
			{
				if(x == nx)
				{
					if(ny > y)
					{
						if(checkLineRight(x, y, ny))
						{
							return false;
						}
					}
					else
					{
						if(checkLineLeft(x, y, ny))
						{
							return false;
						}
					}
				}
				else
				{
					if(nx > x)
					{
						if(checkColumnUp(x, y, nx))
						{
							return false;
						}
					}
					else
					{
						if(checkColumnDown(x, y, nx))
						{
							return false;
						}
					}
				}
			}
			
			break;
		case 'k':
		case 'K':
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
			if(isKingInCheck(nx, ny))
			{
				System.out.println("king is in check there");
				board[x][y] = board[nx][ny];
				board[nx][ny] = null;
				return false;
			}
			board[x][y] = board[nx][ny];
			board[nx][ny] = null;
			
			break;
		case 'n':
		case 'N':
			if(distance_x == 1 && distance_y == 1 || distance_x == distance_y)
			{
				return false;
			}
			
			if(x == nx || y == ny)
			{
				return false;
			}
			
			if(distance_x > board[x][y].move_cells || distance_y > board[x][y].move_cells)
			{
				return false;
			}
			
			if(board[nx][ny] != null)
			{
				if(board[nx][ny].isSame(board[x][y]))
				{
					return false;
				}
			}
			
			break;
		case 'b':
		case 'B':
			if(x == nx || y == ny || distance_x != distance_y)
			{
				return false;
			}
			
			// handle diagonal movement
			if(x != nx && y != ny)
			{
				if(nx < x && ny > y)
				{
					if(checkDiagonalNE(x, y, nx, ny))
					{
						return false;
					}
				}
				else if(nx < x && ny < y)
				{
					if(checkDiagonalNV(x, y, nx, ny))
					{
						return false;
					}
				}
				else if(nx > x && ny > y)
				{
					if(checkDiagonalSE(x, y, nx, ny))
					{
						return false;
					}
				}
				else if(nx > x && ny < y)
				{
					if(checkDiagonalSV(x, y, nx, ny))
					{
						return false;
					}
				}
			}			
			
			break;
		default:
			System.out.println("Unknown piece");
		}
		
		return true;
	}
	
	public void resetBoard()
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
