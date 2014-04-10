package fr.fireflown.chessgame.model.game;

public class Coord3D {
	public int x;
	public int y;
	public int z;
	
	public Coord3D(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Coord3D() {
		x=0;
		y=0;
		z=0;
	}
	
	public boolean equals(Coord3D coord) {
		if(coord == null)
			return false;
		
		return x==coord.x && y==coord.y && z==coord.z;
	}
	
	public String toString() {
		return "x : "+x+" y : "+y+" z : "+z;
	}
}
