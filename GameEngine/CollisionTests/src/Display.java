import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Display extends JPanel implements KeyListener{
	private BufferedImage image;
	private float part = 0.0f;
	
	BufferedImage img1, img2;
	
	State state;
	
	enum State {
		NORMAL,
		FADING
	}
	
	public Display() {
		image = new BufferedImage(512, 512, BufferedImage.TYPE_INT_RGB);
		img1 = new BufferedImage(512, 512, BufferedImage.TYPE_INT_RGB);
		img2 = new BufferedImage(512, 512, BufferedImage.TYPE_INT_RGB);
		
		randomizeImage(image);
		
		state = State.NORMAL;
		
	}
	
	public void randomizeImage(BufferedImage image) {
		float lsupr = (float) (Math.random() * 255);
		float lsupg = (float) (Math.random() * 255);
		float lsupb = (float) (Math.random() * 255);
		
		float rsupr = (float) (Math.random() * 255);
		float rsupg = (float) (Math.random() * 255);
		float rsupb = (float) (Math.random() * 255);
		
		float ldownr = (float) (Math.random() * 255);
		float ldowng = (float) (Math.random() * 255);
		float ldownb = (float) (Math.random() * 255);
		
		float rdownr = (float) (Math.random() * 255);
		float rdowng = (float) (Math.random() * 255);
		float rdownb = (float) (Math.random() * 255);
		
		for(int x=0 ; x<512 ; ++x) {
			for(int y=0 ; y<512 ; ++y) {
				
				int r = (int) Interpolation.interpolateLinear(0, 0, 512, 512, lsupr, rsupr, ldownr, rdownr, x, y);
				int g = (int) Interpolation.interpolateLinear(0, 0, 512, 512, lsupg, rsupg, ldowng, rdowng, x, y);
				int b = (int) Interpolation.interpolateLinear(0, 0, 512, 512, lsupb, rsupb, ldownb, rdownb, x, y);
				
				int color = r * 65536 + g * 256 + b;
				
				image.setRGB(x, y, color);
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		switch(state) {
			case NORMAL:
				paintImage(g);
			
			case FADING:
				interpImage();
				
			default:
				g.drawImage(image, 0, 0, 512, 512, 0, 0, image.getWidth(), image.getHeight(), null);
		}
			

	}
	
	float timer;
	float fadingTime;
	long before;
	
	public void interpImage() {
		timer += System.currentTimeMillis() - before;
		if(timer >= fadingTime) {
			state = State.NORMAL;
			image.setData(img2.getData());
		} else {
			Interpolation.interpolate(img1, img2, image, (timer / fadingTime));
			getGraphics().drawImage(image, 0, 0, 512, 512, 0, 0, image.getWidth(), image.getHeight(), null);
			before = System.currentTimeMillis();
		}
		
	}
	
	public void paintImage(Graphics g) {
		g.drawImage(image, 0, 0, 512, 512, 0, 0, image.getWidth(), image.getHeight(), null);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_P)
			part += 0.1f;
		
		if(arg0.getKeyCode() == KeyEvent.VK_M)
			part -= 0.1f;
		
		if(state == State.NORMAL) {
			if(arg0.getKeyCode() == KeyEvent.VK_SPACE) {
				state = State.FADING;
				timer = 0;
				fadingTime = 2000;
				
				randomizeImage(img2);
				img1.setData(image.getData());
				before = System.currentTimeMillis();
			}
		}
		
		image = Interpolation.interpolate(img1, img2, part);
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
