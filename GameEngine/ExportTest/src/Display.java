import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;


public class Display extends JPanel {
	Image image;
	
	public Display() {
		super();
		this.setSize(500, 500);
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public void paintComponent(Graphics g) {
		if(image != null)
			g.drawImage(image, 0, 0, getWidth(), getHeight(), 0, 0, image.getWidth(null), image.getHeight(null), null);
	}
}
