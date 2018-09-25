package Pieces;

import static Pieces.PieceData.*;
import static java.lang.Math.abs;
import static Board.Board.*;

import java.io.Serializable;

import Board.Move;
import Pieces.Piece;

public class Bishop extends Piece implements Serializable
{
	public Bishop(int color)
	{
		super(color, PieceData.BISHOP);
	}
	
	public boolean isValidMove(Move mv, Piece board[][])
	{
		int x = mv.x;
		int y = mv.y;
		int nx = mv.nx;
		int ny = mv.ny;
		
		if(x == nx || y == ny)
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