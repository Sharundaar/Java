package fr.fireflown.chessgame.controller;

import java.util.LinkedList;

import fr.fireflown.chessgame.model.game.ChessGame;
import fr.fireflown.chessgame.model.game.Coord3D;
import fr.fireflown.chessgame.model.game.WorldInformation;
import fr.fireflown.chessgame.model.pieces.BasePiece;
import fr.fireflown.chessgame.model.pieces.Move;
import fr.fireflown.chessgame.view.Display;

public class HumanPlayer implements IPlayer {
	
	private Display display;
	private WorldInformation worldInfo;
	ChessGame chessGame;
	
	BasePiece selectedPiece;
	Move selectedMove;
	
	PlayerColor color;
	
	LinkedList<BasePiece> ownedPiece;
	
	public boolean isPieceSelected;
	public boolean isMoveSelected;
	
	public HumanPlayer(Display display, PlayerColor color) {
		this.display = display;
		this.color = color;
		ownedPiece = new LinkedList<BasePiece>();
		isPieceSelected = false;
		isMoveSelected = false;
	}
	
	public BasePiece selectPiece() {
		// TODO Auto-generated method stub
		if(isPieceSelected == false) {
			isPieceSelected = false;
			isMoveSelected = false;
			
			if(display.isCaseSelected()) {
				BasePiece piece = worldInfo.getPieceAtCoord(display.getSelectedCase());
				if(piece != null && piece.getOwner() == this) {
					selectedPiece = piece;
					isPieceSelected = true;
					System.out.println(selectedPiece.getName());
				}
			}
		}
		
		return selectedPiece;
	}

	@Override
	public Move getNextMove() {
		isMoveSelected = false;
		
		if(!isPieceSelected)
			return null;
		
		if(display.isCaseSelected()) {
			if(worldInfo.getPieceAtCoord(display.getSelectedCase()) != selectedPiece) {
				LinkedList<Coord3D> reachableCoord = worldInfo.getAllReachablePoint(selectedPiece);
				Coord3D pointedCoord = display.getSelectedCase();
				for(Coord3D coord : reachableCoord) {
					if(pointedCoord.equals(coord)) {
						selectedMove = ChessGame.coordToMove(selectedPiece, pointedCoord);
						isMoveSelected = true;
					}
				}
				
				if(!isMoveSelected) {
					selectedMove = null;
					isPieceSelected = false;
				}
			}
		} else {
			isPieceSelected = false;
			isMoveSelected = false;
		}
		
		return selectedMove;
	}

	@Override
	public PlayerColor getColor() {
		// TODO Auto-generated method stub
		return color;
	}

	@Override
	public void addOwnedPiece(BasePiece piece) {
		// TODO Auto-generated method stub
		ownedPiece.add(piece);
	}

	public void setGame(ChessGame game) {
		// TODO Auto-generated method stub
		chessGame = game;
	}
	
	public void setWorldInformation(WorldInformation w) {
		worldInfo = w;
	}

	public void setDisplay(Display d) {
		// TODO Auto-generated method stub
		display = d;
	}

	@Override
	public void reInit() {
		// TODO Auto-generated method stub
		isPieceSelected = false;
		isMoveSelected = false;
		selectedPiece = null;
		selectedMove = null;
	}

}
