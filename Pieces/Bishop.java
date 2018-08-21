package Pieces;

import static Pieces.PieceValue.*;
import static java.lang.Math.abs;
import static Board.Board.*;

import java.io.Serializable;

import Board.Move;
import Pieces.Piece;

public class Bishop extends Piece implements Serializable
{
	public Bishop(int color)
	{
		super(BISHOP.getValue(), color, BISHOP.getMoveCells(), false, true, true, "Bishop");
	}
	
	public boolean isValidMove(Move mv, Piece board[][])
	{
		int x = mv.x;
		int y = mv.y;
		int nx = mv.nx;
		int ny = mv.ny;
		
		int distance_x = abs(x - nx);
		int distance_y = abs(y - ny);
		
		if(x == nx || y == ny || distance_x != distance_y)
		{
			return false;
		}
		
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
		
		return true;
	}
	
	public boolean isAttacked()
	{
		return false;
	}
}