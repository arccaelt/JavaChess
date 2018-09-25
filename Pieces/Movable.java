package Pieces;

/*
 * This interface will be implemented by those pieces class which need to
 * store and manipulate information about movement(were those pieces moved before(
 * Also, indeed all implementing classes of this interface will give mostly the same
 * concrete definition for these methods but i can't implement this as an abstract class
 * because those classes already extend the Piece class.
 * Also, using the 'instanceof' operator i can test if an object of a class implements
 * this interface(so this interface can also be a maker)
 */
public interface Movable
{
	boolean isMoved();
	void setMoved();
}
