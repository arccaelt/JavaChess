package Pieces;

/*
 * I used an enumeration to store a bunch of enumeration objects that 
 * coresponds to individual chess pieces, like: pawn, rook, etc.
 * NOTE: Each enumeration constant will have its own copy of the fields defined
 * here and the constructor will be called for each enumeration object when it is constructed
 */
public enum PieceData
{
	PAWN(1, 2, "Pawn", 1, true, false, true), 
	ROOK(2, 100, "Rook", 0, false, true, true),
	BISHOP(3, 100, "Bishop", 100, true, false, false),
	KNIGHT(3, 2, "Knight", 2, true, false, false), 
	QUEEN(4, 100, "Quuen", 100, true, true, true), 
	KING(5, 1, "King", 1, true, true, true);
	
	public final int value;
	public int move_cells;
	public final String name;
	public final int move_cells_diagonals; // how many cells we can move on diagonals
	public final boolean can_move_on_diagonals;
	public final boolean can_move_backwards;
	public final boolean can_move_forward;
	
	PieceData(int val, int mc, String name, int move_diag, boolean cmd, boolean cmb, boolean cmf)
	{
		move_cells = mc;
		value = val;
		this.name= name;
		move_cells_diagonals = move_diag;
		can_move_backwards = cmb;
		can_move_forward = cmf;
		can_move_on_diagonals = cmd;
	}
}