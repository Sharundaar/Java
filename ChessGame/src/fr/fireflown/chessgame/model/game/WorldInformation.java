package fr.fireflown.chessgame.model.game;

import java.util.LinkedList;

import fr.fireflown.chessgame.controller.IPlayer;
import fr.fireflown.chessgame.model.boards.BaseBoard;
import fr.fireflown.chessgame.model.pieces.BasePiece;
import fr.fireflown.chessgame.model.pieces.Move;
import fr.fireflown.chessgame.model.pieces.MoveType;

public class WorldInformation {
	private LinkedList<BaseBoard> boardsList;
	private LinkedList<BasePiece> piecesList;
	
	private IPlayer topPlayer;
	private IPlayer bottomPlayer;
	
	private int maxHeight;
	private int minX;
	private int maxX;
	private int minZ;
	private int maxZ;
	
	public WorldInformation(LinkedList<BaseBoard> boardsList, LinkedList<BasePiece> piecesList, IPlayer topPlayer, IPlayer bottomPlayer) {
		this.boardsList = boardsList;
		this.piecesList = piecesList;
		this.topPlayer = topPlayer;
		this.bottomPlayer = bottomPlayer;
		
		minX = 0;
		minZ = 0;
		maxX = 0;
		maxZ = 0;
		
		setBoardList(boardsList);
		setPieceList(piecesList);
		
	}
	
	public LinkedList<BasePiece> getPlayerPieces(IPlayer player) {
		LinkedList<BasePiece> result = new LinkedList<BasePiece>();
		
		for(BasePiece piece : piecesList) {
			if(piece.getOwner() == player) {
				result.add(piece);
			}
		}
		
		return result;
	}
	
	public BasePiece getPieceAtCoord(Coord3D coord) {
		if(coord == null)
			return null;
		
		return getPieceAtCoord(coord.x, coord.y, coord.z);
	}
	
	public BasePiece getPieceAtCoord(int x, int y, int z) {
		for(BasePiece piece : piecesList) {
			if(piece.getX() == x && piece.getY() == y && piece.getZ() == z) {
				return piece;
			}
		}
		
		return null;
	}
	
	public BaseBoard getBoardAtCoord(int x, int y, int z) {
		for(BaseBoard board: boardsList) {
			if(board.getY() == y) {
				if(board.isPointOnBoard(x, y, z)) {
					return board;
				}
			}
		}
		
		return null;
	}
	
	public BaseBoard getBoardAtHeight(int h) {
		for(BaseBoard board : boardsList) {
			if(board.getHeight() == h)
				return board;
		}
		
		return null;
	}
	
	public LinkedList<Coord3D> getVerticalPositionsFromCoord(Coord3D coord, MoveType type, BasePiece piece) {
		LinkedList<Coord3D> coords = new LinkedList<Coord3D>();
		
		for(int y=0 ; y<=getMaxHeight() ; ++y) {
			if(y != coord.y) {
				BaseBoard board = getBoardAtCoord(coord.x, y, coord.z);
				if(board != null) {
					BasePiece pieceOnBoard = getPieceAtCoord(coord.x, y, coord.z);
					if(pieceOnBoard != null) {
						if(type != MoveType.MOVE_ONLY && pieceOnBoard.getOwner() != piece.getOwner()) {
							coords.add(new Coord3D(coord.x, y, coord.z));
						}
					} else {
						if(type != MoveType.ATTACK_ONLY) {
							coords.add(new Coord3D(coord.x, y, coord.z));
						}
					}
				}
			}
		}
		
		return coords;
	}
	
