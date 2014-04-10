package fr.game.engine.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import fr.game.engine.game.Game;

public class Ball extends Entity {

	public float dx = -0.2f;
	public float dy = 0.2f;
	
	private int wwidth = 15;
	private int hheight = 15;
	
	public Ball(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		
		sprite = new Sprite("Ball", wwidth, hheight);
		Graphics2D g = sprite.getImage().createGraphics();
		g.setColor(Color.white);
		g.fillOval(0, 0, wwidth, hheight);
		g.dispose();
		
		setWidth(wwidth);
		setHeight(hheight);
	}
	
	@Override
	public void update(float dt) {		
		translate(dx * dt, dy * dt);
	}

}
