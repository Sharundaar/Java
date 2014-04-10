package fr.fireflown.chessgame.model.pieces;

import java.util.LinkedList;

import fr.fireflown.chessgame.controller.IPlayer;
import fr.fireflown.chessgame.model.game.IMovable;
import fr.fireflown.chessgame.model.game.WorldInformation;

public abstract class BasePiece implements IMovable {
	protected int x;
	protected int y;
	protected int z;
	protected WorldInformation worldInfo;
	protected IPlayer owner;
	protected boolean dead;
	
	protected String name;
	
	public BasePiece(int x, int y, int z, WorldInformation worldInfo, IPlayer owner) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.worldInfo = worldInfo;
		this.owner = owner;
		dead = false;
		
		name = "AbstractPiece";
		owner.addOwnedPiece(this);
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public void kill() {
		dead = true;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getZ() {
		return z;
	}
	
	public void move(Move move) {
		this.move(move.dx, move.dy, move.dz);
	}
	
	public void move(int dx, int dy, int dz) {
		if(!isDead()) {
			this.x += dx;
			this.y += dy;
			this.z += dz;
		}
	}
	
	public abstract LinkedList<Move> getAllPossibleMove();

	public IPlayer getOwner() {
		return owner;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
}
