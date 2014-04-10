package fr.fireflown.chessgame.view;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.fireflown.chessgame.controller.PlayerColor;
import fr.fireflown.chessgame.model.pieces.BasePiece;

/*
 * This class represent the view side of a chess piece
 */
public class PieceUI {
	// Define the length of the piece
	private static final int squareSize=BoardUI.squareSize;
	// A reference on the piece to display
	private BasePiece piece;
	// The image which will represent the piece
	private Image image;
	
	// The X index of this piece sprite on the sprite sheet
	private int XIndex;
	// The Y index of this piece sprite on the sprite sheet
	private int YIndex;
	
	// Default constructor
	public PieceUI(BasePiece piece) {
		// Hold a reference on the piece
		this.piece = piece;
		// Set the index relative to the piece name
		setIndex(piece.getName());
	}
	
	public void setIndex(String pieceName) {
		
		// We use the name of the piece to retrieve the X,Y index on the image
		// On the X axis we have the piece
		// On the Y axis we have the colour
		if(pieceName.equals("Pawn")) {
			XIndex = 5;
		} else if(pieceName.equals("Knight")) {
			XIndex = 1;
		} else if(pieceName.equals("Bishop")) {
			XIndex = 4;
		} else if(pieceName.equals("Queen")) {
			XIndex = 3;
		} else if(pieceName.equals("Tower")) {
			XIndex = 0;
		} else if(pieceName.equals("King")) {
			XIndex = 2;
		} else {
			XIndex = 0;
		}
		
		if(piece.getOwner().getColor() == PlayerColor.BLACK) {
			YIndex = 0;
		} else {
			YIndex = 1;
		}
	}
	
	// Load the sprite sheet and cut the part we need (not memory efficient)
	public boolean loadImage(String image) {
		try {
			// We load the full image
			BufferedImage fullImage = ImageIO.read(new File("ressources/chess.bmp"));
			
			// Using the index we cut the full image into small image of the piece
			this.image = fullImage.getSubimage(XIndex*20, YIndex*20, 20, 20);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// short cut for the height of the piece pointed by this view, useless
	public int getHeight() {
		return piece.getY();
	}
	
	// Draw the piece on the specified graphics object with the specified transparency (used to simulated the depth)
	public void Draw(Graphics g, int transparency) {
		if(!piece.isDead())
			g.drawImage(image, (piece.getX()*squareSize), (piece.getZ()*squareSize), squareSize, squareSize, null, null);
	}
	
	// Draw the piece on the specified graphics object without transparency
	public void Draw(Graphics g) {
		// Affiche l'image au coordonnée x, y, multiplié par squareSize et ajouter a globalX, globalY
		Draw(g, 255);
	}
}
