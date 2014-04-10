package fr.fireflown.chessgame.view;

import java.awt.Color;
import java.awt.Graphics;

import fr.fireflown.chessgame.model.boards.BaseBoard;

// A graphic representation of a chess board
public class BoardUI {
	// Define the length of a square
	public static final int squareSize=40;
	// Reference to the board represented by this object
	BaseBoard board;
	
	// Default constructor
	public BoardUI(BaseBoard board) {
		// Holding a reference on the board
		this.board = board;
	}
	
	// Draw the board on the specified graphical object without transparency
	public void Draw(Graphics g) {
		Draw(g, 255);
	}
	
	// Draw the board on the specified graphical object with transparency
	public void Draw(Graphics g, int transparency) {
		
		// The board is composed of height * width square, so we loop to draw each one of them
		for(int y=0 ; y<board.getHeight() ; ++y) {
			for(int x=0 ; x<board.getWidth(); ++x) {
				// One square over two is black and the other is white
				g.setColor(x%2==0 ? y%2==0 ? new Color(255, 255, 255, transparency) : new Color(0, 0, 0, transparency) : y%2==1 ? new Color(255, 255, 255, transparency) : new Color(0, 0, 0, transparency));
				// Drawing the square at the correct position
				g.fillRect((board.getX()+x)*squareSize, (board.getZ()+y)*squareSize, squareSize, squareSize);
			}
		}
		
		// We draw the border of the board in black and at the correct position
		g.setColor(new Color(0, 0, 0, transparency));
		g.drawRect(board.getX()*squareSize, board.getZ()*squareSize, board.getWidth()*squareSize, board.getHeight()*squareSize);
	}
	
	// Draw the board on the specified graphical object at the specified position
	public void Draw(Graphics g, int x, int y) {
		for(int k=0 ; k<board.getHeight() ; ++k) {
			for(int l=0 ; l<board.getWidth(); ++l) {
				g.setColor(l%2==0 ? k%2==0 ? Color.white : Color.BLACK : k%2==1 ? Color.white : Color.BLACK);
				g.fillRect((x+board.getX()+l)*squareSize, (y+board.getZ()+k)*squareSize, squareSize, squareSize);
			}
		}
	}
	
	// Return the height of the board
	public int getBoardHeight() {
		return board.getY();
	}
	
	// Return the logical board
	public BaseBoard getBoard() {
		return board;
	}
}
