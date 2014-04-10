package fr.fireflown.chessgame.view;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.fireflown.chessgame.controller.BotPlayer;
import fr.fireflown.chessgame.controller.HumanPlayer;
import fr.fireflown.chessgame.controller.IPlayer;
import fr.fireflown.chessgame.controller.PlayerColor;
import fr.fireflown.chessgame.model.game.ChessGame;

/**
* Represent the main window which gives a view on the chess game
*/
public class MainWindow extends JFrame implements ActionListener{
	
	/**
	 * Serial ID used by swing to serialize the frame
	 */
	private static final long serialVersionUID = 6016587016412076166L;
	
	/*
	 * Reference to the display which shows the chess game
	 */
	public Display display;
	
	/*
	 * Reference to the logical part of the chess game
	 */
	public ChessGame chessGame;
	
	/*
	 * Button to go up a layer along the z axis
	 */
	JButton upButton;
	
	/*
	 * Button to go down a layer along the z axis
	 */
	JButton downButton;
	
	/*
	 * Used to capture the mouse event
	 */
	MouseListener ml;
	
	/*
	 * Used to stop the game
	 */
	boolean interupt;
	
	/*
	 * Thread that handle the display and refresh the screen
	 */
	Thread displayThread;
	
	/*
	 * Interface for the two players
	 */
	IPlayer player1;
	IPlayer player2;
	
	/*
	 * A generic human and bot player
	 * Used for the demo
	 */
	HumanPlayer humanPlayer;
	BotPlayer botPlayer;
	
	/*
	 * Label used to display the number of turn and the current layer
	 */
	JLabel label;
	
	/*
	 * This thread is used to refresh the display
	 * without blocking the game
	 */
	class MyThread implements Runnable {
		MyThread() {
			super();
		}
		
		public void run() {
			// We can play on interupt to stop the thread
			while(!interupt) {
				// Refresh the display
				display.repaint();
				// Update the label
				label.setText("Current Height : "+display.getCurrentHeight()+" Turn number : "+chessGame.getNbTurns());
			}
		}
	}
	
	// Default constructor
	public MainWindow() {
		// Setup the window look
		this.setTitle("Chess Game");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Initialize the game
		initGame();
		
		// Inizialize the display
		initDisplay();
		
		// Creating the buttons
		upButton = new JButton("+");
		upButton.addActionListener(this);
		downButton = new JButton("-");
		downButton.addActionListener(this);
		
		// Initialize the label
		label = new JLabel("Initialisation");
		
		// Initialize the global panel which will contain everything
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		
		// Place the diferent element on the window
		content.add(upButton, BorderLayout.EAST);
		content.add(downButton, BorderLayout.WEST);
		content.add(display, BorderLayout.CENTER);
		content.add(label, BorderLayout.SOUTH);
		
		// Setup and start the refresh thread
		interupt = false;
		displayThread = new Thread(new MyThread());
		displayThread.start();
		
		// Tell the window to use our content pane
		this.setContentPane(content);
		this.setVisible(true);
	}
	
	// Update the label text
	public void updateName() {
		label.setName("Current Height : "+display.getHeight()+" Turn number : "+chessGame.getNbTurns());
	}
	
	// Launch the chess game
	public void go() {
		while(chessGame.getWinner() == null) {
			chessGame.turn();
			display.disableSelection();
		}
		interupt = true;
		
		JOptionPane.showMessageDialog(this, "Player "+chessGame.getWinner()+" won !!");
	}
	
	// Inizialize the game logic
	public void initGame() {
		
		// The following code open some dialog box to decide which player is human and which is a bot
		// and if the player want to play 3D or classic chess
		int isHuman;
		int isWhite;
		int classic;
		
		Object[] hoptions = {
				"Human",
				"Random Bot"
		};
		
		Object[] woptions = {
			"White",
			"Black"
		};
		
		Object[] coptions = {
				"3D Chess",
				"Classic"
		};
		
		
		classic = JOptionPane.showOptionDialog(this, "Play classic or 3D chess", "Game type", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, coptions, coptions[0]);
		isHuman = JOptionPane.showOptionDialog(this, "Player one is", "Player type", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, hoptions, hoptions[0]);
		isWhite = JOptionPane.showOptionDialog(this, "Player one is", "Player color", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, woptions, woptions[0]);
		
		if(isHuman == JOptionPane.YES_OPTION) {
			if(isWhite == JOptionPane.YES_OPTION) {
				player1 = new HumanPlayer(display, PlayerColor.WHITE);
			} else {
				player1 = new HumanPlayer(display, PlayerColor.BLACK);
			}
		} else {
			if(isWhite == JOptionPane.YES_OPTION){
				player1 = new BotPlayer(PlayerColor.WHITE); 
			} else {
				player1 = new BotPlayer(PlayerColor.BLACK);
			}
		}
		
		isHuman = JOptionPane.showOptionDialog(this, "Player two is", "Player type", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, hoptions, hoptions[0]);
		if(isHuman == JOptionPane.YES_OPTION) {
			if(isWhite == JOptionPane.YES_OPTION) {
				player2 = new HumanPlayer(display, PlayerColor.BLACK);
			} else {
				player2 = new HumanPlayer(display, PlayerColor.WHITE);
			}
		} else {
			if(isWhite == JOptionPane.YES_OPTION){
				player2 = new BotPlayer(PlayerColor.BLACK); 
			} else {
				player2 = new BotPlayer(PlayerColor.WHITE);
			}
		}
		
		// Inizialize the AI and the tell the chess game to use these AI
		humanPlayer = new HumanPlayer(display, PlayerColor.WHITE);
		botPlayer = new BotPlayer(PlayerColor.BLACK);
		if(player1.getColor() == PlayerColor.WHITE){
			chessGame = new ChessGame(player1, player2, classic == JOptionPane.YES_OPTION ? false : true);
		} else {
			chessGame = new ChessGame(player2, player1, classic == JOptionPane.YES_OPTION ? false : true);
		}
		
		// Setup the AI logic
		player1.setWorldInformation(chessGame.getWorldInformation());
		player2.setWorldInformation(chessGame.getWorldInformation());		
	}
	
	// Inizialize the display
	public void initDisplay() {
		// Tell the display to use the information given by the chess game
		display = new Display(chessGame.getWorldInformation());
		// Tell the player to be relative to the display (for the mouse click for example)
		if(player1 instanceof HumanPlayer) {
			((HumanPlayer) player1).setDisplay(display);
		}
		
		if(player2 instanceof HumanPlayer) {
			((HumanPlayer) player2).setDisplay(display);
		}
	}
	
	// Handle for a click on any button
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
 
		// A click on the up button will move the view one layer up on the z axis
		if(source == upButton){
			display.increaseHeight();
			// Refresh the display immediately
			display.repaint();
		} else if(source == downButton){ // A click on the down button will move the view one layer down on the z axis
			display.decreaseHeight();
			display.repaint();
		}
	}
}
