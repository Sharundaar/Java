import java.awt.Graphics;

import javax.swing.JPanel;


public class Viewport extends JPanel {
	/**
	 * Auto-Generated serial number
	 */
	private static final long serialVersionUID = 3547779987557915954L;
	protected Sprite sprite;
	
	public Viewport() {
		sprite = null;
	}
	
	public Viewport(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public void paintComponent(Graphics g) {
		if(sprite != null && sprite.getImage() != null)
			g.drawImage(sprite.getImage(), 0, 0, getWidth(), getHeight(), (int) sprite.getBounds().x, (int) sprite.getBounds().y, (int) sprite.getBounds().getMaxX(), (int) sprite.getBounds().getMaxY(), null);
	}
}
