package fr.fireflown.chessgame.model.pieces;

public class Move {
	public int dx;
	public int dy;
	public int dz;
	public boolean finite;
	public MoveType type;
	
	public Move(int dx, int dy, int dz, boolean finite, MoveType type) {
		this.dx = dx;
		this.dy = dy;
		this.dz = dz;
		this.finite = finite;
		this.type = type;
	}
	
	public int getDirectionX() {
		if(dx>0) {
			return 1;
		}
		
		if(dx<0) {
			return -1;
		}
		
		return dx;
	}
	
	public int getDirectionZ() {
		if(dz>0) {
			return 1;
		}
		
		if(dz<0) {
			return -1;
		}
		
		return dz;
		
	}
	
	public boolean equals(Move move) {
		return dx == move.dx && dz == move.dz && finite == move.finite && type == move.type;
	}
}
