package com.engine.board;

import com.engine.board.Piece;
import static com.engine.board.PieceValue.*;

final class Knight extends Piece
{
	Knight(int color)
	{
		super(KNIGHT.getValue(), color, KNIGHT.getMoveCells(), true, true, true, "Knight");
	}
}
