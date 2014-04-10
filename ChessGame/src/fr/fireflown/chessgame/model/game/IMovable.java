package fr.fireflown.chessgame.model.game;

public interface IMovable {
	public void move(int dx, int dy, int dz);
	public int getX();
	public int getY();
	public int getZ();
}
