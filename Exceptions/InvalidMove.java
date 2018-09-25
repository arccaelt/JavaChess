package Exceptions;

/*
 * This exception class should abstract the concept of an invalid move
 * in a chess game
 * Also it is an unchecked exception because it extends the RuntimeException
 * class which is also unchecked
 * REMIDER: Checked exceptions are those types of exceptions that 
 */
public class InvalidMove extends Exception
{
	public InvalidMove(String message)
	{
		super(message);
	}
}