	public LinkedList<Coord3D> getAllReachablePoint(BasePiece piece) {
		if(piece == null)
			return null;
		
		LinkedList<Coord3D> coords = new LinkedList<Coord3D>();
		LinkedList<Move> moves = piece.getAllPossibleMove();
		
		for(Move move : moves) {
			/* First we check if the move is finite */
			if(move.finite) {
				/* If its finite then we just add the coordinate of the piece and the move */
				Coord3D coord = new Coord3D(piece.getX()+move.dx, piece.getY(), piece.getZ()+move.dz);
				
				BaseBoard board = getBoardAtCoord(coord.x, coord.y, coord.z);
				if(board != null) {
					BasePiece pieceOnBoard = getPieceAtCoord(coord.x, coord.y, coord.z);
					if(pieceOnBoard != null) {
						if(move.type != MoveType.MOVE_ONLY && pieceOnBoard.getOwner() != piece.getOwner()) {
							coords.add(coord);
						}
					} else {
						if(move.type != MoveType.ATTACK_ONLY) {
							coords.add(coord);
						}
					}
				}
				
				// Then we add the vertical moves
				coords.addAll(getVerticalPositionsFromCoord(coord, move.type, piece));
				
			} else {
				int x=piece.getX()+move.dx, y=piece.getY(), z=piece.getZ()+move.dz;
				
				while(x>=getMinX() && x<=getMaxX() && z>=getMinZ() && z<= getMaxZ()) {
					Coord3D coord = new Coord3D(x, y, z);
					BaseBoard dBoard = getBoardAtCoord(coord.x, coord.y, coord.z);
					if(dBoard != null) {
						BasePiece pieceOnBoard = getPieceAtCoord(coord.x, coord.y, coord.z);
						if(pieceOnBoard != null) {
							if(move.type != MoveType.MOVE_ONLY) {
								if(pieceOnBoard.getOwner() != piece.getOwner()) {
									LinkedList<Coord3D> verticalCoord = getVerticalPositionsFromCoord(coord, move.type, piece);
									if(verticalCoord != null && verticalCoord.size() > 0) {
										coords.add(coord);
									} else {
										coords.add(coord);
										z=99;
									}
								} else {
									LinkedList<Coord3D> verticalCoord = getVerticalPositionsFromCoord(coord, move.type, piece);
									if(verticalCoord != null && verticalCoord.size() > 0) {
										
									} else {
										z=99;
									}
								}
							}
						} else {
							if(move.type != MoveType.ATTACK_ONLY) {
								coords.add(coord);
							}
						}
						
						coords.addAll(getVerticalPositionsFromCoord(coord, move.type, piece));

						x+=move.dx;
						z+=move.dz;
					} else {
						LinkedList<Coord3D> verticalCoord = getVerticalPositionsFromCoord(coord, move.type, piece);
						if(verticalCoord != null && verticalCoord.size() > 0) {
							coords.addAll(verticalCoord);
							x+=move.dx;
							z+=move.dz;
						} else {
							z=99;
						}
					}
				}
			}
		}
		return coords;
	}
	
	public int getMaxHeight() {
		return maxHeight;
	}
	
	public LinkedList<BaseBoard> getBoardsList() {
		return boardsList;
	}
	
	public LinkedList<BasePiece> getPiecesList() {
		return piecesList;
	}

	public IPlayer getTopPlayer() {
		// TODO Auto-generated method stub
		return topPlayer;
	}

	public IPlayer getBottomPlayer() {
		// TODO Auto-generated method stub
		return bottomPlayer;
	}

	public int getMinX() {
		// TODO Auto-generated method stub
		return minX;
	}

	public int getMaxX() {
		// TODO Auto-generated method stub
		return maxX;
	}

	public int getMinZ() {
		// TODO Auto-generated method stub
		return minZ;
	}
	
	public int getMaxZ() {
		// TODO Auto-generated method stub
		return maxZ;
	}

	public void setBoardList(LinkedList<BaseBoard> boardList) {
		// TODO Auto-generated method stub
		this.boardsList = boardList;
		if(boardsList != null) {
			maxHeight=0;
			for(BaseBoard board : boardsList) {
				if(board.getY() > maxHeight)
					maxHeight = board.getY();
				
				if(board.getZ()+board.getHeight() > maxZ)
					maxZ = board.getZ()+board.getHeight();
				
				if(board.getX()+board.getWidth() > maxX)
					maxX = board.getX()+board.getWidth();
			}
		}
	}

	public void setPieceList(LinkedList<BasePiece> pieceList) {
		// TODO Auto-generated method stub
		this.piecesList = pieceList;
	}
}
