package fr.fireflown.lifegame;

// This class handle the life game logic
public class LifeGame {

	// Represent the cells' state at a t instant
	private boolean m_cellState[][];
	// Represent the cells' state at a t+1 instant
	private boolean m_cellNextStepState[][];
	
	// Width of the grid
	private int width;
	// Height of the grid
	private int height;
	// Number of cycle since the simulation has begun
	private int cycle = 0;
	
	// A modulus function that always return a positive integer
	// if a >= 0, return a%mod
	// else build a positive integer by adding the mod value to a
	// Example : a=-1, mod=10, modulus(a, mod) => modulus(-1 + 10, 10) => modulus(9, 10) => 9
	// Is used to loop the grid 
	public static int modulus (int a, int mod) {
		if (a >= 0) {
			return a % mod;
		} else {
			return modulus (mod + a, mod);
		}
	}
	
	// Default constructor
	// Generate a default LifeGame (dim = 10*10)
	public LifeGame() {
		m_cellState = new boolean[10][10];	// x x x x x	width = 5 (just a generic sample..
		width = m_cellState[0].length;		// x x x x x	height = 3 	..not the actual case)
		height = m_cellState.length;		// x x x x x
		
		m_cellNextStepState = new boolean[10][10];
		// at start, all cells are dead (not needed, see Cell constructor)
		for(int i=0 ; i<10 ; i++)
		{
			for(int j=0 ; j<10 ; j++)
			{
				// Initiate all cells to dead
				m_cellState[i][j] = false;
				m_cellNextStepState[i][j] = false;
			}
		}
	}
	
	// Attribute constructor
	// Generate a grid of w width and h height
	public LifeGame(int w, int h) {
		m_cellState = new boolean[h][w];
		width = w;
		height = h;
		
		m_cellNextStepState = new boolean[h][w];
		
		for(int i=0 ; i<h ; i++)
		{
			for(int j=0 ; j<w ; j++)
			{
				m_cellState[i][j] = false;
				m_cellNextStepState[i][j] = false;
			}
		}
	}
	
	// Compute a step of the simulation
	public boolean[][] computeStep() {
		int i, j, cellAliveAround = 0;
		
		// Counting a cycle
		cycle++;
		
		// We have to count for each cell their surrounding
		for(i=0 ; i < height ; i++)
		{
			for(j=0 ; j < width ; j++)
			{					
				// Checking the 8 cells around the considerate cell
				for (int x = i-1; x <= i+1; x++) {
					for (int y = j-1; y <= j+1; y++) {
						// The modulus function protect us against any out of bound exception
						int a = modulus (x,height); // Particularly usefull if x = -1 or y = -1
						int b = modulus (y,width);
						
						// Checking we're not comparing a cell with itself
						if ((b != j) || (a != i)) {
							// Counting the number of cells alive around
							if (m_cellState [a][b]) cellAliveAround ++;
						}
					}
				} 
				/*if(m_cellState[i-1][j-1] || m_cellState[i-1][j] || m_cellState[i-1][j+1]
						|| m_cellState[i][j-1] || m_cellState[i][j+1] 
						|| m_cellState[i+1][j-1] || m_cellState[i+1][j] || m_cellState[i+1][j+1])
					{ cellAliveAround++; }*/
				
				// We compute the new cell state according to the rule
				computeCellState(i, j, cellAliveAround);	
				
				// Reseting the counter
				cellAliveAround = 0;
			}
		}
		
		// Updating the current state by switching the buffer
		switchCellState();
		
		// Here to show the coordinate of the cell around
		// [i-1,j-1] [i-1,j] [i-1,j+1]
		//  [i,j-1]   [i,j]   [i,j+1]
		// [i+1,j-1] [i+1,j] [i+1,j+1]
		return m_cellState;
	}
	
	// Change the size of the cell grid
	public void changeSize(int newRow, int newCollumn) {
	
		// We allocate the new grid and copy the old data into the new one
		boolean[][] newGrid = new boolean[newRow][newCollumn];
		if(height < newRow) {
			if(width < newCollumn) {
				for(int i=0 ; i<height ; i++){
					for(int j=0 ; j<width ; j++) {
						newGrid[i][j] = m_cellState[i][j];
					}
				}
			} else {
				for(int i=0 ; i<height ; i++){
					for(int j=0 ; j<newCollumn ; j++) {
						newGrid[i][j] = m_cellState[i][j];
					}
				}
			}
		} else {
			if(width < newCollumn) {
				for(int i=0 ; i<newRow ; i++){
					for(int j=0 ; j<width ; j++) {
						newGrid[i][j] = m_cellState[i][j];
					}
				}
			} else {
				for(int i=0 ; i<newRow ; i++){
					for(int j=0 ; j<newCollumn ; j++) {
						newGrid[i][j] = m_cellState[i][j];
					}
				}
			}
		}
		
		m_cellState = newGrid;
		newGrid = null;
	}
	
	// Switch the old cell state to the next one
	private void switchCellState() {
		for(int i = 0 ; i<height ; i++)
		{
			for(int j = 0 ; j<width ; j++)
			{
				m_cellState[i][j] = m_cellNextStepState[i][j];
			}
		}
	}
	
	// Randomize the grid with the specified ratio of cell alive
	// A ratio of 100% will cover the grid of cell alive
	// A ratio of 0 % will cover the grid of dead cell
	// Any other ratio will cover the grid in aprox. ratio% of alive cell
	// This function reset the cycle value
	public void randomizeGrid(int ratio) {
		double rand=0.0;
		cycle = 0;
		for(int i=0 ; i<height ; i++) {
			for(int j=0 ; j<width ; j++) {
				rand = Math.random();
				if(rand>(double)(100-ratio)/100.0)
					m_cellState[i][j] = true;
				else 
					m_cellState[i][j] = false;
			}
		}
	}
	
	// Compute the cell state according to the Life Game rules
	private void computeCellState(int x, int y, int cellAliveAround) {
		// If a cell is surrounded by exactly 3 cells it is alive
		if (cellAliveAround == 3)
			m_cellNextStepState[x][y] = true;
		
		// Else it is dead
		if(cellAliveAround < 2 || cellAliveAround > 3)
			m_cellNextStepState[x][y] = false;
	}
	
	// Beep the state of a cell located at x and y coordinates
	public void changeCellState(int x, int y) {
		if(m_cellState[y][x])
			m_cellState[y][x] = false;
		else
			m_cellState[y][x] = true;
		
		m_cellNextStepState[y][x] = m_cellState[y][x];
	}
	
	// Return the current cell state
	public boolean[][] getCellState() {
		return m_cellState;
	}

	// Return the width of the grid
	public int getWidth() {
		return width;
	}

	// Return the height of the grid
	public int getHeight() {
		return height;
	}
	
	// Return the number of cycle passed since the start of the simulation
	public int getCycle() { return cycle; }
}
