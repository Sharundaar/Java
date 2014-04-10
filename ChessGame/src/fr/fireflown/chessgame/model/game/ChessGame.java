package fr.fireflown.chessgame.model.game;

import java.util.LinkedList;

import fr.fireflown.chessgame.controller.IPlayer;
import fr.fireflown.chessgame.controller.PlayerColor;
import fr.fireflown.chessgame.model.boards.BaseBoard;
import fr.fireflown.chessgame.model.boards.FixedBoard;
import fr.fireflown.chessgame.model.boards.MovableBoard;
import fr.fireflown.chessgame.model.pieces.BasePiece;
import fr.fireflown.chessgame.model.pieces.Bishop;
import fr.fireflown.chessgame.model.pieces.King;
import fr.fireflown.chessgame.model.pieces.Knight;
import fr.fireflown.chessgame.model.pieces.Move;
import fr.fireflown.chessgame.model.pieces.MoveType;
import fr.fireflown.chessgame.model.pieces.Pawn;
import fr.fireflown.chessgame.model.pieces.Queen;
import fr.fireflown.chessgame.model.pieces.Tower;


public class ChessGame {
	private LinkedList<BaseBoard> boardList;
	private LinkedList<BasePiece> pieceList;
	
	private IPlayer white;
	private IPlayer black;
	
	private IPlayer currentPlayer;
	
	PlayerColor winner;
	
	private WorldInformation worldInfo;
	
	private String errorMessage;
	
	private int nbTurns;
	
	public ChessGame(IPlayer whitePlayer, IPlayer blackPlayer, boolean classic) {
		boardList = new LinkedList<BaseBoard>();
		pieceList = new LinkedList<BasePiece>();
		
		white = whitePlayer;
		black = blackPlayer;
		
		worldInfo = new WorldInformation(null, null, black, white);
		if(classic)
			setupClassicGame();
		else
			setupGame();
		
		worldInfo.setBoardList(boardList);
		worldInfo.setPieceList(pieceList);
		
		currentPlayer = white;
	
		errorMessage = "";
		winner = null;
		nbTurns = 0;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}	
	
	public void setupClassicGame() {
		FixedBoard mainBoard = new FixedBoard(0, 1, 0, 8, 8);
		boardList.add(mainBoard);
		
/* Setup the pieces */
		
		/* White Pieces */
		Pawn wPawn1 = new Pawn(0, 1, 6, worldInfo, white);
		Pawn wPawn2 = new Pawn(1, 1, 6, worldInfo, white);
		
		Pawn wPawn3 = new Pawn(2, 1, 6, worldInfo, white);
		Pawn wPawn4 = new Pawn(3, 1, 6, worldInfo, white);
		Pawn wPawn5 = new Pawn(4, 1, 6, worldInfo, white);
		Pawn wPawn6 = new Pawn(5, 1, 6, worldInfo, white);
		
		Pawn wPawn7 = new Pawn(6, 1, 6, worldInfo, white);
		Pawn wPawn8 = new Pawn(7, 1, 6, worldInfo, white);
		
		Queen wQueen = new Queen(3, 1, 7, worldInfo, white);
		King wKing = new King(4, 1, 7, worldInfo, white);
		Bishop wBishop1 = new Bishop(2, 1, 7, worldInfo, white);
		Bishop wBishop2 = new Bishop(5, 1, 7, worldInfo, white);
		Tower wTower1 = new Tower(0, 1, 7, worldInfo, white);
		Tower wTower2 = new Tower(7, 1, 7, worldInfo, white);
		Knight wKnight1 = new Knight(1, 1, 7, worldInfo, white);
		Knight wKnight2 = new Knight(6, 1, 7, worldInfo, white);
		
		/* Black Pieces */
		Pawn bPawn1 = new Pawn(0, 1, 1, worldInfo, black);
		Pawn bPawn2 = new Pawn(1, 1, 1, worldInfo, black);
		
		Pawn bPawn3 = new Pawn(2, 1, 1, worldInfo, black);
		Pawn bPawn4 = new Pawn(3, 1, 1, worldInfo, black);
		Pawn bPawn5 = new Pawn(4, 1, 1, worldInfo, black);
		Pawn bPawn6 = new Pawn(5, 1, 1, worldInfo, black);
		
		Pawn bPawn7 = new Pawn(6, 1, 1, worldInfo, black);
		Pawn bPawn8 = new Pawn(7, 1, 1, worldInfo, black);
		
		Queen bQueen = new Queen(3, 1, 0, worldInfo, black);
		King bKing = new King(4, 1, 0, worldInfo, black);
		Bishop bBishop1 = new Bishop(2, 1, 0, worldInfo, black);
		Bishop bBishop2 = new Bishop(5, 1, 0, worldInfo, black);
		Tower bTower1 = new Tower(0, 1, 0, worldInfo, black);
		Tower bTower2 = new Tower(7, 1, 0, worldInfo, black);
		Knight bKnight1 = new Knight(1, 1, 0, worldInfo, black);
		Knight bKnight2 = new Knight(6, 1, 0, worldInfo, black);
		
		
		pieceList.add(wPawn1);
		pieceList.add(wPawn2);
		pieceList.add(wPawn3);
		pieceList.add(wPawn4);
		pieceList.add(wPawn5);
		pieceList.add(wPawn6);
		pieceList.add(wPawn7);
		pieceList.add(wPawn8);
		
		pieceList.add(bPawn1);
		pieceList.add(bPawn2);
		pieceList.add(bPawn3);
		pieceList.add(bPawn4);
		pieceList.add(bPawn5);
		pieceList.add(bPawn6);
		pieceList.add(bPawn7);
		pieceList.add(bPawn8);
		
		pieceList.add(wQueen);
		pieceList.add(wKing);
		pieceList.add(wBishop1);
		pieceList.add(wBishop2);
		pieceList.add(wTower1);
		pieceList.add(wTower2);
		pieceList.add(wKnight1);
		pieceList.add(wKnight2);
		
		pieceList.add(bQueen);
		pieceList.add(bKing);
		pieceList.add(bBishop1);
		pieceList.add(bBishop2);
		pieceList.add(bTower1);
		pieceList.add(bTower2);
		pieceList.add(bKnight1);
		pieceList.add(bKnight2);
		
	}
	
