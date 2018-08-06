package com.engine.board;

enum PieceValue
{
	PAWN(1, 2), ROOK(2, 100), BISHOP(3, 100), KNIGHT(3, 2), QUEEN(4, 100), KING(5, 1);
	
	private final int value;
	private int moveCells;
	
	PieceValue(int val, int mc)
	{
		moveCells = mc;
		value = val;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public int getMoveCells()
	{
		return moveCells;
	}
}