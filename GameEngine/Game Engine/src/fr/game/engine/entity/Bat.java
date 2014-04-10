package fr.game.engine.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import fr.game.engine.game.Game;

public class Bat extends Entity {

	public Bat(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	
		this.sprite = new Sprite("Bat", 25, 50);
		Graphics2D g = sprite.getImage().createGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, 25, 50);
		g.dispose();
		
		setWidth(25);
		setHeight(50);
	}
	
	@Override
	public void update(float dt) {
		if(game.getControl().isPressed(KeyEvent.VK_UP))
			translate(0, -0.5f * dt);
		
		if(game.getControl().isPressed(KeyEvent.VK_DOWN))
			translate(0, 0.5f * dt);
	}

}
