package fr.fireflown.chessgame.model.pieces;

import java.util.LinkedList;

import fr.fireflown.chessgame.controller.IPlayer;
import fr.fireflown.chessgame.model.game.WorldInformation;


public class Tower extends BasePiece {

	public Tower(int x, int y, int z, WorldInformation worldInfo, IPlayer owner) {
		super(x, y, z, worldInfo, owner);
		// TODO Auto-generated constructor stub
		name = "Tower";
	}

	@Override
	public LinkedList<Move> getAllPossibleMove() {
		// TODO Auto-generated method stub
		LinkedList<Move> moves = new LinkedList<Move>();
		
		moves.add(new Move(1, 0, 0, false, MoveType.MOVE_AND_ATTACK));
		moves.add(new Move(-1, 0, 0, false, MoveType.MOVE_AND_ATTACK));
		moves.add(new Move(0, 0, 1, false, MoveType.MOVE_AND_ATTACK));
		moves.add(new Move(0, 0, -1, false, MoveType.MOVE_AND_ATTACK));
		
		return moves;
	}
}
