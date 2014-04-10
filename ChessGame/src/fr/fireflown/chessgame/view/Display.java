package fr.fireflown.chessgame.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import javax.swing.JPanel;

import fr.fireflown.chessgame.model.boards.BaseBoard;
import fr.fireflown.chessgame.model.game.Coord3D;
import fr.fireflown.chessgame.model.game.WorldInformation;
import fr.fireflown.chessgame.model.pieces.BasePiece;

/**
 * Class used to display a graphical representation of the chess game
 */
public class Display extends JPanel implements MouseMotionListener, MouseListener {
	/**
	 * Serial ID used by Swing to serialize the object
	 */
	private static final long serialVersionUID = 1L;
	
	// Hold the current layer to display
	int currentHeight;
	
	// A reference on the information of the chess game
	WorldInformation worldInfo;
	
	// A list of all graphical board and piece to display
	LinkedList<BoardUI> boardList;
	LinkedList<PieceUI> pieceList;
	
	// 3D position of the mouse
	Coord3D mousePosition;
	
	// 3D position of the last square selected
	Coord3D oldSelectedCase;
	// 3D position of the current square selected
	Coord3D selectedCase;
	// Define if a square has been selected or not
	boolean caseSelected;
	
	// A list of coordinate to show the selected piece next possible moves
	LinkedList<Coord3D> selectedPiecePossibleMoves;
	
	// Default constructor, worldInfo are the information on the world we want to display
	public Display(WorldInformation worldInfo) {
		// Initializing the lists
		boardList = new LinkedList<BoardUI>();
		pieceList = new LinkedList<PieceUI>();
		
		// Holding the world informations
		this.worldInfo = worldInfo;
		// For each logical board in the world information we create a graphical board
		for(BaseBoard board : worldInfo.getBoardsList()) {
			boardList.add(new BoardUI(board));
		}
		
		// Same for the pieces
		for(BasePiece piece : worldInfo.getPiecesList()) {
			PieceUI p = new PieceUI(piece);
			// Debug line to check the pieces that are added
			System.out.println(p);
			pieceList.add(p);
			p.loadImage("chess.bmp");
		}
		
		// We set the current view to layer 1
		currentHeight = 1;
		
		mousePosition = new Coord3D();
		selectedCase = new Coord3D();
		selectedPiecePossibleMoves = new LinkedList<Coord3D>();
		
		// Telling swing that this object want to receive the mouse event (motion and action)
		addMouseMotionListener(this);
		addMouseListener(this);
		
	}
	
	// Set the selected piece possible moves to coords, make the display able to show them
	public void setSelectedPiecePossibleMoves(LinkedList<Coord3D> coords) {
		if(coords == null)
			return;
		
		selectedPiecePossibleMoves.clear();
		selectedPiecePossibleMoves.addAll(coords);
	}
	
	// Return the coordinate of the selected square
	public Coord3D getSelectedCase() {
		return selectedCase;
	}
	
	// Check if a square is selected
	public boolean isCaseSelected() {
		return caseSelected;
	}
	
	// Inherited from JPanel, draw the chess game on the panel
	@Override
	public void paintComponent(Graphics g) {
		// First we clear the screen in white
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getSize().width, this.getSize().height);
		
		
		// Then for each layer strictly bellow the current one we draw the boards, then the pieces
		for(int i=0 ; i<currentHeight ; ++i) {
			for(BoardUI board : boardList) {
				if(board.getBoardHeight() == i) { // Test the board height
					board.Draw(g, 255*(i+1)/(currentHeight+1));
				}
			}
			
			for(PieceUI piece : pieceList) {
				if(piece.getHeight() == i) { // Test the piece height
					piece.Draw(g, 255*(i+1)/(currentHeight+1));
				}
			}
		}
		
		// Drawing the boards at the current height
		for(BoardUI board : boardList) {
			if(board.getBoardHeight() == currentHeight) {
				board.Draw(g);
			}
		}
		
		// Drawing the possible move of the selected pieces
		for(Coord3D coord : selectedPiecePossibleMoves) {
			if(coord.y == currentHeight)
				g.fillRect(coord.x * BoardUI.squareSize, coord.z*BoardUI.squareSize, BoardUI.squareSize, BoardUI.squareSize);
		}
		
