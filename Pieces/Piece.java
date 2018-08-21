package Pieces;

import java.io.Serializable;

import Board.Move;

public abstract class Piece implements Serializable
{
	// NOTE: I didn't used any access specifier(public/private/protected) because
	// i want these fields to be accessed only by the classes/subclasses in the current package
	public final int value;
	public final int color;
	public final String name;
	
	// NOTE: This will be modified by the pawn piece
	// TODO: FIND A BETTER WAY TO HANDLE THIS
	public int move_cells; // how many cells we can move 
	public int move_cells_diagonals; // how many cells we can move on diagonals
	public final boolean can_move_on_diagonals;
	public final boolean can_move_backwards;
	public final boolean can_move_forward;

	// Needed for King and Pawn pieces
	public boolean was_moved = false;
	
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

	public final boolean isSame(Piece p)
	{
		return color == p.color;
	}
	
	public final char getChar()
	{
		if(name.equals("Knight"))
			return name.charAt(1);
		return name.charAt(0);
	}
	
	public abstract boolean isValidMove(Move mv, Piece board[][]);
	
	// define a concrete implementation here and let
	// the implementing classes if they want to use it or not
	public boolean isAttacked(int nx, int ny, Piece board[][])
	{
		return false;
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