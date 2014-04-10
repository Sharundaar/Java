package fr.fireflown.chessgame.controller;
import fr.fireflown.chessgame.model.game.WorldInformation;
import fr.fireflown.chessgame.model.pieces.BasePiece;
import fr.fireflown.chessgame.model.pieces.Move;


public interface IPlayer {
	public BasePiece selectPiece();
	public Move getNextMove();
	public PlayerColor getColor();
	public void addOwnedPiece(BasePiece piece);
	public void reInit();
	public void setWorldInformation(WorldInformation worldInformation);
}
