package Pieces;

import static Board.Board.*;
import static Pieces.PieceValue.*;
import static java.lang.Math.abs;

import java.io.Serializable;

import Board.Move;
import Pieces.Piece;

public final class Knight extends Piece implements Serializable
{
	public Knight(int color)
	{
		super(KNIGHT.getValue(), color, KNIGHT.getMoveCells(), true, true, true, "Knight");
	}

	public boolean isValidMove(Move mv, Piece board[][])
	{
		int x = mv.x;
		int y = mv.y;
		int nx = mv.nx;
		int ny = mv.ny;
		
		int distance_x = abs(x - nx);
		int distance_y = abs(y - ny);
		
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
		return true;
	}
}
