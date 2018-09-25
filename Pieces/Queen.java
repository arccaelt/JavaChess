package Pieces;

import static Board.Board.*;
import static Pieces.PieceData.*;
import static java.lang.Math.abs;

import java.io.Serializable;

import Board.Move;
import Pieces.Piece;

public final class Queen extends Piece implements Serializable
{
	public Queen(int color)
	{
		super(color, PieceData.BISHOP);
	}
	
	public boolean isValidMove(Move mv, Piece board[][])
	{
		int x = mv.x;
		int y = mv.y;
		int nx = mv.nx;
		int ny = mv.ny;
		
		int distance_x = abs(x - nx);
		int distance_y = abs(y - ny);
		
		// handle diagonal movement
		if(x != nx && y != ny)
		{
			if(nx < x && ny > y)
			{
				if(checkDiagonalNE(x, y, nx, ny, board))
				{
					return false;
				}
			}
			else if(nx < x && ny < y)
			{
				if(checkDiagonalNV(x, y, nx, ny, board))
				{
					return false;
				}
			}
			else if(nx > x && ny > y)
			{
				if(checkDiagonalSE(x, y, nx, ny, board))
				{
					return false;
				}
			}
			else if(nx > x && ny < y)
			{
				if(checkDiagonalSV(x, y, nx, ny, board))
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
		}
						
		return true;
	}
}