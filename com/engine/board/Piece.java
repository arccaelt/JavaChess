package com.engine.board;

class Piece
{
	// NOTE: I didn't used any access specifier(public/private/protected) because
	// i want these fields to be accessed only by the classes/subclasses in the current package
	final int value;
	final int color;
	final String name;
	
	// NOTE: This will be modified by the pawn piece
	// TODO: FIND A BETTER WAY TO HANDLE THIS
	int move_cells; // how many cells we can move 
	int move_cells_diagonals = 100; // how many cells we can move on diagonals
	final boolean can_move_on_diagonals;
	final boolean can_move_backwards;
	final boolean can_move_forward;
	
	// added solely for knowing if a pawn was moved
	// or not. TODO: Find a better way to handle this
	boolean was_moved = false;
	
	public Piece(int val, int col, int mc, boolean cmf, boolean cmd, boolean cmb, String nm)
	{
		value = val;
		color = col;
		name = nm;
		move_cells = mc;
		can_move_backwards = cmb;
		can_move_forward = cmf;
		can_move_on_diagonals = cmd;
	}

	final boolean isSame(Piece p)
	{
		return color == p.color;
	}
	
	final char getChar()
	{
		if(name.equals("Knight"))
			return name.charAt(1);
		return name.charAt(0);
	}
	
	// overload the toString methods inherited from the Object
	// class(from which all objects are created) to be able to print
	// this piece using System.out.println
	public final String toString()
	{
		// black pieces will be displayed in lowercase
		if(color == 0)
		{
			if(name.equals("Knight"))
				return String.valueOf(name.charAt(1)).toLowerCase();
			return String.valueOf(name.charAt(0)).toLowerCase();
		}
		else
		{
			if(name.equals("Knight"))
				return String.valueOf(name.charAt(1)).toUpperCase();
			return String.valueOf(name.charAt(0)).toUpperCase();
		}
	}
}