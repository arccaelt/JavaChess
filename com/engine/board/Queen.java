package com.engine.board;

import com.engine.board.Piece;
import static com.engine.board.PieceValue.*;

final class Queen extends Piece
{
	Queen(int color)
	{
		super(QUEEN.getValue(), color, QUEEN.getMoveCells(), true, true, true, "Queen");
	}
}

