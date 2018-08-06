package com.engine.board;

import com.engine.board.Piece;
import static com.engine.board.PieceValue.*;

class King extends Piece
{
	King(int color)
	{
		super(KING.getValue(), color, KING.getMoveCells(), true, true, true, "King");
	}
}
