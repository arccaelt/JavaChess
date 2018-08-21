package Pieces;

import static Board.Board.*;
import static Pieces.PieceValue.*;
import static java.lang.Math.abs;
import java.io.Serializable;
import Board.Move;
import Pieces.Piece;

public final class Pawn extends Piece implements Serializable
{
	public Pawn(int color)
	{
		super(PAWN.getValue(), color, PAWN.getMoveCells(), true, true, false, "Pawn");
		move_cells_diagonals = 1;
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
				if(checkColumnDown(x, y, nx, board))
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
				if(checkColumnUp(x, y, nx, board))
				{
					return false;
				}
			}
		}
		System.out.println("yay");
		return true;
	}
}