	public void setupGame() {
		
		/* Setup the boards */
		FixedBoard topBoard = new FixedBoard(1, 5, 1);
		FixedBoard midBoard = new FixedBoard(1, 3, 3);
		FixedBoard botBoard = new FixedBoard(1, 1, 5);
		
		MovableBoard topOne = new MovableBoard(0, topBoard);
		MovableBoard topTwo = new MovableBoard(1, topBoard);
		MovableBoard botOne = new MovableBoard(2, botBoard);
		MovableBoard botTwo = new MovableBoard(3, botBoard);
		
		
		boardList.add(topBoard);
		boardList.add(midBoard);
		boardList.add(botBoard);
		
		boardList.add(topOne);
		boardList.add(topTwo);
		boardList.add(botOne);
		boardList.add(botTwo);
		
		/* Setup the pieces */
		
		/* White Pieces */
		Pawn wPawn1 = new Pawn(0, 2, 8, worldInfo, white);
		Pawn wPawn2 = new Pawn(1, 2, 8, worldInfo, white);
		
		Pawn wPawn3 = new Pawn(1, 1, 7, worldInfo, white);
		Pawn wPawn4 = new Pawn(2, 1, 7, worldInfo, white);
		Pawn wPawn5 = new Pawn(3, 1, 7, worldInfo, white);
		Pawn wPawn6 = new Pawn(4, 1, 7, worldInfo, white);
		
		Pawn wPawn7 = new Pawn(4, 2, 8, worldInfo, white);
		Pawn wPawn8 = new Pawn(5, 2, 8, worldInfo, white);
		
		Queen wQueen = new Queen(2, 1, 8, worldInfo, white);
		King wKing = new King(3, 1, 8, worldInfo, white);
		Bishop wBishop1 = new Bishop(1, 1, 8, worldInfo, white);
		Bishop wBishop2 = new Bishop(4, 1, 8, worldInfo, white);
		Tower wTower1 = new Tower(0, 2, 9, worldInfo, white);
		Tower wTower2 = new Tower(5, 2, 9, worldInfo, white);
		Knight wKnight1 = new Knight(1, 2, 9, worldInfo, white);
		Knight wKnight2 = new Knight(4, 2, 9, worldInfo, white);
		
		/* Black Pieces */
		Pawn bPawn1 = new Pawn(0, 6, 1, worldInfo, black);
		Pawn bPawn2 = new Pawn(1, 6, 1, worldInfo, black);
		
		Pawn bPawn3 = new Pawn(1, 5, 2, worldInfo, black);
		Pawn bPawn4 = new Pawn(2, 5, 2, worldInfo, black);
		Pawn bPawn5 = new Pawn(3, 5, 2, worldInfo, black);
		Pawn bPawn6 = new Pawn(4, 5, 2, worldInfo, black);
		
		Pawn bPawn7 = new Pawn(4, 6, 1, worldInfo, black);
		Pawn bPawn8 = new Pawn(5, 6, 1, worldInfo, black);
		
		Queen bQueen = new Queen(2, 5, 1, worldInfo, black);
		King bKing = new King(3, 5, 1, worldInfo, black);
		Bishop bBishop1 = new Bishop(4, 5, 1, worldInfo, black);
		Bishop bBishop2 = new Bishop(1, 5, 1, worldInfo, black);
		Tower bTower1 = new Tower(0, 6, 0, worldInfo, black);
		Tower bTower2 = new Tower(5, 6, 0, worldInfo, black);
		Knight bKnight1 = new Knight(1, 6, 0, worldInfo, black);
		Knight bKnight2 = new Knight(4, 6, 0, worldInfo, black);
		
		pieceList.add(wPawn1);
		pieceList.add(wPawn2);
		pieceList.add(wPawn3);
		pieceList.add(wPawn4);
		pieceList.add(wPawn5);
		pieceList.add(wPawn6);
		pieceList.add(wPawn7);
		pieceList.add(wPawn8);
		
		pieceList.add(bPawn1);
		pieceList.add(bPawn2);
		pieceList.add(bPawn3);
		pieceList.add(bPawn4);
		pieceList.add(bPawn5);
		pieceList.add(bPawn6);
		pieceList.add(bPawn7);
		pieceList.add(bPawn8);
		
		pieceList.add(wQueen);
		pieceList.add(wKing);
		pieceList.add(wBishop1);
		pieceList.add(wBishop2);
		pieceList.add(wTower1);
		pieceList.add(wTower2);
		pieceList.add(wKnight1);
		pieceList.add(wKnight2);
		
		pieceList.add(bQueen);
		pieceList.add(bKing);
		pieceList.add(bBishop1);
		pieceList.add(bBishop2);
		pieceList.add(bTower1);
		pieceList.add(bTower2);
		pieceList.add(bKnight1);
		pieceList.add(bKnight2);
	}
	
