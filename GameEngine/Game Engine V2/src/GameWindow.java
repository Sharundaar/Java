import java.awt.FlowLayout;

import javax.swing.JFrame;


public class GameWindow extends JFrame {
	/**
	 * Auto-Generated serial number
	 */
	private static final long serialVersionUID = 8466768763113472051L;

	protected Viewport viewport;
	
	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 600;
	
	public GameWindow() {
		viewport = new Viewport();
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(viewport);
		
		this.setVisible(true);
	}
	
	public void resizeViewport(boolean resize) {
		if(resize) {
			this.removeAll();
			this.setLayout(null);
			this.setContentPane(viewport);
			this.repaint();
		} else {
			this.removeAll();
			this.setLayout(new FlowLayout());
			this.add(viewport);
		}
	}
	
	public Viewport getViewport() {
		return viewport;
	}
	
	
}
