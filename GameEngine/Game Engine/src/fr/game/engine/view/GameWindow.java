package fr.game.engine.view;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameWindow extends javax.swing.JFrame {
	private Viewport viewport;
	
	public GameWindow() {
		super("Game Window");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//setLayout(new FlowLayout());
		
		viewport = new Viewport(800, 600);
		add(viewport);
		
		setVisible(true);
	}
	
	public Viewport getViewport() {
		return viewport;
	}
	
	public void render() {
		viewport.repaint();
	}
}
