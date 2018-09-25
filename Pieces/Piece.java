package Pieces;

import java.io.Serializable;

import Board.Move;

public abstract class Piece implements Serializable
{
	public final int color;
	public PieceData info;
	
	public Piece(int color, PieceData d)
	{
		this.color = color;
		info = d;
	}

	/*
	 * Override the equals method
	 */
	
	@Override
	public boolean equals(Object p)
	{
		if(p != null)
		{
			// downcast
			Piece other = (Piece)p;
			return color == other.color;
		}
		return false;
	}
	
	public final char getChar()
	{
		if(info.name.equals("Knight"))
			return info.name.charAt(1);
		return info.name.charAt(0);
	}
	
	public abstract boolean isValidMove(Move mv, Piece board[][]);
	
	// define a concrete implementation here and let
	// the implementing classes if they want to use it or not
	boolean isAttacked(int nx, int ny, Piece board[][])
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
			if(info.name.equals("Knight"))
				return String.valueOf(info.name.charAt(1)).toLowerCase();
			return String.valueOf(info.name.charAt(0)).toLowerCase();
		}
		else
		{
			if(info.name.equals("Knight"))
				return String.valueOf(info.name.charAt(1)).toUpperCase();
			return String.valueOf(info.name.charAt(0)).toUpperCase();
		}
	}
}