package com.engine.board;

import com.engine.board.Piece;
import static com.engine.board.PieceValue.*;

class Bishop extends Piece
{
	Bishop(int color)
	{
		super(BISHOP.getValue(), color, BISHOP.getMoveCells(), false, true, true, "Bishop");
	}
}
