package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;

import com.sun.javafx.geom.Rectangle;

/*
 * We extend the JPanel class so that a Square will be a Panel with a Image on it
 * Also, a square has to keep track of its:
 * 	- index in the component list
 *  - index on the board
 *  - if it is empty(no pieces on it)
 *  - if it is selected
 */
public class Square extends JPanel
{
	private final int SQUARE_WIDTH = 150;
	private final int SQUARE_HEIGHT = 150;
	public final Color bgcolor;
	
	// board and gboard idx
	public int x, y;
	public int gidx;
	
	public boolean is_selected;
	public boolean has_piece;
	public Image piece_im;
	
	Square(int x, int y, int gidx, Color bg)
	{
		this.gidx = gidx;
		this.x = x;
		this.y = y;
		bgcolor = bg;
	}
	
	void select()
	{
		is_selected = true;
		setBackground(Color.BLUE);
	}

	void deselect()
	{
		is_selected = false;
		setBackground(bgcolor);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		if(has_piece)
		{
			g.drawImage(piece_im, 0, 0, null);
		}
		
		if(is_selected)
			setBackground(Color.red);
		else
			setBackground(bgcolor);
	}
	
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(SQUARE_WIDTH, SQUARE_HEIGHT);
	}
}
