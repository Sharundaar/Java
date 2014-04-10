package fr.fireflown.chessgame.model.boards;

public abstract class BaseBoard {
	protected int x;
	protected int y;
	protected int z;
	protected int width;
	protected int height;
	
	public BaseBoard(int x, int y, int z, int width, int height) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.width = width;
		this.height = height;
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

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public boolean isPointOnBoard(int x, int y, int z) {
		if(y != this.y)
			return false;
			
		if(x >= this.x && x < this.x + this.width && z >= this.z && z < this.z + this.height) {
			return true;
		} else {
			return false;
		}
	}
}
