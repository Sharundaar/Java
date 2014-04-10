package fr.fireflown.chessgame.view;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;

import fr.fireflown.chessgame.model.boards.BaseBoard;
import fr.fireflown.chessgame.model.game.WorldInformation;
import fr.fireflown.chessgame.model.pieces.BasePiece;

/*
 * An other kind of display... I think I wanted to make a minimap with this one
 * To find a complete commentary see Display class
 */
public class GlobalDisplay extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5875997918033114281L;

	WorldInformation worldInfo;
	
	LinkedList<BoardUI> boardList;
	LinkedList<PieceUI> pieceList;
	
	public GlobalDisplay(WorldInformation worldInfo) {
		boardList = new LinkedList<BoardUI>();
		
		this.worldInfo = worldInfo;
		for(BaseBoard board : worldInfo.getBoardsList()) {
			boardList.add(new BoardUI(board));
		}
		
		for(BasePiece piece : worldInfo.getPiecesList()) {
			pieceList.add(new PieceUI(piece));
		}
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, this.getSize().width, this.getSize().height);
		
		for(BoardUI board : boardList) {
			board.Draw(g, 0, 1);
		}
		
		/*
		for(PieceUI piece : pieceList) {
			if(piece.getHeight() == currentHeight) { // Test the piece height
				piece.Draw(g);
			}
		}
		*/
	}
}
