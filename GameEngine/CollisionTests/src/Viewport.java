import java.awt.Graphics;

import javax.swing.JPanel;


public class Viewport extends JPanel {
	private int width;
	private int height;
	
	private Camera camera;
	
	public Viewport() {
		camera = new Camera();
		width = 800;
		height = 600;
	}
	
	public Viewport(int width, int height) {
		camera = new Camera();
		this.width = width;
		this.height = height;
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(camera.render(), 0, 0, width, height, 0, 0, camera.getWidth(), camera.getHeight(), null);
	}
}
