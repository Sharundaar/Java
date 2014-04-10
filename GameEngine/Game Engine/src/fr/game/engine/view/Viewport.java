package fr.game.engine.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import fr.game.engine.entity.Entity;

@SuppressWarnings("serial")
public class Viewport extends JPanel {
	private Entity camera;
	private Font textFont;
	
	public Viewport(int width, int height) {
		super();
		this.setPreferredSize(new Dimension(width, height));
		textFont = new Font("Sentury 21", 20, 20);
	}
	
	public void ignoreRefresh(boolean arg) {
		this.setIgnoreRepaint(arg);
	}
	
	public void refresh(boolean val) {
		repaint();
	}
	
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	
	public void paintComponent(Graphics g) {
		// Render the camera
		if(camera != null) {
			camera.render();
			g.drawImage(camera.getSprite().getImage(), 0, 0, getWidth(), getHeight(), 0, 0, (int) camera.getSprite().getBoundX2(), (int) camera.getSprite().getBoundY2(), null);
			g.setColor(Color.white);
			g.setFont(textFont);
			g.drawString("Camera position :", 20, 20);
			g.drawString("X = "+camera.getPosX(), 20, 40);
			g.drawString("Y = "+camera.getPosY(), 20, 60);
		}
	}
}
