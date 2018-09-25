package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;
import Board.*;
import Pieces.*;

public class BoardUI 
{
	// The window frame
	private JFrame frame;
	
	// The panel that contains the board
	private JPanel gboard;
	
	// We need a board reference for this to work
	private Board b;
	private final int SQUARE_WIDTH = 120;
	private final int SQUARE_HEIGTH = 120;
	private final Color EVEN_SQUARE_COLOR = Color.WHITE;
	private final Color ODD_SQUARE_COLOR = Color.DARK_GRAY;
	
	// Here we use the ArrayList to keep the selected squares
	private java.util.List<Square> keep = new ArrayList<>();
	
	public BoardUI(Board b)
	{
		this.b = b;
		
		frame = new JFrame();
		frame.setLayout(new GridLayout(1, 2));
		
		JPanel sidepanel = new JPanel();
		sidepanel.setPreferredSize(new Dimension(frame.WIDTH / 10, frame.HEIGHT));
		sidepanel.setLayout(new BoxLayout(sidepanel, BoxLayout.Y_AXIS));
		
		JButton new_game = new JButton("New game");
		new_game.setPreferredSize(new Dimension(50, 50));
		sidepanel.add(new_game);
		
		// create a JPanel which will store the board
		gboard = new JPanel();
		gboard.setLayout(new GridLayout(8, 8));

		HandleSquareSelection handler = new HandleSquareSelection();
		
		int idx = 1;
		for(int i = 0; i < 8; i++)
		{
			idx = (i % 2 == 0) ? 2 : 1; 
			for(int j = 0; j < 8; j++)
			{
				Square l = new Square(i, j, idx - 1, (idx % 2 == 0) ? EVEN_SQUARE_COLOR : ODD_SQUARE_COLOR);
				l.addMouseListener(handler);
				gboard.add(l);
				idx++;
			}
		}
		
		addPiecesIcons();
		
		frame.add(gboard);
		frame.add(sidepanel);
		
		frame.setVisible(true);
		frame.setTitle("JavaChess");
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/*
	 * This method will add the images for the pieces
	 */
	private void addPiecesIcons()
	{
		// white rooks
		Square rook = (Square)gboard.getComponent(0);
		Image im = getPieceIcon("C:\\Users\\arco\\Desktop\\ChessEngine\\src\\res\\white_rook.png");
		rook.piece_im = im;
		rook.has_piece = true;
		
		rook = (Square)gboard.getComponent(7);
		rook.piece_im = im;
		rook.has_piece = true;
		
		// white knights
		Square knight = (Square)gboard.getComponent(1);
		im = getPieceIcon("C:\\Users\\arco\\Desktop\\ChessEngine\\src\\res\\white_knight.png");
		knight.piece_im = im;
		knight.has_piece = true;
		
		knight = (Square)gboard.getComponent(6);
		knight.piece_im = im;
		knight.has_piece = true;
		
		// white bishops
		Square bishop = (Square)gboard.getComponent(2);
		im = getPieceIcon("C:\\Users\\arco\\Desktop\\ChessEngine\\src\\res\\white_bishop.png");
		bishop.piece_im = im;
		bishop.has_piece = true;
		
		bishop = (Square)gboard.getComponent(5);
		bishop.piece_im = im;
		bishop.has_piece = true;
		
		// white queen and king
		Square queen = (Square)gboard.getComponent(3);
		im = getPieceIcon("C:\\Users\\arco\\Desktop\\ChessEngine\\src\\res\\white_queen.png");
		queen.piece_im = im;
		queen.has_piece = true;
		
		Square king = (Square)gboard.getComponent(4);
		im = getPieceIcon("C:\\Users\\arco\\Desktop\\ChessEngine\\src\\res\\white_king.png");
		king.piece_im = im;
		king.has_piece = true;
		
		// white pawns
		for(int i = 8; i <= 15; i++)
		{
			Square sq = (Square)gboard.getComponent(i);
			im = getPieceIcon("C:\\Users\\arco\\Desktop\\ChessEngine\\src\\res\\white_pawn.png");
			
			sq.piece_im = im;
			sq.has_piece = true;
		}
		
		// black pieces
		
		// black pawns
		for(int i = 48; i < 56; i++)
		{
			Square sq = (Square)gboard.getComponent(i);
			im = getPieceIcon("C:\\Users\\arco\\Desktop\\ChessEngine\\src\\res\\black_pawn.png");
					
			sq.piece_im = im;
			sq.has_piece = true;
		}
		
		Square brook = (Square)gboard.getComponent(56);
		im = getPieceIcon("C:\\Users\\arco\\Desktop\\ChessEngine\\src\\res\\black_rook.png");
		brook.piece_im = im;
		brook.has_piece = true;
		
		brook = (Square)gboard.getComponent(63);
		brook.piece_im = im;
		brook.has_piece = true;
		
		// black knights
		Square bknight = (Square)gboard.getComponent(57);
		im = getPieceIcon("C:\\Users\\arco\\Desktop\\ChessEngine\\src\\res\\black_knight.png");
		bknight.piece_im = im;
		bknight.has_piece = true;
		
		bknight = (Square)gboard.getComponent(62);
		bknight.piece_im = im;
		bknight.has_piece = true;
		
		// white bishops
		Square bbishop = (Square)gboard.getComponent(58);
		im = getPieceIcon("C:\\Users\\arco\\Desktop\\ChessEngine\\src\\res\\black_bishop.png");
		bbishop.piece_im = im;
		bbishop.has_piece = true;
		
		bbishop = (Square)gboard.getComponent(61);
		bbishop.piece_im = im;
		bbishop.has_piece = true;
		
		// white queen and king
		Square bqueen = (Square)gboard.getComponent(59);
		im = getPieceIcon("C:\\Users\\arco\\Desktop\\ChessEngine\\src\\res\\black_queen.png");
		bqueen.piece_im = im;
		bqueen.has_piece = true;
		
		Square bking = (Square)gboard.getComponent(60);
		im = getPieceIcon("C:\\Users\\arco\\Desktop\\ChessEngine\\src\\res\\black_king.png");
		bking.piece_im = im;
		bking.has_piece = true;
	}
	
	private Image getPieceIcon(String path)
	{
		return new ImageIcon(path).getImage().getScaledInstance(SQUARE_WIDTH - 10, SQUARE_WIDTH - 10, Image.SCALE_DEFAULT);
	}
	
	/*
	 * This private inner class extends the MouseAdapter class(so that i don't have to implement
	 * the MouseListner interface and write a lot of empty methods) and override the mouseClicked
	 * method so that when the user click on a square it is selected
	 */
	private class HandleSquareSelection extends MouseAdapter
	{
		@Override
		public void mouseClicked(java.awt.event.MouseEvent e)
		{
			// downcast
			Square sq = (Square)e.getSource();
			
			if(sq.is_selected)
				sq.deselect();
			else
			{
				if(keep.size() == 0)
				{
					sq.select();
					
					// add the square in a list of selected squares
					keep.add(sq);
				}
				// When we have 2 squares selected(the current square + the one we have in the arraylist)
				else if(keep.size() == 1)
				{
					Move mv = new Move(keep.get(0).x, keep.get(0).y, sq.x, sq.y);
					
					// try to make the move
					boolean result = b.move(mv);
					
					// If the move was made
					if(result)
					{
						// update the pieces
						Square source = keep.get(0);
						sq.has_piece = true;
						sq.piece_im = source.piece_im;
						
						source.piece_im = null;
						source.has_piece = false;
						
						// repaint them(this triggers/calls the overriden paintComponent method
						sq.repaint();
						source.repaint();
					}
					
					// clear all selected squares and free the
					keep.clear();
					for(int i = 0; i < gboard.getComponentCount(); i++)
						((Square)gboard.getComponent(i)).deselect();
					
				}
				else
				{
					System.out.print("too many squares selected");
				}
			}

			sq.repaint();
		}
	}
}
