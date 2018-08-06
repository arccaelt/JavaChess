package com.engine.board;

import com.engine.board.Piece;
import static com.engine.board.PieceValue.*;

final class Rook extends Piece
{
	Rook(int color)
	{
		super(ROOK.getValue(), color, ROOK.getMoveCells(), true, false, true, "Rook");
	}
}