	public void turn() {
		Move nextMove;
		IMovable selectPiece;
		
		if(winner == null) {
			do {
				selectPiece = currentPlayer.selectPiece();
				nextMove = currentPlayer.getNextMove();
			} while(!checkMove(selectPiece, nextMove));
			
			BasePiece dPiece = worldInfo.getPieceAtCoord(moveToCoord(selectPiece, nextMove));
			if(dPiece != null) {
				dPiece.move(0, 20, 0);
				dPiece.kill();
				System.out.println("Killed : "+dPiece.toString());
				if(dPiece.getName().equals("King")) {
					winner = currentPlayer.getColor();
				}
			}
			
			selectPiece.move(nextMove.dx, nextMove.dy, nextMove.dz);
			currentPlayer.reInit();
			swapPlayer();
			
			++nbTurns;
		}
	}
	
	public int getNbTurns() {
		return nbTurns;
	}
	
	public PlayerColor getWinner() {
		return winner;
	}
	
	public void swapPlayer() {
		if(currentPlayer == white) {
			currentPlayer = black;
		} else {
			currentPlayer = white;
		}
	}
	
	public boolean checkMove(IMovable moveObject, Move move) {
		if(moveObject instanceof BasePiece) {
			BasePiece piece = (BasePiece) moveObject;
			
			if(piece == null || move == null)
				return false;
			
			if(piece.getOwner() != currentPlayer)
				return false;
			
			if(piece.isDead())
				return false;
			
			LinkedList<Coord3D> checkCoord = worldInfo.getAllReachablePoint(piece);
			Coord3D dcoord = moveToCoord(piece, move);
			for(Coord3D coord : checkCoord) {
				if(dcoord.equals(coord)) {
					return true;
				}
			}
			
			return false;
		} else if(moveObject instanceof MovableBoard) {
			// MovableBoard board = (MovableBoard) moveObject;
			/* Add treatment */
		}
		return false;
	}
	
	public static Move coordToMove(BasePiece piece, Coord3D coord) {
		if(coord == null || piece == null)
			return null;
		
		return new Move(coord.x - piece.getX(), coord.y - piece.getY(), coord.z - piece.getZ(), true, MoveType.MOVE_AND_ATTACK);
	}
	
	public static Coord3D moveToCoord(IMovable piece, Move move) {
		if(move == null || piece == null)
			return null;
		
		return new Coord3D(piece.getX() + move.dx, piece.getY() + move.dy, piece.getZ() + move.dz);
	}
	
	public WorldInformation getWorldInformation() {
		// TODO Auto-generated method stub
		return worldInfo;
	}
}
