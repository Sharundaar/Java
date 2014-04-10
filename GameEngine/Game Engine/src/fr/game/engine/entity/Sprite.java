package fr.game.engine.entity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	private BufferedImage image;
	private String name;
	
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	public Sprite(String name, int width, int height) {
		this.name = name;
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		x1 = 0;
		y1 = 0;
		x2 = width;
		y2 = height;
	}
	
	public Sprite(String name, BufferedImage image) {
		this.image = image;
		this.name = name;
		
		x1 = 0;
		y1 = 0;
		if(image != null) {
			x2 = image.getWidth();
			y2 = image.getHeight();
		}
	}
	
	public Sprite() {
		// TODO Auto-generated constructor stub
		image = null;
		name = null;
		x1=0;
		y1=0;
		x2=0;
		y2=0;
	}
	
	public String getName() {
		return name;
	}

	static public BufferedImage loadImage(String path) throws IOException {
		BufferedImage result = null;
		
		try {
			result = ImageIO.read(new File(path));
		} catch(IOException e) {
			// System.out.println("Unable to load \""+path+"\"");
			throw e;
		}
		
		return result;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
		
		if(image != null) {
			x2 = image.getWidth();
			y2 = image.getHeight();
		}
	}
	
	public int getBoundX1() {
		return x1;
	}
	
	public int getBoundY1() {
		return y1;
	}
	
	public int getBoundX2() {
		return x2;
	}
	
	public int getBoundY2() {
		return y2;
	}
	
	public void setBoundX(int x) {
		if(x >= 0 && image != null && x <= image.getWidth()) {
			x1 = x;
		}
	}
	
	public void setBoundY(int y) {
		if(y >= 0 && image != null && y <= image.getHeight()) {
			y1 = y;
		}
	}
	
	public void setWidth(int width) {
		if (width >= 0) {
			int x = x1 + width;
			if(image != null && x <= image.getWidth()) {
				x2 = x;
			}
		}
	}
	
	public void setHeight(int height) {
		if (height >= 0) {
			int y = y1 + height;
			if(image != null && y <= image.getHeight()) {
				y2 = y;
			}
		}
	}
}
