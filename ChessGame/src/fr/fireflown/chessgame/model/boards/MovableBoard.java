package fr.fireflown.chessgame.model.boards;

import fr.fireflown.chessgame.model.game.IMovable;

public class MovableBoard extends BaseBoard implements IMovable {
	FixedBoard board;
	
	public MovableBoard(int cornerNumber, FixedBoard board) {
		super(0, 0, 0, 2, 2);
		
		this.board = board;
		setPosition(cornerNumber, board);
	
	}
	
	public void setPosition(int cornerNumber, FixedBoard board) {
		int x=0, y=0, z=0;
		
		switch(cornerNumber) {
		case 0:
			x = board.getX()-1;
			y = board.getY()+1;
			z = board.getZ()-1;
			break;
			
		case 1:
			x = board.getX() + board.getWidth() - 1;
			y = board.getY() + 1;
			z = board.getZ() - 1;
			break;
			
		case 2:
			x = board.getX() - 1;
			y = board.getY() + 1;
			z = board.getZ() + board.getHeight() - 1;
			break;
			
		case 3:
			x = board.getX() + board.getWidth() - 1;
			y = board.getY() + 1;
			z = board.getZ() + board.getHeight() - 1;
			break;
		
		case 4:
			x = board.getX()-1;
			y = board.getY()-1;
			z = board.getZ()-1;
			break;
		
		case 5:
			x = board.getX() + board.getWidth();
			y = board.getY() - 1;
			z = board.getZ() - 1;
			break;
			
		case 6:
			x = board.getX() - 1;
			y = board.getY() - 1;
			z = board.getZ() + board.getHeight();
			break;
			
		case 7:
			x = board.getX() + board.getWidth();
			y = board.getY() - 1;
			z = board.getZ() + board.getHeight();
			break;
			
		default:
			System.out.println("CornerNumber error");
			x = this.x;
			y = this.y;
			z = this.z;
		}
		
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void move(int dx, int dy, int dz) {
		// TODO Auto-generated method stub
		
	}
}
