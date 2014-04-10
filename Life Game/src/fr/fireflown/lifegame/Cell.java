package fr.fireflown.lifegame;

public class Cell {

	// Tell if the cell is alive or dead
	public boolean alive;
	
	// Default constructor
	public Cell() {
		alive = false;
	}
	
	// Attribute constructor
	public Cell(boolean state) {
		alive = state;
	}
	
	// Return if the cell is alive
	public boolean isAlive() { return alive; }
	
	// Beep the cell state
	// If the cell is dead then it's alive and if the cell is alive then it's dead
	public void setAlive() {
		if(alive)
			alive = false;
		else
			alive = true;
	}
	
	// Set the state of the cell
	public void setAlive(boolean state) {
		alive = state;
	}
}
