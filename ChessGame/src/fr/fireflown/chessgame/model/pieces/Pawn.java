package fr.fireflown.chessgame.model.pieces;

import java.util.LinkedList;

import fr.fireflown.chessgame.controller.IPlayer;
import fr.fireflown.chessgame.controller.PlayerColor;
import fr.fireflown.chessgame.model.game.WorldInformation;


public class Pawn extends BasePiece {
	private boolean hasMoved;
	
	public Pawn(int x, int y, int z, WorldInformation worldInfo, IPlayer owner) {
		super(x, y, z, worldInfo, owner);
		// TODO Auto-generated constructor stub
		name = "Pawn";
		hasMoved = false;
	}

	@Override
	public LinkedList<Move> getAllPossibleMove() {
		// TODO Auto-generated method stub
		LinkedList<Move> moveList = new LinkedList<Move>();
		
		int forwardDirection = (owner.getColor() == PlayerColor.WHITE ? -1 : 1);
		
		moveList.add(new Move(0, 0, forwardDirection, true, MoveType.MOVE_ONLY));
		moveList.add(new Move(1, 0, forwardDirection, true, MoveType.ATTACK_ONLY));
		moveList.add(new Move(-1, 0, forwardDirection, true, MoveType.ATTACK_ONLY));
		
		if(!hasMoved) {
			moveList.add(new Move(0, 0, 2*forwardDirection, true, MoveType.MOVE_ONLY));
		}
		
		return moveList;
	}
	
	@Override
	public void move(int dx, int dy, int dz) {
		if(!isDead()) {
			this.x += dx;
			this.y += dy;
			this.z += dz;
			hasMoved = true;
		}
	}
}
