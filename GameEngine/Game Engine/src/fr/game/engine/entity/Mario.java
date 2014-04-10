package fr.game.engine.entity;

import java.awt.event.KeyEvent;

import fr.game.engine.game.Control;
import fr.game.engine.game.Game;

public class Mario extends Entity {
	
	public Mario(Game game, float x, float y, float width, float height, Sprite sprite) {
		super(game, x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		
		Control ctr = game.getControl();
		if(ctr != null) {
			if(game.getControl().isPressed(KeyEvent.VK_RIGHT)) {
				translate(0.05f*dt, 0);
			}
			
			if(game.getControl().isPressed(KeyEvent.VK_LEFT)) {
				translate(-0.05f*dt, 0);
			}
			
			if(game.getControl().isPressed(KeyEvent.VK_DOWN)) {
				translate(0, 0.05f*dt);
			}
			
			if(game.getControl().isPressed(KeyEvent.VK_UP)) {
				translate(0, -0.05f*dt);
			}
		}
	}

}
