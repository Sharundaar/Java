package fr.fireflown.chessgame.controller;

import java.util.LinkedList;
import java.util.Random;

import fr.fireflown.chessgame.model.game.ChessGame;
import fr.fireflown.chessgame.model.game.Coord3D;
import fr.fireflown.chessgame.model.game.WorldInformation;
import fr.fireflown.chessgame.model.pieces.BasePiece;
import fr.fireflown.chessgame.model.pieces.Move;

public class BotPlayer implements IPlayer {
	LinkedList<BasePiece> ownedPieceList;
	BasePiece piece;
	PlayerColor color;
	WorldInformation worldInfo;
	
	public BotPlayer(PlayerColor color) {
		this.color = color;
		ownedPieceList = new LinkedList<BasePiece>();
	}
	
	public void setWorldInformation(WorldInformation w) {
		this.worldInfo = w;
	}
	
	@Override
	public BasePiece selectPiece() {
		// TODO Auto-generated method stub
		LinkedList<BasePiece> pieceChoice = new LinkedList<BasePiece>();
		for(BasePiece piece : ownedPieceList) {
			if(!piece.isDead()) {
				pieceChoice.add(piece);
			}
		}
		
		Random r = new Random();
		int valeur = r.nextInt(pieceChoice.size());
		
		piece = pieceChoice.get(valeur);
		return piece;
	}

	@Override
	public Move getNextMove() {
		// TODO Auto-generated method stub
		LinkedList<Coord3D> coordList = worldInfo.getAllReachablePoint(piece);
		Random r = new Random();
		if(coordList.size() == 0)
			return null;
		
		int value = r.nextInt(coordList.size());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ChessGame.coordToMove(piece, coordList.get(value));
	}

	@Override
	public PlayerColor getColor() {
		// TODO Auto-generated method stub
		return color;
	}

	@Override
	public void addOwnedPiece(BasePiece piece) {
		// TODO Auto-generated method stub
		ownedPieceList.add(piece);
	}

	public void reInit() {
		
	}
}
