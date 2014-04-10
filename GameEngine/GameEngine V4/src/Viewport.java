import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Viewport extends JPanel {
	private Sprite sprite;
	
	public Viewport() {
		super();
	}
	
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void paintComponent(Graphics g) {
		if(sprite != null && sprite.getImage() != null)
			g.drawImage(sprite.getImage(), 0, 0, getWidth(), getHeight(), 
					(int) sprite.getBound().x, (int) sprite.getBound().y, (int) sprite.getBound().getMaxX(), (int) sprite.getBound().getMaxY(),
					null);
;	}

}
