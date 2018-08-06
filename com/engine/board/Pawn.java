package com.engine.board;

import com.engine.board.Piece;
import static com.engine.board.PieceValue.*;

final class Pawn extends Piece
{
	Pawn(int color)
	{
		super(PAWN.getValue(), color, PAWN.getMoveCells(), true, true, false, "Pawn");
		move_cells_diagonals = 1;
	}
}
