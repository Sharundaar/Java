import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Sprite {
	Image image;
	Rectangle bound;
	
	public Sprite() {
		bound = new Rectangle();
	}
	
	public Rectangle getBound() {
		return bound;
	}
	
	public void loadImage(String path) {
		Image image = null;
		try {
			image = ImageIO.read(new File(path));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		if(image != null) {
			bound.x = 0;
			bound.y = 0;
			bound.width = image.getWidth(null);
			bound.height = image.getHeight(null);
		}
		
		this.image = image;
	}

	public Image getImage() {
		// TODO Auto-generated method stub
		return image;
	}

	public void setImage(Image image) {
		// TODO Auto-generated method stub
		if(image != null) {
			this.image = image;
			bound.x = 0;
			bound.y = 0;
			bound.width = image.getWidth(null);
			bound.height = image.getHeight(null);
		}
	}
	
}
