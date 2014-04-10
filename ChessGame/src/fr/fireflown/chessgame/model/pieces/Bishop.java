package fr.fireflown.chessgame.model.pieces;

import java.util.LinkedList;

import fr.fireflown.chessgame.controller.IPlayer;
import fr.fireflown.chessgame.model.game.WorldInformation;


public class Bishop extends BasePiece {

	public Bishop(int x, int y, int z, WorldInformation worldInfo, IPlayer owner) {
		super(x, y, z, worldInfo, owner);
		// TODO Auto-generated constructor stub
		name = "Bishop";
	}

	@Override
	public LinkedList<Move> getAllPossibleMove() {
		// TODO Auto-generated method stub
		LinkedList<Move> moveList = new LinkedList<Move>();
		
		moveList.add(new Move(1, 0, 1, false, MoveType.MOVE_AND_ATTACK));
		moveList.add(new Move(1, 0, -1, false, MoveType.MOVE_AND_ATTACK));
		moveList.add(new Move(-1, 0, 1, false, MoveType.MOVE_AND_ATTACK));
		moveList.add(new Move(-1, 0, -1, false, MoveType.MOVE_AND_ATTACK));
		
		return moveList;
	}

}