		/* Drawing the squares hovered by the mouse */
		g.setColor(new Color(255, 255, 0, 125));
		g.fillRect(mousePosition.x*BoardUI.squareSize, mousePosition.z*BoardUI.squareSize, BoardUI.squareSize, BoardUI.squareSize);
	
		// I really don't know what I did there... But it sure is useful !
		if(caseSelected && currentHeight >= selectedCase.y)  {
			// I tried to give a fading effect to the drawn squares by playing on the saturation of the colour
			g.setColor(new Color(0, 255*(selectedCase.y+1)/(currentHeight+1), 0, 175));
			// Drawing the selected square at the good position
			g.fillRect(selectedCase.x*BoardUI.squareSize, selectedCase.z*BoardUI.squareSize, BoardUI.squareSize, BoardUI.squareSize);
			// If the selected square is occupied by a non dead and non null piece then we draw it's possible moves at the current time
			BasePiece p = worldInfo.getPieceAtCoord(selectedCase);
			if(p != null && !p.isDead()) {
				// Making some foreach for fun
				for(Coord3D c : worldInfo.getAllReachablePoint(p)) {
					if(c.y <= currentHeight) {
						if(worldInfo.getPieceAtCoord(c) != null) {
							g.setColor(new Color(255, 0, 0, 175));
						} else {
							g.setColor(new Color(0, 255*(c.y+1)/(currentHeight+1), 0, 175));
						}
						g.fillRect(c.x*BoardUI.squareSize, c.z*BoardUI.squareSize, BoardUI.squareSize, BoardUI.squareSize);
					}
				}
			}
		}
		
		// Drawing every pieces that are at the current layer
		for(PieceUI piece : pieceList) {
			if(piece.getHeight() == currentHeight) { // Test the piece height
				piece.Draw(g);
			}
		}
	}
	
	// Return the current height of the view
	public int getCurrentHeight() {
		return currentHeight;
	}
	
	// Disable the selection, we won't see the selected square anymore
	public void disableSelection() {
		caseSelected = false;
	}
	
	// Go up a layer in the view
	public void increaseHeight() {
		if(currentHeight<worldInfo.getMaxHeight())
			++currentHeight;
	}
	
	// Go down a layer in the view
	public void decreaseHeight() {
		if(currentHeight>0)
			--currentHeight;
	}

	// Required function by the interface MouseMotionListener
	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	// Catch the mouse moved event and update the relative mouse position
	@Override
	public void mouseMoved(MouseEvent arg0) {
		mousePosition.x = arg0.getPoint().x / BoardUI.squareSize;
		mousePosition.z = arg0.getPoint().y / BoardUI.squareSize;
	}
	
	// Getter for the old selected square
	public Coord3D getOldSelectedCase() {
		return oldSelectedCase;
	}

	// Catch the mouse clicked event and update the selected square
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// Get the position of the mouse relative to the board square
		int x=arg0.getPoint().x / BoardUI.squareSize;
		int y=arg0.getPoint().y / BoardUI.squareSize;
		
		// If we click at the same place as the hold selected square we disable square selection
		if(x == selectedCase.x && y == selectedCase.z && currentHeight == selectedCase.y) {
			oldSelectedCase = selectedCase; // I think I went full retard here, maybe copy the field instead of the reference, need further tests
			caseSelected = !caseSelected;
		} else { //
			if(worldInfo.getBoardAtCoord(x, currentHeight, y) != null) {
				oldSelectedCase = selectedCase; // Same as above, I went full retard twice. Shame on me !
				selectedCase.x = x;
				selectedCase.z = y;
				selectedCase.y = currentHeight;
				caseSelected = true;
			}
		}
	}

	// Required function by the interface MouseMotionListener
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	// Required function by the interface MouseMotionListener
	@Override
	public void mouseExited(MouseEvent arg0) {		
	}

	// Required function by the interface MouseListener
	@Override
	public void mousePressed(MouseEvent arg0) {		
	}

	// Required function by the interface MouseListener
	@Override
	public void mouseReleased(MouseEvent arg0) {		
	}
}
