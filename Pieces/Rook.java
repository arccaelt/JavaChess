package Pieces;

import static Board.Board.*;
import static Pieces.PieceValue.*;
import static java.lang.Math.abs;

import java.io.Serializable;

import Board.Move;
import Pieces.Piece;

public final class Rook extends Piece implements Serializable
{
	public Rook(int color)
	{
		super(ROOK.getValue(), color, ROOK.getMoveCells(), true, false, true, "Rook");
	}
	
	public boolean isValidMove(Move mv, Piece board[][])
	{
		int x = mv.x;
		int y = mv.y;
		int nx = mv.nx;
		int ny = mv.ny;
		
		int distance_x = abs(x - nx);
		int distance_y = abs(y - ny);
		
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
				if(checkLineRight(x, y, ny, board))
				{
					return false;
				}
			}
			else
			{
				if(checkLineLeft(x, y, ny, board))
				{
					return false;
				}
			}
		}
		else
		{
			if(nx > x)
			{
				if(checkColumnUp(x, y, nx, board))
				{
					return false;
				}
			}
			else
			{
				if(checkColumnDown(x, y, nx, board))
				{
					return false;
				}
			}
		}		
		
		return true;
	}
}
