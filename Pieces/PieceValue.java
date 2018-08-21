package Pieces;

/*
 * I used an enumeration to store a bunch of enumeration objects that 
 * coresponds to individual chess pieces, like: pawn, rook, etc.
 * NOTE: Each enumeration constant will have its own copy of the fields defined
 * here and the constructor will be called for each enumeration object when it is constructed
 */
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