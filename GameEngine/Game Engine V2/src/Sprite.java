import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	protected Rectangle bounds;
	protected Image image;
	
	public Sprite() {
		bounds = new Rectangle();
		image = null;
	}
	
	public Sprite(Image image) {
		bounds = new Rectangle();
		setImage(image);
	}
	
	public void loadImage(String path) {
		try {
			image = ImageIO.read(new File(path));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = image.getWidth(null);
		bounds.height = image.getHeight(null);
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = image.getWidth(null);
		bounds.height = image.getHeight(null);
	}
}
