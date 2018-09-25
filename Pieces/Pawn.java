package Pieces;

import static Board.Board.*;
import static Pieces.PieceData.*;
import static java.lang.Math.abs;
import java.io.Serializable;
import Board.Move;
import Pieces.Piece;

public final class Pawn extends Piece implements Serializable, Movable
{
	public boolean was_moved;
	public Pawn(int color)
	{
		super(color, PieceData.PAWN);
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
		
		// If we try to move the pawn on diagonals then this is possible
		// only if we capture an enemy piece
		if(x != nx && y != ny)
		{
			if(board[nx][ny] == null || board[nx][ny].equals(board[x][y]))
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
				if(nx < x || ny != y || distance_x > board[x][y].info.move_cells)
				{
					return false;
				}
				if(checkColumnDown(x, y, nx, board))
				{
					return false;
				}
			}
			else
			{
				if(nx > x || ny != y || distance_x > board[x][y].info.move_cells)
				{
					return false;
				}
				if(checkColumnUp(x, y, nx, board))
				{
					return false;
				}
			}
		}
		
		return true;
	}
}
