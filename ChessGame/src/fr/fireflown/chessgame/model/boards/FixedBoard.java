package fr.fireflown.chessgame.model.boards;

public class FixedBoard extends BaseBoard {

	public FixedBoard(int height) {
		super(1, 1, height, 8, 8);
	}
	
	public FixedBoard(int x, int y, int height) {
		super(x, y, height, 4, 4);
	}
	
	public FixedBoard(int x, int z, int y, int width, int height) {
		super(x, z, y, width, height);
	}

}
